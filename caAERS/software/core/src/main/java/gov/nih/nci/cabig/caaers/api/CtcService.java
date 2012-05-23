package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.Ctcs;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface CtcService {
	
	public List<ProcessingOutcome> createOrUpdateCtc(Ctc ctc);
	public CaaersServiceResponse createOrUpdateCtc(Ctcs ctcs);

}
