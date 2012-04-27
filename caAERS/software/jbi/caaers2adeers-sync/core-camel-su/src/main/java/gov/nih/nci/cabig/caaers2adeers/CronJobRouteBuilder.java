package gov.nih.nci.cabig.caaers2adeers;

import gov.nih.nci.cabig.caaers2adeers.cronjob.PayloadGenerator;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Biju Joseph
 */
public class CronJobRouteBuilder implements InitializingBean {

    @Autowired
    PayloadGenerator payloadGenerator;

    private String agentLovCronExpression;
    private String asaelLovCronExpression;
    private String preConditionLovCronExpression;
    private String priorTherapyLovCronExpression;
    private String organizationLovCronExpression;
    private String deviceLovCronExpression;


    public void afterPropertiesSet() throws Exception {
        if(agentLovCronExpression == null) agentLovCronExpression = "0+0+1+*+*+?";  //1am
        if(asaelLovCronExpression == null) asaelLovCronExpression = "0+5+1+*+*+?";  //1:05am
        if(preConditionLovCronExpression == null) preConditionLovCronExpression = "0+10+1+*+*+?"; //1:10am
        if(priorTherapyLovCronExpression == null) priorTherapyLovCronExpression = "0+15+1+*+*+?"; //1:15am
        if(deviceLovCronExpression == null) deviceLovCronExpression = "0+20+1+*+*+?"; //1:20am
        if(organizationLovCronExpression == null) organizationLovCronExpression = "0+25+1+*+*+?"; //1:25am
    }


    public void configure(Caaers2AdeersRouteBuilder routeBuilder){
        routeBuilder.from("quartz://groupName/timerName/?cron="+agentLovCronExpression)
                .setBody(routeBuilder.constant(payloadGenerator.getAgentsRequest()))
                .to("direct:adEERSRequestSink");
    }


    public void setAgentLovCronExpression(String agentLovCronExpression) {
        this.agentLovCronExpression = agentLovCronExpression;
    }

    public void setAsaelLovCronExpression(String asaelLovCronExpression) {
        this.asaelLovCronExpression = asaelLovCronExpression;
    }

    public void setPreConditionLovCronExpression(String preConditionLovCronExpression) {
        this.preConditionLovCronExpression = preConditionLovCronExpression;
    }

    public void setPriorTherapyLovCronExpression(String priorTherapyLovCronExpression) {
        this.priorTherapyLovCronExpression = priorTherapyLovCronExpression;
    }

    public void setOrganizationLovCronExpression(String organizationLovCronExpression) {
        this.organizationLovCronExpression = organizationLovCronExpression;
    }

    public void setDeviceLovCronExpression(String deviceLovCronExpression) {
        this.deviceLovCronExpression = deviceLovCronExpression;
    }
}
