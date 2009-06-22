package gov.nih.nci.cabig.caaers.domain.report;

import com.ibm.icu.util.Calendar;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class TimeScaleUnitTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetCalendarTypeCode() {
		assertEquals(Calendar.DATE, TimeScaleUnit.DAY.getCalendarTypeCode());
		assertEquals(Calendar.SECOND, TimeScaleUnit.SECOND.getCalendarTypeCode());
		assertEquals(Calendar.MINUTE, TimeScaleUnit.MINUTE.getCalendarTypeCode());
		assertEquals(Calendar.HOUR_OF_DAY, TimeScaleUnit.HOUR.getCalendarTypeCode());
		assertEquals(Calendar.WEEK_OF_MONTH, TimeScaleUnit.WEEK.getCalendarTypeCode());
		assertEquals(Calendar.MONTH, TimeScaleUnit.MONTH.getCalendarTypeCode());

		
	}

	public void testGetDisplayName() {
		assertEquals("Second", TimeScaleUnit.SECOND.getDisplayName());
		assertEquals("Minute", TimeScaleUnit.MINUTE.getDisplayName());
		assertEquals("Day", TimeScaleUnit.DAY.getDisplayName());
		assertEquals("Hour", TimeScaleUnit.HOUR.getDisplayName());
		assertEquals("Week", TimeScaleUnit.WEEK.getDisplayName());
		assertEquals("Month", TimeScaleUnit.MONTH.getDisplayName());
	}
	
	public void testGetMilliSecondConversionFactor() {
		assertEquals(1000, TimeScaleUnit.SECOND.getMilliSecondConversionFactor());
		assertEquals(1000 * 60 * 60 * 24 , TimeScaleUnit.DAY.getMilliSecondConversionFactor());
		assertEquals(1000 * 60 * 60 * 24 * 7 * 30 , TimeScaleUnit.MONTH.getMilliSecondConversionFactor());
	}

}
