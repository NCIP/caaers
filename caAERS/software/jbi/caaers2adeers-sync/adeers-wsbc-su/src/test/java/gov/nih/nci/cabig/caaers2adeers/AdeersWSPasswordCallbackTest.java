/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import junit.framework.TestCase;
import org.apache.ws.security.WSPasswordCallback;
import javax.security.auth.callback.Callback;

/**
 * @author Biju Joseph
 */
public class AdeersWSPasswordCallbackTest extends TestCase {

    AdeersWSPasswordCallback callback = new AdeersWSPasswordCallback();
    WSPasswordCallback pc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        callback.setAdeersWSPassword("x");
        callback.setAdeersWSUser("y");
        pc = new WSPasswordCallback("y", 1);
        pc.setIdentifier("y");
        Callback[] callbacks = new Callback[]{pc};
        callback.handle(callbacks);
        assertEquals("x", pc.getPassword());

        pc.setPassword(null);
        pc.setIdentifier("k");
        callback.handle(callbacks);
        assertNull(pc.getPassword());
    }

    public void testHandle() throws Exception {
        assertNotNull(callback);
    }

}
