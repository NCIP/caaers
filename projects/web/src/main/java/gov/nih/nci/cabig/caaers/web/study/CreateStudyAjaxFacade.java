package gov.nih.nci.cabig.caaers.web.study;


import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;


import java.util.List;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class CreateStudyAjaxFacade {
    private AgentDao agentDao;
    private DiseaseCategoryDao diseaseCategoryDao;
    private DiseaseTermDao diseaseTermDao;

   
    
    
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
    
    
    
    
    
}
