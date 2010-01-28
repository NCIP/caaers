package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.utils.ConfigPropertyHelper;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * @author Biju Joseph
 */
public abstract class AbstractTabTestCase<T extends Tab, C extends Object> extends WebTestCase {

    private static final Log log = LogFactory.getLog(AbstractTabTestCase.class);

    private T tab;
    private C command;

    protected abstract T createTab();

    protected ListValues listValues;

    protected ConfigProperty configProperty;

    private Map<String, Object> referenceData;

    protected T getTab() {
        if (tab == null) tab = createTab();
        return tab;
    }

    protected C getCommand() {
        if (command == null) command = createCommand();
        return command;
    }

    protected abstract C createCommand();

    protected Errors errors;

    public void testRefDataIncludesFieldGroups() throws Exception {

        assertTrue(getReferenceData().containsKey("fieldGroups"));
    }

    protected abstract Map<String, Object> createReferenceData();

    protected Map<String, Object> getReferenceData() {
        if (referenceData == null) referenceData = createReferenceData();
        return referenceData;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        errors = new BindException(getCommand(), "command");
        configProperty = ConfigPropertyHelper.getConfigProperty();


        listValues = new ListValues();

    }


    /**
     * Subclasses should override this to initialize all the components of the command they might
     * use. E.g., if the tab being tested generates fields for a collection, put an object in that
     * collection.
     * <p/>
     * Subclasses need not repeat things which are added as part of the minimally valid command.
     */
    protected void fillInUsedProperties(C cmd) {
    }


    public void testFieldPropertiesExist() {
        fillInUsedProperties(getCommand());
        assertAllFieldPropertiesExist();
    }


    protected InputFieldGroup getFieldGroup(String fieldGroupName) {
        return getFieldGroups().get(fieldGroupName);
    }

    protected void assertFieldRequiredErrorRaised(String fieldName, String displayName) {
        ObjectError fieldError = assertFieldRequiedErrorRaised(fieldName);
        assertEquals("Wrong default message for " + fieldName + " error", "Missing " + displayName,
                fieldError.getDefaultMessage());
    }

    protected void assertFieldRequiredCustomErrorMessageRaised(String fieldName, String message) {
        ObjectError fieldError = assertFieldRequiedErrorRaised(fieldName);
        assertEquals("Wrong default message for " + fieldName + " error", message, fieldError.getDefaultMessage());
    }

    private ObjectError assertFieldRequiedErrorRaised(final String fieldName) {
        assertEquals("Wrong number of errors for " + fieldName, 1, errors.getFieldErrorCount(fieldName));
        ObjectError fieldError = errors.getFieldError(fieldName);
        assertEquals("Wrong code for " + fieldName + " error", "REQUIRED", fieldError.getCode());
        return fieldError;
    }

    protected void assertDisplayNameForFieldGroup(String expectedDisplayName, String groupName) {
        InputFieldGroup inputFieldGroup = getFieldGroups().get(groupName);
        assertNotNull("no group exists for  group name:" + groupName, inputFieldGroup);
        String actual = inputFieldGroup.getDisplayName();
        assertEquals("Wrong display name for " + groupName, expectedDisplayName, actual);
    }

    private Map<String, InputFieldGroup> getFieldGroups() {
        Map<String, InputFieldGroup> groupMap = (Map<String, InputFieldGroup>) getReferenceData().get("fieldGroups");
        return groupMap;
    }

    protected void assertFieldProperties(String fieldGroupName, String... expectedProperties) {
        InputFieldGroup actualGroup = getFieldGroup(fieldGroupName);
        assertNotNull("There's no group named " + fieldGroupName, actualGroup);
        List<InputField> actualFields = actualGroup.getFields();
        assertEquals("Wrong number of fields in " + fieldGroupName, expectedProperties.length, actualFields.size());
        for (int i = 0; i < expectedProperties.length; i++) {
            assertEquals("Wrong property " + i, expectedProperties[i], actualFields.get(i).getPropertyName());
        }
    }

    protected void assertAllFieldPropertiesExist() {
        Map<String, InputFieldGroup> groups = getFieldGroups();

        BeanWrapper wrappedCommand = new BeanWrapperImpl(command);
        for (String name : groups.keySet()) {
            for (InputField field : groups.get(name).getFields()) {
                String msg = "The property "
                        + field.getPropertyName()
                        + " (group "
                        + name
                        + ") is not present in the command.  Either the command was not properly initialized (override fillInUsedProperties), or one of the tab's field groups is wrong.";
                try {
                    assertNotNull(msg, wrappedCommand.getPropertyType(field.getPropertyName()));
                } catch (InvalidPropertyException ipe) {
                    log.debug("Property not found exception", ipe);
                    fail(msg);
                }
            }
        }
    }

    protected void doValidate() {
        replayMocks();
        getTab().validate(getCommand(), errors);
        verifyMocks();
    }


    protected Map<Object, Object> getActualSelectFieldOptions(String fieldGroupName,
                                                              String propertyName) {
        return getActualSelectFieldOptions(getFieldGroup(fieldGroupName).getFields(), propertyName);
    }

    @SuppressWarnings({"unchecked"})
    protected Map<Object, Object> getActualSelectFieldOptions(List<InputField> fields,
                                                              String propertyName) {
        InputField field = findField(fields, propertyName);
        Map<Object, Object> options = InputFieldAttributes.getOptions(field);
        assertNotNull("Field for " + propertyName + " is not a select", options);
        return options;
    }

    protected InputField findField(List<InputField> fields, String propertyName) {
        InputField field = null;
        for (InputField candidate : fields) {
            if (candidate.getPropertyName().equals(propertyName)) {
                field = candidate;
                break;
            }
        }
        assertNotNull("No field for " + propertyName + ": " + fields, field);
        return field;
    }


}
