package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapies;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface PriorTherapyManagementService {
	
	public CaaersServiceResponse importPriorTherapies(PriorTherapies xmlPriorTherapies);

}
