package gov.nih.nci.cabig.caaers2adeers;


public class ToAdeersRouteBuilder {
	
	
	private String adEERSLOVServiceJBIURL = "jbi:service:http://services.ctep.nci.nih.gov//CESAELOVService?operation={http://services.ctep.nci.nih.gov/}";
	private String requestXSLBase = "xslt/adeers/request/";
	private String responseXSLBase = "xslt/adeers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;

	public ToAdeersRouteBuilder(Caaers2AdeersRouteBuilder routeBuilder){
		this.routeBuilder = routeBuilder;
	}
	
    private String xpathPredicate(String entity){
        return new StringBuilder("/payload/request/entity/text() = '")
                .append(entity).append("'").toString();
    }
	public void configure(){

		//BASE - Content based Router
		routeBuilder.from("direct:adEERSRequestSink")
    		.to("log:to-adeers")
    		.choice()
    			.when().xpath(xpathPredicate("agent") ).to("direct:adeers-agent-lov")
    			.when().xpath(xpathPredicate("asael")).to("direct:adeers-asael-lov")
    			.otherwise().to("log:unknown-adeers-request");
		
		//LOV - Agents
    	configureLovWSCallRoute("direct:adeers-agent-lov", "agents_lov.xsl",  "getAgentsLOV");

    	//LOV - ASAEL
//    	configureLovWSCallRoute("direct:adeers-asael-lov", "asael_lov.xsl", "getAsaelLOV");
		
    	
  
	}
	
	private void configureLovWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				adEERSLOVServiceJBIURL + serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:adEERSResponseSink");
	}
}
