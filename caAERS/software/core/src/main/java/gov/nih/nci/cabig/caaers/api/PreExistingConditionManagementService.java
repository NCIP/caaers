package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditions;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface PreExistingConditionManagementService {
	
	public CaaersServiceResponse importPreExistingConditions(PreExistingConditions xmlPreExistingConditions);

}
