package gov.nih.nci.cabig.caaers.web;

import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.Map;
import java.util.HashMap;
import java.beans.PropertyEditorSupport;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractWizardFormController {
    private static final int SELECT_ASSIGNMENT_PAGE = 0;
    private static final int ENTER_SINGLE_BASIC_AE = 1;

    private StudyParticipantAssignmentDao assignmentDao;
    private CtcDao ctcDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private CtcTermDao ctcTermDao;

    public CreateAdverseEventController() {
        setCommandClass(CreateAdverseEventCommand.class);
        setCommandName("command");
        setPages(new String[] {
            "ae/selectAssignment",
            "ae/enterBasic"
        });
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateAdverseEventCommand(assignmentDao);
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "ae.ctcTerm", ctcTermDao);
    }

    protected Map<?, ?> referenceData(HttpServletRequest request, int page) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("pageNumber", page);
        switch (page) {
            case SELECT_ASSIGNMENT_PAGE:
                refdata.put("targetNumber", 1);
                break;
            case ENTER_SINGLE_BASIC_AE:
                refdata.put("ctcVersions", ctcDao.getAll());
                refdata.put("grades", Grade.values());
                refdata.put("attributions", Attribution.values());
                refdata.put("targetNumber", 0); // TODO
                break;
        }
        return refdata;
    }

    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        throw new UnsupportedOperationException("processFinish not implemented");
    }

    ////// CONFIGURATION

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }
}
