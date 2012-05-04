package gov.nih.nci.cabig.caaers.domain;

import java.text.DecimalFormat;

import junit.framework.TestCase;

public class ObservedAdverseEventProfileTest extends TestCase {

	private ObservedAdverseEventProfile observedAdverseEventProfile;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		observedAdverseEventProfile = new ObservedAdverseEventProfile();
	}
	
	public void testCalculateStatistics() throws Exception{
		observedAdverseEventProfile.setExpectedFrequency(20.0);
		observedAdverseEventProfile.setObservedNoOfAE(25);
		observedAdverseEventProfile.setTotalNoOfRegistrations(100);
		observedAdverseEventProfile.calculateStatistics();
		assertEquals(25.0, observedAdverseEventProfile.getObservedFrequency());
		assertEquals(0.04, observedAdverseEventProfile.getStandardDeviation());
		assertEquals("1.25", format(observedAdverseEventProfile.getObservedSignificance()));
		assertEquals(".89", format(observedAdverseEventProfile.getpValue()));
	}
	
	public void testCalculateStatisticsForZeroNullExpectedFrequency() throws Exception{
		observedAdverseEventProfile.setExpectedFrequency(0.0);
		observedAdverseEventProfile.setObservedNoOfAE(25);
		observedAdverseEventProfile.setTotalNoOfRegistrations(100);
		observedAdverseEventProfile.calculateStatistics();
		assertEquals(25.0, observedAdverseEventProfile.getObservedFrequency());
		assertTrue(observedAdverseEventProfile.getStandardDeviation() < 0.000001);
		assertTrue(observedAdverseEventProfile.getObservedSignificance() > 100);
		assertEquals("1.00", format(observedAdverseEventProfile.getpValue()));
	}
	
	private String format(Double d){
		if (d == null) return null;
        DecimalFormat f = new DecimalFormat("##.00");  // this will help always keeps in two decimal places
        return f.format(d); 
	}
	
}
