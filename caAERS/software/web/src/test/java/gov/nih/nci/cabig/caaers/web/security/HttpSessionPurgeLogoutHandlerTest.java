/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.tags.csm.AuthorizationDecisionCache;
import org.easymock.classextension.EasyMock;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Biju Joseph
 */
public class HttpSessionPurgeLogoutHandlerTest extends WebTestCase {


    HttpSessionPurgeLogoutHandler handler;
    AuthorizationDecisionCache cache;
    ApplicationContext  applicationContext;
    Map map = new HashMap();

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        handler = new HttpSessionPurgeLogoutHandler();


        applicationContext = EasyMock.createMock(ApplicationContext.class);
        cache = EasyMock.createMock(AuthorizationDecisionCache.class);
        handler.setApplicationContext(applicationContext);

        map.put("x", cache);


        
    }

    public void testLogout() throws Exception{
        String sessionId = session.getId();
        EasyMock.expect(applicationContext.getBeansOfType(AuthorizationDecisionCache.class,true,false)).andReturn(map);
        cache.clear(sessionId);
        EasyMock.expectLastCall().times(1);

        replayMocks();

        handler.logout(request, response, SecurityUtils.getAuthentication() );

        verifyMocks();
    }



    public void testLogoutWhenSessionAlreadyInvalidated() throws Exception{

        request.setSession(null);
        replayMocks();
        handler.logout(request, response, SecurityUtils.getAuthentication() );
        verifyMocks();
    }
}
