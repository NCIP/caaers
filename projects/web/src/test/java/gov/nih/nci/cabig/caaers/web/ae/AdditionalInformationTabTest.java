package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Krikor Krumlian
 */
public class AdditionalInformationTabTest extends AeTabTestCase {
    @Override
    protected AdditionalInformationTab createTab() {
        return new AdditionalInformationTab();
    }

    public void testFields() throws Exception {
        assertFieldProperties("desc",
            "aeReport.additionalInformation.autopsyReport",
            "aeReport.additionalInformation.consults",
            "aeReport.additionalInformation.dischargeSummary",
            "aeReport.additionalInformation.flowCharts",
            "aeReport.additionalInformation.labReports",
            "aeReport.additionalInformation.obaForm",
            "aeReport.additionalInformation.other",
            "aeReport.additionalInformation.pathologyReport",
            "aeReport.additionalInformation.progressNotes",
            "aeReport.additionalInformation.radiologyReports",
            "aeReport.additionalInformation.referralLetters",
            "aeReport.additionalInformation.irbReport",
            "aeReport.additionalInformation.otherInformation"
        );
    }
}