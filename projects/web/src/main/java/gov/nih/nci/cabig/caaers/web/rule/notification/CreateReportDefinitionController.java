package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateReportDefinitionController extends AbstractTabbedFlowFormController<RuleInputCommand> {

	private ReportDefinitionDao rpDefDao;
	private Map<String, String> roles;
	
	public CreateReportDefinitionController() {
		initFlow();
		//setCommandClass(NotificationCommand.class);
	}

    protected void initFlow() {
        setFlow(new Flow<RuleInputCommand>(getFlowName()));
        FirstTab firstTab = new FirstTab();
        ReportDeliveryDefinitionTab deliveryDefTab = new ReportDeliveryDefinitionTab();
        SecondTab secondTab = new SecondTab();
        ThirdTab thirdTab = new ThirdTab();
        
        getFlow().addTab(firstTab);
        getFlow().addTab(deliveryDefTab);
        getFlow().addTab(secondTab);
        getFlow().addTab(thirdTab);
    }
	
	protected String getFlowName() {
		return "Create Report Calendar";
	}
	
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		System.out.println(this);
		//return new NotificationCommand(allRoles, map, notificationDao);
		ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand();
		rpDefCmd.setReportDefinition(new ReportDefinition());
		rpDefCmd.setReportDefinitionDao(rpDefDao);
		rpDefCmd.setRoles(roles);
		return rpDefCmd;
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest req, HttpServletResponse res, Object cmd, BindException arg3) throws Exception {
		ReportDefinitionCommand rpDefCmd = (ReportDefinitionCommand)cmd;
		rpDefDao.save(rpDefCmd.getReportDefinition());
        Map<String, Object> model = new ModelMap();
        //model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToNotificationList", model);
	}

	/**
	 * @return the reportCalendarTemplateDao
	 */
	public ReportDefinitionDao getRpDefDao() {
		return rpDefDao;
	}

	/**
	 * @param rpDefDao the {@link ReportDefinitionDao} to set
	 */
	public void setRpDefDao(ReportDefinitionDao rdDao) {
		this.rpDefDao = rdDao;
	}
	
	public void setRoles(Map<String,String> roleList){
		this.roles = roleList;
	}

	public Map<String,String> getAllRoles(){
		return roles;
	}
//
//	public Map getMap() {
//		return map;
//	}
//
//	public void setMap(Map map) {
//		this.map = map;
//	}
//
	

}