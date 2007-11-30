package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.LoadStatus;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.ccts.grid.IdentifierType;
import gov.nih.nci.ccts.grid.OrganizationAssignedIdentifierType;
import gov.nih.nci.ccts.grid.StudyCoordinatingCenterType;
import gov.nih.nci.ccts.grid.StudyFundingSponsorType;
import gov.nih.nci.ccts.grid.StudyInvestigatorType;
import gov.nih.nci.ccts.grid.StudyOrganizationType;
import gov.nih.nci.ccts.grid.StudySiteType;
import gov.nih.nci.ccts.grid.SystemAssignedIdentifierType;
import gov.nih.nci.ccts.grid.common.StudyConsumerI;
import gov.nih.nci.ccts.grid.studyconsumer.stubs.types.InvalidStudyException;
import gov.nih.nci.ccts.grid.studyconsumer.stubs.types.StudyCreationException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;



public class CaaersStudyConsumer implements StudyConsumerI {
	
	private static final Log log = LogFactory.getLog(CaaersStudyConsumer.class);
	
	private OrganizationDao organizationDao;
	private SiteInvestigatorDao siteInvestigatorDao;
	private StudyDao studyDao;
	private ConfigProperty configurationProperty;
	
	
	
	public void commit(gov.nih.nci.ccts.grid.Study studyDto) throws RemoteException,
			InvalidStudyException {
		log.info("Begining of studyConsumer : commit");
		System.out.println("-- StudyConsumer : commit");
		
		if(studyDto == null) {
			InvalidStudyException e = new InvalidStudyException();
			e.setFaultReason("Null input");
			e.setFaultString("Null input");
			throw e;
		}
		
		String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
		
		try{
			studyDao.commitInprogressStudy(ccIdentifier);
			
		
		}catch(Exception exp){
			log.error("Exception while trying to commit the study", exp);
			InvalidStudyException e = new InvalidStudyException();
			e.setFaultReason("Exception while comitting study,"  + exp.getMessage());
			e.setFaultString("Exception while comitting study," + exp.getMessage());
			throw e;
		}
		log.info("End of studyConsumer : commit");

	}
	
	/**
	 * This method will remove from caAERs the study if its loadStatus is INPROGRESS.
	 */
	public void rollback(gov.nih.nci.ccts.grid.Study studyDto) throws RemoteException,
			InvalidStudyException {
		log.info("Begining of studyConsumer : rollback");
		System.out.println("-- StudyConsumer : rollback");
		if(studyDto == null) {
			InvalidStudyException e = new InvalidStudyException();
			e.setFaultReason("Null input");
			e.setFaultString("Null input");
			throw e;
		}
		
		String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
		
		try{
			studyDao.deleteInprogressStudy(ccIdentifier);
		
		}catch(Exception exp){
			log.error("Exception while trying to rollback the study", exp);
			InvalidStudyException e = new InvalidStudyException();
			e.setFaultReason("Exception while comitting study,"  + exp.getMessage());
			e.setFaultString("Exception while comitting study," + exp.getMessage());
			throw e;
		}
		log.info("End of studyConsumer : rollback");
	}
	
	
	/**
	 * This will create a study in the DB. 
	 * 
	 * Assumptions:-
	 *    Study is identified by Coordinating Center identifier
	 *    There will only be one Organization assigned identifer in the input, and it is the CoordinatingCenterIdentifier
	 *    
	 */
	public void createStudy(gov.nih.nci.ccts.grid.Study studyDto) throws RemoteException,
			InvalidStudyException, StudyCreationException {
		log.info("Begining of studyConsumer : createStudy");
		System.out.println("-- StudyConsumer : createStudy");
		if(studyDto == null) throw new InvalidStudyException();
		
		Study study = null;
		String ccIdentifier = findCoordinatingCenterIdentifier(studyDto);
		if(studyDao.isInprogressStudyExist(ccIdentifier)){
			log.info("Already a study with the same Coordinating Center Identifier (" + ccIdentifier +") exists.Returning without processing the request.");
			return;
		}
		
		study = new Study();
		study.setGridId(studyDto.getGridId());
		study.setLoadStatus(LoadStatus.INPROGRESS.getCode());
		populateStudyDetails(studyDto, study);
		studyDao.save(study);
		log.info("Created the study :" + study.getId());
		log.info("End of studyConsumer : createStudy");
	}
	
	/**
	 * This method will return the identifier specified by Coordinating center to this study.
	 * @param studyDto
	 * @return
	 * @throws InvalidStudyException
	 */
	String findCoordinatingCenterIdentifier(gov.nih.nci.ccts.grid.Study studyDto) throws InvalidStudyException {
		String ccIdentifier = null;
		for(IdentifierType idType : studyDto.getIdentifier()){
			if(idType instanceof OrganizationAssignedIdentifierType &&
				StringUtils.equals(idType.getType(), OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)){
				ccIdentifier = idType.getValue();
				break;
			}
		}
			
		if(ccIdentifier == null){
				
			InvalidStudyException exp = new InvalidStudyException();
			exp.setFaultReason("In Study/Identifiers, Coordinating Center Identifier is not available");
			exp.setFaultString("In Study/Identifiers, Coordinating Center Identifier is not available");
			throw exp;
		}
		return ccIdentifier;
		
		
	}
	
