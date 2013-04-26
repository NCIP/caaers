/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.PersonRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManagerFactoryBean;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractAdverseEventInputController extends AutomaticSaveAjaxableFormController<ExpeditedAdverseEventInputCommand, ExpeditedAdverseEventReport, ExpeditedAdverseEventReportDao> {

    public static final String AJAX_SUBVIEW_PARAMETER = "subview";

    private static final int SUBMISSION_PAGE = 10;

    private static final String UNFILLED_TAB_KEY = "UNFILLED_TABS";

    private static final String MANDATORY_TAB_KEY = "MANDATORY_TABS";
    
    private static final String MANDATORY_FIELD_TAB_KEY = "MANDATORY_FIELD_TABS";

    protected final Log log = LogFactory.getLog(getClass());

    protected ParticipantDao participantDao;

    protected StudyDao studyDao;

    protected StudyParticipantAssignmentDao assignmentDao;

    protected CtcTermDao ctcTermDao;
    protected LowLevelTermDao lowLevelTermDao;
    protected ConditionDao conditionDao;

    protected AgentDao agentDao;

    protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    protected StudyAgentDao studyAgentDao;

    protected CtepStudyDiseaseDao ctepStudyDiseaseDao;
    
    protected MeddraStudyDiseaseDao meddraStudyDiseaseDao;
    
    protected StudyConditionDao studyConditionDao;

    protected AnatomicSiteDao anatomicSiteDao;

    protected PriorTherapyDao priorTherapyDao;

    protected CtcCategoryDao ctcCategoryDao;

    protected PreExistingConditionDao preExistingConditionDao;

    protected TreatmentAssignmentDao treatmentAssignmentDao;

    protected LabTermDao labTermDao;

    protected ChemoAgentDao chemoAgentDao;

    protected LabCategoryDao labCategoryDao;

    protected InterventionSiteDao interventionSiteDao;

    protected NowFactory nowFactory;

    protected EvaluationService evaluationService;

    protected ReportDefinitionDao reportDefinitionDao;

    protected ExpeditedReportTree expeditedReportTree;
    
    protected AdverseEventReportingPeriodDao reportingPeriodDao;
	
    protected RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean;
    
    protected ReportRepository reportRepository;
    
    private Configuration configuration;

    private PersonDao personDao;

    protected StudyDeviceDao studyDeviceDao;

    protected OtherInterventionDao otherInterventionDao;
    
    protected AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

    protected UserRepository userRepository;

    protected PersonRepository personRepository;



	
    protected AbstractAdverseEventInputController() {
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
        setFlowFactory(createFlowFactory());
    }


    public boolean getUnidentifiedMode(){
        boolean unidentifiedMode;
        if (configuration.get(Configuration.UNIDENTIFIED_MODE) == null) unidentifiedMode = false;
        else unidentifiedMode =  configuration.get(Configuration.UNIDENTIFIED_MODE);
        return unidentifiedMode;
    }

    protected abstract FlowFactory<ExpeditedAdverseEventInputCommand> createFlowFactory();

    @Override
    @SuppressWarnings( { "unchecked" })
    protected void onBind(HttpServletRequest request, Object oCommand, BindException errors) throws Exception {
    	log.debug("In onBind");
        super.onBind(request, oCommand, errors);
        ((ExpeditedAdverseEventInputCommand) oCommand).setNextPage(getTargetPage(request, getCurrentPage(request)));
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.setDisallowedFields("aeReport","study","participant");
        super.initBinder(request, binder);
        ControllerTools.registerDomainObjectEditor(binder, ctcTermDao);
        ControllerTools.registerDomainObjectEditor(binder, lowLevelTermDao);
        ControllerTools.registerDomainObjectEditor(binder, conditionDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);

        ControllerTools.registerDomainObjectEditor(binder, studyAgentDao);
        
        DiseaseCodeTerm diseaseCodingTerm = (DiseaseCodeTerm)request.getAttribute("diseaseCodingTerm");
        if(diseaseCodingTerm != null){
        	if(diseaseCodingTerm.equals(DiseaseCodeTerm.MEDDRA)){
        		binder.registerCustomEditor(AbstractStudyDisease.class, new DaoBasedEditor(meddraStudyDiseaseDao));
            }else if(diseaseCodingTerm.equals(DiseaseCodeTerm.OTHER)){
            	binder.registerCustomEditor(AbstractStudyDisease.class, new DaoBasedEditor(studyConditionDao));
            }else {
            	binder.registerCustomEditor(AbstractStudyDisease.class, new DaoBasedEditor(ctepStudyDiseaseDao));
            }
        }
        
        
        ControllerTools.registerDomainObjectEditor(binder, anatomicSiteDao);
        ControllerTools.registerDomainObjectEditor(binder, personDao);
        ControllerTools.registerDomainObjectEditor(binder, priorTherapyDao);
        ControllerTools.registerDomainObjectEditor(binder, preExistingConditionDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcCategoryDao);
        ControllerTools.registerDomainObjectEditor(binder, treatmentAssignmentDao);
        ControllerTools.registerDomainObjectEditor(binder, reportDefinitionDao);
        ControllerTools.registerDomainObjectEditor(binder, labTermDao);
        ControllerTools.registerDomainObjectEditor(binder, chemoAgentDao);
        ControllerTools.registerDomainObjectEditor(binder, interventionSiteDao);
        ControllerTools.registerDomainObjectEditor(binder, studyDeviceDao);
        ControllerTools.registerDomainObjectEditor(binder, otherInterventionDao);

        
        ControllerTools.registerEnumEditor(binder, Grade.class);
        ControllerTools.registerEnumEditor(binder, Hospitalization.class);
        ControllerTools.registerEnumEditor(binder, Attribution.class);
        ControllerTools.registerEnumEditor(binder, PostAdverseEventStatus.class);
        ControllerTools.registerEnumEditor(binder, RadiationAdministration.class);
        ControllerTools.registerEnumEditor(binder, Availability.class);
        ControllerTools.registerEnumEditor(binder, AgentAdjustment.class);
        ControllerTools.registerEnumEditor(binder, EventStatus.class);
    }

    @Override
    protected int getInitialPage(HttpServletRequest request) {
    	log.debug("In gettInitialPage");
        boolean isActionAvailable = request.getParameter("action") != null ? true : false;

        if (isActionAvailable && request.getParameter("action").equals("reportSubmission")) {
            log.debug("This is a report Submission");
            return SUBMISSION_PAGE;
        }
        if (isActionAvailable && request.getParameter("action").equals("create")) {
            log.debug("This is a Create where the StudyParticipantAssignment is already defined");
            return 1;
        }
        // default behaviour
        return super.getInitialPage(request);
    }

    @Override
    @SuppressWarnings( { "unchecked", "RawUseOfParameterizedType" })
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page) throws Exception {
    	
    	log.debug("In referenceData");
    	
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand) oCommand;
        Map<String, Object> refdata = super.referenceData(request, cmd, errors, page);
        StringBuffer sb = new StringBuffer("notab");
        StringBuffer sbSections = new StringBuffer();
        StringBuffer sbMandatoryFieldTab = new StringBuffer();
        for (Tab<ExpeditedAdverseEventInputCommand> tab : getFlow(cmd).getTabs()) {
            if (tab instanceof AeTab) {
                AeTab aeTab = (AeTab) tab;
                sbSections.append(",").append(aeTab.isMandatory(cmd) ? tab.getShortTitle() : "");
                sb.append(",").append(aeTab.hasEmptyMandatoryFields(cmd, request) ? tab.getShortTitle() : "");
                sbMandatoryFieldTab.append(",").append(aeTab.hasMandatoryFields(cmd, request) ? tab.getShortTitle() : "");
            }
        }
        
        refdata.put(MANDATORY_TAB_KEY, sbSections.toString());
        refdata.put(UNFILLED_TAB_KEY, sb.toString());
        refdata.put(MANDATORY_FIELD_TAB_KEY, sbMandatoryFieldTab.toString());
        
        if (displaySummary(page)) {
            refdata.put("aesummary", cmd.getAeReport().getSummary(getUnidentifiedMode()));
        }
        
        refdata.put("showReportContextMenu", displayReportContextMenu(page));
        
       
        return refdata;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        log.debug("In supressValidation");
    	return request.getParameter(AJAX_SUBVIEW_PARAMETER) != null || isFinishRequest(request);
    }

    @Override
    protected boolean shouldSave(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Tab<ExpeditedAdverseEventInputCommand> tab) {
    	log.debug("In should save");
        return  request.getParameter(AJAX_SUBVIEW_PARAMETER) == null;
    }

    protected boolean displaySummary(int page) {
        return true;
    }
    
    protected boolean displayReportContextMenu(int page){
    	return page != SUBMISSION_PAGE;
    }
    
    /**
     * Adds ajax sub-page view capability. TODO: factor this into main tabbed flow controller.
     */
    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        log.debug("In getViewName");
    	String subviewName = request.getParameter(AJAX_SUBVIEW_PARAMETER);
        
        if (subviewName != null) {
        	log.debug("Identified as Ajax view");
            // for side-effects:
            super.getViewName(request, command, page);
            return "ae/ajax/" + subviewName;
        } else {
        	log.debug("Identified as non Ajax view");
            return super.getViewName(request, command, page);
        }
    }

    @Override
    protected Object currentFormObject(HttpServletRequest request, Object oCommand) throws Exception {
        EditExpeditedAdverseEventCommand expeditedCommand = (EditExpeditedAdverseEventCommand) super.currentFormObject(request, oCommand);
        //reuse existing command for Ajax requests
        if( request.getParameter(AJAX_SUBVIEW_PARAMETER) != null || isAjaxRequest(request)) return expeditedCommand;


        ExpeditedAdverseEventReport aeReport = expeditedCommand.getAeReport();
        if(aeReport != null && aeReport.getId() != null){
            expeditedCommand.setAeReport(expeditedAdverseEventReportDao.getById(aeReport.getId()));
            //initializing the review comments collection
            for(Report r: expeditedCommand.getAeReport().getActiveReports()){
            	r.getReviewCommentsInternal().size();
            }

        }else{
            aeReport.setReportingPeriod(reportingPeriodDao.getById(aeReport.getReportingPeriod().getId()));
        }

        if(expeditedCommand.getStudy() != null){
            DiseaseCodeTerm diseaseCodingTerm = expeditedCommand.getAeReport().getStudy().getDiseaseTerminology().getDiseaseCodeTerm();
            request.setAttribute("diseaseCodingTerm", diseaseCodingTerm);
        }

        return expeditedCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
    	log.debug("In processFinish");
        ExpeditedAdverseEventInputCommand command = (ExpeditedAdverseEventInputCommand) oCommand;
        save(command, errors);
        log.debug("After calling save on expedited report (report.version :" + command.getAeReport().getVersion() );
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        log.debug("Returning from processFinish");
        return new ModelAndView("redirectToAeList", model);
       
    }

    @Override
    protected ExpeditedAdverseEventReportDao getDao() {
        return expeditedAdverseEventReportDao;
    }

    @Override
    protected ExpeditedAdverseEventReport getPrimaryDomainObject(ExpeditedAdverseEventInputCommand command) {
        return command.getAeReport();
    }

    // //// CONFIGURATION

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

    public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
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


    public PreExistingConditionDao getPreExistingConditionDao() {
        return preExistingConditionDao;
    }

    public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
        this.preExistingConditionDao = preExistingConditionDao;
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

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    public TreatmentAssignmentDao getTreatmentAssignmentDao() {
        return treatmentAssignmentDao;
    }

    public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
        this.treatmentAssignmentDao = treatmentAssignmentDao;
    }

    public MeddraStudyDiseaseDao getMeddraStudyDiseaseDao() {
        return meddraStudyDiseaseDao;
    }

    public void setMeddraStudyDiseaseDao(MeddraStudyDiseaseDao meddraStudyDiseaseDao) {
        this.meddraStudyDiseaseDao = meddraStudyDiseaseDao;
    }

    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }

    public LabTermDao getLabTermDao() {
        return labTermDao;
    }

    public void setLabTermDao(LabTermDao labTermDao) {
        this.labTermDao = labTermDao;
    }

    public LabCategoryDao getLabCategoryDao() {
        return labCategoryDao;
    }

    public void setLabCategoryDao(LabCategoryDao labCategoryDao) {
        this.labCategoryDao = labCategoryDao;
    }

    public ChemoAgentDao getChemoAgentDao() {
        return chemoAgentDao;
    }

    public void setChemoAgentDao(ChemoAgentDao chemoAgentDao) {
        this.chemoAgentDao = chemoAgentDao;
    }

    public InterventionSiteDao getInterventionSiteDao() {
        return interventionSiteDao;
    }

    public void setInterventionSiteDao(InterventionSiteDao interventionSiteDao) {
        this.interventionSiteDao = interventionSiteDao;
    }
    
    public void setReportingPeriodDao(
			AdverseEventReportingPeriodDao reportingPeriodDao) {
		this.reportingPeriodDao = reportingPeriodDao;
	}
    
	
	public void setRenderDecisionManagerFactoryBean(RenderDecisionManagerFactoryBean renderDecisionManagerFactoryBean) {
		this.renderDecisionManagerFactoryBean = renderDecisionManagerFactoryBean;
	}
 
	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

    public ConditionDao getConditionDao() {
        return conditionDao;
    }

    public void setConditionDao(ConditionDao conditionDao) {
        this.conditionDao = conditionDao;
    }

    public StudyConditionDao getStudyConditionDao() {
        return studyConditionDao;
    }

    public void setStudyConditionDao(StudyConditionDao studyConditionDao) {
        this.studyConditionDao = studyConditionDao;
    }
    
	@Required
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository(){
		return adverseEventRoutingAndReviewRepository;
	}
	
	public void setAdverseEventRoutingAndReviewRepository(AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository){
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}

    public StudyDeviceDao getStudyDeviceDao() {
        return studyDeviceDao;
    }

    public void setStudyDeviceDao(StudyDeviceDao studyDeviceDao) {
        this.studyDeviceDao = studyDeviceDao;
    }

    public OtherInterventionDao getOtherInterventionDao() {
        return otherInterventionDao;
    }

    public void setOtherInterventionDao(OtherInterventionDao otherInterventionDao) {
        this.otherInterventionDao = otherInterventionDao;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
