package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

import java.net.MalformedURLException;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.ServiceFactory;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class ServiceLocator {

	private static final String DEFAULT_SERVER = "localhost";
	private static final String DEFAULT_PORT = "5050";
	private static final String DEFAULT_CONTEXT_NAME = "rules";
	
	private static final String RULE_AUTHORING_SERVICE_URL = "http://"
		+ DEFAULT_SERVER + ":" + DEFAULT_PORT + "/" + DEFAULT_CONTEXT_NAME
		+ "/services/RuleAuthoringService";
	
	private static final String RULE_EXECUTION_SERVICE_URL = "http://"
			+ DEFAULT_SERVER + ":" + DEFAULT_PORT + "/" + DEFAULT_CONTEXT_NAME
			+ "/services/RuleExecutionService";
	
	private static final String RULE_DEPLOYMNT_SERVICE_URL = "http://"
		+ DEFAULT_SERVER + ":" + DEFAULT_PORT + "/" + DEFAULT_CONTEXT_NAME
		+ "/services/RuleDeploymentService";
	
	
	private static final String DEFAULT_LOCAL_SERVICE_URL = "xfire.local:5050//rules/services/";
	
	private static final String DEFAULT_RULE_AUTHORING_LOCAL_SERVICE_URL = DEFAULT_LOCAL_SERVICE_URL + "/RuleAuthoringService";
	private static final String DEFAULT_RULE_DEPLOYMENT_LOCAL_SERVICE_URL = DEFAULT_LOCAL_SERVICE_URL + "/RuleDeploymentService";
	private static final String DEFAULT_RULE_EXECUTION_LOCAL_SERVICE_URL = DEFAULT_LOCAL_SERVICE_URL + "/RuleExecutionService";
	
	private static ServiceLocator instance; 
	
	private ServiceLocator() {
		
	}
	
	public static ServiceLocator getInstance() {
		if(instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	
	
	public RuleAuthoringService getRuleAuthoringService() {
		Service serviceModel = new AnnotationServiceFactory()
				.create(RuleExecutionService.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		RuleAuthoringService service;
		try {
			service = (RuleAuthoringService) factory.create(
					serviceModel, DEFAULT_RULE_AUTHORING_LOCAL_SERVICE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return service;
	}
	
	public RuleAuthoringService getRemoteRuleAuthoringService() {
		XFire xFire = XFireFactory.newInstance().getXFire();
		ServiceFactory factory = new AnnotationServiceFactory(new Jsr181WebAnnotations(), XFireFactory.newInstance().getXFire().getTransportManager(), 
				  new AegisBindingProvider(new JaxbTypeRegistry()));
		Service serviceModel = factory.create(RuleAuthoringService.class);
		RuleAuthoringService service;
		try {
			service = (RuleAuthoringService) new XFireProxyFactory()
					.create(serviceModel, RULE_AUTHORING_SERVICE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return service;
	}
	
	public RuleDeploymentService getRuleDeploymentService() {
		Service serviceModel = new AnnotationServiceFactory()
				.create(RuleExecutionService.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		RuleDeploymentService service;
		try {
			service = (RuleDeploymentService) factory.create(
					serviceModel, DEFAULT_RULE_DEPLOYMENT_LOCAL_SERVICE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return service;
	}

	public RuleDeploymentService getRemoteRuleDeploymentService() {
		XFire xFire = XFireFactory.newInstance().getXFire();
		ServiceFactory factory = new AnnotationServiceFactory(new Jsr181WebAnnotations(), XFireFactory.newInstance().getXFire().getTransportManager(), 
				  new AegisBindingProvider(new JaxbTypeRegistry()));
		Service serviceModel = factory.create(RuleDeploymentService.class);
		RuleDeploymentService service;
		try {
			service = (RuleDeploymentService) new XFireProxyFactory()
					.create(serviceModel, RULE_DEPLOYMNT_SERVICE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return service;
	}
	
	public RuleExecutionService getRemoteExecutionService(){
		XFire xFire = XFireFactory.newInstance().getXFire();
		ServiceFactory factory = new AnnotationServiceFactory(new Jsr181WebAnnotations(), XFireFactory.newInstance().getXFire().getTransportManager(), 
				  new AegisBindingProvider(new JaxbTypeRegistry()));
		Service serviceModel = factory.create(RuleExecutionService.class);
		RuleExecutionService service;
		try {
			service = (RuleExecutionService) new XFireProxyFactory()
					.create(serviceModel, RULE_EXECUTION_SERVICE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return service;		
	}
	
	public RuleExecutionService getRuleExecutionService() {
		Service serviceModel = new AnnotationServiceFactory()
				.create(RuleExecutionService.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		RuleExecutionService service;
		try {
			service = (RuleExecutionService) factory.create(
					serviceModel, DEFAULT_RULE_EXECUTION_LOCAL_SERVICE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return service;
	}
}
