package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.web.security.DefaultObjectPrivilegeGenerator;
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
public class WebTabsAuthorizationCheckIntegrationTest extends CaaersTestCase {

    WebTabsAuthorizationCheck chk;

    public void setUp() throws Exception {
        super.setUp();


    }

    public void testBeanLoading(){

        chk = (WebTabsAuthorizationCheck)getDeployedApplicationContext().getBean("urlAuthorizationCheck");
        assertNotNull(chk);
    }
}