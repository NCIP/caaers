/**
 * 
 */
package gov.nih.nci.ctms.demo.comp;

import java.io.ByteArrayInputStream;

import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import javax.jbi.messaging.DeliveryChannel;
import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.InOut;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.xml.transform.stream.StreamSource;

import org.apache.servicemix.jbi.jaxp.SourceTransformer;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class MockGridServiceProcessor implements GridServiceProcessor {

	
	public void process(MessageExchange exchange, DeliveryChannel channel,
			ServiceSecurityClient client) throws Exception {


		if (exchange.getRole() == MessageExchange.Role.PROVIDER) {

			if (exchange instanceof InOut == false) {
				throw new UnsupportedOperationException("Unsupported MEP: "
						+ exchange.getPattern());
			}
			if (exchange.getStatus() == ExchangeStatus.ACTIVE) {
				if (exchange.getMessage("in") != null) {

					NormalizedMessage in = exchange.getMessage("in");

					String inMsg = new SourceTransformer()
							.contentToString(in);

					NormalizedMessage out = exchange.createMessage();
					out.setContent(new StreamSource(new ByteArrayInputStream(
							inMsg.getBytes())));
					exchange.setMessage(out, "out");
					channel.send(exchange);
		
				} else if (exchange.getFault() != null) {
					exchange.setStatus(ExchangeStatus.DONE);
					channel.send(exchange);
				} else {
					throw new IllegalStateException(
							"Provider exchange is ACTIVE, but no in or fault is provided");
				}
			}

		} else if (exchange.getRole() == MessageExchange.Role.CONSUMER) {
			throw new UnsupportedOperationException(
					"This component does not send messages.");
		} else {
			throw new IllegalStateException("Unkown role: "
					+ exchange.getRole());
		}

	}

}
