package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * CaaersSecurityFacadeImpl Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/08/2010</pre>
 * 
 */
public class CaaersSecurityFacadeImplTest extends CaaersTestCase {

    CaaersSecurityFacadeImpl facade;

    public void setUp() throws Exception {
        super.setUp();
        facade = (CaaersSecurityFacadeImpl) getDeployedApplicationContext().getBean("caaersSecurityFacade");
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLoadingOfBean() throws Exception{
        assertNotNull(facade);
    }

    public void testProvisionUser() throws Exception{
        fail("not implemented");
    }

    public void testCreateOrUpdateCSMUser() throws Exception{
        fail("not implemented");
    }

    public void testCheckAuthorization() throws Exception{
        SecurityTestUtils.switchUser("tester",
                UserGroupType.business_administrator.getSecurityRoleName(),
                UserGroupType.registrar.getSecurityRoleName());
        
        boolean b = facade.checkAuthorization(SecurityUtils.getAuthentication(),  "gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition", "READ");
        assertTrue(b);

        SecurityTestUtils.switchUser("tester",
                UserGroupType.system_administrator.getSecurityRoleName(),
                UserGroupType.registrar.getSecurityRoleName());
        b = facade.checkAuthorization(SecurityUtils.getAuthentication(),  "gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition", "READ");
        assertFalse(b);
    }

    public void testGetAccessibleProtectionElements() throws Exception {
        fail("Not Implemented");
    }

    public void testGetAccessibleStudyIds() throws Exception {
        fail("Not Implemented");
    }

    public void testGetAccessibleOrganizationIds() throws Exception {
        fail("Not Implemented");
    }

}
