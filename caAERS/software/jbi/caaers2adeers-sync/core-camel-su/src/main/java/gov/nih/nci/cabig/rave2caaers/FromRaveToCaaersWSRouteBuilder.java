/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.rave2caaers;

import gov.nih.nci.cabig.caaers2adeers.Caaers2AdeersRouteBuilder;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.rave2caaers.exchange.RaveIntegrationHeaderProcessor.*;

public class FromRaveToCaaersWSRouteBuilder {

	private String caAERSGenerateReportIdServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereportid/SafetyReportIdService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereportid}";
	private String caAERSSAEEvaluationServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/saerules/SAEEvaluationWebServiceImplService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/saerules}";	
	private String caAERSSafetyReportServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/aereport/SafetyReportManagementService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/aereport}";


    private static String responseXSLBase = "xslt/rave/response/";

    private Caaers2AdeersRouteBuilder routeBuilder;
	
	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;

		//content based router
		rb.from("direct:processedRave2CaaersMessageSink")
		    .to("log:gov.nih.nci.cabig.rave2caaers.caaers-ws-request?showHeaders=true&level=TRACE")
		    .choice()
		        .when(rb.header(OPERATION_NAME).isEqualTo(SAFETY_REPORT_ID_OPERATION_NAME))
                    .to("direct:caaers-generateSafetyReportIdService-sync")
                .when(rb.header(OPERATION_NAME).isEqualTo(SAE_EVALUATION_OPERATION_NAME))
                    .to("direct:caaers-saveAndEvaluateAEs-sync")
                .when(rb.header(OPERATION_NAME).isEqualTo(INITIATE_SAFETY_REPORT_OPERATION_NAME))
                    .to("direct:caaers-initiateSafetyReportAction-sync")
		        .otherwise()
                    .to("direct:morgue");
		
        //caAERS - call generate safety report ID service
        configureWSCallRoute("direct:caaers-generateSafetyReportIdService-sync", caAERSGenerateReportIdServiceJBIURL + "generateSafetyReportId", null );
        //caAERS - call generate SAE Evaluation service
        configureWSCallRoute("direct:caaers-saveAndEvaluateAEs-sync", caAERSSAEEvaluationServiceJBIURL + "saveAndEvaluateAEs", "sae-evaluation-merge-actions.xsl" );
        //caAERS - call Initiate Safety Report service 
        configureWSCallRoute("direct:caaers-initiateSafetyReportAction-sync", caAERSSafetyReportServiceJBIURL + "initiateSafetyReportAction", "safetyreport-merge-actions.xsl" );
	}
	
    //no transformation is needed for the input
	private void configureWSCallRoute(String fromSink, String serviceURI, String responseXSL){
        String inXSL = null;
        String outXSL = responseXSL == null ? null : responseXSLBase  + responseXSL;
        routeBuilder.configureWSCallRoute(fromSink,inXSL,serviceURI,outXSL,"direct:rave2CaaersOutSink",
                CAAERS_WS_IN_TRANSFORMATION, CAAERS_WS_INVOCATION_INITIATED, CAAERS_WS_INVOCATION_COMPLETED,
                CAAERS_WS_OUT_TRANSFORMATION, ROUTED_TO_CAAERS_RESPONSE_SINK);
	}
	
}
