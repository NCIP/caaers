package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.List;

public class DCPRulesTest extends BusinessRulesExecutionServiceTest {
    String bindUri = "gov.nih.nci.cabig.caaers.rules.sponsor.division_of_cancer_prevention.sae_reporting_rules";

    public String getBindUri() {
        return bindUri;
    }

    @Override
    public String getRuleFile() {
        // return
        // "/Users/sakkala/tech-workspace/caaers/rules/target/test-classes/gov/nih/nci/cabig/caaers/rules/runtime/dcp.xml";
        return "dcp.xml";

    }

    public void testOutcome() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        Study study = new Study();
        study.setAdeersReporting(false);
        study.setPhaseCode("Phase I Trial");
        Organization org = new Organization();
        org.setName("Division of Cancer Prevention");
        study.setPrimaryFundingSponsorOrganization(org);
        Outcome oc = new Outcome();
        oc.setOutcomeType(OutcomeType.HOSPITALIZATION);
        List<Outcome> ol = new ArrayList<Outcome>();
        ol.add(oc);
        aeReport.setOutcomesInternal(ol);

        ValidationErrors errors = fireRules(aeReport, study);

        assertNoErrors(errors, "Rule Not Fired");

    }

}
