package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

public interface ResearchStaffMigratorService {


	public CaaersServiceResponse saveResearchStaff( gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff) ;

}
