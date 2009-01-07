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
     * RuleName : SEC_BR5B_CHK Rule : Protocol agents must be not be provided if Course Information
     * has not been provided. Error Code : SEC_BR5A_ERR Error Message : PROTOCOL_AGENTS must not be
     * provided if COURSE_INFOMATION is not provided.
     */
    public void testNoAgent_And_NoCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().setCourseAgentsInternal(new ArrayList<CourseAgent>());

        aeReport.getTreatmentInformation().getAdverseEventCourse().setDate(null);
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(null);
        aeReport.getTreatmentInformation().setFirstCourseDate(null);
        aeReport.getTreatmentInformation().setTotalCourses(null);

        ValidationErrors errors = fireRules(aeReport);

        assertNoErrors(errors, "When no courseAgent and Course Information is present");

    }

    /**
     * RuleName : SEC_BR5B_CHK Rule : Protocol agents must be not be provided if Course Information
     * has not been provided. Error Code : SEC_BR5A_ERR Error Message : PROTOCOL_AGENTS must not be
     * provided if COURSE_INFOMATION is not provided.
     */
    public void testAgents_And_Course() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is Agents and Course Information available");
    }


    /**
     * RuleName : SEC_BR5B_CHK Rule : Protocol agents must be not be provided if Course Information
     * has not been provided. Error Code : SEC_BR5B_ERR Error Message : PROTOCOL_AGENTS must not be
     * provided if COURSE_INFOMATION is not provided.
     */
    public void testAgent_WithNoCourse() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getAdverseEventCourse().setDate(null);
        aeReport.getTreatmentInformation().getAdverseEventCourse().setNumber(null);
        aeReport.getTreatmentInformation().setFirstCourseDate(null);
        aeReport.getTreatmentInformation().setTotalCourses(null);

        ValidationErrors errors = fireRules(aeReport);

        assertSameErrorCount(errors, 1,
                "When Agent information is provided without Course information");
        assertCorrectErrorCode(errors, "SEC_BR5B_ERR");

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
    public void testModifiedDose_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            d.setUnits("KK");
         //   ca.setModifiedDose(d);
        }
        ValidationErrors errors = fireRules(aeReport);

        assertTrue("There should be errors when there is only Modified Dose, with out Admin delay",
                errors.getErrorCount() > 0);
        assertEquals("There should be 2 errors", 2, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR1A_ERR", errors.getErrorAt(0).getCode());

    }

    /**
     * RuleName : PAG_BR1A_CHK Logic : 'Duration of Delay' must be provided when 'Dose' is 'Delayed
     * Translated Logic : 'Administration delay' must be provided if 'Dose modified' is checked
     * Error Code PAG_BR1A_ERR Error Message : DELAY must be provided if the AGENT_DELAYED is "Yes"
     */
    public void testModifiedDose_Alone_For_OneOutOfTwo() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            if (i == 0) ca.setAdministrationDelay(new BigDecimal(5));
            i++;
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            d.setUnits("KK");
          //  ca.setModifiedDose(d);
        }
        ValidationErrors errors = fireRules(aeReport);

        assertTrue("There should be errors when there is only Modified Dose, with out Admin delay",
                errors.getErrorCount() > 0);
        assertEquals("There should be 1 error", 1, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR1A_ERR", errors.getErrorAt(0).getCode());
        assertEquals("Error replacement value should be 1", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);

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

    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testNoLastAdministeredDate_NonInvestigationalStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.setLastAdministeredDate(null);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "There should not be any error, when NON ind agents dont have last administred date",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testLastAdministeredDate_InvestigationalStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.setLastAdministeredDate(new Date());

            StudyAgent sa1 = new StudyAgent();
            sa1.setId(6 + i++);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when ind agents have last administred date",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testNoLastAdministeredDate_InvestigationalStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {

            StudyAgent sa1 = new StudyAgent();
            sa1.setId(5 + i++);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "Errors should be there when LastAdministeredDate is not available for IND agents",
                2, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR3_ERR", errors.getErrorAt(0).getCode());
        assertEquals("Error replacement variable should be correct", 1, errors.getErrorAt(0)
                .getReplacementVariables()[0]);
        assertEquals("Error replacement variable should be correct", 2, errors.getErrorAt(1)
                .getReplacementVariables()[0]);
    }

    /**
     * RuleName : PAG_BR3_CHK Rule : 'Date Last Administered' must be provided for an
     * investigational agent." Error Code : PAG_BR3_ERR Error Message : LAST_ADMINISTERED_DATE must
     * be entered for an investigational agent.
     */
    public void testNoLastAdministeredDate_ForOneOutOfTwo_InvestigationalStudyAgents()
            throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            if (i == 0) ca.setLastAdministeredDate(new Date());
            i++;
            StudyAgent sa1 = new StudyAgent();
            sa1.setId(5 + i);
            StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
            InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
            saIND1.setInvestigationalNewDrug(ind1);
            sa1.addStudyAgentINDAssociation(saIND1);

            ca.setStudyAgent(sa1);
        }

        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "Errors should be there when LastAdministeredDate is not available for IND agents",
                1, errors.getErrorCount());
        assertEquals("Error code should be same", "PAG_BR3_ERR", errors.getErrorAt(0).getCode());
        assertEquals("Error replacement variable should be correct", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);

    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testUniqueStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When agents are unique");
    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testDuplicateStudyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testTwoOutOfThreeStudyAgentsSame() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }

        // add one more study agent.
        CourseAgent ca1 = new CourseAgent();
        Dose d1 = new Dose();
        d1.setAmount(new BigDecimal(5));
        d1.setUnits("abc1");

        StudyAgent sa1 = new StudyAgent();
        Agent a = new Agent();
        a.setName("xyz agent");
        sa1.setAgent(a);
        sa1.setId(44);
        ca1.setDose(d1);
        ca1.setStudyAgent(sa1);
        aeReport.getTreatmentInformation().addCourseAgent(ca1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : PAG_UK_CHK Rule : Protocol Agents must be unique" Error Code : PAG_UK_ERR Error
     * Message : PROTOCOL_AGENT must be unique
     */
    public void testThreeOutOfThreeStudyAgentsSame() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            ca.getStudyAgent().setId(4);
            Agent a = new Agent();
            a.setName("xyz");
            ca.getStudyAgent().setAgent(a);
            ca.getStudyAgent().setOtherAgent(null);
        }

        // add one more study agent.
        CourseAgent ca1 = new CourseAgent();
        Dose d1 = new Dose();
        d1.setAmount(new BigDecimal(5));
        d1.setUnits("abc1");
        ca1.setDose(d1);

        StudyAgent sa1 = new StudyAgent();
        Agent a = new Agent();
        a.setName("xyz");
        sa1.setAgent(a);
        sa1.setId(4);

        ca1.setStudyAgent(sa1);
        aeReport.getTreatmentInformation().addCourseAgent(ca1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_UK_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replacements (index) should be correct", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);
        assertEquals("Replacements (agentName) should be correct", "xyz", errors.getErrorAt(0)
                .getReplacementVariables()[1]);
        assertEquals("Replacements (index) should be correct", 3, errors.getErrorAt(1)
                .getReplacementVariables()[0]);
        assertEquals("Replacements (agentName) should be correct", "xyz", errors.getErrorAt(1)
                .getReplacementVariables()[1]);
    }

    /**
     * RuleName : PAG_BR2B_CHK Rule : "'Unit of measure' must be provided if 'Duration of delay' is
     * provided. Error Code : PAG_BR2B_ERR Error Message : DELAY_UOM must be provided if the DELAY
     * is provided
     *
     * @throws Exception
     */
    public void testCourseAgent_HavingNoDose() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount(null);
            d.setUnits(null);
            ca.setDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors);
    }

    /**
     * RuleName : PAG_BR2B_CHK Rule : "'Unit of measure' must be provided if 'Duration of delay' is
     * provided. Error Code : PAG_BR2B_ERR Error Message : DELAY_UOM must be provided if the DELAY
     * is provided
     *
     * @throws Exception
     */
    public void testCourseAgent_HavingTotalDoseAndUnits() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            d.setUnits("KK");
            ca.setDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors);
    }

    /**
     * RuleName : PAG_BR2B_CHK Rule : "'Unit of measure' must be provided if 'Duration of delay' is
     * provided. Error Code : PAG_BR2B_ERR Error Message : DELAY_UOM must be provided if the DELAY
     * is provided
     *
     * @throws Exception
     */
    public void testCourseAgent_NotHavingTotalDoseWithoutUOM() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (CourseAgent ca : aeReport.getTreatmentInformation().getCourseAgents()) {
            Dose d = new Dose();
            d.setAmount(new BigDecimal(9));
            ca.setDose(d);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PAG_BR2B_ERR");
        assertSameErrorCount(errors, 2, "When 2 of the course agents has no UOM for dose");
    }

}
