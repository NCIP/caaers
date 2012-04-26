package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import java.util.Map;

import gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor;
import gov.nih.nci.cabig.caaers2adeers.track.Tracker;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TrackerPreProcessor implements Processor{
	
	// entity type
 	private String entity;
 	
 	// operation name
 	private String operation;
 	
 	// progress made by synch request
 	private Stage stage;
 	
 	// details 
 	private String notes;
 	
	public TrackerPreProcessor(Stage stage, String entity, String operation, String notes) {
		super();
		this.entity = entity;
		this.operation = operation;
		this.stage = stage;
		this.notes = notes;
	}
	
	public TrackerPreProcessor(Stage stage, String notes) {
		this(stage, null, null, notes);
	}
	
	public TrackerPreProcessor(Stage stage) {
		this(stage, null, null, null);
	}

	public void process(Exchange exchange) throws Exception {
		//set the properties in the exchange
        Map<String,Object> properties = exchange.getProperties();
        properties.put(Tracker.TRACKER_STAGE_NAME_HEADER, stage.name());
        if(entity != null){
        	properties.put(ExchangePreProcessor.ENTITY_NAME, entity);
        }
        if(operation != null){
        	properties.put(ExchangePreProcessor.OPERATION_NAME, operation);
        }
        if(notes != null) properties.put(Tracker.TRACKER_NOTES_HEADER, notes);
	}
}
