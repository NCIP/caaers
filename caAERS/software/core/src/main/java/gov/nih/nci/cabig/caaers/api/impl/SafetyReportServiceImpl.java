package gov.nih.nci.cabig.caaers.api.impl;


import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.validation.ExpeditedAdverseEventReportValidator;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.ExpeditedAdverseEventReportConverter;
import gov.nih.nci.cabig.caaers.service.migrator.report.ExpeditedReportMigrator;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Locale;

public class SafetyReportServiceImpl {
	private static Log logger = LogFactory.getLog(SafetyReportServiceImpl.class);
	
	/**	Expedited Report Converter. **/
	private ExpeditedAdverseEventReportConverter eaeConverter;

    private ParticipantServiceImpl participantService;

    private ParticipantDao participantDao;

    private StudyDao studyDao;
    
    private AdverseEventManagementServiceImpl adverseEventManagementService;
    
    private MessageSource messageSource;
    
    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    
    /** Validator Service. **/
	private ExpeditedAdverseEventReportValidator aeReportValidator;
	
	/** Expedited Report Migrator. **/
	private ExpeditedReportMigrator aeReportMigrator;


    /**
     * Does the validation of the input message
     * @param aeSrcReport
     * @return
     */
    private ValidationErrors validateInput(ExpeditedAdverseEventReport aeSrcReport){

        AdverseEventReportingPeriod rpSrc = aeSrcReport.getReportingPeriod();
        ValidationErrors errors = new ValidationErrors();

        //do I have reporting period ?
        if(rpSrc == null){
            errors.addValidationError( "ER-RP-1", "Missing Reporting period and Adverse event in input message");
            return errors;
        }

        //do I have AEs ?
        if(rpSrc.getAdverseEvents() == null || rpSrc.getAdverseEvents().isEmpty()){
            errors.addValidationError("WS_AEMS_025", "Missing Adverse Events in the input message");
            return errors;
        }

        //do I have study site details ?
        StudySite studySiteSrc = rpSrc.getStudySite();
        if(studySiteSrc == null){
            errors.addValidationError("WS_AEMS_034", "StudySite information is missing in input message");
            return errors;
        }

        if(studySiteSrc.getOrganization() == null || studySiteSrc.getOrganization().getNciInstituteCode() == null){
            errors.addValidationError("ER-STU-3", "Missing Study Site details - Organization NCI code");
            return errors;
        }

        //do I have study details ?
        Study studySrc = rpSrc.getStudy();
        if(studySrc == null || studySrc.getFundingSponsorIdentifierValue() == null){
            logger.error("Missing study identifier");
            errors.addValidationError("WS_AEMS_034",  "Missing Study Identifier" );
            return errors;
        }
        if(studySrc.getFundingSponsorIdentifierValue() == null){
            logger.error("Missing study identifier");
            errors.addValidationError("WS_AEMS_034",  "Missing Study Identifier");
            return errors;
        }

        //do I have subject details ?
        Participant subjectSrc = rpSrc.getParticipant();
        if(subjectSrc == null || subjectSrc.getAssignments() == null || subjectSrc.getAssignments().isEmpty()){
            errors.addValidationError("ER-SUB-1", "Subject information is missing in input message");
            return errors;
        }
        StudyParticipantAssignment srcAssignment = subjectSrc.getAssignments().get(0);
        if(srcAssignment == null|| srcAssignment.getStudySubjectIdentifier() == null){
            logger.error("Missing assignment or study subject identifier");
            errors.addValidationError("WS_AEMS_032", "Missing study subject identifier in the input");
            return errors;
        }



        return errors;
    }

    private CaaersServiceResponse populateErrors(CaaersServiceResponse response, ValidationErrors errors){
        logger.error("Adverse Event Management Service create or update call failed :" + String.valueOf(errors));
        for(ValidationError ve : errors.getErrors())  {
            String message = messageSource.getMessage(ve.getCode(),  ve.getReplacementVariables(), ve.getMessage(), Locale.getDefault());
            Helper.populateError(response, ve.getCode(), message);
        }
        return response;
    }

