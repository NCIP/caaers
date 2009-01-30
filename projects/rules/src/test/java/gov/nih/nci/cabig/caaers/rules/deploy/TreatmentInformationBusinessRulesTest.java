package gov.nih.nci.cabig.caaers.rules.deploy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.Dose;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class TreatmentInformationBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {
    String bindUri = "gov.nih.nci.cabig.caaers.rules.reporting_treatment_info_section";

    public String getBindUri() {
        return bindUri;
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_treatment_information.xml";
    }


    /**
     * RuleName : TAI_BR2_CHK Rule : Either and only �Treatment Assignment Code� or �Describe
     * Treatment Assignment� must be provided Error Code : TAI_BR2_ERR Error Message : Either and
     * only TREATMENT_ASSIGNMENT_CODE or OTHER_TREATMENT_ASSIGNMENT must be provided.
     */
    public void testOnlyTACPresent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setTreatmentDescription(null);

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when there is only TAC", 0, errors
                .getErrorCount());

    }

    /**
     * RuleName : TAI_BR2_CHK Rule : Either and only �Treatment Assignment Code� or �Describe
     * Treatment Assignment� must be provided Error Code : TAI_BR2_ERR Error Message : Either and
     * only TREATMENT_ASSIGNMENT_CODE or OTHER_TREATMENT_ASSIGNMENT must be provided.
     */
    public void testOnlyDescriptionPresent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setTreatmentAssignment(null);

        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when there is only Other Description or dose level",
                0, errors.getErrorCount());

    }

    /**
     * RuleName : TAI_BR2_CHK Rule : Either and only �Treatment Assignment Code� or �Describe
     * Treatment Assignment� must be provided Error Code : TAI_BR2_ERR Error Message : Either and
     * only TREATMENT_ASSIGNMENT_CODE or OTHER_TREATMENT_ASSIGNMENT must be provided.
     */
    public void testBothTAC_AndOtherDescriptionPresent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setTreatmentDescription("some desc");
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should be  error, when there is both TAC and Other Description or dose level",
                1, errors.getErrorCount());
        assertEquals("Error code should be same", "TAI_BR2_ERR", errors.getErrorAt(0).getCode());
    }

    /**
     * RuleName : TAI_BR2_CHK Rule : Either and only �Treatment Assignment Code� or �Describe
     * Treatment Assignment� must be provided Error Code : TAI_BR2_ERR Error Message : Either and
     * only TREATMENT_ASSIGNMENT_CODE or OTHER_TREATMENT_ASSIGNMENT must be provided.
     */
    public void testNoTAC_AndNoOtherDescriptionPresent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setTreatmentAssignment(null);
        aeReport.getTreatmentInformation().setTreatmentAssignmentDescription(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when there is no TAC and no Other Description or dose level",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : TAI_BR2_CHK Rule : Either and only �Treatment Assignment Code� or �Describe
     * Treatment Assignment� must be provided Error Code : TAI_BR2_ERR Error Message : Either and
     * only TREATMENT_ASSIGNMENT_CODE or OTHER_TREATMENT_ASSIGNMENT must be provided.
     */
    public void testNullTreatmentInformation() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.setTreatmentInformation(null);
        aeReport.getTreatmentInformation().setTreatmentDescription("some desc");
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when there is no TreatmentInformation at all",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : CIN_BR1_CHK Logic : Course Number of AE must not be greater than Total number of
     * Courses. Error Code CIN_BR1_ERR Error Message : COURSE_NUMBER_OF_AE must not be greater than
     * TOTAL_NUMBER_OF_COURSES
     */
    public void testCourseNumberOfAE_LT_TotalNumberOfCourses() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when courseNo.OfAE is less than TotalNumberOfCourses",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : CIN_BR1_CHK Logic : Course Number of AE must not be greater than Total number of
     * Courses. Error Code CIN_BR1_ERR Error Message : COURSE_NUMBER_OF_AE must not be greater than
     * TOTAL_NUMBER_OF_COURSES
     */
    public void testCourseNumberOfAE_EQ_TotalNumberOfCourses() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setTotalCourses(5);
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(5);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when courseNo.OfAE is less than TotalNumberOfCourses",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : CIN_BR1_CHK Logic : Course Number of AE must not be greater than Total number of
     * Courses. Error Code CIN_BR1_ERR Error Message : COURSE_NUMBER_OF_AE must not be greater than
     * TOTAL_NUMBER_OF_COURSES
     */
    public void testCourseNumberOfAE_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setTotalCourses(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when  TotalNumberOfCourses are null. This is required if only courseNo of AE is mandatory field",
                0, errors.getErrorCount());

    }

    /**
     * RuleName : CIN_BR1_CHK Logic : Course Number of AE must not be greater than Total number of
     * Courses. Error Code CIN_BR1_ERR Error Message : COURSE_NUMBER_OF_AE must not be greater than
     * TOTAL_NUMBER_OF_COURSES
     */
    public void testTotalNumberOfCourses_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setAdverseEventCourse(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when courseNo.OfAE is null", 0, errors
                .getErrorCount());

    }

    /**
     * RuleName : CIN_BR1_CHK Logic : Course Number of AE must not be greater than Total number of
     * Courses. Error Code CIN_BR1_ERR Error Message : COURSE_NUMBER_OF_AE must not be greater than
     * TOTAL_NUMBER_OF_COURSES
     */
    public void testCourseNumberOfAE_GT_TotalNumberOfCourses() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(55);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should be error, when courseNo.OfAE is greater than TotalNumberOfCourses",
                1, errors.getErrorCount());
        assertEquals("Error code should be same", "CIN_BR1_ERR", errors.getErrorAt(0).getCode());
    }

    /**
     * RuleName : CIN_BR1_CHK Logic : Course Number of AE must not be greater than Total number of
     * Courses. Error Code CIN_BR1_ERR Error Message : COURSE_NUMBER_OF_AE must not be greater than
     * TOTAL_NUMBER_OF_COURSES
     */
    public void testNullCourseNumberOfAE_NullTotalNumberOfCourses() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(null);
        aeReport.getTreatmentInformation().setTotalCourses(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when courseNo.OfAE is null and totalNo.OfCourse is null",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     * <p/>
     * RuleName : PAG_BR1B_CHK Logic : " 'Duration of Delay' must not be provided if 'Dose' is not
     * 'Delayed'" Translated Logic : 'Administration delay' must not be provided if 'Dose modified'
     * is not checked Error Code PAG_BR1B_ERR Error Message : DELAY_UOM must not be provided if the
     * AGENT_DELAYED is not "Yes"
     */
    public void testNoCourseAgent() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setCourseAgentsInternal(new ArrayList<CourseAgent>());
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(null);
        aeReport.getTreatmentInformation().setTotalCourses(null);
        aeReport.getTreatmentInformation().setFirstCourseDate(null);
        aeReport.getTreatmentInformation().setAdverseEventCourse(null);

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error when there are no course agents", 0, errors
                .getErrorCount());
    }

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     * <p/>
     * RuleName : PAG_BR1B_CHK Logic : " 'Duration of Delay' must not be provided if 'Dose' is not
     * 'Delayed'" Translated Logic : 'Administration delay' must not be provided if 'Dose modified'
     * is not checked Error Code PAG_BR1B_ERR Error Message : DELAY_UOM must not be provided if the
     * AGENT_DELAYED is not "Yes"
     */
    public void testNoAdminDelay_And_NoModifiedDose() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error when there are course agents, having no admin delay and modified dose",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     * <p/>
     * RuleName : PAG_BR1B_CHK Logic : " 'Duration of Delay' must not be provided if 'Dose' is not
     * 'Delayed'" Translated Logic : 'Administration delay' must not be provided if 'Dose modified'
     * is not checked Error Code PAG_BR1B_ERR Error Message : DELAY_UOM must not be provided if the
     * AGENT_DELAYED is not "Yes"
     */
    public void testAdminDelay_WithModifiedDose() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.setAdministrationDelay(new BigDecimal(5));
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            d.setUnits("KK");
           // ca.setModifiedDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error when both Admin Delay and Modified dose", 0,
                errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     */
    /* these rules are disabled , so no need of this rule
    public void testModifiedDose_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            d.setUnits("KK");
            ca.setModifiedDose(d);
        }
        ValidationErrors errors = fireRules(aeReport);

        assertTrue("There should be errors when there is only Modified Dose, with out Admin delay",
                errors.getErrorCount() > 0);
        assertEquals("There should be 2 errors", 2, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR1A_ERR", errors.getErrorAt(0).getCode());

    }*/

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     */
    /* these rules are disabled , so no need of this rule
    public void testModifiedDose_Alone_For_OneOutOfTwo() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            if (i == 0) ca.setAdministrationDelay(new BigDecimal(5));
            i++;
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            d.setUnits("KK");
            ca.setModifiedDose(d);
        }
        ValidationErrors errors = fireRules(aeReport);

        assertTrue("There should be errors when there is only Modified Dose, with out Admin delay",
                errors.getErrorCount() > 0);
        assertEquals("There should be 1 error", 1, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR1A_ERR", errors.getErrorAt(0).getCode());
        assertEquals("Error replacement value should be 1", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);

    }*/

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     * <p/>
     * RuleName : PAG_BR1B_CHK Logic : " 'Duration of Delay' must not be provided if 'Dose' is not
     * 'Delayed'" Translated Logic : 'Administration delay' must not be provided if 'Dose modified'
     * is not checked Error Code PAG_BR1B_ERR Error Message : DELAY_UOM must not be provided if the
     * AGENT_DELAYED is not "Yes"
     */
    /* these rules are disabled , so no need of this rule
    public void testAdminDelay_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.setAdministrationDelay(new BigDecimal(5));
        }

        ValidationErrors errors = fireRules(aeReport);

        assertTrue("There should be errors when there is only Admin Delay, with out Modified Dose",
                errors.getErrorCount() > 0);
        assertEquals("There should be 2 errors", 2, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR1B_ERR", errors.getErrorAt(0).getCode());

    }*/

}
