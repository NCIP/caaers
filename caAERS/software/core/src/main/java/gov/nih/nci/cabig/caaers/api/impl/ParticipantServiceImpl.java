package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ResponseDataType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantRef;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType.Assignments;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.ParticipantImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.migrator.ParticipantConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.ParticipantSynchronizer;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

public class ParticipantServiceImpl extends AbstractImportService implements ApplicationContextAware {
	
	private static Log logger = LogFactory.getLog(ParticipantServiceImpl.class);
	private ApplicationContext applicationContext;
	private MessageSource messageSource;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private ParticipantImportServiceImpl participantImportServiceImpl;
    private ParticipantConverter participantConverter;
    private ParticipantSynchronizer participantSynchronizer;
    //private DomainObjectValidator domainObjectValidator;
    private Validator validator;
    private EventFactory eventFactory;

    
	/**
	 * Method exisits only to be called from ImportController 
	 */
	public DomainObjectImportOutcome<Participant> processParticipant(ParticipantType xmlParticipant){
		logger.info("Entering processParticipant() in ParticipantServiceImpl");
		
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();
		DomainObjectImportOutcome<Participant> participantImportOutcome = null;
		Participant participant = new Participant();
		
		try{
        	participantConverter.convertParticipantDtoToParticipantDomain(xmlParticipant, participant);
        }catch(CaaersSystemException caEX){
        	participantImportOutcome = new DomainObjectImportOutcome<Participant>();
        	logger.error("ParticipantDto to ParticipantDomain Conversion Failed " , caEX);
        	participantImportOutcome.addErrorMessage("ParticipantDto to ParticipantDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
        }
        
        if(participantImportOutcome == null){
        	participantImportOutcome = participantImportServiceImpl.importParticipant(participant);
        	if(participantImportOutcome.isSavable()){
        		Participant dbParticipant = fetchParticipantByAssignment(participantImportOutcome.getImportedDomainObject(), caaersServiceResponse);
        		
        		if(dbParticipant != null){
        			logger.info("Participant Exists in caAERS trying to Update");
        			participantSynchronizer.migrate(dbParticipant, participantImportOutcome.getImportedDomainObject(), participantImportOutcome);
        			participantImportOutcome.setImportedDomainObject(dbParticipant);
        			logger.info("Participant in caAERS Updated");
        		}else if (caaersServiceResponse.getServiceResponse().getStatus() == Status.FAILED_TO_PROCESS) {
        			participantImportOutcome.addErrorMessage(caaersServiceResponse.getServiceResponse().getMessage(), DomainObjectImportOutcome.Severity.ERROR);
        		}else{
        			logger.info("New Participant to be Created");
        		}
        	}
        }
        logger.info("Leaving processParticipant() in ParticipantServiceImpl");
		return participantImportOutcome;
	}
	
	public String getStudySubjectIdentifierFromInXML(ParticipantType xmlParticipant) {
		String identifier = null;
		Assignments assignments = xmlParticipant.getAssignments();
		for(AssignmentType assignmentType : assignments.getAssignment()){
			identifier = assignmentType.getStudySubjectIdentifier();
			if(StringUtils.isNotEmpty(identifier) ){
				return identifier;
			}
		}
		return null;		
	}
	
	public Identifier getStudyIdentifierFromInXML(ParticipantType xmlParticipant) {
		Identifier identifier = null;
		Assignments assignments = xmlParticipant.getAssignments();
		for(AssignmentType assignmentType : assignments.getAssignment()){
			identifier = new Identifier();
			identifier.setType(assignmentType.getStudySite().getStudy().getIdentifiers().getIdentifier().getType().value());
			identifier.setValue(assignmentType.getStudySite().getStudy().getIdentifiers().getIdentifier().getValue());
			return identifier;
		}
		return identifier;		
	}
	public List<StudySearchableAjaxableDomainObject> getAuthorizedStudies(Identifier identifier) {
		List<StudySearchableAjaxableDomainObject> authorizedStudies = new ArrayList<StudySearchableAjaxableDomainObject>();
		Study study = fetchStudy(identifier);
		if (study!=null) {
			authorizedStudies = getAuthorizedStudies(identifier.getValue());
			return authorizedStudies;
		}
		return authorizedStudies;		
	}

	private String checkAuthorizedOrganizations (ParticipantType xmlParticipant) {
		Assignments assignments = xmlParticipant.getAssignments();
		for(AssignmentType assignmentType : assignments.getAssignment()){
			String organizationName = assignmentType.getStudySite().getOrganization().getName();
			String organizationNciInstituteCode = assignmentType.getStudySite().getOrganization().getNciInstituteCode();
			List<Organization> organizations = new ArrayList<Organization>();
			if (StringUtilities.isBlank(organizationNciInstituteCode)) {
            	//System.out.println("looking by name");
				organizations = getAuthorizedOrganizationsByNameOrNciId(organizationName,null);
            } else {
            	//System.out.println("looking by id");
            	organizations = getAuthorizedOrganizationsByNameOrNciId(null,organizationNciInstituteCode);
            }
			if (organizations.size() == 0 ) {
				return organizationNciInstituteCode + " : " + organizationName;
			}
		}
		return "ALL_ORGS_AUTH";
	}
	

	/**
	 * validates xml input and fetches participant based on study identifier and study subject identifier
	 * @param studySubjectIdentifier - string
	 * @param studyIdentifier - String
	 * @param xmlParticipant - xml participant object
	 * @param caaersServiceResponse - response
	 * @return Participant - returns retrieve participant, if not returns null, with response filled with appropriate messages
	 */
	private Participant validateInputsAndFetchParticipant(String studySubjectIdentifier, Identifier studyIdentifier, ParticipantType xmlParticipant, 
			CaaersServiceResponse caaersServiceResponse) {		
		
		if (studyIdentifier != null ) {
			List<StudySearchableAjaxableDomainObject> authorizedStudies = getAuthorizedStudies(studyIdentifier);
			if(authorizedStudies.size() == 0) {
				createNoStudyAuthorizationResponse(caaersServiceResponse, studyIdentifier);
				return null;
			}			
		}
		
		String errorMsg = checkAuthorizedOrganizations(xmlParticipant);
		if(!errorMsg.equals("ALL_ORGS_AUTH")) {
			createNoOrganizationAuthorizationResponse(caaersServiceResponse, errorMsg);
			return null;
		}
		return fetchParticipantByAssignment(studySubjectIdentifier, studyIdentifier, caaersServiceResponse);		
	}
	
	/**
	 * converts xml participant to the DomainImportOutcome<Participant>
	 * @param xmlParticipant - xml participant object
	 * @param studySubjectIdentifier - string
	 * @param caaersServiceResponse - response
	 * @param processStr - should be created/updated/deleted to be used in response message
	 * @return converted and imported domain object
	 */
	private DomainObjectImportOutcome<Participant> convertToImportedDomainObject(ParticipantType xmlParticipant, String studySubjectIdentifier,
			CaaersServiceResponse caaersServiceResponse, String processStr) {
		Participant participant = new Participant();
		try{
        	participantConverter.convertParticipantDtoToParticipantDomain(xmlParticipant, participant);
        }catch(CaaersSystemException caEX){
        	String message = messageSource.getMessage("WS_PMS_005", new String[] { caEX.getMessage() }, "", Locale
					.getDefault());
			logger.error(message, caEX);
			populateError(caaersServiceResponse, "WS_PMS_005", message);
			return null;
        }
        
        DomainObjectImportOutcome<Participant> participantImportOutcome =
        	participantImportServiceImpl.importParticipant(participant);
    	
    	Participant importedDomainObject = participantImportOutcome.getImportedDomainObject();
    	
		//List<String> errors = domainObjectValidator.validate(importedDomainObject);
    	Set<ConstraintViolation<Participant>> constraintViolations = validator.validate(importedDomainObject, Default.class);
		
		if( !participantImportOutcome.isSavable() || constraintViolations.size() > 0) {
    		String errMessage = messageSource.getMessage("WS_PMS_007", 
    				new String[] { importedDomainObject.getFirstName(), importedDomainObject.getLastName(), 
    				studySubjectIdentifier, processStr },
    				"", Locale.getDefault());
    		populateError(caaersServiceResponse, "WS_PMS_007", errMessage);
			logger.info(errMessage);
			
			List<String> messages = new ArrayList<String>(); 
			for(Message message : participantImportOutcome.getMessages()){
				messages.add(message.getMessage());
			}
			String valErrmsg = null;
			for (ConstraintViolation<Participant> violation : constraintViolations) {
				valErrmsg = violation.getMessage() 
					+ " (" + violation.getPropertyPath() 
					+ ") in " + participant.getClass().getSimpleName() 
					+ "(" + participant.getFullName() + ")";
				messages.add(valErrmsg);
			}
			Helper.populateErrorOutcome(caaersServiceResponse, null, null, null, messages);
			return null;
		}
		
		return participantImportOutcome;
	}
	
	public CaaersServiceResponse createParticipant(
			Participants xmlParticipants) {
		
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);
		
		Identifier studyIdentifier = getStudyIdentifierFromInXML(xmlParticipant);
		String studySubjectIdentifier = getStudySubjectIdentifierFromInXML(xmlParticipant);
		
		Participant dbParticipant = validateInputsAndFetchParticipant(studySubjectIdentifier, studyIdentifier, xmlParticipant, caaersServiceResponse);
		if( dbParticipant != null) {
			String message = messageSource.getMessage("WS_PMS_004", new String[] { studySubjectIdentifier, studyIdentifier.getValue() }, "", Locale
					.getDefault());
			logger.error(message);
			populateError(caaersServiceResponse, "WS_PMS_004", message);
			return caaersServiceResponse;
		} else {
			//remove the error message for participant not found, as this is create flow
			List<WsError> wsErrors = caaersServiceResponse.getServiceResponse().getWsError();
			if(wsErrors != null && wsErrors.size() == 1 && "WS_PMS_003".equals(wsErrors.get(0).getErrorCode()) ) {
				wsErrors.remove(0);		
				Helper.populateMessage(caaersServiceResponse, "");
			}
		}
		
		validateAssignmentSite(caaersServiceResponse, xmlParticipant, null);
		
		if(caaersServiceResponse.getServiceResponse().getStatus() == Status.FAILED_TO_PROCESS ) {
			return caaersServiceResponse;
		}
		//resetting the response object
		caaersServiceResponse = Helper.createResponse();
				
		DomainObjectImportOutcome<Participant> participantImportOutcome =
			convertToImportedDomainObject(xmlParticipant, studySubjectIdentifier, caaersServiceResponse, "created");
    	        
		if(participantImportOutcome != null) {
			Participant importedDomainObject = participantImportOutcome.getImportedDomainObject();
			participantDao.save(importedDomainObject);
			String message = messageSource.getMessage("WS_PMS_006", 
					new String[] { importedDomainObject.getFirstName(), importedDomainObject.getLastName(), studySubjectIdentifier },
					"", Locale.getDefault());    
			Helper.populateMessage(caaersServiceResponse, message);
			logger.info(message);
	        if(eventFactory != null) {
	        	eventFactory.publishEntityModifiedEvent(importedDomainObject, false);
	        }
		}   

		return caaersServiceResponse;
	}


