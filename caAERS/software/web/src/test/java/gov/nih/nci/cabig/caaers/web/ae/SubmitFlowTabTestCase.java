package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepositoryImpl;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * 
 * @author Biju Joseph
 * 
 */
public abstract class SubmitFlowTabTestCase extends WebTestCase {

	protected SubmitExpeditedAdverseEventCommand command;

	protected ExpeditedAdverseEventReportDao expeditedReportDao;
	protected ReportDefinitionDao reportDefinitionDao;
	protected StudyParticipantAssignmentDao assignmentDao;
	protected AdverseEventReportingPeriodDao reportingPeriodDao;
	protected Errors errors;

	protected ExpeditedReportTree expeditedReportTree;
	protected RenderDecisionManager renderDecisionManager;
	protected ReportRepository reportRepository;
	protected ExpeditedAdverseEventReport aeReport;
	

	protected AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	
	protected void setUp() throws Exception {
		super.setUp();
		aeReport = registerMockFor(ExpeditedAdverseEventReport.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        expeditedReportTree = new ExpeditedReportTree();
        expeditedReportDao = registerMockFor(ExpeditedAdverseEventReportDao.class);
        reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
        renderDecisionManager = registerMockFor(RenderDecisionManager.class);
        adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
        reportRepository = registerMockFor(ReportRepositoryImpl.class);
        command = createCommand();

        errors = new BindException(command, "command");
	}


	public abstract SubmitExpeditedAdverseEventCommand createCommand();
	

}
