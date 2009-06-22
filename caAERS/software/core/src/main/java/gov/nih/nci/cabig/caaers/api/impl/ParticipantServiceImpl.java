package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.ParticipantService;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.ParticipantImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.service.migrator.ParticipantConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.ParticipantSynchronizer;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;
import gov.nih.nci.cabig.caaers.webservice.participant.Response;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ParticipantService", serviceName="ParticipantService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ParticipantServiceImpl implements ParticipantService,ApplicationContextAware {
	
	private static Log logger = LogFactory.getLog(ParticipantServiceImpl.class);
	private ApplicationContext applicationContext;
	
    private ParticipantDao participantDao;
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
	
	public CaaersServiceResponse createParticipant(
			Participants xmlParticipants) {
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);
		boolean authorizationOnByDefault = enableAuthorization(false);
		switchUser("SYSTEM_ADMIN", "ROLE_caaers_super_user");
		
		CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
		Response participantServiceResponse = new Response();
		Participant participant = new Participant();
		DomainObjectImportOutcome<Participant> participantImportOutcome = null;
        
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
        enableAuthorization(authorizationOnByDefault);
		switchUser(null);
		caaersServiceResponse.setResponse(participantServiceResponse);
		return caaersServiceResponse;
	}

	public CaaersServiceResponse updateParticipant(
			Participants xmlParticipants) {
		
		ParticipantType xmlParticipant = xmlParticipants.getParticipant().get(0);
		boolean authorizationOnByDefault = enableAuthorization(false);
		switchUser("SYSTEM_ADMIN", "ROLE_caaers_super_user");
		
		CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
		Response participantServiceResponse = new Response();
		
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
        enableAuthorization(authorizationOnByDefault);
		switchUser(null);
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
	
	private void switchUser(String userName, String... roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
	
	private boolean enableAuthorization(boolean on) {
        AuthorizationSwitch sw = (AuthorizationSwitch) this.applicationContext.getBean("authorizationSwitch");
        if (sw == null) throw new RuntimeException("Authorization switch not found");
        boolean current = sw.isOn();
        sw.setOn(on);
        return current;
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
}