package gov.nih.nci.cabig.caaers.web.fields.validators;

import junit.framework.TestCase;

public class PhoneNumberValidatorTest extends TestCase {
	PhoneNumberValidator pv;
	protected void setUp() throws Exception {
		super.setUp();
		pv = new PhoneNumberValidator();
	}


	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testPhoneNumber(){
		assertTrue(pv.isValid("+1-803-319-5334"));
		assertTrue(pv.isValid("1-803-319-5334"));
		assertTrue(pv.isValid("803-319-5334"));
		assertTrue(pv.isValid("803-319-5334x123"));
		assertTrue(pv.isValid("+1.803.319.5334"));
		assertTrue(pv.isValid("1.803.319.5334"));
		assertTrue(pv.isValid("803.319.5334"));
		assertTrue(pv.isValid("1.803.319.5334x123"));
		assertTrue(pv.isValid("+18033195334"));
		assertTrue(pv.isValid("18033195334"));
		assertTrue(pv.isValid("8033195334"));
		assertTrue(pv.isValid("8033195334x234"));
		assertTrue(pv.isValid("+1(803)3195334"));
		assertTrue(pv.isValid("+1(803)319-5334"));
		assertTrue(pv.isValid("1(803)3195334"));
		assertTrue(pv.isValid("(803)3195334"));
		assertTrue(pv.isValid("(803)319-5334"));
		assertTrue(pv.isValid("(803)3195334x234"));
		assertTrue(pv.isValid("(803)319-5334x234"));
		assertTrue(pv.isValid(""));
		assertFalse(pv.isValid("((((803)319-5334"));
		assertFalse(pv.isValid("(803)319-5334xabcd"));
		assertFalse(pv.isValid("1-803)319-5334xabcd"));
		assertFalse(pv.isValid("1-803---319-5334----"));
		assertFalse(pv.isValid("1-803.319.5334)"));
		assertFalse(pv.isValid("+++++18033195334)"));
		assertTrue(pv.isValid("(212)-876-4460"));
	}
}
