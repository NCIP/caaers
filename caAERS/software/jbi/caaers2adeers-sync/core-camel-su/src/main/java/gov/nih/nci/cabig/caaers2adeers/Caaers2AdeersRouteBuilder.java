package gov.nih.nci.cabig.caaers2adeers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;

import gov.nih.nci.cabig.caaers2adeers.track.FileTracker;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private CronJobRouteBuilder cronJobRouteBuilder;
    @Autowired
    private ToAdeersRouteBuilder toAdeersRouteBuilder;
    @Autowired
    private ToCaaersClientRouteBuilder toCaaersClientRouteBuilder;
    @Autowired
    private ToCaaersWebserviceRouteBuilder toCaaersWebserviceRouteBuilder;
    @Autowired
    private FileTracker fileTracker;

	/**
	 * Will create a route that calls a webservice with before-call and after-call transformations
	 * @param fromSink - the channel through which the input arrives. 
	 * @param requestXSL - xslt file that can generate the soap request wrapper
	 * @param serviceURI - the actual webservice endpoint URI
	 * @param responseXSL - xslt file that can generate the internal xml from soap response
	 * @param toSink - the channel through which the output is send. 
	 */
	public void configureWSCallRoute(String fromSink, String requestXSL, String serviceURI,  String responseXSL, String toSink, 
			Stage xslInStage, Stage serviceInvocationStage, Stage serviceCompletionStage, Stage xslOutStage, Stage toSinkStage){
		from(fromSink)
        .process(track(xslInStage))
		.to("xslt:" + requestXSL)
        .process(track(serviceInvocationStage))
        .to(fileTracker.fileURI(serviceInvocationStage))
        .to(ExchangePattern.InOut, serviceURI).processRef("headerGeneratorProcessor")
        .to(fileTracker.fileURI(serviceCompletionStage))
        .process(track(serviceCompletionStage, true))
        .process(track(xslOutStage))
		.to("xslt:" + responseXSL)
        .to("log:caaers.afterWSCallResponseXSL?showHeaders=true")
        .process(track(toSinkStage))
		.to(toSink);
	}

    /**
     * Will create a sub route for transformation.
     * @param fromSink - From channel
     * @param xslFile  - The qualified name of XSL file to use, for the transformation
     */
    public void configureTransformationRoute(String fromSink, String xslFile){
        from(fromSink)
                .to("xslt:" + xslFile)
                .to("direct:outputSink");
    }
	
    public void configure() {
        
        onException(Throwable.class)
                .to("direct:morgue");

        //just for testing generic webservice
        
    	from("jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/generic-processor-sink")
            .processRef("exchangePreProcessor").processRef("headerGeneratorProcessor")
                .process(track(REQUEST_RECEIVED))
                .to(fileTracker.fileURI(REQUEST_RECEIVED))
            .to("xslt:xslt/adeers/request/soap_env_filter.xsl")
                .process(track(ROUTED_TO_ADEERS_REQUEST_SINK))
            .to("direct:adEERSRequestSink");

        //NEED to introduce a QUEUE of similar...

//		.choice()
//                .when(header("c2a_sync_mode").isEqualTo("sync"))
//                	.to("log:after-soap_filter-and-before-sync")
//                	.to("direct:adEERSRequestSink")
//                .otherwise()
//                	.to("log:after-soap_filter-and-before-async")
//                	.multicast(new UseFirstAggregationStrategy()).parallelProcessing().to("xslt:xslt/caaers/response/async_success_response.xsl", "direct:adEERSRequestSink");

        //configure all the Quartz cron jobs
        cronJobRouteBuilder.configure(this);
        //configure routes towards adeers
    	toAdeersRouteBuilder.configure(this);
        //configure route towards caAERS Webservices
    	toCaaersWebserviceRouteBuilder.configure(this);
        //configure routes towards caAERS client
    	toCaaersClientRouteBuilder.configure(this);

    	//need to process AdEERS results, may be the SyncComponent...  
    	from("direct:adEERSResponseSink")
                .to("log:caaers.synch-comp?showHeaders=true")
                .choice()
                    .when().xpath("not(//operation/data/child::*/child::*)")
                        .process(track(NO_DATA_AVAILABLE))
                        .to("direct:outputSink")
                    .when(header("c2a_sync_mode").isEqualTo("async"))
                    	.process(track(ROUTED_TO_CAAERS_REQUEST_SINK))
                        .to("direct:caaersWSRequestSink")
                    .otherwise()
                    	.process(track(ROUTED_TO_CAAERS_REQUEST_SINK))
                        .to("direct:caaersClientRequestSink");
    	
    	//need to process caAERS results
		from("direct:caAERSResponseSink")
                .to("direct:outputSink");


        //BELOW 2 routes are the final sinks of messages.
        from("direct:outputSink")
                .to("log:from-outputSink?showAll=true")
                .process(track(REQUEST_COMPLETION))
                .to(fileTracker.fileURI(REQUEST_COMPLETION)) ;

		//invalid requests
        from("direct:morgue")
                .to("log:caaers.invalid?showAll=true&level=WARN")
        		.process(track(REQUST_PROCESSING_ERROR))
                .to("xslt:xslt/caaers/response/unknown.xsl")
                .to(fileTracker.fileURI(REQUST_PROCESSING_ERROR)) ;

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
