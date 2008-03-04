package gov.nih.nci.cabig.caaers.service.security.user;

public class Credential {
    private String _userName;

    private String _password;

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
}
