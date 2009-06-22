package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.DateValue;
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
import gov.nih.nci.cabig.ccts.domain.IdentifierType;
import gov.nih.nci.cabig.ccts.domain.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.ccts.domain.ParticipantType;
import gov.nih.nci.cabig.ccts.domain.Registration;
import gov.nih.nci.cabig.ccts.domain.SystemAssignedIdentifierType;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.ccts.grid.common.RegistrationConsumerI;
import gov.nih.nci.ccts.grid.stubs.types.InvalidRegistrationException;
import gov.nih.nci.ccts.grid.stubs.types.RegistrationConsumptionException;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.namespace.QName;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse;
import org.oasis.wsrf.properties.GetMultipleResourceProperties_Element;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.QueryResourcePropertiesResponse;
import org.oasis.wsrf.properties.QueryResourceProperties_Element;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 */
public class CaaersRegistrationConsumer implements RegistrationConsumerI {

    private static final Log logger = LogFactory.getLog(CaaersRegistrationConsumer.class);

    private OrganizationDao organizationDao;

    private StudyDao studyDao;

    private ParticipantDao participantDao;

    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

    private ConfigProperty configurationProperty;

    private OpenSessionInViewInterceptor openSessionInViewInterceptor;

    private AuthorizationSwitch authorizationSwitch;

    private StudyParticipantAssignmentAspect assignmentAspect;

    private AuditHistoryRepository auditHistoryRepository;

    private String registrationConsumerGridServiceUrl;

    private Integer rollbackInterval;

