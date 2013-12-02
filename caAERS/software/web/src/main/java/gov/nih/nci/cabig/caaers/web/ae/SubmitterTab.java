/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.TEXT;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.validation.fields.validators.TextSizeValidator;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
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
public class SubmitterTab extends TabWithFields<SubmitExpeditedAdverseEventCommand> {

    public SubmitterTab() {
        super("Submitter info", "Submitter", "ae/submitter");
    }

    @Override
    @SuppressWarnings("unchecked")
    public InputFieldGroupMap createFieldGroups(SubmitExpeditedAdverseEventCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(createPersonGroup("report.lastVersion.submitter", "submitter"));
        return map;
    }

    private InputFieldGroup createPersonGroup(String person, String name) {
        String groupName = name == null ? person : name;
        InputFieldGroup group = new DefaultInputFieldGroup(groupName, StringUtils.capitalize(person)+ " details");
        String base = person + '.';
        group.getFields().add(InputFieldFactory.createTextField(base + "firstName", "First name", true));
        group.getFields().add(InputFieldFactory.createTextField(base + "middleName","Middle name", false));
        group.getFields().add(InputFieldFactory.createTextField(base + "lastName", "Last name", true));

        group.getFields().add(createContactField(base, ReportPerson.EMAIL, "E-mail address", FieldValidator.EMAIL_VALIDATOR, FieldValidator.NOT_NULL_VALIDATOR));
        InputField phoneField = createCustomPhoneTextField(base, ReportPerson.PHONE, "Phone", true);
        InputFieldAttributes.setSize(phoneField, 21);
        InputField faxField = createCustomPhoneTextField(base, ReportPerson.FAX, "Fax", true);
        InputFieldAttributes.setSize(faxField, 21);

        faxField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        
        group.getFields().add(phoneField);
        group.getFields().add(faxField);
        
        return group;
    }
    
    private InputField createCustomPhoneTextField(String base, String contactType, String displayName, boolean required) {
        String propertyName = base + "contactMechanisms[" + contactType + "]";
        FieldValidator validators[] = null;
       if (required) {
           validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, new TextSizeValidator(20)};
       } else {
       	 validators = new FieldValidator[]{new TextSizeValidator(20)};
       }
       return InputFieldFactory.createInputField(TEXT, propertyName, contactType, displayName, validators);
   }
    

    private InputField createContactField(String base, String contactType, String displayName, FieldValidator ... validator) {
        return InputFieldFactory.createTextField(base + "contactMechanisms[" + contactType + ']', displayName, validator);
    }
    


}
