package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;

public class UniqueResearchStaffEmailAddressValidator implements
Validator<UniqueResearchStaffEmailAddress>{
	
	private ResearchStaffDao researchStaffDao;
	String message;
	
	public void initialize(UniqueResearchStaffEmailAddress parameters) {
		message = parameters.message();
		
	}

	public String message() {
		return message;
	}

	public boolean validate(Object value) {
		// is value null ?
        if (value == null) return true;
        
        if (!(value instanceof ResearchStaff)) return true;
        ResearchStaff other = (ResearchStaff) value;
        
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        researchStaffQuery.filterByLoginId(other.getEmailAddress());
        List<ResearchStaff> researchStaffList = researchStaffDao.searchResearchStaff(researchStaffQuery);
            if (researchStaffList!=null && !researchStaffList.isEmpty()) {
            	message = "EmailAddress already in use";
            	return false;
        }
		return true;
	}

}
