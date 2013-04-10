/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.open2caaers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.PRE_PROCESS_OPEN_ODM_MSG;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUEST_COMPLETION;
import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUEST_RECEIVED;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import gov.nih.nci.cabig.caaers2adeers.track.FileTracker;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import gov.nih.nci.cabig.open2caaers.exchange.ParticipantODMMessageProcessor;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The basic flow is towards caAERS.
 * 
 *   The to-caAERS route has the following
 *   [openPostSink] => [router] => [specific-caaers-ws-request] => [caAERSRequestSink]
 *   
 * Once the message is obtained from caAERs, the status is logged/saved in the DB.   
 *   [caAERSResponseSink] => [db-persistense]
 */
public class OpenToCaeersRouteBuilder extends RouteBuilder {

    @Autowired
    private ToCaaersParticipantWSRouteBuilder toCaaersParticipantWSRouteBuilder;
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
        .to("log:gov.nih.nci.cabig.open2caaers.afterWSCallResponseXSL?showHeaders=true&level=TRACE&showException=true&showStackTrace=true")
        .process(track(toSinkStage))
		.to(toSink);
	}

    public void configure() {
        Namespaces ns = new Namespaces("soap",  "http://schemas.xmlsoap.org/soap/envelope/");
        
        onException(Throwable.class)
                .to("direct:morgue");
        
        from("jetty:" + "http://localhost:8080/caaers/participantService/?httpBindingRef=participantODMMessageBinding")
        .process(track(REQUEST_RECEIVED))
        .process(new ParticipantODMMessageProcessor())
        .to("direct:participantOpenOdmMessageSink");
        
        
   //process OPEN ODM msg to add security header and correlation id
        
    	from("direct:participantOpenOdmMessageSink")
            .processRef("exchangePreProcessor").processRef("headerGeneratorProcessor")
                .process(track(REQUEST_RECEIVED))
                .to(fileTracker.fileURI(REQUEST_RECEIVED))
                .process(track(PRE_PROCESS_OPEN_ODM_MSG))
            .to("direct:processedOpenOdmMessageSink");

        //configure route towards caAERS Webservices
    	toCaaersParticipantWSRouteBuilder.configure(this);

    	//need to process caAERS results
		from("direct:caAERSParticipantServiceResponseSink")
                .choice()
                    .when().xpath("//operation/errors/error")
                        .to("direct:morgue")
                    .when().xpath("//soap:Fault", ns)
                        .to("direct:morgue")
                    .otherwise()
                        .to("direct:outputSink");


        //BELOW one is the response to OPEN.
        from("direct:outputSink")
                .to("log:gov.nih.nci.cabig.open2caaers.from-outputSink?showAll=true&level=TRACE&showException=true&showStackTrace=true")
                .process(track(REQUEST_COMPLETION))
                .to(fileTracker.fileURI(REQUEST_COMPLETION))
                .to("direct:openResponseSink");

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
