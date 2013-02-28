/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.authorization.ObjectPrivilegeAuthorizationCheck;
import gov.nih.nci.cabig.caaers.web.security.DefaultObjectPrivilegeGenerator;
import org.acegisecurity.Authentication;
import org.easymock.IArgumentMatcher;
import org.easymock.classextension.EasyMock;

import java.util.HashMap;

/**
 * ObjectPrivilegeAuthorizationCheck Tester...
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre> 
 * 
 */
public class ObjectPrivilegeAuthorizationCheckTest extends AbstractTestCase {

    ObjectPrivilegeAuthorizationCheck chk;
    DefaultObjectPrivilegeGenerator gen;
//    CaaersSecurityFacade facade;

    public void setUp() throws Exception {
        super.setUp();

        chk = new ObjectPrivilegeAuthorizationCheck();
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new DefaultObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);
        SecurityTestUtils.switchToCaaersSecurityFacadeMock(null);
        chk.setCaaersSecurityFacade(CaaersSecurityFacadeImpl.getInstance());
        chk.setObjectPrivilegeGenerator(gen);

    }

    @Override
    protected void tearDown() throws Exception {
        SecurityTestUtils.switchToCaaersSecurityFacade();
        super.tearDown();
    }

    public void testCheckAuthorization() {

        final Authentication auth = SecurityUtils.getAuthentication();
        Object o = "/x";
        boolean b =  chk.checkAuthorization(auth, "/x", o);
        assertTrue(b);
        o = "k";
        b = chk.checkAuthorization(auth, null, o);
        assertFalse(b);
    }



    public void testCheckAuthorizationForObjectId() {

        Authentication auth = SecurityUtils.getAuthentication();

        boolean b =  chk.checkAuthorizationForObjectId(auth, "READ", "y");
        assertTrue(b);
        b =  chk.checkAuthorizationForObjectId(auth, null, null);
        assertFalse(b);
    }


    public void testCheckAuthorizationForObjectIds() {

        Authentication auth = SecurityUtils.getAuthentication();

        boolean b =  chk.checkAuthorizationForObjectIds(auth, "READ", new String[]{"y"});
        assertTrue(b);
        b =  chk.checkAuthorizationForObjectIds(auth, null, new String[]{null});
        assertFalse(b);
    }

}
