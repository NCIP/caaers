package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class AdditionalInformationTest extends AbstractTestCase {

    private AdditionalInformation additionalInformation;
    private Boolean autopsyReport = true;

    private Boolean consults = true;

    private Boolean dischargeSummary = true;

    private Boolean flowCharts = true;

    private Boolean labReports = true;

    private Boolean obaForm = true;

    private Boolean other = true;

    private Boolean pathologyReport = true;

    private Boolean progressNotes = true;

    private Boolean radiologyReports = true;

    private Boolean referralLetters = true;

    private Boolean irbReport = true;

    private String otherInformation = "other";


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        additionalInformation = new AdditionalInformation();
        additionalInformation.setAutopsyReport(autopsyReport);
        additionalInformation.setConsults(consults);
        additionalInformation.setDischargeSummary(dischargeSummary);
        additionalInformation.setFlowCharts(flowCharts);
        additionalInformation.setGridId("grid id");
        additionalInformation.setIrbReport(irbReport);
        additionalInformation.setLabReports(labReports);
        additionalInformation.setObaForm(obaForm);
        additionalInformation.setOther(other);
        additionalInformation.setOtherInformation(otherInformation);
        additionalInformation.setPathologyReport(pathologyReport);
        additionalInformation.setProgressNotes(progressNotes);
        additionalInformation.setRadiologyReports(radiologyReports);
        additionalInformation.setReferralLetters(referralLetters);
        additionalInformation.setOtherInformation(otherInformation);
        additionalInformation.setReport(new ExpeditedAdverseEventReport());
        additionalInformation.setVersion(2);
    }

    public void testCopyBasicProperties() {
        AdditionalInformation copiedAdditionalInformation = additionalInformation.copy();


        assertNull("must not coy id", copiedAdditionalInformation.getId());

        assertNull("must not coy grid id", copiedAdditionalInformation.getGridId());
        assertNull("must not coy version number", copiedAdditionalInformation.getVersion());
        assertNull("must not coy expeditedReport", copiedAdditionalInformation.getReport());
        assertEquals(autopsyReport, copiedAdditionalInformation.getAutopsyReport());
        assertEquals(consults, copiedAdditionalInformation.getConsults());
        assertEquals(dischargeSummary, copiedAdditionalInformation.getDischargeSummary());
        assertEquals(flowCharts, copiedAdditionalInformation.getFlowCharts());
        assertEquals(irbReport, copiedAdditionalInformation.getIrbReport());
        assertEquals(labReports, copiedAdditionalInformation.getLabReports());
        assertEquals(obaForm, copiedAdditionalInformation.getObaForm());
        assertEquals(other, copiedAdditionalInformation.getOther());
        assertEquals(otherInformation, copiedAdditionalInformation.getOtherInformation());
        assertEquals(pathologyReport, copiedAdditionalInformation.getPathologyReport());
        assertEquals(progressNotes, copiedAdditionalInformation.getProgressNotes());
        assertEquals(radiologyReports, copiedAdditionalInformation.getRadiologyReports());
        assertEquals(referralLetters, copiedAdditionalInformation.getReferralLetters());


    }

}
