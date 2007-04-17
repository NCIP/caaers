package gov.nih.nci.cabig.caaers.web.rule.notification;

import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public class NotificationController extends AbstractTabbedFlowFormController<RuleInputCommand> {

	private List<String> allRoles;
	
	private Map map;
	
	private NotificationDao notificationDao;
	
	public NotificationController() {
		initFlow();
		//setCommandClass(NotificationCommand.class);
	}

    protected void initFlow() {
        setFlow(new Flow<RuleInputCommand>(getFlowName()));
        getFlow().addTab(new FirstTab());
        getFlow().addTab(new SecondTab());
    }
	
	protected String getFlowName() {
		return "Create Notification";
	}
	
	@Override
	public Object formBackingObject(HttpServletRequest request) {
		return new NotificationCommand(allRoles, map, notificationDao);
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object command, BindException arg3) throws Exception {
		NotificationCommand notificationCommand = (NotificationCommand)command;
		notificationCommand.save();
        Map<String, Object> model = new ModelMap();
        //model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToNotificationList", model);
	}

	public List<String> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<String> roleList) {
		this.allRoles = roleList;
		((SecondTab)getFlow().getTab(1)).setAllRoles(roleList);
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
	

}