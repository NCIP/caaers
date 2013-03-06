/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import gov.nih.nci.cabig.caaers.validation.fields.validators.AlphanumericValidator;

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
        assertTrue(v.isValid("-"));
        assertTrue(v.isValid(" "));
        assertTrue(v.isValid("- "));
        assertTrue(v.isValid("-' "));
        assertTrue(v.isValid("_"));
        assertTrue(v.isValid("'"));
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


        AlphanumericValidator v1 = new AlphanumericValidator(2,8);
         assertTrue(v1.isValid("aaa-'bc"));
         assertTrue(v1.isValid("nn."));



    }
}
