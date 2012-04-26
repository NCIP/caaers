package gov.nih.nci.cabig.caaers2adeers.track;

import junit.framework.TestCase;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
/**
 * @author Biju Joseph
 */
public class TrackerTest extends TestCase {
    public void testTrack() throws Exception {
         assertNotNull(track(IntegrationLog.Stage.ADEERS_WS_IN_TRANSFORMATION, "test"));
    }
}
