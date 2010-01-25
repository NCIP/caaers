package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManager;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManagerFactory;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManagerFactoryBean;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

public class CaptureAdverseEventController extends AutomaticSaveAjaxableFormController<CaptureAdverseEventInputCommand, AdverseEventReportingPeriod, AdverseEventReportingPeriodDao> {
	
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	
	
	private static final String SELECTED_STUDY_ID = "pre_selected_study_id";
	private static final String SELECTED_PARTICIPANT_ID = "pre_selected_participant_id";
	private static final String SELECTED_COURSE_ID = "pre_selected_reporting_period_id";
	
	
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	private StudyParticipantAssignmentDao assignmentDao;
	private TreatmentAssignmentDao treatmentAssignmentDao;
	private CtcTermDao ctcTermDao;
	private CtcCategoryDao ctcCategoryDao;
	private LowLevelTermDao lowLevelTermDao;
	private AdverseEventDao adverseEventDao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private EvaluationService evaluationService;
	private ReportDefinitionDao reportDefinitionDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private ReportRepository reportRepository;
	
	private RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean;
	private CaaersFieldConfigurationManagerFactory caaersFieldConfigurationManagerFactory;
	
	private Configuration configuration;
	
	private Logger log = Logger.getLogger(getClass());

    public CaptureAdverseEventController(){
		setBindOnNewForm(true);
		setCommandClass(CaptureAdverseEventInputCommand.class);
	}
	
	@Override
	protected AdverseEventReportingPeriodDao getDao() {
		return null;
	}

	/**
	 * Will return the {@link AdverseEventReportingPeriod} 
	 */
	@Override
	protected AdverseEventReportingPeriod getPrimaryDomainObject(CaptureAdverseEventInputCommand cmd) {
		return cmd.getAdverseEventReportingPeriod();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request, Object o,	Errors errors, int page) throws Exception {
		CaptureAdverseEventInputCommand command  = (CaptureAdverseEventInputCommand) o;
		Map referenceData =  super.referenceData(request, command, errors, page);
		Map<String, String> summary = new LinkedHashMap<String, String>();
		
		summary.put("Study", (command.getStudy() == null) ? "" : "(" +  command.getStudy().getPrimaryIdentifierValue() + ") " + command.getStudy().getShortTitle());
        summary.put("Participant", (command.getParticipant() == null) ? "" : "(" +  command.getParticipant().getPrimaryIdentifierValue() + ") " + command.getParticipant().getFullName() );
      
        if(command.getAdverseEventReportingPeriod() != null){
        	summary.put("Course", command.getAdverseEventReportingPeriod().getName());	
        }
        
        //put summary only if page is greater than 0
        if(page > 0){
        	referenceData.put("routineAeSummary", summary);
        }
        
        
        RenderDecisionManager renderDecisionManager = renderDecisionManagerFactoryBean.getRenderDecisionManager();
        
        CaaersFieldConfigurationManager caaersFieldConfigurationManager = caaersFieldConfigurationManagerFactory.getCaaersFieldConfigurationManager();
        renderDecisionManager.reveal(caaersFieldConfigurationManager.getListOfApplicableFields(AdverseEventCaptureTab.class.getName()));
        renderDecisionManager.conceal(caaersFieldConfigurationManager.getListOfNotApplicableFields(AdverseEventCaptureTab.class.getName()));
        
		return referenceData;
	}
	
	
	
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("reviewResult");
		
		CaptureAdverseEventInputCommand command = (CaptureAdverseEventInputCommand) oCommand;

		//populate the base date, associated to each report definition.
		command.getReviewResult().setBaseDateMap(command.findBaseDateMap(command.getReviewResult().getAeReportId()));
		
		//populate the report-IDs (for withdraw & amend)
		command.populateReportsToAmend();
		command.populateReportsToWithdraw();
		
		//populate the reports to un-amend
		command.populateReportsToUnAmend();
		
		command.save();
		
		
		//create the model to return
		ModelAndView modelAndView = null;
		
		Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
	    model.put("study", command.getStudy().getId());
		
