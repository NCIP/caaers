package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.web.security.DefaultObjectPrivilegeGenerator;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.acegisecurity.Authentication;
import org.easymock.classextension.EasyMock;

import java.util.HashMap;

/**
 * WebTabsAuthorizationCheck Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class WebTabsAuthorizationCheckTest extends AbstractTestCase {

    WebTabsAuthorizationCheck chk;
    DefaultObjectPrivilegeGenerator gen;
    CaaersSecurityFacade facade;

    public void setUp() throws Exception {
        super.setUp();

        chk = new WebTabsAuthorizationCheck();
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new DefaultObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);

        facade = registerMockFor(CaaersSecurityFacade.class);
        chk.setCaaersSecurityFacade(facade);
        chk.setObjectPrivilegeGenerator(gen);

    }


    public void testCheckAuthorization() {

        Authentication auth = SecurityUtils.getAuthentication();

        EasyMock.expect(facade.checkAuthorization(auth, "y")).andReturn(true);
        replayMocks();

        Object o = "/x";

        boolean b =  chk.checkAuthorization(auth, "/x", o);
        assertTrue(b);
        verifyMocks();
    }



    public void testCheckAuthorizationForObjectId() {

        Authentication auth = SecurityUtils.getAuthentication();

        EasyMock.expect(facade.checkAuthorization(auth,"y", "READ")).andReturn(true);
        replayMocks();


        boolean b =  chk.checkAuthorizationForObjectId(auth, "READ", "y");
        assertTrue(b);
        verifyMocks();
    }


    public void testCheckAuthorizationForObjectIds() {

        Authentication auth = SecurityUtils.getAuthentication();

        EasyMock.expect(facade.checkAuthorization(auth,"y", "READ")).andReturn(true);
        replayMocks();


        boolean b =  chk.checkAuthorizationForObjectIds(auth, "READ", new String[]{"y"});
        assertTrue(b);
        verifyMocks();
    }
}
