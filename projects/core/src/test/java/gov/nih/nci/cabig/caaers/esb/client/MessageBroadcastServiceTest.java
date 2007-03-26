package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.esb.client.impl.MessageBroadcastServiceImpl;

/**
 * @author Rhett Sutphin
 */
public class MessageBroadcastServiceTest extends CaaersTestCase {
    public void testExceptionWhenBroadcastWithoutDestination() throws Exception {
        MessageBroadcastService service = new MessageBroadcastServiceImpl(null, null, null);
        try {
            service.broadcast("Wahoo!");
            fail("Exception not thrown");
        } catch (BroadcastException be) {
            assertEquals("no send queue provided", be.getMessage());
        }
    }
}
