package gov.nih.nci.cabig.caaers2adeers;


public class ToCaaersAsynchronousRouteBuilder {

	private String caAERSAgentServiceJBIURL = "jbi:service:http://webservice.caaers.cabig.nci.nih.gov/common/AgentManagementWebService?operation={http://webservice.caaers.cabig.nci.nih.gov/common}";
	
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
		.choice()
			.when().xpath(xpathPredicate("agent", "getAgentsLOV")).to("direct:caaers-agent-async")
			.otherwise().to("direct:morgue");
		
		//caAERS - createOrUpdateAgents
		configureWSCallRoute("direct:caaers-agent-async", "agents_async.xsl", caAERSAgentServiceJBIURL + "createOrUpdateAgent" );
	}
	

	private void configureWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:caAERSResponseSink");
	}
	
	
}
