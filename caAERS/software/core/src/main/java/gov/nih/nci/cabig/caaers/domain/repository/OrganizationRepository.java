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
    void create(Organization organization);
    void createOrUpdate(Organization organization);
    List<Organization> getOrganizationsHavingStudySites(final OrganizationFromStudySiteQuery query);
    List<Organization> getApplicableOrganizationsFromStudySites(String text, Integer studyId);
    public List<StudyOrganization> getApplicableOrganizationsFromStudyOrganizations(final String text, Integer studyId);
    void convertToRemote(Organization localOrganization, Organization remoteOrganization);
    

    List<Organization> searchRemoteOrganization(String coppaSearchText, String sType);
    List<Organization> searchOrganization(final OrganizationQuery query);
    List<Organization> restrictBySubnames(final String[] subnames, boolean skipFiltering);
    List<Organization> restrictBySubnames(final String[] subnames);
    List<Organization> getLocalOrganizations(final OrganizationQuery query);
    
	/**
	 * This method will fetch all the organizations in caAERS database.
	 */
    List<Organization> getAllOrganizations();
    List<Organization> getAllNciInstitueCodes();
    void saveImportedOrganization(Organization organization);
    
    Organization getById(int id);
    void evict(Organization org);
}
