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
        rb.from("jbi:endpoint:urn:gov:nih:nci:caaers:jmsIn:consumer")
            .streamCaching()
            .to("log:gov.nih.nci.cabig.report2adeers.first-request?showHeaders=true&multiline=true&level=ERROR")
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
          .process(track(REPORT_SUBMISSION_REQUEST))
                .to(rb.getFileTracker().fileURI(REPORT_SUBMISSION_REQUEST))
          .to("xslt:xslt/adeers/request/report-transformer.xsl")
                .to("log:gov.nih.nci.cabig.report2adeers.presubmit-request?showHeaders=true&multiline=true&level=ERROR")
          .process(track(ADEERS_REPORT_REQUEST))
                .to(rb.getFileTracker().fileURI(ADEERS_REPORT_REQUEST))
          .setExchangePattern(ExchangePattern.InOut)
          .processRef("adeersReportSubmissionProcessor")
                .to("log:gov.nih.nci.cabig.report2adeers.adeers-response?showHeaders=true&multiline=true&level=ERROR")
          .process(track(ADEERS_REPORT_RESPONSE))
                .to(rb.getFileTracker().fileURI(ADEERS_REPORT_RESPONSE))
          .choice()
             .when(rb.header(REPORT_SUBMISSION_STATUS).isEqualTo("ERROR"))
                     .to("direct:communication-error")
             .when(rb.header(REPORT_WITHDRAW).isEqualTo("true"))
                     .to("direct:adeers-response")
             .when(rb.header(REPORT_WITHDRAW).isEqualTo("false"))
                     .to("direct:adeers-response");

        rb.from("direct:communication-error")
            .process(track(ADEERS_SUBMISSION_FAILED, "Communication error"))
            .to("xslt:xslt/adeers/response/report-error-transformer.xsl")
            .process(track(REPORT_SUBMISSION_RESPONSE))
                .to(rb.getFileTracker().fileURI(REPORT_SUBMISSION_RESPONSE))
            .to("log:gov.nih.nci.cabig.report2adeers.pre-multicast?showHeaders=true&multiline=true&level=WARN")
            .to("jbi:endpoint:urn:gov:nih:nci:caaers:jmsOut:provider", "direct:routeAdEERSResponseSink");

        rb.from("direct:adeers-response")
            .to("xslt:xslt/adeers/response/report-transformer.xsl")
            .process(track(REPORT_SUBMISSION_RESPONSE))
                .to(rb.getFileTracker().fileURI(REPORT_SUBMISSION_RESPONSE))
            .to("log:gov.nih.nci.cabig.report2adeers.response-sent?showHeaders=true&multiline=true&level=WARN")
            .multicast()
                .to("direct:toE2bAck", "direct:toJms");


        rb.from("direct:toJms")
                .to("log:gov.nih.nci.cabig.report2adeers.to-caaers-jms?showHeaders=true&multiline=true&level=WARN")
                .to("jbi:endpoint:urn:gov:nih:nci:caaers:jmsOut:provider");

        rb.from("direct:toE2bAck")
                .to("log:gov.nih.nci.cabig.report2adeers.to-e2b-ack?showHeaders=true&multiline=true&level=WARN")
                .to("direct:routeAdEERSResponseSink");
    }

}