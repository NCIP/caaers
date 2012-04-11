package gov.nih.nci.cabig.caaers2adeers;


public class ToCaaersRouteBuilder {

	private String caAERSAgentServiceJBIURL = "jbi:service:http://ws.caaers.cabig.nci.nih.gov//AgentManagementWebService?operation={http://ws.caaers.cabig.nci.nih.gov/}";
	
	private String requestXSLBase = "xslt/caaers/request/";
	private String responseXSLBase = "xslt/caaers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;
	
	public ToCaaersRouteBuilder(Caaers2AdeersRouteBuilder routeBuilder) {
		this.routeBuilder = routeBuilder;
	}
	
	public void configure(){
		//content based router
		routeBuilder.from("direct:caAERSRequestSink")
		.to("log:caaers-request")
		.choice()
			.when().xpath("not('payload/response/operation/errors') and payload/response/operation/data").to("direct:caaers-agent-sync")
			.otherwise().to("log:unknown-caaers-request");
		
		//caAERS - createOrUpdateAgents
		configureWSCallRoute("direct:caaers-agent-sync", "agents_sync.xsl", caAERSAgentServiceJBIURL + "createOrUpdateAgent" );
	}
	

	private void configureWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:caAERSResponseSink");
	}
	
	
}
