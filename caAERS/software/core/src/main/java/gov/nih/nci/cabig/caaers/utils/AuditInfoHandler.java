package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.util.Date;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.AbstractHandler;
import org.codehaus.xfire.transport.http.XFireServletController;

/**
 * Will populate auditing information in current thread context.
 * @see DataAuditInfo
 * @author Monish
 *
 */
public class AuditInfoHandler extends AbstractHandler{

	private static final Log logger = LogFactory.getLog(AuditInfoHandler.class);
	
	@SuppressWarnings("deprecation")
	public void invoke(MessageContext messageContext)  throws XFireFault {
		String remoteAddr = getRemoteAdress();
		 try {
			 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 	if(authentication != null){
			 		String userName = SecurityUtils.getUserLoginName();
			 		if(userName != null){
			 			String url = getFromUrl();
			 			DataAuditInfo.setLocal(new DataAuditInfo(userName, remoteAddr, new Date(), url));
			 		}
			 	}
		} catch (Throwable e) {
			logger.error("AuditInfoHandler", e);
		}
	}
	
	public String getFromUrl() {
		String fromUrl = Thread.currentThread().getName();
		if(XFireServletController.getRequest() != null){
			fromUrl = XFireServletController.getRequest().getRequestURI();
		}
		return fromUrl;
	}
	
	public String getRemoteAdress(){
		String remoteAddr = "127.0.0.1";
		if(XFireServletController.getRequest() != null){
			remoteAddr = XFireServletController.getRequest().getRemoteAddr();
		}
		return remoteAddr;
	}

}
