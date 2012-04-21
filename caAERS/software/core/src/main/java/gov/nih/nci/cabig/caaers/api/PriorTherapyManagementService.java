package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface PriorTherapyManagementService {
	
	public List<ProcessingOutcome> importPriorTherapies(List<PriorTherapy> importedPriorTherapies);

}
