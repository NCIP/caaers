package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;

/**
 * @author Rhett Sutphin
 */
public class AttributionMapTest extends CaaersTestCase {
    private static final CourseAgent COURSE_AGENT_0 = new CourseAgent();
    private static final ConcomitantMedication CON_MED_0 = new ConcomitantMedication();
    private static final ConcomitantMedication CON_MED_1 = new ConcomitantMedication();

    private AdverseEventReport report;
    private AttributionMap map;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        report = new AdverseEventReport();
        report.setAssignment(assignParticipant(createParticipant("D", "C"), createStudy("DC"), createSite("DC")));
        report.setTreatmentInformation(new TreatmentInformation());

        report.getConcomitantMedications().add(CON_MED_0);
        report.getConcomitantMedications().add(CON_MED_1);
        report.getTreatmentInformation().getCourseAgents().add(COURSE_AGENT_0);

        getAe(0).getCourseAgentAttributions().add(new CourseAgentAttribution());
        getAe(0).getCourseAgentAttributions().get(0).setAttribution(Attribution.UNLIKELY);
        getAe(0).getConcomitantMedicationAttributions().add(new ConcomitantMedicationAttribution());
        getAe(0).getConcomitantMedicationAttributions().get(0).setAttribution(Attribution.POSSIBLE);

        map = new AttributionMap(report);
    }

    // just to shorten things up
    private AdverseEvent getAe(int aeIndex) {
        return report.getAdverseEvents().get(aeIndex);
    }

    public void testGetExistingStudyAgentAttribution() throws Exception {
        assertAttributionsMatch(
            getAe(0).getCourseAgentAttributions().get(0).getAttribution(),
            AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY, 0, 0
        );
    }

    public void testGetExistingConMedAttribution() throws Exception {
        assertAttributionsMatch(
            getAe(0).getConcomitantMedicationAttributions().get(0).getAttribution(),
            AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY, 0, 0
        );
    }

    public void testAddCourseAgentAttribution() throws Exception {
        map.get(AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY)
            .get(1).add(Attribution.PROBABLE);
        assertAttributionsMatch(
            getAe(1).getCourseAgentAttributions().get(0).getAttribution(),
            AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY, 1, 0
        );
    }

    public void testAddConMedAttribution() throws Exception {
        map.get(AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY)
            .get(0).add(Attribution.PROBABLE);
        assertAttributionsMatch(
            getAe(0).getConcomitantMedicationAttributions().get(1).getAttribution(),
            AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY, 0, 1
        );
    }

    public void testSetExistingStudyAgentAttribution() throws Exception {
        map.get(AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY)
            .get(0).set(0, Attribution.PROBABLE);
        assertEquals(Attribution.PROBABLE,
            getAe(0).getCourseAgentAttributions().get(0).getAttribution());
    }

    public void testSetExistingConMedAttribution() throws Exception {
        map.get(AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY)
            .get(0).set(0, Attribution.DEFINITE);
        assertEquals(Attribution.DEFINITE,
            getAe(0).getConcomitantMedicationAttributions().get(0).getAttribution());
    }

    public void testSetNonExistentConMedAttribution() throws Exception {
        map.get(AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY)
            .get(1).set(1, Attribution.DEFINITE);
        ConcomitantMedicationAttribution actualAeAttribution
            = getAe(1).getConcomitantMedicationAttributions().get(1);
        assertEquals("Wrong attribution", Attribution.DEFINITE, actualAeAttribution.getAttribution());
        assertSame("AE not set", getAe(1), actualAeAttribution.getAdverseEvent());
        assertSame("Cause not set", CON_MED_1, actualAeAttribution.getCause());
    }

    public void testSetNonExistentCourseAgentAttribution() throws Exception {
        map.get(AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY)
            .get(1).set(0, Attribution.UNRELATED);
        CourseAgentAttribution actualAeAttribution
            = getAe(1).getCourseAgentAttributions().get(0);
        assertEquals("Wrong attribution", Attribution.UNRELATED, actualAeAttribution.getAttribution());
        assertSame("AE not set", getAe(1), actualAeAttribution.getAdverseEvent());
        assertSame("Cause not set", COURSE_AGENT_0, actualAeAttribution.getCause());
    }

    public void testGetNonInitializedAttributionIsUnrelated() throws Exception {
        assertAttributionsMatch(
            Attribution.UNRELATED,
            AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY, 1, 0
        );
    }

    public void testErrorToGetForNonExistentCause() throws Exception {
        try {
            // report only has two concomitant meds
            map.get(AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY)
                .get(1).get(2);
            fail("Exception not thrown");
        } catch (CaaersSystemException e) {
            assertEquals("Could not locate cause 2 for report using accessor ConcomitantMedicationAccessor"
                + ".  This may indicate a problem in generating the attribution form.",
                e.getMessage());
        }
    }

    public void testErrorToSetForNonExistentCause() throws Exception {
        try {
            // report only has two concomitant meds
            map.get(AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY)
                .get(1).set(2, Attribution.PROBABLE);
            fail("Exception not thrown");
        } catch (CaaersSystemException e) {
            assertEquals("Could not locate cause 2 for report using accessor ConcomitantMedicationAccessor"
                + ".  This may indicate a problem in generating the attribution form.",
                e.getMessage());
        }
    }

    private void assertAttributionsMatch(Attribution expectedAttrib, String key, int aeIndex, int causeIndex) {
        assertNotNull("Test setup failure -- no attribution to test against", expectedAttrib);
        assertEquals("Mismatch at [" + key + "][" + aeIndex + "][" + causeIndex + ']',
            expectedAttrib, map.get(key).get(aeIndex).get(causeIndex));
    }
}
