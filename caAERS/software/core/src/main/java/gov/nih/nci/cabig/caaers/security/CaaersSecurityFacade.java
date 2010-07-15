package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.acegisecurity.Authentication;

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
     * Will check the authorization status
     * @param auth   - An authentication object
     * @param objectPrivilege  - An object privilege (Eg: - "Study:READ || Study:UPDATE)
     * @return
     */
    boolean checkAuthorization(Authentication auth, String objectPrivilege);


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
    List<IndexEntry> getAccessibleStudyIds(String loginId);

    /**
     * Will the caAERS database IDs of Organization that one can access. 
     * @param loginId - The loginId
     * @return
     */
    List<IndexEntry> getAccessibleOrganizationIds(String loginId);

	/**
	 * Obtains roles of the user with the given loginId on the given organization.
	 * @param userLoginName
	 * @param org
	 * @return
	 */
	Collection<String> getRoles(String userLoginName, Organization org);
	
	/**
	 * Obtains roles of the user with the given loginId on the given study.
	 * @param userLoginName
	 * @param study
	 * @return
	 */
	Collection<String> getRoles(String userLoginName, Study study);
	
	/**
     * Will get all ProtectionGroupRoleContext for the user 
     * @param loginId
     * @return
     * @throws CSObjectNotFoundException
     */
    Set<ProtectionGroupRoleContext> getProtectionGroupRoleContextForUser(String loginId) throws CSObjectNotFoundException ;
    
    /**
     * Will get all ProtectionElementPrivilegeContext for the user 
     * @param loginId
     * @return
     * @throws CSObjectNotFoundException
     */
    Set<ProtectionElementPrivilegeContext> getProtectionElementPrivilegeContextForUser(String loginId) throws CSObjectNotFoundException ;
    
    /*
     * Clears user cache by login Name 
     */
    void clearUserCache(String userName);
    /**
     * Will provision studies for ResearchStaff in CSM.
     * @param researchStaff
     */
    void provisionStudiesForResearchStaff(ResearchStaff researchStaff);
    
    /**
     * Will provision studies for Investigator in CSM.
     * @param investigator
     */
    void provisionStudiesForInvestigator(Investigator investigator);

}
