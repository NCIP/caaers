package gov.nih.nci.cabig.caaers.domain;

import edu.nwu.bioinformatics.commons.DateUtils;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class DateValueTest extends TestCase {


    public void testDateValueIntegerIntegerInteger() {
        DateValue dv = new DateValue(19, 9, 2002);
        assertEquals(new Integer(2002), dv.getYear());
        assertEquals(new Integer(9), dv.getMonth());
        assertEquals(new Integer(19), dv.getDay());
        assertEquals("09/19/2002", dv.toString());
    }

    public void testDateValueDate() {
        Date d = DateUtils.createDate(2002, 8, 19);
        DateValue dv = new DateValue(d);
        assertEquals("09/19/2002", dv.toString());
    }

    public void testDateValueIsNull() {
        Date d = DateUtils.createDate(2002, 8, 19);
        DateValue dv = new DateValue();

        assertTrue(dv.isNull());

        dv.setDay(2002);
        assertFalse(dv.isNull());
    }

    public void testCompareTo() {
        DateValue dv = new DateValue(19, 12, 2002);
        Date d = DateUtils.createDate(2002, 11, 19);
        DateValue dv2 = new DateValue(d);
        assertEquals(0, dv.compareTo(dv2));

        DateValue dv3 = new DateValue(18, 9, 2001);
        assertEquals(1, dv2.compareTo(dv3));
        assertEquals(-1, dv3.compareTo(dv2));

        assertEquals(1, new DateValue(2002).compareTo(new DateValue(2001)));
        assertEquals(-1, new DateValue(2000).compareTo(new DateValue(2001)));
        assertEquals(-1, new DateValue(2002, 01).compareTo(new DateValue(2002, 02)));
        assertEquals(-1, new DateValue(2002, 01).compareTo(new DateValue(2002, 01, 02)));

        assertEquals(0, new DateValue(2002, 02, 02).compareTo(new DateValue(2002, 02, 02)));
        assertEquals(0, new DateValue(2002, 02).compareTo(new DateValue(2002, 02)));
        assertEquals(0, new DateValue().compareTo(new DateValue()));
        assertEquals(0, new DateValue().compareTo(new DateValue()));
    }

    public void testCompareToNull() {
        DateValue dv = new DateValue(19, 12, 2002);
        DateValue dvNull = new DateValue();
        assertEquals(-1, dvNull.compareTo(dv));
        assertEquals(1, dv.compareTo(dvNull));
    }

    public void testToString() {
        DateValue dv = new DateValue(19, 9, 2002);
        assertEquals("09/19/2002", dv.toString());
    }
    
    public void testToDate(){
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.YEAR, 2008);
    	c.set(Calendar.MONTH, Calendar.FEBRUARY);
    	c.set(Calendar.DATE, 1);
    	
    	DateValue dv = new DateValue(1, 2, 2008);
    	
    	assertEquals(gov.nih.nci.cabig.caaers.utils.DateUtils.formatDate(dv.toDate()), "02/01/2008");
    }


}
