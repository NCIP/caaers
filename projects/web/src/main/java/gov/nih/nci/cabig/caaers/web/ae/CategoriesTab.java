package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class CategoriesTab extends AeRoutTab {
    private CtcDao ctcDao;

    public CategoriesTab() {
        super("Select CTC Categories", "Categories", "ae/categories");
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, RoutineAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("ctcCats", getCategories(command));
        refdata.put("treatmentAssignments", command.getStudy().getTreatmentAssignments());
        return refdata;
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(RoutineAdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        // groups.addRepeatingFieldGroupFactory(fieldFactory,command.getCategories().size());
        return map;
    }

    private List<CtcCategory> getCategories(RoutineAdverseEventInputCommand command) {
        List<CtcCategory> categories = command.getStudy().getAeTerminology().getCtcVersion()
                        .getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }

    @Override
    protected void validate(RoutineAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        Date startDate = command.getAeRoutineReport().getStartDate();
        Date endDate = command.getAeRoutineReport().getEndDate();

        if (startDate == null) {
            errors.rejectValue("aeRoutineReport.startDate", "REQUIRED", "Missing From");
        }
        if (endDate == null) {
            errors.rejectValue("aeRoutineReport.endDate", "REQUIRED", "Missing To");
        }
        if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
            errors.rejectValue("aeRoutineReport.endDate", "REQUIRED",
                            "To cannot be earlier than From");
        }
    }

    @Required
    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

}
