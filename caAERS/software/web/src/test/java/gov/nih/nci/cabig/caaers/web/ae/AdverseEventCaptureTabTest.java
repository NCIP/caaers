/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.OutcomeType.CONGENITAL_ANOMALY;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.DEATH;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.DISABILITY;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.HOSPITALIZATION;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.LIFE_THREATENING;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.OTHER_SERIOUS;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.REQUIRED_INTERVENTION;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.CaaersFieldConfigurationManager;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
    BeanWrapper commandWrapper;
	Errors errors;
	ExpeditedAdverseEventReportDao reportDao;
	ReportDefinitionDao reportDefinitionDao;
	ReportRepository reportRepository;
	AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	CaaersFieldConfigurationManager caaersFieldConfigurationManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new AdverseEventCaptureTab();
		
		setupCaaersFieldConfigurationManager();
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		List<AdverseEvent> aeList = createAdverseEventList();
		reportingPeriod.setAdverseEvents(aeList);
		Fixtures.createCtcV3Terminology(reportingPeriod.getStudy());
        
        setUpCommand(reportingPeriod);

        reportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		reportRepository = registerMockFor(ReportRepository.class);
		
		tab.setReportRepository(reportRepository);
		tab.setCaaersFieldConfigurationManager(caaersFieldConfigurationManager);
	}
	
    private void setUpCommand(AdverseEventReportingPeriod reportingPeriod){
        command = new CaptureAdverseEventInputCommand(null, null,null, reportDefinitionDao, null, reportDao);
        command.setAdverseEventReportingPeriod(reportingPeriod);
        command.setParticipant(reportingPeriod.getParticipant());
        command.setStudy(reportingPeriod.getStudy());
        commandWrapper = new BeanWrapperImpl(command);
        errors = new BindException(command, "command");
    }
	protected void setupCaaersFieldConfigurationManager(){
		caaersFieldConfigurationManager = new CaaersFieldConfigurationManager();
		Map<String, Mandatory> map = new HashMap<String, Mandatory>();
		map.put("adverseEvents[]", Mandatory.OPTIONAL);
		map.put("adverseEvents[].grade", Mandatory.MANDATORY);
		map.put("adverseEvents[].adverseEventCtcTerm", Mandatory.OPTIONAL);
		map.put("adverseEvents[].adverseEventCtcTerm.term", Mandatory.MANDATORY);
		map.put("adverseEvents[].detailsForOther", Mandatory.OPTIONAL);
		map.put("adverseEvents[].startDate", Mandatory.OPTIONAL);
		map.put("adverseEvents[].gradedDate", Mandatory.OPTIONAL);
		map.put("adverseEvents[].endDate", Mandatory.OPTIONAL);
		map.put("adverseEvents[].attributionSummary", Mandatory.OPTIONAL);
		map.put("adverseEvents[].hospitalization", Mandatory.OPTIONAL);
		map.put("adverseEvents[].expected", Mandatory.OPTIONAL);
		map.put("adverseEvents[].eventLocation", Mandatory.OPTIONAL);
		map.put("adverseEvents[].comments", Mandatory.OPTIONAL);
		map.put("adverseEvents[].participantAtRisk", Mandatory.OPTIONAL);
		map.put("adverseEvents[].eventApproximateTime.hourString", Mandatory.OPTIONAL);
		map.put("adverseEvents[].outcomes", Mandatory.OPTIONAL);
		Map<String, Map<String, Mandatory>> fieldMap = new HashMap<String, Map<String, Mandatory>>();
		fieldMap.put(AdverseEventCaptureTab.class.getName(), map);
		caaersFieldConfigurationManager.setFieldConfigurationMap(fieldMap);
	}
	public List<AdverseEvent> createAdverseEventList(){
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		for(int i =0; i < 3; i++){
			AdverseEvent ae = Fixtures.createAdverseEvent(i+1, Grade.getByCode(i+1));
			//add couple of outcomes
			Outcome o1 = Fixtures.createOutcome(i*3 +10,OutcomeType.DEATH);
			Outcome o2 = Fixtures.createOutcome(i*3 +11,OutcomeType.HOSPITALIZATION);
			Outcome o3 = Fixtures.createOutcome(i*3 +12,OutcomeType.OTHER_SERIOUS);
			ae.addOutcome(o1);
			ae.addOutcome(o2);
			ae.addOutcome(o3);
			
			aeList.add(ae);
		}
        aeList.add(Fixtures.createAdverseEvent(4, Grade.MILD, Fixtures.createCtcTerm("j", "k")));
        aeList.add(Fixtures.createAdverseEvent(5, Grade.MILD, Fixtures.createCtcTerm("a", "b")));
		return aeList;
	} 
	public void testCreateFieldGroupsCaptureAdverseEventInputCommand() {
		command.initializeOutcomes();
		Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
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
				"gradedDate",
				"startDate",
				"endDate",
				"attributionSummary",
				"hospitalization",
				"expected",
				"eventApproximateTime",
				"participantAtRisk",
				"eventLocation",
				"externalId");
		
		correctMainGroupFields(fieldMap.get("main1"), "adverseEvents[1].", "detailsForOther",
				"grade",
				"gradedDate",
				"startDate",
				"endDate",
				"attributionSummary",
				"hospitalization",
				"expected",
				"eventApproximateTime",
				"participantAtRisk",
				"eventLocation",
				"externalId");
		
		correctMainGroupFields(fieldMap.get("main2"), "adverseEvents[2].", "detailsForOther",
				"grade",
				"gradedDate",
				"startDate",
				"endDate",
				"attributionSummary",
				"hospitalization",
				"expected",
				"eventApproximateTime",
				"participantAtRisk",
				"eventLocation",
				"externalId");
	}
	public void testPostprocess(){
		command.initializeOutcomes();
		
		command.set_action("joel");
	    command.setReportingMethod("joel");
	    command.setPrimaryAdverseEventId(3);
		
	    request.setAttribute("_action", "test");
	    command.setLoggedInUserEmail("abc.def@gmail.com");
		tab.postProcess(request, command, errors);
		
		assertNull(command.get_action());
		assertNull(command.getReportingMethod());
		assertNull(command.getPrimaryAdverseEventId());
		
		
	}

    public void testValidate_WhenStudyEnforceTermUniquness(){
        command.initializeOutcomes();
        command.getStudy().setAeTermUnique(true);
        command.getStudy().setVerbatimFirst(true);
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getGlobalErrorCount());
        assertEquals("DUPLICATE_EXPECTED_AE", errors.getGlobalErrors().get(0).getCode());
    }

    public void testValidate_WhenStudyDoNotEnforceTermUniquness(){
        command.initializeOutcomes();
        command.getStudy().setAeTermUnique(false);
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        tab.validate(command, commandWrapper, fieldMap, errors);
        System.out.println(errors);
        assertFalse(errors.hasErrors());
    }


    public void testValidate_Graded_Start_EndDates(){
        command.initializeOutcomes();
        command.getStudy().setAeTermUnique(false);
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setGradedDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(1).setGradedDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(2).setGradedDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(3).setGradedDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(4).setGradedDate(new Date());

        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);

        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setStartDate(null);
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setEndDate(null);
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setGradedDate(new Date());
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertFalse(errors.hasErrors());


        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setStartDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setEndDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setGradedDate(new Date());
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertFalse(errors.hasErrors());

        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setStartDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setEndDate(null);
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setGradedDate(DateUtils.tomorrow());
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertFalse(errors.hasErrors());


        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setStartDate(null);
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setEndDate(DateUtils.yesterday());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setGradedDate(new Date());
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertTrue(errors.hasErrors());


        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setStartDate(new Date());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setEndDate(DateUtils.yesterday());
        command.getAdverseEventReportingPeriod().getAdverseEvents().get(0).setGradedDate(DateUtils.tomorrow());
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertTrue(errors.hasErrors());
    }


    public void testValidate_Only1Grade5AdverseEvent(){
        command.initializeOutcomes();
        AdverseEventReportingPeriod reportingPeriod = command.getAdverseEventReportingPeriod();
        reportingPeriod.getAdverseEvents().get(0).setGrade(Grade.DEATH);
        reportingPeriod.getAdverseEvents().get(1).setGrade(Grade.DEATH);

        command.getStudy().setAeTermUnique(false);
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertTrue(errors.hasFieldErrors("adverseEvents[1].grade"));
        assertTrue(errors.getFieldErrors().get(0).getCode().equals("SAE_033"));
    }



    public void testValidate_EventHourMandatory(){
        command.initializeOutcomes();
        AdverseEventReportingPeriod reportingPeriod = command.getAdverseEventReportingPeriod();
        reportingPeriod.getAdverseEvents().remove(0);
        reportingPeriod.getAdverseEvents().remove(0);
        reportingPeriod.getAdverseEvents().remove(0);
        reportingPeriod.getAdverseEvents().get(0).setEventApproximateTime(new TimeValue(10,10));
        reportingPeriod.getAdverseEvents().get(1).setGrade(Grade.DEATH);
        caaersFieldConfigurationManager.getFieldConfigurationMap().get(AdverseEventCaptureTab.class.getName()).put("adverseEvents[].eventApproximateTime.hourString", Mandatory.MANDATORY);
        command.getStudy().setAeTermUnique(false);
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertTrue(errors.hasFieldErrors("adverseEvents[1].eventApproximateTime.hourString"));
        System.out.println(errors);
        assertTrue(errors.getFieldErrors().get(0).getCode().equals("CAE_019"));
    }


    public void testValidate_VerbatimLength(){
        command.initializeOutcomes();
        AdverseEventReportingPeriod reportingPeriod = command.getAdverseEventReportingPeriod();
        String hugeString = "xyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxy" +
                            "xyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxy" +
                            "zxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyzxyz";

        reportingPeriod.getAdverseEvents().get(0).setDetailsForOther("123456");
        reportingPeriod.getAdverseEvents().get(1).setDetailsForOther(hugeString);

        command.getStudy().setAeTermUnique(false);
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        tab.validate(command, commandWrapper, fieldMap, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertTrue(errors.hasFieldErrors("adverseEvents[1].detailsForOther"));
        assertTrue(errors.getFieldErrors().get(0).getCode().equals("SAE_021"));
    }

    
    public void testValidate_WhenVerbatimIsMandatory(){
        command.initializeOutcomes();
        command.getStudy().setAeTermUnique(false);
        caaersFieldConfigurationManager.getFieldConfigurationMap().get(AdverseEventCaptureTab.class.getName()).put("adverseEvents[].detailsForOther", Mandatory.MANDATORY);
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        {
            tab.validate(command, commandWrapper, fieldMap, errors);
            assertTrue(errors.hasErrors());
            assertEquals(5, errors.getErrorCount());
            assertTrue(errors.hasFieldErrors("adverseEvents[0].detailsForOther"));
            assertTrue(errors.hasFieldErrors("adverseEvents[1].detailsForOther"));
            assertTrue(errors.hasFieldErrors("adverseEvents[2].detailsForOther"));
            assertTrue(errors.hasFieldErrors("adverseEvents[3].detailsForOther"));
            assertTrue(errors.hasFieldErrors("adverseEvents[4].detailsForOther"));
        }


        {
           AdverseEventReportingPeriod reportingPeriod = command.getAdverseEventReportingPeriod();
           reportingPeriod.getAdverseEvents().get(0).setDetailsForOther("x");
           reportingPeriod.getAdverseEvents().get(4).setDetailsForOther("y");
           setUpCommand(reportingPeriod);
            tab.validate(command, commandWrapper, fieldMap, errors);
            assertTrue(errors.hasErrors());
            assertEquals(3, errors.getErrorCount());
            assertTrue(errors.hasFieldErrors("adverseEvents[1].detailsForOther"));
            assertTrue(errors.hasFieldErrors("adverseEvents[2].detailsForOther"));
            assertTrue(errors.hasFieldErrors("adverseEvents[3].detailsForOther"));
        }


        {
            AdverseEventReportingPeriod reportingPeriod = command.getAdverseEventReportingPeriod();
            reportingPeriod.getAdverseEvents().get(0).setDetailsForOther("x0");
            reportingPeriod.getAdverseEvents().get(1).setDetailsForOther("x1");
            reportingPeriod.getAdverseEvents().get(2).setDetailsForOther("x2");
            reportingPeriod.getAdverseEvents().get(3).setDetailsForOther("y1");
            reportingPeriod.getAdverseEvents().get(4).setDetailsForOther("y2");
            setUpCommand(reportingPeriod);
            tab.validate(command, commandWrapper, fieldMap, errors);
            assertFalse(errors.hasErrors());

        }




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
