package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.*;

/**
 * @author Rhett Sutphin
 * @author Biju  Joseph
 */
public class InputFieldFactoryTest extends AbstractTestCase {

    public void testCreateSplitDateFieldIfSplitDateIsRequired() {
        InputField field = InputFieldFactory.createSplitDateField("propertyName", "value", false, false, true, true);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&NUMERIC class", "validate-NOTEMPTY&&NUMERIC", field.getValidatorClassName());
        assertEquals("field must be SPLIT_DATE field", InputField.Category.SPLIT_DATE, field.getCategory());
    }

    public void testCreateSplitDateFieldIfSplitDateIsNotRequired() {
        InputField field = InputFieldFactory.createSplitDateField("propertyName", "value", false, false, true, false);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&NUMERIC class", "validate-NUMERIC", field.getValidatorClassName());
        assertEquals("field must be SPLIT_DATE field", InputField.Category.SPLIT_DATE, field.getCategory());
    }

    public void testCreatePhoneFieldIfPhoneIsRequired() {
        InputField field = InputFieldFactory.createPhoneField("propertyName", "value", true);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&US_PHONE_NO class", "validate-NOTEMPTY&&US_PHONE_NO&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());
    }

    public void testCreatePhoneFieldIfPhoneIsNotRequired() {
        InputField field = InputFieldFactory.createPhoneField("propertyName", "value", false);
        assertEquals("commons-validations.js need css validate-US_PHONE_NO class", "validate-US_PHONE_NO&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());

    }

    public void testCreateZipCodeFieldIfZipCodeIsRequired() {
        InputField field = InputFieldFactory.createZipCodeField("propertyName", "value", true);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&ZIPCODE class", "validate-NOTEMPTY&&ZIPCODE&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());
    }

    public void testCreateZipCodeFieldIfZipCodeIsNotRequired() {
        InputField field = InputFieldFactory.createZipCodeField("propertyName", "value", false);
        assertEquals("commons-validations.js need css validate-ZIPCODE class", "validate-ZIPCODE&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());

    }

    public void testCreateEmailFieldIfEmailIsRequired() {
        InputField field = InputFieldFactory.createEmailField("propertyName", "value", true);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&EMAIL class", "validate-NOTEMPTY&&EMAIL&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());
    }

    public void testCreateEmailFieldIfEmailIsNotRequired() {
        InputField field = InputFieldFactory.createEmailField("propertyName", "value", false);
        assertEquals("commons-validations.js need css validate-EMAIL class", "validate-EMAIL&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());

    }

    public void testCreateDateFieldIfDateIsRequired() {
        InputField field = InputFieldFactory.createPastDateField("propertyName", "value", true);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&DATE class", "validate-NOTEMPTY&&DATE", field.getValidatorClassName());
        assertEquals("field must be date field", InputField.Category.DATE, field.getCategory());

    }

    public void testCreateDateFieldIfDateIsNotRequired() {
        InputField field = InputFieldFactory.createPastDateField("propertyName", "value", false);
        assertEquals("commons-validations.js need css validate-DATE class", "validate-DATE", field.getValidatorClassName());

        assertEquals("field must be date field", InputField.Category.DATE, field.getCategory());

    }


    public void testCreateNumberFieldIfNumberIsRequired() {
        InputField field = InputFieldFactory.createNumberField("propertyName", "value", true);
        assertEquals("commons-validations.js need css validate-NOTEMPTY&&NUMERIC&&MAXLENGTH2000 class", "validate-NOTEMPTY&&NUMERIC&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());

    }

    public void testCreateNumberFieldIfNumberIsNotRequired() {
        InputField field = InputFieldFactory.createNumberField("propertyName", "value", false);
        assertEquals("commons-validations.js need css validate-NUMERIC&&MAXLENGTH2000 class", "validate-NUMERIC&&MAXLENGTH2000", field.getValidatorClassName());
        assertEquals("field must be text field", InputField.Category.TEXT, field.getCategory());

    }

    public void testCreateDateFieldIfDateFieldIsNotRequired() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade
                    .getName());
        }
    }

    public void testOptionsStoredAsAttribute() throws Exception {
        Map<Object, Object> expectedOptions = new HashMap<Object, Object>();
        InputField field = InputFieldFactory.createSelectField("pn", "P N", false, expectedOptions);
        assertSame(expectedOptions, InputFieldAttributes.getOptions(field));
    }

    public void testCollectedOptions() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade
                    .getName());
        }
    }

    public void testCollectedOptionsMaintainsOrder() throws Exception {
        List<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", "name");

        List<Object> actualKeys = new LinkedList<Object>(actual.keySet());
        assertEquals("Wrong number of options", items.size(), actualKeys.size());
        for (int i = 0; i < items.size(); i++) {
            assertEquals("Wrong key at " + i, items.get(i).getCode(), actualKeys.get(i));
        }
    }

    public void testCollectedOptionsNullValuePropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, null, "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.toString()), grade
                    .getName());
        }
    }

    public void testCollectedOptionsNullLabelPropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", null);

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade
                    .toString());
        }
    }
}
