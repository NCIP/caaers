package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import org.apache.commons.lang.StringUtils;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
public class SubmitterTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

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
        InputField phoneField = createContactField(base, ReportPerson.PHONE, "Phone", true);
        phoneField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        InputField faxField = createContactField(base, ReportPerson.FAX, "Fax", true);
        faxField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        group.getFields().add(phoneField);
        group.getFields().add(faxField);
        
        return group;
    }


    private InputField createContactField(String base, String contactType, String displayName, boolean required) {
        return InputFieldFactory.createTextField(base + "contactMechanisms[" + contactType + ']', displayName, required);
    }


}
