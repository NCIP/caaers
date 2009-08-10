package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.OutcomeType.CONGENITAL_ANOMALY;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.DEATH;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.DISABILITY;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.HOSPITALIZATION;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.LIFE_THREATENING;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.OTHER_SERIOUS;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.REQUIRED_INTERVENTION;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventCaptureTabTest extends WebTestCase {
	
	AdverseEventCaptureTab tab;
	CaptureAdverseEventInputCommand command;
	Errors errors;
	ExpeditedAdverseEventReportDao reportDao;
	ReportDefinitionDao reportDefinitionDao;
	ReportRepository reportRepository;
	AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new AdverseEventCaptureTab();
		
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		List<AdverseEvent> aeList = createAdverseEventList();
		reportingPeriod.setAdverseEvents(aeList);
		Fixtures.createCtcV3Terminology(reportingPeriod.getStudy());
		reportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		reportRepository = registerMockFor(ReportRepository.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepository.class);
		
		command = new CaptureAdverseEventInputCommand(null, null, null, reportDefinitionDao, null, reportDao);
		command.setAdverseEventReportingPeriod(reportingPeriod);
		
	
		errors = new BindException(command, "command");
		
		tab.setReportRepository(reportRepository);
		tab.setAdverseEventRoutingAndReviewRepository(adverseEventRoutingAndReviewRepository);
	}
	
	public List<AdverseEvent> createAdverseEventList(){
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		for(int i =0; i < 3; i++){
			AdverseEvent ae = Fixtures.createAdverseEvent(i+1, Grade.NORMAL);
			//add couple of outcomes
			Outcome o1 = Fixtures.createOutcome(i*3 +10,OutcomeType.DEATH);
			Outcome o2 = Fixtures.createOutcome(i*3 +11,OutcomeType.HOSPITALIZATION);
			Outcome o3 = Fixtures.createOutcome(i*3 +12,OutcomeType.OTHER_SERIOUS);
			ae.addOutcome(o1);
			ae.addOutcome(o2);
			ae.addOutcome(o3);
			
			aeList.add(ae);
		} 
		return aeList;
	} 

	public void testCreateFieldGroupsCaptureAdverseEventInputCommand() {
		command.initializeOutcomes();
		Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
		System.out.println(fieldMap);
		assertCorrectOutcomeFieldNames(fieldMap.get("outcomes0"), 
				"outcomes[0][" + DEATH.getCode() +"]",
				"outcomes[0][" + HOSPITALIZATION.getCode() +"]",
				"outcomes[0][" + LIFE_THREATENING.getCode() +"]",
				"outcomes[0][" + DISABILITY.getCode() +"]",
				"outcomes[0][" + CONGENITAL_ANOMALY.getCode() +"]",
				"outcomes[0][" + REQUIRED_INTERVENTION.getCode() +"]",
				"outcomes[0][" + OTHER_SERIOUS.getCode() +"]",
				"outcomeOtherDetails[0]" );
		assertCorrectOutcomeFieldNames(fieldMap.get("outcomes1"), 
				"outcomes[1][" + DEATH.getCode() +"]",
				"outcomes[1][" + HOSPITALIZATION.getCode() +"]",
				"outcomes[1][" + LIFE_THREATENING.getCode() +"]",
				"outcomes[1][" + DISABILITY.getCode() +"]",
				"outcomes[1][" + CONGENITAL_ANOMALY.getCode() +"]",
				"outcomes[1][" + REQUIRED_INTERVENTION.getCode() +"]",
				"outcomes[1][" + OTHER_SERIOUS.getCode() +"]",
				"outcomeOtherDetails[1]" );
		assertCorrectOutcomeFieldNames(fieldMap.get("outcomes2"), 
				"outcomes[2][" + DEATH.getCode() +"]",
				"outcomes[2][" + HOSPITALIZATION.getCode() +"]",
				"outcomes[2][" + LIFE_THREATENING.getCode() +"]",
				"outcomes[2][" + DISABILITY.getCode() +"]",
				"outcomes[2][" + CONGENITAL_ANOMALY.getCode() +"]",
				"outcomes[2][" + REQUIRED_INTERVENTION.getCode() +"]",
				"outcomes[2][" + OTHER_SERIOUS.getCode() +"]",
				"outcomeOtherDetails[2]" );
		
		correctMainGroupFields(fieldMap.get("main0"), "adverseEvents[0].", "detailsForOther",
				"grade",
				"startDate",
				"endDate",
				"attributionSummary",
				"eventApproximateTime",
				"eventLocation",
				"hospitalization",
				"expected");
		
		correctMainGroupFields(fieldMap.get("main1"), "adverseEvents[1].", "detailsForOther",
				"grade",
				"startDate",
				"endDate",
				"attributionSummary",
				"eventApproximateTime",
				"eventLocation",
				"hospitalization",
				"expected");
		
		correctMainGroupFields(fieldMap.get("main2"), "adverseEvents[2].", "detailsForOther",
				"grade",
				"startDate",
				"endDate",
				"attributionSummary",
				"eventApproximateTime",
				"eventLocation",
				"hospitalization",
				"expected");
	}
	
	public void testPostprocess(){
		command.initializeOutcomes();
		
		command.set_action("joel");
	    command.setReportingMethod("joel");
	    command.setPrimaryAdverseEventId(3);
		
	    request.setAttribute("_action", "test");
		tab.postProcess(request, command, errors);
		
		assertNull(command.get_action());
		assertNull(command.getReportingMethod());
		assertNull(command.getPrimaryAdverseEventId());
		
	}
	/*
	public void testPostprocessWhenActionIsAmmend(){
		command.initializeOutcomes();
		
		command.set_action("joel");
	    command.setReportingMethod("joel");
	    command.setPrimaryAdverseEventId(3);
	    
	    //add one expedited report with id=1 in the reporting period, so that it will be ammended.
	    ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
	    aeReport.setId(1);
	    Report report = Fixtures.createReport("test");
	    report.setId(4);
	    report.getReportDefinition().setAmendable(true);
	    aeReport.addReport(report);
	    command.getAdverseEventReportingPeriod().addAeReport(aeReport);
		
	    request.setAttribute("_action", "amendmentRequired");
	    request.setAttribute("_amendReportIds", "1");

	    reportDao.reassociate(command.getAdverseEventReportingPeriod().getAeReports().get(0));
	    reportRepository.createAndAmendReport(report.getReportDefinition(), report, false);
	    EasyMock.expect(reportDefinitionDao.merge(report.getReportDefinition())).andReturn(report.getReportDefinition());
	    replayMocks();
		tab.postProcess(request, command, errors);
		
		assertNull(command.get_action());
		assertNull(command.getReportingMethod());
		assertNull(command.getPrimaryAdverseEventId());
		
		verifyMocks();
		
	}
	
	public void testPostprocessWhenActionIsAmmendAndWorkflowEnabled(){
		command.initializeOutcomes();
		
		command.set_action("joel");
	    command.setReportingMethod("joel");
	    command.setPrimaryAdverseEventId(3);
	    command.setWorkflowEnabled(true);
	    command.getAdverseEventReportingPeriod().setWorkflowId(3);
	    
	    //add one expedited report with id=1 in the reporting period, so that it will be ammended.
	    ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
	    aeReport.setId(1);
	    Report report = Fixtures.createReport("test");
	    report.setId(4);
	    report.getReportDefinition().setAmendable(true);
	    aeReport.addReport(report);
	    command.getAdverseEventReportingPeriod().addAeReport(aeReport);
		
	    request.setAttribute("_action", "amendmentRequired");
	    request.setAttribute("_amendReportIds", "1");
	    
	    reportDao.reassociate(command.getAdverseEventReportingPeriod().getAeReports().get(0));
	    EasyMock.expect(reportDefinitionDao.merge(report.getReportDefinition())).andReturn(report.getReportDefinition());
	    reportRepository.createAndAmendReport(report.getReportDefinition(), report, false);
	    EasyMock.expect(adverseEventRoutingAndReviewRepository.enactReportWorkflow(aeReport)).andReturn(3L);
	    replayMocks();
		tab.postProcess(request, command, errors);
		
		assertNull(command.get_action());
		assertNull(command.getReportingMethod());
		assertNull(command.getPrimaryAdverseEventId());
		
		verifyMocks();
		
	}
	*/
	public void assertCorrectOutcomeFieldNames(InputFieldGroup fieldGrp, String...propertyNames){
		int i = 0;
		for(InputField field : fieldGrp.getFields()){
			assertEquals(propertyNames[i], field.getPropertyName());
			if(i< (propertyNames.length -1)){
				assertTrue(field.getCategoryName().equals("checkbox"));
			}
			i++;
		}
	}
	
	public void  correctMainGroupFields(InputFieldGroup fieldGrp, String prefix, String... propertySuffixes){
		int i = 0;
		for(InputField field : fieldGrp.getFields()){
			assertEquals(prefix + propertySuffixes[i], field.getPropertyName());
			i++;
		}
	}

}
