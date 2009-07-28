package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.api.ParticipantService;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.ParticipantImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.service.migrator.ParticipantConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.ParticipantSynchronizer;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.webservice.OrganizationType;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType;
import gov.nih.nci.cabig.caaers.webservice.Study.StudyOrganizations;
import gov.nih.nci.cabig.caaers.webservice.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;
import gov.nih.nci.cabig.caaers.webservice.participant.Response;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType.Assignments;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ParticipantService", serviceName="ParticipantService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ParticipantServiceImpl extends AbstractImportService implements ParticipantService,ApplicationContextAware,MessageSourceAware {
	
	private static Log logger = LogFactory.getLog(ParticipantServiceImpl.class);
	private ApplicationContext applicationContext;
	private MessageSource messageSource;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private StudyRepository studyRepository;
    private ParticipantImportServiceImpl participantImportServiceImpl;
    private ParticipantConverter participantConverter;
    private ParticipantSynchronizer participantSynchronizer;
    private DomainObjectValidator domainObjectValidator;

    
	/**
	 * Method exisits only to be called from ImportController 
	 * @param participantDto
	 */
	public DomainObjectImportOutcome<Participant> processParticipant(ParticipantType xmlParticipant){
		logger.info("Entering processParticipant() in ParticipantServiceImpl");
		
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
				//Check if Study Exists; If Exists then update
        		Participant dbParticipant = fetchParticipant(participantImportOutcome.getImportedDomainObject());
        		if(dbParticipant != null){
        			logger.info("Participant Exists in caAERS trying to Update");
        			participantSynchronizer.migrate(dbParticipant, participantImportOutcome.getImportedDomainObject(), participantImportOutcome);
        			participantImportOutcome.setImportedDomainObject(dbParticipant);
        			logger.info("Participant in caAERS Updated");
        		}else{
        			logger.info("New Participant to be Created");
        		}
        	}
        }
        logger.info("Leaving processParticipant() in ParticipantServiceImpl");
		return participantImportOutcome;
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
	public CaaersServiceResponse createParticipant(
			Participants xmlParticipants) {
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);

		CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
		Response participantServiceResponse = new Response();
		Participant participant = new Participant();
		DomainObjectImportOutcome<Participant> participantImportOutcome = null;
		Identifier identifier = getStudyIdentifierFromInXML(xmlParticipant);
		if (identifier != null ) {
			List<StudySearchableAjaxableDomainObject> authorizedStudies = getAuthorizedStudies(identifier);
			if(authorizedStudies.size() == 0) {
				String message = messageSource.getMessage("WS_AEMS_027", new String[]{identifier.getValue()},"",Locale.getDefault());
				participantServiceResponse.setResponsecode("WS_AEMS_027");
				participantServiceResponse.setDescription(message);
				caaersServiceResponse.setResponse(participantServiceResponse);
				return caaersServiceResponse;
			}			
		}
		
		String errorMsg = checkAuthorizedOrganizations(xmlParticipant);
		if(!errorMsg.equals("ALL_ORGS_AUTH")) {
			String message = messageSource.getMessage("WS_AEMS_029", new String[]{errorMsg},"",Locale.getDefault());
			participantServiceResponse.setResponsecode("WS_AEMS_029");
			participantServiceResponse.setDescription(message);
			caaersServiceResponse.setResponse(participantServiceResponse);
			return caaersServiceResponse;
		}		
		
        try{
        	participantConverter.convertParticipantDtoToParticipantDomain(xmlParticipant, participant);
        }catch(CaaersSystemException caEX){
        	participantImportOutcome = new DomainObjectImportOutcome<Participant>();
        	logger.error("ParticipantDto to ParticipantDomain Conversion Failed " , caEX);
        	participantImportOutcome.addErrorMessage("ParticipantDto to ParticipantDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
        	participantServiceResponse.setResponsecode("1");
        	participantServiceResponse.setDescription("ParticipantDto to ParticipantDomain Conversion Failed ");
        }
        
        if(participantImportOutcome == null){
        	participantImportOutcome = participantImportServiceImpl.importParticipant(participant);
        	if(fetchParticipant(participantImportOutcome.getImportedDomainObject()) != null){
    			participantImportOutcome.addErrorMessage(participant.getClass().getSimpleName() + " identifier already exists. ", Severity.ERROR);
    			participantServiceResponse.setResponsecode("1");
    			participantServiceResponse.setDescription("Participant exists in caAERS, which is identifiable by one of the identifiers provided");
    		}
        	
        	StringBuilder sb = new StringBuilder("Participant ");
    		sb.append(participantImportOutcome.getImportedDomainObject().getFirstName()).append(" ");
    		sb.append(participantImportOutcome.getImportedDomainObject().getLastName());
    		List<String> errors = domainObjectValidator.validate(participantImportOutcome.getImportedDomainObject());
        	if(participantImportOutcome.isSavable() && errors.size() == 0){
        		participantDao.save(participantImportOutcome.getImportedDomainObject());
        		participantServiceResponse.setResponsecode("0");
        		sb.append("  Created in caAERS");
        		participantServiceResponse.setDescription(sb.toString());
				logger.info(sb.toString());
        	}else{
        		participantServiceResponse.setResponsecode("1");
        		sb.append("  could not be created in caAERS");
        		participantServiceResponse.setDescription(sb.toString());
				logger.info(sb.toString());
				List<String> messages = new ArrayList<String>(); 
				for(Message message : participantImportOutcome.getMessages()){
					messages.add(message.getMessage());
				}
				for(String errMsg : errors){
					messages.add(errMsg);
	        	}
				participantServiceResponse.setMessage(messages);
			}
        }

		caaersServiceResponse.setResponse(participantServiceResponse);
		return caaersServiceResponse;
	}

	public CaaersServiceResponse updateParticipant(
			Participants xmlParticipants) {
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);
		
		CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
		Response participantServiceResponse = new Response();

		Identifier identifier = getStudyIdentifierFromInXML(xmlParticipant);
		if (identifier != null ) {
			List<StudySearchableAjaxableDomainObject> authorizedStudies = getAuthorizedStudies(identifier);
			if(authorizedStudies.size() == 0) {
				String message = messageSource.getMessage("WS_AEMS_027", new String[]{identifier.getValue()},"",Locale.getDefault());
				participantServiceResponse.setResponsecode("WS_AEMS_027");
				participantServiceResponse.setDescription(message);
				caaersServiceResponse.setResponse(participantServiceResponse);
				return caaersServiceResponse;
			}			
		}

		String errorMsg = checkAuthorizedOrganizations(xmlParticipant);
		if(!errorMsg.equals("ALL_ORGS_AUTH")) {
			String message = messageSource.getMessage("WS_AEMS_029", new String[]{errorMsg},"",Locale.getDefault());
			participantServiceResponse.setResponsecode("WS_AEMS_029");
			participantServiceResponse.setDescription(message);
			caaersServiceResponse.setResponse(participantServiceResponse);
			return caaersServiceResponse;
		}
		
		DomainObjectImportOutcome<Participant> participantImportOutcome = null;
		Participant participant = new Participant();
       
        try{
        	participantConverter.convertParticipantDtoToParticipantDomain(xmlParticipant, participant);
        }catch(CaaersSystemException caEX){
        	participantImportOutcome = new DomainObjectImportOutcome<Participant>();
        	logger.error("ParticipantDto to ParticipantDomain Conversion Failed " , caEX);
        	participantImportOutcome.addErrorMessage("ParticipantDto to ParticipantDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
        	participantServiceResponse.setResponsecode("1");
        	participantServiceResponse.setDescription("ParticipantDto to ParticipantDomain Conversion Failed ");
        }
        
        if(participantImportOutcome == null){
        	participantImportOutcome = participantImportServiceImpl.importParticipant(participant);
        	
        	StringBuilder sb = new StringBuilder("Participant ");
    		sb.append(participantImportOutcome.getImportedDomainObject().getFirstName()).append(" ");
    		sb.append(participantImportOutcome.getImportedDomainObject().getLastName());
    		List<String> errors = domainObjectValidator.validate(participantImportOutcome.getImportedDomainObject());
        	if(participantImportOutcome.isSavable() && errors.size() == 0){
        		Participant dbParticipant = fetchParticipant(participantImportOutcome.getImportedDomainObject());
        		if(dbParticipant != null){
        			participantSynchronizer.migrate(dbParticipant, participantImportOutcome.getImportedDomainObject(), participantImportOutcome);
        			participantImportOutcome.setImportedDomainObject(dbParticipant);
        			participantDao.save(participantImportOutcome.getImportedDomainObject());
            		participantServiceResponse.setResponsecode("0");
            		sb.append("  Updated in caAERS");
            		participantServiceResponse.setDescription(sb.toString());
    				logger.info(sb.toString());
        		}else{
        			participantServiceResponse.setResponsecode("1");
        			sb.append("  Does not exist in caAERS");
        			participantServiceResponse.setDescription(sb.toString());
        			participantImportOutcome.addErrorMessage(sb.toString() +  " Does not exist in caAERS", DomainObjectImportOutcome.Severity.ERROR);
        		}
        	}else{
        		participantServiceResponse.setResponsecode("1");
        		sb.append("  could not be updated in caAERS");
        		participantServiceResponse.setDescription(sb.toString());
				logger.info(sb.toString());
				List<String> messages = new ArrayList<String>(); 
				for(Message message : participantImportOutcome.getMessages()){
					messages.add(message.getMessage());
				}
				for(String errMsg : errors){
					messages.add(errMsg);
	        	}
				participantServiceResponse.setMessage(messages);
        	}
        }

		caaersServiceResponse.setResponse(participantServiceResponse);
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
	private Study fetchStudy(Identifier identifier){
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

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}


	public ParticipantSynchronizer getParticipantSynchronizer() {
		return participantSynchronizer;
	}

	public void setParticipantSynchronizer(
			ParticipantSynchronizer participantSynchronizer) {
		this.participantSynchronizer = participantSynchronizer;
	}
	
	public void setDomainObjectValidator(DomainObjectValidator domainObjectValidator) {
		this.domainObjectValidator = domainObjectValidator;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
}