package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventCommand;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    private AdverseEventReportDao reportDao;

    public CreateAdverseEventController() {
        setCommandClass(CreateAdverseEventCommand.class);
        Flow<CreateAdverseEventCommand> flow = new Flow<CreateAdverseEventCommand>("Create AE");
        setFlow(flow);
        flow.addTab(new BeginTab());
        flow.addTab(new BasicsTab());
        flow.addTab(new AeTab("Medical information", "Medical", "ae/notimplemented"));
        flow.addTab(new LabsTab());
        flow.addTab(new AeTab("Treatment information", "Treatment", "ae/notimplemented"));
        flow.addTab(new AeTab("Outcome information", "Outcome", "ae/notimplemented"));
        flow.addTab(new AeTab("Prior therapies", "Prior therapies", "ae/notimplemented"));
        flow.addTab(new AeTab("Concomitant medications", "Concomitant medications", "ae/notimplemented"));
        flow.addTab(new AeTab("Study agent(s)", "Agent", "ae/notimplemented"));
        flow.addTab(new AeTab("Medical device(s)", "Device", "ae/notimplemented"));
        flow.addTab(new AeTab("Reporter info", "Reporter", "ae/notimplemented"));
        flow.addTab(new AeTab("Confirm and save", "Save", "ae/save"));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new CreateAdverseEventCommand(assignmentDao, reportDao);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "aeReport.primaryAdverseEvent.ctcTerm", ctcTermDao);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(
        HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors
    ) throws Exception {
        CreateAdverseEventCommand command = (CreateAdverseEventCommand) oCommand;
        command.save();
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        model.put("newReport", command.getAeReport().getId());
        return new ModelAndView("redirectToAeList", model);
    }

    ////// CONFIGURATION

    public void setReportDao(AdverseEventReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
        // TODO: this is a dumb, short-term solution
        ((BasicsTab) getFlow().getTab(1)).setCtcDao(ctcDao);
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
