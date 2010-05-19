package gov.nih.nci.cabig.caaers.tools.spring;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;

public class EventMulticasterFactoryBean implements FactoryBean, InitializingBean {
	
	private SimpleApplicationEventMulticaster simpleApplicationEventMulticaster;
	private TaskExecutor  taskExecutor;
	
	public Object getObject() throws Exception {
		Boolean synchronous = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.SYNCHRONOUS_EVENTS);
		if (!synchronous) {
			simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);
		}
		return this.simpleApplicationEventMulticaster;
	}

	public Class getObjectType() {
		// TODO Auto-generated method stub
		return SimpleApplicationEventMulticaster.class;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setSimpleApplicationEventMulticaster(
			SimpleApplicationEventMulticaster simpleApplicationEventMulticaster) {
		this.simpleApplicationEventMulticaster = simpleApplicationEventMulticaster;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}



}
