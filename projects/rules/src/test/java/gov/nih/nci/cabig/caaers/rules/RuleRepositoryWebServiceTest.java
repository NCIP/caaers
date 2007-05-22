package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.drools.repository.PackageItem;
import org.jdom.Document;


public class RuleRepositoryWebServiceTest extends AbstractXFireTest {

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
        
        service = factory.create(RepositoryService.class,
                                  "RepositoryService",
                                  "urn:RepositoryService",
                                  props);

		factory = new JaxbServiceFactory();
		service = factory.create(RepositoryService.class);        
        service.setProperty(ObjectInvoker.SERVICE_IMPL_CLASS, RepositoryServiceImpl.class);
		getServiceRegistry().register(service);
	}
	
    public void testWsdl() throws Exception
    {
        Document doc = getWSDLDocument("RepositoryService");
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

		Document response = invokeService("RepositoryService",
				"createRuleSet.xml");

		addNamespace("rules", "http://caaers.cabig.nci.nih.gov/rules/brxml");
		//assertValid(
			//	"//s:Body/w:GetWeatherByZipCodeResponse/w:GetWeatherByZipCodeResult",
				//response);
	}
    
	public void testExecuteRule() throws Exception {

		RepositoryService client = (RepositoryService) new XFireProxyFactory(
				getXFire()).create(service,
				"xfire.local://RepositoryService");
		RuleSet ruleSet = new RuleSet();
		ruleSet.setName("First RuleSet");
		ruleSet.setDescription("First RuleSet");
		Rule rule = new Rule();
		ruleSet.getRule().add(rule);
		client.createRuleSet(ruleSet);		
		
	}
	
	public void testDisplayRepository() throws Exception 
	{
		RepositoryService client = (RepositoryService) new XFireProxyFactory(
				getXFire()).create(service,
				"xfire.local://RepositoryService");
		RuleSet ruleSet = new RuleSet();
		ruleSet.setName("First RuleSet");
		ruleSet.setDescription("First RuleSet");
		Rule rule = new Rule();
		ruleSet.getRule().add(rule);
	}

	public void testDisplayPackages() throws Exception
	{
		RepositoryService client = (RepositoryService) new XFireProxyFactory(getXFire())
		                                   .create(service, "xfire://http:localhost:8080/RepositoryService");
		Iterator<PackageItem> packItr= client.getRulesRepository().listPackages();
		
		while(packItr.hasNext())
		{
			System.out.println(packItr.next().toString());	
		}	
		System.out.println();
	}
}