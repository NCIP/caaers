/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.audit.AuditUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;
import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * Will be configured to kick-in before invoking a WebMethod.
 * The primary purpouse of this interceptor is to populate Audting Information in
 * current Thread.
 */
public class AuditInfoPopulatorWSInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Log logger = LogFactory.getLog(AuditInfoPopulatorWSInterceptor.class);

    public AuditInfoPopulatorWSInterceptor(){
        super(Phase.PRE_INVOKE);
    }
    public void handleMessage(Message message) throws Fault {
        if(logger.isDebugEnabled()) logger.debug("About to populate the auditing information");
        Authentication authz = SecurityUtils.getAuthentication();

        String userName = "SYSTEM";
        if(authz != null) {
             userName = SecurityUtils.getUserLoginName(authz);
        }
        String ipAddress = "127.0.0.1";
        String url = "webservice";
        HttpServletRequest request = (HttpServletRequest) message.get("HTTP.REQUEST");
        if(request != null){
           ipAddress =  request.getRemoteAddr();
            url = request.getServletPath();
        }
        DataAuditInfo newAuditInfo = AuditUtils.generateDataAuditInfo(userName, ipAddress, url);
        DataAuditInfo existing = (DataAuditInfo)DataAuditInfo.getLocal();
        DataAuditInfo.setLocal(newAuditInfo);
        if(logger.isDebugEnabled()) logger.debug("Associated to thread AuditInfo  " + newAuditInfo.toString());
    }
}
