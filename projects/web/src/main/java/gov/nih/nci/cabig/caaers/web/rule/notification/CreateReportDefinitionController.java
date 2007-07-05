package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateReportDefinitionController extends AbstractTabbedFlowFormController<ReportDefinitionCommand> {
	
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	public static final String AJAX_REQUEST_PARAMETER = "isAjax";
	
	private ReportDefinitionDao rpDefDao;
	private Map<String, String> roles;
	
	public CreateReportDefinitionController() {
        setAllowDirtyForward(false);
        setAllowDirtyBack(false);
        initFlow();
	}
	
	//initializes the flow
    protected void initFlow() {
        setFlow(new Flow<ReportDefinitionCommand>(getFlowName()));
        FirstTab firstTab = new FirstTab();
        ReportDeliveryDefinitionTab deliveryDefTab = new ReportDeliveryDefinitionTab();
        SecondTab secondTab = new SecondTab();
        ThirdTab thirdTab = new ThirdTab();
        
        getFlow().addTab(firstTab);
        getFlow().addTab(deliveryDefTab);
        getFlow().addTab(secondTab);
        getFlow().addTab(thirdTab);
       // getFlow().addTab(new FirstTab());
    }
	
	protected String getFlowName() {
		return "Create Report Definition";
	}
	
	/**
	 * In the create flow of report definiton, we should make sure that
	 * there exists at least one ReportDeliveryDefinition.
	 */
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		//return new NotificationCommand(roles, rpDefDao);
		ReportDefinition rpDef = new ReportDefinition();
		ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand();
		rpDefCmd.setReportDefinition(rpDef);
		rpDefCmd.setReportDefinitionDao(rpDefDao);
		rpDefCmd.setRoles(roles);
		return rpDefCmd;
	}

	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		 binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		ControllerTools.registerEnumEditor(binder, ReportFormat.class);
	}
	
	@Override
	protected ModelAndView processFinish(HttpServletRequest req, HttpServletResponse res, Object cmd, BindException arg3) throws Exception {
		ReportDefinitionCommand rpDefCmd = (ReportDefinitionCommand)cmd;
		rpDefDao.save(rpDefCmd.getReportDefinition());
        Map<String, Object> model = new ModelMap();
        //model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToNotificationList", model);
	}
	
	@Override
	protected String getViewName(HttpServletRequest request, Object command, int page) {
        Object subviewName = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "rule/notification/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }
	
    private Object findInRequest(HttpServletRequest request, String attributName){
   
    	Object attr = request.getParameter(attributName);
    	if(attr == null) attr = request.getAttribute(attributName);
    	return attr;
    }

	@Override
	protected boolean suppressValidation(HttpServletRequest request, Object command) {
		Object isAjax = findInRequest(request, AJAX_REQUEST_PARAMETER);
		if(isAjax != null) return true;
		return super.suppressValidation(request, command);
	}
	
	public ReportDefinitionDao getRpDefDao() {
		return rpDefDao;
	}

	public void setRpDefDao(ReportDefinitionDao rdDao) {
		this.rpDefDao = rdDao;
	}
	
	public void setRoles(Map<String,String> roleList){
		this.roles = roleList;
	}

	public Map<String,String> getAllRoles(){
		return roles;
	}
}