
package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.grid.aspects.AspectJSecurityInterceptorStub;
import gov.nih.nci.cabig.caaers.security.StudyParticipantAssignmentAspect;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.ccts.grid.IdentifierType;
import gov.nih.nci.ccts.grid.OrganizationAssignedIdentifierType;
import gov.nih.nci.ccts.grid.ParticipantType;
import gov.nih.nci.ccts.grid.Registration;
import gov.nih.nci.ccts.grid.SystemAssignedIdentifierType;
import gov.nih.nci.ccts.grid.common.RegistrationConsumer;
import gov.nih.nci.ccts.grid.stubs.types.InvalidRegistrationException;
import gov.nih.nci.ccts.grid.stubs.types.RegistrationConsumptionException;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class CaaersRegistrationConsumer implements RegistrationConsumer{
	
	private static final Log log = LogFactory.getLog(CaaersRegistrationConsumer.class);
	
	private OrganizationDao organizationDao;
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	private ConfigProperty configurationProperty;
	private OpenSessionInViewInterceptor openSessionInViewInterceptor; 
	private AuthorizationSwitch authorizationSwitch;
	private StudyParticipantAssignmentAspect assignmentAspect;
	
	private WebRequest preProcess(){
		assignmentAspect.setSecurityInterceptor(new AspectJSecurityInterceptorStub());
		authorizationSwitch.setOn(false);
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl("ROLE_caaers_super_user");
     
        Authentication auth = new TestingAuthenticationToken("ROLE_caaers_super_user",
            "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
		
		WebRequest stubWebRequest = new StubWebRequest();
		openSessionInViewInterceptor.preHandle(stubWebRequest);
		return stubWebRequest;
	}
	private void postProcess(WebRequest stubWebRequest){
		openSessionInViewInterceptor.afterCompletion(stubWebRequest, null);
	}
	//@Transactional(readOnly=false)
	public void commit(Registration registration) throws RemoteException,InvalidRegistrationException {
		log.info("Begining of registration-commit");
		WebRequest stubWebRequest = null;
		try{
			stubWebRequest = preProcess();
			String mrn = findMedicalRecordNumber(registration.getParticipant());
			participantDao.commitParticipant(mrn);
			
		}catch(Exception exp){
			InvalidRegistrationException e = new InvalidRegistrationException();
			e.setFaultReason("Error while comitting, " + exp.getMessage());
			e.setFaultString("Error while comitting, " + exp.getMessage());
			exp.printStackTrace();
			throw e;
		}finally{
			postProcess(stubWebRequest);
		}
		log.info("End of registration-commit");
	}
	
	/**
	 * 1. Fetch the study based on Coordinating center Identifier
	 * 2. Fetch the Organization to which the participant is registered
	 */
	//@Transactional(readOnly=false)
	public Registration register(Registration registration)	throws RemoteException, InvalidRegistrationException,
			RegistrationConsumptionException {
		log.info("Begining of registration-register");
		WebRequest stubWebRequest = null;
		try{
			stubWebRequest = preProcess();

			String ccIdentifier = findCoordinatingCenterIdentifier(registration);
			Study study = fetchStudy(ccIdentifier, OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
			
			if(study == null){
				RegistrationConsumptionException exp = new RegistrationConsumptionException();
				exp.setFaultString("Study identified by Coordinating Center Identifier '" + ccIdentifier +"' doesn't exist");
				exp.setFaultReason("Study identified by Coordinating Center Identifier '" + ccIdentifier +"' doesn't exist");
				throw exp;
			}
			
			String siteNCICode = registration.getStudySite().getHealthcareSite(0).getNciInstituteCode();
			StudySite site = findStudySite(study, siteNCICode);
			if(site == null){
				RegistrationConsumptionException exp = new RegistrationConsumptionException();
				exp.setFaultReason("The study '" + study.getShortTitle() +"', identified by Coordinating Center Identifier '" + ccIdentifier + 
						"' is not associated to a site identified by NCI code :'" + siteNCICode +"'");
				exp.setFaultString("The study '" + study.getShortTitle() +"', identified by Coordinating Center Identifier '" + ccIdentifier + 
						"' is not associated to a site identified by NCI code :'" + siteNCICode +"'");

			}
			String mrn = findMedicalRecordNumber(registration.getParticipant());
			Participant participant = fetchParticipant(mrn);
			if(participant == null){
				participant = createParticipant(registration);
				StudyParticipantAssignment assignment = createStudyParticipantAssignment(participant, site);
			}else{
				StudyParticipantAssignment assignment = fetchAssignment(participant, site);
				if(assignment == null){
					assignment = createStudyParticipantAssignment(participant, site);
					
				}else{
					log.info("Already this participant is associated to the study");
				}
			}
			participantDao.save(participant);
			return registration;
		}finally{
			postProcess(stubWebRequest);
		}
		log.info("End of registration-register");
	}
	
	//@Transactional(readOnly=false)
	public void rollback(Registration registration) throws RemoteException,InvalidRegistrationException {
		WebRequest stubWebRequest = null;
		log.info("Begining of registration-rollback");
		try{
			stubWebRequest = preProcess();
			String mrn = findMedicalRecordNumber(registration.getParticipant());
			participantDao.deleteInprogressParticipant(mrn);
			
		}catch(Exception exp){
			InvalidRegistrationException e = new InvalidRegistrationException();
			e.setFaultReason("Error while comitting, " + exp.getMessage());
			e.setFaultString("Error while comitting, " + exp.getMessage());
			exp.printStackTrace();
			throw e;
			
		}finally{
			postProcess(stubWebRequest);
		}
		log.info("End of registration-rollback");
		
	}
	
	Participant createParticipant(Registration registration) throws InvalidRegistrationException{
		ParticipantType partBean = registration.getParticipant();
        Participant participant = new Participant();
        
        participant.setGridId(partBean.getGridId());
        participant.setGender(partBean.getAdministrativeGenderCode());
        participant.setDateOfBirth(partBean.getBirthDate());
        participant.setEthnicity(partBean.getEthnicGroupCode());
        participant.setFirstName(partBean.getFirstName());
        participant.setLastName(partBean.getLastName());
        participant.setRace(partBean.getRaceCode());
        participant.setLoadStatus(0); //load status of participant should be 0
        
        populateIdentifiers(participant, partBean.getIdentifier());
        List<Identifier> participantIdentifiers = participant.getIdentifiers();
        if(participantIdentifiers == null || participantIdentifiers.isEmpty()){
        	log.info("The participant has no identifiers.");
			InvalidRegistrationException exp = new InvalidRegistrationException();
			exp.setFaultReason("There is no identifier associated to this participant, Medical Record Number(MRN) is needed to register this participant");
			exp.setFaultString("There is no identifier associated to this participant, Medical Record Number(MRN) is needed to register this participant");
			throw exp;
        }
        
        return participant;
	}
	
	

	void populateIdentifiers(Participant participant, IdentifierType[] identifierTypes) throws InvalidRegistrationException {
		if(identifierTypes == null){
			log.info("The participant has no identifiers.");
			return;
		}
		List<Lov> identifierLovs = configurationProperty.getMap().get("participantIdentifiersType");
		List<String> knownIdentifierTypes = new ArrayList<String>();
		for(Lov lov : identifierLovs){
			knownIdentifierTypes.add(lov.getCode());
		}
		
		
		for(IdentifierType identifierType : identifierTypes){
			if(!knownIdentifierTypes.contains(identifierType.getType())){
				log.warn("The identifier type '" + identifierType.getType()+"' is unknown to caAERS. So ignoring the identifier("  + identifierType.getValue() + ")");
				continue;
			}
			
			if(identifierType instanceof SystemAssignedIdentifierType){
				SystemAssignedIdentifierType sysIdType = (SystemAssignedIdentifierType)identifierType;
				SystemAssignedIdentifier id = new SystemAssignedIdentifier();
				id.setGridId(identifierType.getGridId());
				id.setPrimaryIndicator(identifierType.getPrimaryIndicator());
				id.setType(sysIdType.getType());
				id.setValue(sysIdType.getValue());
				id.setSystemName(sysIdType.getSystemName());
				participant.addIdentifier(id);
			}else
				if(identifierType instanceof OrganizationAssignedIdentifierType){
				OrganizationAssignedIdentifierType orgIdType = (OrganizationAssignedIdentifierType)identifierType;
				OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
				id.setGridId(orgIdType.getGridId());
				id.setPrimaryIndicator(orgIdType.getPrimaryIndicator());
				id.setType(orgIdType.getType());
				id.setValue(orgIdType.getValue());
				id.setOrganization(fetchOrganization(orgIdType.getHealthcareSite().getNciInstituteCode()));
				participant.addIdentifier(id);
			}else {
				log.error("Unknown IdentifierType in grid Paricipant " + participant.getFullName());
				InvalidRegistrationException e = new InvalidRegistrationException();
				e.setFaultReason("Unknown IdentifierType in grid Participant ");
				e.setFaultString("Unknown IdentifierType in grid Participant ");
				throw e;
			}
		}
	}
	
	String findMedicalRecordNumber(ParticipantType participant) throws InvalidRegistrationException{
		String pIdentifier = findIdentifierOfType(participant.getIdentifier(), SystemAssignedIdentifier.MRN_IDENTIFIER_TYPE);
		
		if(pIdentifier == null){
			log.info("The participant has no identifiers.");
			InvalidRegistrationException exp = new InvalidRegistrationException();
			exp.setFaultReason("There is no identifier associated to this participant, Medical Record Number(MRN) is needed to register this participant");
			exp.setFaultString("There is no identifier associated to this participant, Medical Record Number(MRN) is needed to register this participant");
			throw exp;
		}
		return pIdentifier;
	}
	/*
	 * Finds the coordinating center identifier for the sutdy
	 */
	String findCoordinatingCenterIdentifier(Registration registration) throws InvalidRegistrationException {
		String ccIdentifier = findIdentifierOfType(registration.getStudyRef().getIdentifier(), OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);

		if(ccIdentifier == null){
			InvalidRegistrationException exp = new InvalidRegistrationException();
			exp.setFaultReason("In StudyRef-Identifiers, Coordinating Center Identifier is not available");
			exp.setFaultString("In StudyRef-Identifiers, Coordinating Center Identifier is not available");
			throw exp;
		}
		return ccIdentifier;
		
	}
	
	
	private String findIdentifierOfType(IdentifierType[] idTypes, String ofType){
		if(idTypes == null) return null;
		for(IdentifierType idType : idTypes){
			if(idType instanceof OrganizationAssignedIdentifierType &&
				StringUtils.equals(idType.getType(), ofType)){
				return  idType.getValue();
			}
		}
		return null;
	}
	
	private StudySite findStudySite(Study study, String siteNCICode){
		for(StudySite site : study.getStudySites()){
			if(StringUtils.equals(site.getOrganization().getNciInstituteCode(), siteNCICode)) return site;
		}
		return null;
	}
	
	/**
	 * Fetches the organization from the DB
	 * @param gridId
	 * @return
	 */
	Organization fetchOrganization(String nciCode){
		OrganizationQuery orgQuery = new OrganizationQuery();
		
		
		if(StringUtils.isNotEmpty(nciCode)){
			orgQuery.filterByNciCodeExactMatch(nciCode);
		}
		
		List<Organization> orgList = organizationDao.searchOrganization(orgQuery);
		
		if(orgList == null || orgList.isEmpty()){
			log.error("No organization exists  nciCode :" + nciCode);
			throw new CaaersSystemException("No organization exists with nciCode :" + nciCode);
		}
		if(orgList.size() > 1){
			log.error("Multiple organizations exist in DB with same NCI code :" + nciCode);
		}
		
		return orgList.get(0);
	}
	
	Study fetchStudy(String ccIdentifier, String identifierType){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierValueExactMatch(ccIdentifier);
		studyQuery.filterByIdentifierType(identifierType);
		List<Study> studies = studyDao.find(studyQuery);
		if(studies == null || studies.isEmpty()) return null;
		Study study =  studies.get(0);
		/*if(study != null){
			studyDao.initialize(study);
		}*/
		return study;
	}
	
	Participant fetchParticipant(String mrn){
		ParticipantQuery query = new ParticipantQuery();
		query.joinOnIdentifiers();
		query.filterByIdentifierValueExactMatch(mrn);
		List<Participant> participants = participantDao.searchParticipant(query);
		if(participants == null || participants.isEmpty()) return null;
		return participants.get(0);
	}
	
	
	StudyParticipantAssignment fetchAssignment(Participant participant, StudySite site){
		return studyParticipantAssignmentDao.getAssignment(participant, site);
	}
	

	StudyParticipantAssignment createStudyParticipantAssignment(Participant participant, StudySite site){
		StudyParticipantAssignment assignment = new StudyParticipantAssignment(participant, site);
		participant.addAssignment(assignment);
		site.addAssignment(assignment);
		assignment.setLoadStatus(0);
		return assignment;
	}
	
	///BEAN PROPERTIES
	
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
		return studyParticipantAssignmentDao;
	}

	public void setStudyParticipantAssignmentDao(
			StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
		this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
	}
	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}
	@Required
	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
	@Required
    public void setOpenSessionInViewInterceptor(
			OpenSessionInViewInterceptor openSessionInViewInterceptor) {
		this.openSessionInViewInterceptor = openSessionInViewInterceptor;
	}
	public OpenSessionInViewInterceptor getOpenSessionInViewInterceptor() {
		return openSessionInViewInterceptor;
	}
	
	
	@Required
	public void setAuthorizationSwitch(AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}
	public AuthorizationSwitch getAuthorizationSwitch() {
		return authorizationSwitch;
	}
	
	public StudyParticipantAssignmentAspect getStudyParticipantAssignmentAspect() {
		return assignmentAspect;
	}
	@Required
	public void setStudyParticipantAssignmentAspect(
			StudyParticipantAssignmentAspect assignmentAspect) {
		this.assignmentAspect = assignmentAspect;
	}
	
	private static class StubWebRequest implements WebRequest {
		public String getParameter(final String paramName) {
			return null;
		}

		public String[] getParameterValues(final String paramName) {
			return null;
		}

		public Map getParameterMap() {
			return Collections.emptyMap();
		}

		public Locale getLocale() {
			return null;
		}

		public Object getAttribute(final String name, final int scope) {
			return null;
		}

		public void setAttribute(final String name, final Object value, final int scope) {
		}

		public void removeAttribute(final String name, final int scope) {
		}

		public void registerDestructionCallback(final String name, final Runnable callback, final int scope) {
		}

		public String getSessionId() {
			return null;
		}

		public Object getSessionMutex() {
			return null;
		}
	}
}
