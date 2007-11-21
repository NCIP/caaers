package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class PasswordPolicy {

	private PasswordCreationPolicy passwordCreationPolicy;
	private LoginPolicy loginPolicy;

	public PasswordPolicy(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	public PasswordCreationPolicy getPasswordCreationPolicy() {
		return passwordCreationPolicy;
	}

	public void setPasswordCreationPolicy(
			PasswordCreationPolicy passwordCreationPolicy) {
		this.passwordCreationPolicy = passwordCreationPolicy;
	}

	public LoginPolicy getLoginPolicy() {
		return loginPolicy;
	}

	public void setLoginPolicy(LoginPolicy loginPolicy) {
		this.loginPolicy = loginPolicy;
	}

}
