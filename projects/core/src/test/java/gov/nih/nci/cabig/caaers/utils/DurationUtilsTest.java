package gov.nih.nci.cabig.caaers.utils;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class DurationUtilsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testFormatDuration_DayTesting() {
		long aDay = 1000 * 60 * 60 * 24;
		String str = DurationUtils.formatDuration(aDay, "d");
		assertEquals("Due in 1 day ", str);
		
		long _2Day = aDay * 2;
		str = DurationUtils.formatDuration(_2Day, "d");
		assertEquals("Due in 2 days ", str);
		
		long _2DaysBack = aDay * -2;
		str = DurationUtils.formatDuration(_2DaysBack, "d");
		assertEquals("2 days overdue", str);
		
		
	}
	
	public void testFormatDuration_HourTesting() {
		long aDay = 1000 * 60 * 60 * 24;
		String str = DurationUtils.formatDuration(aDay, "H");
		assertEquals("Due in 24 hours ", str);
		
		long _2Day = aDay * 2;
		str = DurationUtils.formatDuration(_2Day, "H");
		assertEquals("Due in 48 hours ", str);
		
		long _2DaysBack = aDay * -2;
		str = DurationUtils.formatDuration(_2DaysBack, "H");
		assertEquals("48 hours overdue", str);
		
		
	}

	public void testFormatDuration_DayAndHourTesting() {
		long aDay = 1000 * 60 * 60 * 24;
		aDay += (1000 * 60 * 60); // add an hour
		String str = DurationUtils.formatDuration(aDay, "dH");
		assertEquals("Due in 1 day ",str);
		
		long _2Day = aDay * 2;
		str = DurationUtils.formatDuration(_2Day, "dH");
		assertEquals("Due in 2 days ", str);
		
		long _2DaysBack = aDay * -2;
		str = DurationUtils.formatDuration(_2DaysBack, "dH");
		assertEquals("2 days overdue",str);
		
		
	}
}
