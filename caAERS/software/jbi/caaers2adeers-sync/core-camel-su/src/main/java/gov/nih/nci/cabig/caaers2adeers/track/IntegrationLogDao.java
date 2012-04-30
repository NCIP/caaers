package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.cronjob.EntityOperation;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Will record the status of each step in the database
 */
public class IntegrationLogDao{
	
    protected static final Log log = LogFactory.getLog(IntegrationLogDao.class);
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(IntegrationLog integrationLog) throws Exception {
		hibernateTemplate.save(integrationLog);
	}
	
	public String findLastRequestCompletedDatetime(final String entity){

        String date = "1900-01-01T01:01:10";
		String query = "select max(loggedOn) from IntegrationLog where stage=:stage and entity =:entity";

		List<Object> result = hibernateTemplate.findByNamedParam(query,
                new String[]{"stage", "entity"} , new Object[]{Stage.REQUEST_COMPLETION, entity});

        if(result == null || result.isEmpty()) return date;

        if(result.get(0) == null) return date;
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format((Date)result.get(0));
        
	}
	
}
