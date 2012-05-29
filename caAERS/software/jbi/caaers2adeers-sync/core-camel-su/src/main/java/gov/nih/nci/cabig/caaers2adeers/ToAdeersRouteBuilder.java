package gov.nih.nci.cabig.caaers2adeers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;

public class ToAdeersRouteBuilder {
	
	
	private String adEERSLOVServiceJBIURL = "jbi:service:http://services.ctep.nci.nih.gov//CESAELOVService?operation={http://services.ctep.nci.nih.gov/}";
	private String adEERSStudyServiceJBIURL = "jbi:service:http://services.ctep.nci.nih.gov//CESAEStudyService?operation={http://services.ctep.nci.nih.gov/}";
	private String requestXSLBase = "xslt/adeers/request/";
	private String responseXSLBase = "xslt/adeers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;


    private String xpathPredicate(String entity, String operation){
        return new StringBuilder("/payload/request/entity/text() = '").append(entity).append("' ")
                .append(" and ")
                .append("/payload/request/operation/@name = '").append(operation).append("' ")
                .toString();
    }
	public void configure(Caaers2AdeersRouteBuilder rb){
         this.routeBuilder = rb;

		//BASE - Content based Router
		routeBuilder.from("direct:adEERSRequestSink")
    		.to("log:gov.nih.nci.cabig.caaers2adeers.to-adeers?level=TRACE")
    		.process(track(ROUTED_TO_ADEERS_WS_INVOCATION_CHANNEL))
    		.choice()
    			.when().xpath(xpathPredicate("agent", "getAgentsLOV")).to("direct:adeers-agent-lov")
    			.when().xpath(xpathPredicate("asael", "getASAEL")).to("direct:adeers-asael-lov")
    			.when().xpath(xpathPredicate("agentDoseUOM", "getAgentDoseUOMLOV")).to("direct:adeers-doseUOM-lov")
    			.when().xpath(xpathPredicate("device", "getDevicesLOV")).to("direct:adeers-device-lov")
    			.when().xpath(xpathPredicate("ctcae", "getCTCAELOV")).to("direct:adeers-ctcae-lov")
    			.when().xpath(xpathPredicate("preexistingcondition", "getPreExistingConditionsLOV")).to("direct:adeers-condition-lov")
    			.when().xpath(xpathPredicate("priortherapy", "getTherapiesLOV")).to("direct:adeers-therapy-lov")
    			.when().xpath(xpathPredicate("lab", "getLabsLOV")).to("direct:adeers-lab-lov")
    			.when().xpath(xpathPredicate("organization", "getOrganizationsLOV")).to("direct:adeers-organization-lov")
    			.when().xpath(xpathPredicate("mergedorganization", "getMergedOrganization")).to("direct:adeers-merged-organization-lov")
                .when().xpath(xpathPredicate("study", "createStudy")).to("direct:adeers-study-details")
                .when().xpath(xpathPredicate("study", "updateStudy")).to("direct:adeers-study-details")
    			.when().xpath(xpathPredicate("study", "searchStudy")).to("direct:adeers-study-search")
    			.otherwise().to("direct:morgue");
		
		//LOV - Agents
    	configureLovWSCallRoute("direct:adeers-agent-lov", "agent_lov.xsl",  "getAgentsLOV");

    	//LOV - Agents Dose UOM
    	configureLovWSCallRoute("direct:adeers-doseUOM-lov", "doseuom_lov.xsl",  "getAgentDoseUOMLOV");
    	
    	//LOV - ASAEL
    	configureLovWSCallRoute("direct:adeers-asael-lov", "asael_lov.xsl", "getASAEL");

    	//LOV - Device
    	configureLovWSCallRoute("direct:adeers-device-lov", "device_lov.xsl", "getDevicesLOV");
    	
    	//LOV - CTCAE
    	configureLovWSCallRoute("direct:adeers-ctcae-lov", "ctcae_lov.xsl", "getCTCAELOV");

    	//LOV - PreExistingCondition
    	configureLovWSCallRoute("direct:adeers-condition-lov", "pre_existing_condition_lov.xsl", "getPreExistingConditionsLOV");

    	//LOV - PriorTherapy
    	configureLovWSCallRoute("direct:adeers-therapy-lov", "prior_therapy_lov.xsl", "getTherapiesLOV");
    	
    	//LOV - Lab
    	configureLovWSCallRoute("direct:adeers-lab-lov", "lab_lov.xsl", "getLabsLOV");

        //LOV - Organization
        configureLovWSCallRoute("direct:adeers-organization-lov", "organization_lov.xsl",  "getOrganizationsLOV");
        
        //LOV - MergedOrganization
        configureLovWSCallRoute("direct:adeers-merged-organization-lov", "merged_organization.xsl",  "getMergedOrganization");

        //LOV - Study Details
        configureStudyWSCallRoute("direct:adeers-study-details", "study_details.xsl",  "getStudyDetails");

        //Search Study
        configureStudyWSCallRoute("direct:adeers-study-search", "study_search.xsl", "searchStudy");

	}
	
	private void configureLovWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				adEERSLOVServiceJBIURL + serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:adEERSResponseSink",
				ADEERS_WS_IN_TRANSFORMATION, ADEERS_WS_INVOCATION_INITIATED, ADEERS_WS_INVOCATION_COMPLETED, ADEERS_WS_OUT_TRANSFORMATION, ROUTED_TO_ADEERS_RESPONSE_SINK);
	}

    private void configureStudyWSCallRoute(String fromSink, String xslFileName, String serviceURI){
        this.routeBuilder.configureWSCallRoute(fromSink,
                requestXSLBase + xslFileName,
                adEERSStudyServiceJBIURL + serviceURI,
                responseXSLBase + xslFileName,
                "direct:adEERSResponseSink",
				ADEERS_WS_IN_TRANSFORMATION, ADEERS_WS_INVOCATION_INITIATED, ADEERS_WS_INVOCATION_COMPLETED, ADEERS_WS_OUT_TRANSFORMATION, ROUTED_TO_ADEERS_RESPONSE_SINK);
    }
}
