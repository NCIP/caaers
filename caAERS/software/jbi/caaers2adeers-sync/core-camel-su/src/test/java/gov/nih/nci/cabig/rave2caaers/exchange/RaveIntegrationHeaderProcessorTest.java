package gov.nih.nci.cabig.rave2caaers.exchange;


import gov.nih.nci.cabig.caaers2adeers.ExchangeAdapter;
import junit.framework.Assert;
import junit.framework.TestCase;

public class RaveIntegrationHeaderProcessorTest extends TestCase{
    public void testProcess() throws Exception {
        {
            String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:saer=\"http://schema.integration.caaers.cabig.nci.nih.gov/saerules\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body><saer:saveAndEvaluateAEs>abcd</saer:saveAndEvaluateAEs></soapenv:Body>" +
                    "</soapenv:Envelope>";
            ExchangeAdapter exchange = new ExchangeAdapter();
            exchange.getIn().setBody(body);
            new RaveIntegrationHeaderProcessor().process(exchange);
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.CORRELATION_ID));
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.SYNC_HEADER));
            Assert.assertNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.INVALID_MESSAGE));
            Assert.assertEquals("Course", exchange.getProperties().get(RaveIntegrationHeaderProcessor.ENTITY_NAME));
            Assert.assertEquals("saveAndEvaluateAEs", exchange.getProperties().get(RaveIntegrationHeaderProcessor.OPERATION_NAME));

        }
        {
            String body = "<Body><saveAndEvaluateAEs>abc</saveAndEvaluateAEs></Body>";
            ExchangeAdapter exchange = new ExchangeAdapter();
            exchange.getIn().setBody(body);
            new RaveIntegrationHeaderProcessor().process(exchange);
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.CORRELATION_ID));
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.SYNC_HEADER));
            Assert.assertNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.ENTITY_NAME));
            Assert.assertEquals("true", exchange.getProperties().get(RaveIntegrationHeaderProcessor.INVALID_MESSAGE));

        }
        {
            String body = "I am junk";
            ExchangeAdapter exchange = new ExchangeAdapter();
            exchange.getIn().setBody(body);
            new RaveIntegrationHeaderProcessor().process(exchange);
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.CORRELATION_ID));
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.SYNC_HEADER));
            Assert.assertNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.ENTITY_NAME));
            Assert.assertEquals("true", exchange.getProperties().get(RaveIntegrationHeaderProcessor.INVALID_MESSAGE));

        }

        {
            String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:aer=\"http://schema.integration.caaers.cabig.nci.nih.gov/aereportid\">" +
                    "<soapenv:Header/><soapenv:Body><aer:generateSafetyReportId>" +
                    "<aer:caseNumber>6644</aer:caseNumber>" +
                    "</aer:generateSafetyReportId></soapenv:Body>" +
                    "</soapenv:Envelope>";
            ExchangeAdapter exchange = new ExchangeAdapter();
            exchange.getIn().setBody(body);
            new RaveIntegrationHeaderProcessor().process(exchange);
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.CORRELATION_ID));
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.SYNC_HEADER));
            Assert.assertEquals("SafetyReport", exchange.getProperties().get(RaveIntegrationHeaderProcessor.ENTITY_NAME));
            Assert.assertEquals("generateSafetyReportId", exchange.getProperties().get(RaveIntegrationHeaderProcessor.OPERATION_NAME));

        }
        {
            String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:aer=\"http://schema.integration.caaers.cabig.nci.nih.gov/aereport\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <aer:initiateSafetyReportAction>\n" +
                    "        x\n" +
                    "      </aer:initiateSafetyReportAction>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            ExchangeAdapter exchange = new ExchangeAdapter();
            exchange.getIn().setBody(body);
            new RaveIntegrationHeaderProcessor().process(exchange);
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.CORRELATION_ID));
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.SYNC_HEADER));
            Assert.assertEquals("SafetyReport", exchange.getProperties().get(RaveIntegrationHeaderProcessor.ENTITY_NAME));
            Assert.assertEquals("initiateSafetyReportAction", exchange.getProperties().get(RaveIntegrationHeaderProcessor.OPERATION_NAME));

        }
        {
            ExchangeAdapter exchange = new ExchangeAdapter();
            exchange.getIn().setBody("<Body><x>abc</y></Body>");
            new RaveIntegrationHeaderProcessor().process(exchange);
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.CORRELATION_ID));
            Assert.assertNotNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.SYNC_HEADER));
            Assert.assertNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.ENTITY_NAME));
            Assert.assertNull(exchange.getProperties().get(RaveIntegrationHeaderProcessor.OPERATION_NAME));

        }
    }
}
