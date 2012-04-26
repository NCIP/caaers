package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Will record the status of each step in the database
 */
public class IntegrationLogDao{
	
    public static final String TRACKER_STAGE_NAME_HEADER = "c2a_tracker_stage_name";
    public static final String TRACKER_NOTES_HEADER = "c2a_tracker_notes";
    
    protected static final Log log = LogFactory.getLog(IntegrationLogDao.class);
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void record(String coorelationId, Stage stage, String entity, String operationName, String notes) throws Exception {
		if(coorelationId == null || stage == null || entity == null || operationName == null){
        	throw new RuntimeException("Cannot log in database. Required fields are missing");
        }
		log.debug("creating new instance of IntegrationLog with [" + coorelationId+", " + stage+", " + entity+", " + operationName+", " + notes + "]");
		IntegrationLog integrationLog = new IntegrationLog(coorelationId, stage, entity, operationName, notes);
		hibernateTemplate.save(integrationLog);
	}
	
}
