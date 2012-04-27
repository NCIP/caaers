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
	
	public Map<EntityOperation, String> findLastRequestCompletedDatetime(){
		Map<EntityOperation, String> returnMap = new HashMap<EntityOperation, String>();
		String query = "select entity, max(loggedOn) from IntegrationLog where stage=:stage group by entity";
		List<Object[]> result = hibernateTemplate.findByNamedParam(query, "stage", Stage.REQUEST_COMPLETION);
		for(EntityOperation entityOperation : EntityOperation.values()){
			Date date = new GregorianCalendar(1900, 01,01).getTime();;
			for(Object[] values : result){
				String entity = (String)values[0];
				if(entity.equals(entityOperation.getQualifiedName())){
					if(values[1] != null){
						date = (Date)values[1];
					}
					break;
				}
			}
			returnMap.put(entityOperation, new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss").format(date));
		}
		return returnMap;
	}
	
}
