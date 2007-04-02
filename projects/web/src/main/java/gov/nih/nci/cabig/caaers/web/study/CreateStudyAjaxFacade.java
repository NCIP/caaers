package gov.nih.nci.cabig.caaers.web.study;


import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;


import java.util.List;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.AbstractFormController;
import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.WebContextFactory;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class CreateStudyAjaxFacade {
    private AgentDao agentDao;
    private DiseaseCategoryDao diseaseCategoryDao;
    private DiseaseTermDao diseaseTermDao;
    private SiteInvestigatorDao siteInvestigatorDao;	
    private ResearchStaffDao researchStaffDao;
    private SiteDao siteDao;
    
    public List<SiteInvestigator> matchSiteInvestigator(String text, String indexId) {     	    	    	
    	String[] arr = new String[1];
    	arr[0] = text;
    	Study study = getStudyCommand();
    	int siteId = study.getStudySites().get(Integer.parseInt(indexId)).getSite().getId();
    	List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(arr, siteId);    	
    	return siteInvestigators;    	    	
    }
     
    public List<ResearchStaff> matchResearch(String text) {     	    	    	
    	String[] arr = new String[1];
    	arr[0] = text;
    	List<ResearchStaff> researchStaffs = researchStaffDao.getBySubnames(arr);
    	return researchStaffs;    	    	
    }
    
    private Study getStudyCommand() {
    	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
    	String commandName = CreateStudyController.class.getName()+".FORM.command";
    	Study study = (Study)request.getSession().getAttribute(commandName); 
    	request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, study);
    	return study;
    }

    public List<Agent> matchAgents(String text) {
        List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
        return agents;
    }
   
    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }
    
    public List<DiseaseCategory> matchDiseaseCategories(String text, Integer categoryId ) {
        List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getBySubname(extractSubnames(text), categoryId);
        return diseaseCategories;
    }
    
    public List<DiseaseCategory> matchDiseaseCategoriesByParentId(Integer parentCategoryId ) {
        List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getByParentId(parentCategoryId);
        return diseaseCategories;
    }
    
    public List<DiseaseTerm> matchDiseaseTermsByCategoryId(Integer categoryId ) {
        List<DiseaseTerm> diseaseTerms = diseaseTermDao.getByCategoryId(categoryId);
        return diseaseTerms;
    }


    ////// CONFIGURATION

    @Required
    public AgentDao getAgentDao() {
		return agentDao;
	}
    
    @Required
    public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

    @Required
	public DiseaseCategoryDao getDiseaseCategoryDao() {
		return diseaseCategoryDao;
	}

    @Required
    public void setDiseaseCategoryDao(DiseaseCategoryDao diseaseCategoryDao) {
		this.diseaseCategoryDao = diseaseCategoryDao;
	}

    @Required
	public DiseaseTermDao getDiseaseTermDao() {
		return diseaseTermDao;
	}
    @Required	
	public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
		this.diseaseTermDao = diseaseTermDao;
	}

	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public SiteDao getSiteDao() {
		return siteDao;
	}

	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}

	public SiteInvestigatorDao getSiteInvestigatorDao() {
		return siteInvestigatorDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}
    
    
    
    
    
}
