package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.Criteria;
import gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.ImportAdverseEvents;

public interface AdverseEventManagementService {
	
	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse createAdverseEvent(ImportAdverseEvents importAdverseEvents) ;
	
	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse updateAdverseEvent(ImportAdverseEvents importAdverseEvents) ;
	
	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse deleteAdverseEvent(ImportAdverseEvents importAdverseEvents) ;
	
}
