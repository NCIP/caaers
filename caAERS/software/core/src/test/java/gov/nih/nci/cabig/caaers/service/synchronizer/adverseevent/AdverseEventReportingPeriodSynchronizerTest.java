package gov.nih.nci.cabig.caaers.service.synchronizer.adverseevent;

import static org.junit.Assert.*;

import org.junit.Test;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;

public class AdverseEventReportingPeriodSynchronizerTest {
	
	AdverseEventReportingPeriodSynchronizer synch = new AdverseEventReportingPeriodSynchronizer();
	
	@Test
	public void basicTest() {
		AdverseEventReportingPeriod src, dest;
		dest = new AdverseEventReportingPeriod();
		dest.setId(1);
		dest.setExternalId("Waka");
		dest.setDescription("Test2");
		dest.setEpoch(new Epoch());
		src = createTestAdverseEventReportingPeriod();
		synch.migrate(src, dest, null);
		assertTrue("The migrated object should not be the same.", src != dest);
		assertEquals("The migrator should not change ID's", 1, (int) dest.getId());
		assertEquals("The migrator should migrate external id.", "Test", dest.getExternalId());
		//Description is not migrated... is that deliberate or a bug?
		
		//Test that null values are not migrated.
		assertNull("Source value should be null for this test", src.getEpoch());
		assertNotNull("The epoch should not have been overwriten.", dest.getEpoch());
		
		
	}
	
	private AdverseEventReportingPeriod createTestAdverseEventReportingPeriod() {
		AdverseEventReportingPeriod retVal = new AdverseEventReportingPeriod();
		retVal.setCycleNumber(3);
		retVal.setId(3);
		retVal.setExternalId("Test");
		retVal.setDescription("Waka2");
		return retVal;
	}

}
