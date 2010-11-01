package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.authorization.ObjectPrivilegeAuthorizationCheck;
import gov.nih.nci.cabig.caaers.web.security.DefaultObjectPrivilegeGenerator;
import org.acegisecurity.Authentication;
import org.easymock.IArgumentMatcher;
import org.easymock.classextension.EasyMock;

import java.util.HashMap;

/**
 * ObjectPrivilegeAuthorizationCheck Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class ObjectPrivilegeAuthorizationCheckTest extends AbstractTestCase {

    ObjectPrivilegeAuthorizationCheck chk;
    DefaultObjectPrivilegeGenerator gen;
    CaaersSecurityFacade facade;

    public void setUp() throws Exception {
        super.setUp();

        chk = new ObjectPrivilegeAuthorizationCheck();
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new DefaultObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);

        facade = registerMockFor(CaaersSecurityFacade.class);
        SecurityTestUtils.switchToCaaersSecurityFacadeMock(facade);
        chk.setCaaersSecurityFacade(facade);
        chk.setObjectPrivilegeGenerator(gen);

    }




    public void testCheckAuthorization() {

        final Authentication auth = SecurityUtils.getAuthentication();

        EasyMock.expect(facade.checkAuthorization((Authentication)EasyMock.anyObject(), "y")).andReturn(true);
        replayMocks();

        Object o = "/x";

        boolean b =  chk.checkAuthorization(auth, "/x", o);
        assertTrue(b);
        verifyMocks();
    }



    public void testCheckAuthorizationForObjectId() {

        Authentication auth = SecurityUtils.getAuthentication();

        EasyMock.expect(facade.checkAuthorization((Authentication)EasyMock.anyObject(),"y", "READ")).andReturn(true);
        replayMocks();


        boolean b =  chk.checkAuthorizationForObjectId(auth, "READ", "y");
        assertTrue(b);
        verifyMocks();
    }


    public void testCheckAuthorizationForObjectIds() {

        Authentication auth = SecurityUtils.getAuthentication();

        EasyMock.expect(facade.checkAuthorization((Authentication)EasyMock.anyObject(),"y", "READ")).andReturn(true);
        replayMocks();


        boolean b =  chk.checkAuthorizationForObjectIds(auth, "READ", new String[]{"y"});
        assertTrue(b);
        verifyMocks();
    }

}
