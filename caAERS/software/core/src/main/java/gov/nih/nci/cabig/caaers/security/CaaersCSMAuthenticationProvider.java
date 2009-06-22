package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.acegi.csm.authentication.CSMAuthenticationProvider;

import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CaaersCSMAuthenticationProvider extends CSMAuthenticationProvider{
	
	private static final Log logger = LogFactory.getLog(CaaersCSMAuthenticationProvider.class);
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails user, UsernamePasswordAuthenticationToken token) 
	throws AccountExpiredException{
		logger.debug((new StringBuilder()).append("Authenticating ").append(user.getUsername()).append("...").toString());
		if(!user.isAccountNonExpired()){
			throw new AccountExpiredException((new StringBuilder()).append("Error authenticating: User is InActive").toString());
		}
		super.additionalAuthenticationChecks(user, token);
	}
}
