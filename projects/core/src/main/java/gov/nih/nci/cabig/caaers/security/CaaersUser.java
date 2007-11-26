package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.Duration;

import java.sql.Timestamp;

public interface CaaersUser {
    // throw exception if doesn't exist
    public CaaersUser getUser(String userName) throws CaaersSystemException;
    public String getToken();
    public void setToken(String token);
    public Timestamp getTokenTime();
    public void setTokenTime(Timestamp timestamp);
    public void setPassword(String hashedPassword);
    public void addPasswordToHistory(int maxHistorySize);
    public boolean isPassword(String hashedPassword);
    public String getUserName();
    public Duration getPasswordAge();
    public int getFailedLoginAttempts();
    public void setFailedLoginAttempts(int attempts);
    public String getSalt();
    public void setSalt(String salt);
}
