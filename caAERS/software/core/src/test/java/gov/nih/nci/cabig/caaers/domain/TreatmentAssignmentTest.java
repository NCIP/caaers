package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class TreatmentAssignmentTest extends TestCase {
    TreatmentAssignment assignment;
    StudyAgent sa1 = null;
    StudyAgent sa2 = null;
    StudyAgent sa3 = null;

    Study study = null;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignment = Fixtures.createTreatmentAssignment();
        sa1 = Fixtures.createStudyAgent("sa1");
        sa2 = Fixtures.createStudyAgent("sa2");
        sa3 = Fixtures.createStudyAgent("sa3");
        
        study = Fixtures.createStudy("test");

        sa1.setStudy(study);
        sa2.setStudy(study);
        sa3.setStudy(study);
    }

    public void testRemoveInterventionFromTreatmentAssignment() throws Exception {

         assignment.addInterventionToTreatmentAssignment(sa1);
         assignment.addInterventionToTreatmentAssignment(sa2);
        
         assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());

         assignment.removeInterventionFromTreatmentAssignment(sa1);

        assertEquals(1, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
    }

    public void testAddInterventionToTreatmentAssignment() throws Exception {


        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);

        assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa1, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(1).getStudyIntervention());

    }

    public void testAddTreatmentAssignmentStudyIntervention() throws Exception {

        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);

        assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa1, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(1).getStudyIntervention());
    }

    public void testHasIntervention() throws Exception {


        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);

        assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa1, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(1).getStudyIntervention());

        assertFalse(assignment.hasIntervention(sa3) != null);
        assertTrue(assignment.hasIntervention(sa1) != null);
        assertTrue(assignment.hasIntervention(sa2) != null);
        assertSame(sa1, assignment.hasIntervention(sa1).getStudyIntervention());
    }

    public void testRegenerateAllInterventionIdList() throws Exception {

        sa1.setId(100);

        sa2.setId(200);

        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);
        assignment.regenerateAllInterventionIdList();
        assertTrue(assignment.getSelectedStudyAgentInterventionIds().contains(100));
        assertTrue(assignment.getSelectedStudyAgentInterventionIds().contains(200));
        assertFalse(assignment.getSelectedStudyAgentInterventionIds().contains(300));
        assertFalse(assignment.getSelectedOtherInteterventionIds().contains(200));
        assertFalse(assignment.getSelectedStudyDeviceInterventionIds().contains(200));
    }

}
