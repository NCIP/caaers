package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.easymock.classextension.EasyMock;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;

public class CTEPESYSDataIntegrationLogDownloadControllerTest extends WebTestCase{
	
	protected CTEPDataIntegrationLogDownloadController controller;
	protected CTEPESYSDataIntegrationLogsCommand command;
	protected ServletRequestDataBinder binder;
	protected Configuration configuration;
	protected Errors errors;
	protected final String YEAR = "2011";
	protected final String MONTH = "01";
	protected final String DAY_OF_MONTH = "04";
	protected final String ENTITY = "agent";
	protected final String CORRELATION_ID = "21323232";
	File curDir = new File(".");
	protected String ESB_LOCATION;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		controller = new CTEPDataIntegrationLogDownloadController();
		configuration = registerMockFor(Configuration.class);
		controller.setConfiguration(configuration);
		errors = registerMockFor(Errors.class);
		ESB_LOCATION = curDir.getCanonicalPath()+ "\\web\\src\\test\\resources\\gov\\nih\\nci\\cabig\\caaers\\web\\admin\\testdata";
		String dirPath = ESB_LOCATION + File.separator + YEAR + File.separator + MONTH + File.separator + DAY_OF_MONTH + File.separator + ENTITY + File.separator + CORRELATION_ID;
	
		try {
			Boolean dirCreated = new File(dirPath).mkdirs();
			File source = new File(ESB_LOCATION);
			File dest = new File(dirPath);
			FileUtils.copyDirectory(source, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testHandleRequestInternal1() throws Exception{
		String esbLocation = "abc/def";
		EasyMock.expect(configuration.get((ConfigurationProperty)EasyMock.anyObject())).andReturn(esbLocation);
		replayMocks();
		controller.handleRequestInternal(request, response);
		verifyMocks();
	}
	
	public void testHandleRequestInternal2() throws Exception{
	
		request.setParameter("cstr", CORRELATION_ID);
		request.setParameter("dstr", MONTH + "-" + DAY_OF_MONTH + "-" + YEAR);
		request.setParameter("entity", ENTITY);
		
		EasyMock.expect(configuration.get((ConfigurationProperty)EasyMock.anyObject())).andReturn(ESB_LOCATION);
		replayMocks();
		controller.handleRequestInternal(request, response);
		verifyMocks();
	}

}
