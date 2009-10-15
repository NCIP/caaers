package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.ccts.domain.IdentifierType;
import gov.nih.nci.cabig.ccts.domain.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.ccts.domain.StudyCoordinatingCenterType;
import gov.nih.nci.cabig.ccts.domain.StudyFundingSponsorType;
import gov.nih.nci.cabig.ccts.domain.StudyInvestigatorType;
import gov.nih.nci.cabig.ccts.domain.StudyOrganizationType;
import gov.nih.nci.cabig.ccts.domain.StudySiteType;
import gov.nih.nci.cabig.ccts.domain.SystemAssignedIdentifierType;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.ccts.grid.studyconsumer.common.StudyConsumerI;
import gov.nih.nci.ccts.grid.studyconsumer.stubs.types.InvalidStudyException;
import gov.nih.nci.ccts.grid.studyconsumer.stubs.types.StudyCreationException;
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
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse;
import org.oasis.wsrf.properties.GetMultipleResourceProperties_Element;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.QueryResourcePropertiesResponse;
import org.oasis.wsrf.properties.QueryResourceProperties_Element;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;

public class CaaersStudyConsumer implements StudyConsumerI {

    private static Logger logger = Logger.getLogger(CaaersStudyConsumer.class);//logger = LogFactory.getLog(CaaersStudyConsumer.class);

    private OrganizationRepository organizationRepository;

    private SiteInvestigatorDao siteInvestigatorDao;

    private StudyDao studyDao;

    private ConfigProperty configurationProperty;

    private AuditHistoryRepository auditHistoryRepository;

    private String studyConsumerGridServiceUrl;

    private Integer rollbackInterval;
    
    private AuthorizationSwitch authorizationSwitch;

    //private StudyParticipantAssignmentAspect assignmentAspect;
    
    private OpenSessionInViewInterceptor openSessionInViewInterceptor;
    
    public void setOpenSessionInViewInterceptor(
			OpenSessionInViewInterceptor openSessionInViewInterceptor) {
		this.openSessionInViewInterceptor = openSessionInViewInterceptor;
	}
/*
	public void setAssignmentAspect(
			StudyParticipantAssignmentAspect assignmentAspect) {
		this.assignmentAspect = assignmentAspect;
	}
*/
	public void setAuthorizationSwitch(AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}

