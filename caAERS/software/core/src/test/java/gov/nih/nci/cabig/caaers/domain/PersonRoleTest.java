/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * PersonRole Tester.
 *
 * @author Biju Joseph
 * @since <pre>02/16/2010</pre>
 * 
 */
public class PersonRoleTest extends TestCase {


    PersonRole aeReporter;
    PersonRole aeReportReviewer;
    PersonRole aeStudyDataReviewer;

    public void setUp() throws Exception {
        super.setUp();
        aeReporter = PersonRole.AE_REPORTER;
        aeReportReviewer = PersonRole.AE_EXPEDITED_REPORT_REVIEWER;
        aeStudyDataReviewer = PersonRole.AE_STUDY_DATA_REVIEWER;
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    // Will test if all attributes of PersonRole are correct.
    public void testCorrectAttributes() throws Exception {
        
	    assertCorrectAttributes(aeReporter, 4, "Participant Coordinator","ae_reporter", UserGroupType.ae_reporter);
	    assertCorrectAttributes(aeReportReviewer, 9, "Central Office Report Reviewer","ae_expedited_report_reviewer", UserGroupType.ae_expedited_report_reviewer);
        assertCorrectAttributes(aeStudyDataReviewer, 10, "Data Coordinator", "ae_study_data_reviewer", UserGroupType.ae_study_data_reviewer);

    }

    public void assertCorrectAttributes(PersonRole pr, Integer code, String displayName, String roleCode, UserGroupType... groups){
        assertEquals("Incorrect Code", code, pr.getCode());
        assertEquals("Incorrect displayName ", displayName, pr.getDisplayName());
        assertEquals("Incorrect roleCode", roleCode, pr.getRoleCode());
    }
}
