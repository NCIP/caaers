package gov.nih.nci.cabig.caaers.user;

public class Credential {
    private CaaersUser _user;
    private String _password;
    
    public Credential(CaaersUser user, String password){
	_user = user;
	_password = password;
    }
    
    public String getPassword() {
	return _password;
    }
        
    public String getUserName() {
	return _user.getName();
    }
}
