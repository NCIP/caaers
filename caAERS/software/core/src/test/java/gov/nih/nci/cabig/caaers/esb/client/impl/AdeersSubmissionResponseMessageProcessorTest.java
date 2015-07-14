/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.esb.client.MessageNotificationService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import org.easymock.EasyMock;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdeersSubmissionResponseMessageProcessorTest extends AbstractTestCase {
	AdeersSubmissionResponseMessageProcessor processor;

    private MessageNotificationService messageNotificationService;
    protected ReportDao reportDao;
    protected Configuration configuration;


    protected void setUp() throws Exception {
		super.setUp();
        messageNotificationService = registerMockFor(MessageNotificationService.class);
        reportDao = registerDaoMockFor(ReportDao.class);
        configuration = registerMockFor(Configuration.class);
        processor = new AdeersSubmissionResponseMessageProcessor();
        processor.setMessageNotificationService(messageNotificationService);
        processor.setReportDao(reportDao);
        processor.setConfiguration(configuration);

	}

    public void testProcessMessage() throws Exception {

        EasyMock.expect(reportDao.getById(1468)).andReturn(Fixtures.createReport("abcd")).anyTimes();
        messageNotificationService.sendNotificationToReporter(
                (String)EasyMock.anyObject(),
                (String)EasyMock.anyObject(),
                (String)EasyMock.anyObject(),
                (String)EasyMock.anyObject(),
                EasyMock.anyBoolean(),
                (String)EasyMock.anyObject(),
                (String)EasyMock.anyObject(),
               EasyMock.anyBoolean()
                );
        EasyMock.expectLastCall().anyTimes();
        replayMocks();
        {
            String success = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                    "\t<soapenv:Body>\n" +
                    "\t\t<submitAEDataXMLAsAttachmentResponse xmlns=\"http://types.ws.adeers.ctep.nci.nih.gov\">\n" +
                    "\t\t\t<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\">\n" +
                    "\t\t\t\t<jobID xsi:type=\"xsd:string\">9410</jobID>\n" +
                    "\t\t\t\t<ticketNumber xsi:type=\"xsd:string\">1441442</ticketNumber>\n" +
                    "\t\t\t\t<patientID xsi:type=\"xsd:string\">noel-1</patientID>\n" +
                    "\t\t\t\t<protocolNumber xsi:type=\"xsd:string\">A071102</protocolNumber>\n" +
                    "\t\t\t\t<reportStatus>SUCCESS</reportStatus>\n" +
                    "\t\t\t\t<reportURL xsi:type=\"xsd:string\">https://betapps-ctep.nci.nih.gov/reports/rwservlet?ab+report=aereport.rep+DESTYPE=CACHE+desformat=pdf+P_AET_ID=1049383004</reportURL>\n" +
                    "\t\t\t\t<comments xsi:type=\"xsd:string\">**Pending **</comments>\n" +
                    "\t\t\t\t<CAEERS_AEREPORT_ID xmlns=\"\"/>\n" +
                    "\t\t\t\t<CAAERSRID xmlns=\"\">1468</CAAERSRID>\n" +
                    "\t\t\t\t<SUBMITTER_EMAIL xmlns=\"\">biju.joseph@semanticbits.com</SUBMITTER_EMAIL>\n" +
                    "\t\t\t\t<MESSAGE_COMBO_ID xmlns=\"\">1185760279::20140417194024</MESSAGE_COMBO_ID>\n" +
                    "\t\t\t\t<CORRELATION_ID xmlns=\"\">1418997625131</CORRELATION_ID>\n" +
                    "\t\t\t</ns1:AEReportJobInfo>\n" +
                    "\t\t</submitAEDataXMLAsAttachmentResponse>\n" +
                    "\t</soapenv:Body>\n" +
                    "</soapenv:Envelope>";

            processor.processMessage(success);
        }
        {
            StringBuffer failure = new StringBuffer();
            failure.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
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
                    .append( "\t\t\t\t<CAEERS_AEREPORT_ID xmlns=\"\"/>\n" +
                            "\t\t\t\t<CAAERSRID xmlns=\"\">1468</CAAERSRID>\n" +
                            "\t\t\t\t<SUBMITTER_EMAIL xmlns=\"\">biju.joseph@semanticbits.com</SUBMITTER_EMAIL>\n" +
                            "\t\t\t\t<MESSAGE_COMBO_ID xmlns=\"\">1185760279::20140417194024</MESSAGE_COMBO_ID>\n" +
                            "\t\t\t\t<CORRELATION_ID xmlns=\"\">1418997625131</CORRELATION_ID>\n"    )
                    .append("</ns1:AEReportJobInfo>")
                    .append("</submitAEDataXMLAsAttachmentResponse>")
                    .append("</soapenv:Body>")
                    .append("</soapenv:Envelope>");

            processor.processMessage(failure.toString());

        }

    }
	

}
