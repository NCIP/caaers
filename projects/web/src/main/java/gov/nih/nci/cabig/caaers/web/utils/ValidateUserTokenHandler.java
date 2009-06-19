package gov.nih.nci.cabig.caaers.web.utils;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.acegi.csm.authentication.CSMAuthenticationProvider;
import gov.nih.nci.security.exceptions.CSLoginException;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.exchange.InMessage;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;
import org.jdom.Namespace;


public class ValidateUserTokenHandler
    extends AbstractHandler
{
	
   private static final String  TOKEN_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";	
   private CSMAuthenticationProvider localAuthenticationProvider;
   private CSMUserRepository csmUserRepository;
   
   public void invoke(MessageContext context)
        throws XFireFault
    {
	   InMessage message = context.getInMessage();
	   Namespace ns = Namespace.getNamespace(TOKEN_NS);
	   
	   if (message.getHeader() == null)
       {
           throw new XFireFault("Request must include authentication token.",
                                XFireFault.SENDER);
       }
	   Element securityToken = message.getHeader().getChild("Security",ns);
       if (securityToken == null)
       {
           throw new XFireFault("Request must include Security token.",
                                XFireFault.SENDER);
       }
       
       Element userNameToken = securityToken.getChild("UsernameToken",ns);

       if (userNameToken == null)
       {
           throw new XFireFault("Security must include UsernameToken.",
                                XFireFault.SENDER);
       }
       
       Element username = userNameToken.getChild("Username",ns);
       Element password = userNameToken.getChild("Password",ns);
       
       if (username == null || password == null)
       {
           throw new XFireFault("UsernameToken must include username and password.",
                                XFireFault.SENDER);
       }       
   	   
    	   gov.nih.nci.cabig.caaers.domain.User dbUSer = getByUserName(username.getValue());
    	   if (dbUSer == null) {
    		   throw new XFireFault("User Does Not Exist", XFireFault.SENDER);
    	   }
    	   
			List<UserGroupType> groups = getUserGroups(dbUSer);
			GrantedAuthority[] authorities = new GrantedAuthority[groups.size()];
			int idx = 0;

			for (Iterator i = groups.iterator(); i.hasNext(); idx++) {
				UserGroupType group = (UserGroupType) i.next();
				authorities[idx] = new GrantedAuthorityImpl(group.getCsmName());
			}

			AuthenticationManager authenticationManager = localAuthenticationProvider.getCsmAuthenticationManager();
			try {
				authenticationManager.login(username.getValue(), password.getValue());
			} catch (CSLoginException e) {
				throw new XFireFault("Invalid username/password.", XFireFault.SENDER);
			} catch (Exception e) {
				throw new XFireFault(e.getMessage(), XFireFault.SENDER);
			} 
			Authentication authRequest = new TestingAuthenticationToken(username.getValue(), "ignored", authorities);//new UsernamePasswordAuthenticationToken(authorities);
			authRequest.setAuthenticated(true);
			SecurityContextHolder.getContext().setAuthentication(authRequest);
			
/*			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			GrantedAuthority[] grantedAuthorities = SecurityUtils.getGrantedAuthorities(authentication);
			System.out.println("**** ga****");
			for (int i=0; i<grantedAuthorities.length; i++) {
	        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
	        	System.out.println(grantedAuthority.toString());
	        }
			System.out.println("**** ga****");*/


    }
   
   private gov.nih.nci.cabig.caaers.domain.User getByUserName(String userName) {
	   return csmUserRepository.getUserByName(userName);
   }
   public List<UserGroupType> getUserGroups(gov.nih.nci.cabig.caaers.domain.User user) {
		return user.getUserGroupTypes();
	}
   
	public void setLocalAuthenticationProvider(
			CSMAuthenticationProvider localAuthenticationProvider) {
		this.localAuthenticationProvider = localAuthenticationProvider;
	}


	public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}
}
//  END SNIPPET: secresult