		//check if this is alone a withdraw ?
		if(command.getReviewResult().isOnlyActionWithdraw()){
			ExpeditedAdverseEventReport aeReport = command.getAdverseEventReportingPeriod().findExpeditedAdverseEventReport(command.getReviewResult().getAeReportId());
			if(aeReport != null){
				expeditedAdverseEventReportDao.lock(aeReport);
				if(CollectionUtils.isNotEmpty(command.getReviewResult().getUnwantedAEList())){
					
					
					//remove all the deselected aes. 
					for(Integer aeId : command.getReviewResult().getUnwantedAEList()){
						AdverseEvent ae = aeReport.findAdverseEventById(aeId);
						if(ae == null) continue;
						aeReport.getAdverseEvents().remove(ae);
						ae.clearAttributions();
						ae.setReport(null);
					}
					
					expeditedAdverseEventReportDao.save(aeReport);
				}
				
				//process the reports. 
				reportRepository.processReports(aeReport, null, command.getReviewResult().getReportsToUnAmendList(), command.getReviewResult().getReportsToWithdraw(), null);

				
				model.put("adverseEventReportingPeriod", command.getAdverseEventReportingPeriod().getId());
				model.put("addReportingPeriodBinder", "true");
				model.put("displayReportingPeriod", "true");
				model.put("_page", "0");
				model.put("_target2", "2");
				modelAndView = new ModelAndView("redirectToCaptureAe", model);
			}
			
		}else{
			//continuing to expedited flow
		    if(command.getReviewResult().getAeReportId() > 0){
		    	model.put("aeReport", command.getReviewResult().getAeReportId().intValue());
		    }
		    model.put("from", "captureAE");
		    
		    session.setAttribute("reviewResult", command.getReviewResult());
			modelAndView = new ModelAndView("redirectToExpeditedAeEdit", model);
		}
		
