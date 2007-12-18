package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

public interface UserService {

    // public void saveResearchStaff(ResearchStaff staff) throws CaaersSystemException;
    
    /**
     * Create the csm users and groups for newly created research staff or updates csm users/groups if research staff already exists.
     * @throws CaaersSystemException if CSM user can not be created.
     * @param researchStaff the research staff for which csm users and groups to be created or updated
     */
    void createOrUpdateCSMUserAndGroupsForResearchStaff(gov.nih.nci.cabig.caaers.domain.ResearchStaff researchStaff);
    
    public User getUserByName(String userName) throws CaaersSystemException;
    public void userChangePassword(String userName, String password, int maxHistorySize) throws CaaersSystemException;
    public boolean userHasPassword(String userName, String password) throws CaaersSystemException;
    public boolean userHadPassword(String userName, String password) throws CaaersSystemException;
    public String userCreateToken(String userName) throws CaaersSystemException;
    public void sendUserEmail(String userName, String message);
}
