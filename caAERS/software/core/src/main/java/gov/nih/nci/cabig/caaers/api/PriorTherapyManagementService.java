/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
