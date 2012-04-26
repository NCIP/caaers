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

	public void save(IntegrationLog integrationLog) throws Exception {
		hibernateTemplate.save(integrationLog);
	}
	
}
