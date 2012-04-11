package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.ExchangePattern;
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
	
	
	//the below items should come from properties file, via spring.
	private String adeersUsername;
	private String adeersPassword;
	private String caaersUsername = "SYSTEM";
	private String caaersPassword = "system_admin";
	
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
		.to("xslt:" + requestXSL)
		.to(ExchangePattern.InOut, serviceURI)  
		.to("xslt:" + responseXSL)
		.to(toSink);
	}
	
    public void configure() {

    	//just for testing.. 
    	from("timer://tutorial?fixedRate=true&delay=30000&period=300000")
    		.setBody(constant(getMessage()))
    		.to("direct:adEERSRequestSink");
    	
    	new ToAdeersRouteBuilder(this).configure();
    	new ToCaaersRouteBuilder(this).configure();
    	
    	//need to process AdEERS results, may be the SyncComponent...  
    	from("direct:adEERSRequestSink").to("direct:caAERSRequestSink");
    	
    	//need to process caAERS results
		from("direct:caAERSResponseSink") .to("log:direct:caAERSResponseSink");
    	
		//need to elaborate error handling. 
		onException(Exception.class).to("log:email-me");
    }
    
    private String getMessage() {
    	
        return "<payload>" +
        		"<system>adeers</system>" +
        		"<request>" +
            		"<entity>agent</entity>" +
            		"<operation name=\"getAgentsLOV\">" +
            		"<criteria>" +
            		"<criterion  name=\"createdDate\">12-02-2002</criterion>" +
            		"<criterion name=\"lastUpdatedDate\">12-02-2002</criterion>" +
            		"</criteria>" +
            		"</operation>" +
            		"</request></payload>";
        }
}

/**
 * The internal XML Formats
 * Request
 * ========
 * <payload>
 * <system>adeers</system>
 * <request>
 * <entity>agent</entity>
 * <operation method="getAgentsLOV">
 * 	<criteria>
 * 		<criterion  name="createdDate">12-02-2002</criterion>
 * 		<criterion name="lastUpdatedDate">12-02-2002</criterion>
 * 	</criteria>
 * </operation>
 * </request>
 * </payload>
 * 
 * Response
 * ========
 * <payload>
 * <system>adeers</system>
 * <response>
 *  <entity>agent</entity>
 *  <operation type="getAgentsLOV">
 *  <errors>
 *  	<error></error>
 *  <errors>
 *  <data>
 *  </data>
 *  </operation>
 * </response>
 * </payload>
 */
