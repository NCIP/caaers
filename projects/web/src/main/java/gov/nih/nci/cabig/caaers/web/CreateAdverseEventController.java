package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractTabbedFlowFormController {
    private StudyParticipantAssignmentDao assignmentDao;
    private CtcDao ctcDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private CtcTermDao ctcTermDao;

    public CreateAdverseEventController() {
        setCommandClass(CreateAdverseEventCommand.class);
        setFlow(new Flow("Create AE", Arrays.asList(
            new Tab(0, "Select participant and study", "Begin", "ae/selectAssignment"),
            new Tab(1, "Enter basic event information", "Basics", "ae/enterBasic") {
                public Map<String, Object> referenceData() {
                    Map<String, Object> refdata = super.referenceData();
                    refdata.put("ctcVersions", ctcDao.getAll());
                    refdata.put("grades", Grade.values());
                    refdata.put("attributions", Attribution.values());
                    return refdata;
                }
            },
            new Tab(2, "Medical information", "Medical", "ae/notimplemented"),
            new Tab(3, "Lab values", "Labs", "ae/notimplemented"),
            new Tab(4, "Treatment information", "Treatment", "ae/notimplemented"),
            new Tab(5, "Outcome information", "Outcome", "ae/notimplemented"),
            new Tab(6, "Prior therapies", "Prior therapies", "ae/notimplemented"),
            new Tab(7, "Concomitant medications", "Concomitant medications", "ae/notimplemented"),
            new Tab(8, "Study agent(s)", "Agent", "ae/notimplemented"),
            new Tab(9, "Medical device(s)", "Device", "ae/notimplemented"),
            new Tab(10, "Reporter info", "Reporter", "ae/notimplemented"),
            new Tab(11, "Confirm and save", "Save", "ae/notimplemented")
        )));
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateAdverseEventCommand(assignmentDao);
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "ae.ctcTerm", ctcTermDao);
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
