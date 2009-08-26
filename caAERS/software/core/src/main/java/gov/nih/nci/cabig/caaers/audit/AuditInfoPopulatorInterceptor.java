package gov.nih.nci.cabig.caaers.audit;

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
	
	
	@SuppressWarnings("deprecation")
	public Object invoke(MethodInvocation method) {
		DataAuditInfo oldAuditInfo = null;
		 try {
			 
			 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 	if(authentication != null){
			 		String userName = authentication.getName();
			 		if(userName != null){
			 			oldAuditInfo = (DataAuditInfo) DataAuditInfo.getLocal();
			 			DataAuditInfo.setLocal(new DataAuditInfo(userName, "127.0.0.1", new Date(), Thread.currentThread().getName()));
			 		}
			 	}
			 	
			 	return method.proceed();
			 	
		} catch (Throwable e) {
			logger.error("AuditInfoPopulatorInterceptor", e);
		}finally{
			DataAuditInfo.setLocal(oldAuditInfo);
		}
		return null;
	}

}
