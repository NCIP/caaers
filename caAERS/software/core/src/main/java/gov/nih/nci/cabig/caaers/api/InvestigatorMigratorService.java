package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;





public interface InvestigatorMigratorService {
	

	public CaaersServiceResponse saveInvestigator(gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff) ;
	
}
