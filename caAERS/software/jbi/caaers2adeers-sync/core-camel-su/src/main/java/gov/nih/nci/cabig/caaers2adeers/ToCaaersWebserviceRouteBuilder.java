package gov.nih.nci.cabig.caaers2adeers;

import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;

public class ToCaaersWebserviceRouteBuilder {

	private String caAERSAgentServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/AgentManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSAgentDoseUOMServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/ConfigPropertiesWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSASAELServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/ASAELService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSDeviceServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/DevicesService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSCtcServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/CtcsService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSLabServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/LabManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSOrganizationServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/OrganizationManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSAgentPreExistingConditionServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/PreExistingConditionManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSPriorTherapyServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/PriorTherapyManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSStudyServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/study/StudyService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/study}";
	
	private String requestXSLBase = "xslt/caaers/request/";
	private String responseXSLBase = "xslt/caaers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;

    private String xpathPredicate(String entity, String operation){
        
        return new StringBuilder("/payload/response/operation/data ")
                .append(" and ")
                .append("not(/payload/response/operation/errors)")
                .append(" and ")
                .append("/payload/response/operation/@name = '").append(operation).append("' ")
                .append("and ")
                .append("/payload/response/entity = '").append(entity).append("'").toString();
    }
	
	public void configure(Caaers2AdeersRouteBuilder rb){
        this.routeBuilder = rb;

		//content based router
		routeBuilder.from("direct:caaersWSRequestSink")
		.to("log:gov.nih.nci.cabig.caaers2adeers.caaers-ws-request?showHeaders=true&level=TRACE")
		.process(track(ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL))
		.choice()
			.when().xpath(xpathPredicate("agent", "getAgentsLOV")).to("direct:caaers-agent-async")
			.when().xpath(xpathPredicate("agentDoseUOM", "getAgentDoseUOMLOV")).to("direct:caaers-doseUOM-async")
			.when().xpath(xpathPredicate("asael", "getASAEL")).to("direct:caaers-asael-async")
			.when().xpath(xpathPredicate("organization", "getOrganizationsLOV")).to("direct:caaers-organization-async")
			.when().xpath(xpathPredicate("mergedorganization", "getMergedOrganization")).to("direct:caaers-merged-organization-async")
			.when().xpath(xpathPredicate("device", "getDevicesLOV")).to("direct:caaers-device-async")
			.when().xpath(xpathPredicate("ctcae", "getCTCAELOV")).to("direct:caaers-ctc-async")
			.when().xpath(xpathPredicate("lab", "getLabsLOV")).to("direct:caaers-lab-async")
			.when().xpath(xpathPredicate("preexistingcondition", "getPreExistingConditionsLOV")).to("direct:caaers-condition-async")
			.when().xpath(xpathPredicate("priortherapy", "getTherapiesLOV")).to("direct:caaers-therapy-async")
			.when().xpath(xpathPredicate("study", "createStudy")).to("direct:caaers-studydetailsCreate-async")
			.when().xpath(xpathPredicate("study", "updateStudy")).to("direct:caaers-studydetailsUpdate-async")
			.otherwise().to("direct:morgue");
		
		//caAERS - createOrUpdateAgents
		configureWSCallRoute("direct:caaers-agent-async", "agent_async.xsl", caAERSAgentServiceJBIURL + "createOrUpdateAgent" );

		//caAERS - createOrUpdateConfigProperties
		configureWSCallRoute("direct:caaers-doseUOM-async", "doseuom_async.xsl", caAERSAgentDoseUOMServiceJBIURL + "createOrUpdateConfigProperties" );

		//caAERS - createOrUpdateASAEL
		configureWSCallRoute("direct:caaers-asael-async", "asael_async.xsl", caAERSASAELServiceJBIURL + "createOrUpdateASAEL" );

        //caAERS - createOrUpdateOrganization
        configureWSCallRoute("direct:caaers-organization-async", "organization_async.xsl", caAERSOrganizationServiceJBIURL + "createOrUpdateOrganization" );
        
      //caAERS - mergeOrganization
        configureWSCallRoute("direct:caaers-merged-organization-async", "merged_organization_async.xsl", caAERSOrganizationServiceJBIURL + "mergeOrganization" );

        //caAERS - createOrUpdateDevices
        configureWSCallRoute("direct:caaers-device-async", "device_async.xsl", caAERSDeviceServiceJBIURL + "createOrUpdateDevices" );
        
      //caAERS - createOrUpdateCtcs
        configureWSCallRoute("direct:caaers-ctc-async", "ctc_async.xsl", caAERSCtcServiceJBIURL + "createOrUpdateCtcs" );
        
        //caAERS - createOrUpdateLabs
        configureWSCallRoute("direct:caaers-lab-async", "lab_async.xsl", caAERSLabServiceJBIURL + "createOrUpdateLabs" );

        //caAERS - createOrUpdatePreExistingCondition
        configureWSCallRoute("direct:caaers-condition-async", "pre_existing_condition_async.xsl", caAERSAgentPreExistingConditionServiceJBIURL + "createOrUpdatePreExistingCondition" );

        //caAERS - createOrUpdatePriorTherapy
        configureWSCallRoute("direct:caaers-therapy-async", "prior_therapy_async.xsl", caAERSPriorTherapyServiceJBIURL + "createOrUpdatePriorTherapy" );

        //caAERS - getStudyDetails
        configureWSCallRoute("direct:caaers-studydetailsCreate-async", "study_details_async.xsl", caAERSStudyServiceJBIURL + "createStudy" );
        configureWSCallRoute("direct:caaers-studydetailsUpdate-async", "study_details_async.xsl", caAERSStudyServiceJBIURL + "updateStudy" );

	}
	

	private void configureWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:caAERSResponseSink", 
				CAAERS_WS_IN_TRANSFORMATION, CAAERS_WS_INVOCATION_INITIATED, CAAERS_WS_INVOCATION_COMPLETED, CAAERS_WS_OUT_TRANSFORMATION, ROUTED_TO_CAAERS_RESPONSE_SINK);
	}
	
	
}
