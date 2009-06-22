package gov.nih.nci.cabig.caaers.web.fields.validators;

import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class PastDateValidatorTest extends TestCase {

	public void testIsValid() {
		Date now = new Date();
		PastDateValidator pastDateValidator = new PastDateValidator();
		boolean valid = pastDateValidator.isValid(now);
		assertTrue(valid);
		
		Date startDate1 = new GregorianCalendar(2009, 07, 14, 14, 00).getTime();
		boolean notValid = pastDateValidator.isValid(startDate1);
		assertFalse(notValid);
		
	}

}