	public CaaersServiceResponse updateParticipant(
			Participants xmlParticipants) {
		
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);
		
		Identifier studyIdentifier = getStudyIdentifierFromInXML(xmlParticipant);
		String studySubjectIdentifier = getStudySubjectIdentifierFromInXML(xmlParticipant);
		
		Participant dbParticipant = validateInputsAndFetchParticipant(studySubjectIdentifier, studyIdentifier, xmlParticipant, caaersServiceResponse);
		if( dbParticipant == null) {			
			return caaersServiceResponse;
		}
		
		validateAssignmentSite(caaersServiceResponse, xmlParticipant, dbParticipant);
		
		if(caaersServiceResponse.getServiceResponse().getStatus() == Status.FAILED_TO_PROCESS) {
			return caaersServiceResponse;
		}
		//resetting the response object
		caaersServiceResponse = Helper.createResponse();
				
		DomainObjectImportOutcome<Participant> participantImportOutcome =
			convertToImportedDomainObject(xmlParticipant, studySubjectIdentifier, caaersServiceResponse, "updated");
    	        
        if(participantImportOutcome != null){
        	Participant importedDomainObject = participantImportOutcome.getImportedDomainObject();
        	
			participantSynchronizer.migrate(dbParticipant, participantImportOutcome.getImportedDomainObject(), participantImportOutcome);
			participantImportOutcome.setImportedDomainObject(dbParticipant);
			participantDao.save(participantImportOutcome.getImportedDomainObject());
			
			String message = messageSource.getMessage("WS_PMS_008", 
					new String[] { importedDomainObject.getFirstName(), importedDomainObject.getLastName(), studySubjectIdentifier },
					"", Locale.getDefault());    
			Helper.populateMessage(caaersServiceResponse, message);
			logger.info(message);
	        if(eventFactory != null) {
	        	eventFactory.publishEntityModifiedEvent(importedDomainObject, false);
	        }
        }

