package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class MessageNotificationServiceIntegrationTest extends CaaersTestCase {
	MessageNotificationService service;
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testBeanLoading(){
		service = (MessageNotificationService)getDeployedApplicationContext().getBean("messageNotificationService");
		assertNotNull(service);
	}
}
