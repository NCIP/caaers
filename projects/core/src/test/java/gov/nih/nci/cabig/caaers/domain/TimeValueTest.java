package gov.nih.nci.cabig.caaers.domain;


import junit.framework.TestCase;

public class TimeValueTest extends TestCase {

	public void testToString() {
		TimeValue tv = new TimeValue();
		assertEquals("00:00", tv.toString());
		
		tv.setHour(12);
		tv.setMinute(12);
		assertEquals("12:12", tv.toString());
	}

}
