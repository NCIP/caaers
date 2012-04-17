package gov.nih.nci.cabig.caaers2adeers;

import junit.framework.TestCase;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

/**
 * @author Biju Joseph
 */
public class HeaderPreProcessorTest extends TestCase {
    public void testProcess() throws Exception {
        HeaderPreProcessor processor = new HeaderPreProcessor();
        processor.setAdeersWSPassword("ap");
        processor.setAdeersWSUser("au");
        processor.setCaaersWSPassword("cp");
        processor.setCaaersWSUser("cu");

        Exchange e = new ExchangeAdapter();
        assertNull(e.getIn().getHeader("c2a_correlation_id"));
        processor.process(e);
        assertNotNull(e.getIn().getHeader("c2a_correlation_id")); 
        assertEquals("cu", e.getIn().getHeader("c2a_caaers_ws_username"));
        assertEquals("cp", e.getIn().getHeader("c2a_caaers_ws_password"));
        assertEquals("au", e.getIn().getHeader("c2a_adeers_ws_username"));
        assertEquals("ap", e.getIn().getHeader("c2a_adeers_ws_password"));
        assertEquals("async", e.getIn().getHeader("c2a_sync_mode"));
    }
}
