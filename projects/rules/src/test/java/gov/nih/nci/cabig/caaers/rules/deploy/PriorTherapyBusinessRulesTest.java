package gov.nih.nci.cabig.caaers.rules.deploy;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.Date;

public class PriorTherapyBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {

    @Override
    public String getBindUri() {
        return "gov.nih.nci.cabig.caaers.rules.reporting_prior_therapies_section";
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_prior_therapy.xml";
    }

    /**
     * RuleName : PTY_BR1_CHK Logic : "Comments (prior therapy) must be provided if 'Prior therapy'
     * is 'No Prior Therapy'" Error Code : PTY_BR1_ERR Error Message : COMMENTS must be provided
     * (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
     */
    public void testCorrectPriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When other comments is not provided for a non 'Prior Therapy NOS'");
    }

    /**
     * RuleName : PTY_BR1_CHK Logic : "Comments (prior therapy) must be provided if 'Prior therapy'
     * is 'No Prior Therapy'" Error Code : PTY_BR1_ERR Error Message : COMMENTS must be provided
     * (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
     */
    public void testNoPriorTherapy_With_OtherComments() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPriorTherapies().get(0).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(0).setOther("Other");
        aeReport.getSaeReportPriorTherapies().get(0).setStartDate(new DateValue(org.apache.commons.lang.time.DateUtils.addDays(new Date(), -1)));
        aeReport.getSaeReportPriorTherapies().get(0).setEndDate(new DateValue(new Date()));
        aeReport.getSaeReportPriorTherapies().get(1).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(1).setOther("Other1");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When other comments is provided for a non 'Prior Therapy NOS'");
    }

    /**
     * RuleName : PTY_BR1_CHK Logic : "Comments (prior therapy) must be provided if 'Prior therapy'
     * is 'No Prior Therapy'" Error Code : PTY_BR1_ERR Error Message : COMMENTS must be provided
     * (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
     */
    public void testNoPriorTherapy_Without_OtherComments() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPriorTherapies().get(0).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(0).setStartDate(new DateValue(org.apache.commons.lang.time.DateUtils.addDays(new Date(), -1)));
        aeReport.getSaeReportPriorTherapies().get(0).setEndDate(new DateValue(new Date()));
        System.out.println(aeReport.getSaeReportPriorTherapies().get(0).getName());
        aeReport.getSaeReportPriorTherapies().get(0).setOther(null);
        aeReport.getSaeReportPriorTherapies().get(1).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(1).setOther(null);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR1_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Correct replacement variable value", 2, errors.getErrorAt(1).getReplacementVariables()[0]);
    }

    /**
     * RuleName : PTY_BR1_CHK Logic : "Comments (prior therapy) must be provided if 'Prior therapy'
     * is 'No Prior Therapy'" Error Code : PTY_BR1_ERR Error Message : COMMENTS must be provided
     * (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
     */
    public void testOneOutOfTwoPriorTherapy_IsWithout_OtherComments() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPriorTherapies().get(0).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(0).setStartDate(new DateValue(org.apache.commons.lang.time.DateUtils.addDays(new Date(), -1)));
        aeReport.getSaeReportPriorTherapies().get(0).setEndDate(new DateValue(new Date()));
        aeReport.getSaeReportPriorTherapies().get(0).setOther("Other");
        aeReport.getSaeReportPriorTherapies().get(1).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(1).setOther(null);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR1_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Correct replacement variable value", 2, errors.getErrorAt(0).getReplacementVariables()[0]);

    }

    /**
     * RuleName : PTY_UK_CHK Logic : Prior Therapy must be unique Error Code : PTY_UK_ERR Error
     * Message : PRIOR_THERAPY must be unique
     */
    public void testUniquePriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When prior therapy is unique");

    }

    /**
     * RuleName : PTY_UK_CHK Logic : Prior Therapy must be unique Error Code : PTY_UK_ERR Error
     * Message : PRIOR_THERAPY must be unique
     */
    public void testTwoOutOfThreeSamePriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.addSaeReportPriorTherapies(aeReport.getSaeReportPriorTherapies().get(1));
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_UK_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Replacement variable incorrect", 3, errors.getErrorAt(0).getReplacementVariables()[0]);
    }

    /**
     * RuleName : PTY_BR4A_CHK Logic : ?Prior Therapy Agents? must be provided if "Prior_Therapy" is
     * ?Bone Marrow Transplant? ?Chemotherapy (NOS)? ?Chemotherapy multiple agents systemic?
     * ?Chemotherapy single agent systemic? ?Immunotherapy? ?Hormonal Therapy? Error Code :
     * PTY_BR4A_ERR Error Message : CHEMO_AGENTS must be provided for the provided PRIOR_THERAPY
     * value.
     */

    public void testBMTPriorTherapy_With_PriorTherapyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3);
            PriorTherapyAgent pta = new PriorTherapyAgent();
            ChemoAgent ca = new ChemoAgent();
            ca.setId(2 + i);
            ca.setName("chemoagent " + i++);
            pta.setChemoAgent(ca);
            aet.getPriorTherapyAgents().add(pta);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors,"when bone marrow transplant has priortherapy agents with chemo agents");
    }

    /**
     * RuleName : PTY_BR4A_CHK Logic : ?Prior Therapy Agents? must be provided if "Prior_Therapy" is
     * ?Bone Marrow Transplant? ?Chemotherapy (NOS)? ?Chemotherapy multiple agents systemic?
     * ?Chemotherapy single agent systemic? ?Immunotherapy? ?Hormonal Therapy? Error Code :
     * PTY_BR4A_ERR Error Message : CHEMO_AGENTS must be provided for the provided PRIOR_THERAPY
     * value.
     */

