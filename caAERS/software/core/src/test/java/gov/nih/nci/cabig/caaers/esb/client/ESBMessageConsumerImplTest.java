package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.esb.client.impl.AdeersSubmissionResponseMessageProcessor;

import java.util.Locale;

import org.easymock.classextension.EasyMock;
import org.jdom.Element;
import org.springframework.context.MessageSource;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ESBMessageConsumerImplTest extends AbstractTestCase {

	ResponseMessageProcessor consumer;
	MessageNotificationService messageNotificationService;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		consumer = new AdeersSubmissionResponseMessageProcessor();
		messageNotificationService = registerMockFor(MessageNotificationService.class);
		
		
		consumer.setMessageNotificationService(messageNotificationService);
		
		
	}
	
	//correct xml, no exceptions
	public void testGetJobInfo() {
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
				"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
		.append("<soapenv:Body>")
	    .append("<submitAEDataXMLAsAttachmentResponse>")
		.append("<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\">")
		.append("<jobID xsi:type=\"xsd:string\">4632</jobID>")
		.append("<patientID xsi:type=\"xsd:string\">SH-888</patientID>")
		.append("<protocolNumber xsi:type=\"xsd:string\">5876</protocolNumber>")
		.append("<jobExceptions>")
		.append("<code>TYPE_OF_CAUSE: FLD_VAL_ERR</code>")
		.append("<description>Invalid Value.</description>")
		.append("</jobExceptions>")
		.append("<jobExceptions>")
		.append("<code>TYPE_OF_CAUSE: FLD_VAL_ERR</code>")
		.append("<description>Invalid Value.</description>")
		.append("</jobExceptions>")
		.append("<reportStatus>ERROR</reportStatus>")
		.append("</ns1:AEReportJobInfo>")
		.append("</submitAEDataXMLAsAttachmentResponse>")
		.append("</soapenv:Body>")
		.append("</soapenv:Envelope>");
		
		Element jobInfo = consumer.getResponseElement(xmlBuffer.toString(),"submitAEDataXMLAsAttachmentResponse","AEReportJobInfo");
		assertNotNull(jobInfo);
		assertEquals("AEReportJobInfo", jobInfo.getName());
	}
	

	//invalid xml, exceptions
	public void testGetJobInfo_ThorwingException() {
		StringBuffer xmlBuffer = new StringBuffer().append("<Test>")
		.append("<Body>")
		.append("<Invalid>invalid</INVALID>")
		.append("</Body>")
		.append("</Test>");
		try {
		Element jobInfo = consumer.getResponseElement(xmlBuffer.toString(),"submitAEDataXMLAsAttachmentResponse","AEReportJobInfo");
		} catch (Exception e){
			assertEquals(e.getMessage(), "org.jdom.input.JDOMParseException: Error on line 1: The element type \"Invalid\" must be terminated by the matching end-tag \"</Invalid>\".");
		}
		
	}


	public void testProcessMessage_InvalidResponseXML() throws Exception {
		StringBuffer xmlBuffer = new StringBuffer().append("<Test>")
		.append("<Body>")
		.append("<Invalid>invalid</INVALID>")
		.append("</Body>")
		.append("</Test>");
		
		//messageNotificationService.sendNotificationToReporter("", "", "", "", false, "", "", true);
		//replayMocks();
		try {
			consumer.processMessage(xmlBuffer.toString());
		} catch (Exception e){
			assertEquals(e.getMessage(), "org.jdom.input.JDOMParseException: Error on line 1: The element type \"Invalid\" must be terminated by the matching end-tag \"</Invalid>\".");
		}
		//verifyMocks();
		
	}
	
	
	public void testProcessMessage_CommunicationError() throws Exception {
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
				"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
		.append("<soapenv:Body>")
	    .append("<submitAEDataXMLAsAttachmentResponse>")
		.append("<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\">")
		.append("<jobID xsi:type=\"xsd:string\">4632</jobID>")
		.append("<CAEERS_AEREPORT_ID xsi:type=\"xsd:string\">4632</CAEERS_AEREPORT_ID>")
		.append("<REPORT_ID xsi:type=\"xsd:string\">4632</REPORT_ID>")
		.append("<SUBMITTER_EMAIL xsi:type=\"xsd:string\">submitter@usa.com</SUBMITTER_EMAIL>")
		.append("<patientID xsi:type=\"xsd:string\">SH-888</patientID>")
		.append("<protocolNumber xsi:type=\"xsd:string\">5876</protocolNumber>")
		.append("<jobExceptions>")
		.append("<code>caAERS-adEERS : COMM_ERR</code>")
		.append("<description>Invalid Value.</description>")
		.append("</jobExceptions>")
		.append("<jobExceptions>")
		.append("<code>TYPE_OF_CAUSE: FLD_VAL_ERR</code>")
		.append("<description>Invalid Value.</description>")
		.append("</jobExceptions>")
		.append("<reportStatus>ERROR</reportStatus>")
		.append("</ns1:AEReportJobInfo>")
		.append("</submitAEDataXMLAsAttachmentResponse>")
		.append("</soapenv:Body>")
		.append("</soapenv:Envelope>");
		
		
		//EasyMock.expect(messageSource.getMessage(EasyMock.eq("failed.reportSubmission.message"), (Object[])EasyMock.anyObject(), EasyMock.eq(Locale.getDefault())) ).andReturn("hello how are you");
		
		//messageNotificationService.sendNotificationToReporter("submitter@usa.com", "hello how are you", "4632", "4632", false, "", "", true);
		//replayMocks();
		
		consumer.processMessage(xmlBuffer.toString());
		
		//verifyMocks();
	}
	
	public void testProcessMessage_AdEERSError() throws Exception {
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
				"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
		.append("<soapenv:Body>")
	    .append("<submitAEDataXMLAsAttachmentResponse>")
		.append("<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\">")
		.append("<jobID xsi:type=\"xsd:string\">4632</jobID>")
		.append("<CAEERS_AEREPORT_ID xsi:type=\"xsd:string\">4632</CAEERS_AEREPORT_ID>")
		.append("<REPORT_ID xsi:type=\"xsd:string\">4632</REPORT_ID>")
		.append("<SUBMITTER_EMAIL xsi:type=\"xsd:string\">submitter@usa.com</SUBMITTER_EMAIL>")
		.append("<patientID xsi:type=\"xsd:string\">SH-888</patientID>")
		.append("<protocolNumber xsi:type=\"xsd:string\">5876</protocolNumber>")
		.append("<jobExceptions>")
		.append("<code>TYPE_OF_CAUSE: FLD_VAL_ERR</code>")
		.append("<description>Invalid Value.</description>")
		.append("</jobExceptions>")
		.append("<jobExceptions>")
		.append("<code>TYPE_OF_CAUSE: FLD_VAL_ERR</code>")
		.append("<description>Invalid Value.</description>")
		.append("</jobExceptions>")
		.append("<reportStatus>ERROR</reportStatus>")
		.append("</ns1:AEReportJobInfo>")
		.append("</submitAEDataXMLAsAttachmentResponse>")
		.append("</soapenv:Body>")
		.append("</soapenv:Envelope>");
		
		
	//	EasyMock.expect(messageSource.getMessage(EasyMock.eq("failed.reportSubmission.message"), (Object[])EasyMock.anyObject(), EasyMock.eq(Locale.getDefault())) ).andReturn("hello how are you");
		
		//messageNotificationService.sendNotificationToReporter("submitter@usa.com", "hello how are you", "4632", "4632", false, "", "", false);
		//replayMocks();
		
		consumer.processMessage(xmlBuffer.toString());
		
		//verifyMocks();
		
	}
	
	public void testProcessMessage() throws Exception {
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
				"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
		.append("<soapenv:Body>")
	    .append("<submitAEDataXMLAsAttachmentResponse>")
		.append("<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\">")
		.append("<jobID xsi:type=\"xsd:string\">4632</jobID>")
		.append("<CAEERS_AEREPORT_ID xsi:type=\"xsd:string\">4632</CAEERS_AEREPORT_ID>")
		.append("<REPORT_ID xsi:type=\"xsd:string\">4632</REPORT_ID>")
		.append("<SUBMITTER_EMAIL xsi:type=\"xsd:string\">submitter@usa.com</SUBMITTER_EMAIL>")
		.append("<ticketNumber xsi:type=\"xsd:string\">1234</ticketNumber>")
		.append("<reportURL xsi:type=\"xsd:string\">http://www.abc.com</reportURL>")
		.append("<patientID xsi:type=\"xsd:string\">SH-888</patientID>")
		.append("<protocolNumber xsi:type=\"xsd:string\">5876</protocolNumber>")
		.append("<reportStatus>SUCCESS</reportStatus>")
		.append("<comments>hi</comments>")
		.append("</ns1:AEReportJobInfo>")
		.append("</submitAEDataXMLAsAttachmentResponse>")
		.append("</soapenv:Body>")
		.append("</soapenv:Envelope>");

		
	//	EasyMock.expect(messageSource.getMessage(EasyMock.eq("successful.reportSubmission.message"), (Object[])EasyMock.anyObject(), EasyMock.eq(Locale.getDefault())) ).andReturn("hello how are you");

//		EasyMock.expect(messageSource.getMessage(EasyMock.eq("comments.reportSubmission.message"), (Object[])EasyMock.anyObject(), EasyMock.eq(Locale.getDefault())) ).andReturn("hi");
		
		//messageNotificationService.sendNotificationToReporter("submitter@usa.com", "hello how are youhi", "4632", "4632", true, "1234", "http://www.abc.com", false);
		//replayMocks();
		
		consumer.processMessage(xmlBuffer.toString());
		
		//verifyMocks();
	}

}
