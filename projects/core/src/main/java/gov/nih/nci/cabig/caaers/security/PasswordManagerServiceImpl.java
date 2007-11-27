package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.user.CaaersUser;
import gov.nih.nci.cabig.caaers.user.Credential;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.Date;
import java.sql.Timestamp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceImpl implements PasswordManagerService {

    // store these in PasswordPolicy
    private static final long TOKEN_TIMEOUT_MS = 48*60*60*1000;
    private static final String HASH_ALGORITHM = "MD5";
    private static final String FORCED_CHARSET = "UTF-8";

    public static final PasswordManagerService Singleton = new PasswordManagerServiceImpl();
    private PasswordManagerServiceImpl() {}

    public String requestToken(String userName) throws CaaersSystemException {
	CaaersUser user = null; // ClassImplementingCaaersUser.getUser(userName);
	user.setTokenTime(new Timestamp(new Date().getTime()));
	user.setToken(hash(user.getSalt() + user.getTokenTime().toString() + "random_string"));
	return user.getToken();
    }

    public void setPassword(String userName, String password, String token) throws CaaersSystemException {
	CaaersUser user = null; // ClassImplementingCaaersUser.getUser(userName);
	Timestamp earliestTokenTime = new Timestamp(new Date().getTime() - TOKEN_TIMEOUT_MS);
	if (user.getTokenTime().after(earliestTokenTime)) {
	    if (token.equals(user.getToken())) {
		if (PasswordPolicyServiceImpl.Singleton.validatePasswordAgainstCreationPolicy(new Credential(user, password))) { // credential take user
		    user.addPasswordToHistory(PasswordPolicyServiceImpl.Singleton.getPasswordPolicy().getPasswordCreationPolicy().getPasswordHistorySize());
		    user.setPassword(hash(user.getSalt() + password));
		    user.setTokenTime(earliestTokenTime);
		}
	    } else throw new CaaersSystemException("Invalid set password token.");
	} else throw new CaaersSystemException("Set password token has timed out.");
    }

    private String hash(String string) throws CaaersSystemException {
	try {
	    return new String(MessageDigest.getInstance(HASH_ALGORITHM).digest(string.getBytes(FORCED_CHARSET)), FORCED_CHARSET);
	} catch (NoSuchAlgorithmException e) {
	    throw new CaaersSystemException(HASH_ALGORITHM + " not supported on system.");
	} catch (UnsupportedEncodingException e) {
	    throw new CaaersSystemException(FORCED_CHARSET + " not supported on system.");
	}
    }
}
