/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.fields.*;

import java.util.Map;

/**
 * 
 * @author Biju Joseph
 * 
 */
public class ReviewAssignmentTab extends TabWithFields<AssignParticipantStudyCommand> {
	private OrganizationDao organizationDao;
	
    public ReviewAssignmentTab() {
        super("Review", "Review", "par/reg_review_submit");
        addFieldDecorators(new SecurityObjectIdFieldDecorator(Participant.class), new ReadonlyFieldDecorator());
    }

    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }
    
//    @Override
//    public void onDisplay(HttpServletRequest request,AssignParticipantStudyCommand command) {
//    	super.onDisplay(request, command);
//    	StudySite site = command.getStudySite();
//    	if(site != null) organizationDao.lock(site.getOrganization());
//    }
    public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}
    public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}
