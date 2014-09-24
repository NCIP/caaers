package gov.nih.nci.cabig.rave2caaers.exchange;


import gov.nih.nci.cabig.caaers2adeers.ExchangeAdapter;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.camel.Exchange;

public class CredentialsProcessorTest  extends TestCase {

    public void testProcessValid() throws Exception {

        CredentialsProcessor processor = new CredentialsProcessor();

        Exchange e = new ExchangeAdapter();
        e.getIn().setBody(goodInput());
        processor.process(e);

        Assert.assertEquals("SYSTEM",e.getProperty(CredentialsProcessor.CAAERS_WS_USERNAME));
        Assert.assertEquals("system_admin",e.getProperty(CredentialsProcessor.CAAERS_WS_PASSWORD));
    }

    public void testProcessInValid() throws Exception {

        CredentialsProcessor processor = new CredentialsProcessor();

        Exchange e = new ExchangeAdapter();
        e.getIn().setBody(inputInValid());
        processor.process(e);

        Object userName= e.getProperty(CredentialsProcessor.CAAERS_WS_USERNAME);
        Assert.assertNull(userName);
        Assert.assertNull(e.getProperty(CredentialsProcessor.CAAERS_WS_PASSWORD));
    }


    String goodInput() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:saer=\"http://schema.integration.caaers.cabig.nci.nih.gov/saerules\">\n" +
                "        <soapenv:Header>\n" +
                "        <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soapenv:mustUnderstand=\"1\">\n" +
                "        <wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-19\">\n" +
                "        <wsse:Username>SYSTEM</wsse:Username>\n" +
                "        <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">system_admin</wsse:Password>\n" +
                "        <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">/w9qY2QnBmzEx9lXoIxXyA==</wsse:Nonce>\n" +
                "        <wsu:Created>2014-08-18T14:54:20.5348495+00:00</wsu:Created>\n" +
                "        </wsse:UsernameToken>\n" +
                "        </wsse:Security>\n" +
                "        </soapenv:Header>" +
                "</soapenv:Envelope>";
    }


    String inputInValid() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:saer=\"http://schema.integration.caaers.cabig.nci.nih.gov/saerules\">\n" +
                "        <soapenv:Header>\n" +
                "        <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soapenv:mustUnderstand=\"1\">\n" +
                "        <wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-19\">\n" +
                "        <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">system_admin</wsse:Password>\n" +
                "        <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">/w9qY2QnBmzEx9lXoIxXyA==</wsse:Nonce>\n" +
                "        <wsu:Created>2014-08-18T14:54:20.5348495+00:00</wsu:Created>\n" +
                "        </wsse:UsernameToken>\n" +
                "        </wsse:Security>\n" +
                "        </soapenv:Header>" +
                "</soapenv:Envelope>";
    }
}
