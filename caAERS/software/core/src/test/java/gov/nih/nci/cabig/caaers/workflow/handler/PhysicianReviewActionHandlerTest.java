package gov.nih.nci.cabig.caaers.workflow.handler;

import java.util.HashMap;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import static org.easymock.EasyMock.expect;


/**
 * This class tests - PhysicianReviewActionHandler class.
 * @author Sameer Sawant
 * @author Biju Joseph
 *
 */
public class PhysicianReviewActionHandlerTest extends AbstractTestCase{
	
	PhysicianReviewActionHandler handler;
	ExecutionContext context;
	ProcessInstance pInstance;
	ReportDao reportDao;
	ContextInstance cInstance;
	Map<Object, Object> contextVariables;
    
	private static String VAR_WF_TYPE = "var_wf_type";
	private static String VAR_REPORT_ID = "var_report_id";
	private static String PHYSICIAN_APPROVE_TRANSITION = "Approve Report";
	private static String ADDITIONAL_INFO_NEEDED_TRANSITION = "Request Additional Information";
	
	protected void setUp() throws Exception {
		super.setUp();
		reportDao = registerDaoMockFor(ReportDao.class);
		handler = new PhysicianReviewActionHandler();
		context = registerMockFor(ExecutionContext.class);
		pInstance = registerMockFor(ProcessInstance.class);
		cInstance = registerMockFor(ContextInstance.class);
		handler.setReportDao(reportDao);
		setUpContextVariables();
	}
	
	public void testExecuteForApproveReport() throws Exception{
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		Report report = Fixtures.createReport("report 1");
		report.setStatus(ReportStatus.PENDING);
		aeReport.addReport(report);
		report = Fixtures.createReport("report 2");
		report.setStatus(ReportStatus.PENDING);
		aeReport.addReport(report);
		Transition transition = new Transition(PHYSICIAN_APPROVE_TRANSITION);
		expect(context.getProcessInstance()).andReturn(pInstance);
		expect(pInstance.getContextInstance()).andReturn(cInstance);
		expect(cInstance.getVariables()).andReturn(contextVariables);
		expect(context.getTransition()).andReturn(transition).anyTimes();
		expect(reportDao.getById(1)).andReturn(report);
		reportDao.save(report);
		replayMocks();
		handler.execute(context);
		verifyMocks();
		assertNull("Physician sign-off incorrectly set to false", aeReport.getReports().get(0).getLastVersion().getPhysicianSignoff());
		assertTrue("Physician sign-off incorrectly set to false", aeReport.getReports().get(1).getLastVersion().getPhysicianSignoff());
	}
	
	public void testExecuteForAdditionalInfoNeeded() throws Exception{
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		Report report = Fixtures.createReport("report 1");
		report.setStatus(ReportStatus.PENDING);
		aeReport.addReport(report);
		report = Fixtures.createReport("report 2");
		report.setStatus(ReportStatus.PENDING);
		aeReport.addReport(report);
		Transition transition = new Transition(ADDITIONAL_INFO_NEEDED_TRANSITION);
		expect(context.getProcessInstance()).andReturn(pInstance);
		expect(pInstance.getContextInstance()).andReturn(cInstance);
		expect(cInstance.getVariables()).andReturn(contextVariables);
		expect(context.getTransition()).andReturn(transition).anyTimes();
		expect(reportDao.getById(1)).andReturn(report);
		reportDao.save(report);
		replayMocks();
		handler.execute(context);
		verifyMocks();
		assertNull("Physician sign-off incorrectly set to true", aeReport.getReports().get(0).getLastVersion().getPhysicianSignoff());
		assertFalse("Physician sign-off incorrectly set to true", aeReport.getReports().get(1).getLastVersion().getPhysicianSignoff());
	}
	
	public void setUpContextVariables(){
	    contextVariables = new HashMap<Object, Object>();
		contextVariables.put(VAR_WF_TYPE, Report.class.getName());
		contextVariables.put(VAR_REPORT_ID, 1);
	}
}