package gov.nih.nci.cabig.caaers.domain;


import junit.framework.TestCase;

public class TimeValueTest extends TestCase {

	public void testToString() {
		TimeValue tv = new TimeValue();
		tv.setHour(1);
		tv.setMinute(2);
		assertEquals("01:02 AM", tv.toString());
		
		tv.setHour(12);
		tv.setMinute(12);
		tv.setType(1);
		assertEquals("12:12 PM", tv.toString());
	}

}
