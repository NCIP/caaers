/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.track;

import junit.framework.TestCase;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
/**
 * @author Biju Joseph
 */
public class TrackerTest extends TestCase {
    public void testTrack() throws Exception {
         assertNotNull(track(ADEERS_WS_IN_TRANSFORMATION, "test"));
    }
}
