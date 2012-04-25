package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.caaers2adeers.IntegrationLog.Stage;


public class ToCaaersAsynchronousRouteBuilder {

	private String caAERSAgentServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/AgentManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSASAELServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/ASAELService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSDeviceServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/DevicesService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSOrganizationServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/OrganizationManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSAgentPreExistingConditionServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/PreExistingConditionManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSPriorTherapyServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/PriorTherapyManagementWebService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/common}";
	private String caAERSStudyServiceJBIURL = "jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/study/StudyService?operation={http://schema.integration.caaers.cabig.nci.nih.gov/study}";
	
	private String requestXSLBase = "xslt/caaers/request/";
	private String responseXSLBase = "xslt/caaers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;
	
	public ToCaaersAsynchronousRouteBuilder(Caaers2AdeersRouteBuilder routeBuilder) {
		this.routeBuilder = routeBuilder;
	}
    
    private String xpathPredicate(String entity, String operation){
        
        return new StringBuilder("/payload/response/operation/data ")
                .append(" and ")
                .append("not(/payload/response/operation/errors)")
                .append(" and ")
                .append("/payload/response/operation/@name = '").append(operation).append("' ")
                .append("and ")
                .append("/payload/response/entity = '").append(entity).append("'").toString();
    }
	
	public void configure(){
		//content based router
		routeBuilder.from("direct:caAERSAsynchronousRequestSink")
		.to("log:caaers.caaers-request?showHeaders=true")
		.process(new TrackerPreProcessor(Stage.ROUTED_TO_CAAERS_SINK)).to("bean:tracker?method=record")
		.choice()
			.when().xpath(xpathPredicate("agent", "getAgentsLOV")).to("direct:caaers-agent-async")
			.when().xpath(xpathPredicate("organization", "getOrganizationsLOV")).to("direct:caaers-organization-async")
			.when().xpath(xpathPredicate("study", "getStudyDetails")).to("direct:caaers-studydetails-async")
			.otherwise().to("direct:morgue");
		
		//caAERS - createOrUpdateAgents
		configureWSCallRoute("direct:caaers-agent-async", "agent_async.xsl", caAERSAgentServiceJBIURL + "createOrUpdateAgent" );

        //caAERS - createOrUpdateOrganization
        configureWSCallRoute("direct:caaers-organization-async", "organization_async.xsl", caAERSOrganizationServiceJBIURL + "createOrUpdateOrganization" );

        //caAERS - getStudyDetails
        configureWSCallRoute("direct:caaers-studydetails-async", "study_details_async.xsl", caAERSStudyServiceJBIURL + "createStudy" );

	}
	

	private void configureWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:caAERSResponseSink");
	}
	
	
}
