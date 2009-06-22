package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.domain.DelayUnits.DAYS;
import static gov.nih.nci.cabig.caaers.domain.DelayUnits.HOURS;
import static gov.nih.nci.cabig.caaers.domain.DelayUnits.MINUTES;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */

public class DelayUnitsTest extends CaaersTestCase {
    public void testMinutesToMinutes() throws Exception {
        assertEquals(new BigDecimal(34), MINUTES.toMinutes(new BigDecimal(34)));
    }

    public void testHoursToMinutes() throws Exception {
        assertEquals(new BigDecimal(180), HOURS.toMinutes(new BigDecimal(3)));
    }

    public void testDaysToMinutes() throws Exception {
        assertEquals(new BigDecimal(10080), DAYS.toMinutes(new BigDecimal(7)));
    }

    public void testMinutesFromMinutes() throws Exception {
        assertEquals(new BigDecimal(12), MINUTES.fromMinutes(new BigDecimal(12)));
    }

    public void testHoursFromMinutes() throws Exception {
        assertEquals(new BigDecimal(7), HOURS.fromMinutes(new BigDecimal(420)));
    }

    public void testDaysFromMinutes() throws Exception {
        assertEquals(new BigDecimal(2), DAYS.fromMinutes(new BigDecimal(2880)));
    }

    public void testMinutesIsExactMinutes() throws Exception {
        assertTrue(MINUTES.isExact(new BigDecimal(9)));
        assertFalse(MINUTES.isExact(new BigDecimal("9.1")));
    }

    public void testHoursIsExact() throws Exception {
        assertTrue(HOURS.isExact(new BigDecimal(60)));
        assertTrue(HOURS.isExact(new BigDecimal(240)));
        assertTrue(HOURS.isExact(new BigDecimal(0)));
        assertFalse(HOURS.isExact(new BigDecimal(220)));
        assertFalse(HOURS.isExact(new BigDecimal("59.7")));
    }

    public void testDaysIsExact() throws Exception {
        assertTrue(HOURS.isExact(new BigDecimal(1440)));
        assertTrue(HOURS.isExact(new BigDecimal(2880)));
        assertTrue(HOURS.isExact(new BigDecimal(0)));
        assertFalse(HOURS.isExact(new BigDecimal(1)));
        assertFalse(HOURS.isExact(new BigDecimal(1441)));
        assertFalse(HOURS.isExact(new BigDecimal("1440.2")));
    }

    public void testDisplayName() throws Exception {
        assertEquals("Hours", HOURS.getDisplayName());
        assertEquals("Minutes", MINUTES.getDisplayName());
        assertEquals("Days", DAYS.getDisplayName());
    }
}
