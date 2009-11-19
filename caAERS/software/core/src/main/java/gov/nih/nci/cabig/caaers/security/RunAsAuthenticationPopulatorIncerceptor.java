package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Will populate the authentication context
 * @see RunAsAuthenticationPopulator
 * @author Biju Joseph
 *
 */
public class RunAsAuthenticationPopulatorIncerceptor implements MethodInterceptor {
	
	private static final Log logger = LogFactory.getLog(RunAsAuthenticationPopulatorIncerceptor.class);

	private RunAsAuthenticationPopulator runAsAuthenticationPopulator;
	
	public Object invoke(MethodInvocation method) throws Throwable{
		
		Authentication existingAuthentication = SecurityContextHolder.getContext().getAuthentication();
		
		try{

			runAsAuthenticationPopulator.populate();
			return method.proceed();
			
		}catch(Throwable e){
			logger.error("RunAsAuthenticationPopulatorIncerceptor", e);
			throw e;
		}finally{
			SecurityContextHolder.getContext().setAuthentication(existingAuthentication);
		}
		
		//return null;
	}
	
	@Required
	public void setRunAsAuthenticationPopulator(
			RunAsAuthenticationPopulator runAsAuthenticationPopulator) {
		this.runAsAuthenticationPopulator = runAsAuthenticationPopulator;
	}
	
}
