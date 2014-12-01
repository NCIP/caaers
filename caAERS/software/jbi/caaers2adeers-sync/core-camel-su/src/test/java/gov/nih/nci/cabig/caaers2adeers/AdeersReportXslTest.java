package gov.nih.nci.cabig.caaers2adeers;

public class AdeersReportXslTest extends SafetyReportE2BXslTest {

    public void testWithdrawXML() throws Exception {
        String result = null;
        result = transform("xslt/adeers/request/report/withdraw.xml", "xslt/adeers/request/report-transformer.xsl");
        System.out.println(result);
        assertNotNull(result);
        assertNotSame("", result);
        assertTrue(result.contains("<TICKET_NUMBER>1642216</TICKET_NUMBER>"));
        assertTrue(result.contains("<PATIENT_ID>SUB85-1106</PATIENT_ID>"));
        assertTrue(result.contains("<NCI_PROTOCOL_NUMBER>RTOG-1106</NCI_PROTOCOL_NUMBER>"));
    }
}
