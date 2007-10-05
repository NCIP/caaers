package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.security.authorization.domainobjects.Group;

public interface OrganizationService {


    //public void save(Organization organization) throws CaaersSystemException;

    //
    //* merge also calls save on the dao because he dao's save calls a saveOrUpdate which works just fine.
    //* thsi methiod was created so that we caould avoid calling createGroupForOrganization
    //* @see edu.duke.cabig.c3pr.service.OrganizationService#merge(edu.duke.cabig.c3pr.domain.HealthcareSite)
    //
    //public void merge(HealthcareSite organization) throws CaaersSystemException;

     Group createGroupForOrganization(Organization organization) ;
}
