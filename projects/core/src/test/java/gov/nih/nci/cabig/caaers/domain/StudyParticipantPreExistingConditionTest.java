package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class StudyParticipantPreExistingConditionTest extends AbstractTestCase {


    private SAEReportPreExistingCondition saeReportPreExistingCondition;
    private PreExistingCondition preExistingCondition;
    private String other;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        saeReportPreExistingCondition = new SAEReportPreExistingCondition();
        saeReportPreExistingCondition.setId(1);
        saeReportPreExistingCondition.setGridId("grid id");
        saeReportPreExistingCondition.setVersion(2);
        preExistingCondition = new PreExistingCondition();
        saeReportPreExistingCondition.setPreExistingCondition(preExistingCondition);
        other = "other";
        saeReportPreExistingCondition.setOther(other);
        saeReportPreExistingCondition.setReport(new ExpeditedAdverseEventReport());

    }

    public void testCreateAssignmentPreExistingConditionForBasicProperties() {

        StudyParticipantPreExistingCondition studyParticipantPreExistingCondition = StudyParticipantPreExistingCondition.
                createAssignmentPreExistingCondition(saeReportPreExistingCondition);

        assertNotNull(studyParticipantPreExistingCondition);

        assertNull("must not copy id ", studyParticipantPreExistingCondition.getId());
        assertNull("must not copy grid id ", studyParticipantPreExistingCondition.getGridId());
        assertNull("must not copy version no ", studyParticipantPreExistingCondition.getVersion());
        assertNull("must not copy assignment ", studyParticipantPreExistingCondition.getAssignment());


        assertEquals(saeReportPreExistingCondition.getPreExistingCondition(), studyParticipantPreExistingCondition.getPreExistingCondition());

        assertSame("must refer same pre existing condition", preExistingCondition, studyParticipantPreExistingCondition.getPreExistingCondition());
        assertEquals(saeReportPreExistingCondition.getName(), studyParticipantPreExistingCondition.getName());
        assertEquals(other, studyParticipantPreExistingCondition.getOther());


    }


}