/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.*;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.User;
import org.springframework.validation.BindException;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;


/**
 * This class tests - ReviewAeReportController.java
 * @author Sameer Sawant
 */
public class ReviewAeReportControllerTest extends WebTestCase{
	
	protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	protected ReportDao reportDao;
	protected StudyParticipantAssignmentDao assignmentDao;
	protected Configuration configuration;
	BindException errors;
	
	protected ReviewAeReportController controller;
	protected ReviewAeReportCommand command;
	protected EvaluationService evaluationService;
	protected ReportValidationService reportValidationService;
	protected CSMUserRepository csmUserRepository;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		configuration = registerMockFor(Configuration.class);
		evaluationService = registerMockFor(EvaluationService.class);
		reportValidationService = registerMockFor(ReportValidationService.class);
		reportDao = registerDaoMockFor(ReportDao.class);
		command = new ReviewAeReportCommand(expeditedAdverseEventReportDao, reportDao);
		errors = new BindException(command,"command");
		csmUserRepository = registerMockFor(CSMUserRepository.class);
		
		controller = new ReviewAeReportController();
		controller.setExpeditedAdverseEventReportDao(expeditedAdverseEventReportDao);
		controller.setAssignmentDao(assignmentDao);
		controller.setConfiguration(configuration);
		controller.setEvaluationService(evaluationService);
		controller.setReportValidationService(reportValidationService);
		controller.setCsmUserRepository(csmUserRepository);
	}
	
	
	public void testReferenceData() throws Exception{
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		Report report = Fixtures.createReport("test report");
		SecurityContext context = registerMockFor(SecurityContext.class);
        Authentication auth =  registerMockFor(Authentication.class);
        User user = registerMockFor(User.class);
        session.setAttribute(ReviewAeReportController.class + ".FORM.command",command);
        session.setAttribute("ACEGI_SECURITY_CONTEXT",context);
		aeReport.addReport(report);
		report.setId(1);
		command.setAeReport(aeReport);
		command.setReportId(1);
		ValidationErrors vErrors = new ValidationErrors();
		expect(evaluationService.validateReportingBusinessRules(isA(ExpeditedAdverseEventReport.class), isA(ExpeditedReportSection.class))).andReturn(vErrors).anyTimes();
		expect(reportValidationService.isSubmittable(report)).andReturn(new ReportSubmittability());
		expect(context.getAuthentication()).andReturn(auth);
        expect(auth.getPrincipal()).andReturn(user);
        expect(user.getUsername()).andReturn("SYSTEM_ADMIN");
        expect(csmUserRepository.getUserGroups("SYSTEM_ADMIN")).andReturn(new ArrayList<UserGroupType>());
		replayMocks();
		Map<String, Object> refdata = controller.referenceData(request, command, errors);
		verifyMocks();
		assertFalse("isUserSAECoordinator flag set incorrectly", (Boolean)refdata.get("isUserSAECoordinato"));
		assertContainsKey("Report messages is expected in jsp, but not set in the reference data", refdata, "reportMessages");
	}
}
