package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Biju Joseph
 * 
 */
public class ReviewAssignmentTab extends TabWithFields<AssignParticipantStudyCommand> {
	private OrganizationDao organizationDao;
	
    public ReviewAssignmentTab() {
        super("Review", "Review", "par/reg_review_submit");
//        super("Review and Submit", "Review and Submit", "par/par_confirmation");
    }

    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }
    
    @Override
    public void onDisplay(HttpServletRequest request,AssignParticipantStudyCommand command) {
    	super.onDisplay(request, command);
    	StudySite site = command.getStudySite();
    	if(site != null) organizationDao.lock(site.getOrganization());
    }
    public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}
    public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}