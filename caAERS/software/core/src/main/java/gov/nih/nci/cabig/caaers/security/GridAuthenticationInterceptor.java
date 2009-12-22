package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.UserDetails;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.globus.wsrf.security.SecurityManager;
import org.springframework.beans.factory.annotation.Required;

public class GridAuthenticationInterceptor implements MethodInterceptor {
	
	private static final Log logger = LogFactory.getLog(GridAuthenticationInterceptor.class);


	private CaaersCSMUserDetailsService csmUserDetailsService;
	
	public Object invoke(MethodInvocation method) throws Throwable{
		

		String gridIdentity = SecurityManager.getManager().getCaller();
		
		String userName = gridIdentity.substring(gridIdentity.indexOf("/CN=")+4, gridIdentity.length());
		UserDetails userDetails = csmUserDetailsService.loadUserByUsername(userName);

		try{

			populate(userName,userDetails.getAuthorities());
			return method.proceed();
			
		}catch(Throwable e){
			logger.error("RunAsAuthenticationPopulatorIncerceptor", e);
			throw e;
		}finally{
			SecurityContextHolder.getContext().setAuthentication(SecurityContextHolder.getContext().getAuthentication());
		}
		
		//return null;
	}
	
	private void populate(String username,GrantedAuthority[] auths) {
		String authStrings = "";
		for (GrantedAuthority auth : auths) {
            System.out.println(authStrings +  auth.getAuthority() + " , ");
        }
		
        logger.debug("populating authentication [userName : " + username + ", authorities : " + authStrings + "]");

        Authentication auth = new UsernamePasswordAuthenticationToken(username, "ignoreme", auths);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
	
	@Required
	public void setCsmUserDetailsService(
			CaaersCSMUserDetailsService csmUserDetailsService) {
		this.csmUserDetailsService = csmUserDetailsService;
	}	
}
