/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;

import javax.jbi.messaging.NormalizedMessage;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class PropertyBasicAuthenticationTransformer implements
		BasicAuthenticationTransformer {

	/**
	 * The NormalizedMessage property that will contain the username.
	 */
	private String usernameProperty;

	/**
	 * The NormalizedMessage property that will contain the password
	 */
	private String passwordProperty;

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ctms.demo.BasicAuthenticationTransformer#transform(javax.jbi.messaging.NormalizedMessage)
	 */
	public BasicAuthenticationCredential transform(NormalizedMessage inMsg)
			throws InvalidCredentialException {

		BasicAuthenticationCredential bac = new BasicAuthenticationCredential();

		String username = null;
		try {
			username = (String) inMsg.getProperty(getUsernameProperty());
		} catch (ClassCastException ex) {
			throw new InvalidCredentialException("username is of wrong type: "
					+ inMsg.getProperty(getUsernameProperty()).getClass()
							.getName());
		} catch (Exception ex) {
			throw new RuntimeException("Error retrieving username: "
					+ ex.getMessage(), ex);
		}
		if (username == null) {
			throw new InvalidCredentialException("username not found");
		}

		String password = null;
		try {
			password = (String) inMsg.getProperty(getPasswordProperty());
		} catch (ClassCastException ex) {
			throw new InvalidCredentialException("password is of wrong type: "
					+ inMsg.getProperty(getPasswordProperty()).getClass()
							.getName());
		} catch (Exception ex) {
			throw new RuntimeException("Error retrieving password: "
					+ ex.getMessage(), ex);
		}
		if (password == null) {
			throw new InvalidCredentialException("password not found");
		}
		
		bac.setUserId(username);
		bac.setPassword(password);

		return bac;
	}

	public String getPasswordProperty() {
		return passwordProperty;
	}

	public void setPasswordProperty(String passwordProperty) {
		this.passwordProperty = passwordProperty;
	}

	public String getUsernameProperty() {
		return usernameProperty;
	}

	public void setUsernameProperty(String usernameProperty) {
		this.usernameProperty = usernameProperty;
	}

}
