/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor;
import junit.framework.TestCase;
import org.apache.camel.Exchange;

/**
 * @author Biju Joseph
 */
public class ExchangePreProcessorTest extends TestCase {
    public void testProcess() throws Exception {
        ExchangePreProcessor processor = new ExchangePreProcessor();
        processor.setAdeersWSPassword("ap");
        processor.setAdeersWSUser("au");
        processor.setCaaersWSPassword("cp");
        processor.setCaaersWSUser("cu");

        Exchange e = new ExchangeAdapter();
        e.getIn().setBody("<hello>I am not an xml</hello>");
        assertNull(e.getProperty("c2a_correlation_id"));
        processor.process(e);
        assertNotNull(e.getProperty("c2a_correlation_id")); 
        assertEquals("cu", e.getProperty("c2a_caaers_ws_username"));
        assertEquals("cp", e.getProperty("c2a_caaers_ws_password"));
        assertEquals("au", e.getProperty("c2a_adeers_ws_username"));
        assertEquals("ap", e.getProperty("c2a_adeers_ws_password"));
        assertEquals("async", e.getProperty("c2a_sync_mode"));
    }

    public void testProcess_NoExceptionForInvalidXML() {
        ExchangePreProcessor processor = new ExchangePreProcessor();
        processor.setAdeersWSPassword("ap");
        processor.setAdeersWSUser("au");
        processor.setCaaersWSPassword("cp");
        processor.setCaaersWSUser("cu");

        Exchange e = new ExchangeAdapter();
        e.getIn().setBody("I am not an xml");
        assertNull(e.getProperty("c2a_correlation_id"));
        try {
            processor.process(e);
        } catch (Exception ex) {
            fail("exception must not be thrown");
        }
        assertNotNull(e.getProperty("c2a_correlation_id"));

    }
}
