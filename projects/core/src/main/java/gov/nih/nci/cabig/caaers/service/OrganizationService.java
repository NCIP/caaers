package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.Organization;

public interface OrganizationService {
    void create(Organization organization);

    void createOrUpdate(Organization organization);
}