	void populateStudyDetails(gov.nih.nci.ccts.grid.Study studyDto , Study study) throws StudyCreationException, InvalidStudyException{
		
		study.setShortTitle(studyDto.getShortTitleText());
		study.setLongTitle(studyDto.getLongTitleText());
		study.setPrecis(studyDto.getPrecisText());
		study.setDescription(studyDto.getDescriptionText());
		study.setStatus(Study.STATUS_ACTIVE);
		study.setPhaseCode(studyDto.getPhaseCode());
		study.setMultiInstitutionIndicator(BooleanUtils.toBoolean(studyDto.getMultiInstitutionIndicator()));
		
		
		//populate study identifiers
		IdentifierType[] identifierTypes = studyDto.getIdentifier();
		populateIdentifiers(study,identifierTypes);
		
		//populate study coordinating center and study funding sponsor
		StudyOrganizationType[] studyOrgTypes = studyDto.getStudyOrganization();
		populateStudyOrganizations(study, studyOrgTypes);
		
	}
	
	/**
	 * Populates study identifiers
	 * @param study
	 * @param identifiertypes
	 * @throws StudyCreationException
	 */
	void populateIdentifiers(Study study, IdentifierType[] identifierTypes) throws StudyCreationException {
		if(ArrayUtils.isEmpty(identifierTypes)){
			log.error("No identifiers are associated to this study");
			StudyCreationException exp = new StudyCreationException();
			exp.setFaultString("No identifiers are assigned to this study");
			exp.setFaultReason("No identifiers are assigned to this study (grid Id : " + study.getGridId() + ")");
			throw exp;
		}
		
		List<Lov> identifierLovs = configurationProperty.getMap().get("identifiersType");
		List<String> knownIdentifierTypes = new ArrayList<String>();
		for(Lov lov : identifierLovs){
			knownIdentifierTypes.add(lov.getCode());
		}
		
		for(IdentifierType identifierType : identifierTypes){
			if(identifierType instanceof SystemAssignedIdentifierType){
				if(!knownIdentifierTypes.contains(identifierType.getType())){
					log.warn("The identifier type '" + identifierType.getType()+"' is unknown to caAERS. So ignoring the identifier("  + identifierType.getValue() + ")");
					continue;
				}
				SystemAssignedIdentifierType sysIdType = (SystemAssignedIdentifierType)identifierType;
				SystemAssignedIdentifier id = new SystemAssignedIdentifier();
				id.setGridId(identifierType.getGridId());
				id.setPrimaryIndicator(identifierType.getPrimaryIndicator());
				id.setType(sysIdType.getType());
				id.setValue(sysIdType.getValue());
				id.setSystemName(sysIdType.getSystemName());
				study.addIdentifier(id);
			}else
				if(identifierType instanceof OrganizationAssignedIdentifierType){
				OrganizationAssignedIdentifierType orgIdType = (OrganizationAssignedIdentifierType)identifierType;
				OrganizationAssignedIdentifier id = new OrganizationAssignedIdentifier();
				id.setGridId(orgIdType.getGridId());
				id.setPrimaryIndicator(orgIdType.getPrimaryIndicator());
				id.setType(id.COORDINATING_CENTER_IDENTIFIER_TYPE);
				id.setValue(orgIdType.getValue());
				id.setOrganization(fetchOrganization(orgIdType.getHealthcareSite().getNciInstituteCode()));
				study.addIdentifier(id);
			}else {
				log.error("Unknown IdentifierType in grid Study " + study.getGridId());
				StudyCreationException e = new StudyCreationException();
				e.setFaultReason("Unknown IdentifierType in grid Study ");
				e.setFaultString("Unknown IdentifierType in grid Study ");
				throw e;
			}
		}
	}
	
	/**
	 * Populates study organization and returns it.
	 * 
	 * @param study
	 * @param studyOrgTypeList
	 * @throws InvalidStudyException
	 */
	void populateStudyOrganizations(Study study, StudyOrganizationType[] studyOrgTypes) throws StudyCreationException, InvalidStudyException{
		
		if(ArrayUtils.isEmpty(studyOrgTypes)){
			log.error("No organization is associated to this study (gridId :" + study.getGridId() +")");
			StudyCreationException exp = new StudyCreationException();
			exp.setFaultString("No organization is associated to this study");
			exp.setFaultReason("No organization is associated to this study (gridId :" + study.getGridId() +")");
			throw exp;
		}
		
		
		List<StudyOrganization> studyOrgList = new ArrayList<StudyOrganization>();
		boolean isPrimarySponsor = true;
		for(StudyOrganizationType studyOrgType : studyOrgTypes){
			StudyOrganization studyOrganization = null;
			if(studyOrgType instanceof StudyFundingSponsorType){
				
				StudyFundingSponsor fundingSponsor = new StudyFundingSponsor();
				fundingSponsor.setPrimary(isPrimarySponsor);
				isPrimarySponsor = false;
				studyOrganization = fundingSponsor;
				
			}else if(studyOrgType instanceof StudyCoordinatingCenterType){
				studyOrganization = new StudyCoordinatingCenter();
			}else if(studyOrgType instanceof StudySiteType){
				studyOrganization = new StudySite();
			}else {
				log.error("Unknown StudyOrganizationType in grid Study " + studyOrgType.toString());
				InvalidStudyException e = new InvalidStudyException();
				e.setFaultReason("Unknown StudyOrganizationType in grid Study ");
				e.setFaultString("Unknown StudyOrganizationType in grid Study ");
				throw e;
			}

			studyOrganization.setOrganization(fetchOrganization(
					studyOrgType.getHealthcareSite(0).getNciInstituteCode()));
			studyOrganization.setStudy(study);
			studyOrganization.setGridId(studyOrgType.getGridId());
			
			
			//populate investigators
			populateInvestigators(studyOrganization, studyOrgType.getStudyInvestigator());
			
			studyOrgList.add(studyOrganization);
		}//~for
		
		study.setStudyOrganizations(studyOrgList);
	}
	
