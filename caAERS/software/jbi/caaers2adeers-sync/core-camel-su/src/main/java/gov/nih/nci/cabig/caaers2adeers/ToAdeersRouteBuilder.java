package gov.nih.nci.cabig.caaers2adeers;


public class ToAdeersRouteBuilder {
	
	
	private String adEERSLOVServiceJBIURL = "jbi:service:http://services.ctep.nci.nih.gov//CESAELOVService?operation={http://services.ctep.nci.nih.gov/}";
	private String adEERSStudyServiceJBIURL = "jbi:service:http://services.ctep.nci.nih.gov//CESAEStudyService?operation={http://services.ctep.nci.nih.gov/}";
	private String requestXSLBase = "xslt/adeers/request/";
	private String responseXSLBase = "xslt/adeers/response/";
	
	private Caaers2AdeersRouteBuilder routeBuilder;

	public ToAdeersRouteBuilder(Caaers2AdeersRouteBuilder routeBuilder){
		this.routeBuilder = routeBuilder;
	}
	
    private String xpathPredicate(String entity, String operation){
        return new StringBuilder("/payload/request/entity/text() = '").append(entity).append("' ")
                .append(" and ")
                .append("/payload/request/operation/@name = '").append(operation).append("' ")
                .toString();
    }
	public void configure(){

		//BASE - Content based Router
		routeBuilder.from("direct:adEERSRequestSink")
            .processRef("exchangePreProcessor")
            .processRef("headerGeneratorProcessor")
    		.to("log:caaers.to-adeers")
    		.choice()
    			.when().xpath(xpathPredicate("agent", "getAgentsLOV") ).to("direct:adeers-agent-lov")
    			.when().xpath(xpathPredicate("asael", "getASAEL")).to("direct:adeers-asael-lov")
    			.when().xpath(xpathPredicate("study", "searchStudy")).to("direct:adeers-study-search")
    			.otherwise().to("direct:morgue");
		
		//LOV - Agents
    	configureLovWSCallRoute("direct:adeers-agent-lov", "agents_lov.xsl",  "getAgentsLOV");

    	//LOV - ASAEL
//    	configureLovWSCallRoute("direct:adeers-asael-lov", "asael_lov.xsl", "getAsaelLOV");

        //Search Study
        configureStudyWSCallRoute("direct:adeers-study-search", "study_search.xsl", "searchStudy");

	}
	
	private void configureLovWSCallRoute(String fromSink, String xslFileName, String serviceURI){
		this.routeBuilder.configureWSCallRoute(fromSink, 
				requestXSLBase + xslFileName, 
				adEERSLOVServiceJBIURL + serviceURI, 
				responseXSLBase + xslFileName, 
				"direct:adEERSResponseSink");
	}

    private void configureStudyWSCallRoute(String fromSink, String xslFileName, String serviceURI){
        this.routeBuilder.configureWSCallRoute(fromSink,
                requestXSLBase + xslFileName,
                adEERSStudyServiceJBIURL + serviceURI,
                responseXSLBase + xslFileName,
                "direct:adEERSResponseSink");
    }
}
