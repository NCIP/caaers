/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.open2caaers;

import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;

public class ToCaaersParticipantWSRouteBuilder {

private String caAERSParticipantServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/participant/ParticipantService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/participant}";
	
	private String requestXSLBase = "xslt/caaers/request/";
	private String responseXSLBase = "xslt/caaers/response/";
	
	private OpenToCaeersRouteBuilder routeBuilder;

	public void configure(OpenToCaeersRouteBuilder rb){
        this.routeBuilder = rb;

		//content based router
		routeBuilder.from("direct:processedOpenOdmMessageSink")
		.to("log:gov.nih.nci.cabig.open2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
		.process(track(ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL))
		.to("direct:caaers-participantdetailsCreate-sync");
		
        //caAERS - createParticipant
        configureWSCallRoute("direct:caaers-participantdetailsCreate-sync", "participant_details_sync.xsl", caAERSParticipantServiceJBIURL + "createParticipant" );
	}
	

	private void configureWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:caAERSParticipantServiceResponseSink", 
				CAAERS_WS_IN_TRANSFORMATION, CAAERS_WS_INVOCATION_INITIATED, CAAERS_WS_INVOCATION_COMPLETED, CAAERS_WS_OUT_TRANSFORMATION, ROUTED_TO_CAAERS_RESPONSE_SINK);
	}
	
}
