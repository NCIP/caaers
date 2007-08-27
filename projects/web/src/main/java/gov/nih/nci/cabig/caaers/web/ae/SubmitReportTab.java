package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportTab extends AeTab {

    public SubmitReportTab() {
        super("Submission", "Submit Report", "ae/submitReport");
       
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        return groups;
    }
    
    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        System.out.println(command.getAeReport().getReports());
    }
    
    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    }
}
