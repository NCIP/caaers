package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class DecimalValidatorTest extends TestCase {
	DecimalValidator dv;
	protected void setUp() throws Exception {
		super.setUp();
		dv = new DecimalValidator();
	}

	public void testIsValid() {
		assertTrue(dv.isValid("99.99"));
		assertTrue(dv.isValid("-99.99"));
		assertTrue(dv.isValid("99.99"));
		assertFalse(dv.isValid("-9x9.99"));
	}

	public void testGetMessagePrefix() {
		assertEquals("Incorrect", dv.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("DECIMAL", dv.getValidatorCSSClassName());
	}

}
