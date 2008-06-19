package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

public class CaptureAdverseEventController extends AutomaticSaveAjaxableFormController<CaptureAdverseEventInputCommand, AdverseEventReportingPeriod, AdverseEventReportingPeriodDao> {
	
	private static final String AJAX_SUBVIEW_PARAMETER = "subview";
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	private StudyParticipantAssignmentDao assignmentDao;
	private TreatmentAssignmentDao treatmentAssignmentDao;
	private CtcTermDao ctcTermDao;
	private CtcCategoryDao ctcCategoryDao;
	private LowLevelTermDao lowLevelTermDao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;

	
	
	@Override
	protected AdverseEventReportingPeriodDao getDao() {
		return null;
	}

	@Override
	protected AdverseEventReportingPeriod getPrimaryDomainObject(CaptureAdverseEventInputCommand cmd) {
		//TODO should be refined.
		return new AdverseEventReportingPeriod();
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3) throws Exception {
		return null;
	}
	
	@Override
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
		ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        //ControllerTools.registerDomainObjectEditor(binder, "aeReport", reportDao);
        //ControllerTools.registerDomainObjectEditor(binder, "aeRoutineReport", routineReportDao);
        ControllerTools.registerDomainObjectEditor(binder, "adverseEventReportingPeriod", adverseEventReportingPeriodDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcTermDao);
        //ControllerTools.registerDomainObjectEditor(binder, studyAgentDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcCategoryDao);
        ControllerTools.registerDomainObjectEditor(binder, lowLevelTermDao);
        //ControllerTools.registerDomainObjectEditor(binder, treatmentAssignmentDao);
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerEnumEditor(binder, Grade.class);
        ControllerTools.registerEnumEditor(binder, Hospitalization.class);
        ControllerTools.registerEnumEditor(binder, Attribution.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		CaptureAdverseEventInputCommand cmd = new CaptureAdverseEventInputCommand(assignmentDao);
		
		return cmd;
	}
	
	@Override
	public FlowFactory<CaptureAdverseEventInputCommand> getFlowFactory() {
		return new FlowFactory<CaptureAdverseEventInputCommand>() {
			public Flow<CaptureAdverseEventInputCommand> createFlow(CaptureAdverseEventInputCommand cmd) {
				Flow<CaptureAdverseEventInputCommand> flow = new Flow<CaptureAdverseEventInputCommand>("CaptureAdverseEventFlow");
				flow.addTab(new BeginTab<CaptureAdverseEventInputCommand>());
				flow.addTab(new AdverseEventCaptureTab());
				return flow;
			}
		};
	}
	
	@Override
    protected boolean suppressValidation(final HttpServletRequest request) {

        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null) {
            return true;
        }
        String action = (String) findInRequest(request, "_action");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
            return true;
        }
        return super.suppressValidation(request);
    }
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {

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
		return false;
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
}
