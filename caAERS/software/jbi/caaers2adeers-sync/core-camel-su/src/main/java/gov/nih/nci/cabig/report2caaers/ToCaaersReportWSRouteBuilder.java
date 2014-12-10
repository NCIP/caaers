/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers;

import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;

import java.util.HashMap;
import java.util.Map;

import static gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor.*;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;

public class ToCaaersReportWSRouteBuilder {

	private static String caAERSSafetyReportJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereport/SafetyReportManagementService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereport}";
	
	private static String requestXSLBase = "xslt/e2b/request/";
	private static String responseXSLBase = "xslt/e2b/response/";
	
	private static String[] msgComboIdPaths = { "//safetyreportid", "//messagedate"};
	
	private String inputEDIDir;
	private String outputEDIDir;	
		
	private Caaers2AdeersRouteBuilder routeBuilder;

	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;
        
        Map<String, String> nss = new HashMap<String, String>();
        nss.put("svrl", "http://purl.oclc.org/dsdl/svrl");
        
        routeBuilder.from("file://"+inputEDIDir+"?preMove=inprogress&move=done&moveFailed=movefailed")
            .streamCaching()
            .setProperty(CORRELATION_ID, rb.constant(String.valueOf(System.currentTimeMillis())))
            .setProperty(SYNC_HEADER, rb.constant("sync"))
            .setProperty(ENTITY_NAME, rb.constant("SafetyReport"))
            .setProperty(OPERATION_NAME, rb.constant("submitSafetyReport"))
            .processRef("headerGeneratorProcessor")
            .process(track(REQUEST_RECEIVED))
                .to(rb.getFileTracker().fileURI(REQUEST_RECEIVED))
            .convertBodyTo(String.class, "UTF-8")
        	.convertBodyTo(byte[].class, "Windows-1252")
        	.convertBodyTo(String.class, "UTF-8")
        	.to("log:gov.nih.nci.cabig.report2caaers.caaers-ws-request?showHeaders=true&level=WARN")
            .processRef("removeEDIHeadersAndFootersProcessor")
            .process(track(E2B_SUBMISSION_REQUEST_RECEIVED, msgComboIdPaths))
                .to(routeBuilder.getFileTracker().fileURI(E2B_SUBMISSION_REQUEST_RECEIVED))
            .processRef("eDIMessagePreProcessor")
            .process(track(PRE_PROCESS_EDI_MSG))
                .to(routeBuilder.getFileTracker().fileURI(PRE_PROCESS_EDI_MSG))
			.to("direct:performSchematronValidation");
        
        //perform schematron validation
        routeBuilder.from("direct:performSchematronValidation")                
			.to("xslt:" + requestXSLBase + "safetyreport_e2b_schematron.xsl?transformerFactoryClass=net.sf.saxon.TransformerFactoryImpl") //for XSLT2.0 support
            .processRef("headerGeneratorProcessor")
            .process(track(E2B_SCHEMATRON_VALIDATION))
			    .to(routeBuilder.getFileTracker().fileURI(E2B_SCHEMATRON_VALIDATION))
			.choice()
                .when().xpath("//svrl:failed-assert", nss) 
                	.to("xslt:" + responseXSLBase + "extract-failures.xsl")
                	.to("xslt:" + responseXSLBase + "E2BSchematronErrors2ACK.xsl")
                	.to("direct:sendE2BAckSink")
                .otherwise()
                	.to("direct:processE2B");

        routeBuilder.from("direct:processE2B")
        	.to("log:gov.nih.nci.cabig.report2caaers.post-validation?showHeaders=true&level=ERROR")
        	.processRef("resetOriginalMessageProcessor")
            .processRef("headerGeneratorProcessor")
            .process(track(ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL))
            .to("direct:caaers-reportSubmit-sync");


        //caAERS - submitsafety route
        configureWSCallRoute("direct:caaers-reportSubmit-sync", "safetyreport_e2b_sync.xsl", caAERSSafetyReportJBIURL + "submitSafetyReport" );


        nss = new HashMap<String, String>();
        nss.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        nss.put("ns1", "http://schema.integration.caaers.cabig.nci.nih.gov/aereport");
        nss.put("ns3", "http://schema.integration.caaers.cabig.nci.nih.gov/common");

        //content based router
        //if it is saveSafetyReportResponse, then E2B ack will not be sent
        //also, if submit safety report is processed successfully or successfully submitted to AdEERS, then E2B ack will not be sent
        routeBuilder.from("direct:processedE2BMessageSink")
			.to("log:gov.nih.nci.cabig.report2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
			.choice()
                .when().xpath("/soap:Envelope/soap:Body/ns1:saveSafetyReportResponse", nss)
                	.to("direct:morgue")
                .when().xpath("/ichicsrack/acknowledgment/messageacknowledgment/parsingerrormessage", nss)
                	.to("direct:sendE2BAckSink")
                .otherwise()
                	.to("direct:morgue");


        routeBuilder.from("direct:sendE2BAckSink")
			.processRef("addEDIHeadersAndFootersProcessor")
			.process(track(REQUST_PROCESSING_ERROR))
			.to(routeBuilder.getFileTracker().fileURI(REQUST_PROCESSING_ERROR))
			.to("file://"+outputEDIDir);
	}
	

	private void configureWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:processedE2BMessageSink", 
				CAAERS_WS_IN_TRANSFORMATION, CAAERS_WS_INVOCATION_INITIATED, CAAERS_WS_INVOCATION_COMPLETED, CAAERS_WS_OUT_TRANSFORMATION, ROUTED_TO_CAAERS_RESPONSE_SINK);
	}


	public String getInputEDIDir() {
		return inputEDIDir;
	}


	public void setInputEDIDir(String inputEDIDir) {
		this.inputEDIDir = inputEDIDir;
	}


	public String getOutputEDIDir() {
		return outputEDIDir;
	}


	public void setOutputEDIDir(String outputEDIDir) {
		this.outputEDIDir = outputEDIDir;
	}
	
	
	
}
