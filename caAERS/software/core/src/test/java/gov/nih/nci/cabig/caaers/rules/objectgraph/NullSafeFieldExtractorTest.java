package gov.nih.nci.cabig.caaers.rules.objectgraph;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.NotReadablePropertyException;

import com.semanticbits.rules.objectgraph.NullSafeFieldExtractor;

public class NullSafeFieldExtractorTest extends AbstractNoSecurityTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testExtractFieldWithNull(){
    	 ExpeditedAdverseEventReport aeReport = createAEReport();
    	 aeReport.getLabs().get(0);
    	 Object o = NullSafeFieldExtractor.extractField(aeReport,
         "labs[0].labTerm.category.name");
    	 assertTrue(o.equals("null"));
    }

    public void testExtractField() {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (AdverseEvent adverseEvent : aeReport.getAdverseEvents()) {
            System.out.println(NullSafeFieldExtractor.extractField(adverseEvent,
                    "lowLevelTerm")
            );

        }
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        Object o = NullSafeFieldExtractor.extractField(aeReport,
                "responseDescription.presentStatus.code");
        assertTrue(o.equals( 6));
    }

    public void testExtractFieldNullInput() {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(null);
        Object o = NullSafeFieldExtractor.extractField(null,
                "responseDescription.presentStatus.displayName");
        System.out.println(o);
        assertTrue(true);
    }

    public void testExtractFieldInvalidProperty() {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(null);
        try {
            Object o = NullSafeFieldExtractor.extractField(aeReport,
                    "xresponseDescription.presentStatus.displayName");
        } catch (NotReadablePropertyException e) {

        }
    	assertTrue(true);
    }

    public ExpeditedAdverseEventReport createAEReport() {
        Participant p = Fixtures.createParticipant("Biju", "Joseph");
        Study s = Fixtures.createStudy("Test");
        Organization org = Fixtures.createOrganization("Test");
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
        d1.setAmount("5");
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
        d2.setAmount("51");
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


}