	private WebRequest preProcess() {
        //assignmentAspect.setSecurityInterceptor(new AspectJSecurityInterceptorStub());
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

    public void commit(gov.nih.nci.cabig.ccts.domain.Study studyDto) throws RemoteException,
                    InvalidStudyException {
        logger.info("Begining of studyConsumer : commit");
        /*
         * if(studyDto == null) { InvalidStudyException e = new InvalidStudyException();
         * e.setFaultReason("Null input"); e.setFaultString("Null input"); throw e; }
         * 
         * String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
         * 
         * try{ studyDao.commitInprogressStudy(ccIdentifier);
         * 
         * 
         * }catch(Exception exp){ log.error("Exception while trying to commit the study", exp);
         * InvalidStudyException e = new InvalidStudyException(); e.setFaultReason("Exception while
         * comitting study," + exp.getMessage()); e.setFaultString("Exception while comitting
         * study," + exp.getMessage()); throw e; }
         */
        logger.info("End of studyConsumer : commit");

    }

    /**
     * This method will remove from caAERs the study if its loadStatus is INPROGRESS.
     */
    public void rollback(gov.nih.nci.cabig.ccts.domain.Study studyDto) throws RemoteException,
                    InvalidStudyException {
    	System.out.println("Begining of studyConsumer : rollback");
    	WebRequest stubWebRequest = null;
    	stubWebRequest = preProcess();
        logger.info("Begining of studyConsumer : rollback");
        if (studyDto == null) {
            InvalidStudyException invalidStudyException = getInvalidStudyException("Null input");
            throw invalidStudyException;
        }

        String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
        gov.nih.nci.cabig.caaers.domain.Study study = fetchStudy(ccIdentifier,
                        OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
        // check if study was created by the grid service or not
        if (study == null) {
            String message = "Null input";
            throw getInvalidStudyException(message);
        }
        boolean checkIfEntityWasCreatedByGridService = auditHistoryRepository
                        .checkIfEntityWasCreatedByUrl(study.getClass(), study.getId(),
                                        studyConsumerGridServiceUrl);

        if (!checkIfEntityWasCreatedByGridService) {
        	logger.error("Study was not created by the grid service url:"
                            + studyConsumerGridServiceUrl + " so can not rollback this study:"
                            + study.getId());
            return;
        }
        System.out.println("Study (id:" + study.getId() + ") was created by the grid service url:"
                        + studyConsumerGridServiceUrl);

        // check if this study was created one minute before or not
        Calendar calendar = Calendar.getInstance();

        boolean checkIfStudyWasCreatedOneMinuteBeforeCurrentTime = auditHistoryRepository
                        .checkIfEntityWasCreatedMinutesBeforeSpecificDate(study.getClass(), study
                                        .getId(), calendar, rollbackInterval);
        try {
            if (checkIfStudyWasCreatedOneMinuteBeforeCurrentTime) {
            	System.out.println("Study was created one minute before the current time:"
                                + calendar.getTime().toString() + " so deleting this study:"
                                + study.getId());
                studyDao.delete(study);
            } else {
            	logger.error("Study was not created one minute before the current time:"
                                + calendar.getTime().toString()
                                + " so can not rollback this study:" + study.getId());
            }
        } catch (Exception expception) {
            String message = "Exception while comitting study," + expception.getMessage();
            throw getInvalidStudyException(message);
        } finally {
            postProcess(stubWebRequest);
        }
        System.out.println("End of studyConsumer : rollback");
    }
    private AeTerminology createCtcV3Terminology(Study study) {
        AeTerminology t = new AeTerminology();
        Ctc v3 = new Ctc();
        v3.setId(3);
        v3.setName(4 + "");
        t.setTerm(Term.CTC);
        t.setStudy(study);
        t.setCtcVersion(v3);
        return t;
    }

    /**
     * This will create a study in the DB. <p/> Assumptions:- Study is identified by Coordinating
     * Center identifier There will only be one Organization assigned identifer in the input, and it
     * is the CoordinatingCenterIdentifier
     */
    public void createStudy(gov.nih.nci.cabig.ccts.domain.Study studyDto) throws RemoteException,
                    InvalidStudyException, StudyCreationException {
    	System.out.println("Begining of studyConsumer : createStudy");
    	WebRequest stubWebRequest = null;
    	try {
    		stubWebRequest = preProcess();
        	logger.info("Begining of studyConsumer : createStudy");
            if (studyDto == null) throw getInvalidStudyException("null input");

            gov.nih.nci.cabig.caaers.domain.Study study = null;
            String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
            study = fetchStudy(ccIdentifier,
                            OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
            if (study != null) {
            	logger.error("Already a study with the same Coordinating Center Identifier ("
                                + ccIdentifier
                                + ") exists.Returning without processing the request.");
                return;
            }

            study = new gov.nih.nci.cabig.caaers.domain.LocalStudy();
            study.setGridId(studyDto.getGridId());
            populateStudyDetails(studyDto, study);
            studyDao.save(study);
            logger.info("Created the study :" + study.getId());
            logger.info("End of studyConsumer : createStudy");
        } catch (InvalidStudyException e) {
            throw e;
        } catch (StudyCreationException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error while creating study", e);
            throw new RemoteException("Unable to create study", e);
        } finally {
            postProcess(stubWebRequest);
        }

    }

    /**
     * This method will return the identifier specified by Coordinating center to this study.
     * 
     * @param studyDto
     * @return
     * @throws InvalidStudyException
     */
    String findCoordinatingCenterIdentifier(gov.nih.nci.cabig.ccts.domain.Study studyDto)
                    throws InvalidStudyException {
        String ccIdentifier = null;
        for (IdentifierType idType : studyDto.getIdentifier()) {
            if (idType instanceof OrganizationAssignedIdentifierType
                            && StringUtils
                                            .equals(
                                                            idType.getType(),
                                                            OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)) {
                ccIdentifier = idType.getValue();
                break;
            }
        }

        if (ccIdentifier == null) {

            InvalidStudyException exp = getInvalidStudyException("In Study/Identifiers, Coordinating Center Identifier is not available");
            throw exp;
        }
        return ccIdentifier;

    }

    void populateStudyDetails(gov.nih.nci.cabig.ccts.domain.Study studyDto,
                    gov.nih.nci.cabig.caaers.domain.Study study) throws StudyCreationException,
                    InvalidStudyException {
    	System.out.println("Creating study..");
        study.setShortTitle(studyDto.getShortTitleText());
        study.setLongTitle(studyDto.getLongTitleText());
        study.setPrecis(studyDto.getPrecisText());
        study.setDescription(studyDto.getDescriptionText());
        study.setStatus(gov.nih.nci.cabig.caaers.domain.Study.STATUS_ACTIVE);
        study.setAdeersReporting(Boolean.FALSE);
        study.setPhaseCode(studyDto.getPhaseCode());
        study.setMultiInstitutionIndicator(BooleanUtils.toBoolean(studyDto
                        .getMultiInstitutionIndicator()));
        study.addStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION);
        study.setBlindedIndicator(BooleanUtils.toBoolean(studyDto.getBlindedIndicator()));
        
         //fixed by srini , bug Id CAAERS-1038
         AeTerminology aet = createCtcV3Terminology(study);
         study.setAeTerminology(aet);

        // populate study identifiers
        IdentifierType[] identifierTypes = studyDto.getIdentifier();
        populateIdentifiers(study, identifierTypes);

        // populate study coordinating center and study funding sponsor
        StudyOrganizationType[] studyOrgTypes = studyDto.getStudyOrganization();
        populateStudyOrganizations(study, studyOrgTypes);

    }

    /**
     * Populates study identifiers
     * 
     * @param study
     * @param identifierTypes
     * @throws StudyCreationException
     */
    void populateIdentifiers(gov.nih.nci.cabig.caaers.domain.Study study,
                    IdentifierType[] identifierTypes) throws StudyCreationException {
        if (ArrayUtils.isEmpty(identifierTypes)) {
            logger.error("No identifiers are associated to this study");
            String message = "No identifiers are assigned to this study (grid Id : "
                            + study.getGridId() + ")";
            throw getStudyCreationException(message);
        }

        List<Lov> identifierLovs = configurationProperty.getMap().get("identifiersType");
        List<String> knownIdentifierTypes = new ArrayList<String>();
        for (Lov lov : identifierLovs) {
            knownIdentifierTypes.add(lov.getCode());
        }

        List<SystemAssignedIdentifier> sysIdentifiers = new ArrayList<SystemAssignedIdentifier>();
        List<OrganizationAssignedIdentifier> orgIdentifiers = new ArrayList<OrganizationAssignedIdentifier>();

        for (IdentifierType identifierType : identifierTypes) {
            if (identifierType instanceof SystemAssignedIdentifierType) {
                if (!knownIdentifierTypes.contains(identifierType.getType())) {
                	logger.error("The identifier type '" + identifierType.getType()
                                    + "' is unknown to caAERS. So ignoring the identifier("
                                    + identifierType.getValue() + ")");
                    continue;
                }
                SystemAssignedIdentifierType sysIdType = (SystemAssignedIdentifierType) identifierType;
                SystemAssignedIdentifier id = new SystemAssignedIdentifier();
                id.setGridId(identifierType.getGridId());
                id.setPrimaryIndicator(identifierType.getPrimaryIndicator());
                id.setType(sysIdType.getType());
                id.setValue(sysIdType.getValue());
                id.setSystemName(sysIdType.getSystemName());
                sysIdentifiers.add(id);
            } else if (identifierType instanceof OrganizationAssignedIdentifierType) {
                OrganizationAssignedIdentifierType orgIdType = (OrganizationAssignedIdentifierType) identifierType;
                OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
                id.setGridId(orgIdType.getGridId());
                id.setPrimaryIndicator(orgIdType.getPrimaryIndicator());
                id.setType(orgIdType.getType());
                id.setValue(orgIdType.getValue());
                id.setOrganization(fetchOrganization(orgIdType.getHealthcareSite()
                                .getNciInstituteCode()));
                orgIdentifiers.add(id);
            } else {
                String message = "Unknown IdentifierType in grid Study " + study.getGridId();
                throw getStudyCreationException(message);
            }
            /*
             * //find coordinating center identifier OrganizationAssignedIdentifier
             * coordinatingCenterId = null; //find funding sponsor identifier
             * OrganizationAssignedIdentifier fundingSponsorId = null;
             * for(OrganizationAssignedIdentifier id : orgIdentifiers) {
             * if(id.getType().equals(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)){
             * coordinatingCenterId = id; } if(StringUtils.equals(id.getType(),
             * OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE) ){ fundingSponsorId = id; } }
             * 
             * //remove them from organization identifiers list. //or create them if they dont exist
             * if(coordinatingCenterId != null){ orgIdentifiers.remove(coordinatingCenterId); }else {
             * coordinatingCenterId = new OrganizationAssignedIdentifier();
             * coordinatingCenterId.setType(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
             * coordinatingCenterId.setValue("UNKNOWN");
             * coordinatingCenterId.setOrganization(fetchOrganization("NCIC")); }
             * 
             * if(fundingSponsorId != null){ orgIdentifiers.remove(fundingSponsorId); }else {
             * fundingSponsorId = new OrganizationAssignedIdentifier();
             * fundingSponsorId.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
             * fundingSponsorId.setValue("UNKNOWN");
             * fundingSponsorId.setOrganization(fetchOrganization("NCIC")); }
             * 
             * 
             * //Add identifiers to the study. study.addIdentifier(fundingSponsorId);
             * study.addIdentifier(coordinatingCenterId);
             */
            for (OrganizationAssignedIdentifier id : orgIdentifiers) {
                study.addIdentifier(id);
            }

            for (SystemAssignedIdentifier id : sysIdentifiers) {
                study.addIdentifier(id);
            }

        }
    }

    private StudyCreationException getStudyCreationException(String message) {
        StudyCreationException exp = new StudyCreationException();
        exp.setFaultString(message);
        exp.setFaultReason(message);
        logger.error(message);
        return exp;
    }

    /**
     * Populates study organization and returns it.
     * 
     * @param study
     * @param studyOrgTypes
     * @throws InvalidStudyException
     */
    void populateStudyOrganizations(gov.nih.nci.cabig.caaers.domain.Study study,
                    StudyOrganizationType[] studyOrgTypes) throws StudyCreationException,
                    InvalidStudyException {

        if (ArrayUtils.isEmpty(studyOrgTypes)) {
            logger.error("No organization is associated to this study (gridId :"
                            + study.getGridId() + ")");
            StudyCreationException exp = new StudyCreationException();
            exp.setFaultString("No organization is associated to this study");
            exp.setFaultReason("No organization is associated to this study (gridId :"
                            + study.getGridId() + ")");
            throw exp;
        }

        List<StudyOrganization> studyOrgList = new ArrayList<StudyOrganization>();
        boolean isPrimarySponsor = true;
        for (StudyOrganizationType studyOrgType : studyOrgTypes) {
            StudyOrganization studyOrganization = null;
            if (studyOrgType instanceof StudyFundingSponsorType) {

                StudyFundingSponsor fundingSponsor = new StudyFundingSponsor();
                fundingSponsor.setPrimary(isPrimarySponsor);
                isPrimarySponsor = false;
                studyOrganization = fundingSponsor;

            } else if (studyOrgType instanceof StudyCoordinatingCenterType) {
                studyOrganization = new StudyCoordinatingCenter();
            } else if (studyOrgType instanceof StudySiteType) {
                studyOrganization = new StudySite();
            } else {
                logger.error("Unknown StudyOrganizationType in grid Study "
                                + studyOrgType.toString());
                throw getInvalidStudyException("Unknown StudyOrganizationType in grid Study ");
            }

            studyOrganization.setOrganization(fetchOrganization(studyOrgType.getHealthcareSite(0)
                            .getNciInstituteCode()));
            studyOrganization.setStudy(study);
            studyOrganization.setGridId(studyOrgType.getGridId());

            // populate investigators
            populateInvestigators(studyOrganization, studyOrgType.getStudyInvestigator());

            studyOrgList.add(studyOrganization);
        }// ~for

        study.setStudyOrganizations(studyOrgList);
    }

    /**
     * Will populate the investigator
     * 
     * @param studyOrganization
     * @param invTypes
     */
    void populateInvestigators(StudyOrganization studyOrganization, StudyInvestigatorType[] invTypes)
                    throws StudyCreationException {
        if (ArrayUtils.isEmpty(invTypes)) {
        	logger.error("No investigators are available in the input message");
            return;
        }

        for (StudyInvestigatorType invType : invTypes) {
            String invNCICode = invType.getHealthcareSiteInvestigator().getInvestigator(0)
                            .getNciIdentifier();

            SiteInvestigator siteInvestigator = null;

            if (StringUtils.isEmpty(invNCICode)) {
                logger.error("Investigator details are missing!");
                StudyCreationException exp = new StudyCreationException();
                exp
                                .setFaultReason("Missing investigator details in input : InvestigatorType.healthcareSiteInvesitagor.investigatorType[0].nciIdentifier");
                exp.setFaultString("Invalid input, missing investigator information");
                throw exp;
            }

            // find the study Investigator
            List<SiteInvestigator> siteInvestigators = siteInvestigatorDao
                            .getOrganizationInvestigators(studyOrganization.getOrganization());
            if (siteInvestigators != null) {
                // figure out the correct investigator
                for (SiteInvestigator si : siteInvestigators) {
                    if (StringUtils.equals(si.getInvestigator().getNciIdentifier(), invNCICode)) {
                        siteInvestigator = si;
                        break;
                    }
                }
            }

            // check if we were able to fetch siteInvestigator
            if (siteInvestigator == null) {
                logger
                                .error("Unable to associate investigators.No investigators are associated to organization :"
                                                + studyOrganization.getOrganization().getName()
                                                + " nciCode :"
                                                + studyOrganization.getOrganization()
                                                                .getNciInstituteCode());
                StudyCreationException exp = new StudyCreationException();
                exp
                                .setFaultReason("Unable to associate the given investigaor {investigatorNCI code :"
                                                + invNCICode
                                                + "} to the site {"
                                                + studyOrganization.getOrganization().getName()
                                                + ", nciCode :"
                                                + studyOrganization.getOrganization()
                                                                .getNciInstituteCode()
                                                + "}. The specified investigator should be associated to the organization.");
                exp
                                .setFaultReason("Missing SiteInvestigator (investigator NCI Code :"
                                                + invNCICode
                                                + ")details for the Organization {"
                                                + studyOrganization.getOrganization().getName()
                                                + ", nciCode : "
                                                + studyOrganization.getOrganization()
                                                                .getNciInstituteCode()
                                                + "}. The specified investigator should be associated to the organization.");
                throw exp;
            }

            StudyInvestigator studyInvestigator = new StudyInvestigator();
            studyInvestigator.setStudyOrganization(studyOrganization);
            studyInvestigator.setRoleCode(invType.getRoleCode());
            studyInvestigator.setSiteInvestigator(siteInvestigator);
            studyOrganization.addStudyInvestigators(studyInvestigator);
        }
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
            logger.error("No organization exists  nciCode :" + nciCode);
            throw new CaaersSystemException("No organization exist with nciCode :" + nciCode);
        }
        if (orgList.size() > 1) {
            logger.error("Multiple organizations exist with same NCI code :" + nciCode);
        }

        return orgList.get(0);
    }

    gov.nih.nci.cabig.caaers.domain.Study fetchStudy(String ccIdentifier, String identifierType) {
        StudyQuery studyQuery = new StudyQuery();
        studyQuery.filterByIdentifierValueExactMatch(ccIdentifier);
        studyQuery.filterByIdentifierType(identifierType);
        List<gov.nih.nci.cabig.caaers.domain.Study> studies = studyDao.find(studyQuery);
        if (studies == null || studies.isEmpty()) return null;
        return studies.get(0);

    }

    private InvalidStudyException getInvalidStudyException(String message) {
        logger.error(message);
        InvalidStudyException invalidStudyException = new InvalidStudyException();
        invalidStudyException.setFaultReason(message);

        invalidStudyException.setFaultString(message);
        return invalidStudyException;
    }

    // /CONFIGURATION


    @Required
    public SiteInvestigatorDao getSiteInvestigatorDao() {
        return siteInvestigatorDao;
    }

    @Required
    public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }

    @Required
    public StudyDao getStudyDao() {
        return studyDao;
    }

    @Required
    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Required
    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    @Required
    public void setAuditHistoryRepository(AuditHistoryRepository auditHistoryRepository) {
        this.auditHistoryRepository = auditHistoryRepository;
    }

    @Required
    public void setStudyConsumerGridServiceUrl(String studyConsumerGridServiceUrl) {
        this.studyConsumerGridServiceUrl = studyConsumerGridServiceUrl;
    }

    @Required
    public void setRollbackInterval(Integer rollbackInterval) {
        this.rollbackInterval = rollbackInterval;
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
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
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
}
