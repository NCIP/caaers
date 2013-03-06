/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.validation.AbstractConstraintValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class UniqueResearchStaffEmailAddressValidator extends AbstractConstraintValidator<UniqueResearchStaffEmailAddress, Object> implements
Validator<UniqueResearchStaffEmailAddress>{
	
	private ResearchStaffRepository researchStaffRepository;
	String message;
	
	public void initialize(UniqueResearchStaffEmailAddress parameters) {
    	super.initialize(parameters);
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
	
	@Autowired
	@Required
	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

}
