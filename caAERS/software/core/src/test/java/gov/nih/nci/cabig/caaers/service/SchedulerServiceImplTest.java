package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class SchedulerServiceImplTest extends CaaersTestCase {
	
	
	SchedulerServiceImpl scheduler;
	protected void setUp() throws Exception {
		super.setUp();
		scheduler = (SchedulerServiceImpl)applicationContext.getBean("schedulerService");
	}

	public void testGetScheduler() throws Exception{
		if(true){
			//no point of running this from Hudson, 
			//as loading of scheduler is tested otherwise itself. 
		assertNotNull(scheduler);
		assertNotNull(scheduler.getScheduler());
		System.out.println("sleeping...");
		Thread.sleep(1000 * 60 * 10);
		}
		assertTrue(true);
	}

}
