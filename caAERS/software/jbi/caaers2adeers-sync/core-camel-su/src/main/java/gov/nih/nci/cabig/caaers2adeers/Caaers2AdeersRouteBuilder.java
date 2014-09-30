/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.NO_DATA_AVAILABLE;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.PRE_PROCESS_OPEN_ODM_MSG;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.PRE_PROCESS_RAV_CAAERS_INTEG_MSG;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUEST_COMPLETION;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUEST_RECEIVED;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUST_PROCESSING_ERROR;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.ROUTED_TO_ADEERS_REQUEST_SINK;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.ROUTED_TO_CAAERS_REQUEST_SINK;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import gov.nih.nci.cabig.caaers2adeers.track.FileTracker;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import gov.nih.nci.cabig.open2caaers.ToCaaersParticipantWSRouteBuilder;
import gov.nih.nci.cabig.rave2caaers.FromRaveToCaaersWSRouteBuilder;
import gov.nih.nci.cabig.report2caaers.AdeersResponseToE2BAckRouteBuilder;
import gov.nih.nci.cabig.report2caaers.ToCaaersReportWSRouteBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
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
    @Autowired
    private ToCaaersParticipantWSRouteBuilder toCaaersParticipantWSRouteBuilder;
    @Autowired
    private FromRaveToCaaersWSRouteBuilder fromRaveToCaaersWSRouteBuilder;
    @Autowired
    private ToCaaersReportWSRouteBuilder toCaaersReportWSRouteBuilder;
    @Autowired
    private AdeersResponseToE2BAckRouteBuilder adeersResponseToE2BAckRouteBuilder;
    
	public FileTracker getFileTracker() {
		return fileTracker;
	}

	public void setFileTracker(FileTracker fileTracker) {
		this.fileTracker = fileTracker;
	}

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
        .to("log:gov.nih.nci.cabig.caaers2adeers.afterWSCallResponseXSL?showHeaders=true&level=TRACE&showException=true&showStackTrace=true")
        .process(track(toSinkStage))
		.to(toSink);
	}
	
	
	public void configureRave2CaaersWSCallRoute(String fromSink, String serviceURI, String responseXSL, String toSink,
			Stage xslInStage, Stage serviceInvocationStage, Stage serviceCompletionStage, Stage xslOutStage, Stage toSinkStage){
		from(fromSink)
        .to(fileTracker.fileURI(serviceInvocationStage))
        .to(ExchangePattern.InOut, serviceURI)
        .processRef("headerGeneratorProcessor")
        .to(fileTracker.fileURI(serviceCompletionStage))
        .process(track(serviceCompletionStage, true))
        .process(track(xslOutStage))
        .to("xslt:" + responseXSL)
        .to("log:gov.nih.nci.cabig.caaers2adeers.afterWSCallResponseXSL?showHeaders=true&level=TRACE&showException=true&showStackTrace=true")
        .process(track(toSinkStage))
		.to(toSink);
	}



    public void configureRave2CaaersWSCallRoute(String fromSink, String serviceURI, String toSink,
                                                Stage xslInStage, Stage serviceInvocationStage,
                                                Stage serviceCompletionStage, Stage xslOutStage, Stage toSinkStage){
        from(fromSink)
                .to(fileTracker.fileURI(serviceInvocationStage))
                .to(ExchangePattern.InOut, serviceURI)
                .processRef("headerGeneratorProcessor")
                .to(fileTracker.fileURI(serviceCompletionStage))
                .process(track(serviceCompletionStage, true))
                .process(track(xslOutStage))
                .to("log:gov.nih.nci.cabig.caaers2adeers.afterWSCallResponseXSL?showHeaders=true&level=TRACE&showException=true&showStackTrace=true")
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
        Namespaces ns = new Namespaces("soap",  "http://schemas.xmlsoap.org/soap/envelope/");
        onException(Throwable.class)
                .to("direct:morgue");
        
        onException(ClassCastException.class)
        // create a custom failure response
        .transform(constant("<Response ReferenceNumber=\"" + System.currentTimeMillis() + "\" IsTransactionSuccessful=\"0\" " +
        		"ReasonCode=\"WS_GEN_007\" ErrorClientResponseMessage=\"Invalid XML\"/>"))
        // remember not to set as handled(true) to make camel think it's OK response, 
        // this error would be caught in the binding component, which will determine correct response
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
        .process(track(REQUST_PROCESSING_ERROR, "Error"))
        .to(fileTracker.fileURI(REQUST_PROCESSING_ERROR)) ;


        // route for caaers integration services - trim white space
        from("jetty:http://0.0.0.0:7711/caaers/services/RaveIntegrationServices")
	        .choice()
		        .when(header("CamelHttpMethod").isEqualTo("POST"))
		         	.processRef("trimWhitespaceMessageProcessor")
			        .processRef("headerGeneratorProcessor")
                    .doTry()
                        .to("validator:xsd/soap-envelope.xsd")
                            .to(fileTracker.fileURI(REQUEST_RECEIVED))
                            .process(track(PRE_PROCESS_RAV_CAAERS_INTEG_MSG))
                            .to("direct:processedRave2CaaersMessageSink")
                    .doCatch(org.apache.camel.ValidationException.class)
                        .handled(true)
                        .to("direct:morgue");

      //configure route towards caAERS Webservices
  	fromRaveToCaaersWSRouteBuilder.configure(this);

    //check for errors 
      from("direct:rave2CaaersOutSink")
	        .to("log:gov.nih.nci.cabig.rave2caaers.from-rave2CaaersOutSink?showAll=true&level=TRACE&showException=true&showStackTrace=true")
      	.process(track(REQUEST_COMPLETION))
      	.to(fileTracker.fileURI(REQUEST_COMPLETION));
      
        // route for Participant Service
        
        from("jetty:http://0.0.0.0:7700/caaers/ParticipantInitialization?httpBindingRef=participantODMMessageBinding")
	        .streamCaching()
	        .choice() 
		        .when(header("CamelHttpMethod").isEqualTo("POST"))
		         	.process(track(REQUEST_RECEIVED))
			        .processRef("participantODMMessageProcessor")
			        .processRef("headerGeneratorProcessor")
			        .to(fileTracker.fileURI(REQUEST_RECEIVED))
			        .to("xslt:" + "xslt/caaers/request/strip_namespaces.xsl")
			        .process(track(PRE_PROCESS_OPEN_ODM_MSG))
			        .to("direct:processedOpenOdmMessageSink") 
		         .otherwise().end();

        //configure route towards caAERS Webservices
    	toCaaersParticipantWSRouteBuilder.configure(this);

      //check for errors 
        from("direct:odmOutSink")
	        .to("log:gov.nih.nci.cabig.open2caaers.from-oDMoutputSink?showAll=true&level=TRACE&showException=true&showStackTrace=true")
        	.process(track(REQUEST_COMPLETION))
        	.to(fileTracker.fileURI(REQUEST_COMPLETION));


        //just for testing generic webservice
        
    	from("jbi:service:http://schema.integration.caaers.cabig.nci.nih.gov/common/generic-processor-sink")
            .processRef("exchangePreProcessor").processRef("headerGeneratorProcessor")
                .process(track(REQUEST_RECEIVED))
                .to(fileTracker.fileURI(REQUEST_RECEIVED))
            .to("xslt:xslt/adeers/request/soap_env_filter.xsl")
                .process(track(ROUTED_TO_ADEERS_REQUEST_SINK))
            .to("direct:adEERSRequestSink");
    	

        //configure all the Quartz cron jobs
        cronJobRouteBuilder.configure(this);
        //configure routes towards adeers
    	toAdeersRouteBuilder.configure(this);
        //configure route towards caAERS Webservices
    	toCaaersWebserviceRouteBuilder.configure(this);
        //configure routes towards caAERS client
    	toCaaersClientRouteBuilder.configure(this);
    	//configure routes towards caAERS safety report service with E2B input
    	toCaaersReportWSRouteBuilder.configure(this);
    	//process adeers response and generate E2B ACK
    	adeersResponseToE2BAckRouteBuilder.configure(this);
    	
    	//need to process AdEERS results, may be the SyncComponent...  
    	    	
    	from("direct:adEERSResponseSink")
                .to("log:gov.nih.nci.cabig.caaers2adeers.synch-comp?showHeaders=true&level=TRACE&showException=true&showStackTrace=true")
                .choice()
                    .when().xpath("//operation/errors/error")
                        .to("direct:morgue")
                    .when().xpath("not(//operation/data/child::*/child::*)")
                        .process(track(NO_DATA_AVAILABLE, "Processed"))
                        .to("direct:outputSink")
                    .when(header("c2a_sync_mode").isEqualTo("async"))
                    	.process(track(ROUTED_TO_CAAERS_REQUEST_SINK))
                        .to("direct:caaersWSRequestSink")
                    .otherwise()
                    	.process(track(ROUTED_TO_CAAERS_REQUEST_SINK))
                        .to("direct:caaersClientRequestSink");
    	
    	//need to process caAERS results
		from("direct:caAERSResponseSink")
                .choice()
                    .when().xpath("//operation/errors/error")
                        .to("direct:morgue")
                    .when().xpath("//soap:Fault", ns)
                        .to("direct:morgue")
                    .otherwise()
                        .to("direct:outputSink");


        //BELOW 2 routes are the final sinks of messages.
        from("direct:outputSink")
                .to("log:gov.nih.nci.cabig.caaers2adeers.from-outputSink?showAll=true&level=TRACE&showException=true&showStackTrace=true")
                .process(track(REQUEST_COMPLETION))
                .to(fileTracker.fileURI(REQUEST_COMPLETION)) ;

		//invalid requests
        from("direct:morgue")
                .to("log:gov.nih.nci.cabig.caaers2adeers.invalid?showAll=true&level=WARN&showException=true&showStackTrace=true")
        		.process(track(REQUST_PROCESSING_ERROR, "Error"))
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
