/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.easymock.EasyMock;

/**
 * @author: Biju Joseph
 */
public class RoleCheckTest extends AbstractTestCase {
    public void setUp() throws Exception {

    }

    public void testCheckAuthorizationForObjectId() throws Exception {
        RoleCheck rc = new RoleCheck();
        GrantedAuthority ga1 = registerMockFor(GrantedAuthority.class);
        EasyMock.expect(ga1.getAuthority()).andReturn("r1").anyTimes();

        replayMocks();
        Authentication a =  SecurityUtils.createAuthentication("x", "y", ga1);
        boolean authorized =  rc.checkAuthorizationForObjectId(a, "r1",  null);
        assertTrue(authorized);
        authorized =  rc.checkAuthorizationForObjectId(a, "rz1,rc2",  null);
        assertFalse(authorized);
        verifyMocks();

    }
}
