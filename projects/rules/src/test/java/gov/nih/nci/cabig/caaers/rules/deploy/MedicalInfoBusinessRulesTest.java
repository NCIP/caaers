package gov.nih.nci.cabig.caaers.rules.deploy;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.rules.objectgraph.NullSafeFieldExtractor;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class MedicalInfoBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {

    String bindUri = "gov.nih.nci.cabig.caaers.rules.reporting_medical_info_section";

    public String getBindUri() {
        return bindUri;
    }


    @Override
    public String getRuleFile() {
        return "rules_reporting_medical_info.xml";
    }

    /**
     * RuleName : PTY_BR1_CHK Logic : "Comments (prior therapy) must be provided if 'Prior therapy'
     * is 'No Prior Therapy'" Error Code : PTY_BR1_ERR Error Message : COMMENTS must be provided
     * (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
     */
    public void testCorrectPriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        assertEquals("Correct replacement variable value", 2, errors.getErrorAt(1)
                .getReplacementVariables()[0]);
    }

    /**
     * RuleName : PTY_BR1_CHK Logic : "Comments (prior therapy) must be provided if 'Prior therapy'
     * is 'No Prior Therapy'" Error Code : PTY_BR1_ERR Error Message : COMMENTS must be provided
     * (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
     */
    public void testOneOutOfTwoPriorTherapy_IsWithout_OtherComments() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getSaeReportPriorTherapies().get(0).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(0).setStartDate(new DateValue(org.apache.commons.lang.time.DateUtils.addDays(new Date(), -1)));
        aeReport.getSaeReportPriorTherapies().get(0).setEndDate(new DateValue(new Date()));
        aeReport.getSaeReportPriorTherapies().get(0).setOther("Other");
        aeReport.getSaeReportPriorTherapies().get(1).getPriorTherapy().setText("Prior Therapy NOS");
        aeReport.getSaeReportPriorTherapies().get(1).setOther(null);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR1_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Correct replacement variable value", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);

    }

    /**
     * RuleName : PTY_UK_CHK Logic : Prior Therapy must be unique Error Code : PTY_UK_ERR Error
     * Message : PRIOR_THERAPY must be unique
     */
    public void testUniquePriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When prior therapy is unique");

    }

    /**
     * RuleName : PTY_UK_CHK Logic : Prior Therapy must be unique Error Code : PTY_UK_ERR Error
     * Message : PRIOR_THERAPY must be unique
     */
    public void testDuplicatePriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getSaeReportPriorTherapies().get(0).getPriorTherapy().setText("ll");
        aeReport.getSaeReportPriorTherapies().get(1).getPriorTherapy().setText("ll");
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_UK_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : PTY_UK_CHK Logic : Prior Therapy must be unique Error Code : PTY_UK_ERR Error
     * Message : PRIOR_THERAPY must be unique
     */
    public void testTwoOutOfThreeSamePriorTherapy() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.addSaeReportPriorTherapies(aeReport.getSaeReportPriorTherapies().get(1));
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_UK_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Replacement variable incorrect", 3, errors.getErrorAt(0)
                .getReplacementVariables()[0]);
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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        assertNoErrors(errors,
                "when bone marrow transplant has priortherapy agents with chemo agents");
    }

    /**
     * RuleName : PTY_BR4A_CHK Logic : ?Prior Therapy Agents? must be provided if "Prior_Therapy" is
     * ?Bone Marrow Transplant? ?Chemotherapy (NOS)? ?Chemotherapy multiple agents systemic?
     * ?Chemotherapy single agent systemic? ?Immunotherapy? ?Hormonal Therapy? Error Code :
     * PTY_BR4A_ERR Error Message : CHEMO_AGENTS must be provided for the provided PRIOR_THERAPY
     * value.
     */

    public void testBMTPriorTherapy_Without_PriorTherapyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "PTY_BR4A_ERR");
        assertSameErrorCount(errors, 2);

    }

    /**
     * RuleName : PTY_BR4B_CHK Logic : ?Prior Therapy Agents? must not be provided if
     * "Prior_Therapy" is not ?Bone Marrow Transplant? ?Chemotherapy (NOS)? ?Chemotherapy multiple
     * agents systemic? ?Chemotherapy single agent systemic? ?Immunotherapy? ?Hormonal Therapy?
     * Error Code : PTY_BR4B_ERR Error Message : CHEMO_AGENTS must be provided for the provided
     * PRIOR_THERAPY value.
     */

    public void testXYZPriorTherapy_With_PriorTherapyAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
        aeReport.getSaeReportPreExistingConditions().clear();

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
     * RuleName : PTA_UK_CHK Logic : Prior Therapy Agents must be unique Error Code : PTA_UK_ERR
     * Error Message : CHEMO_AGENT_NAME must be unique
     */
    public void testUniqueChemoAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

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
     */
    public void testDuplicateChemoAgents() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        int i = 0;
        for (SAEReportPriorTherapy aet : aeReport.getSaeReportPriorTherapies()) {
            aet.getPriorTherapy().setId(3);
            PriorTherapyAgent pta = new PriorTherapyAgent();
            ChemoAgent ca = new ChemoAgent();
            ca.setId(2 + i);
            ca.setName("chemoagent");
            pta.setChemoAgent(ca);
            aet.getPriorTherapyAgents().add(pta);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 1);
        assertCorrectErrorCode(errors, "PTA_UK_ERR");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testNoPreConditionAndNoOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when there are no pre-conditions and other");
    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testPreConditionOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().get(0).setOther(null);
        aeReport.getSaeReportPreExistingConditions().get(1).setOther(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when there are  other");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testOtherOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().get(0).setPreExistingCondition(null);
        aeReport.getSaeReportPreExistingConditions().get(1).setPreExistingCondition(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when there are  pre conditions only");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testBothPreConditionAndOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 2);
        assertCorrectErrorCode(errors, "PEC_BR1_ERR");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testOneOutOfTwoHasBothPreConditionAndOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().get(0).setPreExistingCondition(null);
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 1);
        assertCorrectErrorCode(errors, "PEC_BR1_ERR");

    }

    /**
     * RuleName : PAT_BR2A_CHK Rule : Disease Name Not Listed must not be null if Disease Name is
     * 'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'. Error Code : PAT_BR2A_ERR Error
     * Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or
     * "Hematopoietic malignancy, NOS".
     *
     * @throws Exception
     */
    public void testOtherDiseaseName_WhenDiseaseTermIsSolidTumorNOS() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No errors when OtherDiseaseName is present", 0, errors.getErrorCount());
    }

    /**
     * RuleName : PAT_BR2A_CHK Rule : Disease Name Not Listed must not be null if Disease Name is
     * 'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'. Error Code : PAT_BR2A_ERR Error
     * Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or
     * "Hematopoietic malignancy, NOS".
     *
     * @throws Exception
     */
    public void testOtherDiseaseName_WhenDiseaseTermIsHematopoieticmalignancyNOS() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        DiseaseTerm diseaseTerm = new DiseaseTerm();
        diseaseTerm.setTerm("Hematopoietic malignancy, NOS");
        aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No errors when OtherDiseaseName is present", 0, errors.getErrorCount());
    }


    /**
     * RuleName : PAT_BR3_CHK
     * Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
     * Error Code : PAT_BR3B_ERR
     * Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
     */
    public void testOtherPrimarySiteOfDisease_NullCodedPrimaryDisease() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(null);
        aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite("OtherSite");

        System.out.println("b0: " + NullSafeFieldExtractor.extractField(aeReport, "diseaseHistory.codedPrimaryDiseaseSite.name"));
        System.out.println("b1" + StringUtils.equalsIgnoreCase(NullSafeFieldExtractor.extractStringField(aeReport, "diseaseHistory.codedPrimaryDiseaseSite.name"), "Other, specify"));

        System.out.println("a1 :" + "null".equals(NullSafeFieldExtractor.extractField(aeReport, "diseaseHistory.codedPrimaryDiseaseSite")));
        System.out.println("a2 :" + StringUtils.equalsIgnoreCase(NullSafeFieldExtractor.extractStringField(aeReport, "diseaseHistory.codedPrimaryDiseaseSite.name"), "Other, specify"));

        System.out.println("Condition 2 :" + NullSafeFieldExtractor.extractField(aeReport, "diseaseHistory.otherPrimaryDiseaseSite") != null);

        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No Errors when OtherPrimarySiteOfDisease is only present", 0, errors.getErrorCount());
    }

    /**
     * RuleName : PAT_BR3_CHK
     * Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
     * Error Code : PAT_BR3B_ERR
     * Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
     */
    public void testOtherPrimarySiteOfDisease_OtherCodedPrimaryDiseaseSite() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite site = new AnatomicSite();
        site.setName("Other, specify");
        aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(site);
        aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite("OtherSite");
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No Errors when OtherPrimarySiteOfDisease is only present", 0, errors.getErrorCount());
    }

    /**
     * RuleName : PAT_BR3_CHK
     * Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
     * Error Code : PAT_BR3B_ERR
     * Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
     */
    public void testPrimarySiteOfDiseaseOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite(null);
        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("orignal disease site");
        aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(diseaseSite);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No Errors when CodedPrimaryDiseaseSite is only present", 0, errors.getErrorCount());

    }


    /**
     * RuleName : PAT_BR2A_CHK Rule : Disease Name Not Listed must not be null if Disease Name is
     * 'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'. Error Code : PAT_BR2A_ERR Error
     * Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or
     * "Hematopoietic malignancy, NOS".
     *
     * @throws Exception
     */
    public void testNoOtherDiseaseName_WhenDiseaseTermIsNotHematopoieticmalignancyNOS()
            throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getDiseaseHistory().setOtherPrimaryDisease(null);
        DiseaseTerm diseaseTerm = new DiseaseTerm();
        diseaseTerm.setTerm("xxxHematopoietic malignancy, NOS");
        aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                "No Errors when OtherDiseaseName is not present and diseaseTerm is not Hemtopoietc.....",
                0, errors.getErrorCount());
    }

    /**
     * RuleName : PAT_BR2B_CHK Rule : Disease Name Not Listed must not be provided where Disease
     * Name is not 'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'. Error Code : PAT_BR2B_ERR
     * Error Message : DISEASE_NAME_NOT_LISTED must not be provided if DISEASE_NAME is not " Solid
     * tumor, NOS" or " Hematopoietic malignancy, NOS".
     *
     * @throws Exception
     */
    public void testOtherDiseaseName_WhenDiseaseTermIsNotHematopoieticmalignancyNOS()
            throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getDiseaseHistory().setOtherPrimaryDisease("Some diesase");
        DiseaseTerm diseaseTerm = new DiseaseTerm();
        diseaseTerm.setTerm("abc NOS");
        aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("Errors when OtherDiseaseName is present and disease is not Hematopoietic...",
                1, errors.getErrorCount());
        assertEquals("Error code must be same", "PAT_BR2B_ERR", errors.getErrorAt(0).getCode());
    }

    /**
     * RuleName : PAT_BR3_CHK Rule : "'Other Primary Site of Disease' must not be provided if
     * 'Primary Site of Disease' is provided and vice-versa. Error Code : PAT_BR3B_ERR Error Message :
     * Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
     */
    public void testOtherPrimarySiteOfDiseaseOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite("OtherSite");
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No Errors when OtherPrimarySiteOfDisease is only present", 0, errors
                .getErrorCount());
    }

    /**
     * RuleName : PAT_BR3_CHK Rule : "'Other Primary Site of Disease' must not be provided if
     * 'Primary Site of Disease' is provided and vice-versa. Error Code : PAT_BR3B_ERR Error Message :
     * Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
     */
    public void testNoPrimaryOrOtherDiseaseSite() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite(null);
        aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No Errors when both other and coded disease site is not present", 0, errors
                .getErrorCount());
    }

    /**
     * RuleName : PAT_BR3_CHK Rule : "'Other Primary Site of Disease' must not be provided if
     * 'Primary Site of Disease' is provided and vice-versa. Error Code : PAT_BR3B_ERR Error Message :
     * Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
     */
    public void testOtherPrimarySiteOfDisease_AndCodedSiteOfDisease() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("orignal disease site");
        aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(diseaseSite);
        aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite("some site");
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("Errors when OtherDiseaseSite and CodedDiseaseSite is present ", 1, errors
                .getErrorCount());
        assertEquals("Error code must be same", "PAT_BR3B_ERR", errors.getErrorAt(0).getCode());

    }

    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testNoMetastaticDisease() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "No errors should be there when there are no metastatic diseases");
    }

    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testMetastaticDiseaseWithOnlySiteName() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("orignal disease site");
        MetastaticDiseaseSite site = new MetastaticDiseaseSite();
        site.setCodedSite(diseaseSite);
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors,
                "No errors should be there when there only site name in metastatic disease");
    }

    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testMetastaticDiseaseWithOnlyOtherSiteName() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        MetastaticDiseaseSite site = new MetastaticDiseaseSite();
        site.setOtherSite("test");
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors,
                "No errors should be there when there only other site name in metastatic disease");
    }

    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testMetastaticDiseaseWithBothDiseaseNameAndOtherSiteName() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("orignal disease site");
        MetastaticDiseaseSite site = new MetastaticDiseaseSite();
        site.setCodedSite(diseaseSite);
        site.setOtherSite("test");
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SMD_BR1_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testOneOutOfTwoHasBothMetastaticDiseaseAndOtherSite() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("orignal disease site");
        MetastaticDiseaseSite site = new MetastaticDiseaseSite();
        site.setCodedSite(diseaseSite);
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);

        diseaseSite = new AnatomicSite();
        diseaseSite.setName("orignal disease site");
        site = new MetastaticDiseaseSite();
        site.setCodedSite(diseaseSite);
        site.setOtherSite("test");
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SMD_BR1_ERR");
        assertSameErrorCount(errors, 1);
        assertSame("Replacement should be correct", 2, errors.getErrorAt(0)
                .getReplacementVariables()[0]);

    }

    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testMetastaticDiseaseWithOnlySiteName_Is_Other() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("Other, specify");
        MetastaticDiseaseSite site = new MetastaticDiseaseSite();
        site.setCodedSite(diseaseSite);
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors,
                "No errors should be there when there only site name in metastatic disease");
    }


    /**
     * RuleName : SMD_BR1_CHK Logic : 'Sites of Metastatic Disease' must not be provided if 'Other
     * Sites of Metastatic Disease' is provided and vice-versa. Error Code : SMD_BR1_ERR Error
     * Message : Either and only SITE_OF_METASTATIC_DISEASE or OTHER_SITE_OF_METASTATIC_DISEASE must
     * be provided.
     */
    public void testMetastaticDiseaseWithOnlySiteName_Is_OtherAndOtherSite() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();

        AnatomicSite diseaseSite = new AnatomicSite();
        diseaseSite.setName("Other, specify");
        MetastaticDiseaseSite site = new MetastaticDiseaseSite();

        site.setCodedSite(diseaseSite);
        site.setOtherSite("another");
        aeReport.getDiseaseHistory().addMetastaticDiseaseSite(site);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors,
                "No errors should be there when there only site name in metastatic disease");
    }
}
