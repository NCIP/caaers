package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
@Transactional(readOnly=false)
public interface OrganizationService {
	
	public List<EntityErrorMessage> createOrUpdateOrganizations(List<Organization> organizations);

}