/*
    public void testBMTPriorTherapy_Without_PriorTherapyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR4A_ERR");
        assertSameErrorCount(errors, 2);

    }
*/

    /**
     * RuleName : PTY_BR4B_CHK Logic : ?Prior Therapy Agents? must not be provided if
     * "Prior_Therapy" is not ?Bone Marrow Transplant? ?Chemotherapy (NOS)? ?Chemotherapy multiple
     * agents systemic? ?Chemotherapy single agent systemic? ?Immunotherapy? ?Hormonal Therapy?
     * Error Code : PTY_BR4B_ERR Error Message : CHEMO_AGENTS must be provided for the provided
     * PRIOR_THERAPY value.
     */

    public void testXYZPriorTherapy_With_PriorTherapyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(23);
            PriorTherapyAgent pta = new PriorTherapyAgent();
            ChemoAgent ca = new ChemoAgent();
            ca.setId(2 + i);
            ca.setName("chemoagent " + i++);
            pta.setChemoAgent(ca);
            aet.getPriorTherapyAgents().add(pta);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR4B_ERR");
        assertSameErrorCount(errors, 2);
    }

    /**
     * RuleName : PTY_BR3_CHK Logic : ?Therapy End Date? must not be provided if ?Therapy Start
     * Date? is not provided Error Code : PTY_BR3_ERR Error Message : THERAPY_END_DATE must be not
     * be provided if THERAPY_START_DATE is not provided.
     * <p/>
     * <p/>
     * RuleName : PTY_BR2_CHK Logic : 'Therapy End Date' must not be later than 'Therapy Start Date'
     * Error Code : PTY_BR2_ERR Error Message : THERAPY_END_DATE must be later than or equal
     * THERAPY_START_DATE
     */
    public void testPriorTherapyNoStartDate_NoEndDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "No errors when no startdate and enddate");
    }

    /**
     * RuleName : PTY_BR3_CHK Logic : ?Therapy End Date? must not be provided if ?Therapy Start
     * Date? is not provided Error Code : PTY_BR3_ERR Error Message : THERAPY_END_DATE must be not
     * be provided if THERAPY_START_DATE is not provided.
     *
     * @throws Exception
     */
    public void testPriorTherapyNoStartDate_ButEndDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
            aet.setEndDate(new DateValue(new Date()));
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR3_ERR");
        assertSameErrorCount(errors, 2);
    }

    /**
     * RuleName : PTY_BR2_CHK Logic : 'Therapy End Date' must not be later than 'Therapy Start Date'
     * Error Code : PTY_BR2_ERR Error Message : THERAPY_END_DATE must be later than or equal
     * THERAPY_START_DATE
     */

    public void testPriorTherapyStartOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
            aet.setStartDate(new DateValue(new Date()));
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "No errors when only startdate ");
    }

    /**
     * RuleName : PTY_BR2_CHK Logic : 'Therapy End Date' must not be later than 'Therapy Start Date'
     * Error Code : PTY_BR2_ERR Error Message : THERAPY_END_DATE must be later than or equal
     * THERAPY_START_DATE
     */

    public void testPriorTherapyStartOnlyAndEndDateIsNull() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
            aet.setStartDate(new DateValue(new Date()));
            aet.setEndDate(null);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "No errors when only startdate ");
    }

    /**
     * RuleName : PTY_BR2_CHK Logic : 'Therapy End Date' must not be later than 'Therapy Start Date'
     * Error Code : PTY_BR2_ERR Error Message : THERAPY_END_DATE must be later than or equal
     * THERAPY_START_DATE
     */

    public void testPriorTherapyStart_LT_EndDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
            aet.setStartDate(new DateValue(DateUtils.createDate(2007, 11, 9)));
            aet.setEndDate(new DateValue(DateUtils.createDate(2007, 11, 11)));
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "No errors when only startdate is less than end date ");
    }

    /**
     * RuleName : PTY_BR2_CHK Logic : 'Therapy End Date' must not be later than 'Therapy Start Date'
     * Error Code : PTY_BR2_ERR Error Message : THERAPY_END_DATE must be later than or equal
     * THERAPY_START_DATE
     */
    public void testPriorTherapyStartDate_GT_EndDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
            aet.setStartDate(new DateValue(DateUtils.createDate(2007, 11, 19)));
            aet.setEndDate(new DateValue(DateUtils.createDate(2007, 11, 11)));
        }
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 2);
        assertCorrectErrorCode(errors, "PTY_BR2_ERR");

    }

    /**
     * RuleName : PTY_BR2_CHK Logic : 'Therapy End Date' must not be later than 'Therapy Start Date'
     * Error Code : PTY_BR2_ERR Error Message : THERAPY_END_DATE must be later than or equal
     * THERAPY_START_DATE
     */
    public void testOneOutOfTwoPriorTherapyStartDate_GT_EndDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(83);
            aet.setStartDate(new DateValue(DateUtils.createDate(2007, 11, 9)));
            if (i < 1) {
                aet.setEndDate(new DateValue(DateUtils.createDate(2007, 11, 11)));
            } else {
                aet.setEndDate(new DateValue(DateUtils.createDate(2007, 10, 1)));
            }

            i++;
        }
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 1);
        assertCorrectErrorCode(errors, "PTY_BR2_ERR");
        assertEquals("Incorrect replacement variable", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);

    }

    /**
     * RuleName : PTY_UK_CHK Logic : Prior Therapy must be unique Error Code : PTY_UK_ERR Error
     * Message : Two identical prior therapies cannot share the same starting month and year
     */
    public void testDuplicatePriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPriorTherapies().get(0).getPriorTherapy().setText("ll");
        aeReport.getSaeReportPriorTherapies().get(0).getStartDate().setYear(2009);
        aeReport.getSaeReportPriorTherapies().get(0).getStartDate().setMonth(01);

        aeReport.getSaeReportPriorTherapies().get(1).getPriorTherapy().setText("ll");
        aeReport.getSaeReportPriorTherapies().get(1).getStartDate().setYear(2009);
        aeReport.getSaeReportPriorTherapies().get(1).getStartDate().setMonth(01);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_UK_ERR");
        System.out.println("errors=" + errors.getErrors().size());
    }

    /**
     * RuleName : PTA_UK_CHK Logic : Agents within a single prior therapy must be unique
     * Message : Agents within a single prior therapy must be unique
     * Input Data:  2 Prior Therapies with the same Agent
     * Output Data:  0 error messages expected
     */
    public void testTheSameAgentForDifferentPriorTherapies() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;

        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3 + i*10);

            PriorTherapyAgent ptaOne = new PriorTherapyAgent();

            ChemoAgent caOne = new ChemoAgent();
            caOne.setId(200);
            caOne.setName("Chemoagent");
            ptaOne.setChemoAgent(caOne);

            aet.getPriorTherapyAgents().add(ptaOne);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 0);
    }

    /**
     * RuleName : PTA_UK_CHK Logic : Prior Therapy Agents must be unique Error Code : PTA_UK_ERR
     * Error Message : CHEMO_AGENT_NAME must be unique
     */
    public void testUniqueChemoAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3);
            PriorTherapyAgent pta = new PriorTherapyAgent();
            ChemoAgent ca = new ChemoAgent();
            ca.setId(2 + i);
            ca.setName("chemoagent " + i++);
            pta.setChemoAgent(ca);
            aet.getPriorTherapyAgents().add(pta);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when all has priortherapy agents with unique chemo agents");

    }

    /**
     * RuleName : PTA_UK_CHK Logic : Prior Therapy Agents must be unique Error Code : PTA_UK_ERR
     * Error Message : CHEMO_AGENT_NAME must be unique
     * Input Data:  2 Prior Therapies with two identical Agents each
     * Output Data:  2 error messages expected complaining about duplicate agents within the same PT
     */
    public void testDuplicateChemoAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3 + 11*i);

            PriorTherapyAgent pta1 = new PriorTherapyAgent();
            PriorTherapyAgent pta2 = new PriorTherapyAgent();

            ChemoAgent ca1 = new ChemoAgent();
            ca1.setId(2 + i);
            ca1.setName("Chemoagent");
            pta1.setChemoAgent(ca1);

            ChemoAgent ca2 = new ChemoAgent();
            ca2.setId(100 + i);
            ca2.setName("Chemoagent");
            pta2.setChemoAgent(ca2);

            aet.getPriorTherapyAgents().add(pta1);
            aet.getPriorTherapyAgents().add(pta2);
        }
        
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 2);
        assertCorrectErrorCode(errors, "PTA_UK_ERR");
    }

}
