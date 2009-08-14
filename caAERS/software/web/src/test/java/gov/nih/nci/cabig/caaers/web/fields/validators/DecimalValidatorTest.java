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
public class DecimalValidatorTest extends TestCase {
	DecimalValidator dv;
	protected void setUp() throws Exception {
		super.setUp();
		dv = new DecimalValidator(6, 2);
	}

	public void testIsValid() {
		assertTrue(dv.isValid("-453422.93"));
		assertTrue(dv.isValid("-99.99"));
		assertTrue(dv.isValid("99.99"));
		assertFalse(dv.isValid("-9x9.99"));
		assertFalse(dv.isValid("99.992"));

        dv = new DecimalValidator(14, 6);
		assertFalse(dv.isValid("901234567890123456.012345"));
		assertTrue(dv.isValid("999999123.012345"));
	}

	public void testGetMessagePrefix() {
		assertEquals("Incorrect", dv.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("DECIMAL", dv.getValidatorCSSClassName());
	}

}
