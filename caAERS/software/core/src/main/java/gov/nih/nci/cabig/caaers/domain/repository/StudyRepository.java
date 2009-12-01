package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.AbstractAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.RemoteStudy;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.resolver.CoppaConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class StudyRepository {
	
	private static Log log = LogFactory.getLog(StudyRepository.class);
	
    private StudyDao studyDao;
    private ResearchStaffDao researchStaffDao;
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigatorDao investigatorDao;
    private WorkflowConfigDao workflowConfigDao;
    
    //nci_institute_code for National Cancer Institute. 
    private static final String INSTITUTE_CODE = "NCI"; 
    
    /**
     * 
     * @param query
     * @return
     */
    @Transactional(readOnly = false)
    public List<Object[]> search(AbstractAjaxableDomainObjectQuery query,String type,String text){

    	try{
        	if(text.indexOf("%") == -1 && StringUtils.isNotEmpty(text)){
            	Study study = new RemoteStudy();
            	Organization nciOrg = organizationDao.getByNCIcode(INSTITUTE_CODE);
            	
            	if("st".equals(type) && !"".equals(text)){
            		study.setShortTitle(text);
            	}
            	
            	if("idtf".equals(type) && !"".equals(text)){
            		OrganizationAssignedIdentifier orgAssignedIdentifier = new OrganizationAssignedIdentifier();
                    orgAssignedIdentifier.setType(CoppaConstants.NCI_ASSIGNED_IDENTIFIER);
                    orgAssignedIdentifier.setValue(text.toUpperCase());
                    study.addIdentifier(orgAssignedIdentifier);
            	}
            	
                //Fetch studies from COPPA matching shortTitle or Identifier
            	List<Study> remoteStudies = studyDao.getExternalStudiesByExampleFromResolver(study);
            	
            	if(remoteStudies != null & remoteStudies.size() > 0 ){
            		for(Study remoteStudy : remoteStudies){
            			remoteStudy.getNciAssignedIdentifier().setOrganization(nciOrg);
            			verifyAndSaveOrganizations(remoteStudy);
            			verifyAndSaveInvestigators(remoteStudy);
            		}
            		//Save the studies returned from COPPA
            		studyDao.saveRemoteStudies(remoteStudies);
            	}
        	}
    	}catch(Exception e){
    		log.error("Error saving Remote Studies -- " + e.getMessage());
    	}

        //Perform normal search on caAERS DB & return results. 
    	List<Object[]> objectArray = (List<Object[]>) studyDao.search(query);
        return objectArray;
    }
    
    
    /**
     * This method checks if the Investigator already in caAERS. If exists it uses it else creates new investigator in caAERS
     * @param remoteStudy
     */
    @Transactional(readOnly = false)
    private void verifyAndSaveInvestigators(Study remoteStudy){
    	
		for(StudyOrganization studyOrg : remoteStudy.getStudyOrganizations()){
			for(StudyInvestigator studyInv : studyOrg.getStudyInvestigatorsInternal()){
				if(studyInv.getSiteInvestigator() != null && studyInv.getSiteInvestigator().getInvestigator() != null){
					Investigator dbInv = investigatorDao.getByExternalId(studyInv.getSiteInvestigator().getInvestigator().getExternalId());
					if(dbInv != null){
						SiteInvestigator dbSiteInvestigator = dbInv.findSiteInvestigator(studyInv.getSiteInvestigator());
						if(dbSiteInvestigator == null){
							dbInv.addSiteInvestigator(studyInv.getSiteInvestigator());
							investigatorDao.save(dbInv);
							dbSiteInvestigator = studyInv.getSiteInvestigator();
						}
						studyInv.setSiteInvestigator(dbSiteInvestigator);
					}else{
						investigatorDao.save(studyInv.getSiteInvestigator().getInvestigator());
					}
				}
			}
		}
    }

    /**
     * This method checks if the Organization in StudyOrganization is already in caAERS. If exists it uses it else creates new organization in caAERS
     * @param remoteStudy
     */
    @Transactional(readOnly = false)
    private void verifyAndSaveOrganizations(Study remoteStudy){
    	Date today = new Date();
    	long todayInMills = today.getTime();
    	
    	Map<String,Organization> orgMap = new HashMap<String,Organization>();
    	Organization dbOrg = null;
    	List<StudyOrganization> studyOrgList = remoteStudy.getStudyOrganizations();
    	
    	for(StudyOrganization studyOrg : studyOrgList){
    		if(studyOrg.getOrganization().getNciInstituteCode() == null || "".equals(studyOrg.getOrganization().getNciInstituteCode())){
    			todayInMills = todayInMills + 1;
    			studyOrg.getOrganization().setNciInstituteCode("**NULL**-" + todayInMills);
    			dbOrg = organizationDao.getByName(studyOrg.getOrganization().getName());
    		}else{
    			dbOrg = organizationDao.getByNCIcode(studyOrg.getOrganization().getNciInstituteCode());
    		}
    		if(dbOrg == null){
    			organizationRepository.createOrUpdate(studyOrg.getOrganization());
    		}else{
    			studyOrg.setOrganization(dbOrg);
    		}
    		if(studyOrg instanceof StudyFundingSponsor){
				orgMap.put(CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER, studyOrg.getOrganization());
			}
    		if(studyOrg instanceof StudyCoordinatingCenter){
				orgMap.put(CoppaConstants.COORDINATING_CENTER_IDENTIFER, studyOrg.getOrganization());
			}
    		for(StudyInvestigator studyInvestigator : studyOrg.getStudyInvestigatorsInternal()){
    			if(studyInvestigator.getSiteInvestigator() != null){
    				studyInvestigator.getSiteInvestigator().setOrganization(studyOrg.getOrganization());
    			}
    		}
    	}
    	//Associate db org's to Identifiers.
    	for(OrganizationAssignedIdentifier identifier : remoteStudy.getOrganizationAssignedIdentifiers()){
    		if(CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER.equals(identifier.getType())){
    			identifier.setOrganization(orgMap.get(CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER));
    		}
    		if(CoppaConstants.COORDINATING_CENTER_IDENTIFER.equals(identifier.getType())){
    			identifier.setOrganization(orgMap.get(CoppaConstants.COORDINATING_CENTER_IDENTIFER));
    		}
		}
    	
    	if(remoteStudy.getFundingSponsorIdentifier() != null && remoteStudy.getFundingSponsorIdentifier().getValue() != null){
    		if(remoteStudy.getFundingSponsorIdentifier().getValue().indexOf("**NULL**-") != -1){
    			remoteStudy.getFundingSponsorIdentifier().setValue(remoteStudy.getCoordinatingCenterIdentifier().getValue());
    		}
    	}
    }
    
    /**
     * Search using a sample populate Study object
     *
     * @param study the study object
     * @return List of Study objects based on the sample study object
     * @throws Exception runtime exception object
     */
    public List<Study> search(Study study) throws Exception {
        return studyDao.searchByExample(study, true);
    }

    @Transactional(readOnly = false)
    public void synchronizeStudyPersonnel(Study study) {
        //Identify newly added StudyOrganizations to associate ResearchStaff
        //whose associateAllStudies flag is set to true.
        ResearchStaffQuery query = null;
        List<ResearchStaff> researchStaffs = null;
        for(StudyOrganization studyOrganization : study.getStudyOrganizations()){
            if (studyOrganization.getOrganization() == null) continue;
            researchStaffs = new ArrayList<ResearchStaff>();
            query= new ResearchStaffQuery();
            query.filterByAssociateAllStudies(true);
            query.filterByOrganization(Integer.toString(studyOrganization.getOrganization().getId()));
            researchStaffs = researchStaffDao.getLocalResearchStaff(query);
            for(ResearchStaff researchStaff : researchStaffs){
            	study.syncStudyPersonnel(researchStaff);
            }
        }
    }
    
    /**
     * Will merge the study and return the merged study back. 
     * @param study
     * @return
     */
    @Transactional(readOnly=false)
    public Study merge(Study study){
    	associateSiteToWorkflowConfig(study.getStudySites());
    	return studyDao.merge(study);
    }
    
    /**
     * Saves a study object
     *
     * @param study the study object
     * @throws Exception runtime exception object
     */

    @Transactional(readOnly = false)
    public void save(Study study){
    	associateSiteToWorkflowConfig(study.getStudySites());
    	//Save the study
        studyDao.save(study);
    }

    @Required
    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public List<Study> find(final AbstractQuery query) {
        return studyDao.find(query);
    }
    
    //Associate the ResearchStaff to all the Studies 
    public void associateStudyPersonnel(ResearchStaff researchStaff) throws Exception{
    	StudyQuery query = null;
    	List<Study> studies = null;
    	for(SiteResearchStaff siteResearchStaff : researchStaff.getSiteResearchStaffs()){
    		if(BooleanUtils.isTrue(siteResearchStaff.getAssociateAllStudies())){
        		query = new StudyQuery();
        		query.joinStudyOrganization();
        		query.filterByOrganizationId(siteResearchStaff.getOrganization().getId());
        		studies = studyDao.find(query);
        		for(Study study : studies){
        			study.syncStudyPersonnel(researchStaff);
        			studyDao.save(study);	
        		}
    		}
    	}
    }
    
    /**
     * This method will associate StudySites to the {@link AdverseEventReportingPeriod} and {@link ExpeditedAdverseEventReport} workflow.
     * The default assigned to {@link AdverseEventReportingPeriod} is <b>reportingperiod_coordinating_center</b>
     * The default assigned to {@link ExpeditedAdverseEventReport} is <b>expedited_domestic</b> 
     * @param site
     */
    public void associateSiteToWorkflowConfig(List<StudySite> sites){
    	
    	if(CollectionUtils.isEmpty(sites)) return;
    	WorkflowConfig rpWorkflowConfig = null;
    	WorkflowConfig rWorkflowConfig = null;
    	
    	for(StudySite site : sites){
    		if(site.getStudySiteWorkflowConfigs().isEmpty()){
    			if(rpWorkflowConfig == null) rpWorkflowConfig = workflowConfigDao.getByWorkflowDefinitionName("reportingperiod_coordinating_center");
    			site.addStudySiteWorkflowConfig(new StudySiteWorkflowConfig("reportingPeriod", site, rpWorkflowConfig));
    			if(rWorkflowConfig == null) rWorkflowConfig =  workflowConfigDao.getByWorkflowDefinitionName("expedited_domestic");
    			site.addStudySiteWorkflowConfig(new StudySiteWorkflowConfig("report", site, rWorkflowConfig));
        	}
    	}
    	
    }

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
	
	public void setWorkflowConfigDao(WorkflowConfigDao workflowConfigDao) {
		this.workflowConfigDao = workflowConfigDao;
	}

	
}
