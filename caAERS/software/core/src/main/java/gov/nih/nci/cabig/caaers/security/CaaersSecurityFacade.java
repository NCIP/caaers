/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import org.acegisecurity.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * 
 * The facade layer to CSM.
 *
 * @author: Biju Joseph
 */
public interface CaaersSecurityFacade {

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

    /*
     * Clears user cache by login Name 
     */
    void clearUserCache(String userName);
   
}
