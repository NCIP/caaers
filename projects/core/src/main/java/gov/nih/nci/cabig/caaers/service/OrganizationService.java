package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.security.authorization.domainobjects.Group;

public interface OrganizationService {
    void create(Organization organization);

    void createOrUpdate(Organization organization);
}
