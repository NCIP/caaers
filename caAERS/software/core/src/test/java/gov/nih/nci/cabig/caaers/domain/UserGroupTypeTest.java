/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class UserGroupTypeTest extends TestCase {
	
	private UserGroupType grpType = UserGroupType.ae_reporter;
	
	public void testGetCsmName() {
		assertEquals("ae_reporter", grpType.getCsmName());
	}

	public void testGetByCode() {
		assertSame(grpType, UserGroupType.getByCode(-117));
	}
	
	public void testGetByCode_WrongCode() {
		assertNull(UserGroupType.getByCode(-87777777));
	}

	public void testToString() {
		assertEquals("ae_reporter", grpType.toString());
	}


	
	public void testGetDisplayName(){
		assertEquals("AE Reporter", grpType.getDisplayName());
	}
	
	public void testGetCode(){
		assertEquals(-117, grpType.getCode().intValue());
	}

    public void testValueOf(){
        assertEquals(UserGroupType.ae_reporter, UserGroupType.valueOf("ae_reporter"));
    }
	
	public void testAcronyms() {
        assertEquals("AE Reporter", UserGroupType.ae_reporter.getDisplayName());
        assertEquals("AE Expedited Report Reviewer", UserGroupType.ae_expedited_report_reviewer.getDisplayName());
        assertEquals("AE Rule And Report Manager", UserGroupType.ae_rule_and_report_manager.getDisplayName());
        assertEquals("AE Study Data Reviewer", UserGroupType.ae_study_data_reviewer.getDisplayName());
        assertEquals("Study QA Manager", UserGroupType.study_qa_manager.getDisplayName());
        assertEquals("Registration QA Manager", UserGroupType.registration_qa_manager.getDisplayName());
    }


}
