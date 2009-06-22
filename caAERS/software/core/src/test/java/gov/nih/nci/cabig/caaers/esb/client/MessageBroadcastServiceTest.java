package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class MessageBroadcastServiceTest extends CaaersTestCase {
    public void testExceptionWhenBroadcastWithoutDestination() throws Exception {
        /*
         * CaaersAdeersMessageBroadcastServiceImpl service =
         * (CaaersAdeersMessageBroadcastServiceImpl)this.getDeployedApplicationContext().getBean("messageBroadcaster");
         * 
         * try { service.initialize(); service.broadcast("<test>Wahoo</test>"); // fail("Exception
         * not thrown"); } catch (BroadcastException be) { assertEquals("JMS Connection Factory not
         * provided..", be.getMessage()); }
         */
    }
}
