package gov.nih.nci.cabig.caaers.web.ae;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReviewAndReportTabTest extends WebTestCase {
	
	
	ReviewAndReportTab tab;
	Errors errors;
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new ReviewAndReportTab();
		errors = new BindException(createCommand(), "command");
	}

	public void testReferenceDataHttpServletRequestCaptureAdverseEventInputCommand() {

		CaptureAdverseEventInputCommand command = registerMockFor(CaptureAdverseEventInputCommand.class);
		command.findApplicableReportDefinitions();
		command.evaluateSAERules();
		command.generateReadableRulesMessage();
		command.refreshRecommendedReportTable();
		command.refreshApplicableReportTable();
		command.refreshAeReportIdIndex();
		
		replayMocks();
		tab.referenceData(request, command);
		verifyMocks();
		
	}
	
	
	
	/**
	 * working on an existing report. 
	 */
	public void testOnBindHttpServletRequestCaptureAdverseEventInputCommandErrors_OldDCMode() {
		CaptureAdverseEventInputCommand command = createCommand();
		
		request.setParameter("activeAeReportId", "1");
		
		//all report defs
		request.setParameter("rd_1", new String[]{"1", "2", "3"});
		
		//actual actions
		request.setParameter("rd_1_1_actualaction", "Amend");
		request.setParameter("rd_1_2_actualaction", "Withdraw");
		request.setParameter("rd_1_3_actualaction", "Create");
		
		//selected report defs
		request.setParameter("rd_1_checked", new String[] {"1" , "3"});
		
		//aes
		request.setParameter("ae_1", new String[] {"1", "2"});
		request.setParameter("ae_0_primary", "1");
		
		tab.onBind(request, command, errors);
		
		assertNotNull(command.getReviewResult());
		assertEquals(new Integer(1),command.getReviewResult().getAeReportId());
		assertEquals(2, command.getReviewResult().getAeList().size());
		assertEquals(2, command.getReviewResult().getCreateList().size());
		assertEquals(1,command.getReviewResult().getAmendList().size());
		assertSame(command.getApplicableReportDefinitions().getReportDefinitionMap().get(new Integer(1)),command.getReviewResult().getAmendList().get(0));
		assertTrue(command.getReviewResult().getEditList().isEmpty());
		assertEquals(1,command.getReviewResult().getWithdrawList().size());
		assertEquals(new Integer(2), command.getReviewResult().getWithdrawList().get(0));
		
	}
	
	
	/**
	 * working on a new report. 
	 */
	public void testOnBindHttpServletRequestCaptureAdverseEventInputCommandErrors_NewDCMode() {
		CaptureAdverseEventInputCommand command = createCommand();
		
		request.setParameter("activeAeReportId", "0");
		
		//all report defs
		request.setParameter("rd_0", new String[]{"1", "2", "3"});
		
		//actual actions
		request.setParameter("rd_0_1_actualaction", "");
		request.setParameter("rd_0_2_actualaction", "");
		request.setParameter("rd_0_3_actualaction", "Create");
		
		//selected report defs
		request.setParameter("rd_0_checked", "3");
		
		//aes
		request.setParameter("ae_0", new String[] {"1", "2"});
		request.setParameter("ae_0_primary", "1");
		
		tab.onBind(request, command, errors);
		
		assertNotNull(command.getReviewResult());
		assertEquals(new Integer(0),command.getReviewResult().getAeReportId());
		assertEquals(2, command.getReviewResult().getAeList().size());
		assertEquals(1, command.getReviewResult().getCreateList().size());
		assertSame(command.getApplicableReportDefinitions().getReportDefinitionMap().get(new Integer(3)), command.getReviewResult().getCreateList().get(0));
		assertTrue(command.getReviewResult().getAmendList().isEmpty());
		assertTrue(command.getReviewResult().getEditList().isEmpty());
		assertTrue(command.getReviewResult().withdrawList.isEmpty());
		
	}

	
	
	public CaptureAdverseEventInputCommand createCommand(){
		CaptureAdverseEventInputCommand command = new CaptureAdverseEventInputCommand();
		
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		reportingPeriod.setId(3);
		command.setAdverseEventReportingPeriod(reportingPeriod);
		
		final Map<Integer, ReportDefinition> rdMap = new LinkedHashMap<Integer, ReportDefinition>();

		ReportDefinition rd1 = Fixtures.createReportDefinition("1");
		rd1.setId(new Integer(1));
		rdMap.put(new Integer(1), rd1);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("2");
		rd1.setId(new Integer(2));
		rdMap.put(new Integer(2), rd2);
		
		
		ReportDefinition rd3 = Fixtures.createReportDefinition("3");
		rd1.setId(new Integer(3));
		rdMap.put(new Integer(3), rd3);
		
		
		ApplicableReportDefinitionsDTO applicableReportDefs = new ApplicableReportDefinitionsDTO(){
			@Override
			public Map<Integer, ReportDefinition> getReportDefinitionMap() {
				// TODO Auto-generated method stub
				return rdMap;
			}
		};
		
		
		command.setApplicableReportDefinitions(applicableReportDefs);
		
		return command;
	}

}
