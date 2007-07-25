package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Rhett Sutphin
 */
public class CategoriesTab extends AeRoutTab {
    private CtcDao ctcDao;

    public CategoriesTab() {
        super("Select Ctc Categories", "Categories", "ae/categories");
    }
    
    @Override
    public Map<String, Object> referenceData(RoutineAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("ctcCats" , getCategories(command));
        return refdata;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(RoutineAdverseEventInputCommand command) {
    	InputFieldGroupMap map = new InputFieldGroupMap();
        //groups.addRepeatingFieldGroupFactory(fieldFactory,command.getCategories().size());
        return map;
    }
    
    private List<CtcCategory> getCategories(RoutineAdverseEventInputCommand command) {
        List<CtcCategory> categories = command.getStudy().getCtcVersion().getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }

    @Required
	public CtcDao getCtcDao() {
		return ctcDao;
	}

	public void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}
    
    
}
