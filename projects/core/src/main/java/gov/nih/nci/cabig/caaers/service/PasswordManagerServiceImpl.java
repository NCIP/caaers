package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.User;

import java.util.Date;
import java.sql.Timestamp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceImpl implements PasswordManagerService {

    private static final long TOKEN_TIMEOUT_MS = 48*60*60*1000;

    private static final String HASH_ALGORITHM = "MD5";
    private static final String FORCED_CHARSET = "UTF-8";

    public String requestToken(User user) throws CaaersSystemException {
	/* if user exists {
	   user.token_time = TimeStamp(new Date().getTime()); */
	return hash("random_string"); // user.salt + random_string
	/* } else throw new CaaersSystemException("User does not exist"); */
    }

    public void setPassword(User user, String password, String token) throws CaaersSystemException {
	/* if user exists {
	     earliest_token_time = TimeStamp(new Date().getTime() - TOKEN_TIMEOUT_MS);
	     if (user.token_time.after(earliest_token_time)) {
	       if (token == user.token) {
	         password = hash(user.salt + password); 
	         if (password not in user.password_history && !password.equals(user.password)) {
		   user.password_history.add(password)
		   user.password = password
		 } else throw new CaaersSystemException("User password invalid: violation of password recycling policy");
	       } else throw new CaaersSystemException("Invalid set password token.");
	     } else throw new CaaersSystemException("Set password token has timed out.");
	   } else throw new CaaersSystemException("User does not exist.");
	*/
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
