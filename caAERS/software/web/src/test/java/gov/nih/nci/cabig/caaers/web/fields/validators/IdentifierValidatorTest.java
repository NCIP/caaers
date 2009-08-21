package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class IdentifierValidatorTest extends TestCase {
	IdentifierValidator v;
	protected void setUp() throws Exception {
		super.setUp();
		v = new IdentifierValidator();
	}

	public void testIsValid() {
		assertTrue(v.isValid("abc-90"));
		assertTrue(v.isValid("abc_#90"));
		assertTrue(v.isValid("abc[12]"));
		assertTrue(v.isValid("abc(1)"));
		assertTrue(v.isValid("abc[2]"));
		assertTrue(v.isValid("abc."));
		assertTrue(v.isValid("abc{1}*''"));

		assertFalse(v.isValid("abc%"));
		assertFalse(v.isValid("abc$"));
	}

}