package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author Biju Joseph
 */
public class HttpSessionPurgeLogoutHandlerTest extends WebTestCase {


    HttpSessionPurgeLogoutHandler handler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        handler = new HttpSessionPurgeLogoutHandler();
    }

    public void testLogout() throws Exception{

        session.setAttribute("test", "hello");

        assertNotNull(session.getAttribute("test"));

        handler.logout(request, response, null);

        assertNull(session.getAttribute("hello"));

         for(Enumeration<Object> e = session.getAttributeNames(); e.hasMoreElements(); ){
            fail("session must not have " + e.nextElement().toString() );
         }


    }
}
