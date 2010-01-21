package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.util.Date;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Will populate auditing information in current thread context.
 * @see DataAuditInfo
 * @author Biju Joseph
 *
 */
public class AuditInfoPopulatorInterceptor implements MethodInterceptor {
	
	private static final Log logger = LogFactory.getLog(AuditInfoPopulatorInterceptor.class);
	
	private String fromUrl;
	
	@SuppressWarnings("deprecation")
	public Object invoke(MethodInvocation method) throws Throwable{
		DataAuditInfo oldAuditInfo = null;
		 try {
			 
			 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 	if(authentication != null){
			 		String userName = SecurityUtils.getUserLoginName();
			 		if(userName != null){
			 			oldAuditInfo = (DataAuditInfo) DataAuditInfo.getLocal();
			 			String url = fromUrl == null ? Thread.currentThread().getName() : fromUrl;
			 			DataAuditInfo.setLocal(new DataAuditInfo(userName, "127.0.0.1", new Date(), url));
			 		}
			 	}
			 	
			 	return method.proceed();
			 	
		} catch (Throwable e) {
			logger.error("AuditInfoPopulatorInterceptor", e);
			throw e;
		}finally{
			DataAuditInfo.setLocal(oldAuditInfo);
		}
		//return null;
	}
	
	public String getFromUrl() {
		return fromUrl;
	}
	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}
}
