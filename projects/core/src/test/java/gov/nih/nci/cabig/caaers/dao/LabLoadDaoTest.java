package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.LabLoad;

public class LabLoadDaoTest extends DaoTestCase<LabLoadDao> {
	
	public void testGet() throws Exception {
		LabLoad loaded = getDao().getById(-1001);
        assertNotNull("Lab not found", loaded);
        assertEquals("New Lab", loaded.getName());
    }
	
}
