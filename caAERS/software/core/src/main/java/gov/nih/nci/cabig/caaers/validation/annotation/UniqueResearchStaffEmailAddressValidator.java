package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public class UniqueResearchStaffEmailAddressValidator implements
Validator<UniqueResearchStaffEmailAddress>{
	
	private ResearchStaffRepository researchStaffRepository;
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
        String emailAddress = (String)value;
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        researchStaffQuery.filterByLoginId(emailAddress);
        List<ResearchStaff> researchStaffList = researchStaffRepository.getResearchStaff(researchStaffQuery);
            if (researchStaffList!=null && !researchStaffList.isEmpty()) {
            	message = "EmailAddress already in use";
            	return false;
        }
		return true;
	}

	@Required
	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

}
