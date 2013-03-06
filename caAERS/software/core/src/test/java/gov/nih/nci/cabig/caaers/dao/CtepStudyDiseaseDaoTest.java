/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CtepStudyDiseaseDaoTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testDomainClass() {
		assertEquals(CtepStudyDisease.class, new CtepStudyDiseaseDao().domainClass());
	}

}
