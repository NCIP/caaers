package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Saurabh Agrawal
 */
public class StudyParticipantAssignmentTest extends CaaersTestCase {

    private StudyParticipantAssignment assignment;

    private ExpeditedAdverseEventReport report;
    private Participant participant;
    private SAEReportPriorTherapy saeReportPriorTherapy;
    private SAEReportPriorTherapy existingSaeReportPriorTherapy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createAssignment();
        report = new ExpeditedAdverseEventReport();
        AdverseEventReportingPeriod period = new AdverseEventReportingPeriod();
        period.setAssignment(assignment);
        report.setReportingPeriod(period);
        saeReportPriorTherapy = new SAEReportPriorTherapy();
        existingSaeReportPriorTherapy = new SAEReportPriorTherapy();
        existingSaeReportPriorTherapy.setId(1);
    }

    private void createAssignment() {
        assignment = new StudyParticipantAssignment();
        assignment.setId(1);
        participant = new Participant();
        participant.setFirstName("first name");
        assignment.setParticipant(participant);
    }

    public void testWrongUsesOfSyncrhonizeMethod() {
        report.setReportingPeriod(new AdverseEventReportingPeriod());
        String message = String.format("Wrong uses of synchronizeMedicalHistoryFromReportToAssignment. " +
                "This report %s does not belong to this assigment %s ", report.getId(), assignment.getParticipant().getFullName());
        try {
            assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
            fail();

        } catch (CaaersSystemException e) {
            assertEquals(message, e.getMessage());
        }

    }

    public void testSyncrhonizePriorTherapies() {
        report.addSaeReportPriorTherapies(saeReportPriorTherapy);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must copy the prior therapy", 1, assignment.getPriorTherapies().size());

        report.addSaeReportPriorTherapies(existingSaeReportPriorTherapy);
        createAssignment();
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must not copy the prior therapy", 1, assignment.getPriorTherapies().size());


    }

    public void testSyncrhonizeDiseaseHistory() {
        DiseaseHistory diseaseHistory = new DiseaseHistory();
        report.setDiseaseHistory(diseaseHistory);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertNull("must not copy the diseaseHistory", assignment.getDiseaseHistory());

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
        report.addSaeReportPreExistingCondition(new SAEReportPreExistingCondition());
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must copy the pre existing condition", 1, assignment.getPreExistingConditions().size());

        createAssignment();
        SAEReportPreExistingCondition reportPreExistingCondition = new SAEReportPreExistingCondition();
        reportPreExistingCondition.setId(1);
        report.addSaeReportPreExistingCondition(reportPreExistingCondition);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must not copy the pre existing condition", 1, assignment.getPreExistingConditions().size());


    }

    public void testSyncrhonizeConcomitantMedication() {
        report.addConcomitantMedication(new ConcomitantMedication());
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);
        assertEquals("must copy the concomitantMedication", 1, assignment.getConcomitantMedications().size());

        createAssignment();
        ConcomitantMedication concomitantMedication = new ConcomitantMedication();
        concomitantMedication.setId(2);
        report.addConcomitantMedication(concomitantMedication);
        assignment.synchronizeMedicalHistoryFromReportToAssignment(report);

        assertEquals("must not copy the concomitantMedication", 1, assignment.getConcomitantMedications().size());


    }
}
