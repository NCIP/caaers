/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers;

import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;

public class ToCaaersReportWSRouteBuilder {

	private static String caAERSSafetyReportJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereport/SafetyReportManagementService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereport}";
	
	private static String requestXSLBase = "xslt/e2b/request/";
	private static String responseXSLBase = "xslt/e2b/response/";
	
	private String inputEDIDir;
	private String outputEDIDir;	
		
	private Caaers2AdeersRouteBuilder routeBuilder;

	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;
        
        routeBuilder.from("file://"+inputEDIDir+"?preMove=inprogress&move=done&moveFailed=movefailed")
        	.to("log:gov.nih.nci.cabig.report2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
			.process(track(REQUEST_RECEIVED))
			.to(routeBuilder.getFileTracker().fileURI(REQUEST_RECEIVED))
			.processRef("removeEDIHeadersAndFootersProcessor")
			.process(track(PRE_PROCESS_EDI_MSG))
			.to(routeBuilder.getFileTracker().fileURI(PRE_PROCESS_EDI_MSG))
			.to("direct:e2bStudySink", "direct:caaers-reportSubmit-sync");
        
		//content based router
		routeBuilder.from("direct:processedE2BMessageSink")
			.to("log:gov.nih.nci.cabig.report2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
			.process(track(ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL))
			.processRef("addEDIHeadersAndFootersProcessor")
			.process(track(POST_PROCESS_EDI_MSG))
			.to(routeBuilder.getFileTracker().fileURI(POST_PROCESS_EDI_MSG))
			.to("file://"+outputEDIDir);
		
        //caAERS - createParticipant
        configureWSCallRoute("direct:caaers-reportSubmit-sync", "safetyreport_e2b_sync.xsl", caAERSSafetyReportJBIURL + "submitSafetyReport" );

        //route to invoke study sync
        routeBuilder.from("direct:e2bStudySink")
                .to("xslt:" + requestXSLBase + "study_sync_request.xsl")
                .processRef("exchangePreProcessor").processRef("headerGeneratorProcessor")
                .to("direct:adEERSRequestSink");

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
