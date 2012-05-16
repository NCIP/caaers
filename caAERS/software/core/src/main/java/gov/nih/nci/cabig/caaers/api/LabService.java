package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.LabCategory;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface LabService {
	
	public List<ProcessingOutcome> createOrUpdateLabs(List<LabCategory> labCategories);

}
