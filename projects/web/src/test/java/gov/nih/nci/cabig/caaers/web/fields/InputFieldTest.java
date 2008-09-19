package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * This class actually tests the features of {@link AbstractInputField}; maven doesn't seem to run
 * testcases that start with "Abstract".
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class InputFieldTest extends AbstractTestCase {
    private AbstractInputField field;

    private TestBean bean;

    private BeanWrapper wrappedBean;

    private Errors errors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bean = new TestBean();
        wrappedBean = new BeanWrapperImpl(bean);
        errors = new BindException(bean, "command");

        field = new AbstractInputField() {
            @Override
            public Category getCategory() {
                return Category.TEXT;
            }
        };
        field.setPropertyName("name");
        field.setDisplayName("Nomen");
    }

    public void testNoErrorsWhenRequiredFieldIsPresent() throws Exception {
        bean.setName("Jo-jo");
        field.setRequired(true);
        field.validate(wrappedBean, errors);
        assertFalse(errors.hasErrors());
    }

    public void testNoErrorsWhenNotRequiredFieldIsPresent() throws Exception {
        bean.setName("Jo-jo");
        field.setRequired(false);
        field.validate(wrappedBean, errors);
        assertFalse(errors.hasErrors());
    }

    public void testErrorWhenRequiredFieldIsNotPresent() throws Exception {
        bean.setName(null);
        field.setRequired(true);
        field.validate(wrappedBean, errors);
        assertEquals("Wrong number of errors", 1, errors.getErrorCount());
        assertEquals("Wrong number of field errors", 1, errors.getFieldErrorCount());
        FieldError actualError = errors.getFieldError("name");
        assertNotNull("Error is on wrong property", actualError);
        assertEquals("Wrong key for error", "REQUIRED", actualError.getCode());
        assertEquals("Wrong default message for error", "Missing Nomen", actualError
                .getDefaultMessage());
    }

    public void testNoErrorsWhenNotRequiredFieldIsNotPresent() throws Exception {
        bean.setName(null);
        field.setRequired(false);
        field.validate(wrappedBean, errors);
        assertFalse(errors.hasErrors());
    }

    public void testGetValidatorClassNameIfOnlySingleValidatorIsUsed() throws Exception {
        field = new AbstractInputField("propertyName", "displayName", FieldValidator.PAST_DATE_VALIDATOR) {
            public Category getCategory() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        assertEquals("commons-validations.js uses this class name for validation", "validate-DATE", field.getValidatorClassName());
    }


    public void testGetValidatorClassNameForRequiredTextField() throws Exception {
        field = new AbstractInputField("propertyName", "displayName", FieldValidator.NOT_NULL_VALIDATOR) {
            public Category getCategory() {
                return Category.TEXT;
            }
        };

        assertEquals("commons-validations.js uses this class name for validation", "validate-NOTEMPTY&&MAXLENGTH2000", field.getValidatorClassName());
    }

    public void testGetValidatorClassNameForRequiredTextAreaField() throws Exception {
        field = new AbstractInputField("propertyName", "displayName", FieldValidator.NOT_NULL_VALIDATOR) {
            public Category getCategory() {
                return Category.TEXTAREA;
            }
        };

        assertEquals("commons-validations.js uses this class name for validation", "validate-NOTEMPTY&&MAXLENGTH2000", field.getValidatorClassName());
    }

    public void testGetValidatorClassNameForNonRequiredTextField() throws Exception {
        field = new AbstractInputField("propertyName", "displayName") {
            public Category getCategory() {
                return Category.TEXT;
            }
        };

        assertEquals("commons-validations.js uses this class name for validation", "validate-MAXLENGTH2000", field.getValidatorClassName());
    }

    public void testGetValidatorClassNameForNonRequiredTextAreaField() throws Exception {
        field = new AbstractInputField("propertyName", "displayName") {
            public Category getCategory() {
                return Category.TEXTAREA;
            }
        };

        assertEquals("commons-validations.js uses this class name for validation", "validate-MAXLENGTH2000", field.getValidatorClassName());
    }

    public void testGetValidatorClassNameIfNoValidatorsAreApplied() throws Exception {
        field = new AbstractInputField("propertyName", "displayName") {
            public Category getCategory() {
                return null;
            }
        };

        assertTrue("class name muast be empty", StringUtils.isEmpty(field.getValidatorClassName()));
    }

    public void testGetValidatorClassNameIfMultipleValidatorIsUsed() throws Exception {
        field = new AbstractInputField("propertyName", "displayName", FieldValidator.PAST_DATE_VALIDATOR, FieldValidator.NOT_NULL_VALIDATOR) {
            public Category getCategory() {
                return null;
            }
        };

        assertEquals("commons-validations.js uses this class name for validation", "validate-DATE&&NOTEMPTY", field.getValidatorClassName());
    }

    private static class TestBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
