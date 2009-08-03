package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

public class SchedulerServiceImplTest extends CaaersTestCase {
	
	
	SchedulerServiceImpl scheduler;
	protected void setUp() throws Exception {
		super.setUp();
		scheduler = (SchedulerServiceImpl)applicationContext.getBean("schedulerService");
	}

	public void testGetScheduler() {
		assertNotNull(scheduler);
		assertNotNull(scheduler.getScheduler());
	}

}
