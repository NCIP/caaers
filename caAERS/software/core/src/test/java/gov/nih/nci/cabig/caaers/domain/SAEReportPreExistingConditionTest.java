package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class SAEReportPreExistingConditionTest extends AbstractTestCase {


    private StudyParticipantPreExistingCondition studyParticipantPreExistingCondition;
    private PreExistingCondition preExistingCondition;
    private String other;
    SAEReportPreExistingCondition reportPreExistingCondition;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        studyParticipantPreExistingCondition = new StudyParticipantPreExistingCondition();
        studyParticipantPreExistingCondition.setId(1);
        studyParticipantPreExistingCondition.setGridId("grid id");
        studyParticipantPreExistingCondition.setVersion(2);
        preExistingCondition = new PreExistingCondition();
        studyParticipantPreExistingCondition.setPreExistingCondition(preExistingCondition);
        other = "other";
        studyParticipantPreExistingCondition.setOther(other);
        studyParticipantPreExistingCondition.setAssignment(new StudyParticipantAssignment());


        reportPreExistingCondition = new SAEReportPreExistingCondition();
        reportPreExistingCondition.setId(1);
        reportPreExistingCondition.setGridId("grid id");
        reportPreExistingCondition.setVersion(2);
        reportPreExistingCondition.setPreExistingCondition(preExistingCondition);
        reportPreExistingCondition.setOther(other);
        reportPreExistingCondition.setReport(new ExpeditedAdverseEventReport());

    }

    public void testCopyForBasicProperties() {

        SAEReportPreExistingCondition saeReportPreExistingCondition = reportPreExistingCondition.copy();

        assertNotNull(saeReportPreExistingCondition);

        assertNull("must not copy id ", saeReportPreExistingCondition.getId());
        assertNull("must not copy grid id ", saeReportPreExistingCondition.getGridId());
        assertNull("must not copy version no ", saeReportPreExistingCondition.getVersion());
        assertNull("must not copy report ", saeReportPreExistingCondition.getReport());


        assertEquals(preExistingCondition, saeReportPreExistingCondition.getPreExistingCondition());

        assertSame(preExistingCondition, saeReportPreExistingCondition.getPreExistingCondition());
        assertEquals(reportPreExistingCondition.getName(), saeReportPreExistingCondition.getName());
        assertEquals(other, saeReportPreExistingCondition.getOther());


    }

    public void testCreateSAEReportPreExistingConditionForBasicProperties() {

        SAEReportPreExistingCondition saeReportPreExistingCondition = SAEReportPreExistingCondition.
                createSAEReportPreExistingCondition(studyParticipantPreExistingCondition);

        assertNotNull(saeReportPreExistingCondition);

        assertNull("must not copy id ", saeReportPreExistingCondition.getId());
        assertNull("must not copy grid id ", saeReportPreExistingCondition.getGridId());
        assertNull("must not copy version no ", saeReportPreExistingCondition.getVersion());
        assertNull("must not copy report ", saeReportPreExistingCondition.getReport());


        assertEquals(preExistingCondition, saeReportPreExistingCondition.getPreExistingCondition());

        assertSame(preExistingCondition, saeReportPreExistingCondition.getPreExistingCondition());
        assertEquals(studyParticipantPreExistingCondition.getName(), saeReportPreExistingCondition.getName());
        assertEquals(other, saeReportPreExistingCondition.getOther());


    }

}