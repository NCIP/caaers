package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.User;
import org.acegisecurity.Authentication;

import java.util.List;

/**
 * The Facade Layer to CSM. 
 * @author: Biju Joseph
 */
public class CaaersSecurityFacadeImpl implements CaaersSecurityFacade{

    /**
     * Will check the authorization status.
     *
     * @param auth      - The acegi authentication object
     * @param objectId  - The secure object Id
     * @param privilege - The privilege (CREATE,UPDATE)
     * @return
     */
    public boolean checkAuthorization(Authentication auth, String objectId, String privilege) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Will provision user in CSM, ie. will only populate the role associations and protection group association.
     *
     * @param user - The logged-in user.
     */
    public void provisionUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Will create or update a csm user.
     *
     * @param csmUser - A user defined in CSM.
     */
    public void createOrUpdateCSMUser(gov.nih.nci.security.authorization.domainobjects.User csmUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Will get the accessible protection element Ids (ObjectIDs) for the login.
     *
     * @param loginId - The loginId
     * @return
     */
    public List<String> getAccessibleProtectionElements(String loginId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Will the caAERS database IDs of Study that one can access.
     *
     * @param loginId - The loginId
     * @return
     */
    public List<Integer> getAccessibleStudyIds(String loginId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Will the caAERS database IDs of Organization that one can access.
     *
     * @param loginId - The loginId
     * @return
     */
    public List<Integer> getAccessibleOrganizationIds(String loginId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
