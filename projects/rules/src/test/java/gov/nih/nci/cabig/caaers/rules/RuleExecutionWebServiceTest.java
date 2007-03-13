package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.ArrayOfRule;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.codehaus.xfire.aegis.type.Type;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbServiceFactory;
import org.codehaus.xfire.jaxb2.JaxbType;
import org.codehaus.xfire.service.MessagePartInfo;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.ObjectInvoker;
import org.codehaus.xfire.soap.SoapConstants;
import org.codehaus.xfire.test.AbstractXFireTest;
import org.jdom.Document;


public class RuleExecutionWebServiceTest extends AbstractXFireTest {

	//private RuleExecutionServiceImpl ruleExecutionService;

    private Service service;
    private ObjectServiceFactory factory;
	
	protected void setUp() throws Exception {
		super.setUp();

        factory = new JaxbServiceFactory(getXFire().getTransportManager());
        factory.setStyle(SoapConstants.STYLE_DOCUMENT);
        
        // Set the schemas
        ArrayList<String> schemas = new ArrayList<String>();
        //TODO
        schemas.add(getTestFile("bin/schema/rules.xsd").getAbsolutePath());
        
        Map<String,Object> props = new HashMap<String,Object>();
        props.put(ObjectServiceFactory.SCHEMAS, schemas);
        
        service = factory.create(RuleAuthoringService.class,
                                  "RuleAuthoringService",
                                  "urn:RuleAuthoringService",
                                  props);

		factory = new JaxbServiceFactory();
		service = factory.create(RuleAuthoringService.class);        
        service.setProperty(ObjectInvoker.SERVICE_IMPL_CLASS, RuleAuthoringServiceImpl.class);
		getServiceRegistry().register(service);
	}
	
    public void testWsdl() throws Exception
    {
        Document doc = getWSDLDocument("RuleAuthoringService");
        printNode(doc);
        addNamespace("xsd", SoapConstants.XSD);
        
        assertValid("//xsd:schema[@targetNamespace='http://caaers.cabig.nci.nih.gov/rules/brxml'][1]", doc);
        assertInvalid("//xsd:schema[@targetNamespace='http://www.webservicex.net'][2]", doc);
    }
	
    public void testService() throws Exception {
		MessagePartInfo info = (MessagePartInfo) service.getServiceInfo()
				.getOperation("createRuleSet").getInputMessage()
				.getMessageParts().get(0);

		assertNotNull(info);

		Type type = (Type) info.getSchemaType();
		assertTrue(type instanceof JaxbType);

		assertTrue(type.isComplex());
		assertFalse(type.isWriteOuter());

		assertEquals(new QName("http://caaers.cabig.nci.nih.gov/rules/brxml",
				"ruleSet"), info.getName());

		Document response = invokeService("RuleAuthoringService",
				"createRuleSet.xml");

		addNamespace("rules", "http://caaers.cabig.nci.nih.gov/rules/brxml");
		//assertValid(
			//	"//s:Body/w:GetWeatherByZipCodeResponse/w:GetWeatherByZipCodeResult",
				//response);
	}
    
	public void testExecuteRule() throws Exception {

		RuleAuthoringService client = (RuleAuthoringService) new XFireProxyFactory(
				getXFire()).create(service,
				"xfire.local://RuleAuthoringService");
		RuleSet ruleSet = new RuleSet();
		ruleSet.setName("First RuleSet");
		ruleSet.setDescription("First RuleSet");
		Rule rule = new Rule();
		ArrayOfRule arrayOfRule = new ArrayOfRule();
		arrayOfRule.getRule().add(rule);
		ruleSet.setArrayOfRule(arrayOfRule);
		client.createRuleSet(ruleSet);		
		
	}
}