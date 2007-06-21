package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.CtepStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractAdverseEventInputController
    extends AutomaticSaveFlowFormController<ExpeditedAdverseEventInputCommand, ExpeditedAdverseEventReport, ExpeditedAdverseEventReportDao>
{
    public static final String AJAX_SUBVIEW_PARAMETER = "subview";

    protected ParticipantDao participantDao;
    protected StudyDao studyDao;
    protected StudyParticipantAssignmentDao assignmentDao;
    protected CtcTermDao ctcTermDao;
    protected AgentDao agentDao;
    protected ExpeditedAdverseEventReportDao reportDao;
    protected RoutineAdverseEventReportDao routineReportDao;
    protected StudyAgentDao studyAgentDao;
    protected CtepStudyDiseaseDao ctepStudyDiseaseDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected PriorTherapyDao priorTherapyDao;
    protected CtcCategoryDao ctcCategoryDao;
    protected NowFactory nowFactory;

    protected AbstractAdverseEventInputController() {
        setFlow(new Flow<ExpeditedAdverseEventInputCommand>(getFlowName()));
        addTabs(getFlow());
    }

    protected void addTabs(Flow<ExpeditedAdverseEventInputCommand> flow) {
        flow.addTab(new BasicsTab());
        flow.addTab(new ReporterTab());
        flow.addTab(new DescriptionTab());
        flow.addTab(new MedicalInfoTab());
        flow.addTab(new TreatmentTab());
        flow.addTab(new LabsTab());
        // TODO: readd this when we have some idea what it should be
        // flow.addTab(new EmptyAeTab("Outcome information", "Outcome", "ae/notimplemented"));
        flow.addTab(new PriorTherapyTab());
        flow.addTab(new ConcomitantMedicationsTab());
        flow.addTab(new OtherCausesTab());
        flow.addTab(new AttributionTab());
        flow.addTab(new Tab<ExpeditedAdverseEventInputCommand>("Confirm and save", "Save", "ae/save"));
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
        ControllerTools.registerDomainObjectEditor(binder, ctepStudyDiseaseDao);
        ControllerTools.registerDomainObjectEditor(binder, anatomicSiteDao);
        ControllerTools.registerDomainObjectEditor(binder, priorTherapyDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcCategoryDao);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerEnumEditor(binder, Grade.class);
        ControllerTools.registerEnumEditor(binder, Hospitalization.class);
        ControllerTools.registerEnumEditor(binder, Attribution.class);
        ControllerTools.registerEnumEditor(binder, PostAdverseEventStatus.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(
        HttpServletRequest request, Object oCommand, Errors errors, int page
    ) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, oCommand, errors, page);
        if (displaySummary(page)) {
            refdata.put("summary", ((ExpeditedAdverseEventInputCommand) oCommand).getAeReport().getSummary());
        }
        return refdata;
    }

    @Override
    protected boolean shouldSave(
        HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
        Tab<ExpeditedAdverseEventInputCommand> tab
    ) {
        return super.shouldSave(request, command, tab)
            && request.getParameter(AJAX_SUBVIEW_PARAMETER) == null;
    }

    protected boolean displaySummary(int page) {
        return true;
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
        ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) oCommand;
        save(command, errors);
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }

    @Override
    protected ExpeditedAdverseEventReportDao getDao() {
        return reportDao;
    }

    @Override
    protected ExpeditedAdverseEventReport getPrimaryDomainObject(ExpeditedAdverseEventInputCommand command) {
        return command.getAeReport();
    }

    ////// CONFIGURATION

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

    public void setReportDao(ExpeditedAdverseEventReportDao reportDao) {
        this.reportDao = reportDao; 
    }

    public void setStudyAgentDao(StudyAgentDao studyAgentDao) {
        this.studyAgentDao = studyAgentDao;
    }

    public void setCtepStudyDiseaseDao(CtepStudyDiseaseDao ctepStudyDiseaseDao) {
        this.ctepStudyDiseaseDao = ctepStudyDiseaseDao;
    }

    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
        this.anatomicSiteDao = anatomicSiteDao;
    }

    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
        this.priorTherapyDao = priorTherapyDao;
    }

    public CtcCategoryDao getCtcCategoryDao() {
        return ctcCategoryDao;
    }

    public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
        this.ctcCategoryDao = ctcCategoryDao;
    }

    public NowFactory getNowFactory() {
        return nowFactory;
    }

    public void setNowFactory(NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }

    public RoutineAdverseEventReportDao getRoutineReportDao() {
        return routineReportDao;
    }

    public void setRoutineReportDao(RoutineAdverseEventReportDao routineReportDao) {
        this.routineReportDao = routineReportDao;
    }
    
    
}
