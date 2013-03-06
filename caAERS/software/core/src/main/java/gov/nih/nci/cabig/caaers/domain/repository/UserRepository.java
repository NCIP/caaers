/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.query.UserQuery;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

 
/**
 * The Interface UserRepository.
 */
public interface UserRepository {

	/**
	 * Gets the user by login name.
	 *
	 * @param loginName the login name
	 * @return the user by login name
	 */
	public User getUserByLoginName(String loginName);
	
	/**
	 * Creates the or update user.
	 *
	 * @param user the user
	 * @param changeURL the change url
	 */
	public void createOrUpdateUser(User user,String changeURL);
	
	/**
	 * User change password.
	 *
	 * @param user the user
	 * @param password the password
	 * @param maxHistorySize the max history size
	 */
	public void userChangePassword(User user, String password, int maxHistorySize);
	
	/**
	 * Save.
	 *
	 * @param user the user
	 */
	public void save(User user);
	
	/**
	 * Send user email.
	 *
	 * @param emailAddress the email address
	 * @param subject the subject
	 * @param text the text
	 */
	public void sendUserEmail(String emailAddress, String subject, String text);
	
	/**
	 * Unlock user.
	 *
	 * @param user the user
	 */
	public void unlockUser(User user);
	
	/**
	 * Search csm user.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param userName the user name
	 * @return the list
	 */
	public List searchCsmUser(String firstName,String lastName,String userName);

    public List searchCsmUser(String name);

	/**
	 * Login id in use.
	 *
	 * @param loginId the login id
	 * @return true, if successful
	 */
	public boolean loginIDInUse(String loginId);
	
	/**
	 * Gets the user groups.
	 *
	 * @param csmUserId CSMUser's id
	 * @return the user groups
	 */
	public List<UserGroupType> getUserGroups(String csmUserId);

    /**
     * Will provision the user.
     *
     * @param user the user
     */
    public void provisionUser(User user);

    /**
     * Search.
     *
     * @param query the query
     * @return the list
     */
    public List<User> search(UserQuery query);


    /**
     * This method provisions all the SuiteRoleMemebrships for a given User in CSM.
     *
     * @param csmUser the csm user
     * @param roleMemberships the role memberships
     */
    void provisionRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User csmUser, List<SuiteRoleMembership> roleMemberships);
    

}
