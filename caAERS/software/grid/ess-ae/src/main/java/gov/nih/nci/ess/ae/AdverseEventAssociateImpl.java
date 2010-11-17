package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.Id;
import ess.caaers.nci.nih.gov.StudyProtocolVersion;

public class AdverseEventAssociateImpl {
	
	public StudyProtocolVersion associateAdverseEventToStudy(Id adverseEventIdentifier  , Id studyIdentifier) {
		return null;
	}
	
	public StudyProtocolVersion dissociateAdverseEventFromStudy(Id adverseEventIdentifier  , Id studyIdentifier) {
		return null;
	}
	
	public AdverseEvent associateAdditionalInformationToAdverseEvent(Id adverseEventIdentifier  , Id additionalInformationIdentifier) {
		return null;
	}
	
	public AdverseEvent dissociateAdditionalInformationFromAdverseEvent(Id adverseEventIdentifier  , Id additionalInformationIdentifier) {
		return null;
	}
	
	public AdverseEvent associateTreatmentInformationToAdverseEvent(Id adverseEventIdentifier  , Id performedActivityIdentifier) {
		return null;
	}
	
	public AdverseEvent disassociateTreatmentInformationFromAdverseEvent(Id adverseEventIdentifier  , Id performedActivityIdentifier) {
		return null;
	}
	
	
}