		return caaersServiceResponse;
	}

	private void validateAssignmentSite(CaaersServiceResponse caaersServiceResponse, ParticipantType xmlParticipant,
			Participant dbParticipant) {
		
		OrganizationType xmlOrg = xmlParticipant.getAssignments().getAssignment().get(0).getStudySite().getOrganization();
		
		if (StringUtils.isEmpty(xmlOrg.getName()) || StringUtils.isEmpty(xmlOrg.getNciInstituteCode()) 
				|| ":".equals(xmlOrg.getNciInstituteCode().trim())) {
			String message = messageSource.getMessage("WS_PMS_017", new String[] {}, "", Locale
					.getDefault());
			logger.error(message);
			populateError(caaersServiceResponse, "WS_PMS_017", message);
			
		}
		
		if (dbParticipant == null) { //for create flow
			return;
		}
		
		Organization dbOrg = dbParticipant.getAssignments().get(0).getStudySite().getOrganization();
			
		if ( (dbOrg.getName() != null &&  !dbOrg.getName().equals(xmlOrg.getName()) )
				|| (dbOrg.getNciInstituteCode() != null &&  !dbOrg.getNciInstituteCode().equals(xmlOrg.getNciInstituteCode()) )
				) {
			String message = messageSource.getMessage("WS_PMS_018", new String[] {}, "", Locale
					.getDefault());
			logger.error(message);
			populateError(caaersServiceResponse, "WS_PMS_018", message);
		}
	}
	
	public CaaersServiceResponse deleteParticipant(Participants xmlParticipants) {
		
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);
		
		Identifier studyIdentifier = getStudyIdentifierFromInXML(xmlParticipant);
		String studySubjectIdentifier = getStudySubjectIdentifierFromInXML(xmlParticipant);
		
		Participant dbParticipant = validateInputsAndFetchParticipant(studySubjectIdentifier, studyIdentifier, xmlParticipant, caaersServiceResponse);
		if( dbParticipant == null) {
			return caaersServiceResponse;
		} else if(dbParticipant.getHasReportingPeriods()) {
			String message = messageSource.getMessage("WS_PMS_009", new String[] { studySubjectIdentifier, studyIdentifier.getValue() }, "", Locale
					.getDefault());
			logger.error(message);
			populateError(caaersServiceResponse, "WS_PMS_009", message);
		}
		
		if(caaersServiceResponse.getServiceResponse().getStatus() == Status.FAILED_TO_PROCESS) {
			return caaersServiceResponse;
		}
		//resetting the response object
		caaersServiceResponse = Helper.createResponse();
				
		DomainObjectImportOutcome<Participant> participantImportOutcome =
			convertToImportedDomainObject(xmlParticipant, studySubjectIdentifier, caaersServiceResponse, "deleted");
    	        
        if(participantImportOutcome != null){
        	Participant importedDomainObject = participantImportOutcome.getImportedDomainObject();
        	
			participantDao.delete(dbParticipant);
			String message = messageSource.getMessage("WS_PMS_010", 
					new String[] { importedDomainObject.getFirstName(), importedDomainObject.getLastName(), studySubjectIdentifier },
					"", Locale.getDefault());    
			Helper.populateMessage(caaersServiceResponse, message);
			logger.info(message);
	        if(eventFactory != null) {
	        	eventFactory.publishEntityModifiedEvent(importedDomainObject, false);
	        }
        }

		return caaersServiceResponse;
	}
	
	// returns the domain participant after converting to jaxb participant based on the input identifiers
	public CaaersServiceResponse getParticipant(ParticipantRef xmlParticipantRefType) {
		
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();
		
		Participant dbParticipant = null;
		
		//TODO : Only fetch By Assignment works..Need to add fetch by Identifier(mostly not required)       
        ParticipantRef.ParticipantAssignment assignment = xmlParticipantRefType.getParticipantAssignment();
        if(assignment == null || assignment.getStudyIdentifier() == null || assignment.getStudyIdentifier().getType() == null) {
        	populateError(caaersServiceResponse, "WS_PMS_013", messageSource.getMessage("WS_PMS_013", 
        			new String[]{},"",Locale.getDefault()));
        	return caaersServiceResponse;
        }
        Identifier studyId = new Identifier();
        studyId.setType(assignment.getStudyIdentifier().getType().value());
        studyId.setValue(assignment.getStudyIdentifier().getValue());
        dbParticipant = fetchParticipantByAssignment(assignment.getStudySubjectIdentifier(), studyId, caaersServiceResponse);
        
		if(dbParticipant != null ){
			caaersServiceResponse.getServiceResponse().setResponsecode("0");
			 ParticipantType dbParticipantType = new ParticipantType();
			 participantConverter.convertDomainParticipantToParticipantDto(dbParticipant, dbParticipantType);
			 caaersServiceResponse.getServiceResponse().setResponseData(new ResponseDataType());
			 Participants participants = new Participants();
			 participants.getParticipant().add(dbParticipantType);
			 caaersServiceResponse.getServiceResponse().getResponseData().setAny(participants);
		}

		return caaersServiceResponse;
	}
	
	private CaaersServiceResponse createNoStudyAuthorizationResponse(CaaersServiceResponse caaersServiceResponse, Identifier identifier){
		populateError(caaersServiceResponse, "WS_GEN_003", messageSource.getMessage("WS_GEN_003", new String[]{identifier.getValue()},"",Locale.getDefault()));
		return caaersServiceResponse;
	}
	
	private CaaersServiceResponse createNoOrganizationAuthorizationResponse(CaaersServiceResponse caaersServiceResponse, String errorMsg){
		populateError(caaersServiceResponse, "WS_GEN_005", messageSource.getMessage("WS_GEN_005", new String[]{errorMsg},"",Locale.getDefault()));
		return caaersServiceResponse;
	}
	
	private Participant fetchParticipant(Participant participant){
		Participant dbParticipant = null;
		for(Identifier identifier : participant.getIdentifiers()){
			dbParticipant = participantDao.getParticipantDesignByIdentifier(identifier);
			if(dbParticipant != null){
				break;
			}
			participantDao.evict(dbParticipant);
		}
		return dbParticipant;
	}
	
	private Participant fetchParticipantByAssignment(Participant participant,
			CaaersServiceResponse caaersServiceResponse) {
		Participant dbParticipant = null;
		for(StudyParticipantAssignment assignment : participant.getAssignments()){
			for(Identifier identifier : assignment.getStudySite().getStudy().getIdentifiers()) {
				dbParticipant = fetchParticipantByAssignment(assignment.getStudySubjectIdentifier(),
						identifier, caaersServiceResponse);
				if(dbParticipant != null){
					participantDao.evict(dbParticipant);
					return dbParticipant;
				}				
			}			
		}
		return dbParticipant;
	}
	
	private Participant fetchParticipantByAssignment(String studySubjectIdentifier,
			Identifier studyIdentifier, CaaersServiceResponse caaersServiceResponse) {
		Participant dbParticipant = null;
		
		if (StringUtils.isEmpty(studySubjectIdentifier)) {
			String message = messageSource.getMessage("WS_PMS_014", new String[] { studySubjectIdentifier }, "", Locale
					.getDefault());
			logger.error(message);
			populateError(caaersServiceResponse, "WS_PMS_014", message);
			return null;
		}
		
		if (studyIdentifier == null || StringUtils.isEmpty(studyIdentifier.getValue())) {
			String message = messageSource.getMessage("WS_PMS_015", new String[] { studyIdentifier.getValue() }, "", Locale
					.getDefault());
			logger.error(message);
			populateError(caaersServiceResponse, "WS_PMS_015", message);
			return null;
		}
		
		try {
			Study study = fetchStudy(studyIdentifier);
			if(study == null) {
				String message = messageSource.getMessage("WS_PMS_002", new String[] { studyIdentifier.getValue() }, "", Locale
						.getDefault());
				logger.error(message);
				populateError(caaersServiceResponse, "WS_PMS_002", message);	
				return dbParticipant;
			}
			
			ParticipantQuery pq = new ParticipantQuery();
			pq.joinStudy();
			pq.filterByStudySubjectIdentifier(studySubjectIdentifier, "=");
			pq.filterByStudyId(study.getId(), "=");
			
			List<Participant> dbParticipants = participantDao.searchParticipant(pq);			
			if (dbParticipants != null && dbParticipants.size() == 1) {
				logger.info("Participant registered to this study in caAERS");
				dbParticipant = dbParticipants.get(0);
			} else {
				String message = messageSource.getMessage("WS_PMS_003", new String[] { studySubjectIdentifier, studyIdentifier.getValue() }, "", Locale
						.getDefault());
				logger.error(message);
				populateError(caaersServiceResponse, "WS_PMS_003", message);				
			}
			
		} catch (Exception e) {
			String message = messageSource.getMessage("WS_PMS_016", new String[] { e.getMessage() }, "", Locale
					.getDefault());
			logger.error("Error retrieving participant", e);
			populateError(caaersServiceResponse, "WS_PMS_016", message);
			dbParticipant = null;
		}
		return dbParticipant;
	}
	
	private void populateError(CaaersServiceResponse caaersServiceResponse, String errorCode, String message) {
		Helper.populateError(caaersServiceResponse, errorCode, message);
		caaersServiceResponse.getServiceResponse().setMessage(message);
	}
	
	private Study fetchStudy(Identifier identifier) {
		Study dbStudy = null;
		dbStudy = studyDao.getByIdentifier(identifier);
		if(dbStudy != null){
			return dbStudy;
		}
		studyDao.evict(dbStudy);
		return dbStudy;
	}
	
	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public ParticipantImportServiceImpl getParticipantImportServiceImpl() {
		return participantImportServiceImpl;
	}

	public void setParticipantImportServiceImpl(
			ParticipantImportServiceImpl participantImportServiceImpl) {
		this.participantImportServiceImpl = participantImportServiceImpl;
	}
	
	public ParticipantConverter getParticipantConverter() {
		return participantConverter;
	}

	public void setParticipantConverter(ParticipantConverter participantConverter) {
		this.participantConverter = participantConverter;
	}

	public ParticipantSynchronizer getParticipantSynchronizer() {
		return participantSynchronizer;
	}

	public void setParticipantSynchronizer(
			ParticipantSynchronizer participantSynchronizer) {
		this.participantSynchronizer = participantSynchronizer;
	}
	
	/*public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator) {
		this.domainObjectValidator = domainObjectValidator;
	}*/
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}