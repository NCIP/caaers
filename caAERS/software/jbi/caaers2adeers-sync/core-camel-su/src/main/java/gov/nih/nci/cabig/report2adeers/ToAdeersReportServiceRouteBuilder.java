package gov.nih.nci.cabig.report2adeers;

import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;
import org.apache.camel.ExchangePattern;

import static gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor.*;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.*;

/**
 * will transform the caaers XML to AdEERS XML and publish the request to AdEERS Report Service
 */
public class ToAdeersReportServiceRouteBuilder {


    public void configure(Caaers2AdeersRouteBuilder rb){
        rb.from("activemq:queue:gov:nih:nci:caaers:jmsOut:submitReport")
            .streamCaching()
            .to("log:gov.nih.nci.cabig.report2adeers.first-request?showHeaders=true&multiline=true&level=ERROR")
            .setProperty(OPERATION_NAME, rb.constant("sendReportToAdeers"))
            .setProperty(ENTITY_NAME, rb.constant("SafetyReport"))
            .processRef("adeersHeaderGeneratorProcessor")
            .processRef("headerGeneratorProcessor")
            .to("log:gov.nih.nci.cabig.report2adeers.split-path?showHeaders=true&multiline=true&level=DEBUG")
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
             .otherwise()
                     .to("direct:adeers-response");

        rb.from("direct:communication-error")
            .process(track(ADEERS_SUBMISSION_FAILED, "Communication error"))
            .to("xslt:xslt/adeers/response/report-error-transformer.xsl")
            .process(track(REPORT_SUBMISSION_RESPONSE))
                .to(rb.getFileTracker().fileURI(REPORT_SUBMISSION_RESPONSE))
            .to("log:gov.nih.nci.cabig.report2adeers.pre-multicast?showHeaders=true&multiline=true&level=WARN")
            .to("activemq:queue:gov:nih:nci:caaers:jmsOut:returnReport", "direct:routeAdEERSResponseSink");

        rb.from("direct:adeers-response")
            .to("xslt:xslt/adeers/response/report-transformer.xsl")
            .process(track(REPORT_SUBMISSION_RESPONSE))
                .to(rb.getFileTracker().fileURI(REPORT_SUBMISSION_RESPONSE))
            .to("log:gov.nih.nci.cabig.report2adeers.response-sent?showHeaders=true&multiline=true&level=WARN")
            .multicast()
                .to("direct:toE2bAck", "direct:toJms");


        rb.from("direct:toJms")
                .to("log:gov.nih.nci.cabig.report2adeers.to-caaers-jms?showHeaders=true&multiline=true&level=WARN")
                .to("activemq:queue:gov:nih:nci:caaers:jmsOut:returnReport");

        rb.from("direct:toE2bAck")
                .to("log:gov.nih.nci.cabig.report2adeers.to-e2b-ack?showHeaders=true&multiline=true&level=WARN")
                .to("direct:routeAdEERSResponseSink");
    }

}
