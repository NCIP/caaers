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
public class ConfigPropertyTypeTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetCode() {
		assertEquals(new Integer(1), ConfigPropertyType.REPORT_GROUP.getCode());
	}

	public void testGetDisplayName() {
		assertEquals("Report Definition Group", ConfigPropertyType.REPORT_GROUP.getDisplayName());
	}

}
