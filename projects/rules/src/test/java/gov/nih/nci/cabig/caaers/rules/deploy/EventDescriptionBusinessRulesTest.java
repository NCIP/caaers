package gov.nih.nci.cabig.caaers.rules.deploy;

import java.util.Date;

import edu.nwu.bioinformatics.commons.DateUtils;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class EventDescriptionBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {
    String bindUri = "gov.nih.nci.cabig.caaers.rules.reporting_description_section";

    public String getBindUri() {
        return bindUri;
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_event_description.xml";
    }

    /**
     * RuleName : DSC_BR1A_CHK Rule : Date of Recovery or Death� must be provided if �Present
     * Status� has one of following values: Fatal/Died Recovered/Resolved without Sequelae
     * Recovered/Resolved with Sequelae" Error Code : DSC_BR1A_ERR Error Message :
     * DATE_OF_RECOVERY_DEATH must be provided for the provided PRESENT_STATUS value.
     */
    public void testEventDescriptionRule1() throws Exception {

        try {
            ExpeditedAdverseEventReport aeReport = createAEReport();
            aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
            aeReport.getResponseDescription().setRecoveryDate(new Date());
            ValidationErrors errors = fireRules(aeReport);

            assertEquals("There should not be any validation error", 0, errors.getErrorCount());
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occured while execution of rule");
        }
    }

    /**
     * RuleName : DSC_BR1A_CHK Rule : Date of Recovery or Death� must be provided if �Present
     * Status� has one of following values: Fatal/Died Recovered/Resolved without Sequelae
     * Recovered/Resolved with Sequelae" Error Code : DSC_BR1A_ERR Error Message :
     * DATE_OF_RECOVERY_DEATH must be provided for the provided PRESENT_STATUS value.
     */
    public void testEventDescriptionRule1_WithoutRecoveryDate() throws Exception {
        try {
            ExpeditedAdverseEventReport aeReport = createAEReport();
            aeReport.getResponseDescription().setRecoveryDate(null);
            aeReport.getResponseDescription().setDateRemovedFromProtocol(null);
            ValidationErrors errors = fireRules(aeReport);

            assertNotSame("There should be at least 1 validation error", 1, errors.getErrorCount());
            errors
                            .getErrorAt(0)
                            .getMessage()
                            .equals(
                                            "'Date of Recovery or Death' must be provided if 'Present Status' has "
                                                            + "one of following values:'Fatal/Died',"
                                                            + "'Recovered/Resolved without Sequelae','Recovered/Resolved with Sequelae'");

        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occured while execution of rule");
        }
    }

    /**
     * RuleName : DSC_BR1A_CHK Rule : Date of Recovery or Death� must be provided if �Present
     * Status� has one of following values: Fatal/Died Recovered/Resolved without Sequelae
     * Recovered/Resolved with Sequelae" Error Code : DSC_BR1A_ERR Error Message :
     * DATE_OF_RECOVERY_DEATH must be provided for the provided PRESENT_STATUS value.
     */
    public void testEventDescriptionRule1_WithoutRecoveryDateAndNotRecoverdPresentStatus()
                    throws Exception {
        try {
            ExpeditedAdverseEventReport aeReport = createAEReport();
            aeReport.getResponseDescription().setRecoveryDate(null);
            aeReport.getResponseDescription()
                            .setPresentStatus(PostAdverseEventStatus.NOT_RECOVERED);
            ValidationErrors errors = fireRules(aeReport);

            assertEquals("There should not be validation error", 0, errors.getErrorCount());
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occured while execution of rule");
        }
    }

    /**
     * 
     * RuleName : DSC_BR1B_CHK Rule : 'Date of Recovery or Death' must not be provided if 'Present
     * Status' is not one of following values: Fatal/Died Recovered/Resolved without Sequelae
     * Recovered/Resolved with Sequelae" Error Code : DSC_BR1B_ERR Error Message :
     * DATE_OF_RECOVERY_DEATH must not be provided for the provided PRESENT_STATUS value.
     * 
     * @throws Exception
     */
    public void testDateOfRecovery_WithoutPresentStatus() throws Exception {
        try {
            ExpeditedAdverseEventReport aeReport = createAEReport();
            aeReport.getResponseDescription().setPresentStatus(null);
            ValidationErrors errors = fireRules(aeReport);

            assertEquals("There should be validation error", 1, errors.getErrorCount());
            assertEquals("The error code should be : DSC_BR1B_ERR", errors.getErrorAt(0).getCode(),
                            "DSC_BR1B_ERR");
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occured while execution of rule");
        }
    }

    /**
     * 
     * RuleName : DSC_BR1B_CHK Rule : 'Date of Recovery or Death' must not be provided if 'Present
     * Status' is not one of following values: Fatal/Died Recovered/Resolved without Sequelae
     * Recovered/Resolved with Sequelae" Error Code : DSC_BR1B_ERR Error Message :
     * DATE_OF_RECOVERY_DEATH must not be provided for the provided PRESENT_STATUS value.
     * 
     * @throws Exception
     */

    public void testNoDateOfRecovery_NoPresentStatus() throws Exception {
        try {
            ExpeditedAdverseEventReport aeReport = createAEReport();
            aeReport.getResponseDescription().setPresentStatus(null);
            aeReport.getResponseDescription().setRecoveryDate(null);
            ValidationErrors errors = fireRules(aeReport);

            assertEquals("There should not be validation error", 0, errors.getErrorCount());
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occured while execution of rule");
        }
    }

    /**
     * 
     * RuleName : DSC_BR1B_CHK Rule : 'Date of Recovery or Death' must not be provided if 'Present
     * Status' is not one of following values: Fatal/Died Recovered/Resolved without Sequelae
     * Recovered/Resolved with Sequelae" Error Code : DSC_BR1B_ERR Error Message :
     * DATE_OF_RECOVERY_DEATH must not be provided for the provided PRESENT_STATUS value.
     * 
     * @throws Exception
     */

    public void testDateOfRecovery_WithNOT_RECOVEREDPresentStatus() throws Exception {
        try {
            ExpeditedAdverseEventReport aeReport = createAEReport();
            aeReport.getResponseDescription()
                            .setPresentStatus(PostAdverseEventStatus.NOT_RECOVERED);
            ValidationErrors errors = fireRules(aeReport);

            assertEquals("There should not be validation error", 1, errors.getErrorCount());
            assertEquals("The error code should be same", "DSC_BR1B_ERR", errors.getErrorAt(0)
                            .getCode());
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occured while execution of rule");
        }
    }

    /**
     * RuleName : DSC_BR2_CHK Rule : "'Retreated' must be �No� if 'Present Status' is �Fatal/Died� "
     * Error Code : DSC_BR2_ERR Error Message : RETREATED must be "No" if PRESENT_STATUS is
     * ''Fatal/Died'
     */

    public void testRetreatedNo_WithPresentStatusDEAD() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setRetreated(Boolean.FALSE); // No
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when Retreated is no", 0, errors
                        .getErrorCount());
    }

    /**
     * RuleName : DSC_BR2_CHK Rule : "'Retreated' must be �No� if 'Present Status' is �Fatal/Died� "
     * Error Code : DSC_BR2_ERR Error Message : RETREATED must be "No" if PRESENT_STATUS is
     * ''Fatal/Died'
     */

    public void testRetreatedNo_WithPresentStatusRECOVERED() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(
                        PostAdverseEventStatus.RECOVERED_WITHOUT_SEQUELAE);
        aeReport.getResponseDescription().setRetreated(Boolean.FALSE); // No
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should not be any error, when Retreated is no and status is RECOVERED",
                        0, errors.getErrorCount());
    }

    /**
     * RuleName : DSC_BR2_CHK Rule : "'Retreated' must be �No� if 'Present Status' is �Fatal/Died� "
     * Error Code : DSC_BR2_ERR Error Message : RETREATED must be "No" if PRESENT_STATUS is
     * ''Fatal/Died'
     */

    public void testRetreatedYes_WithPresentStatusDEAD() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setRetreated(Boolean.TRUE); // No
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should  error, when Retreated is no", 1, errors.getErrorCount());
        assertEquals("Error code must me 'DSC_BR2_ERR'", "DSC_BR2_ERR", errors.getErrorAt(0)
                        .getCode());
    }

    /**
     * RuleName : DSC_BR2_CHK Rule : "'Retreated' must be �No� if 'Present Status' is �Fatal/Died� "
     * Error Code : DSC_BR2_ERR Error Message : RETREATED must be "No" if PRESENT_STATUS is
     * ''Fatal/Died'
     */

    public void testRetreatedNull_WithPresentStatusDEAD() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setRetreated(null); // No
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("There should be error, when present status is DEAD ", 1, errors
                        .getErrorCount());
        assertEquals("Error code must me 'DSC_BR2_ERR'", "DSC_BR2_ERR", errors.getErrorAt(0)
                        .getCode());
    }

    /**
     * RuleName : DSC_BR2_CHK Rule : "'Retreated' must be �No� if 'Present Status' is �Fatal/Died� "
     * Error Code : DSC_BR2_ERR Error Message : RETREATED must be "No" if PRESENT_STATUS is
     * ''Fatal/Died'
     */

    public void testRetreatedYes_WithPresentStatusRECOVERED() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(
                        PostAdverseEventStatus.RECOVERED_WITH_SEQUELAE);
        aeReport.getResponseDescription().setRetreated(Boolean.TRUE); // No
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                        "There should not be any error, when Retreated is YES and present status is not DEAD",
                        0, errors.getErrorCount());
    }

    /**
     * RuleName : DSC_BR3_CHK Rule : 'Date Removed from Protocol Treatment' must be �Yes� if 'Present
     * Status' is �Fatal/Died� Error Code : DSC_BR3_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT
     * must be "Yes" if PRESENT_STATUS is ''Fatal/Died'
     */
    public void testDateRemovedFromProtocol_WhenPresentStatusIsDEAD() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(new Date());
        ValidationErrors errors = fireRules(aeReport);
        assertEquals("There should not be any error, when DateRemovedFromProtocol is not null, and present status is  DEAD",0, errors.getErrorCount());
    }

    /**
     * RuleName : DSC_BR3_CHK Rule : 'Date Removed from Protocol Treatment' must be �Yes� if 'Present
     * Status' is �Fatal/Died� Error Code : DSC_BR3_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT
     * must be "Yes" if PRESENT_STATUS is ''Fatal/Died'
     */
    public void testDateRemovedFromProtocolNull_WhenPresentStatusIsDEAD() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                        "There should be error, when DateRemovedFromProtocol is  null, and present status is  DEAD",
                        1, errors.getErrorCount());
        assertEquals("Error code should be DSC_BR3_ERR", "DSC_BR3_ERR", errors.getErrorAt(0)
                        .getCode());
    }

    /**
     * RuleName : DSC_BR3_CHK Rule : 'Removed from Protocol Treatment' must be Yes if 'Present
     * Status' is Fatal/Died Error Code : DSC_BR3_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT
     * must be "Yes" if PRESENT_STATUS is ''Fatal/Died'
     */
    public void testDateRemovedFromProtocolNull_WhenPresentStatusIsRECOVERING() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(
                        PostAdverseEventStatus.RECOVERED_WITH_SEQUELAE);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(null);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                        "There should not be error, when DateRemovedFromProtocol is  null, and present status is  RECOVERING",
                        0, errors.getErrorCount());
    }

    /**
     * RuleName : DSC_BR5_CHK Rule : " 'Date Removed from Protocol Treatment' must not be greater
     * than 'Date of Recovery or Death' when 'Present Status' is 'Fatal/Died'" Error Code :
     * DSC_BR5_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT_DATE should not be greater than the
     * DATE_OF_RECOVERY_DEATH when PRESENT_STATUS is ''Fatal/Died'
     * 
     */

    public void testDateRemoved_NotGtDateOfRecovery() throws Exception {
        Date removedDate = DateUtils.createDate(2007, 11, 20);
        Date recoveryDate = DateUtils.createDate(2007, 11, 22);
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(removedDate);
        aeReport.getResponseDescription().setRecoveryDate(recoveryDate);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No errors when dateRemoved less than DateOfRecovery", 0, errors
                        .getErrorCount());

    }

    /**
     * RuleName : DSC_BR5_CHK Rule : " 'Date Removed from Protocol Treatment' must not be greater
     * than 'Date of Recovery or Death' when 'Present Status' is 'Fatal/Died'" Error Code :
     * DSC_BR5_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT_DATE should not be greater than the
     * DATE_OF_RECOVERY_DEATH when PRESENT_STATUS is ''Fatal/Died'
     * 
     */

    public void testDateRemoved_NotGtDateOfRecovery_PresentStatusNotDEAD() throws Exception {
        Date removedDate = DateUtils.createDate(2007, 11, 20);
        Date recoveryDate = DateUtils.createDate(2007, 11, 22);
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(
                        PostAdverseEventStatus.RECOVERED_WITH_SEQUELAE);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(removedDate);
        aeReport.getResponseDescription().setRecoveryDate(recoveryDate);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No errors when dateRemoved less than DateOfRecovery and status not DEAD", 0,
                        errors.getErrorCount());
    }

    /**
     * RuleName : DSC_BR5_CHK Rule : " 'Date Removed from Protocol Treatment' must not be greater
     * than 'Date of Recovery or Death' when 'Present Status' is 'Fatal/Died'" Error Code :
     * DSC_BR5_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT_DATE should not be greater than the
     * DATE_OF_RECOVERY_DEATH when PRESENT_STATUS is ''Fatal/Died'
     * 
     */

    public void testDateRemoved_GtDateOfRecovery_PresentStatusDEAD() throws Exception {
        Date removedDate = DateUtils.createDate(2007, 11, 24);
        Date recoveryDate = DateUtils.createDate(2007, 11, 22);
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(removedDate);
        aeReport.getResponseDescription().setRecoveryDate(recoveryDate);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals(
                        "Error should be there when dateRemoved Greater than DateOfRecovery and status is DEAD",
                        1, errors.getErrorCount());
        assertEquals("Error message should be same", "DSC_BR5_ERR", errors.getErrorAt(0).getCode());
    }

    /**
     * RuleName : DSC_BR5_CHK Rule : " 'Date Removed from Protocol Treatment' must not be greater
     * than 'Date of Recovery or Death' when 'Present Status' is 'Fatal/Died'" Error Code :
     * DSC_BR5_ERR Error Message : REMOVED_FROM_PROTOCOL_TRT_DATE should not be greater than the
     * DATE_OF_RECOVERY_DEATH when PRESENT_STATUS is ''Fatal/Died'
     * 
     */

    public void testDateRemoved_GtDateOfRecovery_Status_NotDEAD() throws Exception {
        Date removedDate = DateUtils.createDate(2007, 11, 24);
        Date recoveryDate = DateUtils.createDate(2007, 11, 22);
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getResponseDescription().setPresentStatus(
                        PostAdverseEventStatus.RECOVERED_WITH_SEQUELAE);
        aeReport.getResponseDescription().setDateRemovedFromProtocol(removedDate);
        aeReport.getResponseDescription().setRecoveryDate(recoveryDate);
        ValidationErrors errors = fireRules(aeReport);

        assertEquals("No errors when dateRemoved greater than DateOfRecovery and status not DEAD",
                        0, errors.getErrorCount());

    }

}
