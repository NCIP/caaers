package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventCommand;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventController extends AbstractTabbedFlowFormController<CreateAdverseEventCommand> {
    private StudyParticipantAssignmentDao assignmentDao;
    private CtcDao ctcDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private CtcTermDao ctcTermDao;

    public CreateAdverseEventController() {
        setCommandClass(CreateAdverseEventCommand.class);
        Flow<CreateAdverseEventCommand> flow = new Flow<CreateAdverseEventCommand>("Create AE");
        setFlow(flow);
        flow.addTab(new BeginTab());
        flow.addTab(new BasicsTab());
        flow.addTab(new AeTab("Medical information", "Medical", "ae/notimplemented"));
        flow.addTab(new AeTab("Lab values", "Labs", "ae/notimplemented"));
        flow.addTab(new AeTab("Treatment information", "Treatment", "ae/notimplemented"));
        flow.addTab(new AeTab("Outcome information", "Outcome", "ae/notimplemented"));
        flow.addTab(new AeTab("Prior therapies", "Prior therapies", "ae/notimplemented"));
        flow.addTab(new AeTab("Concomitant medications", "Concomitant medications", "ae/notimplemented"));
        flow.addTab(new AeTab("Study agent(s)", "Agent", "ae/notimplemented"));
        flow.addTab(new AeTab("Medical device(s)", "Device", "ae/notimplemented"));
        flow.addTab(new AeTab("Reporter info", "Reporter", "ae/notimplemented"));
        flow.addTab(new AeTab("Confirm and save", "Save", "ae/notimplemented"));
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

    ////// INNER CLASSES

    private class BasicsTab extends AeTab {
        public BasicsTab() {
            super("Enter basic event information", "Basics", "ae/enterBasic");
        }

        public Map<String, Object> referenceData() {
            Map<String, Object> refdata = super.referenceData();
            refdata.put("ctcVersions", ctcDao.getAll());
            refdata.put("grades", Grade.values());
            refdata.put("attributions", Attribution.values());
            return refdata;
        }

        public boolean isAllowDirtyForward() {
            return false;
        }
    }
}
