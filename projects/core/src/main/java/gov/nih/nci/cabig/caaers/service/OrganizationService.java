package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.Organization;

/**
 * This service interface is used to build the organization domain object.
 * 
 *
 */
public interface OrganizationService {
    void create(Organization organization);

    void createOrUpdate(Organization organization);
}
