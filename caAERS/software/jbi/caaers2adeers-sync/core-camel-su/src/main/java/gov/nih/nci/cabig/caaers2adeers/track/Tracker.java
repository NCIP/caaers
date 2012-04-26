package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import java.util.Map;

import gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor;
import org.apache.camel.Exchange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Will record the status of each step in the database
 */
public class Tracker{
	
    public static final String TRACKER_STAGE_NAME_HEADER = "c2a_tracker_stage_name";
    public static final String TRACKER_NOTES_HEADER = "c2a_tracker_notes";
    
    protected static final Log log = LogFactory.getLog(Tracker.class);
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void record(Exchange exchange) throws Exception {
		IntegrationLog integrationLog = getInstance(exchange);
		hibernateTemplate.save(integrationLog);
	}
	
	private static IntegrationLog getInstance(Exchange exchange){
		//set the properties in the exchange
        Map<String,Object> properties = exchange.getProperties();
        String coorelationId = properties.get(ExchangePreProcessor.CORRELATION_ID)+"";
		Stage stage = Stage.valueOf(properties.get(TRACKER_STAGE_NAME_HEADER)+"");
		String entity = properties.get(ExchangePreProcessor.ENTITY_NAME)+"";
		String operationName = properties.get(ExchangePreProcessor.OPERATION_NAME)+"";
		String notes = properties.get(TRACKER_NOTES_HEADER) == null ? null : properties.get(TRACKER_NOTES_HEADER)+"";
        log.debug("creating new instance of IntegrationLog with [" + coorelationId+", " + stage+", " + entity+", " + operationName+", " + notes + "]");
        if(coorelationId == null || stage == null || entity == null || operationName == null){
        	throw new RuntimeException("Cannot log in database. Required fields are missing");
        }
        IntegrationLog integrationLog = new IntegrationLog(coorelationId, stage, entity, operationName, notes);
		return integrationLog;
	}
	
}
