package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class SubmitterTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {
    // private EvaluationService evaluationService;

    public SubmitterTab() {
        super("Submitter info", "Submitter", "ae/submitter");
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        String reportIndex = ((SubmitExpeditedAdverseEventCommand) command).getReportIndex();
        if (reportIndex == null) {
            throw new CaaersSystemException("Report Index Not Defined");
        }
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup physicianSignoff = new DefaultInputFieldGroup("physicianSignoff");
        physicianSignoff.getFields().add(InputFieldFactory.createSelectField("aeReport.reports[" + reportIndex + "].lastVersion.physicianSignoff", "Physician sign-off", true, createExpectedOptions()));
        map.addInputFieldGroup(physicianSignoff);
        map.addInputFieldGroup(createPersonGroup("reporter", null));
        map.addInputFieldGroup(createPersonGroup("reports[" + reportIndex + "].lastVersion.submitter", "submitter"));
        return map;
    }

    private InputFieldGroup createPersonGroup(String person, String name) {
        String groupName = name == null ? person : name;
        InputFieldGroup group = new DefaultInputFieldGroup(groupName, StringUtils
                        .capitalize(person)
                        + " details");
        String base = "aeReport." + person + '.';
        group.getFields().add(InputFieldFactory.createTextField(base + "firstName", "First name", true));
        group.getFields().add(InputFieldFactory.createTextField(base + "middleName","Middle name", false));
        group.getFields().add(InputFieldFactory.createTextField(base + "lastName", "Last name", true));
        group.getFields().add(createContactField(base, ReportPerson.EMAIL, "E-mail address", true));
        InputField phoneField = createContactField(base, ReportPerson.PHONE, "Phone number", true);
        phoneField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        InputField faxField = createContactField(base, ReportPerson.FAX, "Fax number", true);
        faxField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        group.getFields().add(phoneField);
        group.getFields().add(faxField);
        
        return group;
    }

    private Map<Object, Object> createExpectedOptions() {
        Map<Object, Object> expectedOptions = new LinkedHashMap<Object, Object>();
        expectedOptions.put("", "Please select");
        expectedOptions.put(Boolean.TRUE, "Yes");
        expectedOptions.put(Boolean.FALSE, "No");
        return expectedOptions;
    }

    private InputField createContactField(String base, String contactType) {
        return createContactField(base, contactType, StringUtils.capitalize(contactType), false);
    }

    private InputField createContactField(String base, String contactType, String displayName, boolean required) {
        return InputFieldFactory.createTextField(base + "contactMechanisms[" + contactType + ']', displayName, required);
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        String reportIndex = ((SubmitExpeditedAdverseEventCommand) command).getReportIndex();
        Boolean hasPhysicianSignedOff = command.getAeReport().getReports().get(((int) Integer.parseInt(reportIndex))).getLastVersion().getPhysicianSignoff();
        hasPhysicianSignedOff = hasPhysicianSignedOff == null ? false : hasPhysicianSignedOff;

        if (!hasPhysicianSignedOff) {
            InputField field = fieldGroups.get("physicianSignoff").getFields().get(0);
            errors.rejectValue(field.getPropertyName(), "Physician sign-off required", "Not Valid " + field.getDisplayName());
        }
    }

}
