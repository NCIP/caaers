/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package webservice.adeers;

import gov.nih.nci.ctep.adeers.client.AEReportCancelInfo;
import gov.nih.nci.ctep.adeers.client.AEReportJobInfo;

/**
 * @author: Biju Joseph
 */
public class CaaersAdeersSubmissionWorkflowTest extends AdeersIntegrationTestCase {

       private String tempPath = "caaers-adeers-webservice-su/src/test/resources/webservice/adeers/submission/";

    /**
     * On a multi modality study  RTOG-0524
     *   1. Report 24 hr with NCI Ind Agent
     *   2. Complete 24 hr with 5 Day report with Radiation information aswell.
     * @throws Exception
     */
      public void test24HrSubmissionFollowedBy5Day() throws Exception{

          String ticketNumber = submitAndReturnTicketNumber("RTOG-0524_24Hr_NCI_INDAgent.xml");
          String secondTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("RTOG-0524_5day_NCI_INDAgentAndRadiation.xml",
                  ticketNumber, null);
          assertEquals(secondTicketNumber, ticketNumber);

       }
    /**
     * On a multi modality study  T92-0215
     *   1. Report 24 hr with NCI Ind Agent
     *   2. Complete 24 hr with 5 Day report with Radiation information aswell.
     * @throws Exception
     */
      public void testSurgery24HrSubmissionFollowedBy5DayWithNCIAgentAndRadiation() throws Exception{

          String ticketNumber = submitAndReturnTicketNumber("T92-0215_NCI_Surgery24Hr.xml");
          String secondTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("T92-0215_NCI_SurgeryAndRadiationAndAgent5Day.xml",
                  ticketNumber, null);
          assertEquals(secondTicketNumber, ticketNumber);

       }


