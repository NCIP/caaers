package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.List;

 
/**
 * This service interface is used to manage user access to the caaers UI.
 */
public interface CSMUserRepository {
    
    /**
     * Create the csm users and groups for newly created research staff or updates csm users/groups
     * if research staff already exists.
     *
     * @param researchStaff the research staff for which csm users and groups to be created or updated
     * @param changeURL the change url
     */
    void createOrUpdateCSMUserAndGroupsForResearchStaff(gov.nih.nci.cabig.caaers.domain.ResearchStaff researchStaff, String changeURL);
    
    /**
     * Creates a csm user and groups for the newly created investigator.
     * Or
     * Updates the csm user groups for an existing investigator.
     *
     * @param investigator the investigator
     * @param changeURL the change url
     */
    void createOrUpdateCSMUserAndGroupsForInvestigator(Investigator investigator, String changeURL);
    
    /**
     * Returns the user if it is available.
     *
     * @param userName the user name
     * @return the user by name
     */
    public User getUserByName(String userName);
    
    /**
     * Fetches the groups associated to users.
     *
     * @param userName the user name
     * @return the user groups
     */
    public List<UserGroupType> getUserGroups(String userName);

    /**
     * User change password.
     *
     * @param user the user
     * @param password the password
     * @param maxHistorySize the max history size
     */
    public void userChangePassword(User user, String password, int maxHistorySize);

    /**
     * User has password.
     *
     * @param userName the user name
     * @param password the password
     * @return true, if successful
     */
    public boolean userHasPassword(String userName, String password);

    /**
     * User had password.
     *
     * @param userName the user name
     * @param password the password
     * @return true, if successful
     */
    public boolean userHadPassword(String userName, String password);
    
    /**
     * User create token.
     *
     * @param user the user
     * @return the string
     */
    public String userCreateToken(User user);

    /**
     * Send user email.
     *
     * @param emailAddress the email address
     * @param subject the subject
     * @param text the text
     */
    public void sendUserEmail(String emailAddress, String subject, String text);
    
    /**
     * Login id in use.
     *
     * @param loginId the login id
     * @return true, if successful
     */
    public boolean loginIDInUse(String loginId);

    /**
     * Gets the cSM user by name.
     *
     * @param userName the user name
     * @return the cSM user by name
     */
    public gov.nih.nci.security.authorization.domainobjects.User getCSMUserByName(String userName);
    
    /**
     * Search csm user.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user name
     * @return the list
     */
    public List searchCsmUser(String firstName,String lastName,String userName);
  
}
