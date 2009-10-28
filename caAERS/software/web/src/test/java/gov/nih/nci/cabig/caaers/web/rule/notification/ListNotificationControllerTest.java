package gov.nih.nci.cabig.caaers.web.rule.notification;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.service.migrator.ReportDefinitionConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.ReportDefinitionSynchronizer;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import static org.easymock.EasyMock.expect;

import org.springframework.validation.BindException;

/**
 * @author Sameer Sawant
 */
public class ListNotificationControllerTest extends WebTestCase{
	private ListNotificationController controller;
	
	private ListNotificationCommand command;
	
	private ReportDefinitionDao reportDefinitionDao;
	
	private ReportDefinitionConverter reportDefinitionConverter;
	
	private ReportDefinitionSynchronizer reportDefinitionSynchronizer;
	
	private BindException errors;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		reportDefinitionConverter = registerMockFor(ReportDefinitionConverter.class);
		reportDefinitionSynchronizer = registerMockFor(ReportDefinitionSynchronizer.class);
		
		command = new ListNotificationCommand(reportDefinitionDao);
		controller = new ListNotificationController() {
			@Override
            public Object formBackingObject(HttpServletRequest request){
                return command;
            }
		};
		errors = new BindException(command, "command");
	}
	
	public void testOnSubmitNoFile() throws Exception{
		MultipartFile file = registerMockFor(MultipartFile.class);
		command.setRuleSetFile1(file);
		expect(file.isEmpty()).andReturn(true);
		replayMocks();
		ModelAndView modelAndView = controller.onSubmit(request, response, command, errors);
		assertEquals("Please choose a file", command.getMessage());
		verifyMocks();
	}
}