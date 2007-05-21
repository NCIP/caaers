package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Rhett Sutphin
 */
public class CategoriesTab<C extends CreateAdverseEventCommand> extends AeTab<C> {
    private RepeatingFieldGroupFactory fieldFactory;
    private InputFieldGroup reportFieldGroup;
    private CtcDao ctcDao;
    

    public CategoriesTab() {
        super("Select Ctc Categories", "Categories", "ae/categories");
        
        fieldFactory = new RepeatingFieldGroupFactory("ctcCategory", "ctcCategories");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                char c = (char) ('A' + index);
                return "Lab " + c;
            }
        });
        fieldFactory.addField(new DefaultTextField("name", "Category name", true));
       
    }
    
    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("ctcCats", getCategories(3));
        return refdata;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(CreateAdverseEventCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        groups.addRepeatingFieldGroupFactory(fieldFactory,command.getCategories().size());
        return groups;
    }
    
    private List<CtcCategory> getCategories(int ctcVersionId) {
        List<CtcCategory> categories = ctcDao.getById(ctcVersionId).getCategories();
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
