/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryField;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import org.easymock.classextension.EasyMock;

import java.util.*;

import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.ATTRIBUTION_SECTION;

/**
 *  
 * @author Sameer Sawant
 * @author Biju Joseph
 */


public class ReportValidationServiceTest extends AbstractTestCase {
	
	private ReportValidationServiceImpl reportValidationService;
	private ExpeditedReportTree expeditedReportTree;
	private ExpeditedAdverseEventReport expeditedData;
    private EvaluationService evaluationService;
	
	private static final String TERM = "Auralmonagem";
	private static final Attribution[] SUFFICIENT_ATTRIBUTIONS = new Attribution[]{
        Attribution.POSSIBLE, Attribution.PROBABLE, Attribution.DEFINITE};

	private static final Attribution[] INSUFFICENT_ATTRIBUTIONS = new Attribution[]{ Attribution.UNLIKELY, Attribution.UNRELATED };
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        expeditedReportTree = new ExpeditedReportTree(null);
        reportValidationService = new ReportValidationServiceImpl();
        
        reportValidationService.setExpeditedReportTree(expeditedReportTree);
        evaluationService = registerMockFor(EvaluationService.class);
        reportValidationService.setEvaluationService(evaluationService);
        
        expeditedData = new ExpeditedAdverseEventReport();
        CtcTerm ctcTerm = new CtcTerm();
        ctcTerm.setTerm(TERM);
        expeditedData.getAdverseEvents().get(0).getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
	}
	
	private ReportSubmittability validateForAttribution() {
        return reportValidationService.validate(createAttributionMandatoryReport(), Collections.<ExpeditedReportSection>emptySet(), new ArrayList<ExpeditedReportSection>());
    }
	
	private Report createAttributionMandatoryReport() {
        Report report = new Report();
        report.setReportDefinition(new ReportDefinition());
        report.getReportDefinition().setId(new Integer(0));
        report.getReportDefinition().setPhysicianSignOff(false);
        report.setAeReport(expeditedData);
        report.getReportDefinition().setAttributionRequired(true);
        report.setMandatoryFields(new ArrayList<ReportMandatoryField>());
        // TODO:
        // report.setAttributionMandatory(true);
        return report;
    }
	
	private void assertNoAttributionsAreSufficent(AdverseEventAttribution attr) {
        for (Attribution attribution : Attribution.values()) {
            attr.setAttribution(attribution);
            ReportSubmittability actual = validateForAttribution();
            assertInsuffientAttributionMessage(attribution + " should not be sufficient", actual);
        }
    }

    private void assertSufficientAttributionsAreSufficent(AdverseEventAttribution attr) {
        for (Attribution attribution : SUFFICIENT_ATTRIBUTIONS) {
            attr.setAttribution(attribution);
            ReportSubmittability actual = validateForAttribution();
            assertTrue(attribution + " should be sufficent", actual.isSubmittable());
        }

        for (Attribution attribution : INSUFFICENT_ATTRIBUTIONS) {
            attr.setAttribution(attribution);
            ReportSubmittability actual = validateForAttribution();
            assertInsuffientAttributionMessage(attribution + " should not be sufficent", actual);
        }
    }
    
    private void assertInsuffientAttributionMessage(String assertionMessage,
            ReportSubmittability container) {
    		assertTrue(assertionMessage + ": No attribution section messages", container.getMessages().containsKey(ATTRIBUTION_SECTION));
    		assertTrue(assertionMessage + ": No attribution section messages", container.getMessages().get(ATTRIBUTION_SECTION).size() > 0);
    		assertEquals(
    				assertionMessage + ": Wrong message",
    				"The adverse event "
    				+ TERM
    				+ " is not attributed to a cause. An attribution of possible or higher must be selected for at least one of the causes.",
    				container.getMessages().get(ATTRIBUTION_SECTION).get(0).getText());
    }

    //case where there is no mandatory section.
    public void testIsSubmittable() throws Exception{
        Report report = createAttributionMandatoryReport();
        Map<Integer, Collection<ExpeditedReportSection>> mandatorySectionMap = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        ArrayList<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        mandatorySectionMap.put(new Integer(0), sections);
        EasyMock.expect(evaluationService.mandatorySections(expeditedData, report.getReportDefinition())).andReturn(mandatorySectionMap);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        report.getReportDefinition().setAttributionRequired(false);
        replayMocks();
        ReportSubmittability result =  reportValidationService.isSubmittable(report);
        assertTrue(result.isSubmittable()) ;
        verifyMocks();
    }

    //case where Study Intervention & Agents is mandatory, but it is not provided
    public void testIsSubmittableInterventionsMandatory() throws Exception{
        Report report = createAttributionMandatoryReport();
        Map<Integer, Collection<ExpeditedReportSection>> mandatorySectionMap = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        ArrayList<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.STUDY_INTERVENTIONS);
        sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
        mandatorySectionMap.put(new Integer(0), sections);
        EasyMock.expect(evaluationService.mandatorySections(expeditedData, report.getReportDefinition())).andReturn(mandatorySectionMap);
         evaluationService.evaluateMandatoryness(expeditedData, report);

        report.getReportDefinition().setAttributionRequired(false);
        replayMocks();
        ReportSubmittability result =  reportValidationService.isSubmittable(report);
        assertFalse(result.isSubmittable()) ;
        assertTrue(result.getMessages().get(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION).get(0).getText().equals("The section 'Agents' is mandatory for this report and cannot be empty"));

        verifyMocks();
    }

    //case where StudyIntervention is mandatory, and Agent information is provided
    public void testIsSubmittableInterventionsMandatoryAndIsProvided() throws Exception{
        Report report = createAttributionMandatoryReport();
        Map<Integer, Collection<ExpeditedReportSection>> mandatorySectionMap = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        ArrayList<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.STUDY_INTERVENTIONS);
        mandatorySectionMap.put(new Integer(0), sections);
        EasyMock.expect(evaluationService.mandatorySections(expeditedData, report.getReportDefinition())).andReturn(mandatorySectionMap);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        report.getReportDefinition().setAttributionRequired(false);
        populateAgents(expeditedData);

        replayMocks();
        ReportSubmittability result =  reportValidationService.isSubmittable(report);
        assertTrue(result.isSubmittable()) ;

        verifyMocks();
    }

    //case where Radiation is mandatory, but only agent is provided
    public void testIsSubmittableRadiationInterventionIsMandatoryButAgentInterventionProvided() throws Exception{
        Report report = createAttributionMandatoryReport();
        Map<Integer, Collection<ExpeditedReportSection>> mandatorySectionMap = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        ArrayList<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.STUDY_INTERVENTIONS);
        sections.add(ExpeditedReportSection.RADIATION_INTERVENTION_SECTION);
        mandatorySectionMap.put(new Integer(0), sections);
        EasyMock.expect(evaluationService.mandatorySections(expeditedData, report.getReportDefinition())).andReturn(mandatorySectionMap);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        report.getReportDefinition().setAttributionRequired(false);
        populateAgents(expeditedData);

        replayMocks();
        ReportSubmittability result =  reportValidationService.isSubmittable(report);
        assertFalse(result.isSubmittable()) ;
        assertTrue(result.getMessages().get(ExpeditedReportSection.RADIATION_INTERVENTION_SECTION).get(0).getText().equals("The section 'Radiation' is mandatory for this report and cannot be empty"));

        verifyMocks();
    }




    public void testIsElementPresentInSection(){
        //value available
        assertTrue(reportValidationService.isElementPresentInSection( expeditedData, ExpeditedReportSection.ADVERSE_EVENT_SECTION));
        //value not available
        assertFalse(reportValidationService.isElementPresentInSection( expeditedData, ExpeditedReportSection.AGENTS_INTERVENTION_SECTION));
        //section has no fields in tree
        assertTrue(reportValidationService.isElementPresentInSection( expeditedData, ExpeditedReportSection.STUDY_INTERVENTIONS));

        //soft delete all adverse events - validates the case where there are only retired elements.
        for(AdverseEvent ae : expeditedData.getAdverseEvents()) ae.retire();
        assertFalse(reportValidationService.isElementPresentInSection( expeditedData, ExpeditedReportSection.ADVERSE_EVENT_SECTION));

    }

    //checkes if the agent (generic fields are valid)
    public void testValidate(){
        populateAgents(expeditedData);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false); //turn off attribution requiredness

        //set mandatory fields on agent fields
        ReportMandatoryField mf1 = new ReportMandatoryField("treatmentInformation.courseAgents[].studyAgent", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("treatmentInformation.courseAgents[].formulation", Mandatory.MANDATORY);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);

        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();

        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        assertTrue(reportSubmittability.isSubmittable());
        verifyMocks();

    }



    //checkes if the agent (generic fields are valid)
    public void testValidateWithErrors(){
        populateAgents(expeditedData);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false); //turn off attribution requiredness

        //set mandatory fields on agent fields
        ReportMandatoryField mf1 = new ReportMandatoryField("treatmentInformation.courseAgents[].studyAgent", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("treatmentInformation.courseAgents[].lotNumber", Mandatory.MANDATORY);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);

        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();

        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        assertFalse(reportSubmittability.isSubmittable());
        verifyMocks();

    }


    //checkes if the agent (indexed)
    public void testValidateWithSelfReferencedFields(){
        populateAgents(expeditedData);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false); //turn off attribution requiredness

        //set mandatory fields on agent fields
        ReportMandatoryField mf1 = new ReportMandatoryField("treatmentInformation.courseAgents[0].studyAgent", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("treatmentInformation.courseAgents[0].formulation", Mandatory.MANDATORY);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);

        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();

        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        assertTrue(reportSubmittability.isSubmittable());
        verifyMocks();

    }




    //checkes if only the indexed agent is validated
    public void testValidateWithSelfReferencedFieldsOnlyAtAnIndex(){
        populateAgents(expeditedData);
        expeditedData.getTreatmentInformation().getCourseAgents().add(new CourseAgent());
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false); //turn off attribution requiredness

        //set mandatory fields on agent fields
        ReportMandatoryField mf1 = new ReportMandatoryField("treatmentInformation.courseAgents[0].studyAgent", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("treatmentInformation.courseAgents[0].formulation", Mandatory.MANDATORY);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);

        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();

        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        assertTrue(reportSubmittability.isSubmittable());
        verifyMocks();

    }




    //checkes if both self referenced and non self referenced agents are validated
    public void testValidateMix(){
        populateAgents(expeditedData);
        expeditedData.getTreatmentInformation().getCourseAgents().add(new CourseAgent());
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false); //turn off attribution requiredness

        //set mandatory fields on agent fields
        ReportMandatoryField mf1 = new ReportMandatoryField("treatmentInformation.courseAgents[].studyAgent", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("treatmentInformation.courseAgents[0].formulation", Mandatory.MANDATORY);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);

        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();

        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        assertFalse(reportSubmittability.isSubmittable());
        assertTrue(reportSubmittability.getMessages().containsKey(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION));
        verifyMocks();

    }

    public void testValidateHiddenLabFields(){
        populateLabs(expeditedData);
        ReportMandatoryField mf1 = new ReportMandatoryField("labs[].baseline.value", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("labs[].labDate", Mandatory.MANDATORY);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();
        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.LABS_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        System.out.println(reportSubmittability.getMessages());
        assertTrue(reportSubmittability.isSubmittable());
        verifyMocks();
    }


    public void testValidateHiddenLabFieldsWhenDataNotProvied(){
        populateLabs(expeditedData);
        ReportMandatoryField mf1 = new ReportMandatoryField("labs[].recovery.value", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("labs[].site", Mandatory.MANDATORY);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();
        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.LABS_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        System.out.println(reportSubmittability.getMessages());
        assertFalse(reportSubmittability.isSubmittable());
        verifyMocks();
    }

    public void testValidateHiddenMedicalDeviceFields(){
       populateMedicalDevices(expeditedData);
        ReportMandatoryField mf1 = new ReportMandatoryField("medicalDevices[].reprocessorName", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("medicalDevices[].returnedDate", Mandatory.MANDATORY);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();
        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.MEDICAL_DEVICE_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        System.out.println(reportSubmittability.getMessages());
        assertTrue(reportSubmittability.isSubmittable());
        verifyMocks();
    }

    public void testValidateHiddenMedicalDeviceFieldsWhenValuesNotProvided(){
        populateMedicalDevices(expeditedData);
        expeditedData.getMedicalDevices().get(0).setReprocessorName(null);
        expeditedData.getMedicalDevices().get(1).setReturnedDate(null);
        ReportMandatoryField mf1 = new ReportMandatoryField("medicalDevices[].reprocessorName", Mandatory.MANDATORY);
        ReportMandatoryField mf2 = new ReportMandatoryField("medicalDevices[].returnedDate", Mandatory.MANDATORY);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false);
        report.getMandatoryFields().add(mf1);
        report.getMandatoryFields().add(mf2);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();
        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.MEDICAL_DEVICE_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        System.out.println(reportSubmittability.getMessages());
        assertFalse(reportSubmittability.isSubmittable());
        verifyMocks();
    }


    public void testValidateHiddenConMedFields(){
        expeditedData.addConcomitantMedication(Fixtures.createConcomitantMedication(true));
        ReportMandatoryField mf1 = new ReportMandatoryField("concomitantMedications[].endDate", Mandatory.MANDATORY);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false);
        report.getMandatoryFields().add(mf1);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();
        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.CONCOMITANT_MEDICATION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        System.out.println(reportSubmittability.getMessages());
        assertTrue(reportSubmittability.isSubmittable());
        verifyMocks();
    }


    public void testValidateHiddenConMedFieldsNotProvided(){
        expeditedData.addConcomitantMedication(Fixtures.createConcomitantMedication(false));
        ReportMandatoryField mf1 = new ReportMandatoryField("concomitantMedications[].endDate.year", Mandatory.MANDATORY);
        Report report = createAttributionMandatoryReport();
        report.getReportDefinition().setAttributionRequired(false);
        report.getMandatoryFields().add(mf1);
        evaluationService.evaluateMandatoryness(expeditedData, report);

        replayMocks();
        List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
        sections.add(ExpeditedReportSection.CONCOMITANT_MEDICATION_SECTION);
        ReportSubmittability reportSubmittability = reportValidationService.validate(report, sections , null);
        System.out.println(reportSubmittability.getMessages());
        assertFalse(reportSubmittability.isSubmittable());
        verifyMocks();
    }


    private void populateMedicalDevices(ExpeditedAdverseEventReport aeReport){
        MedicalDevice md1 = Fixtures.createMedicalDevice(true, false);
        md1.setReprocessorAddress("x");
        md1.setReprocessorName("x");
        MedicalDevice md2 = Fixtures.createMedicalDevice(false, true);
        md2.setReturnedDate(new Date());
        aeReport.addMedicalDevice(md1);
        aeReport.addMedicalDevice(md2);


    }
    private void populateLabs(ExpeditedAdverseEventReport aeReport){
        Lab l1 = Fixtures.createLab("abcd", "efg");
        l1.getBaseline().setDate(new Date());
        l1.getBaseline().setValue("1");

        Lab l2 = Fixtures.createLab("Microbiology NOS", "Microbiology");
        l2.setLabDate(new Date());
        aeReport.addLab(l1);
        aeReport.addLab(l2);

    }


    //Will populate agent information in the Expedited Adverse Event Report
    private void populateAgents(ExpeditedAdverseEventReport aeReport){
        // Treatment Information
        TreatmentAssignment ta = new TreatmentAssignment();
        ta.setCode("Arm A");
        ta.setDescription("My TAC description");
        TreatmentInformation ti = new TreatmentInformation();
        ti.setTreatmentAssignment(ta);


        // - Course Information
        CourseDate adverseEventCourse = new CourseDate();
        adverseEventCourse.setDate(new Date());
        adverseEventCourse.setNumber(1);
        ti.setAdverseEventCourse(adverseEventCourse);
        ti.setTotalCourses(1);

        // -- populate course agents
        CourseAgent ca1 = new CourseAgent();

        Dose d1 = new Dose();
        d1.setAmount("5");
        d1.setUnits("abc1");

        StudyAgent sa1 = new StudyAgent();
        Agent a = new Agent();
        a.setName("xyz agent");
        sa1.setAgent(a);
        sa1.setId(44);
        ca1.setDose(d1);
        ca1.setStudyAgent(sa1);
        ca1.setFormulation("test");

        ti.addCourseAgent(ca1);
        aeReport.setTreatmentInformation(ti);
    }
}
