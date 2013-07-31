/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;


import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.validation.ExpeditedAdverseEventReportValidator;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.service.AdeersIntegrationFacade;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.service.migrator.ExpeditedAdverseEventReportConverter;
import gov.nih.nci.cabig.caaers.service.migrator.report.ExpeditedReportMigrator;
import gov.nih.nci.cabig.caaers.service.synchronizer.report.ExpeditedAdverseEventReportSynchronizer;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class SafetyReportServiceImpl {
	private static Log logger = LogFactory.getLog(SafetyReportServiceImpl.class);
	
	/**	Expedited Report Converter. **/
	private ExpeditedAdverseEventReportConverter eaeConverter;

    private ParticipantServiceImpl participantService;

    private ParticipantDao participantDao;

    private StudyDao studyDao;
    

    private MessageSource messageSource;
    
    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
    
    /** Validator Service. **/
	private ExpeditedAdverseEventReportValidator aeReportValidator;
	
	/** Expedited Report Migrator. **/
	private ExpeditedReportMigrator aeReportMigrator;
	private ExpeditedAdverseEventReportSynchronizer aeReportSynchronizer;

    /** The report Repository. */
    private ReportRepository reportRepository;
    private ReportSubmissionService reportSubmissionService;

    private AdeersIntegrationFacade adeersIntegrationFacade;

    private EventFactory eventFactory;

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }

    public AdeersIntegrationFacade getAdeersIntegrationFacade() {
        return adeersIntegrationFacade;
    }

    public void setAdeersIntegrationFacade(AdeersIntegrationFacade adeersIntegrationFacade) {
        this.adeersIntegrationFacade = adeersIntegrationFacade;
    }

    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void setReportSubmissionService(ReportSubmissionService reportSubmissionService) {
        this.reportSubmissionService = reportSubmissionService;
    }

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
        if(subjectSrc == null ){
            errors.addValidationError("ER-SUB-1", "Subject information is missing in input message");
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

    public void migrate(ExpeditedAdverseEventReport aeSrcReport, ExpeditedAdverseEventReport aeDestReport, ValidationErrors errors){
        try{
             adeersIntegrationFacade.updateStudy(aeSrcReport.getStudy().getId(), true);
        }catch (Exception e){
            logger.warn("Study synchronization failed.", e);
        }
        DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        aeReportMigrator.migrate(aeSrcReport, aeDestReport, outCome);
        if(outCome.hasErrors()) errors.addValidationErrors(outCome.getValidationErrors().getErrors());
    }
    
    private void transferStudySubjectIfRequired(ExpeditedAdverseEventReport aeSrcReport, ExpeditedAdverseEventReport aeDestReport,ValidationErrors errors){
    	try {
			StudySite originalSite = aeDestReport.getAssignment().getStudySite();
			Participant dbParticipant = aeDestReport.getAssignment().getParticipant();
			Organization organizationTransferredTo = aeSrcReport.getAssignment().getStudySite().getOrganization();
			  if(!aeDestReport.getAssignment().getStudySite().getOrganization().getNciInstituteCode().
				   equals(aeSrcReport.getAssignment().getStudySite().getOrganization().getNciInstituteCode())){
			  participantService.transferParticipant(dbParticipant, originalSite, organizationTransferredTo, errors);
			  }
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
    }

    /**
     * Will create a Report and associate it to the ExpeditedAdverseEventReport
     * @param report
     * @param aeReport
     * @return
     */
    public Report createReport(Report report, ExpeditedAdverseEventReport aeReport){
        Report newReport = reportRepository.createReport(report.getReportDefinition(), aeReport) ;
        newReport.copy(report);
        reportRepository.save(newReport);
        return newReport;
    }

    /**
     * Will update a Report associated it to the ExpeditedAdverseEventReport and in parallel withdraw any Notifications
     * that are submitted previously for the Report being withdrawn.
     * @param report
     * @param aeReport
     * @return
     */
    public Report withdrawReport(Report report, ExpeditedAdverseEventReport aeReport){
        reportRepository.withdrawReport(report);
        reportRepository.withdrawExternalReport(aeReport, report);
        return report;
    }

    /**
     * Will amend the Report
     * @param report
     * @param aeReport
     * @return
     */
    public Report amendReport(Report report, ExpeditedAdverseEventReport aeReport){
        reportRepository.amendReport(report);
        return report;
    }

    /**
     * Will unamend an older version when a new revision of the report is withdrawn.
     * @param report
     * @param aeReport
     * @return
     */
    public Report unAmendReport(Report report, ExpeditedAdverseEventReport aeReport){
        reportRepository.unAmendReport(report);
        return report;
    }

    /**
     * Will update an ExpeditedAdverseEventReport, and return the list of Reports that got updated.
     * @param aeSrcReport
     * @param dbReport
     * @param errors
     * @return
     */
    public List<Report> updateSafetyReport(ExpeditedAdverseEventReport aeSrcReport, ExpeditedAdverseEventReport dbReport, ValidationErrors errors){
        List<Report> reportsAffected = new ArrayList<Report>();
        ExpeditedAdverseEventReport aeDestReport = new ExpeditedAdverseEventReport();
        migrate(aeSrcReport, aeDestReport, errors);
        if(errors.hasErrors()) return reportsAffected;
        DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        aeReportSynchronizer.migrate(aeDestReport, dbReport, outCome);
        if(outCome.hasErrors()) errors.addValidationErrors(outCome.getValidationErrors().getErrors());
        // Update AE Signatures.
        aeDestReport.updateAESignatures();
        
        expeditedAdverseEventReportDao.save(dbReport);
        
        transferStudySubjectIfRequired(aeSrcReport, aeDestReport, errors);

        dbReport.getAssignment().synchronizeMedicalHistoryFromReportToAssignment(dbReport);
        studyParticipantAssignmentDao.save(dbReport.getAssignment());


        if(aeDestReport.getReports() == null || aeDestReport.getReports().isEmpty()) {
            //withdraw active reports
            List<Report> reportsToWithdraw = dbReport.getActiveReports();
            for(Report srcReport : reportsToWithdraw){
                withdrawReport(srcReport, dbReport);
            }
        } else {
            //create amend or withdraw reports
            for(Report srcReport : aeDestReport.getReports()){
                List<Report> reportsToAmend = dbReport.findReportsToAmmend(srcReport.getReportDefinition());
                for(Report  report: reportsToAmend){
                    reportsAffected.add(amendReport(report, dbReport));
                }
                List<Report> reportsToWithdraw = dbReport.findReportsToWithdraw(srcReport.getReportDefinition());
                for(Report  report: reportsToWithdraw){
                    withdrawReport(report, dbReport);
                }
                List<Report> reportsToEdit = dbReport.findReportsToEdit(srcReport.getReportDefinition());
                if(reportsToEdit.isEmpty()) {
                    reportsAffected.add(createReport(srcReport, dbReport));
                }

                //TODO : BJ implement unammend feature
            }
        }

        if(getEventFactory() != null) getEventFactory().publishEntityModifiedEvent(aeDestReport);
        return reportsAffected;
    }

    /**
     * Will create an ExpeditedAdverseEventReport, then will return all the Reports that got created.
     * @param aeSrcReport
     * @param aeDestReport
     * @param errors
     * @return
     */
    public  List<Report> createSafetyReport(ExpeditedAdverseEventReport aeSrcReport, ExpeditedAdverseEventReport aeDestReport, ValidationErrors errors){
        List<Report> reportsAffected = new ArrayList<Report>();

        //Call the Migration
        migrate(aeSrcReport, aeDestReport, errors);
        if(errors.hasErrors()) return reportsAffected;
        
        for(AdverseEvent ae : aeDestReport.getAdverseEvents()){
            ae.setReport(aeDestReport);
        }
        // Set the signature for the AE.
        aeDestReport.updateAESignatures();
        
        //Call the ExpediteReportDao and save this report.
        expeditedAdverseEventReportDao.save(aeDestReport);
        
        // transfer the study subject if required.
        transferStudySubjectIfRequired(aeSrcReport, aeDestReport, errors);

        aeDestReport.getAssignment().synchronizeMedicalHistoryFromReportToAssignment(aeDestReport);
        studyParticipantAssignmentDao.save(aeDestReport.getAssignment());

        // Deep copy the reports as it is throwing ConcurrentModification Exception.
        List<Report> reports = new ArrayList(aeDestReport.getReports());
        aeDestReport.getReports().clear();
        List<Report> newReports = new ArrayList<Report>();
        // Save the report(s) after Migration.
        for ( Report rpt: reports )    {
            reportsAffected.add(createReport(rpt, aeDestReport));
        }

        if(getEventFactory() != null) getEventFactory().publishEntityModifiedEvent(aeDestReport);
        return reportsAffected;
    }


    /**
     * Will create/update the ExpeditedAdverseEventReport and then will submit the Reports modified to external agency.
     * @param adverseEventReport
     * @return
     */
    @Transactional(readOnly=false)
    public CaaersServiceResponse submitSafetyReport(AdverseEventReport adverseEventReport) {
        CaaersServiceResponse response = Helper.createResponse();
        try{
            List<Report> reportsAffected = new ArrayList<Report>();
            ValidationErrors errors = createOrUpdateSafetyReport(adverseEventReport, reportsAffected);
            if(errors.hasErrors()) populateErrors(response, errors);

            //submit report
          /*  for(Report report : reportsAffected){
                reportSubmissionService.submitReport(report);
            }*/
        }catch (Exception e){
            logger.error("Unable to Create/Update a Report from Safety Management Service", e);
            Helper.populateError(response, "WS_GEN_000",e.getMessage() );
        }
        return response;
    }

    /**
     * Will save the ExpeditedAdverseEventReport
     * @param adverseEventReport
     * @return
     */
    @Transactional(readOnly=false)
	public CaaersServiceResponse saveSafetyReport(AdverseEventReport adverseEventReport) {
        CaaersServiceResponse response = Helper.createResponse();
        try{
            ValidationErrors errors = createOrUpdateSafetyReport(adverseEventReport, new ArrayList<Report>());
            if(errors.hasErrors()) populateErrors(response, errors);
        }catch (Exception e){
            logger.error("Unable to Create/Update a Report from Safety Management Service", e);
            Helper.populateError(response, "WS_GEN_000",e.getMessage() );
        }
        return response;
    }

    /**
     * Will create or update an ExpeditedAdverseEventReport, and updates the reportsAffected with the Reports that
     * got amended/edited/created.
     * @param adverseEventReport
     * @param reportsAffected
     * @return
     * @throws Exception
     */
    public ValidationErrors createOrUpdateSafetyReport(AdverseEventReport adverseEventReport, List<Report> reportsAffected) throws Exception {
       ValidationErrors errors = new ValidationErrors();

	   try {
		   
           // 1. Call the Converter(s) to construct the domain object.
           ExpeditedAdverseEventReport aeSrcReport = eaeConverter.convert(adverseEventReport);
           //2. Do some basic validations (if needed)

           //3. Determine the flow, create vs update
           String externalId = aeSrcReport.getExternalId();
           ExpeditedAdverseEventReport dbAeReport = externalId != null ? expeditedAdverseEventReportDao.getByExternalId(externalId) : null;

           if(dbAeReport == null){
               //create flow
                reportsAffected.addAll(createSafetyReport(aeSrcReport, new ExpeditedAdverseEventReport(), errors));
           }else{
               //update flow
               reportsAffected.addAll(updateSafetyReport(aeSrcReport, dbAeReport, errors));
           }

           if(errors.hasErrors())  {
               expeditedAdverseEventReportDao.clearSession();
               return errors;
           }

       }catch(Exception e) {
           expeditedAdverseEventReportDao.clearSession();
		   logger.error("Unable to Create/Update a Report from Safety Management Service", e);

	   }
       return errors;
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

    public ExpeditedAdverseEventReportSynchronizer getAeReportSynchronizer() {
        return aeReportSynchronizer;
    }

    public void setAeReportSynchronizer(ExpeditedAdverseEventReportSynchronizer aeReportSynchronizer) {
        this.aeReportSynchronizer = aeReportSynchronizer;
    }

    public StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
        return studyParticipantAssignmentDao;
    }

    public void setStudyParticipantAssignmentDao(StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
        this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
    }
}
