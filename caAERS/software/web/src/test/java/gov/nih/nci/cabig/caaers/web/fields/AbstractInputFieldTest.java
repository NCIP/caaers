package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import junit.framework.TestCase;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ion C. Olaru
 * 
 */
public class AbstractInputFieldTest extends TestCase {

    public void testRequiredTogetherWithOtherValidators() {
        AbstractInputField field = new AbstractInputField("propertyName","displayName",  "labelProperty", true, FieldValidator.EMAIL_VALIDATOR, FieldValidator.DATE_VALIDATOR) {
            @Override
            public Category getCategory() {
                return Category.TEXT;
            }
        };

        assertEquals(3, field.getValidators().length);
        assertEquals(FieldValidator.EMAIL_VALIDATOR, field.getValidators()[0]);
        assertEquals(FieldValidator.DATE_VALIDATOR, field.getValidators()[1]);
        assertEquals(FieldValidator.NOT_NULL_VALIDATOR, field.getValidators()[2]);
    }

}