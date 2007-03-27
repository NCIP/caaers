package gov.nih.nci.cabig.caaers.rules.runtime.action;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.Action;
import gov.nih.nci.cabig.caaers.rules.brxml.Notification;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleContext;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class ActionDispatcher {

	Logger log = Logger.getLogger(ActionDispatcher.class.toString());
	
	private ApplicationContext applicationContext;
	
	public ActionDispatcher() {
		this.applicationContext = new ClassPathXmlApplicationContext(
                new String[] { "classpath*:config/spring/applicationContext-rules-email.xml" });		
	}
	
	public void dispatchAction(String actionId, RuleContext ruleContext) {
		ActionContext actionContext = fetchAction(actionId);
		Action action = actionContext.getAction();
		NotificationHandler notificationHandler = null;
		if(action instanceof Notification) {
			Notification notification = (Notification)action;
			String actionHandlerClass = notification.getActionHandlerClass();
			if(actionHandlerClass == null) {
				//actionHandlerClass = DefaultEmailNotificationHandler.class.getName();
				log.info("Could not find a actionHandler class. Using the Default class " + actionHandlerClass);
				notificationHandler = (NotificationHandler)this.applicationContext.getBean("defaultEmailNotificationHandler");
			} else{
				notificationHandler = (NotificationHandler)loadObject(actionHandlerClass);
			}
			notificationHandler.performNotify(actionContext, ruleContext);
		}
	}
	
	/**
	 * Fetch the Action Type based on action Id from DB
	 * 
	 * @param actionId 
	 * */
	private ActionContext fetchAction(String actionId) {
		log.info("Action with action Id = " + actionId + "found. Fetching it...");
		System.out.println("Action with action Id = " + actionId + "found. Fetching it...");
		
		ActionContext actionContext = new ActionContext();
		
		//Imagining that i found a notification action
		Notification notification = new Notification();
		notification.setActionId(actionId);
		
		actionContext.setAction(notification);
		return actionContext;
	}
	
	public Object loadObject(String className) {
		try {
			return loadClass(className).newInstance();
		} catch (InstantiationException e) {
			throw new RuleException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}
	
	public Class loadClass(String className)throws RuleException {
		try {
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		} catch (ClassNotFoundException e) {
			throw new RuleException(e.getMessage(), e);
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}