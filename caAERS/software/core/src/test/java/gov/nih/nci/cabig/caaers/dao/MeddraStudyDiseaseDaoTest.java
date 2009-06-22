package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;

public class MeddraStudyDiseaseDaoTest extends DaoNoSecurityTestCase<MeddraStudyDiseaseDao> {
	
	public void testGetDomainClass(){
		Object obj = getDao().domainClass();
		assertNotNull(obj);
	}

}
