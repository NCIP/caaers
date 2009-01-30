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

public class AgentsBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {
    String bindUri = "gov.nih.nci.cabig.caaers.rules.agents_intervention_section";

    public String getBindUri() {
        return bindUri;
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_agents.xml";
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

        assertSameErrorCount(errors, 1, "When Agent information is provided without Course information");
        assertCorrectErrorCode(errors, "SEC_BR5B_ERR");
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