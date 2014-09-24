/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;

import java.util.Map;

public class IncomingCredentialExtractingInterceptor extends WSS4JOutInterceptor {
    private String caaersWSUser;
    private String caaersWSPassword;

    protected static final Log log = LogFactory.getLog(IncomingCredentialExtractingInterceptor.class);

    public IncomingCredentialExtractingInterceptor(Map<String, Object> props) {
        super();
        if(!props.containsKey("user")) props.put("user", "dummyUser");

        setProperties(props);
    }

    @Override
    public void handleMessage(SoapMessage mc) throws Fault {
        //fetch the credentials
        String body = String.valueOf(mc.getExchange());
        if(StringUtils.isEmpty(body)) return ;

        int start = body.indexOf("Username>") ;
        if(start < 0) {
            log.error(String.format("Unable to obtain username, start index %s\n message body :%s", String.valueOf(start), body));
            return;
        }
        start+= 9;
        int end = body.indexOf("</", start);
        if(end < start) {
            log.error(String.format("Unable to obtain username, end index %s\n message body :%s", String.valueOf(end), body));
            return;
        }
        caaersWSUser = body.substring(start,end);


        start = body.indexOf("Password", end);
        start = start > end ?  body.indexOf(">", start) : start;
        if(start < 0) {
            log.error(String.format("Unable to obtain password, start index %s\n message body :%s", String.valueOf(start), body));
            return;
        }
        end = body.indexOf("</", start);
        if(end < start) {
            log.error(String.format("Unable to obtain password, end index %s\n message body :%s", String.valueOf(end), body));
            return;
        }
        caaersWSPassword = body.substring(start+1,end);

        super.handleMessage(mc);
    }

    @Override
    public WSPasswordCallback getPassword(String username, int doAction, String clsProp, String refProp, RequestData reqData) throws WSSecurityException {
        return new WSPasswordCallback(caaersWSUser, caaersWSPassword, null ,WSPasswordCallback.USERNAME_TOKEN);
    }

    public String getCaaersWSUser() {
        return caaersWSUser;
    }

    public void setCaaersWSUser(String caaersWSUser) {
        this.caaersWSUser = caaersWSUser;
    }

    public String getCaaersWSPassword() {
        return caaersWSPassword;
    }

    public void setCaaersWSPassword(String caaersWSPassword) {
        this.caaersWSPassword = caaersWSPassword;
    }
}