	/**
	 * Will populate the investigator
	 * @param studyOrganization
	 * @param invTypes
	 */
	void populateInvestigators(StudyOrganization studyOrganization, StudyInvestigatorType[] invTypes) throws StudyCreationException{
		if(ArrayUtils.isEmpty(invTypes)){
			log.info("No investigators are available in the input message");
			return;
		}
		
		for(StudyInvestigatorType invType : invTypes){
			String invNCICode = invType.getHealthcareSiteInvestigator().getInvestigator(0).getNciIdentifier();
			
			SiteInvestigator siteInvestigator = null;
			
			if(StringUtils.isEmpty(invNCICode)){
				log.error("Investigator details are missing!");
				StudyCreationException exp = new StudyCreationException();
				exp.setFaultReason("Missing investigator details in input : InvestigatorType.healthcareSiteInvesitagor.investigatorType[0].nciIdentifier");
				exp.setFaultString("Invalid input, missing investigator information");
				throw exp;
			}
			
			//find the study Investigator
			List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getOrganizationInvestigators(studyOrganization.getOrganization());
			if(siteInvestigators != null){
				//figure out the correct investigator
				for(SiteInvestigator si : siteInvestigators){
				  if(StringUtils.equals(si.getInvestigator().getNciIdentifier(), invNCICode)){
					  siteInvestigator = si;
					  break;
				  }
				}
			}
			
			//check if we were able to fetch siteInvestigator
			if(siteInvestigator == null){
				log.error("Unable to associate investigators.No investigators are associated to organization :" + studyOrganization.getOrganization().getName() + " nciCode :" + studyOrganization.getOrganization().getNciInstituteCode());
				StudyCreationException exp = new StudyCreationException();
				exp.setFaultReason("Unable to associate the given investigaor {investigatorNCI code :" + invNCICode +"} to the site {" + studyOrganization.getOrganization().getName()+", nciCode :" + studyOrganization.getOrganization().getNciInstituteCode() + "}. The specified investigator should be associated to the organization.");
				exp.setFaultReason("Missing SiteInvestigator (investigator NCI Code :" + invNCICode +")details for the Organization {" + studyOrganization.getOrganization().getName() +", nciCode : " + studyOrganization.getOrganization().getNciInstituteCode() + "}. The specified investigator should be associated to the organization.");
				throw exp;
			}
			

			StudyInvestigator studyInvestigator = new StudyInvestigator();
			studyInvestigator.setStudyOrganization(studyOrganization);
			studyInvestigator.setRoleCode(invType.getRoleCode());
			studyInvestigator.setSiteInvestigator(siteInvestigator);
			studyInvestigator.setStatusCode(invType.getStatusCode());
			studyOrganization.addStudyInvestigators(studyInvestigator);
		}
	}
	
	
	
	
	/**
	 * Fetches the organization from the DB
	 * @param gridId
	 * @return
	 */
	Organization fetchOrganization( String nciCode){
		OrganizationQuery orgQuery = new OrganizationQuery();
		
		
		if(StringUtils.isNotEmpty(nciCode)){
			orgQuery.filterByNciCodeExactMatch(nciCode);
		}
		
		List<Organization> orgList = organizationDao.searchOrganization(orgQuery);
		
		if(orgList == null || orgList.isEmpty()){
			log.error("No organization exists  nciCode :" + nciCode);
			throw new CaaersSystemException("No organization exist with nciCode :" + nciCode);
		}
		if(orgList.size() > 1){
			log.error("Multiple organizations exist with same NCI code :" + nciCode);
		}
		
		return orgList.get(0);
	}
	
	Study fetchStudy(String ccIdentifier, String identifierType){
		StudyQuery studyQuery = new StudyQuery();
		studyQuery.filterByIdentifierValueExactMatch(ccIdentifier);
		studyQuery.filterByIdentifierType(identifierType);
		List<Study> studies = studyDao.find(studyQuery);
		if(studies == null || studies.isEmpty()) return null;
		return studies.get(0);
		
	}
	
	
	///CONFIGURATION
	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	@Required
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}
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
	
	
	
}
