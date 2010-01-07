package gov.nih.nci.cabig.caaers.esb.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {

	public String cxfbcUserPassword;

	public String cxfbcSignerPassword;

	static Logger logger = LogManager.getLogger(ClientPasswordCallback.class);

	/*
	 * Gets called based on the action set in the WSS4JOutInterceptor
	 * 
	 * @see
	 * javax.security.auth.callback.CallbackHandler#handle(javax.security.auth
	 * .callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		logger.debug("**********PASSWORD VALIDATOR BEAN CALLED**********");

		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

			int usage = pc.getUsage();


			if (usage == WSPasswordCallback.USERNAME_TOKEN) {
				logger.debug("USERNAME PASSWORD BLOCK");
				// username token pwd...
				pc.setPassword(cxfbcUserPassword);
			} else if (usage == WSPasswordCallback.SIGNATURE) {
				logger.debug("Signature BLOCK");
				// set the password for client's keystore.keyPassword
				pc.setPassword(cxfbcSignerPassword);
			}
		}

	}
	
	public String getCxfbcUserPassword() {
		return cxfbcUserPassword;
	}

	public void setCxfbcUserPassword(String cxfbcUserPassword) {
		this.cxfbcUserPassword = cxfbcUserPassword;
	}

	public String getCxfbcSignerPassword() {
		return cxfbcSignerPassword;
	}

	public void setCxfbcSignerPassword(String cxfbcSignerPassword) {
		this.cxfbcSignerPassword = cxfbcSignerPassword;
	}




}
