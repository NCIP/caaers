package gov.nih.nci.cabig.caaers.service.security.user;

import gov.nih.nci.cabig.caaers.domain._User;

public class Credential {
    private String _userName;

    private String _password;
    
    private _User user;

    public Credential(String userName, String password) {
        _userName = userName;
        _password = password;
    }

    public String getPassword() {
        return _password;
    }

    public String getUserName() {
        return _userName;
    }

	public _User getUser() {
		return user;
	}

	public void setUser(_User user) {
		this.user = user;
	}
}
