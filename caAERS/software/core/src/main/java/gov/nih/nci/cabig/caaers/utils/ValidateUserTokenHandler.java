package gov.nih.nci.cabig.caaers.utils;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.acegi.csm.authentication.CSMAuthenticationProvider;
import gov.nih.nci.security.exceptions.CSLoginException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
   private static final Log logger = LogFactory.getLog(ValidateUserTokenHandler.class);
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
	       //LOGIN ....
			AuthenticationManager authenticationManager = localAuthenticationProvider.getCsmAuthenticationManager();
			try {
				authenticationManager.login(username.getValue(), password.getValue());
			} catch (CSLoginException e) {
				throw new XFireFault("Invalid username/password.", XFireFault.SENDER);
			} catch (Exception e) {
				throw new XFireFault(e.getMessage(), XFireFault.SENDER);
			} 
			//TODO handle super user in a generic way .. this is hardcoded as temp fix because authorities are not getting populated 
			GrantedAuthority[] authorities = null;
			if (username.getValue().equals("SYSTEM_ADMIN")) {
				authorities = new GrantedAuthority[1];
				authorities[0] = new GrantedAuthorityImpl(UserGroupType.caaers_super_user.getSecurityRoleName());
			} else {
			   // GET GROUPS ...
	    	   gov.nih.nci.cabig.caaers.domain.User dbUSer = getByUserName(username.getValue());
	    	   if (dbUSer == null) {
	    		   throw new XFireFault("User Does Not Exist", XFireFault.SENDER);
	    	   }
	    	   
				List<UserGroupType> groups = getUserGroups(dbUSer);
				authorities = new GrantedAuthority[groups.size()];
				int idx = 0;
	
				for (Iterator i = groups.iterator(); i.hasNext(); idx++) {
					UserGroupType group = (UserGroupType) i.next();
					authorities[idx] = new GrantedAuthorityImpl(group.getCsmName());
				}
			}

			// populate groups ... 
			Authentication authRequest = new TestingAuthenticationToken(username.getValue(), "ignored", authorities);//new UsernamePasswordAuthenticationToken(authorities);
			authRequest.setAuthenticated(true);
			SecurityContextHolder.getContext().setAuthentication(authRequest);

			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			DataAuditInfo oldAuditInfo = null;
			 try {

				 	if(authentication != null){
				 		String userName = authentication.getName();
				 		if(userName != null){
				 			oldAuditInfo = (DataAuditInfo) DataAuditInfo.getLocal();
				 			DataAuditInfo.setLocal(new DataAuditInfo(userName, "127.0.0.1", new Date(), Thread.currentThread().getName()));
				 		}
				 	}
				 	
			} catch (Throwable e) {
				logger.error("ValidateUserTokenHandler", e);
			}finally{
				DataAuditInfo.setLocal(oldAuditInfo);
			}
			
			
			
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
