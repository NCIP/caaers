/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security.authorization;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import junit.framework.TestCase;
import org.acegisecurity.Authentication;
import org.easymock.EasyMock;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class SiteAuthorizationCheckTest extends AbstractTestCase {
    SiteAuthorizationCheck check;
    CaaersSecurityFacade facade;
    Authentication auth;

    public void setUp() throws Exception {
        check = new SiteAuthorizationCheck();
        facade = registerMockFor(CaaersSecurityFacade.class);
        check.setCaaersSecurityFacade(facade);
        SecurityTestUtils.switchToSuperuser();
        auth = SecurityUtils.getAuthentication();

    }

    public void testCheckAuthorization() throws Exception {
        Organization o = Fixtures.createOrganization("test", "test");
        String role = UserGroupType.ae_reporter.getCsmName();
        boolean authorized = check.checkAuthorization(auth, role, new LocalStudy());
        assertFalse(authorized);

        List<String> roles = Arrays.asList("ae_reporter");
        EasyMock.expect(facade.getRoles("SYSTEM", o)).andReturn(roles).anyTimes();
        replayMocks();
        authorized = check.checkAuthorization(auth, role, o);
        assertTrue(authorized);
        authorized = check.checkAuthorization(auth, "lab_data_user", o);
        assertFalse(authorized);
    }

    public void testCheckAuthorizationForObjectId() throws Exception {
       assertSame(facade, check.getCaaersSecurityFacade());
       try{
           check.checkAuthorizationForObjectId(auth, "x", "y")  ;
           fail("must throw exception");
       } catch (UnsupportedOperationException u ){
           
       }
    }

    public void testCheckAuthorizationForObjectIds() throws Exception {
        try{
            check.checkAuthorizationForObjectIds(auth, "x", new String[]{"y"})  ;
            fail("must throw exception");
        } catch (UnsupportedOperationException u ){

        }
    }
}
