/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.rave2caaers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_INVOCATION_COMPLETED;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_INVOCATION_INITIATED;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_IN_TRANSFORMATION;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.CAAERS_WS_OUT_TRANSFORMATION;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.ROUTED_TO_CAAERS_RESPONSE_SINK;
import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;

public class FromRaveToCaaersWSRouteBuilder {

	private String caAERSGenerateReportIdServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereportid/SafetyReportIdService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereportid}";
	private String caAERSSAEEvaluationServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/saerules/SAEEvaluationWebServiceImplService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/saerules}";	
	private String caAERSSafetyReportServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereport/SafetyReportManagementService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereport}";


    private static String responseXSLBase = "xslt/rave/response/";

    private Caaers2AdeersRouteBuilder routeBuilder;
	
	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;

		//content based router
		routeBuilder.from("direct:processedRave2CaaersMessageSink")
		.to("log:gov.nih.nci.cabig.rave2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
		.choice()
		.when().xpath("//*[local-name()='generateSafetyReportId']").to("direct:caaers-generateSafetyReportIdService-sync")
		.when().xpath("//*[local-name()='saveAndEvaluateAEs']").to("direct:caaers-saveAndEvaluateAEs-sync")
		.when().xpath("//*[local-name()='submitSafetyReport']").to("direct:caaers-submitSafetyReport-sync")
		.when().xpath("//*[local-name()='initiateSafetyReportAction']").to("direct:caaers-initiateSafetyReportAction-sync")
		.otherwise().to("direct:morgue");
		
        //caAERS - call generate safety report ID service
        configureWSCallRoute("direct:caaers-generateSafetyReportIdService-sync", caAERSGenerateReportIdServiceJBIURL + "generateSafetyReportId", null );
        //caAERS - call generate SAE Evaluation service
        configureWSCallRoute("direct:caaers-saveAndEvaluateAEs-sync", caAERSSAEEvaluationServiceJBIURL + "saveAndEvaluateAEs", "sae-evaluation-merge-actions.xsl" );
        //caAERS - call Submit Safety Report service 
        configureWSCallRoute("direct:caaers-submitSafetyReport-sync", caAERSSafetyReportServiceJBIURL + "submitSafetyReport", "sae-evaluation-merge-actions.xsl" );
        //caAERS - call Initiate Safety Report service 
        configureWSCallRoute("direct:caaers-initiateSafetyReportAction-sync", caAERSSafetyReportServiceJBIURL + "initiateSafetyReportAction", null );
	}
	

	private void configureWSCallRoute(String fromSink, String serviceURI, String responseXSL){
        if(responseXSL == null) {

            this.routeBuilder.configureRave2CaaersWSCallRoute(fromSink,
                    serviceURI,
                    "direct:rave2CaaersOutSink",
                    CAAERS_WS_IN_TRANSFORMATION, CAAERS_WS_INVOCATION_INITIATED, CAAERS_WS_INVOCATION_COMPLETED, CAAERS_WS_OUT_TRANSFORMATION, ROUTED_TO_CAAERS_RESPONSE_SINK);

        } else {

            this.routeBuilder.configureRave2CaaersWSCallRoute(fromSink,
                    serviceURI,  responseXSLBase + responseXSL,
                    "direct:rave2CaaersOutSink",
                    CAAERS_WS_IN_TRANSFORMATION, CAAERS_WS_INVOCATION_INITIATED, CAAERS_WS_INVOCATION_COMPLETED, CAAERS_WS_OUT_TRANSFORMATION, ROUTED_TO_CAAERS_RESPONSE_SINK);

        }
	}
	
	
}
