/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Biju Joseph
 */
public class AuditInfoPopulatorWSInterceptorTest extends AbstractTestCase {
    Message m;
    MockHttpServletRequest request;
    AuditInfoPopulatorWSInterceptor interceptor;

    public void  setUp(){
        SecurityTestUtils.switchToSuperuser();
       m = registerMockFor(Message.class);
        request = new MockHttpServletRequest("GET", "hello/audit");
        request.setRemoteAddr("11.11.11.11");
        request.setServletPath("hello/audit");
        m = new MessageImpl(){
            @Override
            public Object get(Object key) {
                return request;
            }
        };
        interceptor = new AuditInfoPopulatorWSInterceptor();
    }


    public void testHandleMessage() throws Exception {
        SecurityTestUtils.switchUser("wsclient", "test");    //flipping the username
        interceptor.handleMessage(m);
        DataAuditInfo i = (DataAuditInfo)DataAuditInfo.getLocal();
        assertEquals("wsclient", i.getUsername());
        assertEquals("hello/audit", i.getUrl());
        assertEquals("11.11.11.11", i.getIp());

    }

    public void testHandleMessageNoLogin() throws Exception {

        interceptor.handleMessage(m);
        DataAuditInfo i = (DataAuditInfo)DataAuditInfo.getLocal();
        assertEquals("SYSTEM", i.getUsername());
        assertEquals("hello/audit", i.getUrl());
        assertEquals("11.11.11.11", i.getIp());

    }

}
