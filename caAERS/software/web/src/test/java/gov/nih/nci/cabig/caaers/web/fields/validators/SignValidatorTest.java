package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class SignValidatorTest extends TestCase {
	SignValidator dv;
	SignValidator dv2;
	protected void setUp() throws Exception {
		super.setUp();
		dv = new SignValidator(true);
		dv2 = new SignValidator(false);
	}

	public void testIsValid() {
		assertTrue(dv.isValid(123));
		assertTrue(dv.isValid(0));
		assertFalse(dv2.isValid(123));
		assertFalse(dv2.isValid(0));

		assertFalse(dv.isValid(-1));
		assertFalse(dv.isValid(-7899999.123));
		assertTrue(dv2.isValid(-1));
		assertTrue(dv2.isValid(-7899999.123));
	}

	public void testGetMessagePrefix() {
		assertEquals("Invalid sign", dv.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("POSITIVE", dv.getValidatorCSSClassName());
	}

}