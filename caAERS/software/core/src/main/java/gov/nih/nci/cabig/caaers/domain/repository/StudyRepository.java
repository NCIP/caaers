package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class StudyRepository {

    private StudyDao studyDao;
    private ResearchStaffDao researchStaffDao;

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

    /**
     * Saves a study object
     *
     * @param study the study object
     * @throws Exception runtime exception object
     */

    @Transactional(readOnly = false)
    public void save(Study study){
    	
    	//Identify newly added StudyOrganizations to associate ResearchStaff 
    	//whose associateAllStudies flag is set to true.
        ResearchStaffQuery query = null;
        List<ResearchStaff> researchStaffs = null;
        StudyPersonnel studyPersonnel = null;
    	for(StudyOrganization studyOrganization : study.getStudyOrganizations()){
    		if(studyOrganization.getId() == null){
            	researchStaffs = new ArrayList<ResearchStaff>();
            	query= new ResearchStaffQuery();
            	query.filterByAssociateAllStudies(true);
            	query.filterByOrganization(Integer.toString(studyOrganization.getOrganization().getId()));
            	researchStaffs = researchStaffDao.getLocalResearchStaff(query);
            	for(ResearchStaff researchStaff : researchStaffs){
            		for(SiteResearchStaff siteResearchStaff : researchStaff.getSiteResearchStaffs()){
            			if(studyOrganization.getOrganization().equals(siteResearchStaff.getOrganization())){
        					for(SiteResearchStaffRole siteResearchStaffRole : siteResearchStaff.getSiteResearchStaffRoles()){
        						studyPersonnel = new StudyPersonnel();
            					studyPersonnel.setSiteResearchStaff(siteResearchStaff);
            					studyPersonnel.setStudyOrganization(studyOrganization);
            					studyPersonnel.setRoleCode(siteResearchStaffRole.getRoleCode());
            					studyPersonnel.setStartDate(siteResearchStaffRole.getStartDate());
            					studyPersonnel.setEndDate(siteResearchStaffRole.getEndDate());
            					studyOrganization.addStudyPersonnel(studyPersonnel);
        	    			}
            			}
            		}
            	}
    		}
    	}
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
    	StudyPersonnel studyPersonnel = null;
    	for(SiteResearchStaff siteResearchStaff : researchStaff.getSiteResearchStaffs()){
    		if(siteResearchStaff.getAssociateAllStudies() == null){
    			siteResearchStaff.setAssociateAllStudies(Boolean.FALSE);
    		}
    			query = new StudyQuery();
    			query.joinStudyOrganization();
    			query.filterByStudyOrganizationNameExactMatch(siteResearchStaff.getOrganization().getName());
    			studies = studyDao.find(query);
    			for(Study study : studies){
    				for(StudyOrganization studyOrganization : study.getStudyOrganizations()){
    					if(studyOrganization.getOrganization().equals(siteResearchStaff.getOrganization())){
        					for(SiteResearchStaffRole siteResearchStaffRole : siteResearchStaff.getSiteResearchStaffRoles()){
        						studyPersonnel = new StudyPersonnel();
            					studyPersonnel.setSiteResearchStaff(siteResearchStaff);
            					studyPersonnel.setStudyOrganization(studyOrganization);
            					studyPersonnel.setRoleCode(siteResearchStaffRole.getRoleCode());
            					studyPersonnel.setStartDate(siteResearchStaffRole.getStartDate());
            					studyPersonnel.setEndDate(siteResearchStaffRole.getEndDate());
            					boolean studyPersonnelExists = false;
            					for(StudyPersonnel existingStudyPersonnel : studyOrganization.getStudyPersonnels()){
            						if(existingStudyPersonnel.equals(studyPersonnel)){
            							studyPersonnelExists = true;
            							existingStudyPersonnel.setEndDate(siteResearchStaffRole.getEndDate());
            							break;
            						}
            					}
            					if(!studyPersonnelExists & siteResearchStaff.getAssociateAllStudies()){
            						studyOrganization.addStudyPersonnel(studyPersonnel);
            					}
        	    			}
    					}
    				}
    				studyDao.save(study);	
    			}
    	}
    }

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}
    
}
