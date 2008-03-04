package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
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
        if (newDrug != null && newDrug.booleanValue()) {
            sa.getStudyAgentINDAssociations().get(0);
        }
        // sa.setInvestigationalNewDrugIndicator(newDrug);
        ca.setStudyAgent(sa);

        return ca;
    }
}
