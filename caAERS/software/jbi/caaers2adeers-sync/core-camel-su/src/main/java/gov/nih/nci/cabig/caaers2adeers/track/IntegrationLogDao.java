package gov.nih.nci.cabig.caaers2adeers.track;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Will record the status of each step in the database
 */
public class IntegrationLogDao{
	
    protected static final Log log = LogFactory.getLog(IntegrationLogDao.class);
    
    public static final String DATE = "1900-01-01T01:01:10";
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(IntegrationLog integrationLog) throws Exception {
		hibernateTemplate.save(integrationLog);
	}
	
	public String findLastRequestCompletedDatetime(final String entity){

		String query = "select max(loggedOn) from IntegrationLog where stage=:stage and entity =:entity";

		List<Object> result = hibernateTemplate.findByNamedParam(query,
                new String[]{"stage", "entity"} , new Object[]{Stage.REQUEST_COMPLETION, entity});

        if(result == null || result.isEmpty()) return DATE;

        if(result.get(0) == null) return DATE;
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format((Date)result.get(0));
        
	}
	
	public List findInactiveOrganizationCTEPIds(){
		return hibernateTemplate.executeFind(new HibernateCallback() {
			
			public List doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sqlQuery= session.createSQLQuery("Select nci_institute_code from organizations where retired_indicator='1' and merged_org_id is null");
				return sqlQuery.list();
			}
		});
	}
	
}
