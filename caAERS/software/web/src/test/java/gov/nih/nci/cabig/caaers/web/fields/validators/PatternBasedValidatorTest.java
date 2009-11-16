package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class PatternBasedValidatorTest extends TestCase {
	PatternBasedValidator validator;
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	public void testIsValid() {
		validator = new PatternBasedValidator("[0-9]{1,6}([.][0-9]{1,4})?");
		assertTrue(validator.isValid("10"));
		assertTrue(validator.isValid("100000"));
		assertTrue(validator.isValid("100000.1"));
		assertTrue(validator.isValid("100000.1000"));
		assertTrue(validator.isValid("100000.1010"));
		
		validator = new PatternBasedValidator("[0-9]{1,6}([.][0-9]{1,4})?");
		assertFalse(validator.isValid("."));
		assertFalse(validator.isValid("+6.0"));
		assertFalse(validator.isValid(".10"));
		assertFalse(validator.isValid("1.1055555"));
		assertFalse(validator.isValid("-1.1"));
		assertFalse(validator.isValid("-4444444444441.1"));
		
		validator = new PatternBasedValidator("[0-9]{0,9}([.][0-9]{1,6})?");
		assertTrue(validator.isValid("1"));
		assertTrue(validator.isValid(null));
		assertFalse(validator.isValid("1."));
		assertTrue(validator.isValid(""));
		assertTrue(validator.isValid("999999999.999999"));
		assertFalse(validator.isValid("9999999991.9"));
		
	
	}

	public void testGetMessagePrefix() {
		validator = new PatternBasedValidator("[0-9]{1,6}([.][0-9]{1,4})?");
		assertEquals("Invalid", validator.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		validator = new PatternBasedValidator("[0-9]{1,6}([.][0-9]{1,4})?");
		assertEquals("DECIMAL", validator.getValidatorCSSClassName());
	}
	
	

}
