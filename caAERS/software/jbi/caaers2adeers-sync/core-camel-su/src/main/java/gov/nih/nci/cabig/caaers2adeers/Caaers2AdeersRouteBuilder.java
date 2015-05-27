/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.caaers2adeers.track.FileTracker;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import gov.nih.nci.cabig.open2caaers.ToCaaersParticipantWSRouteBuilder;
import gov.nih.nci.cabig.rave2caaers.FromRaveToCaaersWSRouteBuilder;
import gov.nih.nci.cabig.report2adeers.ToAdeersReportServiceRouteBuilder;
import gov.nih.nci.cabig.report2caaers.AdeersResponseToE2BAckRouteBuilder;
import gov.nih.nci.cabig.report2caaers.ToCaaersReportWSRouteBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.model.ProcessorDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXParseException;

import javax.annotation.Resource;

import static gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor.INVALID_MESSAGE;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.*;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;

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
    @Autowired
    private ToAdeersReportServiceRouteBuilder adeersReportServiceRouteBuilder;

    @Resource(name = "participantInitializationPort")
    private String participantInitializationPort;
    @Resource(name = "raveIntegrationServicesPort")
    private String raveIntegrationServicesPort;
    
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
		ProcessorDefinition<?> pd = from(fromSink);

        if(requestXSL != null) {
            pd.process(track(xslInStage))
                    .to("log:gov.nih.nci.cabig.caaers2adeers.pre-in-xsl-invoke?showHeaders=true&level=TRACE&multiline=true&showException=true&showStackTrace=true")
                .to("xslt:" + requestXSL);
        }

        pd.process(track(serviceInvocationStage))
                .to(fileTracker.fileURI(serviceInvocationStage))
                .to("log:gov.nih.nci.cabig.caaers2adeers.preservice-invoke?showHeaders=true&level=TRACE&multiline=true&showException=true&showStackTrace=true")
                .to(ExchangePattern.InOut, serviceURI)
                .processRef("headerGeneratorProcessor")
                .to("log:gov.nih.nci.cabig.caaers2adeers.post-service-invoke?showHeaders=true&level=TRACE&multiline=true&showException=true&showStackTrace=true")
                .to(fileTracker.fileURI(serviceCompletionStage))
                .process(track(serviceCompletionStage, true));

        if(responseXSL != null) {
            pd.process(track(xslOutStage))
                    .to("log:gov.nih.nci.cabig.caaers2adeers.pre-out-xsl-invoke?showHeaders=true&level=TRACE&multiline=true&showException=true&showStackTrace=true")
                    .to("xslt:" + responseXSL);

        }
        pd.to("log:gov.nih.nci.cabig.caaers2adeers.afterWSCallResponseXSL?showHeaders=true&level=TRACE&showException=true&showStackTrace=true")
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
        
		onException(Throwable.class, Exception.class, NullPointerException.class)
			.to("log:gov.nih.nci.cabig.caaers2adeers.general_error?showHeaders=true&multiline=true&level=ERROR")
    		.choice()
            .when(header(ToCaaersReportWSRouteBuilder.NEEDS_ACK).isEqualTo(Boolean.TRUE.toString()))
            	.to("xslt:" + ToCaaersReportWSRouteBuilder.responseXSLBase + "E2BParserErrors2ACK.xsl")
            	.to("direct:sendE2BAckSink")
            .otherwise()
                .to("direct:morgue")
                .stop()
                .end();
        
        onException(ClassCastException.class)
                // create a custom failure response
        	.to("log:gov.nih.nci.cabig.caaers2adeers.class_cast_error?showHeaders=true&multiline=true&level=ERROR")
        	.choice()
            .when(header(ToCaaersReportWSRouteBuilder.NEEDS_ACK).isEqualTo(Boolean.TRUE.toString()))
            	.to("xslt:" + ToCaaersReportWSRouteBuilder.responseXSLBase + "E2BParserErrors2ACK.xsl")
            	.to("direct:sendE2BAckSink")
            .otherwise()
        		//todo; this generates the referencenumber on startup and it is not modified afterward.
                .transform(constant("<Response ReferenceNumber=\"" + System.currentTimeMillis() + "\" IsTransactionSuccessful=\"0\" " +
                        "ReasonCode=\"WS_GEN_007\" ErrorClientResponseMessage=\"Invalid XML\"/>"))
                // remember not to set as handled(true) to make camel think it's OK response,
                // this error would be caught in the binding component, which will determine correct response
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .process(track(REQUST_PROCESSING_ERROR, "Error"))
                .to(fileTracker.fileURI(REQUST_PROCESSING_ERROR))
        .stop()
        .end() ;



        // route for caaers integration services - trim white space
        from("jetty:http://0.0.0.0:"+raveIntegrationServicesPort+"/caaers/services/RaveIntegrationServices")
	            .streamCaching()
                .choice()
		        .when(header("CamelHttpMethod").isEqualTo("POST"))
                    .setExchangePattern(ExchangePattern.InOut)
                    .processRef("crlfFixProcessor")
                    .processRef("raveIntegrationHeaderProcessor")
                    .processRef("headerGeneratorProcessor")
                    .choice()
                      .when(header(INVALID_MESSAGE).isEqualTo("true"))
                            .to("direct:soapfault")
                      .otherwise()
                        .process(track(REQUEST_RECEIVED))
                        .to(fileTracker.fileURI(REQUEST_RECEIVED))
                        .doTry()
                            .to("validator:xsd/soap-envelope.xsd")
                            .process(track(PRE_PROCESS_RAVE_INTEGRATION_MSG))
                                .to(fileTracker.fileURI(PRE_PROCESS_RAVE_INTEGRATION_MSG))
                            .to("direct:processedRave2CaaersMessageSink")
                        .doCatch(SAXParseException.class)
                            .to("direct:soapfault")
                        .stop()
                    .end();



      //configure route towards caAERS Webservices
  	fromRaveToCaaersWSRouteBuilder.configure(this);

    //check for errors 
      from("direct:rave2CaaersOutSink")
	        .to("log:gov.nih.nci.cabig.rave2caaers.from-rave2CaaersOutSink?showAll=true&level=TRACE&showException=true&showStackTrace=true")
      	.process(track(REQUEST_COMPLETION))
      	.to(fileTracker.fileURI(REQUEST_COMPLETION));
      
        // route for Participant Service
        
        from("jetty:http://0.0.0.0:"+participantInitializationPort+"/caaers/ParticipantInitialization?httpBindingRef=participantODMMessageBinding")
	        .streamCaching()
	        .choice() 
		        .when(header("CamelHttpMethod").isEqualTo("POST"))
		         	.process(track(REQUEST_RECEIVED))
			        .processRef("participantODMMessageProcessor")
			        .processRef("crlfFixProcessor")
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
//
        //for report submission to adeers
        adeersReportServiceRouteBuilder.configure(this);
    	
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


        //BELOW 3 routes are the final sinks of messages.
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

		//invalid soap requests
        from("direct:soapfault")
                .to("log:gov.nih.nci.cabig.caaers2adeers.invalidsoap?showAll=true&level=WARN&showException=true&showStackTrace=true")
                .transform(constant("<error>Invalid Soap Request</error>"))
                .to("xslt:xslt/caaers/response/soapfault.xsl")
                //.process(track(REQUST_PROCESSING_ERROR, "Invalid SOAP request"))
                .to(fileTracker.fileURI(REQUST_PROCESSING_ERROR));

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
