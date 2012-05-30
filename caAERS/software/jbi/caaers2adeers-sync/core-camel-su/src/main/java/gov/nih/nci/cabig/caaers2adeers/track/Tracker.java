package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Biju Joseph (minor refactoring)
 */
public class Tracker implements Processor{
	
	protected static final Log log = LogFactory.getLog(Tracker.class);
	public static final String SOAP_FAULT_STATUS= "FAILED_TO_PROCESS";
	public static final String CAAERS_RESPONSE_ERROR= "FAILED_TO_PROCESS";
	
 	// progress made by synch request
 	private Stage stage;
 	
 	// details 
 	private String notes;
 	
 	boolean caputureLogDetails = false; 
 	
	public Tracker(Stage stage, String notes, boolean caputureLogDetails) {
		super();
		this.stage = stage;
		this.notes = notes;
		this.caputureLogDetails = caputureLogDetails;
	}
	
//	public Tracker(Stage stage, String notes, boolean caputureLogDetails) {
//		this(stage, null, null, notes, caputureLogDetails);
//	}
	
	public static Tracker track(Stage stage, String notes, boolean caputureLogDetails){
		return new Tracker(stage, notes, caputureLogDetails);
    }
	
	public static Tracker track(Stage stage, boolean caputureLogDetails){
        return track(stage, null, caputureLogDetails);
    }
	
    public static Tracker track(Stage stage, String notes){
        return track(stage, notes, false);
    }
    public static Tracker track(Stage stage){
        return track(stage, false);
    }
    
	public void process(Exchange exchange) throws Exception {
		//set the properties in the exchange
        Map<String,Object> properties = exchange.getProperties();
        String entity = properties.get(ExchangePreProcessor.ENTITY_NAME)+"";
        String operation = properties.get(ExchangePreProcessor.OPERATION_NAME)+"";
        String coorelationId = properties.get(ExchangePreProcessor.CORRELATION_ID)+"";
        if(coorelationId == null || stage == null || entity == null || operation == null){
        	throw new RuntimeException("Cannot log in database. Required fields are missing");
        }
        log.debug("logging with tracker");
        if(coorelationId == null || stage == null || entity == null || operation == null){
        	throw new RuntimeException("Cannot log in database. Required fields are missing");
        }
		log.debug("creating new instance of IntegrationLog with [" + coorelationId+", " + stage+", " + entity+", " + operation+", " + notes + "]");

        IntegrationLog integrationLog = new IntegrationLog(coorelationId, stage, entity, operation, notes);
        String status = XPathBuilder.xpath("//status").evaluate(exchange, String.class);
        if (!StringUtils.isBlank(status)){
            integrationLog.setNotes(status);
        }

        IntegrationLogDao integrationLogDao = (IntegrationLogDao)exchange.getContext().getRegistry().lookup("integrationLogDao");
        if(caputureLogDetails){
        	//Check for soap fault
        	String faultString = XPathBuilder.xpath("//faultstring").evaluate(exchange, String.class);
        	if(!StringUtils.isBlank(faultString)){
        		integrationLog.setNotes(SOAP_FAULT_STATUS);
        		integrationLog.addIntegrationLogDetail(new IntegrationLogDetail(null, faultString, true));
        	}
        	
        	//check for caaers error message in response
        	String errorString = XPathBuilder.xpath("//error").evaluate(exchange, String.class);
        	if(!StringUtils.isBlank(errorString)){
        		integrationLog.setNotes(CAAERS_RESPONSE_ERROR);
        		integrationLog.addIntegrationLogDetail(new IntegrationLogDetail(null, errorString, true));
        	}
        	
        	//check for 'com:entityProcessingOutcomes'
        	NodeList nodes = XPathBuilder.xpath("//com:entityProcessingOutcomes")
                    .namespace("com", "http://schema.integration.caaers.cabig.nci.nih.gov/common")
                    .nodeResult()
                    .evaluate(exchange, NodeList.class);

        	if(nodes != null){

	        	for(int i=0 ; i<nodes.getLength() ; i++){
	        		Node outcome = nodes.item(i);
	        		if(StringUtils.equals( outcome.getLocalName(),"entityProcessingOutcome")){
		        		NodeList children = outcome.getChildNodes();
		        		String businessIdentifier = null;
		        		String outcomeMsg = null;
		        		boolean failed = false;
		        		for(int j=0 ; j<children.getLength() ; j++){
		            		Node child = children.item(j);
		            		String childLocalName = child.getLocalName();
		            		if(!StringUtils.isBlank(childLocalName) && childLocalName.equals("businessIdentifier")){
		            			businessIdentifier = child.getFirstChild().getNodeValue();
		            		}else if(!StringUtils.isBlank(childLocalName) && childLocalName.equals("message")){
		            			outcomeMsg = child.getFirstChild() != null ? child.getFirstChild().getNodeValue() : null ;
		            		}else if(!StringUtils.isBlank(childLocalName) && childLocalName.equals("failed")){
		            			failed = new Boolean(child.getFirstChild() != null ? child.getFirstChild().getNodeValue() : "false");
		            		}
		        		}
		        		if(businessIdentifier != null){
		        			integrationLog.addIntegrationLogDetail(new IntegrationLogDetail(businessIdentifier, outcomeMsg, failed));
		        		}
	        		}
	        	}
        	}
        }
        
        integrationLogDao.save(integrationLog);
        
	}
    
    
}
