package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
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
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
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
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse;
import org.oasis.wsrf.properties.GetMultipleResourceProperties_Element;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.QueryResourcePropertiesResponse;
import org.oasis.wsrf.properties.QueryResourceProperties_Element;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 */
public class CaaersRegistrationConsumer implements RegistrationConsumerI {

	private static final Log logger = LogFactory.getLog(CaaersRegistrationConsumer.class);

    private OrganizationRepository organizationRepository;

    private StudyDao studyDao;

    private ParticipantDao participantDao;

    private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

    private ConfigProperty configurationProperty;

    private AuthorizationSwitch authorizationSwitch;

    private StudyParticipantAssignmentAspect assignmentAspect;

    private AuditHistoryRepository auditHistoryRepository;

    private String registrationConsumerGridServiceUrl;

    private Integer rollbackInterval;


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
        try {

            String ccIdentifier = findCoordinatingCenterIdentifier(registration);
            Study study = fetchStudy(ccIdentifier,
                            OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
            
            if (study == null) {
                String message = "Study identified by Coordinating Center Identifier '"
                                + ccIdentifier + "' doesn't exist";
                RegistrationConsumptionException exp = getRegistrationConsumptionException(message);
                throw exp;
            }
            String siteNCICode = registration.getStudySite().getHealthcareSite(0).getNciInstituteCode();
            StudySite site = findStudySite(study, siteNCICode);
            if (site == null) {
                String message = "The study '" + study.getShortTitle()
                                + "', identified by Coordinating Center Identifier '"
                                + ccIdentifier
                                + "' is not associated to a site identified by NCI code :'"
                                + siteNCICode + "'";

                throw getRegistrationConsumptionException(message);

            }
            Boolean dataEntryStatus = study.getDataEntryStatus();
            if (dataEntryStatus==null || !dataEntryStatus) {
            	String message = "Study identified by Coordinating Center Identifier '"
                    + ccIdentifier + "' is not open , Please login to caAERS , complete the Study details and open the Study";
            	RegistrationConsumptionException exp = getRegistrationConsumptionException(message);
            	throw exp;
            }

            String mrn = findMedicalRecordNumber(registration.getParticipant());
            Participant participant = fetchParticipant(mrn,site);
            
            if(participant != null && participant.isAssignedToStudySite(site)) {
                StringBuilder message = new StringBuilder("Participant with MRN : ")
                .append(mrn)
                .append(", is already associated to the Study (Coordinating Center Identifier :")
                .append(ccIdentifier).append(" )") ;
                logger.error(message.toString());
                RegistrationConsumptionException exp = getRegistrationConsumptionException(message.toString());
                throw exp;
            }
            
            if (participant == null) {
                participant = createParticipant(registration);
            }
            createStudyParticipantAssignment(registration.getGridId(), participant, site, registration.getIdentifier());
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

        logger.info("Begining of registration-rollback");
        try {
        	
            Calendar calendar = Calendar.getInstance();
            StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getByGridId(registration.getGridId());
            if(assignment != null){
            	boolean checkIfAssignmentWasCreatedOneMinuteBeforeCurrentTime = 
        					auditHistoryRepository.checkIfEntityWasCreatedMinutesBeforeSpecificDate(
        																							StudyParticipantAssignment.class,
																			    					assignment.getId(),
																			    					calendar,
																			    					rollbackInterval);
            	
            	Participant participant = assignment.getParticipant();
            	
    			boolean checkIfSubjectWasCreatedOneMinuteBeforeCurrentTime = auditHistoryRepository.checkIfEntityWasCreatedMinutesBeforeSpecificDate(
    					Participant.class, 
						participant.getId(),
						calendar,
						rollbackInterval);
            	
        		if(checkIfAssignmentWasCreatedOneMinuteBeforeCurrentTime && checkIfSubjectWasCreatedOneMinuteBeforeCurrentTime){
        			participantDao.delete(participant);
        		}else if(checkIfAssignmentWasCreatedOneMinuteBeforeCurrentTime && !checkIfSubjectWasCreatedOneMinuteBeforeCurrentTime){
        			participant.getAssignments().remove(assignment);
                    participantDao.save(participant);
        		}else{
        			logger.info("StudyParticipantAssignment was not created one minute before the current time:"
                            + calendar.getTime().toString()
                            + " so can not rollback this assignment:" + assignment.getId());
        		}
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            logger.error(exp);
            String message = "Error while rolling back, " + exp.getMessage();
            InvalidRegistrationException e = getInvalidRegistrationException(message);
            throw e;

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

        List<Organization> orgList = organizationRepository.searchOrganization(orgQuery);

        if (orgList == null || orgList.isEmpty()) {
            logger.error("User is not associated with this Organization (or) No organization exists with nciCode :" + nciCode);
            throw new CaaersSystemException("User is not associated with this Organization (or) No organization exists with nciCode :" + nciCode);
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

    Participant fetchParticipant(String mrn,StudySite site) {
        ParticipantQuery query = new ParticipantQuery();
        query.joinOnIdentifiers();
        query.filterByIdentifierValueExactMatch(mrn);
        query.filterByOrganizationId(site.getOrganization().getId());
        List<Participant> participants = participantDao.searchParticipant(query);
        if (participants == null || participants.isEmpty()) return null;
        return participants.get(0);
    }

    StudyParticipantAssignment createStudyParticipantAssignment(String assignmentGridId,
                    Participant participant, StudySite site, IdentifierType[] identifierTypes) {
        StudyParticipantAssignment assignment = new StudyParticipantAssignment(participant, site);
        assignment.setGridId(assignmentGridId);
        String studySubjectIdentifier = getStudySubjectIdentifier(identifierTypes);
        if (studySubjectIdentifier != null) {
        	assignment.setStudySubjectIdentifier(studySubjectIdentifier);
        }
        participant.addAssignment(assignment);
        site.addAssignment(assignment);
        return assignment;
    }
    
    
    private String getStudySubjectIdentifier(IdentifierType[] identifierTypes) {
    	String studySubjectIdentifier = null;
    	for (IdentifierType identifierType : identifierTypes) {
    		if (identifierType instanceof OrganizationAssignedIdentifierType) {
    			if (identifierType.getType().equals("COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER")) {
    				studySubjectIdentifier = identifierType.getValue();
    				break;
    			}
    		}
    	}
    	return studySubjectIdentifier;
    	
    }
    // /BEAN PROPERTIES



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

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
}