/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_INVOCATION_COMPLETED;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_INVOCATION_INITIATED;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_IN_TRANSFORMATION;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_OUT_TRANSFORMATION;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.POST_PROCESS_EDI_MSG;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.PRE_PROCESS_EDI_MSG;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUEST_RECEIVED;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.ROUTED_TO_CAAERS_RESPONSE_SINK;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.E2B_SCHEMATRON_VALIDATION;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;

import java.util.HashMap;
import java.util.Map;

import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;

public class ToCaaersReportWSRouteBuilder {

	private static String caAERSSafetyReportJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereport/SafetyReportManagementService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereport}";
	
	private static String requestXSLBase = "xslt/e2b/request/";
	private static String responseXSLBase = "xslt/e2b/response/";
	
	private static String[] msgComboIdPaths = { "//safetyreportid",
												"//messagedate"};
	
	private String inputEDIDir;
	private String outputEDIDir;	
		
	private Caaers2AdeersRouteBuilder routeBuilder;

	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;
        
        routeBuilder.from("file://"+inputEDIDir+"?preMove=inprogress&move=done&moveFailed=movefailed")
        	.to("log:gov.nih.nci.cabig.report2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
        	.processRef("removeEDIHeadersAndFootersProcessor")
			.process(track(REQUEST_RECEIVED, msgComboIdPaths))
			.to(routeBuilder.getFileTracker().fileURI(REQUEST_RECEIVED))
			.processRef("eDIMessagePreProcessor")
			.process(track(PRE_PROCESS_EDI_MSG))
			.to(routeBuilder.getFileTracker().fileURI(PRE_PROCESS_EDI_MSG))
			.to("direct:performSchematronValidation");
        
        Map<String, String> nss = new HashMap<String, String>();
        nss.put("svrl", "http://purl.oclc.org/dsdl/svrl");
        
        //perform schematron validation
        routeBuilder.from("direct:performSchematronValidation")                
			.process(track(E2B_SCHEMATRON_VALIDATION))
			.to("xslt:" + requestXSLBase + "safetyreport_e2b_schematron.xsl")
			.to(routeBuilder.getFileTracker().fileURI(E2B_SCHEMATRON_VALIDATION))
			.choice()
                .when().xpath("//payload/svrl:failed-assert", nss) 
                	.to("xslt:" + responseXSLBase + "extract-failures.xsl")
                	.to("xslt:" + responseXSLBase + "E2BSchematronErrors2ACK.xsl")
                	.to("direct:sendE2BAckSink")
                .otherwise()
                	.to("direct:caaers-reportSubmit-sync");
        
        nss = new HashMap<String, String>();
        nss.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        nss.put("ns1", "http://schema.integration.caaers.cabig.nci.nih.gov/aereport");
        nss.put("ns3", "http://schema.integration.caaers.cabig.nci.nih.gov/common");
               
		//content based router 
        //if it is saveSafetyReportResponse, then E2B ack will not be sent
        //also, if submit safety report is processed successfully to indicate succesfully submitted to AdEERS, then E2B ack will not be sent
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
			.process(track(ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL))
			.processRef("addEDIHeadersAndFootersProcessor")
			.process(track(POST_PROCESS_EDI_MSG))
			.to(routeBuilder.getFileTracker().fileURI(POST_PROCESS_EDI_MSG))
			.to("file://"+outputEDIDir);
		
        //caAERS - createParticipant
        configureWSCallRoute("direct:caaers-reportSubmit-sync", "safetyreport_e2b_sync.xsl", caAERSSafetyReportJBIURL + "submitSafetyReport" );
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
