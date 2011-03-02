package gov.nih.nci.cabig.caaers.dao.security;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import org.dbunit.operation.DatabaseOperation;

import java.util.List;

public class RolePrivilegeDaoTest extends DaoTestCase<RolePrivilegeDao> {
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetRoles(){
		List<String> roles = getDao().getRoles("gov.nih.nci.cabig.caaers.Task", "ACCESS");
		assertNotNull(roles);
		assertEquals(2, roles.size());
		
		roles = getDao().getRoles("gov.nih.nci.cabig.caaers.Admin", "ACCESS");
		assertNotNull(roles);
		assertEquals(1, roles.size());
	}

}
