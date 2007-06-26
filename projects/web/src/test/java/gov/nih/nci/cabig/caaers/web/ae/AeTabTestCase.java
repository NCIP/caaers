package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public abstract class AeTabTestCase extends AeWebTestCase {
    private AeTab tab;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tab = createTab();
    }

    protected abstract AeTab createTab();

    @Override
    protected CreateExpeditedAdverseEventCommand createCommand() {
        return createMinimallyValidMockCommand();
    }

    protected InputFieldGroup getFieldGroup(String fieldGroupName) {
        return getTab().createFieldGroups(command).get(fieldGroupName);
    }

    protected void assertFieldRequiredErrorRaised(String fieldName, String displayName) {
        assertEquals("Wrong number of errors for " + fieldName, 1, errors.getFieldErrorCount(fieldName));
        ObjectError fieldError = errors.getFieldError(fieldName);
        assertEquals("Wrong code for " + fieldName + " error", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong default message for " + fieldName + " error",
            "Missing " + displayName, fieldError.getDefaultMessage());
    }

    protected void assertDisplayNameForFieldGroup(String expectedDisplayName, String groupName) {
        String actual = getTab().createFieldGroups(command).get(groupName).getDisplayName();
        assertEquals("Wrong display name for " + groupName, expectedDisplayName, actual);
    }

    protected void assertFieldProperties(String fieldGroupName, String... expectedProperties) {
        InputFieldGroup actualGroup = getFieldGroup(fieldGroupName);
        assertNotNull("There's no group named " + fieldGroupName, actualGroup);
        List<InputField> actualFields = actualGroup.getFields();
        assertEquals("Wrong number of fields in " + fieldGroupName,
            expectedProperties.length, actualFields.size());
        for (int i = 0; i < expectedProperties.length; i++) {
            assertEquals("Wrong property " + i, expectedProperties[i], actualFields.get(i).getPropertyName());
        }
    }

    protected void doValidate() {
        replayMocks();
        getTab().validate(command, errors);
        verifyMocks();
    }

    public AeTab getTab() {
        return tab;
    }

}
