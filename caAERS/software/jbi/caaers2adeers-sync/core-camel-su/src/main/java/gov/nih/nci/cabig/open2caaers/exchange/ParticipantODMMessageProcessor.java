/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.open2caaers.exchange;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RuntimeExpressionException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.codec.binary.Base64;

public class ParticipantODMMessageProcessor implements Processor {
	
	public static final String SYNC_HEADER = "sync_mode";
	public static final String CORRELATION_ID = "c2a_correlation_id";
	public static final String OPERATION_NAME = "c2a_operation";
	public static final String ENTITY_NAME = "c2a_entity";
    public static final String CAAERS_WS_USERNAME = "c2a_caaers_ws_username";
    public static final String CAAERS_WS_PASSWORD = "c2a_caaers_ws_password";

    //user name and password to use while invoking caAERS
	private String caaersWSUser;
    private String caaersWSPassword;

	public String getCaaersWSUser() {
		return caaersWSUser;
	}

	public void setCaaersWSUser(String caaersWSUser) {
		this.caaersWSUser = caaersWSUser;
	}

	public String getCaaersWSPassword() {
		return caaersWSPassword;
	}

	public void setCaaersWSPassword(String caaersWSPassword) {
		this.caaersWSPassword = caaersWSPassword;
	}

	protected static final Log log = LogFactory
			.getLog(ParticipantODMMessageProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		// check authentication
		checkAuthentication(exchange);

		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		properties.put(OPERATION_NAME, "createParticipant");
		properties.put(ENTITY_NAME, "participant");
		properties.put(SYNC_HEADER, "sync");
        properties.put(CAAERS_WS_USERNAME, caaersWSUser);
        properties.put(CAAERS_WS_PASSWORD, caaersWSPassword);

		log.debug("adding correlationId.");
		Object correlationId = System.currentTimeMillis() + "";
		properties.put(CORRELATION_ID, correlationId);

		if (log.isDebugEnabled())
			log.debug("Exchange properties :" + String.valueOf(properties));

		exchange.getOut().setBody(body);
	}

	private void checkAuthentication(Exchange exchange) {

		// get the authorization header
		String authorizationString = (String) exchange.getIn().getHeader(
				"Authorization");
		if (StringUtils.isBlank(authorizationString)) {
			log.debug("Could not find username/password in the request header");
			throw new RuntimeExpressionException("No Authentication found");
		}

		try {
			String[] authInfoSplit = authorizationString.split("\\s+");

			// decode and validate the authorization header
			Base64 decoder = new Base64();
			byte[] decodedAuth = decoder.decode(authInfoSplit[1].getBytes());

			String usernamePassword = new String(decodedAuth, "UTF-8");
			if ((!usernamePassword.split(":")[0].equals(this.caaersWSUser))
					|| !usernamePassword.split(":")[1]
							.equals(this.caaersWSPassword)) {
				throw new RuntimeExpressionException("Invalid Authentication");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeExpressionException("Invalid Authentication");
		}

	}
}