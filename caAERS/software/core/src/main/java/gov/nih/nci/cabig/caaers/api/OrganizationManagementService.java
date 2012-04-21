package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface OrganizationManagementService {
	
	public List<ProcessingOutcome> createOrUpdateOrganizations(List<Organization> organizations);
	
	public List<ProcessingOutcome> mergeOrganizations(List<Organization> organizations);

}
