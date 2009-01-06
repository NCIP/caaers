package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.LabCategory;

public class LabCategoryDaoTest extends DaoTestCase<LabCategoryDao> {

	
	public void testGetDomainClass(){
		Object obj = getDao().domainClass();
		assertNotNull(obj);
	}
	
	public void testGetAll(){
		List<LabCategory> labCats = getDao().getAll();
		assertNotNull(labCats);
		assertEquals(3,labCats.size());
	}
}
