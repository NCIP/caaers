package gov.nih.nci.cabig.caaers.web;

import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractWizardFormController {
    private static final int SELECT_ASSIGNMENT_PAGE = 0;
    private static final int ENTER_SINGLE_BASIC_AE = 1;

    private StudyParticipantAssignmentDao assignmentDao;

    public CreateAdverseEventController() {
        setCommandClass(CreateAdverseEventCommand.class);
        setPages(new String[] {
            "ae/selectAssignment",
            "ae/enterBasic"
        });
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateAdverseEventCommand(assignmentDao);
    }

    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        throw new UnsupportedOperationException("processFinish not implemented");
    }

    ////// CONFIGURATION

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }
}
