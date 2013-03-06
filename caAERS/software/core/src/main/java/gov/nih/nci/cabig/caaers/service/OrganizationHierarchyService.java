/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * Interface to determine organization hierarchy
 *
 */
public interface OrganizationHierarchyService {
	
	/**
	 * Should return the main member Organization. 
	 * @param site - Organization, a study site is representing.
	 * @param study - OPTIONAL
	 * @return
	 */
	Organization findParent(Organization site, Study study);
}
