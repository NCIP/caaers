package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

/**
 * @author Sameer Sawant
 */
public abstract class AbstractExpeditedAdverseEventReportController extends
	AutomaticSaveAjaxableFormController<ExpeditedAdverseEventReportCommand, ExpeditedAdverseEventReport, ExpeditedAdverseEventReportDao> {

	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected ParticipantDao participantDao;

    protected StudyDao studyDao;

    protected StudyParticipantAssignmentDao assignmentDao;
    
    protected ExpeditedAdverseEventReportDao reportDao;
    
    protected ReportDefinitionDao reportDefinitionDao;

    protected ExpeditedReportTree expeditedReportTree;
    
    protected AbstractExpeditedAdverseEventReportController() {
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
        //setFlowFactory(createFlowFactory());
    }
    
    //protected abstract FlowFactory<ExpeditedAdverseEventReportCommand> createFlowFactory();
    
    @Override
    @SuppressWarnings( { "unchecked" })
    protected void onBind(HttpServletRequest request, Object oCommand, BindException errors)
                    throws Exception {
    	log.debug("In onBind");
        super.onBind(request, oCommand, errors);
        ((EditExpeditedAdverseEventReportCommand) oCommand).setNextPage(getTargetPage(request,
                        getCurrentPage(request)));
    }
    
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
                    throws Exception {
    	
    	ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, "aeReport", reportDao);
    }
    
    @Override
    @SuppressWarnings( { "unchecked", "RawUseOfParameterizedType" })
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page)
                    throws Exception {
    	
    	log.debug("In referenceData");
    	
        ExpeditedAdverseEventReportCommand cmd = (ExpeditedAdverseEventReportCommand) oCommand;
        Map<String, Object> refdata = super.referenceData(request, cmd, errors, page);
     
        return refdata;
    }
    
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        log.debug("In supressValidation");
    	return request.getParameter(AJAX_SUBVIEW_PARAMETER) != null;
    }
    
    @Override
    protected boolean shouldSave(HttpServletRequest request,
                    ExpeditedAdverseEventReportCommand command,
                    Tab<ExpeditedAdverseEventReportCommand> tab) {
    	Object isAjax = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (isAjax != null || 1 != getCurrentPage(request)) {
            return false;
        }
       return true;
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
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object oCommand, BindException errors) throws Exception {
    	log.debug("In processFinish");
        ExpeditedAdverseEventReportCommand command = (ExpeditedAdverseEventReportCommand) oCommand;
        save(command, errors);
        log.debug("After calling save on expedited report (report.version :" + command.getAeReport().getVersion() );
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        log.debug("Returning from processFinish");
        return new ModelAndView("redirectToAeList", model);
       
    }
    
    @Override
    protected ExpeditedAdverseEventReportDao getDao() {
        return reportDao;
    }

    @Override
    protected ExpeditedAdverseEventReport getPrimaryDomainObject(
                    ExpeditedAdverseEventReportCommand command) {
        return command.getAeReport();
    }
    
    /*public void setReportDao(ExpeditedAdverseEventReportDao reportDao) {
        this.reportDao = reportDao;
    }*/
    
    public void setReportDao(ExpeditedAdverseEventReportDao reportDao) {
		this.reportDao = reportDao;
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
}