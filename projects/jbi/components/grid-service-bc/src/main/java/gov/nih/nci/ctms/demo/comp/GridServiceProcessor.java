/**
 * 
 */
package gov.nih.nci.ctms.demo.comp;

import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import javax.jbi.messaging.DeliveryChannel;
import javax.jbi.messaging.MessageExchange;

/**
 * Processes a MessageExchange.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public interface GridServiceProcessor {
	
	void process(MessageExchange exchange, DeliveryChannel channel, ServiceSecurityClient client) throws Exception;

}
