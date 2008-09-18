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
