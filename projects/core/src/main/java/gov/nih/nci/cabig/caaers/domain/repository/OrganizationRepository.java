package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;

import java.util.List;

/**
 * This service interface is used to build the organization domain object.
 * 
 * 
 */
public interface OrganizationRepository {
    void create(Organization organization);
    void createOrUpdate(Organization organization);
    List<Organization> getOrganizationsHavingStudySites();
    void convertToRemote(Organization localOrganization, Organization remoteOrganization);
    

    List<Organization> searchRemoteOrganization(String coppaSearchText, String sType);
    List<Organization> searchOrganization(final OrganizationQuery query);
    List<Organization> restrictBySubnames(final String[] subnames);
    
}
