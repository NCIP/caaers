package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

/**
 * @author Krikor Krumlian
 */
public class ViewReportTab extends AeTab {
    //private RepeatingFieldGroupFactory fieldFactory;

    public ViewReportTab() {
        super("Submission", "Submit", "ae/submit");

    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        return groups;
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.SUBMIT_REPORT_SECTION;
    }
}
