package gov.nih.nci.cabig.caaers.web.fields.validators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;


public class FutureDateValidatorTest extends TestCase {

	public void testIsValid() {
		Calendar gcNow = GregorianCalendar.getInstance();
		gcNow.add(Calendar.MINUTE,-5);
		Date now = gcNow.getTime();
		boolean valid;
		FutureDateValidator futureDateValidator = new FutureDateValidator();
		valid = futureDateValidator.isValid(now);
		assertFalse(valid);
		
		Date futureDate = new GregorianCalendar(2009, 07, 14, 14, 00).getTime();
		valid = futureDateValidator.isValid(futureDate);
		assertTrue(valid);
		
		Date pastDate = new GregorianCalendar(2009, 01, 14, 14, 00).getTime();
		valid = futureDateValidator.isValid(pastDate);
		assertFalse(valid);
		
		
	}

}
