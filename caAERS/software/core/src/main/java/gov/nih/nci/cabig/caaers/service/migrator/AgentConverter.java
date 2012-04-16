package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -11:49 AM
 */
public class AgentConverter extends DomainObjectConverter{

    public static Agent convert(AgentType at) {
        Agent a = new Agent();
        a.setName(at.getName());
        a.setNscNumber(at.getNscNumber());
        a.setDescription(at.getDescriptionText());
        if(at.getStatus() != null){
			a.setRetiredIndicator(at.getStatus().name().equals("AC") ? false:true);
		}
        return a;
    }

    public static AgentType convert(Agent a) {
        AgentType at = new AgentType();
        at.setName(a.getName());
        at.setNscNumber(a.getNscNumber());
        at.setDescriptionText(a.getDescription());
        return at;
    }

}
