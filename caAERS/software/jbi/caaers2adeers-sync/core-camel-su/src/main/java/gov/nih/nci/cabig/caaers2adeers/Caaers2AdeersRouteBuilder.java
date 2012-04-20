package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.caaers2adeers.test.MockMessageGenerator;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * The basic flow can be classified into 3, a) towards adEERS b) towards caAERS c) towards SynchComponent.
 * 
 * At the moment, the Synch Component is logical
 *   
 *   The to-adEERS route has the following
 *   [adEERSRequestSink] => [router] => [specific-adeers-ws-request] => [adEERSResponseSink]
 *   
 *   The to-caAERS route has the following
 *   [adEERSResponseSink] => [router] => [specific-caaers-ws-request] => [caAERSResponseSink]
 *   
 * Once the message is obtained from caAERs, the status is logged/saved in the DB.   
 *   [caAERSResponseSink] => [db-persistense]
 */
public class Caaers2AdeersRouteBuilder extends RouteBuilder {
    
	/**
	 * Will create a route that calls a webservice with before-call and after-call transformations
	 * @param fromSink - the channel through which the input arrives. 
	 * @param requestXSL - xslt file that can generate the soap request wrapper
	 * @param serviceURI - the actual webservice endpoint URI
	 * @param responseXSL - xslt file that can generate the internal xml from soap response
	 * @param toSink - the channel through which the output is send. 
	 */
	public void configureWSCallRoute(String fromSink, String requestXSL, String serviceURI,  String responseXSL, String toSink){
		from(fromSink)
        .to("log:caaers.beforeRequestXSL?showHeaders=true")
		.to("xslt:" + requestXSL)
        .to("log:caaers.afterRequestXSL?showHeaders=true")
        .to(ExchangePattern.InOut, serviceURI).processRef("headerGeneratorProcessor")
        .to("log:caaers.beforeResponseXSL?showAll=true")
		.to("xslt:" + responseXSL)
        .to("log:caaers.afterResponseXSL?showHeaders=true")
		.to(toSink);
	}

    /**
     * Will create a sub route for transformation.
     * @param fromSink - From channel
     * @param xslFile  - The qualified name of XSL file to use, for the transformation
     */
    public void configureTransformationRoute(String fromSink, String xslFile){
        from(fromSink)
                .to("log:caaers.beforeSyncXSL?showHeaders=true")
                .to("xslt:" + xslFile)
                .to("log:caaers.afterSyncXSL?showHeaders=true");
    }
	
    public void configure() {

        onException(Throwable.class).to("direct:morgue");

        //webservice request
        //from("")
        // .setHeader(C2A_SYNC_HEADER, xpath("/payload/request/operation/@mode = 'sync'"))
        // .to("direct:adEERSRequestSink");
        
      //just for testing.. 
//    	from("timer://tutorial?fixedRate=true&delay=2000&period=300000")
//        	.processRef("exchangePreProcessor")
//    		.setBody(constant(MockMessageGenerator.getStudyDetails("CALGB-90802")))
//    		.to("direct:adEERSRequestSink");

    	//just for testing.. 
//    	from("timer://tutorial?fixedRate=true&delay=10000&period=300000")
//        	.processRef("exchangePreProcessor")
//    		.setBody(constant(getMessage()))
//    		.to("direct:adEERSRequestSink");
        
        //just for testing generic webservice
    	from("jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/generic-processor-sink")
    		.processRef("exchangePreProcessor")
    		.to("xslt:xslt/adeers/request/soap_env_filter.xsl")
    		.to("log:after-soap_filter")
			.to("direct:adEERSRequestSink");

    	new ToAdeersRouteBuilder(this).configure();
    	new ToCaaersAsynchronousRouteBuilder(this).configure();
    	new ToCaaersSynchronousRouteBuilder(this).configure();

    	//need to process AdEERS results, may be the SyncComponent...  
    	from("direct:adEERSResponseSink")
                .to("log:caaers.synch-comp?showHeaders=true")
                .choice()
                    .when(header("c2a_sync_mode").isEqualTo("sync")).to("direct:caAERSSynchronousRequestSink")
                    .otherwise().to("direct:caAERSAsynchronousRequestSink");
    	
    	//need to process caAERS results
		from("direct:caAERSResponseSink").to("log:caaers.direct-caAERSResponseSink");
    	
		//invalid requests
        from("direct:morgue")
                .to("log:fromMorgue?showAll=true")
                .to("xslt:xslt/caaers/response/unknown.xsl")
                .to("log:after-unknown")
                .to("log:caaers.invalid?showAll=true&level=WARN");

    }

}

/**
 * The internal XML Formats
 * Request
 * ========
 * <payload>
 *  <system>adeers</system>
 *   <request>
 *      <entity>agent</entity>
 *      <operation name="getAgentsLOV" mode="sync">
 * 	        <criteria>
 * 		        <criterion  name="createdDate">12-02-2002</criterion>
 * 		        <criterion name="lastUpdatedDate">12-02-2002</criterion>
 * 	        </criteria>
 *      </operation>
 *    </request>
 * </payload>
 * 
 * Response
 * ========
 * <payload>
 *  <system>adeers</system>
 *  <response>
 *      <entity>agent</entity>
 *      <operation name="getAgentsLOV">
 *          <errors>
 *  	        <error></error>
 *          <errors>
 *          <data>
 *          </data>
 *      </operation>
 *  </response>
 * </payload>
 */
