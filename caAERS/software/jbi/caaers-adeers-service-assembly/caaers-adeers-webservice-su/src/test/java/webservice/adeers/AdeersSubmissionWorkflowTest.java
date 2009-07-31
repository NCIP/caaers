package webservice.adeers;

import java.io.BufferedReader;
import java.io.FileReader;

import caaers.client.AEReportJobInfo;

/*
 * R - Regular Report
 * RA - Regular Amend
 * 24 - 24 hr notofication
 * 24C - 24 hr complete
 * 24A - 24 hr Amend
 * 24AC - 24 hr Amend Complete
 */
public class AdeersSubmissionWorkflowTest extends BaseCaller {
	private static String R = "Regular report";
	private static String RA = "Regular amendment";
	private static String _24 = "24-hr notification";
	private static String _24C = "24-hr notification complete";
	private static String _24A = "24-hr amendment";
	private static String _24AC = "24-hr amendment complete";
	private static String RT = "$RT";
	private static String TN_AN = "<!--TN,AN-->";
	
	/*
	 * Work Flow :
	 * 	1. Submit a new Regular report, obtain ticket number 
	 *  2. Submit Regular amendment with ammendment # 1 and ticket number 
	 *  2. Submit Regular amendment with ammendment # 2 and ticket number 
	 */
	public void test_R_RA_RA_Success() throws Exception{
		// get XML from file 
		String adeersXMLFile = "adeers-basic-full.xml";
		String baseXML = getString(adeersXMLFile);
		String xml = baseXML.replace(RT, R);
		// submit report
		AEReportJobInfo aeReportJobInfo = submit(xml);
		// assert status
		assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
		String ticketNumber = aeReportJobInfo.getTicketNumber();
		// modify XML with ticket number and amendment number 		
		xml = baseXML.replace(RT,RA);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo2 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo2.getReportStatus().toString());
		//modify XML with ticket number and amendment number 		
		xml = baseXML.replace(RT,RA);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>2</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo3 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo3.getReportStatus().toString());
	}

	/*
	 * Work Flow :
	 * 	1. Submit a new 24-hr notification , obtain ticket number 
	 *  2. Submit 24-hr notification complete with ticket number only 
	 *  3. Submit 24-hr amendment with ticket number and amendment # 1
	 *  4. Submit 24-hr amendment complete with ticket number and amendment # 1
	 */
	public void test_24_24C_24A_24AC_Success() throws Exception{
		// get XML from file 
		String adeersXMLFile = "adeers-basic-full.xml";
		String baseXML = getString(adeersXMLFile);
		String xml = baseXML.replace(RT, _24);
		// submit report
		AEReportJobInfo aeReportJobInfo = submit(xml);
		// assert status
		assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
		String ticketNumber = aeReportJobInfo.getTicketNumber();
		// modify XML with ticket number and submit 24C 		
		xml = baseXML.replace(RT, _24C);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo2 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo2.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24A 		
		xml = baseXML.replace(RT, _24A);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo3 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo3.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24AC 		
		xml = baseXML.replace(RT, _24AC);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo4 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo4.getReportStatus().toString());		
	}

	/*
	 * Work Flow :
	 * 	1. Submit a new 24-hr notification , obtain ticket number 
	 *  2. Submit 24-hr notification complete with ticket number only 
	 *  3. Submit Regular amendment with ticket number and amendment # 1
	 *  4. Submit 24-hr amendment with ticket number and amendment # 2
	 *  5. Submit 24-hr amendment complete with ticket number and amendment # 2
	 */
	public void test_24_24C_RA_24A_24AC_Success() throws Exception{
		// get XML from file 
		String adeersXMLFile = "adeers-basic-full.xml";
		String baseXML = getString(adeersXMLFile);
		String xml = baseXML.replace(RT, _24);
		// submit report
		AEReportJobInfo aeReportJobInfo = submit(xml);
		// assert status
		assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
		String ticketNumber = aeReportJobInfo.getTicketNumber();
		// modify XML with ticket number and submit 24C 		
		xml = baseXML.replace(RT, _24C);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo2 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo2.getReportStatus().toString());
		// modify XML with ticket number and submit RA 		
		xml = baseXML.replace(RT, RA);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo2_5 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo2_5.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24A 		
		xml = baseXML.replace(RT, _24A);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>2</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo3 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo3.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24AC 		
		xml = baseXML.replace(RT, _24AC);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>2</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo4 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo4.getReportStatus().toString());		
	}

	/*
	 * Work Flow :
	 * 	1. Submit a new Regular Report , obtain ticket number 
	 *  2. Submit Regular amendment with ticket number and amendment # 1
	 *  3. Submit 24-hr amendment with ticket number and amendment # 2
	 *  4. Submit 24-hr amendment complete with ticket number and amendment # 2
	 */
	public void test_R_RA_24A_24AC_Success() throws Exception{
		// get XML from file 
		String adeersXMLFile = "adeers-basic-full.xml";
		String baseXML = getString(adeersXMLFile);
		String xml = baseXML.replace(RT, R);
		// submit report
		AEReportJobInfo aeReportJobInfo = submit(xml);
		// assert status
		assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
		String ticketNumber = aeReportJobInfo.getTicketNumber();
		// modify XML with ticket number and submit RA 		
		xml = baseXML.replace(RT, RA);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo2_5 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo2_5.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24A 		
		xml = baseXML.replace(RT, _24A);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>2</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo3 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo3.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24AC 		
		xml = baseXML.replace(RT, _24AC);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>2</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo4 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo4.getReportStatus().toString());		
	}

	/*
	 * Work Flow :
	 * 	1. Submit a new Regular Report , obtain ticket number 
	 *  3. Submit 24-hr amendment with ticket number and amendment # 1
	 *  4. Submit 24-hr amendment complete with ticket number and amendment # 1
	 */
	public void test_R_24A_24AC_Success() throws Exception{
		// get XML from file 
		String adeersXMLFile = "adeers-basic-full.xml";
		String baseXML = getString(adeersXMLFile);
		String xml = baseXML.replace(RT, R);
		// submit report
		AEReportJobInfo aeReportJobInfo = submit(xml);
		// assert status
		assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
		String ticketNumber = aeReportJobInfo.getTicketNumber();

		// modify XML with ticket number , amendment number and submit 24A 		
		xml = baseXML.replace(RT, _24A);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo3 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo3.getReportStatus().toString());
		// modify XML with ticket number , amendment number and submit 24AC 		
		xml = baseXML.replace(RT, _24AC);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo4 = submit(xml);
		//assert status
		assertEquals("SUCCESS", aeReportJobInfo4.getReportStatus().toString());		
	}
	
	/*
	 * Work Flow :
	 * 	1. Submit a new 24-hr notification , obtain ticket number 
	 *  3. Submit 24-hr amendment with ticket number and amendment # 1
	 *  FAIL - Cannot amend as there exists another amendment that is still in Pending state
	 */
	public void test_24_24A_Failure() throws Exception{
		// get XML from file 
		String adeersXMLFile = "adeers-basic-full.xml";
		String baseXML = getString(adeersXMLFile);
		String xml = baseXML.replace(RT, _24);
		// submit report
		AEReportJobInfo aeReportJobInfo = submit(xml);
		// assert status
		assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
		String ticketNumber = aeReportJobInfo.getTicketNumber();
		
		// modify XML with ticket number , amendment number and submit 24A 		
		xml = baseXML.replace(RT, _24A);
		xml = xml.replace(TN_AN, "<TICKET_NUMBER>"+ticketNumber+"</TICKET_NUMBER><AMENDMENT_NUMBER>1</AMENDMENT_NUMBER>");
		//submit amendment
		AEReportJobInfo aeReportJobInfo3 = submit(xml);

		//assert status
		assertEquals("Cannot amend as there exists another amendment that is still in Pending state", aeReportJobInfo3.getJobExceptions()[0].getDescription().toString().trim());
	}
	
	private String getString(String fileName) throws Exception {
		String filePath = "caaers-adeers-webservice-su/src/test/resources/webservice/adeers/";

		StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath+fileName));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();

	}
}
