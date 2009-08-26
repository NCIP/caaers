package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author Biju Joseph
 *
 */
public class PositiveValidatorTest extends TestCase {
	PositiveValidator dv;
	PositiveValidator dv2;
	protected void setUp() throws Exception {
		super.setUp();
		dv = new PositiveValidator(true);
		dv2 = new PositiveValidator(false);
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
		assertEquals("Incorrect sign", dv.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("POSITIVE", dv.getValidatorCSSClassName());
	}

}