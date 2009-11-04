package gov.nih.nci.cabig.caaers.domain.factory;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.classextension.EasyMock;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AERoutingAndReviewDTOFactoryTest extends CaaersNoSecurityTestCase {
	public AERoutingAndReviewDTOFactory factory;
	
	private AdverseEventRoutingAndReviewRepository repository;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		factory = new AERoutingAndReviewDTOFactory();
		repository = registerMockFor(AdverseEventRoutingAndReviewRepository.class);
		factory.setAdverseEventRoutingAndReviewRepository(repository);
	}
	
	public void testCreateAdverseEventEvalutionPeriodDTO_4Null() {
		String userId = "tester";
		assertNull(factory.createAdverseEventEvalutionPeriodDTO(null, userId));
	}

	public void testCreateAdverseEventEvalutionPeriodDTO() {
		/*String userId = "tester";
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		
		rp.addAeReport(aeReport);
		aeReport.setReportingPeriod(rp);
		
		int workflowId = 55;
		List<String> actions = new ArrayList<String>();
		actions.add("action1");
		actions.add("action2");
		
		aeReport.setWorkflowId(workflowId);
		rp.setWorkflowId(workflowId);
		rp.setStartDate(new Date());
		rp.setEndDate(new Date());
		Epoch e = new Epoch();
		e.setName("abcd");
		rp.setEpoch(e);

		EasyMock.expect(repository.nextTransitionNames(workflowId, userId)).andReturn(actions).anyTimes();
		replayMocks();
		
		AdverseEventReportingPeriodDTO dto = factory.createAdverseEventEvalutionPeriodDTO(rp, userId);
		
		verifyMocks();
		
		assertSame(rp, dto.getAdverseEventReportingPeriod());*/
	}
	
	
	public void testCreateAdverseEventReportDTO_4Null(){
		String userId = "tester";
		assertNull(factory.createAdverseEventReportDTO(null, userId));
	}
	
	public void testCreateAdverseEventReportDTO(){
		/*String userId = "tester";
		int workflowId = 55;
		List<String> actions = new ArrayList<String>();
		actions.add("action1");
		actions.add("action2");
		
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		aeReport.setReportingPeriod(rp);
		
		aeReport.setId(7);
		aeReport.setWorkflowId(workflowId);
		
		EasyMock.expect(repository.nextTransitionNamesForAeReportWorkflow(aeReport, userId)).andReturn(actions);
		replayMocks();
		
		ExpeditedAdverseEventReportDTO dto = factory.createAdverseEventReportDTO( aeReport, userId);
		assertSame(aeReport,dto.getAeReport());*/
		
	}
	
}
