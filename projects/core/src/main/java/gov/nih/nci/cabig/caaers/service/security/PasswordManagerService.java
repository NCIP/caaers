package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.User;


/**
 * @author Jared Flatow
 */
public interface PasswordManagerService {
    /**
     * Creates a token that can be used to set the password of the specified user. There is no
     * guarantee the token will be valid at the time of setting the password.
     * 
     * @return a User object containing the newly generated token
     * @throws CaaersSyystemException
     *                 if the user does not exist.
     */
    public User requestToken(String userName) throws CaaersSystemException;

    /**
     * Set the password for the specified user.
     * 
     * @param token
     *                should have been generated using requestToken on the same user.
     * @throws CaaersSystemException
     *                 if the password could not be set for the user.
     */
    public void setPassword(String userName, String password, String token)
                    throws CaaersSystemException;
}
