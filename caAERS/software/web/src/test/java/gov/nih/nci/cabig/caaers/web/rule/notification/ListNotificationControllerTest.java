package gov.nih.nci.cabig.caaers.web.rule.notification;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
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
	private static final String DISABLE_ACTION = "disable";
	private static final String ENABLE_ACTION = "enable";
	
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
		controller.setReportDefinitionDao(reportDefinitionDao);
		addReportDefinitionsToCommand();
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
	
	public void testReferenceDataForEnableAction() throws Exception{
		request.setParameter("action", ENABLE_ACTION);
		request.setParameter("repDefId", "3");
		reportDefinitionDao.save(command.getReportCalendarTemplateList().get(2));
		replayMocks();
		controller.referenceData(request, command, errors);
		assertEquals("incorrect number of active report definitions", 3, command.getActiveReportDefinitionsList().size());
		verifyMocks();
	}
	
	public void testReferenceDataForDisableAction() throws Exception{
		request.setParameter("action", DISABLE_ACTION);
		request.setParameter("repDefId", "1");
		reportDefinitionDao.save(command.getReportCalendarTemplateList().get(0));
		replayMocks();
		controller.referenceData(request, command, errors);
		assertEquals("incorrect number of inactive report definitions", 2, command.getInactiveReportDefinitionsList().size());
		verifyMocks();
	}
	
	public void addReportDefinitionsToCommand(){
		List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
		ReportDefinition rd1 = Fixtures.createReportDefinition("activeRd1");
		rd1.setEnabled(true);
		rd1.setId(1);
		ReportDefinition rd2 = Fixtures.createReportDefinition("activeRd2");
		rd2.setEnabled(true);
		rd2.setId(2);
		ReportDefinition rd3 = Fixtures.createReportDefinition("inactiveRd3");
		rd3.setEnabled(false);
		rd3.setId(3);
		reportDefinitionList.add(rd1);
		reportDefinitionList.add(rd2);
		reportDefinitionList.add(rd3);
		command.setReportCalendarTemplateList(reportDefinitionList);
	}
	
	public void testInitializeActiveInactiveLists() throws Exception{
		controller.initializeActiveInactiveLists(command);
		assertEquals("Incorrect number of active report definitions", 2, command.getActiveReportDefinitionsList().size());
		assertEquals("Incorrect number of inactive report definitions", 1, command.getInactiveReportDefinitionsList().size());
	}
}