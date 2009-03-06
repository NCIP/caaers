package gov.nih.nci.cabig.caaers.rules.deploy;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.CourseDate;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Dose;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.rules.RulesTestCase;
import gov.nih.nci.cabig.caaers.rules.objectgraph.FactResolver;
import gov.nih.nci.cabig.caaers.rules.runtime.BusinessRulesExecutionServiceImpl;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractBusinessRulesExecutionTestCase extends RulesTestCase {

    protected RuleDeploymentServiceImpl deploymetService;

    protected BusinessRulesExecutionServiceImpl executionService;

    public abstract String getBindUri();

    public abstract String getRuleFile();

    @Override
    public Class<AbstractBusinessRulesExecutionTestCase> getTestClass() {
        return AbstractBusinessRulesExecutionTestCase.class;
    }

    protected void setUp() throws Exception {
        super.setUp();
        deploymetService = new RuleDeploymentServiceImpl();
        executionService = new BusinessRulesExecutionServiceImpl();
        try {
            unregisterRule();
        } catch (Exception e) {
        }
        registerRule();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
        unregisterRule();
    }

    public void assertNoErrors(ValidationErrors errors, String... msg) {
        String message = (msg.length > 0) ? "[" + msg[0] + "]" : "";
        assertEquals("No errors should be there," + message, 0, errors.getErrorCount());
    }

    public void assertCorrectErrorCode(ValidationErrors errors, String... msg) {

        String errCode = (msg.length > 0) ? msg[0] : "";
        String message = (msg.length > 1) ? "[" + msg[1] + "]" : "";
        assertHasErrors(errors, message);
        for (int i = 0; i < errors.getErrorCount(); i++) {
            assertEquals("Error code should be same," + message, errCode, errors.getErrorAt(i)
                            .getCode());
        }
    }

    public void assertHasErrors(ValidationErrors errors, String... msg) {
        String message = (msg.length > 0) ? "[" + msg[0] + "]" : "";
        assertTrue("There should be errors," + message, errors.getErrorCount() > 0);
    }

    public void assertSameErrorCount(ValidationErrors errors, int n, String... msg) {
        String message = (msg.length > 0) ? "[" + msg[0] + "]" : "";
        assertEquals("Number of errors should same," + message, n, errors.getErrorCount());
    }

    public void assertHasErrorsHavingCode(ValidationErrors errors, String errCode){

        assertHasErrors(errors, "Must have errors");
        for (int i = 0; i < errors.getErrorCount(); i++) {
           if(errors.getErrorAt(i).getCode().equals(errCode)) return;
        }
        fail("At least one error with the code:" + errCode + " should exist");
    }

    public void assertNoErrorsHavingCode(ValidationErrors errors, String errCode){
    	if(errors.hasErrors()){
    		for (int i = 0; i < errors.getErrorCount(); i++) {
    	           if(errors.getErrorAt(i).getCode().equals(errCode)) fail("No error with error code :" + errCode + ", should exist");
    	    }
    	}
    }

    public ExpeditedAdverseEventReport createAEReport() {
        Participant p = Fixtures.createParticipant("John", "Doe");
        Study s = Fixtures.createStudy("Test");
        Organization org = Fixtures.createOrganization("Test Organization");
        StudyParticipantAssignment assignment = Fixtures.assignParticipant(p, s, org);
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        assignment.addReportingPeriod(reportingPeriod);
        reportingPeriod.addAeReport(aeReport);
        aeReport.setReportingPeriod(reportingPeriod);
        aeReport.setId(-5);
        
        // update adverseEvents
        AdverseEvent ae1 = new AdverseEvent();
        ae1.setGrade(Grade.MILD);
        ae1.setHospitalization(Hospitalization.NONE);
        ae1.setStartDate(DateUtils.createDate(2007, 11, 2));
        ae1.setEndDate(DateUtils.createDate(2007, 11, 4));
        AdverseEventCtcTerm aeCTC = new AdverseEventCtcTerm();
        aeCTC.setId(5);
        CtcTerm term = new CtcTerm();
        term.setId(5);
        term.setTerm("abc");
        aeCTC.setTerm(term);
        ae1.setAdverseEventCtcTerm(aeCTC);
        DiseaseAttribution da = new DiseaseAttribution();
        da.setAttribution(Attribution.PROBABLE);

        List<DiseaseAttribution> daList = new ArrayList<DiseaseAttribution>();
        daList.add(da);
        ae1.setDiseaseAttributions(daList);

        aeReport.addAdverseEvent(ae1);

        AdverseEvent ae2 = new AdverseEvent();
        ae2.setGrade(Grade.MILD);
        ae2.setHospitalization(Hospitalization.NONE);
        ae2.setStartDate(DateUtils.createDate(2007, 11, 2));
        ae2.setEndDate(DateUtils.createDate(2007, 11, 4));
        AdverseEventCtcTerm aeCTC2 = new AdverseEventCtcTerm();
        aeCTC2.setId(6);
        CtcTerm term2 = new CtcTerm();
        term2.setTerm("abcx");
        term2.setId(6);
        aeCTC2.setTerm(term2);
        ae2.setAdverseEventCtcTerm(aeCTC2);
        DiseaseAttribution da2 = new DiseaseAttribution();
        da2.setAttribution(Attribution.PROBABLE);

        List<DiseaseAttribution> daList2 = new ArrayList<DiseaseAttribution>();
        daList2.add(da2);
        ae2.setDiseaseAttributions(daList2);

        aeReport.addAdverseEvent(ae2);

        // update event description
        AdverseEventResponseDescription aeResponseDesc = new AdverseEventResponseDescription();
        aeResponseDesc.setEventDescription("This is a sample description");
        aeResponseDesc.setRecoveryDate(new Date());
        aeResponseDesc.setDateRemovedFromProtocol(new Date());
        aeResponseDesc.setPresentStatus(PostAdverseEventStatus.DEAD);
        aeResponseDesc.setRetreated(Boolean.FALSE);
        aeReport.setResponseDescription(aeResponseDesc);

        // patient details section
        DiseaseHistory aeDiseaseHistory = new DiseaseHistory();
        CtepStudyDisease studyDisease = new CtepStudyDisease();
        DiseaseTerm diseaseTerm = new DiseaseTerm();
        diseaseTerm.setTerm("Solid tumor, NOS");
        studyDisease.setDiseaseTerm(diseaseTerm);
        aeDiseaseHistory.setAbstractStudyDisease(studyDisease);
        aeDiseaseHistory.setOtherPrimaryDisease("MyOtherDisease");
        aeReport.setDiseaseHistory(aeDiseaseHistory);

        // Treatment Information
        TreatmentAssignment ta = new TreatmentAssignment();
        ta.setCode("Arm A");
        ta.setDescription("My TAC description");
        TreatmentInformation ti = new TreatmentInformation();
        ti.setTreatmentAssignment(ta);

        // - Course Information
        CourseDate adverseEventCourse = new CourseDate();
        adverseEventCourse.setDate(new Date());
        adverseEventCourse.setNumber(3);
        ti.setAdverseEventCourse(adverseEventCourse);
        ti.setTotalCourses(4);

        // -- populate course agents
        CourseAgent ca1 = new CourseAgent();

        Dose d1 = new Dose();
        d1.setAmount(new BigDecimal(5));
        d1.setUnits("abc1");

        StudyAgent sa1 = new StudyAgent();
        Agent a = new Agent();
        a.setName("xyz agent");
        sa1.setAgent(a);
        sa1.setId(44);
        ca1.setDose(d1);
        ca1.setStudyAgent(sa1);

        CourseAgent ca2 = new CourseAgent();
        Dose d2 = new Dose();
        d2.setAmount(new BigDecimal(51));
        d2.setUnits("abc2");
        ca2.setDose(d2);

        StudyAgent sa2 = new StudyAgent();
        sa2.setOtherAgent("OtherAgent");
        sa2.setId(55);
        ca2.setStudyAgent(sa2);

        ti.addCourseAgent(ca1);
        ti.addCourseAgent(ca2);

        aeReport.setTreatmentInformation(ti);

        // populate prior therapy
        PriorTherapy p1 = new PriorTherapy();
        p1.setId(14);
        p1.setText("Xyz");
        SAEReportPriorTherapy ap1 = new SAEReportPriorTherapy();
        ap1.setPriorTherapy(p1);
        aeReport.addSaeReportPriorTherapies(ap1);
        PriorTherapy p2 = new PriorTherapy();
        p2.setId(19);
        p2.setText("Xyzz");
        SAEReportPriorTherapy ap2 = new SAEReportPriorTherapy();
        ap2.setPriorTherapy(p2);
        aeReport.addSaeReportPriorTherapies(ap2);

        // populate pre-existing condition.
        PreExistingCondition pc1 = new PreExistingCondition();
        pc1.setText("abcd");
        SAEReportPreExistingCondition aePc = new SAEReportPreExistingCondition();
        aePc.setOther("Other");
        aePc.setPreExistingCondition(pc1);
        aeReport.addSaeReportPreExistingCondition(aePc);

        PreExistingCondition pc2 = new PreExistingCondition();
        pc2.setText("abcd");
        SAEReportPreExistingCondition aePc2 = new SAEReportPreExistingCondition();
        aePc2.setPreExistingCondition(pc2);
        aePc2.setOther("Other");
        aeReport.addSaeReportPreExistingCondition(aePc2);

        // populate lab information
        Lab l1 = new Lab();

        LabTerm t = new LabTerm();
        t.setTerm("LabName");
        l1.setLabTerm(t);
        aeReport.addLab(l1);

        Lab l2 = new Lab();
        LabTerm t2 = new LabTerm();
        t2.setTerm("LabName2");
        l2.setLabTerm(t2);
        aeReport.addLab(l2);

        return aeReport;
    }


    public ValidationErrors retrieveValidationErrors(List<Object> list) {
        for (Object o : list)
            if (o instanceof ValidationErrors) return (ValidationErrors) o;
        return null;
    }

    public ValidationErrors fireRules(ExpeditedAdverseEventReport aeReport) throws Exception {
        List<Object> input = new ArrayList<Object>();
        input.add(aeReport);
        ValidationErrors errors = new ValidationErrors();
        input.add(errors);

        List<Object> output = executionService.fireRules(getBindUri(), input);
        errors = retrieveValidationErrors(output);
        printErrors(errors);
        return errors;
    }

    public ValidationErrors fireRules(ExpeditedAdverseEventReport aeReport, Study study)
                    throws Exception {
        List<Object> input = new ArrayList<Object>();
        input.add(aeReport);
        input.add(study);
        FactResolver factResolver = new FactResolver();
        input.add(factResolver);
        ValidationErrors errors = new ValidationErrors();
        input.add(errors);

        List<Object> output = executionService.fireRules(getBindUri(), input);
        errors = retrieveValidationErrors(output);
        printErrors(errors);
        return errors;
    }

    public void printErrors(ValidationErrors errros) {
        System.out.println("{");
        for (ValidationError error : errros.getErrors()) {
            System.out.println("\t" + error);
        }
        System.out.println("}");
    }

    public void registerRule() throws Exception {
        String ruleXml = getFileContext(getRuleFile());
        try {
        	deploymetService.deregisterRuleSet(getBindUri());
        } catch (Exception e) {
        	System.out.println("registering for first time");
        }
        deploymetService.registerRuleXml(getBindUri(), ruleXml);
        assertTrue("Rule deployed", true);
    }

    public void unregisterRule() throws Exception {
        deploymetService.deregisterRuleSet(getBindUri());
        assertTrue("Rule undeployed", true);
    }
}
