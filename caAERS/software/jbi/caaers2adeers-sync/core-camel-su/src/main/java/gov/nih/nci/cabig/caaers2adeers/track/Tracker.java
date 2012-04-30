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

public class Tracker implements Processor{
	
	protected static final Log log = LogFactory.getLog(Tracker.class);
	
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
        IntegrationLogDao integrationLogDao = (IntegrationLogDao)exchange.getContext().getRegistry().lookup("integrationLogDao");
        if(caputureLogDetails){
        	NodeList nodes = XPathBuilder.xpath("//com:entityProcessingOutcomes").namespace("com", "http://schema.integration.caaers.cabig.nci.nih.gov/common").nodeResult().evaluate(exchange, NodeList.class);
        	if(nodes != null){
        		String status = XPathBuilder.xpath("//status").evaluate(exchange, String.class);
        		if (!StringUtils.isBlank(status)){
        			integrationLog.setNotes(status);
        		}
	        	for(int i=0 ; i<nodes.getLength() ; i++){
	        		Node outcome = nodes.item(i);
	        		if(!StringUtils.isBlank(outcome.getLocalName()) && outcome.getLocalName().equals("entityProcessingOutcome")){
		        		NodeList children = outcome.getChildNodes();
		        		String businessIdentifier = null;
		        		String outcomeMsg = null;
		        		for(int j=0 ; j<children.getLength() ; j++){
		            		Node child = children.item(j);
		            		String childLocalName = child.getLocalName();
		            		if(!StringUtils.isBlank(childLocalName) && childLocalName.equals("businessIdentifier")){
		            			businessIdentifier = child.getFirstChild().getNodeValue();
		            		}else if(!StringUtils.isBlank(childLocalName) && childLocalName.equals("message")){
		            			outcomeMsg = child.getFirstChild() != null ? child.getFirstChild().getNodeValue() : null ;
		            		}
		        		}
		        		if(businessIdentifier != null){
		        			integrationLog.addIntegrationLogDetail(new IntegrationLogDetail(businessIdentifier, outcomeMsg));
		        		}
	        		}
	        	}
        	}
        }
        
        integrationLogDao.save(integrationLog);
        
	}
    
    
}
