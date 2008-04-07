package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.Organization;

/**
 * This service interface is used to build the organization domain object.
 * 
 * 
 */
public interface OrganizationRepository {
    void create(Organization organization);

    void createOrUpdate(Organization organization);
}
