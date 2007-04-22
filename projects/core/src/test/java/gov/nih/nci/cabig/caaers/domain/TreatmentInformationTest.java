package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class TreatmentInformationTest extends CaaersTestCase {
    private TreatmentInformation treatmentInformation = new TreatmentInformation();

    public void testInvestigationDrugUsedWhenUsed() throws Exception {
        treatmentInformation.addCourseAgent(createCourseAgent(true));
        treatmentInformation.addCourseAgent(createCourseAgent(false));
        treatmentInformation.addCourseAgent(createCourseAgent(null));

        assertTrue(treatmentInformation.isInvestigationalAgentAdministered());
    }

    public void testInvestigationalDrugUsedWhenNotUsed() throws Exception {
        treatmentInformation.addCourseAgent(createCourseAgent(false));
        treatmentInformation.addCourseAgent(createCourseAgent(false));
        treatmentInformation.addCourseAgent(createCourseAgent(null));

        assertFalse(treatmentInformation.isInvestigationalAgentAdministered());
    }

    public void testInvestigationalDrugUsedWhenIndicatorNull() throws Exception {
        treatmentInformation.addCourseAgent(createCourseAgent(null));

        assertFalse(treatmentInformation.isInvestigationalAgentAdministered());
    }

    public void testInvestigationalDrugUsedWhenNone() throws Exception {
        assertFalse(treatmentInformation.isInvestigationalAgentAdministered());
    }

    private CourseAgent createCourseAgent(Boolean newDrug) {
        CourseAgent ca = new CourseAgent();
        StudyAgent sa = new StudyAgent();
        sa.setInvestigationalNewDrugIndicator(newDrug);
        ca.setStudyAgent(sa);

        return ca;
    }
}
