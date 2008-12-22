package gov.nih.nci.cabig.caaers.domain.factory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.classextension.EasyMock;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AERoutingAndReviewDTOFactoryTest extends CaaersNoSecurityTestCase {
	public AERoutingAndReviewDTOFactory factory;
	
	private WorkflowService workflowService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		factory = new AERoutingAndReviewDTOFactory();
		workflowService = registerMockFor(WorkflowService.class);
		factory.setWorkflowService(workflowService);
	}
	
	public void testCreateAdverseEventEvalutionPeriodDTO_4Null() {
		assertNull(factory.createAdverseEventEvalutionPeriodDTO(null));
	}

	public void testCreateAdverseEventEvalutionPeriodDTO() {
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		Report r = Fixtures.createReport("test");
		r.setAeReport(aeReport);
		aeReport.addReport(r);
		
		rp.addAeReport(aeReport);
		aeReport.setReportingPeriod(rp);
		
		int workflowId = 55;
		List<ReviewStatus> statuses = new ArrayList<ReviewStatus>();
		statuses.add(ReviewStatus.DRAFTINCOMPLETE);
		statuses.add(ReviewStatus.INFO1REVIEW);
		r.setWorkflowId(workflowId);
		rp.setWorkflowId(workflowId);
		rp.setStartDate(new Date());
		rp.setEndDate(new Date());
		Epoch e = new Epoch();
		e.setName("abcd");
		rp.setEpoch(e);

		EasyMock.expect(workflowService.nextStatuses(workflowId)).andReturn(statuses).anyTimes();
		replayMocks();
		
		AdverseEventReportingPeriodDTO dto = factory.createAdverseEventEvalutionPeriodDTO(rp);
		
		verifyMocks();
		
		assertSame(rp, dto.getAdverseEventReportingPeriod());
	}
	
	
	public void testCreateAdverseEventReportDT0_4Null(){
		assertNull(factory.createAdverseEventReportDTO(null, null));
	}
	
	public void testCreateAdverseEventReportDT0(){
		int workflowId = 55;
		List<ReviewStatus> statuses = new ArrayList<ReviewStatus>();
		statuses.add(ReviewStatus.DRAFTINCOMPLETE);
		statuses.add(ReviewStatus.INFO1REVIEW);
		
		Report r = Fixtures.createReport("test");
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		aeReport.setReportingPeriod(rp);
		r.setAeReport(aeReport);
		
		r.setId(7);
		r.setWorkflowId(workflowId);
		Fixtures.createReportVersion(r);
		
		EasyMock.expect(workflowService.nextStatuses(workflowId)).andReturn(statuses);
		replayMocks();
		
		AdverseEventReportDTO dto = factory.createAdverseEventReportDTO(r, aeReport);
		assertSame(r,dto.getReport());
		
	}
	
}
