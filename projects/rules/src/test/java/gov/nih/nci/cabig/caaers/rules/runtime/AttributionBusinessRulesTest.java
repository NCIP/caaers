package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class AttributionBusinessRulesTest extends BusinessRulesExecutionServiceTest {
    @Override
    public String getBindUri() {
        // TODO Auto-generated method stub
        return "gov.nih.nci.cabig.caaers.rules.reporting_attribution_section";
    }

    @Override
    public String getRuleFile() {
        // TODO Auto-generated method stub
        return "attribution_rule.xml";
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testCorrectAttribution() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when correctly attributed");
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testInCorrectAttribution() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.getDiseaseAttributions().get(0).setAttribution(Attribution.UNLIKELY);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "AER_BR7_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replacment variable should be same", 2, errors.getErrorAt(1)
                        .getReplacementVariables()[0]);
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testNoAttributionAtAll() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.getDiseaseAttributions().clear();
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "AER_BR7_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replacment variable should be same", 2, errors.getErrorAt(1)
                        .getReplacementVariables()[0]);
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testNoAttributionAtAll_AllAEHasGradeDEATH() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.setGrade(Grade.DEATH);
            ae.getDiseaseAttributions().clear();
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when not attributed for Grade.DEATH AEs");
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testNoAttributionAtAll_OneOutOfTwoAEHasGradeDEATH() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        int i = 0;
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            if (i == 0) ae.setGrade(Grade.DEATH);
            ae.getDiseaseAttributions().clear();
            i++;
        }
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "AER_BR7_ERR");
        assertSameErrorCount(errors, 1);
        assertEquals("Replacment variable should be same", 2, errors.getErrorAt(0)
                        .getReplacementVariables()[0]);

    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testAttribution_AllAEHasGradeDEATH() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.setGrade(Grade.DEATH);
        }
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When attributed for Grade.DEATH");
    }
}
