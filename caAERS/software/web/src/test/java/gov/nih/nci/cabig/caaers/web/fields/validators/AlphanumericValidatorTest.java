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

        v = new AlphanumericValidator(1, 1);
        assertTrue(v.isValid("a"));
        assertFalse(v.isValid("ab"));

        v = new AlphanumericValidator(4, 7);
        assertFalse(v.isValid("abc"));
        assertTrue(v.isValid("abde"));
        assertTrue(v.isValid("abdefgh"));
        assertFalse(v.isValid("abdefghi"));
        
        v = new AlphanumericValidator(3);
        assertTrue(v.isValid("abd"));
        assertFalse(v.isValid("abdefghi"));

        v = new AlphanumericValidator(3, 0);
        assertFalse(v.isValid("ad"));
        assertTrue(v.isValid("abde"));
        assertTrue(v.isValid("abde80934jhkd87j"));

    }
}