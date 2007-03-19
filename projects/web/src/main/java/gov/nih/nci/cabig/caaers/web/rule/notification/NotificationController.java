package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.tabbedflow.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;


public class NotificationController extends AbstractTabbedFlowFormController<RuleInputCommand> {

	public NotificationController() {
		initFlow();
		setCommandClass(NotificationCommand.class);
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
	protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}