    public ExpeditedAdverseEventReport createSafetyReport(ExpeditedAdverseEventReport aeReport, ValidationErrors errors){
        Study studySrc = aeReport.getStudy();
        Participant subjectSrc = aeReport.getParticipant();
        StudyParticipantAssignment srcAssignment = subjectSrc.getAssignments().get(0);

        //does the study exist ?
        if ( studySrc == null ) {
        	studySrc = studyDao.getByIdentifier(studySrc.getFundingSponsorIdentifier());
	        if(studySrc == null){
	            logger.error("Study not present in caAERS with the sponsor identifier : " + studySrc.getFundingSponsorIdentifierValue());
	            errors.addValidationError("WS_AEMS_003", "Study with sponsor identifier " + studySrc.getFundingSponsorIdentifierValue() +" does not exist in caAERS");
	            return null;
	        }
        }

        //Does the subject exist ?
        //NOTE :- With this logic, the participant will be recreated for every assignment.
        ParticipantQuery pq = new ParticipantQuery();
        pq.filterByStudySubjectIdentifier(srcAssignment.getStudySubjectIdentifier());
        pq.filterByStudyId(studySrc.getId());

        List<Participant> dbParticipants = participantDao.searchParticipant(pq);
        if(dbParticipants == null || dbParticipants.isEmpty()){
            //create the participant.
            ParticipantType xmlParticipant = new ParticipantType();
            participantService.getParticipantConverter().convertDomainParticipantToParticipantDto(subjectSrc, xmlParticipant);
            Participants participants = new Participants();
            participants.getParticipant().add(xmlParticipant);
            CaaersServiceResponse participantResponse = participantService.createParticipant(participants);
            logger.info("Response from Participant Service " + String.valueOf(participantResponse));
            if(!participantResponse.getServiceResponse().getWsError().isEmpty()){
                logger.error("Error thrown by Participant Service " + String.valueOf(participantResponse.getServiceResponse().getWsError()));
                for(WsError wsError : participantResponse.getServiceResponse().getWsError()) {
                    errors.addValidationError(wsError.getErrorCode(), wsError.getErrorDesc());
                }
                return null;
            }
        }



        //Call Ae Management
        AdverseEventReportingPeriod reportingPeriod = adverseEventManagementService.createOrUpdateAdverseEvents(aeReport.getReportingPeriod(), errors);
        logger.info("Created or Updated reporting period returned :" + reportingPeriod == null ? "NULL" : reportingPeriod.getId());
        if(errors.hasErrors()){
            logger.error("Adverse Event Management Service create or update call failed :" + String.valueOf(errors));
            return null;
        }

        //Call the Migration
        ExpeditedAdverseEventReport aeDestReport = new ExpeditedAdverseEventReport();
        DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        aeReportMigrator.migrate(aeReport, aeDestReport, outCome);
        errors = outCome.getValidationErrors();
        if(errors.hasErrors()){
            errors.addValidationErrors(errors.getErrors());
            return null;
        }

        //Call the ExpediteReportDao and save this report.
        expeditedAdverseEventReportDao.save(aeDestReport);

        return aeDestReport;
    }

	@Transactional(readOnly=false)
	public CaaersServiceResponse submitSafetyReport(AdverseEventReport adverseEventReport) {

	   CaaersServiceResponse response = Helper.createResponse();
	   
	   try {
		   
           // 1. Call the Converter(s) to construct the domain object.
           ExpeditedAdverseEventReport aeSrcReport = eaeConverter.convert(adverseEventReport);
           
           //2. Run the validation (basic)
           ValidationErrors errors = validateInput(aeSrcReport);
           if(errors.hasErrors()) return populateErrors(response, errors);
           
           // 2. Call the GenericValidator to make sure input is correct.
		   Errors reportValidatorErrors = new BindException(aeSrcReport, "ExpeditedAdverseEventReport");
		   aeReportValidator.validate( aeSrcReport, reportValidatorErrors);
		   
		   if ( reportValidatorErrors.hasErrors()) {
			   Helper.populateError(response, "GEN_ORH_001", "Error(s) occured during Valdation step.");
			   return response;
		   }

           //TODO : below call will change based on create or Amend flow
           //3. Save the report
           ExpeditedAdverseEventReport aeReport = createSafetyReport(aeSrcReport, errors);
           if(errors.hasErrors()) return populateErrors(response, errors);
	       
	   }catch(Exception e) {
		   logger.error("Unable to Create a Report from Safety Management Service", e);
		   Helper.populateError(response, "WS_GEN_000",e.getMessage() );
	   }
       return response;
	}

    public ExpeditedAdverseEventReportConverter getEaeConverter() {
        return eaeConverter;
    }

    public void setEaeConverter(ExpeditedAdverseEventReportConverter eaeConverter) {
        this.eaeConverter = eaeConverter;
    }

    public ParticipantServiceImpl getParticipantService() {
        return participantService;
    }

    public void setParticipantService(ParticipantServiceImpl participantService) {
        this.participantService = participantService;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public AdverseEventManagementServiceImpl getAdverseEventManagementService() {
        return adverseEventManagementService;
    }

    public void setAdverseEventManagementService(AdverseEventManagementServiceImpl adverseEventManagementService) {
        this.adverseEventManagementService = adverseEventManagementService;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
        return expeditedAdverseEventReportDao;
    }

    public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    public ExpeditedReportMigrator getAeReportMigrator() {
        return aeReportMigrator;
    }

    public void setAeReportMigrator(ExpeditedReportMigrator aeReportMigrator) {
        this.aeReportMigrator = aeReportMigrator;
    }

	public ExpeditedAdverseEventReportValidator getAeReportValidator() {
		return aeReportValidator;
	}

	public void setAeReportValidator(
			ExpeditedAdverseEventReportValidator aeReportValidator) {
		this.aeReportValidator = aeReportValidator;
	}
}