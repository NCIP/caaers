package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;
import junit.framework.TestCase;

/**
 * Validating whether the value is an alphanumeric one or not
 * @author Ion C. Olaru
 *
 */
public class AlphanumericValidatorTest extends TestCase {
    public void testIsValid() {
        AlphanumericValidator v = new AlphanumericValidator();
        assertTrue(v.isValid(""));
        assertTrue(v.isValid(" "));
        assertTrue(v.isValid("sample String 8"));
        assertTrue(v.isValid("sample String 8"));
        assertTrue(v.isValid("sample String 8 j 99 6"));
        assertFalse(v.isValid("sample String 8="));
        assertFalse(v.isValid(" ="));
        assertFalse(v.isValid("- "));
        assertTrue(v.isValid("_"));
        assertFalse(v.isValid("'"));
        assertFalse(v.isValid("\""));
    }
}