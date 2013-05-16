/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.query.OrganizationFromStudySiteQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;

import java.util.List;

 
/**
 * This service interface is used to build the organization domain object.
 * 
 * 
 */
public interface OrganizationRepository {
    
    /**
     * Creates the.
     *
     * @param organization the organization
     */
    void create(Organization organization);
    
    /**
     * Creates the or update.
     *
     * @param organization the organization
     */
    void createOrUpdate(Organization organization);
    
    /**
     * Gets the organizations having study sites.
     *
     * @param query the query
     * @return the organizations having study sites
     */
    List<Organization> getOrganizationsHavingStudySites(final OrganizationFromStudySiteQuery query);
    
    /**
     * Gets the applicable organizations from study sites.
     *
     * @param text the text
     * @param studyId the study id
     * @return the applicable organizations from study sites
     */
    List<Organization> getApplicableOrganizationsFromStudySites(String text, Integer studyId);
    
    /**
     * Gets the applicable organizations from study organizations.
     *
     * @param text the text
     * @param studyId the study id
     * @return the applicable organizations from study organizations
     */
    public List<StudyOrganization> getApplicableOrganizationsFromStudyOrganizations(final String text, Integer studyId);
    
    /**
     * Convert to remote.
     *
     * @param localOrganization the local organization
     * @param remoteOrganization the remote organization
     */
    void convertToRemote(Organization localOrganization, Organization remoteOrganization);
    

    /**
     * Search remote organization.
     *
     * @param coppaSearchText the coppa search text
     * @param sType the s type
     * @return the list
     */
    List<Organization> searchRemoteOrganization(String coppaSearchText, String sType);
    
    /**
     * Search organization.
     *
     * @param query the query
     * @return the list
     */
    List<Organization> searchOrganization(final OrganizationQuery query);
    
    /**
     * Restrict by subnames.
     *
     * @param subnames the subnames
     * @param skipFiltering the skip filtering
     * @return the list
     */
    List<Organization> restrictBySubnames(final String[] subnames, boolean skipFiltering, boolean filterByRetired, boolean filterByOrgType);

    /**
     * Restrict by subnames.
     *
     * @param subnames the subnames
     * @return the list
     */
    List<Organization> restrictBySubnames(final String[] subnames);
    
    /**
     * Gets the local organizations.
     *
     * @param query the query
     * @return the local organizations
     */
    List<Organization> getLocalOrganizations(final OrganizationQuery query);
    
	/**
	 * This method will fetch all the organizations in caAERS database.
	 *
	 * @return the all organizations
	 */
    List<Organization> getAllOrganizations();
    
    /**
     * Gets the all nci institue codes.
     *
     * @return the all nci institue codes
     */
    List<Organization> getAllNciInstitueCodes();
    
    /**
     * Save imported organization.
     *
     * @param organization the organization
     */
    void saveImportedOrganization(Organization organization);
    
    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    Organization getById(int id);
    
    /**
     * Evict.
     *
     * @param org the org
     */
    void evict(Organization org);
}
