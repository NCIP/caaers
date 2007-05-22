package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


public class RuleAuthoringWebServiceTest extends AbstractXFireTest {

	//private RuleExecutionServiceImpl ruleExecutionService;

    private Service service;
    private ObjectServiceFactory factory;

    RuleAuthoringService ruleAuthoringService;
    
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

		ruleAuthoringService  = (RuleAuthoringService) new XFireProxyFactory(getXFire())
		.create(service, "http://localhost:8080/rules/services/RuleAuthoringService");
		
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
		ruleSet.getRule().add(rule);
		client.createRuleSet(ruleSet);		
		
	}
	
	public void testDisplayPackages() throws Exception
	{
		
		RuleAuthoringService client = (RuleAuthoringService) new XFireProxyFactory(getXFire())
		                                   .create(service, "http://localhost:8080/rules/services/RuleAuthoringService");
		
/*		RuleAuthoringService client = (RuleAuthoringService) new XFireProxyFactory(getXFire())
        					.create(service, "xfire.local://RuleAuthoringService");
*/
		client.listPackages();
		
	}
	
	public void testCreateCategory() throws Exception
	{
		
		// TagName: /Sponsor/Sponsor Name
		
		Category nciCat = createCategory("/Sponsor", "National Cancer Institute", "NCI Category");

		
	}
	
	private Category createCategory(String path, String name, String description) 
	{
		Category category = null;
		
		try 
		{
			category = ruleAuthoringService.getCategory(path + "/" + name);
		} 
		catch(RemoteException e) 
		{
			//Forget this exception now
			e.printStackTrace();
		}
		
		if(category != null) return category;

		category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(path);
		metaData.setName(name);
		metaData.setDescription(description);
		category.setMetaData(metaData);		
		
		try 
		{
			ruleAuthoringService.createCategory(category);
		} 
		catch(RemoteException remoteException) 
		{
			throw new CaaersSystemException(remoteException.getMessage(), remoteException);
		}
		
		return category;
	}
	
	public void testFindRuleSetsForSponsor()
	{
		List<RuleSet> ruleSets = this.ruleAuthoringService.findRuleSetsForSponsor("National Cancer Institute");
		System.out.println("End of test case");
	}
}