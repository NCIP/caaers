package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.User;

/**
 * This service interface is used to manage user access to the caaers UI.
 */
public interface CSMUserRepository {
    /**
     * Create the csm users and groups for newly created research staff or updates csm users/groups
     * if research staff already exists.
     *
     * @param researchStaff the research staff for which csm users and groups to be created or updated
     * @throws CaaersSystemException if CSM user can not be created.
     */
    void createOrUpdateCSMUserAndGroupsForResearchStaff(
            gov.nih.nci.cabig.caaers.domain.ResearchStaff researchStaff, String changeURL);
    /**
     * Creates a csm user and groups for the newly created investigator. 
     * Or
     * Updates the csm user groups for an existing investigator.
     * 
     * @param investigator
     * @param changeURL
     */
    void createOrUpdateCSMUserAndGroupsForInvestigator(Investigator investigator, String changeURL);
    
    /**
     * Returns the user if it is available 
     * @param userName
     * @return
     */
    public User getUserByName(String userName);

    public void userChangePassword(User user, String password, int maxHistorySize);

    public boolean userHasPassword(String userName, String password);

    public boolean userHadPassword(String userName, String password);
    
    public String userCreateToken(User user);

    public void sendUserEmail(String emailAddress, String subject, String text);
    
    public boolean loginIDInUse(String loginId);
  
}