    private WebRequest preProcess() {
        assignmentAspect.setSecurityInterceptor(new AspectJSecurityInterceptorStub());
        authorizationSwitch.setOn(false);
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        authorities[0] = new GrantedAuthorityImpl("ROLE_caaers_super_user");

        Authentication auth = new TestingAuthenticationToken("ROLE_caaers_super_user", "ignored",
                        authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        WebRequest stubWebRequest = new StubWebRequest();
        openSessionInViewInterceptor.preHandle(stubWebRequest);
        return stubWebRequest;
    }

    private void postProcess(WebRequest stubWebRequest) {
        openSessionInViewInterceptor.afterCompletion(stubWebRequest, null);
    }

    // @Transactional(readOnly=false)
    public void commit(Registration registration) throws RemoteException,
                    InvalidRegistrationException {
        logger.info("Begining of registration-commit");
        System.out.println("-- RegistrationConsumer :commit called");
        /*
         * WebRequest stubWebRequest = null; try{ stubWebRequest = preProcess(); String mrn =
         * findMedicalRecordNumber(registration.getParticipant());
         * participantDao.commitParticipant(mrn);
         * 
         * }catch(Exception exp){ InvalidRegistrationException e = new
         * InvalidRegistrationException(); e.setFaultReason("Error while comitting, " +
         * exp.getMessage()); e.setFaultString("Error while comitting, " + exp.getMessage());
         * exp.printStackTrace(); throw e; }finally{ postProcess(stubWebRequest); }
         */
        logger.info("End of registration-commit");
    }

    /**
     * 1. Fetch the study based on Coordinating center Identifier 2. Fetch the Organization to which
     * the participant is registered
     */
    // @Transactional(readOnly=false)
    public Registration register(Registration registration) throws RemoteException,
                    InvalidRegistrationException, RegistrationConsumptionException {
        logger.info("Begining of registration-register");
        System.out.println("-- RegistrationConsumer : register");
        WebRequest stubWebRequest = null;
        try {
            stubWebRequest = preProcess();

            String ccIdentifier = findCoordinatingCenterIdentifier(registration);
            Study study = fetchStudy(ccIdentifier,
                            OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);

            if (study == null) {
                String message = "Study identified by Coordinating Center Identifier '"
                                + ccIdentifier + "' doesn't exist";
                RegistrationConsumptionException exp = getRegistrationConsumptionException(message);
                throw exp;
            }

            String siteNCICode = registration.getStudySite().getHealthcareSite(0)
                            .getNciInstituteCode();
            StudySite site = findStudySite(study, siteNCICode);
            if (site == null) {
                String message = "The study '" + study.getShortTitle()
                                + "', identified by Coordinating Center Identifier '"
                                + ccIdentifier
                                + "' is not associated to a site identified by NCI code :'"
                                + siteNCICode + "'";

                throw getRegistrationConsumptionException(message);

            }
            String mrn = findMedicalRecordNumber(registration.getParticipant());
            Participant participant = fetchParticipant(mrn);

            if (participant == null) {
                participant = createParticipant(registration);
                createStudyParticipantAssignment(registration.getGridId(), participant, site);
            } else {

                logger.info("The participant identified by MRN :" + mrn
                                + ", already available, so using existing participant");
                if (participant.isAssignedToStudySite(site)) {
                    logger
                                    .info("Already this participant is associated to the study, so throwing exception");
                    String message = "Participant with MRN : "
                                    + mrn
                                    + ", is already associated to the Study (Coordinating Center Identifier :"
                                    + ccIdentifier + ")";
                    RegistrationConsumptionException exp = getRegistrationConsumptionException(message);
                    throw exp;
                }
                createStudyParticipantAssignment(registration.getGridId(), participant, site);

            }
            participantDao.save(participant);
            logger.info("End of registration-register");
            return registration;
        } catch (InvalidRegistrationException e) {
            throw e;
        } catch (RegistrationConsumptionException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error while registering", e);
            throw new RemoteException("Error while registering", e);
        } finally {
            postProcess(stubWebRequest);
        }

    }

    private RegistrationConsumptionException getRegistrationConsumptionException(String message) {
        RegistrationConsumptionException exp = new RegistrationConsumptionException();
        exp.setFaultReason(message);
        exp.setFaultString(message);
        return exp;
    }

    // @Transactional(readOnly=false)
    public void rollback(Registration registration) throws RemoteException,
                    InvalidRegistrationException {
        WebRequest stubWebRequest = null;
        logger.info("Begining of registration-rollback");
        try {
            stubWebRequest = preProcess();
            String mrn = findMedicalRecordNumber(registration.getParticipant());
            Participant participant = fetchParticipant(mrn);
            if (participant == null) {
                logger.info("Unable to find the participant with MRN :" + mrn);
                return;
            }

            boolean checkIfEntityWasCreatedByGridService = auditHistoryRepository
                            .checkIfEntityWasCreatedByUrl(participant.getClass(), participant
                                            .getId(), registrationConsumerGridServiceUrl);
            if (!checkIfEntityWasCreatedByGridService) {
                logger.debug("Participant was not created by the grid service url:"
                                + registrationConsumerGridServiceUrl
                                + " so can not rollback this registration:" + participant.getId());
                return;
            }

            logger.info("Subject (id:" + participant.getId()
                            + ") was created by the grid service url:"
                            + registrationConsumerGridServiceUrl);

            // check if this subject was created one minute before or not
            Calendar calendar = Calendar.getInstance();
            boolean checkIfSubjectWasCreatedOneMinuteBeforeCurrentTime = auditHistoryRepository
                            .checkIfEntityWasCreatedMinutesBeforeSpecificDate(participant
                                            .getClass(), participant.getId(), calendar,
                                            rollbackInterval);
            if (!checkIfSubjectWasCreatedOneMinuteBeforeCurrentTime) {
                logger.debug("Participant was not created one minute before the current time:"
                                + calendar.getTime().toString()
                                + " so can not rollback this registration:" + participant.getId());
                return;

            }
            logger.info("Participant was created one minute before the current time:"
                            + calendar.getTime().toString());

            if (participant.getAssignments().size() <= 1) {
                logger
                                .info("The participant is assigned to only one study, so removing the participant");
                participantDao.delete(participant);
            } else {
                logger.info("Removing only the assignment");

                String ccIdentifier = findCoordinatingCenterIdentifier(registration);
                Study study = fetchStudy(ccIdentifier,
                                OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);

                if (study == null) {
                    String message = "Study identified by Coordinating Center Identifier '"
                                    + ccIdentifier + "' doesn't exist";
                    RegistrationConsumptionException exp = getRegistrationConsumptionException(message);
                    throw exp;
                }

                String siteNCICode = registration.getStudySite().getHealthcareSite(0)
                                .getNciInstituteCode();
                StudySite site = findStudySite(study, siteNCICode);

                StudyParticipantAssignment assignment = participant
                                .getStudyParticipantAssignment(site);
                participant.getAssignments().remove(assignment);
                participantDao.save(participant);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            logger.error(exp);
            String message = "Error while rolling back, " + exp.getMessage();
            InvalidRegistrationException e = getInvalidRegistrationException(message);
            throw e;

        } finally {
            postProcess(stubWebRequest);
        }
        logger.info("End of registration-rollback");

    }

    private InvalidRegistrationException getInvalidRegistrationException(String message) {
        logger.error(message);
        InvalidRegistrationException e = new InvalidRegistrationException();

        e.setFaultReason(message);
        e.setFaultString(message);
        return e;
    }

    Participant createParticipant(Registration registration) throws InvalidRegistrationException {
        ParticipantType partBean = registration.getParticipant();
        Participant participant = new Participant();

        participant.setGridId(partBean.getGridId());
        participant.setGender(partBean.getAdministrativeGenderCode());
        participant.setDateOfBirth(new DateValue(partBean.getBirthDate()));
        participant.setEthnicity(partBean.getEthnicGroupCode());
        participant.setFirstName(partBean.getFirstName());
        participant.setLastName(partBean.getLastName());
        participant.setRace(partBean.getRaceCode());

        populateIdentifiers(participant, partBean.getIdentifier());
        List<Identifier> participantIdentifiers = participant.getIdentifiers();
        if (participantIdentifiers == null || participantIdentifiers.isEmpty()) {
            logger.info("The participant has no identifiers.");
            InvalidRegistrationException exp = getInvalidRegistrationException("There is no identifier associated to this participant, Medical Record Number(MRN) is needed to register this participant");
            throw exp;
        }

        return participant;
    }

    void populateIdentifiers(Participant participant, IdentifierType[] identifierTypes)
                    throws InvalidRegistrationException {
        if (identifierTypes == null) {
            logger.info("The participant has no identifiers.");
            return;
        }
        List<Lov> identifierLovs = configurationProperty.getMap().get("participantIdentifiersType");
        List<String> knownIdentifierTypes = new ArrayList<String>();
        for (Lov lov : identifierLovs) {
            knownIdentifierTypes.add(lov.getCode());
        }

        for (IdentifierType identifierType : identifierTypes) {
            if (!knownIdentifierTypes.contains(identifierType.getType())) {
                logger.warn("The identifier type '" + identifierType.getType()
                                + "' is unknown to caAERS. So ignoring the identifier("
                                + identifierType.getValue() + ")");
                continue;
            }

            if (identifierType instanceof SystemAssignedIdentifierType) {
                SystemAssignedIdentifierType sysIdType = (SystemAssignedIdentifierType) identifierType;
                SystemAssignedIdentifier id = new SystemAssignedIdentifier();
                id.setGridId(identifierType.getGridId());
                id.setPrimaryIndicator(identifierType.getPrimaryIndicator());
                id.setType(sysIdType.getType());
                id.setValue(sysIdType.getValue());
                id.setSystemName(sysIdType.getSystemName());
                participant.addIdentifier(id);
            } else if (identifierType instanceof OrganizationAssignedIdentifierType) {
                OrganizationAssignedIdentifierType orgIdType = (OrganizationAssignedIdentifierType) identifierType;
                OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
                id.setGridId(orgIdType.getGridId());
                id.setPrimaryIndicator(orgIdType.getPrimaryIndicator());
                id.setType(orgIdType.getType());
                id.setValue(orgIdType.getValue());
                id.setOrganization(fetchOrganization(orgIdType.getHealthcareSite()
                                .getNciInstituteCode()));
                participant.addIdentifier(id);
            } else {
                logger.error("Unknown IdentifierType in grid Paricipant "
                                + participant.getFullName());
                throw getInvalidRegistrationException("Unknown IdentifierType in grid Participant ");
            }
        }
    }

    String findMedicalRecordNumber(ParticipantType participant) throws InvalidRegistrationException {
        String pIdentifier = findIdentifierOfType(participant.getIdentifier(),
                        SystemAssignedIdentifier.MRN_IDENTIFIER_TYPE);

        if (pIdentifier == null) {
            logger.info("The participant has no identifiers.");
            throw getInvalidRegistrationException("There is no identifier associated to this participant, Medical Record Number(MRN) is needed to register this participant");
        }
        return pIdentifier;
    }

    /*
     * Finds the coordinating center identifier for the sutdy
     */
    String findCoordinatingCenterIdentifier(Registration registration)
                    throws InvalidRegistrationException {
        String ccIdentifier = findIdentifierOfType(registration.getStudyRef().getIdentifier(),
                        OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);

        if (ccIdentifier == null) {
            String message = "In StudyRef-Identifiers, Coordinating Center Identifier is not available";
            throw getInvalidRegistrationException(message);
        }
        return ccIdentifier;

    }

    private String findIdentifierOfType(IdentifierType[] idTypes, String ofType) {
        if (idTypes == null) return null;
        for (IdentifierType idType : idTypes) {
            if (idType instanceof OrganizationAssignedIdentifierType
                            && StringUtils.equals(idType.getType(), ofType)) {
                return idType.getValue();
            }
        }
        return null;
    }

    private StudySite findStudySite(Study study, String siteNCICode) {
        for (StudySite site : study.getStudySites()) {
            if (StringUtils.equals(site.getOrganization().getNciInstituteCode(), siteNCICode)) return site;
        }
        return null;
    }

    /**
     * Fetches the organization from the DB
     * 
     * @param nciCode
     * @return
     */
    Organization fetchOrganization(String nciCode) {
        OrganizationQuery orgQuery = new OrganizationQuery();

        if (StringUtils.isNotEmpty(nciCode)) {
            orgQuery.filterByNciCodeExactMatch(nciCode);
        }

        List<Organization> orgList = organizationDao.searchOrganization(orgQuery);

        if (orgList == null || orgList.isEmpty()) {
            logger.error("No organization exists  nciCode :" + nciCode);
            throw new CaaersSystemException("No organization exists with nciCode :" + nciCode);
        }
        if (orgList.size() > 1) {
            logger.error("Multiple organizations exist in DB with same NCI code :" + nciCode);
        }

        return orgList.get(0);
    }

    Study fetchStudy(String ccIdentifier, String identifierType) {
        StudyQuery studyQuery = new StudyQuery();
        studyQuery.filterByIdentifierValueExactMatch(ccIdentifier);
        studyQuery.filterByIdentifierType(identifierType);
        List<Study> studies = studyDao.find(studyQuery);
        if (studies == null || studies.isEmpty()) return null;
        Study study = studies.get(0);
        /*
         * if(study != null){ studyDao.initialize(study); }
         */
        return study;
    }

    Participant fetchParticipant(String mrn) {
        ParticipantQuery query = new ParticipantQuery();
        query.joinOnIdentifiers();
        query.filterByIdentifierValueExactMatch(mrn);
        List<Participant> participants = participantDao.searchParticipant(query);
        if (participants == null || participants.isEmpty()) return null;
        return participants.get(0);
    }

    StudyParticipantAssignment createStudyParticipantAssignment(String assignmentGridId,
                    Participant participant, StudySite site) {
        StudyParticipantAssignment assignment = new StudyParticipantAssignment(participant, site);
        assignment.setGridId(assignmentGridId);
        participant.addAssignment(assignment);
        site.addAssignment(assignment);
        return assignment;
    }

    // /BEAN PROPERTIES

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

    @Required
    public void setRegistrationConsumerGridServiceUrl(String registrationConsumerGridServiceUrl) {
        this.registrationConsumerGridServiceUrl = registrationConsumerGridServiceUrl;
    }

    @Required
    public void setRollbackInterval(Integer rollbackInterval) {
        this.rollbackInterval = rollbackInterval;
    }

    @Required
    public void setAuditHistoryRepository(AuditHistoryRepository auditHistoryRepository) {
        this.auditHistoryRepository = auditHistoryRepository;
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

        public void registerDestructionCallback(final String name, final Runnable callback,
                        final int scope) {
        }

        public String getSessionId() {
            return null;
        }

        public Object getSessionMutex() {
            return null;
        }
    }

	public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(GetMultipleResourceProperties_Element params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public GetResourcePropertyResponse getResourceProperty(QName params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryResourcePropertiesResponse queryResourceProperties(QueryResourceProperties_Element params) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
