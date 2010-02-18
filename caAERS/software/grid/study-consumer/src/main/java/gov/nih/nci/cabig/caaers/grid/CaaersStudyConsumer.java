package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.PersonRole;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
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
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.ccts.domain.HealthcareSiteType;
import gov.nih.nci.cabig.ccts.domain.IdentifierType;
import gov.nih.nci.cabig.ccts.domain.InvestigatorType;
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

import java.rmi.RemoteException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.namespace.QName;

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
import org.springframework.web.context.request.WebRequest;

/**
 * @author Srini
 * @author Biju Joseph
 * @author Monish Dombla
 *
 */
public class CaaersStudyConsumer implements StudyConsumerI {

    private static Logger logger = Logger.getLogger(CaaersStudyConsumer.class);

    private OrganizationRepository organizationRepository;

    private SiteInvestigatorDao siteInvestigatorDao;
    
    private InvestigatorRepository investigatorRepository;

    private StudyDao studyDao;

    private ConfigProperty configurationProperty;

    private AuditHistoryRepository auditHistoryRepository;

    private String studyConsumerGridServiceUrl;

    private Integer rollbackInterval;
    

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
     * This method will remove a study from caAERS. A rollback can only be successful if 
     *   <li> the request is received within a minute of study creation </li>
     *   <li> the study was created via the studyConsumerGrid message</li>
     *   
     *   Assumption:-
     *   The study to be deleted is identified using the Coordinating Center identifier. 
     */
    public void rollback(gov.nih.nci.cabig.ccts.domain.Study studyDto) throws RemoteException, InvalidStudyException {
    	
    	if(logger.isDebugEnabled())logger.debug("Begining of studyConsumer : rollback");
    	
        if (studyDto == null) {
        	logger.error("Invalid study input message, the studydto is null");
        	InvalidStudyException invalidStudyException = getInvalidStudyException("Null input");
            throw invalidStudyException;
        }

        String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
        gov.nih.nci.cabig.caaers.domain.Study study = fetchStudy(ccIdentifier,OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
        
        // check if study was created by the grid service or not
        if (study == null) {
        	logger.error("Unable to find study having Identifier [type: " + OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE + ", value :" + ccIdentifier + "]" );
            String message = "Null input";
            throw getInvalidStudyException(message);
        }
        
        boolean checkIfEntityWasCreatedByGridService = auditHistoryRepository.checkIfEntityWasCreatedByUrl(study.getClass(), study.getId(),
                                        studyConsumerGridServiceUrl);

        if (!checkIfEntityWasCreatedByGridService) {
        	logger.warn("Study was not created by the grid service url:" + studyConsumerGridServiceUrl + " so can not rollback this study: "  + study.getId());
            return;
        }
        
        if(logger.isDebugEnabled()) logger.debug("Study (id:" + study.getId() + ") was created by the grid service url:" + studyConsumerGridServiceUrl);

        // check if this study was created one minute before or not
        Calendar calendar = Calendar.getInstance();

        boolean recentlyCreated = auditHistoryRepository.checkIfEntityWasCreatedMinutesBeforeSpecificDate(study.getClass(), 
        			study.getId(), calendar, rollbackInterval);
        try {
            if (recentlyCreated) {
            	if(logger.isInfoEnabled()) logger.info("Study was created one minute before the current time:" + calendar.getTime().toString() + " so deleting this study:" + study.getId());
                studyDao.delete(study);
            } else {
            	logger.warn("Study was not created one minute before the current time:" + calendar.getTime().toString() + " so can not rollback this study:" + study.getId());
            }
        } catch (Exception expception) {
            String message = "Exception while comitting study," + expception.getMessage();
            throw getInvalidStudyException(message);
        } 
        
        if(logger.isDebugEnabled()) logger.debug("End of studyConsumer : rollback");
    
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
     * Center identifier There will only be one Organization assigned identifier in the input, and it
     * is the CoordinatingCenterIdentifier
     */
    public void createStudy(gov.nih.nci.cabig.ccts.domain.Study studyDto) throws RemoteException,
                    InvalidStudyException, StudyCreationException {
    	try {
        	logger.info("Begining of studyConsumer : createStudy");
            if (studyDto == null) throw getInvalidStudyException("null input");

            gov.nih.nci.cabig.caaers.domain.Study study = null;
            String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
            study = fetchStudy(ccIdentifier, OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
            if (study != null) {
            	logger.error("Already a study with the same Coordinating Center Identifier ("
                                + ccIdentifier
                                + ") exists.Returning without processing the request.");
                 return;
            }
            
            String coppaIdentifier = findCoppaIdentifier(studyDto);
            if (coppaIdentifier != null) {
            	study = new gov.nih.nci.cabig.caaers.domain.RemoteStudy();
            } else {            
            	study = new gov.nih.nci.cabig.caaers.domain.LocalStudy();
            }
            study.setGridId(studyDto.getGridId());
            populateStudyDetails(studyDto, study, coppaIdentifier);
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
        } 

    }
    
    /**
     * This method will retrieve from the Study DTO the identifier value of a specific type. 
     * @param studyDto
     * @param identifierType
     * @return
     */
    private String findOrganizationIdentifier(gov.nih.nci.cabig.ccts.domain.Study studyDto , String identifierType){
    	
    	for (IdentifierType idType : studyDto.getIdentifier()) {
    		if(idType instanceof SystemAssignedIdentifierType) continue;
            if (StringUtils.equals(idType.getType(),identifierType)) return idType.getValue();
        }
    	
    	return null;
    }
    /**
     * This method will retrieve from the Study DTO the System identifier value of a specific type. 
     * @param studyDto
     * @param identifierType
     * @return
     */
    private String findSystemIdentifier(gov.nih.nci.cabig.ccts.domain.Study studyDto , String identifierType){
    	
    	for (IdentifierType idType : studyDto.getIdentifier()) {
    		if(idType instanceof OrganizationAssignedIdentifierType) continue;
            if (StringUtils.equals(idType.getType(),identifierType)) return idType.getValue();
        }
    	
    	return null;
    }
    
    /**
     * This method will return the identifier specified by Coordinating center to this study.
     * 
     * @param studyDto
     * @return
     * @throws InvalidStudyException
     */
    String findCoordinatingCenterIdentifier(gov.nih.nci.cabig.ccts.domain.Study studyDto) throws InvalidStudyException {
        String ccIdentifier = findOrganizationIdentifier(studyDto, OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
        if (ccIdentifier == null) {
        	logger.error("Could not find Coordinating center identifier in the Study message");
            InvalidStudyException exp = getInvalidStudyException("In Study/Identifiers, Coordinating Center Identifier is not available");
            throw exp;
        }
        return ccIdentifier;
    }

    /**
     * This method will return the identifier specified by COPPA.
     */
    String findCoppaIdentifier(gov.nih.nci.cabig.ccts.domain.Study studyDto) {
    	return findSystemIdentifier(studyDto,  "COPPA Identifier");
    }
    
    /**
     * This method will find out the Sponsor Identifier
     * @param studyDto
     * @return
     */
    String findFundingSponsorIdentifier(gov.nih.nci.cabig.ccts.domain.Study studyDto){
    	return findOrganizationIdentifier(studyDto, OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
    }
    
    
    void populateStudyDetails(gov.nih.nci.cabig.ccts.domain.Study studyDto,
                    gov.nih.nci.cabig.caaers.domain.Study study, String coppaIdentifier) throws StudyCreationException,
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
        if (coppaIdentifier != null ) {
        	study.setExternalId(coppaIdentifier);
        }
        
         //fixed by srini , bug Id CAAERS-1038
         AeTerminology aet = createCtcV3Terminology(study);
         study.setAeTerminology(aet);
         
         // populate study coordinating center and study funding sponsor
         StudyOrganizationType[] studyOrgTypes = studyDto.getStudyOrganization();
         populateStudyOrganizations(study, studyOrgTypes);

        // populate study identifiers
        IdentifierType[] identifierTypes = studyDto.getIdentifier();
        populateIdentifiers(study, identifierTypes);
    }

    /**
     * Populates study identifiers
     * 
     * @param study
     * @param identifierTypes
     * @throws StudyCreationException
     */
    void populateIdentifiers(gov.nih.nci.cabig.caaers.domain.Study study, IdentifierType[] identifierTypes) throws StudyCreationException {
        if (ArrayUtils.isEmpty(identifierTypes)) {
            logger.error("No identifiers are associated to this study");
            String message = "No identifiers are assigned to this study (grid Id : "+ study.getGridId() + ")";
            throw getStudyCreationException(message);
        }
        
        //figure out the list of known identifiers
        List<Lov> identifierLovs = configurationProperty.getMap().get("identifiersType");
        List<String> knownIdentifierTypes = new ArrayList<String>();
        for (Lov lov : identifierLovs) {
            knownIdentifierTypes.add(lov.getCode());
        }

        
        List<SystemAssignedIdentifier> sysIdentifiers = new ArrayList<SystemAssignedIdentifier>();
        List<OrganizationAssignedIdentifier> orgIdentifiers = new ArrayList<OrganizationAssignedIdentifier>();
        
        OrganizationAssignedIdentifier ccIdentifier = null;
        OrganizationAssignedIdentifier sponsorIdentifier = null;
        

        for (IdentifierType identifierType : identifierTypes) {
        	if (!knownIdentifierTypes.contains(identifierType.getType())) {
            	logger.warn("The identifier type '" + identifierType.getType()+ "' is unknown to caAERS. So ignoring the identifier(" + identifierType.getValue() + ")");
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
                sysIdentifiers.add(id);
                
            } else if (identifierType instanceof OrganizationAssignedIdentifierType) {
            	
                OrganizationAssignedIdentifierType orgIdType = (OrganizationAssignedIdentifierType) identifierType;
                OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
                id.setGridId(orgIdType.getGridId());
                id.setPrimaryIndicator(orgIdType.getPrimaryIndicator());
                id.setType(orgIdType.getType());
                id.setValue(orgIdType.getValue());
                id.setOrganization(fetchOrganization(orgIdType.getHealthcareSite()));
 
                if(StringUtils.equals(id.getType(),OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)){
            		ccIdentifier = id;
            	}else if(StringUtils.equals(id.getType(),OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)){
            		sponsorIdentifier = id;
            	}else{
            		orgIdentifiers.add(id);
            	}
            } 
        }

        //if the sponsor identifier is not supplied, use coordinating center instead. 
        if(sponsorIdentifier == null){
        	  sponsorIdentifier = new OrganizationAssignedIdentifier();
              sponsorIdentifier.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
              sponsorIdentifier.setValue(ccIdentifier.getValue());
              sponsorIdentifier.setOrganization(study.getPrimaryFundingSponsorOrganization());
        }
      
        study.addIdentifier(sponsorIdentifier);
        study.addIdentifier(ccIdentifier);
        
        for (OrganizationAssignedIdentifier id : orgIdentifiers) {
            study.addIdentifier(id);
        }

        for (SystemAssignedIdentifier id : sysIdentifiers) {
            study.addIdentifier(id);
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

            studyOrganization.setOrganization(fetchOrganization(studyOrgType.getHealthcareSite(0)));
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
        
        SiteInvestigator siteInvestigator = null;
        InvestigatorQuery query = null;
        Investigator dbInvestigator = null;
        StudyInvestigator studyInvestigator = null;
        
        for (StudyInvestigatorType invType : invTypes) {
        	//Extract NCI IDENTIFIER of Investigator 
            String invNCICode = invType.getHealthcareSiteInvestigator().getInvestigator(0).getNciIdentifier();
            if (StringUtils.isEmpty(invNCICode)) {
                logger.error("Investigator details are missing!");
                StudyCreationException exp = new StudyCreationException();
                exp.setFaultReason("Missing investigator details in input : InvestigatorType.healthcareSiteInvesitagor.investigatorType[0].nciIdentifier");
                exp.setFaultString("Invalid input, missing investigator information");
                throw exp;
            }
            query = new InvestigatorQuery();
            query.filterByNciIdentifierExactMatch(invNCICode);
            List<Investigator> investigators = investigatorRepository.searchInvestigator(query);
            if(investigators == null){
            	dbInvestigator = null;
            }else{
            	dbInvestigator = investigators.get(0);
            }
            //Create or Update the Investigator as needed.
            dbInvestigator = createOrUpdateInvestigator(dbInvestigator, studyOrganization.getOrganization(), invType.getHealthcareSiteInvestigator().getInvestigator(0));
            //Extract the SiteInvestigator from the Investigator 
            siteInvestigator = dbInvestigator.findSiteInvestigator(studyOrganization.getOrganization());
            //Associate SiteInvestigator to Study.
            studyInvestigator = new StudyInvestigator();
            studyInvestigator.setStudyOrganization(studyOrganization);
            String roleCode = null;
            PersonRole[] caaersRoles = new PersonRole[]{PersonRole.SITE_INVESTIGATOR, PersonRole.SITE_PRINCIPAL_INVESTIGATOR, PersonRole.PRINCIPAL_INVESTIGATOR};
            if (invType.getRoleCode() == null) {
            	StudyCreationException exp = new StudyCreationException();
            	exp.setFaultReason("Investigator role is NULL in Study message");
            	throw exp;
            }
    		for (PersonRole caaersRole:caaersRoles) {
    			if (caaersRole.getDisplayName().equals(invType.getRoleCode())) {
    				roleCode = caaersRole.getRoleCode();
    				break;
    			}
    		}

            if (roleCode == null) {
            	StudyCreationException exp = new StudyCreationException();
            	exp.setFaultReason("Supplied Investigator role "+ invType.getRoleCode() + " does not map with roles in caAERS");
            	throw exp;            	
            }
            studyInvestigator.setRoleCode(roleCode);
            studyInvestigator.setStartDate(siteInvestigator.getStartDate());
            studyInvestigator.setSiteInvestigator(siteInvestigator);
            studyOrganization.addStudyInvestigators(studyInvestigator);
        }
    }
    
    /**
     * This method creates an Investigator object and saves it if investigator is null.
     * If investigator is not null & is not associated to the organization provided. A new SiteInvestigator object is created
     * and associated to the investigator and saved. 
     * @param investigator
     * @param organization
     * @param invType
     * @return
     */
    protected Investigator createOrUpdateInvestigator(Investigator investigator, Organization organization, InvestigatorType invType){
    	SiteInvestigator siteInvestigator = null;
    	if(investigator != null){
    		siteInvestigator = investigator.findSiteInvestigator(organization);
    	}else{
        	//If externalId is not empty create a RemoteInvestigator else create a LocalInvestigator
        	if(StringUtils.isNotEmpty(invType.getExternalId())){
        		investigator = new RemoteInvestigator();
        	}else{
        		investigator = new LocalInvestigator();
        	}
        	//Set all the basic values.
        	investigator.setAllowedToLogin(Boolean.FALSE);
        	investigator.setExternalId(invType.getExternalId());
        	investigator.setFirstName(invType.getFirstName());
        	investigator.setLastName(invType.getLastName());
        	investigator.setNciIdentifier(invType.getNciIdentifier());
        	investigator.setPhoneNumber(invType.getPhoneNumber());
        	investigator.setFaxNumber(invType.getFaxNumber());
        	investigator.setEmailAddress(invType.getEmail());
    	}
    	//Create Site,Investigator association.
    	if(siteInvestigator == null){
        	siteInvestigator = new SiteInvestigator();
        	siteInvestigator.setStartDate(DateUtils.today());
        	siteInvestigator.setInvestigator(investigator);
        	siteInvestigator.setOrganization(organization);
        	investigator.addSiteInvestigator(siteInvestigator);
    	}
    	//Save to the DB
    	investigatorRepository.save(investigator, "");

    	return investigator;
    }
    
    /**
     * Fetches the organization from the DB. If one does not exist an appropriate Organization is created and saved.
     * 
     * @param HealthcareSiteType
     * @return Organization
     */
    protected Organization fetchOrganization(HealthcareSiteType healthCareSiteType) {

    	if(healthCareSiteType == null){
    		return null;
    	}
    	//Check if an organization exists in caAERS for the given NCI CODE.
        OrganizationQuery orgQuery = new OrganizationQuery();
        if (StringUtils.isNotEmpty(healthCareSiteType.getNciInstituteCode())) {
            orgQuery.filterByNciCodeExactMatch(healthCareSiteType.getNciInstituteCode());
        }
        List<Organization> orgList = organizationRepository.searchOrganization(orgQuery);
        Organization organization = null;
        //If there is no Organization in caAERS with given NCI CODE then create an Organization in caAERS.
        if (orgList == null || orgList.isEmpty()) {
        	//if externalId is provided it means that Organization is Remote. (Fetched from COPPA)
        	if (StringUtils.isNotEmpty(healthCareSiteType.getGridId())) {
        		organization = new RemoteOrganization();
        		populateOrganization(healthCareSiteType,organization);
        	}else{
        		organization = new LocalOrganization();
        		populateOrganization(healthCareSiteType,organization);
        	}
        	organizationRepository.create(organization);
        	return organization;
        }
        return orgList.get(0);
    }
    
    /**
     * This method populates an Organization entity given a HealthcareSiteType
     * @param HealthcareSiteType
     * @param Organization
     */
    protected void populateOrganization(HealthcareSiteType healthCareSiteType, Organization organization){
    	organization.setNciInstituteCode(healthCareSiteType.getNciInstituteCode());
		organization.setName(healthCareSiteType.getName());
		organization.setDescriptionText(healthCareSiteType.getDescriptionText());
		organization.setExternalId(healthCareSiteType.getGridId());
		if(healthCareSiteType.getAddress() != null){
			organization.setCity(healthCareSiteType.getAddress().getCity());
			organization.setState(healthCareSiteType.getAddress().getStateCode());
			organization.setCountry(healthCareSiteType.getAddress().getCountryCode());
		}
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
		//Auto-generated method stub
		return null;
	}

	public GetResourcePropertyResponse getResourceProperty(QName params) throws RemoteException {
		//Auto-generated method stub
		return null;
	}

	public QueryResourcePropertiesResponse queryResourceProperties(QueryResourceProperties_Element params) throws RemoteException {
		//Auto-generated method stub
		return null;
	}
	
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
	
	@Required
	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}
	
    @SuppressWarnings("unused")
	private static class StubWebRequest implements WebRequest {
        public String getParameter(final String paramName) {
            return null;
        }

        public String[] getParameterValues(final String paramName) {
            return null;
        }

        @SuppressWarnings("unchecked")
		public Map getParameterMap() {
            return Collections.emptyMap();
        }

        public Locale getLocale() {
            return null;
        }

        public String getContextPath() {
            return null;
        }

        public String getRemoteUser() {
            return null;
        }

        public Principal getUserPrincipal() {
            return null;
        }

        public boolean isUserInRole(String role) {
            return false;
        }

        public boolean isSecure() {
            return false; 
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
