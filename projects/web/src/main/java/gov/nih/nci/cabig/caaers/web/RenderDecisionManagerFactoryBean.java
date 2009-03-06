package gov.nih.nci.cabig.caaers.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RenderDecisionManagerFactoryBean implements ApplicationContextAware {
	private ApplicationContext ctx;
	
	
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	public RenderDecisionManager getRenderDecisionManager(){
		return (RenderDecisionManager)ctx.getBean("renderDecisionManagerBean");
	}

	
}