		return modelAndView;
	}
	
	
	/**
	 * Will bind editors for this flow
	 */
	@Override
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
		ControllerTools.registerDomainObjectEditor(binder,  participantDao);
        ControllerTools.registerDomainObjectEditor(binder,  studyDao);
        ControllerTools.registerDomainObjectEditor(binder,  adverseEventReportingPeriodDao);

        ControllerTools.registerDomainObjectEditor(binder, "adverseEvent", adverseEventDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcTermDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcCategoryDao);
        ControllerTools.registerDomainObjectEditor(binder, lowLevelTermDao);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerEnumEditor(binder, Grade.class);
        ControllerTools.registerEnumEditor(binder, Hospitalization.class);
        ControllerTools.registerEnumEditor(binder, Attribution.class);
        ControllerTools.registerEnumEditor(binder, OutcomeType.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		CaptureAdverseEventInputCommand cmd = new CaptureAdverseEventInputCommand(adverseEventReportingPeriodDao,assignmentDao, evaluationService, reportDefinitionDao, studyDao, expeditedAdverseEventReportDao);

		cmd.setWorkflowEnabled(configuration.get(Configuration.ENABLE_WORKFLOW));
		
		if(findInRequest(request, "study") == null){

			//restore the values from session if they are available. 
			HttpSession session = request.getSession();

			Integer studyId = (Integer) session.getAttribute(SELECTED_STUDY_ID);
			if(studyId != null){
				cmd.setStudy(studyDao.getById(studyId));
			}
			
			Integer subjectId = (Integer) session.getAttribute(SELECTED_PARTICIPANT_ID);
			if(subjectId != null){
				cmd.setParticipant(participantDao.getById(subjectId));
			}
			
			Integer courseId = (Integer) session.getAttribute(SELECTED_COURSE_ID);
			if(courseId != null){
				cmd.setAdverseEventReportingPeriod(adverseEventReportingPeriodDao.getById(courseId));
			}

		}
				
		return cmd;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//force clear off the session attribute if the request is re-directed (from Manage Report/Review And Report). 
		String displayReportingPeriod = WebUtils.getStringParameter(request, "displayReportingPeriod");
		if(StringUtils.isNotEmpty(displayReportingPeriod)){
			
			String formAttributeName = getFormSessionAttributeName(request);
			HttpSession session = request.getSession();
			if(session != null){
				session.removeAttribute(formAttributeName);
			}
		}
		
		return super.handleRequestInternal(request, response);
	}
	
	/**
	 * Will return true if we are entering into capture flow from Manage Reports or through some other redirection. 
	 */
	@Override
    protected boolean isFormSubmission(HttpServletRequest request) {
		String displayReportingPeriod = WebUtils.getStringParameter(request, "displayReportingPeriod");
		if(StringUtils.isNotEmpty(displayReportingPeriod)) return true;
		return super.isFormSubmission(request);
    }
	

	/**
	 * If the entry to capture adverse event is from Manage reports, we need to handle the invalid submit case, as it the isFormSubmission is flaged 'true'. 
	 */
	
	@Override
	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String displayReportingPeriod = WebUtils.getStringParameter(request, "displayReportingPeriod");
		if(StringUtils.isEmpty(displayReportingPeriod)) return  super.handleInvalidSubmit(request, response);
		
		//generate the form, validate , processFormSubmission.
		Object command = formBackingObject(request);
		ServletRequestDataBinder binder = bindAndValidate(request, command);
		BindException errors = new BindException(binder.getBindingResult());
		
		return processFormSubmission(request, response, command, errors);
		
    }

    @Override
	public FlowFactory<CaptureAdverseEventInputCommand> getFlowFactory() {
		return new FlowFactory<CaptureAdverseEventInputCommand>() {
			public Flow<CaptureAdverseEventInputCommand> createFlow(CaptureAdverseEventInputCommand cmd) {
				
            	/**
            	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
            	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
            	 */
				Flow<CaptureAdverseEventInputCommand> flow = new Flow<CaptureAdverseEventInputCommand>("Enter AEs || Select Subject and Study");
				flow.addTab(new BeginTab<CaptureAdverseEventInputCommand>());
				flow.addTab(new AdverseEventCaptureTab());
				flow.addTab(new ReviewAndReportTab());
				return flow;
			}
		};
	}
	
	/**
	 * Supress the validation in the following cases.
	 *   1 - When we go back
	 *   2 - When it is an Ajax request, which dont has form submission
	 */
	
	@Override
    protected boolean suppressValidation(final HttpServletRequest request) {

        Object isAjax = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (isAjax != null) return true;
        
        //check current page and next page
        int currPage = getCurrentPage(request);
    	int targetPage = getTargetPage(request, currPage);
        return targetPage < currPage;

    }
	
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
    
    /**
     * Adds ajax sub-page view capability. TODO: factor this into main tabbed flow controller.
     */
    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        String subviewName = request.getParameter(AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            // for side-effects:
            super.getViewName(request, command, page);
            return "ae/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }
    
   @Override
	protected boolean shouldSave(HttpServletRequest request,CaptureAdverseEventInputCommand command,Tab<CaptureAdverseEventInputCommand> tab) {
		Object isAjax = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (isAjax != null || 1 != getCurrentPage(request)) {
            return false;
        }
       return true;
	}
	
	@Override
	protected CaptureAdverseEventInputCommand save(final CaptureAdverseEventInputCommand command, final Errors errors){
		if(!errors.hasErrors())
			command.save();
		
		return command;
	}
	
	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
	public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
		this.assignmentDao = assignmentDao;
	}
	
	public void setTreatmentAssignmentDao(
			TreatmentAssignmentDao treatmentAssignmentDao) {
		this.treatmentAssignmentDao = treatmentAssignmentDao;
	}
	
	public TreatmentAssignmentDao getTreatmentAssignmentDao() {
		return treatmentAssignmentDao;
	}
	
	public CtcTermDao getCtcTermDao() {
		return ctcTermDao;
	}
	
	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}
	
	public CtcCategoryDao getCtcCategoryDao() {
		return ctcCategoryDao;
	}
	
	public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
		this.ctcCategoryDao = ctcCategoryDao;
	}
	
	public LowLevelTermDao getLowLevelTermDao() {
		return lowLevelTermDao;
	}
	
	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}
	
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
		return adverseEventReportingPeriodDao;
	}
	
	public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	
	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}
	
	public AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}
	
	public EvaluationService getEvaluationService() {
		return evaluationService;
	}
	
	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}
	
	public ReportDefinitionDao getReportDefinitionDao() {
		return reportDefinitionDao;
	}
	
	public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao){
		this.reportDefinitionDao = reportDefinitionDao;
	}
	
	public void setRenderDecisionManagerFactoryBean(RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean) {
		this.renderDecisionManagerFactoryBean = renderDecisionManagerFactoryBean;
	}
	@Required
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}
	
	@Required
	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@Required
	public void setCaaersFieldConfigurationManagerFactory(CaaersFieldConfigurationManagerFactory caaersFieldConfigurationManagerFactory){
		this.caaersFieldConfigurationManagerFactory = caaersFieldConfigurationManagerFactory;
	}
}
