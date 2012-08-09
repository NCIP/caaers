package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;

import java.util.Map;

import org.easymock.classextension.EasyMock;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;

public class CTEPESYSDataIntegrationLogsControllerTest extends WebTestCase{
	
	protected CTEPESYSDataIntegrationLogsController controller;
	protected CTEPESYSDataIntegrationLogsCommand command;
	protected ServletRequestDataBinder binder;
	protected Configuration configuration;
	protected Errors errors;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		controller = new CTEPESYSDataIntegrationLogsController();
		command = registerMockFor(CTEPESYSDataIntegrationLogsCommand.class);
		binder = registerMockFor(ServletRequestDataBinder.class);
		configuration = registerMockFor(Configuration.class);
		controller.setConfiguration(configuration);
		errors = registerMockFor(Errors.class);
		
	}
	
	public void testInitBinder() throws Exception{
		controller.initBinder(request, binder);
	}
	
	public void testFormBackingObject() throws Exception{
		String esbLocation = "abc/def";
		EasyMock.expect(configuration.get((ConfigurationProperty)EasyMock.anyObject())).andReturn(esbLocation);
		replayMocks();
		controller.formBackingObject(request);
		verifyMocks();
	}
	
	public void testReferenceData() throws Exception{
		Map map = controller.referenceData(request, new Object(), errors);
		assertNotNull(map.get("fieldGroups"));
	}

}
