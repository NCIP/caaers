package gov.nih.nci.cabig.caaers2adeers;

import static gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage.REQUEST_RECEIVED;
import static gov.nih.nci.cabig.caaers2adeers.track.Tracker.track;
import gov.nih.nci.cabig.caaers2adeers.cronjob.EntityOperation;
import gov.nih.nci.cabig.caaers2adeers.track.FileTracker;

import java.util.Map;

import org.apache.camel.builder.xml.XPathBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Biju Joseph
 */
public class CronJobRouteBuilder implements InitializingBean {

    @Autowired
    private FileTracker fileTracker;


    Map<EntityOperation, String> cronExpressions;

    public void afterPropertiesSet() throws Exception {
    	for(EntityOperation entityOperation : EntityOperation.values()){
    		if(cronExpressions.get(entityOperation) == null){
    			cronExpressions.put(entityOperation, entityOperation.getCronJobExpression());
    		}
    	}
    }

    public void configure(Caaers2AdeersRouteBuilder routeBuilder){
    	XPathBuilder xPathBuilder = new XPathBuilder("//payload");
    	for(EntityOperation entityOperation : EntityOperation.values()){

	        routeBuilder.from("quartz://c2a/" + entityOperation.getQualifiedName() + "/?cron="+cronExpressions.get(entityOperation))
	                .setBody(routeBuilder.constant("<m>"+ entityOperation.name() + "</m>") )
                    .processRef("payloadGenerator")
                    .split(xPathBuilder).convertBodyTo(String.class).to("log:afterSplitting-")
                    .processRef("exchangePreProcessor").processRef("headerGeneratorProcessor")
                    .process(track(REQUEST_RECEIVED))
                    .to(fileTracker.fileURI(REQUEST_RECEIVED))
                    .to("direct:adEERSRequestSink");
    	}
    }

	public void setCronExpressions(Map<EntityOperation, String> cronExpressions) {
		this.cronExpressions = cronExpressions;
	}

}
