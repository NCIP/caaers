package gov.nih.nci.cabig.caaers.utils;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.acegi.csm.authentication.CSMAuthenticationProvider;
import gov.nih.nci.security.exceptions.CSLoginException;

import java.util.List;

import javax.xml.namespace.QName;

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

/**
 * This handler validates the username & password provided in the incoming soap message.
 * The incoming soap message must contain a header element as provided below
 * 	<soapenv:Header>
 *	    <wsse:Security>
 *	       <wsse:UsernameToken>
 *	         <wsse:Username>SYSTEM</wsse:Username> 
 *	         <wsse:Password>system_admin</wsse:Password> 
 *	       </wsse:UsernameToken>
 *	     </wsse:Security> 
 *	</soapenv:Header> 
 *   
 * @author Monish
 *
 */
public class ValidateUserTokenHandler extends AbstractHandler{
	
   private static final String  TOKEN_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";	
   private CSMAuthenticationProvider localAuthenticationProvider;
   private CSMUserRepository csmUserRepository;
   private static final Log logger = LogFactory.getLog(ValidateUserTokenHandler.class);
   
   
   public void invoke(MessageContext context) throws XFireFault{
	   
	   InMessage message = context.getInMessage();
	   Namespace ns = Namespace.getNamespace(TOKEN_NS);
	   
	   //Check if security header is present 
	   if (message.getHeader() == null){
		   logger.error("Request must include authentication token.");
	       throw new XFireFault("Request must include authentication token.",XFireFault.SENDER);
	   }
	   Element securityToken = message.getHeader().getChild("Security",ns);
	   if (securityToken == null){
		   logger.error("Request must include Security token.");
	       throw new XFireFault("Request must include Security token.",XFireFault.SENDER);
	   }
	   
	   //Extract Username & Password from Security Header
	   Element userNameToken = securityToken.getChild("UsernameToken",ns);
	   if (userNameToken == null){
		   logger.error("Security must include UsernameToken.");
	       throw new XFireFault("Security must include UsernameToken.",XFireFault.SENDER);
	   }
	   Element userNameElement = userNameToken.getChild("Username",ns);
	   Element passwordElement = userNameToken.getChild("Password",ns);
	   if (userNameElement == null || passwordElement == null){
		   logger.error("UsernameToken must include username and password.");
	       throw new XFireFault("UsernameToken must include username and password.",XFireFault.SENDER);
	   }       
	   String userName = userNameElement.getValue();
	   String password = passwordElement.getValue();
	   
	   //Validate Username & Password
	   AuthenticationManager authenticationManager = localAuthenticationProvider.getCsmAuthenticationManager();
	   try {
		   authenticationManager.login(userName, password);
	   }catch(CSLoginException e) {
		   logger.error("Invalid Username/Password.");
		   throw new XFireFault("Invalid Username/Password.", XFireFault.SENDER);
	   }catch(Exception e) {
		   logger.error(e.getMessage());
		   throw new XFireFault(e.getMessage(), XFireFault.SENDER);
	   } 
	   
	   List<UserGroupType> groups = csmUserRepository.getUserGroups(userName);
	   GrantedAuthority[] authorities = new GrantedAuthority[groups.size()];
	   int idx = 0;
	   for(UserGroupType group : groups) {
		   authorities[idx] = new GrantedAuthorityImpl(group.getSecurityRoleName());
		   idx = idx + 1;
	   }
	   // populate groups ... 
	   Authentication authentication = new TestingAuthenticationToken(userName, "ignored", authorities);
	   authentication.setAuthenticated(true);
	   SecurityContextHolder.getContext().setAuthentication(authentication);

    }
   
   public QName[] getUnderstoodHeaders() {
       return new QName[] {
               new QName(TOKEN_NS, "Security")
       };
   } 
   
   public void setLocalAuthenticationProvider(CSMAuthenticationProvider localAuthenticationProvider) {
		this.localAuthenticationProvider = localAuthenticationProvider;
   }

   public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
	   this.csmUserRepository = csmUserRepository;
   }
}
