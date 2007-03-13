package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.aegis.type.Type;
import org.codehaus.xfire.aegis.type.TypeMapping;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbType;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.ServiceFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleExecutionWebServiceClient {

	private static final String DEFAULT_SERVER = "localhost";
	private static final String DEFAULT_PORT = "5050";
	private static final String DEFAULT_CONTEXT_NAME = "rules";
	
	private static final String RULE_EXECUTION_SERVICE_URL = "http://"
			+ DEFAULT_SERVER + ":" + DEFAULT_PORT + "/" + DEFAULT_CONTEXT_NAME
			+ "/services/RuleExecutionServiceImpl";

	private static final String DEFAULT_RULE_EXECUTION_SERVICE_LOCAL_URL = "xfire.local://rules/services/RuleExecutionServiceImpl";
	
	
	//private RuleExecutionServiceImpl ruleExecutionService;

/*	protected void setUp() throws Exception {
		this.ruleExecutionService = new RuleExecutionServiceImpl();
	}
*/	

	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			new RuleExecutionWebServiceClient().testExecuteRuleRemote();
		} else {
			new RuleExecutionWebServiceClient().testExecuteRuleLocal();
		}
	}
	
	public void testExecuteRuleRemote() throws Exception {
		String bindUri = "URI_1";
		bindUri = "gov.nih.nci.cabig.caaers.rules";
		List<AdverseEventSDO> inObjects = new ArrayList<AdverseEventSDO>();
		StudySDO study = new StudySDO();
		study.setPrimarySponsorCode("SC_1");
		AdverseEventSDO sdo = new AdverseEventSDO();
		sdo.setTerm("aaa");
		inObjects.add(sdo);
		
		RuleExecutionService service = getRemoteRuleExecutionService();

//		RuleExecutionInfo executionInfo = new RuleExecutionInfo();
//		executionInfo.setBindUri(bindUri);
//		executionInfo.getInputObjects().add(study);
//		service.fireRules(executionInfo);
		//service.fireRules(bindUri);
		service.fireRules(bindUri, study, inObjects);
		//service.fireRules(bindUri, inObjects);
	}

	
	public void testExecuteRuleLocal() throws Exception {
		String bindUri = "URI_1";
		bindUri = "gov.nih.nci.cabig.caaers.rules";
		List inObjects = new ArrayList();
		StudySDO study = new StudySDO();
		study.setPrimarySponsorCode("SC_1");
		inObjects.add(study);
		RuleExecutionService service = getLocalRuleExecutionService();

		//RuleExecutionInfo executionInfo = new RuleExecutionInfo();
		//executionInfo.setBindUri(bindUri);
		//executionInfo.getInputObjects().add(study);
		//service.fireRules(executionInfo);
		service.fireRules(bindUri, study, inObjects);
	}
	
	private RuleExecutionService getRemoteRuleExecutionService()
			throws MalformedURLException {
/*		ServiceFactory factory = new AnnotationServiceFactory(new Jsr181WebAnnotations(), XFireFactory.newInstance().getXFire().getTransportManager(), 
				  new AegisBindingProvider(new JaxbTypeRegistry()));
*/		
		ServiceFactory factory = new ObjectServiceFactory(XFireFactory.newInstance().getXFire().getTransportManager(), 
				  new AegisBindingProvider(new JaxbTypeRegistry()));
		
		//ServiceFactory factory = new AnnotationServiceFactory();
		
		Service serviceModel = factory.create(RuleExecutionService.class);

/*		TypeMapping typeMapping = ((AegisBindingProvider) serviceModel.getBindingProvider()).getTypeMapping(serviceModel);
		Type badObjectTypeOverride = typeMapping.getType(new QName("http://www.w3.org/2001/XMLSchema","anyType",""));
		typeMapping.removeType(badObjectTypeOverride);*/
		
		ArrayList packages = new ArrayList();
		packages.add("gov.nih.nci.cabig.caaers.rules.domain");
		serviceModel.setProperty(JaxbType.SEARCH_PACKAGES, packages);		
		RuleExecutionService service = (RuleExecutionService) new XFireProxyFactory()
				.create(serviceModel, RULE_EXECUTION_SERVICE_URL);
		return service;
	}	
	
	private RuleExecutionService getLocalRuleExecutionService() throws MalformedURLException {
		Service serviceModel = new AnnotationServiceFactory()
				.create(RuleExecutionService.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		RuleExecutionService service = (RuleExecutionService) factory.create(
				serviceModel, DEFAULT_RULE_EXECUTION_SERVICE_LOCAL_URL);
		return service;
	}
}