    /**
     * On a multi modality study  N0071
     *   1. Report 10 day Device Only
     *   2. Amend it with 10 day Device Only with Attribution changes
     *   3. Amend it with 10 day Device Only with Attribution changes
     * @throws Exception
     */
    public void test10DayDeviceOnlySubmissionFollowedBy10DayAmend() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("N0071_10Day_DeviceOnly.xml");
        String secondTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("N0071_10Day_DeviceOnly_AmendWith10Day.xml",
                ticketNumber, "1");
        assertEquals(secondTicketNumber, ticketNumber);
        String thirdTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("N0071_10Day_DeviceOnly_AmendWith10Day.xml",
                ticketNumber, "2");
        assertEquals(thirdTicketNumber, ticketNumber);

    }

    /**
     * On a multi modality study  ANBL09P1
     *   1. Report 24Hr with Surgery and Radiation
     *   2. Withdraw generated 5Day
     * @throws Exception
     */
    public void testSubmit24HrAndWithdraw5DayChild() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("ANBL09P1_24Hr_WithSurgeryAndRadiation.xml");
        String secondTicketNumber =  replaceValuesThenWithdrawAndReturnTicketNumber("ANBL09P1_24Hr_WithSurgeryAndRadiation_Withdraw.xml",
                ticketNumber,  null);
        assertEquals(secondTicketNumber, ticketNumber);
    }


    /**
     * On a surgery only study  GOG-0210
     *   1. Report 10Day Surgery
     * @throws Exception
     */
    public void testSubmit10DaySurgeryOnly() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("GOG-0210_10Day_SurgeryOnly.xml");
        assertNotNull(ticketNumber);
    }
    /**
     * On a Commercial Study
     *   1. Report 10Day Agent only
     * @throws Exception
     */
    public void testSubmit10DayCommercialAgent() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("CALGB-50303_CommercialAgent10DayReport.xml");
        assertNotNull(ticketNumber);
    }
   /**
     * On a Commercial Study
     *   1. Report 10Day Agent only
     * @throws Exception
     */
    public void testSubmit10DayOnNonNCIAgent() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("N0071_NonNCI_Agent10Day.xml");
        assertNotNull(ticketNumber);
    }


    /**
     * On a multi modality study  N0071
     *   1. Report 10 day Agent Only
     *   2. Amend it with 10 day device only
     * @throws Exception
     */
    public void test10DayNonNCIAgentThenAmendAndReplaceItWithNonNCIDevice10Day() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("N0071_10Day_DeviceOnly.xml");
        String secondTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("N0071_Amend10DayAgnent_With10DayDevice.xml",
                ticketNumber, "1");
        assertEquals(secondTicketNumber, ticketNumber);
    }

    /**
     * On a surgery  study  GOG-0210
     *   1. Report 10Day Surgery
     *   2. Amend it with 24 Hr
     *   3. Complete it with 5Day
     * @throws Exception
     */
    public void testSubmit10DaySurgeryOnly_ThenAmendWith24Hr_ThenComplete5Day() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("GOG-0210_10Day_SurgeryOnly.xml");
        String secondTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("GOG-0210_10Day_SurgeryOnly_AmendWith24Hr.xml",
                ticketNumber,  "1");
        assertEquals(secondTicketNumber, ticketNumber);
        String thridTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("GOG-0210_10Day_SurgeryOnly_AmendWith24Hr_CompleteWith5Day.xml",
                ticketNumber,  "1");
        assertEquals(thridTicketNumber, ticketNumber);
    }

    /**
     * On a surgery  study  GOG-0210
     *   1. Report 10Day Surgery
     *   2. Amend it with 24 Hr
     *   3. Withdraw 5Day
     * @throws Exception
     */
    public void testSubmit10DaySurgeryOnly_ThenAmendWith24Hr_ThenWithdraw5Day() throws Exception{

        String ticketNumber = submitAndReturnTicketNumber("GOG-0210_10Day_SurgeryOnly.xml");
        String secondTicketNumber =  replaceValuesThenSubmitAndReturnTicketNumber("GOG-0210_10Day_SurgeryOnly_AmendWith24Hr.xml",
                ticketNumber,  "1");
        assertEquals(secondTicketNumber, ticketNumber);
        String thridTicketNumber =  replaceValuesThenWithdrawAndReturnTicketNumber("GOG-0210_24Hr_WithSurgeryWithdraw.xml",
                ticketNumber,  "1");
        assertEquals(thridTicketNumber, ticketNumber);
    }

    private String submitAndReturnTicketNumber(String fileName) throws Exception{
        String xml24 = transform(tempPath + fileName);
        AEReportJobInfo aeReportJobInfo = submit(xml24);
        assertSuccess(aeReportJobInfo);
        assertFalse(aeReportJobInfo.getTicketNumber() ==  null);
        return aeReportJobInfo.getTicketNumber();

    }

    private String replaceValuesThenSubmitAndReturnTicketNumber(String fileName, String ticketNumber, String versionNumber) throws Exception {
             return  replaceValuesThenSubmitOrWithdrawAndReturnTicketNumber(fileName, ticketNumber, versionNumber, false);
    }

    private String replaceValuesThenWithdrawAndReturnTicketNumber(String fileName, String ticketNumber, String versionNumber) throws Exception {
        return replaceValuesThenSubmitOrWithdrawAndReturnTicketNumber(fileName, ticketNumber, versionNumber, true);

    }
    private String replaceValuesThenSubmitOrWithdrawAndReturnTicketNumber(String fileName, String ticketNumber, String versionNumber, boolean cancel) throws Exception {
        String xml = loadFile(tempPath + fileName);
        if(ticketNumber != null){
            String newAssignedId = "<assignedIdentifer>" + ticketNumber + "</assignedIdentifer>";
            xml = xml.replaceAll("<assignedIdentifer>1</assignedIdentifer>", newAssignedId);
        }
        if(versionNumber != null){
            String newVersionNumber = "<reportVersionId>" + versionNumber + "</reportVersionId>";
            xml = xml.replaceAll("<reportVersionId>1</reportVersionId>", newVersionNumber);
        }


        xml = transformContent(xml);
        if(cancel){
            AEReportCancelInfo aeReportJobInfo = withdraw(xml);
            assertSuccess(aeReportJobInfo);
            assertFalse(aeReportJobInfo.getTicketNumber() ==  null);
            return aeReportJobInfo.getTicketNumber();
        }  else {
            AEReportJobInfo aeReportJobInfo = submit(xml);
            assertSuccess(aeReportJobInfo);
            assertFalse(aeReportJobInfo.getTicketNumber() ==  null);
            return aeReportJobInfo.getTicketNumber();
        }

    }



}
