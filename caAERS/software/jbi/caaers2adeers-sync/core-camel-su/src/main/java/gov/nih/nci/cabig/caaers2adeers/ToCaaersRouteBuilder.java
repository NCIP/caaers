package gov.nih.nci.cabig.caaers2adeers;


public class ToCaaersRouteBuilder {

	private String caAERSAgentServiceJBIURL = "jbi:service:http://webservice.caaers.cabig.nci.nih.gov/common/AgentManagementWebService?operation={http://webservice.caaers.cabig.nci.nih.gov/common}";
	
	private String requestXSLBase = "xslt/caaers/request/";
	private String responseXSLBase = "xslt/caaers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;
	
	public ToCaaersRouteBuilder(Caaers2AdeersRouteBuilder routeBuilder) {
		this.routeBuilder = routeBuilder;
	}
    
    private String xpathPredicate(String entity){
        
        return new StringBuilder("/payload/response/operation/data ")
                .append(" and ")
                .append("not(/payload/response/operation/errors)")
                .append("and ")
                .append("/payload/response/entity = '").append(entity).append("'").toString();
    }
	
	public void configure(){
		//content based router
		routeBuilder.from("direct:caAERSRequestSink")
		.to("log:caaers-request")
		.choice()
			.when().xpath(xpathPredicate("agent")).to("direct:caaers-agent-sync")
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
