package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
@Transactional(readOnly=false)
public interface PreExistingConditionLOVService {
	
	public List<EntityErrorMessage> importPreExistingConditions(List<PreExistingCondition> importedPreExistingConditions);

}
