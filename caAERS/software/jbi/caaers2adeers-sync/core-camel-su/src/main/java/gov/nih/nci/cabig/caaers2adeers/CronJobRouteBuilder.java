package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.caaers2adeers.cronjob.EntityOperation;
import gov.nih.nci.cabig.caaers2adeers.cronjob.PayloadGenerator;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLogDao;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Biju Joseph
 */
public class CronJobRouteBuilder implements InitializingBean {

    @Autowired
    PayloadGenerator payloadGenerator;
    
    @Autowired
    IntegrationLogDao integrationLogDao;
    
    Map<EntityOperation, String> cronExpressions;

    public void afterPropertiesSet() throws Exception {
    	for(EntityOperation entityOperation : EntityOperation.values()){
    		if(cronExpressions.get(entityOperation) == null){
    			cronExpressions.put(entityOperation, entityOperation.getCronJobExpression());
    		}
    	}
    }

    public void configure(Caaers2AdeersRouteBuilder routeBuilder){
    	Map<EntityOperation, String> entityMap = integrationLogDao.findLastRequestCompletedDatetime();
    	for(EntityOperation entityOperation : entityMap.keySet()){
	        routeBuilder.from("quartz://caaersSync/"+entityOperation.toString()+"_timer/?cron="+cronExpressions.get(entityOperation))
	                .setBody(routeBuilder.constant(payloadGenerator.getRequest("adeers", entityOperation.getQualifiedName(), 
	                		entityOperation.getOperationName(), entityOperation.getMode(), entityMap.get(entityOperation))))
	                .to("direct:adEERSRequestSink");
    	}
    }

	public void setCronExpressions(Map<EntityOperation, String> cronExpressions) {
		this.cronExpressions = cronExpressions;
	}

}
