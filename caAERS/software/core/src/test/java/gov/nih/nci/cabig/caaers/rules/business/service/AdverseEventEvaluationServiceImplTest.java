package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import org.drools.spi.AgendaFilter;
import org.easymock.classextension.EasyMock;

import com.semanticbits.rules.api.BusinessRulesExecutionService;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.impl.RuleEvaluationResult;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventEvaluationServiceImplTest extends AbstractTestCase {
	Study study;
    BusinessRulesExecutionService businessRulesExecutionService;
    CaaersRulesEngineService caaersRulesEngineService;
    AdverseEventEvaluationServiceImpl impl;
    
    ExpeditedAdverseEventReport aeReport;
    List<Report> reports;
    List<AdverseEvent> aeList;
    
    final  List<Object> evaluationResult = new ArrayList<Object>();
    
    
	protected void setUp() throws Exception {
		super.setUp();
		businessRulesExecutionService = new BusinessRulesExecutionService(){
			public List<Object> fireRules(String arg0, List<Object> arg1) {
				return evaluationResult;
			}
		};
		caaersRulesEngineService = registerMockFor(CaaersRulesEngineService.class);
		aeReport = registerMockFor(ExpeditedAdverseEventReport.class);
		study = registerMockFor(Study.class);
		reports = new ArrayList<Report>();
		Report r1 = Fixtures.createReport("r1");
		Report r2 = Fixtures.createReport("r2");
		Report r3 = Fixtures.createReport("r3");
		reports.add(r1);
		reports.add(r2);
		reports.add(r3);
		
		aeList = new ArrayList<AdverseEvent>();
		aeList.add(Fixtures.createAdverseEvent(1, Grade.SEVERE));
		
		impl = new AdverseEventEvaluationServiceImpl();
		impl.setBusinessRulesExecutionService(businessRulesExecutionService);
		impl.setCaaersRulesEngineService(caaersRulesEngineService);
		
	}

	
	/**
	 * This method test {@link AdverseEventEvaluationServiceImpl#mandatorySections(gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport, gov.nih.nci.cabig.caaers.domain.report.ReportDefinition...)}
	 */
	public void testMandatorySections() throws Exception {
        EasyMock.expect(aeReport.getTreatmentInformation()).andReturn(null).anyTimes();
		EasyMock.expect(aeReport.getStudy()).andReturn(study).anyTimes();
		EasyMock.expect(study.getStudyOrganizations()).andReturn(new ArrayList<StudyOrganization>()).anyTimes();
		EasyMock.expect(aeReport.getAdverseEvents()).andReturn(aeList).anyTimes();
		EasyMock.expect(study.getPrimaryFundingSponsorOrganization()).andReturn(Fixtures.createOrganization("test","test")).anyTimes();
		EasyMock.expect(study.getShortTitle()).andReturn("test").anyTimes();
		EasyMock.expect(caaersRulesEngineService.getRuleSetForSponsorDefinedStudy("Mandatory Sections Rules", "test", "test")).andReturn(new RuleSet()).anyTimes();
		
		//frame the evalutaion result
		RuleEvaluationResult result1 = new RuleEvaluationResult();
		result1.setMessage("ADVERSE_EVENT_SECTION");
		evaluationResult.add(result1);
		
		replayMocks();
		Collection<ExpeditedReportSection> sections = impl.mandatorySections(aeReport, reports.get(0).getReportDefinition(), reports.get(1).getReportDefinition(), reports.get(2).getReportDefinition() );
		assertEquals("ADVERSE_EVENT_SECTION", sections.iterator().next().name());
		verifyMocks();
	}
	
	/**
	 * This method test {@link AdverseEventEvaluationServiceImpl#mandatorySections(gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport, gov.nih.nci.cabig.caaers.domain.report.ReportDefinition...)}
	 */
	public void testMandatorySections_AllReportsActive() throws Exception{
		EasyMock.expect(aeReport.getReports()).andReturn(reports);
        EasyMock.expect(aeReport.getTreatmentInformation()).andReturn(null).anyTimes();
		EasyMock.expect(aeReport.getStudy()).andReturn(study).anyTimes();
		EasyMock.expect(study.getStudyOrganizations()).andReturn(new ArrayList<StudyOrganization>()).anyTimes();
		EasyMock.expect(aeReport.getAdverseEvents()).andReturn(aeList).anyTimes();
		EasyMock.expect(study.getPrimaryFundingSponsorOrganization()).andReturn(Fixtures.createOrganization("test","test")).anyTimes();
		EasyMock.expect(study.getShortTitle()).andReturn("test").anyTimes();
		EasyMock.expect(caaersRulesEngineService.getRuleSetForSponsorDefinedStudy("Mandatory Sections Rules", "test", "test")).andReturn(new RuleSet()).anyTimes();
		
		//frame the evalutaion result
		RuleEvaluationResult result1 = new RuleEvaluationResult();
		result1.setMessage("ADVERSE_EVENT_SECTION");
		evaluationResult.add(result1);
		
		replayMocks();
		Collection<ExpeditedReportSection> sections = impl.mandatorySections(aeReport);
		assertEquals("ADVERSE_EVENT_SECTION", sections.iterator().next().name());
		verifyMocks();
	}

	/**
	 * This method test {@link AdverseEventEvaluationServiceImpl#mandatorySections(gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport, gov.nih.nci.cabig.caaers.domain.report.ReportDefinition...)}
	 */
	public void testMandatorySections_AllReportsInActive() throws Exception{
		EasyMock.expect(aeReport.getReports()).andReturn(reports);
		for(Report r: reports) r.setStatus(ReportStatus.REPLACED);
		EasyMock.expect(aeReport.getStudy()).andReturn(study).anyTimes();
		EasyMock.expect(study.getStudyOrganizations()).andReturn(new ArrayList<StudyOrganization>()).anyTimes();
		EasyMock.expect(aeReport.getAdverseEvents()).andReturn(aeList).anyTimes();
		EasyMock.expect(study.getPrimaryFundingSponsorOrganization()).andReturn(Fixtures.createOrganization("test","test")).anyTimes();
		EasyMock.expect(study.getShortTitle()).andReturn("test").anyTimes();
		EasyMock.expect(caaersRulesEngineService.getRuleSetForSponsorDefinedStudy("Mandatory Sections Rules", "test", "test")).andReturn(new RuleSet()).anyTimes();
		
		//frame the evalutaion result
		RuleEvaluationResult result1 = new RuleEvaluationResult();
		result1.setMessage("ADVERSE_EVENT_SECTION");
		evaluationResult.add(result1);
		
		replayMocks();
		Collection<ExpeditedReportSection> sections = impl.mandatorySections(aeReport);
		assertTrue(sections.isEmpty());
		verifyMocks();
	}

    //optional when mandatory field has no rules information.
    public void testEvaluateFieldLevelRules(){
        EasyMock.expect(aeReport.getTreatmentInformation()).andReturn(null).anyTimes();
        replayMocks();
        ReportMandatoryFieldDefinition def1 = Fixtures.createMandatoryField("a", RequirednessIndicator.OPTIONAL);
        assertEquals("OPTIONAL", impl.evaluateFieldLevelRules(aeReport, reports.get(0), def1));
        verifyMocks();
    }

    //checks that agenda filter is created and passed in the input
    public void testEvaluateFieldLevelRulesAgnedaFilterAvailable(){
       impl.setBusinessRulesExecutionService(new BusinessRulesExecutionService(){
           public List<Object> fireRules(String bindingURI, List<Object> objects) {
               boolean hasAgendaFilter = false;
               for(Object o : objects) hasAgendaFilter |= (o instanceof AgendaFilter);
               if(!hasAgendaFilter) throw new RuntimeException("Agenda filter not present in input");
               return null;
           }
       });
       EasyMock.expect(aeReport.getTreatmentInformation()).andReturn(null).anyTimes();
       EasyMock.expect(aeReport.getStudy()).andReturn(study).anyTimes();
       replayMocks();
       ReportMandatoryFieldDefinition def1 = Fixtures.createMandatoryField("a", RequirednessIndicator.OPTIONAL);
       def1.setRuleBindURL("abc");
       def1.setRuleName("a");
       impl.evaluateFieldLevelRules(aeReport, reports.get(0), def1);
       verifyMocks();
    }

}
