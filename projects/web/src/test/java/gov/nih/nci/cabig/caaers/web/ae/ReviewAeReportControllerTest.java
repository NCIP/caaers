package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import static org.easymock.EasyMock.expect;


/**
 * This class tests - ReviewAeReportController.java
 * @author Sameer Sawant
 */
public class ReviewAeReportControllerTest extends WebTestCase{
	
	protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	protected StudyParticipantAssignmentDao assignmentDao;
	protected Configuration configuration;
	
	protected ReviewAeReportController controller;
	protected ReviewAeReportCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		configuration = registerMockFor(Configuration.class);
		
		controller = new ReviewAeReportController();
		controller.setExpeditedAdverseEventReportDao(expeditedAdverseEventReportDao);
		controller.setAssignmentDao(assignmentDao);
		controller.setConfiguration(configuration);
	}
	
	public void testFormBackingObject() throws Exception{
		request.setParameter("aeReport", "1");
		ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
		report.setId(1);
		expect(expeditedAdverseEventReportDao.getById(1)).andReturn(report);
		expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(true);
		replayMocks();
		command = (ReviewAeReportCommand) controller.formBackingObject(request);
		verifyMocks();
	}
}