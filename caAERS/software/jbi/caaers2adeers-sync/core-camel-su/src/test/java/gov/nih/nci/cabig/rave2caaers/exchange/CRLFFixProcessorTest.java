package gov.nih.nci.cabig.rave2caaers.exchange;


import gov.nih.nci.cabig.caaers2adeers.ExchangeAdapter;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CRLFFixProcessorTest extends TestCase {

    public void testProcess() throws Exception {
        ExchangeAdapter exchange = new ExchangeAdapter();
        exchange.getIn().setBody(" Hello How are you");
        new CRLFFixProcessor().process(exchange);
        Assert.assertEquals("Hello How are you", exchange.getOut().getBody());

        exchange.getIn().setBody(" Hello\r\n How are you\r\n");
        new CRLFFixProcessor().process(exchange);
        Assert.assertEquals("Hello\n How are you", exchange.getOut().getBody());


    }
}
