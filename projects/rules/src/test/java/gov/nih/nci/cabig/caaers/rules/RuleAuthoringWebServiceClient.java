package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbServiceFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.ObjectInvoker;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleAuthoringWebServiceClient {

	private static final String DEFAULT_SERVER = "localhost";
	private static final String DEFAULT_PORT = "5050";
	private static final String DEFAULT_CONTEXT_NAME = "rules";
	
	private static final String RULE_EXECUTION_SERVICE_URL = "http://"
			+ DEFAULT_SERVER + ":" + DEFAULT_PORT + "/" + DEFAULT_CONTEXT_NAME
			+ "/services/RuleAuthoringService";

	private static final String DEFAULT_RULE_EXECUTION_SERVICE_LOCAL_URL = "xfire.local://rules/services/RuleAuthoringService";
	
	
	//private RuleExecutionServiceImpl ruleExecutionService;

/*	protected void setUp() throws Exception {
		this.ruleExecutionService = new RuleExecutionServiceImpl();
	}
*/	

	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
		//	new RuleAuthoringWebServiceClient().testExecuteRuleRemote();
			new RuleAuthoringWebServiceClient().testGetRulesByCategory();
		} else {
			//new RuleAuthoringWebServiceClient().testExecuteRuleLocal();
		}
	}
	
	public void testExecuteRuleRemote() throws Exception {
		String bindUri = "URI_1";
		bindUri = "gov.nih.nci.cabig.caaers.rules";
		List<AdverseEventSDO> inObjects = new ArrayList<AdverseEventSDO>();
		StudySDO study = new StudySDO();
		study.setPrimarySponsorCode("SC_1");
		AdverseEventSDO sdo = new AdverseEventSDO();
		inObjects.add(sdo);
		
		RuleAuthoringService service = getRemoteRuleAuthoringService();

//		RuleExecutionInfo executionInfo = new RuleExecutionInfo();
//		executionInfo.setBindUri(bindUri);
//		executionInfo.getInputObjects().add(study);
//		service.fireRules(executionInfo);
		//service.fireRules(bindUri);
		
		
		List<RuleSet> x  =  service.getAllRuleSets();
		for(int i = 0 ; i < x.size(); i++) {
			System.out.println(x.get(i));
			System.out.println(x.get(i).getName());
		}
		x.size();
		
		//service.createRuleSet(getRuleSet());
		//service.fireRules(bindUri, inObjects);
	}

	private RuleSet getRuleSet() {
		RuleSet ruleSet = new RuleSet();
		ruleSet.setName("gov.nih.nci.cabig.caaers.rules");
		ruleSet.setDescription("Hai");
		Rule newRule = new Rule();
		MetaData metaData = new MetaData();
		metaData.setName("First Rule");
		newRule.setMetaData(metaData);
    	Condition condition = new Condition();
		Column column = new Column();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO");
		column.setIdentifier("adverseEventSDO1");
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("grade");
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue("2");
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		column.getFieldConstraint().add(fieldConstraint);
		condition.getColumn().add(column);

		column = new Column();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO");
		column.setIdentifier("adverseEventSDO2");
		fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("term");
		literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue("Term One");
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		column.getFieldConstraint().add(fieldConstraint);
		condition.getColumn().add(column);
		
		newRule.setCondition(condition);
		ruleSet.getRule().add(newRule);

		newRule = new Rule();
		metaData = new MetaData();
		metaData.setName("Second Rule");
		newRule.setMetaData(metaData);
		condition = new Condition();
		column = new Column();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO");
		column.setIdentifier("adverseEventSDO2");
		fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("term");
		literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue("Sample Term");
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		column.getFieldConstraint().add(fieldConstraint);
		condition.getColumn().add(column);
		newRule.setCondition(condition);  	
		ruleSet.getRule().add(newRule);
		return ruleSet;
	}
	
	public void testGetRulesByCategory() throws RemoteException, MalformedURLException, JAXBException {
		RuleAuthoringService service = getRemoteRuleAuthoringService();
		List rules = service.getRulesByCategory("/National Cancer Institute/default/AML-MDS 9911");
		Rule rule = service.getRule("65f28bca-1d44-4866-ae1d-5dde24e692d5");
		System.out.println("Rules = "+ rules);
	}
	
/*	public void testExecuteRuleLocal() throws Exception {
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
	}*/
	
	private RuleAuthoringService getRemoteRuleAuthoringService()
			throws MalformedURLException, JAXBException {
		ObjectServiceFactory factory = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
/*		factory = new AnnotationServiceFactory(new Jsr181WebAnnotations(), xFire.getTransportManager(), 
				  new AegisBindingProvider(new JaxbTypeRegistry()));
*/		factory = new JaxbServiceFactory();
//		ServiceFactory factory = new AnnotationServiceFactory();
		
		//factory = new JaxbServiceFactory(xFire.getTransportManager());
        //factory.setStyle(SoapConstants.STYLE_DOCUMENT);		
		
        
		Service service = factory.create(RuleAuthoringService.class);
        service.setProperty(ObjectInvoker.SERVICE_IMPL_CLASS, RuleAuthoringServiceImpl.class);
        //service.setProperty(AbstractBinding.OPERATION_KEY, "createRuleSet");
        RuleAuthoringService ruleAuthoringService = (RuleAuthoringService) new XFireProxyFactory()
				.create(service, RULE_EXECUTION_SERVICE_URL);
		return ruleAuthoringService;
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