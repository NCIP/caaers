package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class PasswordPolicy {

    private PasswordCreationPolicy _passwordCreationPolicy;
    private LoginPolicy _loginPolicy;
    private String _hashAlgorithm;

    public PasswordCreationPolicy getPasswordCreationPolicy() {
	return _passwordCreationPolicy;
    }

    public void setPasswordCreationPolicy(PasswordCreationPolicy passwordCreationPolicy) {
	_passwordCreationPolicy = passwordCreationPolicy;
    }
    
    public LoginPolicy getLoginPolicy() {
	return _loginPolicy;
    }

    public void setLoginPolicy(LoginPolicy loginPolicy) {
	_loginPolicy = loginPolicy;
    }

    public String getHashAlgorithm() {
	return _hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
	_hashAlgorithm = hashAlgorithm;
    }
}
