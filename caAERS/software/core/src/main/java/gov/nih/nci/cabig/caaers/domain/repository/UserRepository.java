/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.query.UserQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

public interface UserRepository {

	public _User getUserByLoginName(String loginName);
	public void createOrUpdateUser(_User user,String changeURL);
	public void userChangePassword(_User user, String password, int maxHistorySize);
	public void save(_User user);
	public void sendUserEmail(String emailAddress, String subject, String text);
	public void unlockUser(_User user);
	public List searchCsmUser(String firstName,String lastName,String userName);
	public boolean loginIDInUse(String loginId);
	public List<UserGroupType> getUserGroups(String loginName);

    /**
     * Will provision the user. 
     * @param user
     */
    public void provisionUser(_User user);

    public List<_User> search(UserQuery query);


    /**
     * This method provisions all the SuiteRoleMemebrships for a given User in CSM.
     * @param csmUser
     * @param roleMemberships
     */
    void provisionRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User csmUser, List<SuiteRoleMembership> roleMemberships);
    

}
