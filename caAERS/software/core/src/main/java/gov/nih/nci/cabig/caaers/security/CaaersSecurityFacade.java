package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.User;
import org.acegisecurity.Authentication;

import java.util.List;

/**
 * 
 * The facade layer to CSM.
 *
 * @author: Biju Joseph
 */
public interface CaaersSecurityFacade {

    /**
     * Will provision user in CSM, ie. will only populate the role associations and protection group association.
     * 
     * @param user - The logged-in user.
     */
    void provisionUser(User user);

    /**
     * Will create or update a csm user.
     * @param csmUser - A user defined in CSM.
     */
    void createOrUpdateCSMUser(User user, String changeURL );


    /**
     * Will check the authorization status.
     * @param auth - The acegi authentication object
     * @param objectId - The secure object Id
     * @param privilege - The privilege (CREATE,UPDATE)
     * @return
     */
    boolean checkAuthorization(Authentication auth, String objectId, String privilege);

    /**
     * Will get the accessible protection element Ids (ObjectIDs) for the login. 
     * @param loginId - The loginId
     * @return
     */
    List<String> getAccessibleProtectionElements(String loginId);

    /**
     * Will the caAERS database IDs of Study that one can access. 
     * @param loginId - The loginId
     * @return
     */
    List<Integer> getAccessibleStudyIds(String loginId);

    /**
     * Will the caAERS database IDs of Organization that one can access. 
     * @param loginId - The loginId
     * @return
     */
    List<Integer> getAccessibleOrganizationIds(String loginId);



}
