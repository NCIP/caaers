package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface PreExistingConditionManagementService {
	
	public List<ProcessingOutcome> importPreExistingConditions(List<PreExistingCondition> importedPreExistingConditions);

}
