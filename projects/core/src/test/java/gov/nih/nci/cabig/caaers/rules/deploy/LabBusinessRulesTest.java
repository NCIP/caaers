package gov.nih.nci.cabig.caaers.rules.deploy;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.Date;

public class LabBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {

    @Override
    public String getBindUri() {
        return "gov.nih.nci.cabig.caaers.rules.reporting_labs_section";
    }

    @Override
    public String getRuleFile() {
        // TODO Auto-generated method stub
        return "rules_reporting_labs.xml";
    }

    /**
     * RuleName : LAB_BR1_CHK Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab' is
     * provided and vice versa Error Code : LAB_BR1_ERR Error Message : Either and only LAB_CATEGORY
     * and LAB_NAME or OTHER_LAB must be provided.
     * 
     * RuleName : LAB_BR3_CHK Logic :"“Baseline Date” must not be greater “Nadir/Worst Date”" Error
     * Code : LAB_BR3_ERR Error Message : BASELINE_DATE must not be greater WORST_DATE
     * 
     * RuleName : LAB_UK_CHK Logic :Lab Results must be unique Error Code : LAB_UK_ERR Error Message
     * :Lab Results must be unique
     * 
     */
    public void testNoLab() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getLabs().clear();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there are no labs");
    }

    /**
     * RuleName : LAB_BR1_CHK Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab' is
     * provided and vice versa Error Code : LAB_BR1_ERR Error Message : Either and only LAB_CATEGORY
     * and LAB_NAME or OTHER_LAB must be provided.
     * 
     * RuleName : LAB_UK_CHK Logic :Lab Results must be unique Error Code : LAB_UK_ERR Error Message
     * :Lab Results must be unique
     */
    public void testLabWithOnlyName() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (Lab l : aeReport.getLabs()) {
            LabValue lv = new LabValue();
            lv.setDate(DateUtils.createDate(2008, 1, 2));
            lv.setValue("v");

            l.setBaseline(lv);
            l.setNadir(lv);
            l.setRecovery(lv);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When only names are there for labs");
    }

    /**
     * RuleName : LAB_BR1_CHK Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab' is
     * provided and vice versa Error Code : LAB_BR1_ERR Error Message : Either and only LAB_CATEGORY
     * and LAB_NAME or OTHER_LAB must be provided.
     * 
     * RuleName : LAB_UK_CHK Logic :Lab Results must be unique Error Code : LAB_UK_ERR Error Message
     * :Lab Results must be unique
     */
    public void testLabWithOnlyOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getLabs().get(0).getLabTerm().setTerm(null);
        aeReport.getLabs().get(0).setOther("other1");
        aeReport.getLabs().get(1).getLabTerm().setTerm(null);
        aeReport.getLabs().get(1).setOther("other2");

        for (Lab l : aeReport.getLabs()) {
            LabValue lv = new LabValue();
            lv.setDate(DateUtils.createDate(2008, 1, 2));
            lv.setValue("v");

            l.setBaseline(lv);
            l.setNadir(lv);
            l.setRecovery(lv);
        }

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When only OtherName are there for labs");
    }

    /**
     * RuleName : LAB_BR1_CHK Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab' is
     * provided and vice versa Error Code : LAB_BR1_ERR Error Message : Either and only LAB_CATEGORY
     * and LAB_NAME or OTHER_LAB must be provided.
     */
    public void testLabWithNameAndOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getLabs().get(0).setOther("other1");
        aeReport.getLabs().get(1).setOther("other2");

        for (Lab l : aeReport.getLabs()) {
            LabValue lv = new LabValue();
            lv.setDate(DateUtils.createDate(2008, 1, 2));
            lv.setValue("v");

            l.setBaseline(lv);
            l.setNadir(lv);
            l.setRecovery(lv);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR1_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replacement values incorrect", 0, errors.getErrorAt(0).getReplacementVariables()[0]);
        assertEquals("Replacement values incorrect", 1, errors.getErrorAt(1).getReplacementVariables()[0]);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object i = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.labs[" + i + "].lab-category", "aeReport.labs[" + i + "].labName", "aeReport.labs[" + i + "].other");

    }

    /**
     * RuleName : LAB_BR1_CHK Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab' is
     * provided and vice versa Error Code : LAB_BR1_ERR Error Message : Either and only LAB_CATEGORY
     * and LAB_NAME or OTHER_LAB must be provided.
     */
    public void testOneOutOfTwoHasBothNameAndOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getLabs().get(1).setOther("other2");
        for (Lab l : aeReport.getLabs()) {
            LabValue lv = new LabValue();
            lv.setDate(DateUtils.createDate(2008, 1, 2));
            lv.setValue("v");

            l.setBaseline(lv);
            l.setNadir(lv);
            l.setRecovery(lv);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR1_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Replacement values incorrect", 1, errors.getErrorAt(0).getReplacementVariables()[0]);
    }

    /**
     * RuleName : LAB_BR3_CHK Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”." Error
     * Code : LAB_BR3_ERR Error Message : WORST_DATE must not be greater BASELINE_DATE
     * 
     * RuleName : LAB_BR2B_CHK Logic : “Baseline”, “Nadir/Worst”, “Recovery” or “Latest” fields must
     * be provided if “Lab Category” is not ‘Microbiology’. Error Code : LAB_BR2B_ERR Error Message :
     * "BASELINE_DATE, BASELINE_VALUE, BASELINE_UOM, WORST_DATE, WORST_VALUE, WORST_UOM,
     * RECOVERY_LATEST_DATE, RECOVERY_LATEST_VALUE and RECOVERY_LATEST_UOM must be provided if
     * LAB_CATEGORY is not ""Microbiology"".
     * 
     */
    public void testLabsHavingOnlyBaselineDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(new Date());

        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR2B_ERR");
        assertSameErrorCount(errors, 2);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object i = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.labs[" + i + "].baseline", "aeReport.labs[" + i + "].nadir", "aeReport.labs[" + i + "].recovery", "aeReport.labs[" + i + "].lab-category");
    }

    /**
     * RuleName : LAB_BR3_CHK Logic :"“Baseline Date” must not be greater .“Nadir/Worst Date” "
     * Error Code : LAB_BR3_ERR Error Message :BASELINE_DATE must not be greater WORST_DATE
     * 
     * RuleName : LAB_BR4_CHK Logic :"“Nadir/Worst Date” must not be greater than “Recovery Date” .
     * Error Code : LAB_BR4_ERR Error Message : WORST_DATE must not be greater than
     * RECOVERY_LAST_DATE
     * 
     * RuleName : LAB_BR2B_CHK Logic : “Baseline”, “Nadir/Worst”, “Recovery” or “Latest” fields must
     * be provided if “Lab Category” is not ‘Microbiology’. Error Code : LAB_BR2B_ERR Error Message :
     * "BASELINE_DATE, BASELINE_VALUE, BASELINE_UOM, WORST_DATE, WORST_VALUE, WORST_UOM,
     * RECOVERY_LATEST_DATE, RECOVERY_LATEST_VALUE and RECOVERY_LATEST_UOM must be provided if
     * LAB_CATEGORY is not ""Microbiology"".
     * 
     */
    public void testLabsHavingOnlyWorstDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(new Date());

        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv1);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR2B_ERR");
        assertSameErrorCount(errors, 2);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object i = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.labs[" + i + "].baseline", "aeReport.labs[" + i + "].nadir", "aeReport.labs[" + i + "].recovery", "aeReport.labs[" + i + "].lab-category");

    }

    /**
     * RuleName : LAB_BR3_CHK Logic :"“Baseline Date” must not be greater “Nadir/Worst Date”." Error
     * Code : LAB_BR3_ERR Error Message :BASELINE_DATE must not be greater WORST_DATE
     */
    /**
     * @throws Exception
     */
    public void testLabsHavingBaselineDateLTWorstDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(DateUtils.createDate(2005, 12, 1));
        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2006, 1, 3));

        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv1);

        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(DateUtils.createDate(2007, 1, 2));

        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When Baseline date is greater than Worst date");
    }

    /**
     * RuleName : LAB_BR3_CHK Logic :"“Baseline Date” must not be greater “Nadir/Worst Date” ."
     * Error Code : LAB_BR3_ERR Error Message : BASELINE_DATE must not be greater WORST_DATE
     */
    public void testLabsHavingBaselineDateGTWorstDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(DateUtils.createDate(2008, 1, 1));
        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2007, 1, 3));

        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv1);

        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(DateUtils.createDate(2008, 1, 2));

        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR3_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replcement incorrect", 0, errors.getErrorAt(0).getReplacementVariables()[0]);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object i = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.labs[" + i + "].baseline", "aeReport.labs[" + i + "].nadir");
        
    }

    /**
     * RuleName : LAB_BR3_CHK Logic :" “Baseline Date” must not be greater.“Nadir/Worst Date”" Error
     * Code : LAB_BR3_ERR Error Message : BASELINE_DATE must not be greater WORST_DATE
     */
    public void testLabsOneOutOfTwoHavingBaselineDateGTWorstDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(DateUtils.createDate(2007, 1, 1));
        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2006, 1, 3));
        LabValue wv2 = new LabValue();
        wv2.setValue("33");
        wv2.setDate(DateUtils.createDate(2007, 1, 3));
        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv2);

        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(DateUtils.createDate(2008, 1, 2));

        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR3_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Replcement incorrect", 0, errors.getErrorAt(0).getReplacementVariables()[0]);
    }

    /**
     * RuleName : LAB_BR4_CHK Logic :"“Nadir/Worst Date” must not be greater than “Recovery Date” .
     * Error Code : LAB_BR4_ERR Error Message : WORST_DATE must not be greater than
     * RECOVERY_LAST_DATE
     */
    public void testRecoverDateOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(new Date());

        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR2B_ERR");
        assertSameErrorCount(errors, 2);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object i = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.labs[" + i + "].baseline", "aeReport.labs[" + i + "].nadir", "aeReport.labs[" + i + "].recovery", "aeReport.labs[" + i + "].lab-category");

    }

    /**
     * RuleName : LAB_BR4_CHK Logic :"“Nadir/Worst Date” must not be greater than “Recovery Date” .
     * Error Code : LAB_BR4_ERR Error Message : WORST_DATE must not be greater than
     * RECOVERY_LAST_DATE
     */
    public void testWorstDateEqualToRecoveryDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(DateUtils.createDate(2005, 1, 1));
        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);

        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(DateUtils.createDate(2007, 1, 3));
        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2007, 1, 3));

        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv1);

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When recovery date is same as that of Worst date");
    }

    /**
     * RuleName : LAB_BR4_CHK Logic :"“Nadir/Worst Date” must not be greater than “Recovery Date” .
     * Error Code : LAB_BR4_ERR Error Message : WORST_DATE must not be greater than
     * RECOVERY_LAST_DATE
     */
    public void testWorstDateGTRecoveryDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(DateUtils.createDate(2004, 1, 1));
        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);

        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(DateUtils.createDate(2005, 1, 3));
        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2006, 1, 3));

        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR4_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replcement incorrect", 0, errors.getErrorAt(0).getReplacementVariables()[0]);
    }

    /**
     * RuleName : LAB_BR4_CHK Logic :"“Nadir/Worst Date” must not be greater than “Recovery Date” .
     * Error Code : LAB_BR4_ERR Error Message : WORST_DATE must not be greater than
     * RECOVERY_LAST_DATE
     */
    public void testOneOutOfTwoHavingWorstDateGTRecoveryDate() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();

        LabValue bv1 = new LabValue();
        bv1.setValue("33");
        bv1.setDate(DateUtils.createDate(2004, 1, 1));
        aeReport.getLabs().get(0).setBaseline(bv1);
        aeReport.getLabs().get(1).setBaseline(bv1);

        LabValue rv1 = new LabValue();
        rv1.setValue("33");
        rv1.setDate(DateUtils.createDate(2007, 1, 3));
        aeReport.getLabs().get(0).setRecovery(rv1);
        aeReport.getLabs().get(1).setRecovery(rv1);

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2008, 1, 3));
        LabValue wv2 = new LabValue();
        wv2.setValue("33");
        wv2.setDate(DateUtils.createDate(2007, 1, 3));
        aeReport.getLabs().get(0).setNadir(wv1);
        aeReport.getLabs().get(1).setNadir(wv2);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_BR4_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Replcement incorrect", 0, errors.getErrorAt(0).getReplacementVariables()[0]);

        assertNotNull(errors.getErrorAt(0).getFieldNames());
        Object i = errors.getErrorAt(0).getReplacementVariables()[0];
        assertCorrectFieldNames(errors.getErrorAt(0), "aeReport.labs[" + i + "].nadir", "aeReport.labs[" + i + "].recovery");
        
    }

    /**
     * RuleName : LAB_UK_CHK Logic :Lab Results must be unique Error Code : LAB_UK_ERR Error Message
     * :Lab Results must be unique
     */
    public void testDuplicateLabs() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (Lab l : aeReport.getLabs()) {
            LabValue lv = new LabValue();
            lv.setDate(DateUtils.createDate(2008, 1, 2));
            lv.setValue("v");

            l.setBaseline(lv);
            l.setNadir(lv);
            l.setRecovery(lv);
            l.getLabTerm().setTerm("KK");
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_UK_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Incorrect replacement", "KK", errors.getErrorAt(0).getReplacementVariables()[1]);
        assertEquals("Incorrect replacement", 1, errors.getErrorAt(0).getReplacementVariables()[0]);

        assertNull(errors.getErrorAt(0).getFieldNames());
    }

    /**
     * RuleName : LAB_UK_CHK Logic :Lab Results must be unique Error Code : LAB_UK_ERR Error Message
     * :Lab Results must be unique
     */
    public void testDuplicateLabsWithNameRecoveryWorstAndBasline() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        LabValue bl1 = new LabValue();
        bl1.setDate(DateUtils.createDate(2007, 11, 11));
        bl1.setValue("abcd1");

        LabValue bl2 = new LabValue();
        bl2.setDate(DateUtils.createDate(2007, 11, 11));
        bl2.setValue("abcd1");

        LabValue r1 = new LabValue();
        r1.setDate(DateUtils.createDate(2007, 11, 11));
        r1.setValue("abcdx");

        LabValue r21 = new LabValue();
        r21.setDate(DateUtils.createDate(2007, 11, 11));
        r21.setValue("abcdx");

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2007, 11, 11));

        aeReport.getLabs().get(0).getLabTerm().setTerm("kk");
        aeReport.getLabs().get(0).setBaseline(bl1);
        aeReport.getLabs().get(0).setRecovery(r1);
        aeReport.getLabs().get(0).setNadir(wv1);

        aeReport.getLabs().get(1).getLabTerm().setTerm("kk");
        aeReport.getLabs().get(1).setBaseline(bl2);
        aeReport.getLabs().get(1).setRecovery(r21);
        aeReport.getLabs().get(1).setNadir(wv1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_UK_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Incorrect replacement", "kk",errors.getErrorAt(0).getReplacementVariables()[1]);
        assertEquals("Incorrect replacement", 1, errors.getErrorAt(0).getReplacementVariables()[0]);
    }

    public void testNAFields() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        LabValue bl1 = new LabValue();
        bl1.setDate(DateUtils.createDate(2007, 11, 11));
        bl1.setValue("abcd1");

        LabValue bl2 = new LabValue();
        bl2.setDate(DateUtils.createDate(2007, 11, 11));
        bl2.setValue("abcd1");

        LabValue r1 = new LabValue();
        r1.setDate(DateUtils.createDate(2007, 11, 11));
        r1.setValue("abcdx");

        LabValue r21 = new LabValue();
        r21.setDate(DateUtils.createDate(2007, 11, 11));
        r21.setValue("abcdx");

        LabValue wv1 = new LabValue();
        wv1.setValue("33");
        wv1.setDate(DateUtils.createDate(2007, 11, 11));

        aeReport.getLabs().get(0).getLabTerm().setTerm("kk");
        aeReport.getLabs().get(0).setBaseline(bl1);
        aeReport.getLabs().get(0).setRecovery(r1);
        aeReport.getLabs().get(0).setNadir(wv1);

        aeReport.getLabs().get(1).getLabTerm().setTerm("kk");
        aeReport.getLabs().get(1).setBaseline(bl2);
        aeReport.getLabs().get(1).setRecovery(r21);
        aeReport.getLabs().get(1).setNadir(wv1);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "LAB_UK_ERR");
        assertSameErrorCount(errors, 1);
        assertNull(errors.getErrorAt(0).getFieldNames());
    }
}
