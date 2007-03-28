package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.ui.ModelMap;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import java.util.Date;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractAdverseEventInputController<C extends AdverseEventInputCommand> extends AbstractTabbedFlowFormController<AdverseEventInputCommand> {
    protected StudyParticipantAssignmentDao assignmentDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private CtcTermDao ctcTermDao;

    protected AbstractAdverseEventInputController() {
        initFlow();
        addCommonTabs();
    }

    protected void initFlow() {
        setFlow(new Flow<AdverseEventInputCommand>(getFlowName()));
    }

    protected abstract String getFlowName();

    protected final void addCommonTabs() {
        getFlow().addTab(new BasicsTab());
        getFlow().addTab(new EmptyAeTab("Medical information", "Medical", "ae/notimplemented"));
        getFlow().addTab(new LabsTab());
        getFlow().addTab(new EmptyAeTab("Treatment information", "Treatment", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Outcome information", "Outcome", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Prior therapies", "Prior therapies", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Concomitant medications", "Concomitant medications", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Study agent(s)", "Agent", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Medical device(s)", "Device", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Reporter info", "Reporter", "ae/notimplemented"));
        getFlow().addTab(new EmptyAeTab("Confirm and save", "Save", "ae/save"));
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "aeReport.primaryAdverseEvent.ctcTerm", ctcTermDao);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerEnumEditor(binder, Grade.class);
        ControllerTools.registerEnumEditor(binder, Hospitalization.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(
        HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors
    ) throws Exception {
        C command = (C) oCommand;
        doSave(command);
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }

    protected void doSave(C command) {
    }

    ////// CONFIGURATION

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
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
