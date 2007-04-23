package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.ui.ModelMap;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractAdverseEventInputController<C extends AdverseEventInputCommand>
    extends AbstractTabbedFlowFormController<AdverseEventInputCommand>
    implements InitializingBean
{
    public static final String AJAX_SUBVIEW_PARAMETER = "subview";

    private TabAutowirer autowirer;
    protected ParticipantDao participantDao;
    protected StudyDao studyDao;
    protected StudyParticipantAssignmentDao assignmentDao;
    protected CtcTermDao ctcTermDao;
    protected AgentDao agentDao;
    protected AdverseEventReportDao reportDao;
    protected StudyAgentDao studyAgentDao;
    protected RuleExecutionService ruleExecutionService;

    protected AbstractAdverseEventInputController() {
        setFlow(new Flow<AdverseEventInputCommand>(getFlowName()));
        addTabs(getFlow());
    }

    protected void addTabs(Flow<AdverseEventInputCommand> flow) {
        flow.addTab(new BasicsTab());
        flow.addTab(new MedicalInfoTab());
        flow.addTab(new TreatmentTab());
        flow.addTab(new LabsTab());
        flow.addTab(new EmptyAeTab("Outcome information", "Outcome", "ae/notimplemented"));
        flow.addTab(new EmptyAeTab("Prior therapies", "Prior therapies", "ae/notimplemented"));
        flow.addTab(new ConcomitantMedicationsTab());
        flow.addTab(new AttributionTab());
        flow.addTab(new ReporterTab());
        flow.addTab(new EmptyAeTab("Confirm and save", "Save", "ae/save"));
    }

    protected abstract String getFlowName();

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "aeReport", reportDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcTermDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, studyAgentDao);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerEnumEditor(binder, Grade.class);
        ControllerTools.registerEnumEditor(binder, Hospitalization.class);
        ControllerTools.registerEnumEditor(binder, Attribution.class);
    }

    /** Adds ajax sub-page view capability.  TODO: factor this into main tabbed flow controller. */
    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        String subviewName = request.getParameter(AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "ae/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
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

    public void afterPropertiesSet() throws Exception {
        autowirer.injectDependencies(getFlow());
    }

    public void setAutowirer(TabAutowirer autowirer) {
        this.autowirer = autowirer;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public void setReportDao(AdverseEventReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public void setStudyAgentDao(StudyAgentDao studyAgentDao) {
        this.studyAgentDao = studyAgentDao;
    }

    public void setRuleExecutionService(RuleExecutionService ruleExecutionService) {
        this.ruleExecutionService = ruleExecutionService;
    }
}
