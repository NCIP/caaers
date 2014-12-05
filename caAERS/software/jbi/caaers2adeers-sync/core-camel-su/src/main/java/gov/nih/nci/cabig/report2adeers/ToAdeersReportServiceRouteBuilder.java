package gov.nih.nci.cabig.report2adeers;

import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.xml.XPathBuilder;

import static gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor.*;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.*;

/**
 * will transform the caaers XML to AdEERS XML and publish the request to AdEERS Report Service
 */
public class ToAdeersReportServiceRouteBuilder {


    public void configure(Caaers2AdeersRouteBuilder rb){
/*

//      For testing only do not enable this
        String s1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AdverseEventReport><EXTERNAL_SYSTEMS>https://betapps-ctep.nci.nih.gov/adeerswsbeta/services/AEReportXMLService::iaminternal::FALL#2013</EXTERNAL_SYSTEMS><CAAERSRID>1726</CAAERSRID><SUBMITTER_EMAIL>bj@sb.com</SUBMITTER_EMAIL><MESSAGE_COMBO_ID>1992385162::20140316163537</MESSAGE_COMBO_ID><CORRELATION_ID>9080649</CORRELATION_ID><WITHDRAW>true</WITHDRAW><id>1091</id></AdverseEventReport>";
        String s2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><hello>welcome</hello>";

        rb.from("timer://tutorial?fixedRate=true&delay=5000&period=30000")
                .setBody(rb.constant(s1))
                .to("jbi:endpoint:urn:gov:nih:nci:caaers:jmsIn:provider");
*/


        rb.from("jbi:endpoint:urn:gov:nih:nci:caaers:jmsIn:consumer")
            .to("log:gov.nih.nci.cabig.report2adeers.caaers-ws-request?showHeaders=true&multiline=true&level=TRACE")
            .setProperty(OPERATION_NAME, rb.constant("sendReportToAdeers"))
            .setProperty(REPORT_WITHDRAW, XPathBuilder.xpath("/AdverseEventReport/WITHDRAW", String.class))
            .setProperty(AE_REPORT_ID, XPathBuilder.xpath("/AdverseEventReport/CAEERS_AEREPORT_ID", String.class))
            .setProperty(REPORT_ID, XPathBuilder.xpath("/AdverseEventReport/CAAERSRID", String.class))
            .setProperty(REPORT_EXTERNAL_ENDPOINT, XPathBuilder.xpath("/AdverseEventReport/EXTERNAL_SYSTEMS", String.class))
            .setProperty(REPORT_SUBMITTER_EMAIL, XPathBuilder.xpath("/AdverseEventReport/SUBMITTER_EMAIL", String.class))
            .setProperty(REPORT_MESSAGE_COMBO_ID, XPathBuilder.xpath("/AdverseEventReport/MESSAGE_COMBO_ID", String.class))
            .setProperty(CORRELATION_ID, XPathBuilder.xpath("/AdverseEventReport/CORRELATION_ID", String.class))
            .setProperty(ENTITY_NAME, rb.constant("SafetyReport"))
            .processRef("headerGeneratorProcessor")
            .choice()
                .when(rb.header(REPORT_WITHDRAW).isEqualTo("true"))
                    .to("direct:submit-report")
                .when(rb.header(REPORT_WITHDRAW).isEqualTo("false"))
                    .to("direct:submit-report")
                .otherwise()
                    .to("direct:morgue");


        rb.from("direct:submit-report")
          .process(track(REPORT_SUBMISSION_REQUEST_RECEIVED))
                .to(rb.getFileTracker().fileURI(REPORT_SUBMISSION_REQUEST_RECEIVED))
          .to("xslt:xslt/adeers/request/report-transformer.xsl")
                .to("log:gov.nih.nci.cabig.report2adeers.caaers-ws-request?showHeaders=true&multiline=true&level=TRACE")
          .process(track(ADEERS_REPORT_REQUEST_TRANSFORMATION))
                .to(rb.getFileTracker().fileURI(ADEERS_REPORT_REQUEST_TRANSFORMATION))
          .setExchangePattern(ExchangePattern.InOut)
          .processRef("adeersReportSubmissionProcessor")
                .to("log:gov.nih.nci.cabig.report2adeers.caaers-ws-request?showHeaders=true&multiline=true&level=TRACE")
          .process(track(ADEERS_REPORT_REQUEST_COMPLETED))
                .to(rb.getFileTracker().fileURI(ADEERS_REPORT_REQUEST_COMPLETED))
          .choice()
             .when(rb.header(REPORT_SUBMISSION_STATUS).isEqualTo("ERROR"))
                     .to("direct:communication-error")
             .when(rb.header(REPORT_WITHDRAW).isEqualTo("true"))
                     .to("direct:adeers-response")
             .when(rb.header(REPORT_WITHDRAW).isEqualTo("false"))
                     .to("direct:adeers-response");

        rb.from("direct:communication-error")
            .to("xslt:xslt/adeers/response/report-error-transformer.xsl")
            .process(track(ADEERS_REPORT_SUBMISSION_RESPONSE_TRASNSFORMATION))
                .to(rb.getFileTracker().fileURI(ADEERS_REPORT_SUBMISSION_RESPONSE_TRASNSFORMATION))
            .to("log:gov.nih.nci.cabig.report2adeers.caaers-ws-request?showHeaders=true&multiline=true&level=TRACE")
            .to("jbi:endpoint:urn:gov:nih:nci:caaers:jmsOut:provider", "direct:routeAdEERSResponseSink");

        rb.from("direct:adeers-response")
            .to("xslt:xslt/adeers/response/report-transformer.xsl")
            .process(track(ADEERS_REPORT_SUBMISSION_RESPONSE_TRASNSFORMATION))
                .to(rb.getFileTracker().fileURI(ADEERS_REPORT_SUBMISSION_RESPONSE_TRASNSFORMATION))
            .to("log:gov.nih.nci.cabig.report2adeers.caaers-ws-request?showHeaders=true&multiline=true&level=TRACE")
            .multicast()
                .to("jbi:endpoint:urn:gov:nih:nci:caaers:jmsOut:provider", "direct:routeAdEERSResponseSink");



    }

}
