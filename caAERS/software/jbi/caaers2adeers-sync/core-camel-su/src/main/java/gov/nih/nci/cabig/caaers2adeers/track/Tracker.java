package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class Tracker implements Processor{
	
	protected static final Log log = LogFactory.getLog(Tracker.class);
	
	// entity type
 	private String entity;
 	
 	// operation name
 	private String operation;
 	
 	// progress made by synch request
 	private Stage stage;
 	
 	// details 
 	private String notes;
 	
	public Tracker(Stage stage, String entity, String operation, String notes) {
		super();
		this.entity = entity;
		this.operation = operation;
		this.stage = stage;
		this.notes = notes;
	}
	
	public Tracker(Stage stage, String notes) {
		this(stage, null, null, notes);
	}
	
	public Tracker(Stage stage) {
		this(stage, null, null, null);
	}

    public static Tracker track(Stage stage, String notes){
        return new Tracker(stage, notes);
    }
    public static Tracker track(Stage stage){
        return new Tracker(stage);
    }

	public void process(Exchange exchange) throws Exception {
		//set the properties in the exchange
        Map<String,Object> properties = exchange.getProperties();
        properties.put(IntegrationLogDao.TRACKER_STAGE_NAME_HEADER, stage.name());
        if(entity == null){
        	entity = properties.get(ExchangePreProcessor.ENTITY_NAME)+"";
        }
        if(operation == null){
        	operation = properties.get(ExchangePreProcessor.OPERATION_NAME)+"";
        }
        String coorelationId = properties.get(ExchangePreProcessor.CORRELATION_ID)+"";
        if(coorelationId == null || stage == null || entity == null || operation == null){
        	throw new RuntimeException("Cannot log in database. Required fields are missing");
        }
        log.debug("logging with tracker");
        IntegrationLogDao integrationLogDao = (IntegrationLogDao)exchange.getContext().getRegistry().lookup("integrationLogDao");
        integrationLogDao.record(coorelationId, stage, entity, operation, notes);
	}
}
