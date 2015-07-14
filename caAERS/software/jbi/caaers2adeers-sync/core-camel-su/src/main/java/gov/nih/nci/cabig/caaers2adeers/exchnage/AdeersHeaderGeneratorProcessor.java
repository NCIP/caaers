/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.exchnage;

import static gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor.CORRELATION_ID;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.AE_REPORT_ID;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.REPORT_EXTERNAL_ENDPOINT;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.REPORT_ID;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.REPORT_MESSAGE_COMBO_ID;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.REPORT_SUBMITTER_EMAIL;
import static gov.nih.nci.cabig.report2adeers.exchange.AdeersReportSubmissionProcessor.REPORT_WITHDRAW;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This bean will pre-process the messages
 */
public class AdeersHeaderGeneratorProcessor implements Processor {

    protected static final Log log = LogFactory.getLog(AdeersHeaderGeneratorProcessor.class);

    public void process(Exchange exchange) throws Exception {
        exchange.setProperty(REPORT_WITHDRAW, XPathBuilder.xpath("/AdverseEventReport/WITHDRAW/text()").evaluate(exchange, String.class));
        exchange.setProperty(AE_REPORT_ID, XPathBuilder.xpath("/AdverseEventReport/CAEERS_AEREPORT_ID/text()").evaluate(exchange, String.class));
        exchange.setProperty(REPORT_ID, XPathBuilder.xpath("/AdverseEventReport/CAAERSRID/text()").evaluate(exchange, String.class));
        exchange.setProperty(REPORT_EXTERNAL_ENDPOINT, XPathBuilder.xpath("/AdverseEventReport/EXTERNAL_SYSTEMS/text()").evaluate(exchange, String.class));
        exchange.setProperty(REPORT_SUBMITTER_EMAIL, XPathBuilder.xpath("/AdverseEventReport/SUBMITTER_EMAIL/text()").evaluate(exchange, String.class));
        exchange.setProperty(REPORT_MESSAGE_COMBO_ID, XPathBuilder.xpath("/AdverseEventReport/MESSAGE_COMBO_ID/text()").evaluate(exchange, String.class));
        exchange.setProperty(CORRELATION_ID, XPathBuilder.xpath("/AdverseEventReport/CORRELATION_ID/text()").evaluate(exchange, String.class));
    }
}
