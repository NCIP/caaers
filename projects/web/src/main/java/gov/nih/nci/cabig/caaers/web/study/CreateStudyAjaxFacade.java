package gov.nih.nci.cabig.caaers.web.study;


import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;


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
    private OrganizationDao organizationDao;
    
    public List<SiteInvestigator> matchSiteInvestigator(String text, int indexId) {
    	String[] arr = new String[] { text };
    	Study study = getStudyCommand();
    	int siteId = study.getStudySites().get(indexId).getOrganization().getId();
    	List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(arr, siteId);

        return ObjectTools.reduceAll(siteInvestigators,
            new ObjectTools.Initializer<SiteInvestigator>() {
                public void initialize(SiteInvestigator instance) {
                    instance.setInvestigator(new Investigator());
                }
            },
            "id", "investigator.firstName", "investigator.lastName");
    }
     
    public List<ResearchStaff> matchResearch(String text) {
    	List<ResearchStaff> researchStaff = researchStaffDao.getBySubnames(new String[] { text });
        return ObjectTools.reduceAll(researchStaff, "id", "firstName", "lastName");
    }
    
    private Study getStudyCommand() {
        // TODO: this won't work with editing
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

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public SiteInvestigatorDao getSiteInvestigatorDao() {
		return siteInvestigatorDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}
    
    
    
    
    
}
