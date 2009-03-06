package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
/**
 * @author Biju Joseph
 * 
 */
public class DateUtilsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCompareDate() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2002, Calendar.FEBRUARY, 12);
		Date d1 = cal.getTime();
		Date d2 = cal.getTime();
		
		
		cal.add(Calendar.HOUR, 24);
		Date d3 = cal.getTime();
		
		assertEquals(-1, DateUtils.compareDate(d1, d3));
		assertEquals(1, DateUtils.compareDate(d3, d2));
		
	}

	public void testFormatDate() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2002, Calendar.FEBRUARY, 12);
		String formatedDate = DateUtils.formatDate(cal.getTime());
		assertEquals("02/12/2002", formatedDate);
	}

	public void testParseDateString() {
		DateValue dv = DateUtils.parseDateString("12/29/1976");
		assertEquals(new Integer(12), dv.getMonth());
		assertEquals(new Integer(29), dv.getDay());
		assertEquals(new Integer(1976), dv.getYear());
		
		dv = DateUtils.parseDateString("12/1976");
		assertEquals(new Integer(12), dv.getMonth());
		assertEquals(new Integer(1976), dv.getYear());
		
		
		dv = DateUtils.parseDateString("1976");
		assertEquals(new Integer(1976), dv.getYear());
		
		try{
			dv = DateUtils.parseDateString("20092");
			fail("must throw exception");
		}catch(Exception e){
			assertEquals("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'", e.getMessage());
		}
		
		try{
			dv = DateUtils.parseDateString("66/20090");
			fail("must throw exception");
		}catch(Exception e){
			assertEquals("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'", e.getMessage());
		}
		
		try{
			dv = DateUtils.parseDateString("669/2009");
			fail("must throw exception");
		}catch(Exception e){
			assertEquals("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'", e.getMessage());
		}
		
		try{
			dv = DateUtils.parseDateString("121/66/2009");
			fail("must throw exception");
		}catch(Exception e){
			assertEquals("Unknown format, expected format is 'mm/dd/yyyy' or 'mm/yyyy' or 'yyyy'", e.getMessage());
		}
	}
	
	

}
