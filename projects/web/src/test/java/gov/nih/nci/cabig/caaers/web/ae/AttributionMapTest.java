package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.assignParticipant;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.createOrganization;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.createParticipant;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.createStudy;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class AttributionMapTest extends AbstractTestCase {
    private static final CourseAgent COURSE_AGENT_0 = new CourseAgent();

    private static final ConcomitantMedication CON_MED_0 = new ConcomitantMedication();

    private static final ConcomitantMedication CON_MED_1 = new ConcomitantMedication();

    private static final OtherCause OTHER_CAUSE_0 = new OtherCause();

    private ExpeditedAdverseEventReport report;

    private AttributionMap map;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        report = new ExpeditedAdverseEventReport();
        AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        // This has changed to handle Many-To-One relationship between ReportingPeriod and ExpeditedReport
        // TODO: fix it when use case is ready.
        reportingPeriod.addAeReport(report);
        report.setReportingPeriod(reportingPeriod);
        report.setAssignment(assignParticipant(createParticipant("D", "C"), createStudy("DC"),
                        createOrganization("DC")));
        report.setTreatmentInformation(new TreatmentInformation());

        report.getConcomitantMedications().add(CON_MED_0);
        report.getConcomitantMedications().add(CON_MED_1);
        report.getTreatmentInformation().getCourseAgents().add(COURSE_AGENT_0);
        report.getOtherCauses().add(OTHER_CAUSE_0);

        getAe(0).getCourseAgentAttributions().add(new CourseAgentAttribution());
        getAe(0).getCourseAgentAttributions().get(0).setAttribution(Attribution.UNLIKELY);
        getAe(0).getConcomitantMedicationAttributions().add(new ConcomitantMedicationAttribution());
        getAe(0).getConcomitantMedicationAttributions().get(0).setAttribution(Attribution.POSSIBLE);
        getAe(0).getOtherCauseAttributions().add(new OtherCauseAttribution());
        getAe(0).getOtherCauseAttributions().get(0).setAttribution(Attribution.DEFINITE);

        map = new AttributionMap(report);
    }

    // just to shorten things up
    private AdverseEvent getAe(int aeIndex) {
        return report.getAdverseEvents().get(aeIndex);
    }

    public void testGetExistingStudyAgentAttribution() throws Exception {
        assertAttributionsMatch(getAe(0).getCourseAgentAttributions().get(0).getAttribution(),
                        ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY, 0, 0);
    }

    public void testGetExistingConMedAttribution() throws Exception {
        assertAttributionsMatch(getAe(0).getConcomitantMedicationAttributions().get(0)
                        .getAttribution(),
                        ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY,
                        0, 0);
    }

    public void testGetExistingOtherCauseAttribution() throws Exception {
        assertAttributionsMatch(getAe(0).getOtherCauseAttributions().get(0).getAttribution(),
                        ExpeditedAdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY, 0, 0);
    }

    public void testAddCourseAgentAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY).get(1).add(
                        Attribution.PROBABLE);
        assertAttributionsMatch(getAe(1).getCourseAgentAttributions().get(0).getAttribution(),
                        ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY, 1, 0);
    }

    public void testAddConMedAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(0)
                        .add(Attribution.PROBABLE);
        assertAttributionsMatch(getAe(0).getConcomitantMedicationAttributions().get(1)
                        .getAttribution(),
                        ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY,
                        0, 1);
    }

    public void testAddOtherCauseAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY).get(1).add(
                        Attribution.PROBABLE);
        assertAttributionsMatch(getAe(1).getOtherCauseAttributions().get(0).getAttribution(),
                        ExpeditedAdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY, 1, 0);
    }

    public void testSetExistingStudyAgentAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY).get(0).set(0,
                        Attribution.PROBABLE);
        assertEquals(Attribution.PROBABLE, getAe(0).getCourseAgentAttributions().get(0)
                        .getAttribution());
    }

    public void testSetExistingConMedAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(0)
                        .set(0, Attribution.DEFINITE);
        assertEquals(Attribution.DEFINITE, getAe(0).getConcomitantMedicationAttributions().get(0)
                        .getAttribution());
    }

    public void testSetExistingOtherCauseAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY).get(0).set(0,
                        Attribution.PROBABLE);
        assertEquals(Attribution.PROBABLE, getAe(0).getOtherCauseAttributions().get(0)
                        .getAttribution());
    }

    public void testSetNonExistentConMedAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(1)
                        .set(1, Attribution.DEFINITE);
        ConcomitantMedicationAttribution actualAeAttribution = getAe(1)
                        .getConcomitantMedicationAttributions().get(1);
        assertEquals("Wrong attribution", Attribution.DEFINITE, actualAeAttribution
                        .getAttribution());
        assertSame("AE not set", getAe(1), actualAeAttribution.getAdverseEvent());
        assertSame("Cause not set", CON_MED_1, actualAeAttribution.getCause());
    }

    public void testSetNonExistentCourseAgentAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY).get(1).set(0,
                        Attribution.UNRELATED);
        CourseAgentAttribution actualAeAttribution = getAe(1).getCourseAgentAttributions().get(0);
        assertEquals("Wrong attribution", Attribution.UNRELATED, actualAeAttribution
                        .getAttribution());
        assertSame("AE not set", getAe(1), actualAeAttribution.getAdverseEvent());
        assertSame("Cause not set", COURSE_AGENT_0, actualAeAttribution.getCause());
    }

    public void testSetNonExistentOtherCauseAttribution() throws Exception {
        map.get(ExpeditedAdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY).get(1).set(0,
                        Attribution.POSSIBLE);
        OtherCauseAttribution actualAeAttribution = getAe(1).getOtherCauseAttributions().get(0);
        assertEquals("Wrong attribution", Attribution.POSSIBLE, actualAeAttribution
                        .getAttribution());
        assertSame("AE not set", getAe(1), actualAeAttribution.getAdverseEvent());
        assertSame("Cause not set", OTHER_CAUSE_0, actualAeAttribution.getCause());
    }



    public void testErrorToGetForNonExistentCause() throws Exception {
        try {
            // report only has two concomitant meds
            map.get(ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(
                            1).get(2);
            fail("Exception not thrown");
        } catch (CaaersSystemException e) {
            assertEquals(
                            "Could not locate cause 2 for report using accessor ConcomitantMedicationAccessor"
                                            + ".  This may indicate a problem in generating the attribution form.",
                            e.getMessage());
        }
    }

    public void testErrorToSetForNonExistentCause() throws Exception {
        try {
            // report only has two concomitant meds
            map.get(ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(
                            1).set(2, Attribution.PROBABLE);
            fail("Exception not thrown");
        } catch (CaaersSystemException e) {
            assertEquals(
                            "Could not locate cause 2 for report using accessor ConcomitantMedicationAccessor"
                                            + ".  This may indicate a problem in generating the attribution form.",
                            e.getMessage());
        }
    }

    private void assertAttributionsMatch(Attribution expectedAttrib, String key, int aeIndex,
                    int causeIndex) {
        assertNotNull("Test setup failure -- no attribution to test against", expectedAttrib);
        assertEquals("Mismatch at [" + key + "][" + aeIndex + "][" + causeIndex + ']',
                        expectedAttrib, map.get(key).get(aeIndex).get(causeIndex));
    }
}
