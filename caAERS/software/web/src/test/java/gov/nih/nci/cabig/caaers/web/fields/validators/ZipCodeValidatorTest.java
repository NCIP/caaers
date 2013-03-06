/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;

public class ZipCodeValidatorTest extends TestCase {
	ZipCodeValidator validator = new ZipCodeValidator();
	public void testIsValid() {
		assertFalse(validator.isValid("abcde"));
		assertFalse(validator.isValid("1234567"));
		assertFalse(validator.isValid("2017"));
		assertTrue(validator.isValid("20171"));
	}

	public void testGetMessagePrefix() {
		assertEquals("Missing", validator.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("ZIPCODE", validator.getValidatorCSSClassName());
	}

}
