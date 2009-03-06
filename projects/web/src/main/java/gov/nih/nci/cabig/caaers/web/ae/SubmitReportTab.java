package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Map;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

    public SubmitReportTab() {
        super("Submission", "Submit Report", "ae/submitReport");

    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        String reportIndex = ((SubmitExpeditedAdverseEventCommand) command).getReportIndex();
        if (reportIndex == null) {
            throw new CaaersSystemException("Report Index Not Defined");
        }
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup ccReport = new DefaultInputFieldGroup("ccReport");
        InputField cc = InputFieldFactory.createTextArea("aeReport.reports[" + reportIndex
                        + "].lastVersion.ccEmails", "Cc");
        InputFieldAttributes.setColumns(cc, 50);
        ccReport.getFields().add(cc);
        map.addInputFieldGroup(ccReport);
        return map;
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        String reportIndex = ((SubmitExpeditedAdverseEventCommand) command).getReportIndex();
        String emailString = command.getAeReport().getReports().get(
                        ((int) Integer.parseInt(reportIndex))).getLastVersion().getCcEmails();

        if (emailString != null) {
            String[] emails = emailString.split(",");

            for (String email : emails) {

                if (!isEmailValid(email)) {
                    InputField field = fieldGroups.get("ccReport").getFields().get(0);
                    errors.rejectValue(field.getPropertyName(), "NOT VALID", "Not Valid "
                                    + field.getDisplayName());
                    break;
                }
            }
        }
    }

    public boolean isEmailValid(String email) {
    	String trimmedEmail = (email != null) ? email.trim() : email;
        return EmailValidator.getInstance().isValid(trimmedEmail);
    }

}
