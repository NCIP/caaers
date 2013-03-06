/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Will provide the password for AdEERSWebservice and caAERS Webservice
 * 
 * @author Biju Joseph
 */
public class CaaersWsseValidationPasswordCallback implements CallbackHandler {
	private String caaersWSUser;
	private String caaersWSPassword;

	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		org.apache.ws.security.WSPasswordCallback pc = (org.apache.ws.security.WSPasswordCallback) callbacks[0];
		if (equals(pc.getIdentifier(), caaersWSUser)) {
			if (!equals(pc.getPassword(), caaersWSPassword)) {
				throw new IOException("invalid password- ["+pc.getPassword()+"]");
			}
		}else{
			throw new IOException("invalid username- ["+pc.getIdentifier()+"]");
		}
	}

	private boolean equals(String a, String b) {
		if (a == null || b == null)
			return false;
		return a.equals(b);
	}

	public void setCaaersWSUser(String caaersWSUser) {
		this.caaersWSUser = caaersWSUser;
	}

	public void setCaaersWSPassword(String caaersWSPassword) {
		this.caaersWSPassword = caaersWSPassword;
	}
}
