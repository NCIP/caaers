/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;

public class MeddraStudyDiseaseDaoTest extends DaoNoSecurityTestCase<MeddraStudyDiseaseDao> {
	
	public void testGetDomainClass(){
		Object obj = getDao().domainClass();
		assertNotNull(obj);
	}

}
