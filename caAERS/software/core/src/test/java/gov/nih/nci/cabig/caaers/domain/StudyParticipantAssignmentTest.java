package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Saurabh Agrawal
 */
public class StudyParticipantAssignmentTest extends AbstractNoSecurityTestCase {

    private StudyParticipantAssignment assignment;

    private ExpeditedAdverseEventReport report;
    private Participant participant;
    private SAEReportPriorTherapy saeReportPriorTherapy;
    private StudyParticipantPriorTherapy existingPriorTherapy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createAssignment();
        report = new ExpeditedAdverseEventReport();
        AdverseEventReportingPeriod period = new AdverseEventReportingPeriod();
        period.setAssignment(assignment);
        report.setReportingPeriod(period);
      
        PriorTherapy priorTherapy = new PriorTherapy();
        saeReportPriorTherapy = new SAEReportPriorTherapy();
        saeReportPriorTherapy.setPriorTherapy(priorTherapy);
      
        existingPriorTherapy = new StudyParticipantPriorTherapy();
        existingPriorTherapy.setPriorTherapy(priorTherapy);
    }

    private void createAssignment() {
        assignment = new StudyParticipantAssignment();
        assignment.setId(1);
        participant = new Participant();
        participant.setFirstName("first name");
        assignment.setParticipant(participant);
        assignment.setPriorTherapies(new ArrayList<StudyParticipantPriorTherapy>());
        assignment.setConcomitantMedications(new ArrayList<StudyParticipantConcomitantMedication>());
        assignment.setPreExistingConditions(new ArrayList<StudyParticipantPreExistingCondition>());
    }

    public void testWrongUsesOfSyncrhonizeMethod() {
        report.setReportingPeriod(new AdverseEventReportingPeriod());
        String message = String.format("Wrong uses of synchronizeMedicalHistoryFromReportToAssignment. " + "This report %s does not belong to this assigment %s ", report.getId(), assignment.getParticipant().getFullName());
        try {
            assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
            fail();
        } catch (CaaersSystemException e) {
            assertEquals(message, e.getMessage());
        }
    }

    public void testSyncrhonizePriorTherapies() {
        report.addSaeReportPriorTherapies(saeReportPriorTherapy);

        StudySite ss = new StudySite();
        ss.setStudy(new Study());
        assignment.setStudySite(ss);
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        report.getStudy().setDiseaseTerminology(new DiseaseTerminology());
        report.getStudy().getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        report.getDiseaseHistory().setCtepStudyDisease(new CtepStudyDisease());
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must copy the prior therapy", 1, assignment.getPriorTherapies().size());
        
        //create a fresh assignment, add one StudyParticipantPriorTherapy. 
        createAssignment();
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        assignment.addPriorTherapy(existingPriorTherapy);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must not copy the prior therapy", 1, assignment.getPriorTherapies().size());
    }

    public void testSyncrhonizeDiseaseHistory() {
        DiseaseHistory diseaseHistory = new DiseaseHistory();
        report.setDiseaseHistory(diseaseHistory);

        StudySite ss = new StudySite();
        ss.setStudy(new Study());
        assignment.setStudySite(ss);
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        report.getStudy().setDiseaseTerminology(new DiseaseTerminology());
        report.getStudy().getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        report.getDiseaseHistory().setCtepStudyDisease(new CtepStudyDisease());
        
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        report.getDiseaseHistory().addMetastaticDiseaseSite(new MetastaticDiseaseSite());
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must  copy the MetastaticDiseaseSite", 1, assignment.getDiseaseHistory().getMetastaticDiseaseSites().size());

        createAssignment();
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        MetastaticDiseaseSite diseaseSite = new MetastaticDiseaseSite();
        diseaseSite.setId(2);
        
        report.getDiseaseHistory().addMetastaticDiseaseSite(diseaseSite);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must not copy the MetastaticDiseaseSite", 1, assignment.getDiseaseHistory().getMetastaticDiseaseSites().size());
    }

    public void testSyncrhonizePreExistingCondition() {
        StudySite ss = new StudySite();
        ss.setStudy(new Study());
        assignment.setStudySite(ss);
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        report.getStudy().setDiseaseTerminology(new DiseaseTerminology());
        report.getStudy().getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        report.getDiseaseHistory().setCtepStudyDisease(new CtepStudyDisease());

        report.addSaeReportPreExistingCondition(new SAEReportPreExistingCondition());
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must copy the pre existing condition", 1, assignment.getPreExistingConditions().size());

        createAssignment();
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        
        SAEReportPreExistingCondition reportPreExistingCondition = new SAEReportPreExistingCondition();
        reportPreExistingCondition.setId(1);
        report.addSaeReportPreExistingCondition(reportPreExistingCondition);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must not copy the pre existing condition", 1, assignment.getPreExistingConditions().size());
    }

    public void testSyncrhonizeConcomitantMedication() {
    	ConcomitantMedication conMed = new ConcomitantMedication();
    	conMed.setAgentName("myagent");
        report.addConcomitantMedication(conMed);

        StudySite ss = new StudySite();
        ss.setStudy(new Study());
        assignment.setStudySite(ss);
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        report.getStudy().setDiseaseTerminology(new DiseaseTerminology());
        report.getStudy().getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        report.getDiseaseHistory().setCtepStudyDisease(new CtepStudyDisease());

        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must copy the concomitantMedication", 1, assignment.getConcomitantMedications().size());

        createAssignment();
        assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        
        ConcomitantMedication concomitantMedication = new ConcomitantMedication();
        concomitantMedication.setId(2);
        report.addConcomitantMedication(concomitantMedication);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must not copy the concomitantMedication", 1, assignment.getConcomitantMedications().size());
    }
    
    public void testGetMaxCycleNumber(){
    	AdverseEventReportingPeriod rp1 = Fixtures.createReportingPeriod();
    	AdverseEventReportingPeriod rp2 = Fixtures.createReportingPeriod();
    	AdverseEventReportingPeriod rp3 = Fixtures.createReportingPeriod();
    	assignment.addReportingPeriod(rp1);
    	assignment.addReportingPeriod(rp2);
    	assignment.addReportingPeriod(rp3);
    	rp1.setCycleNumber(5);
    	rp2.setCycleNumber(4);
    	Integer maxCycle = assignment.getMaxCycleNumber();
    	assertEquals(new Integer(5), maxCycle);
    }
}
