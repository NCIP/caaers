package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
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
	private StudySiteDao studySiteDao;
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
    	studySiteDao.lock(command.getStudySite());
    }
    
    public StudySiteDao getStudySiteDao() {
		return studySiteDao;
	}
    public void setStudySiteDao(StudySiteDao studySiteDao) {
		this.studySiteDao = studySiteDao;
	}
}