package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
/**
 * @author Biju Joseph
 * @author Ion C. Olaru 
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
	
	public void testCompareDateAndTime(){
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2002, Calendar.FEBRUARY, 12);
		Date d1 = cal.getTime();
		Date d2 = cal.getTime();
		
		assertEquals(0, DateUtils.compateDateAndTime(d1, d2));
		cal.add(Calendar.HOUR, 24);
		Date d3 = cal.getTime();
		assertEquals(1, DateUtils.compateDateAndTime(d3, d2));
		assertEquals(-1, DateUtils.compateDateAndTime(d2, d3));
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
	
	
	public void testBetween(){
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2002, Calendar.FEBRUARY, 11);
		Date d0 = cal.getTime();
		
		cal.set(2002, Calendar.FEBRUARY, 12);
		Date d1 = cal.getTime();
		
		cal.add(Calendar.DATE, 1);
		Date d2 = cal.getTime();
		
		
		cal.add(Calendar.DATE, 2);
		Date d3 = cal.getTime();
		
		cal.add(Calendar.DATE, 2);
		Date d4 = cal.getTime();
		
		assertTrue(DateUtils.between(d2, d1, d3));
		assertTrue(DateUtils.between(d2, d2, d3));
		assertFalse(DateUtils.between(d3, d2, d3));
		assertTrue(DateUtils.between(d2, d1, null));
		assertTrue(DateUtils.between(d2, d2, null));
		
		assertFalse(DateUtils.between(d0, d2, d3));
		assertFalse(DateUtils.between(d4, d2, d3));
	}
	
    public void testValidationByString() {
        assertFalse(DateUtils.isValidDate("13/12/2009"));
        assertTrue(DateUtils.isValidDate("12/12/2009"));
    }

    public void testValidationByDateValue() {
        assertTrue(DateUtils.isValidDate(new DateValue(11, 11, 2009)));
        assertFalse(DateUtils.isValidDate(new DateValue(12, 13, 2009)));
    }

    public void testNullLeftSide() {
        assertEquals(-1, DateUtils.compareDate(null, DateUtils.today()));
    }

    public void testNullRightSide() {
        assertEquals(1, DateUtils.compareDate(DateUtils.today(), null));
    }


    public void testParseDate() throws Exception {
        assertEquals("09/09/2009", DateUtils.formatDate(DateUtils.parseDate("09/09/2009")));
    }


    public void testParseDateBoundaryConditions() throws Exception {
        try {
            DateUtils.parseDate("09/09/2009", null);
        } catch (IllegalArgumentException e) {
           
        }
    }

    public void testParseDateIncorrectDateFormat() throws Exception {
        try {
            DateUtils.parseDate("09/090/2009", "MM/dd/yyyy");
        } catch (ParseException e) {

        }
    }

    public void testParseDateIncorrectDatePattern() throws Exception {
        try {
            DateUtils.parseDate("09/09/2009", "MM-dd-yyyy");
        } catch (ParseException e) {

        }
    }
}
