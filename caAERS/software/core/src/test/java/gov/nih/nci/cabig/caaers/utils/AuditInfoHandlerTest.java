package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.codehaus.xfire.MessageContext;

public class AuditInfoHandlerTest extends CaaersTestCase {

	private AuditInfoHandler auditInfoHandler;
	private MessageContext messageContext;
	
	protected void setUp() throws Exception {
		super.setUp();
		auditInfoHandler = (AuditInfoHandler) getDeployedApplicationContext().getBean("auditInfoHandler");
		messageContext = new MessageContext();
		GrantedAuthority[] authorities = null;
		authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl(UserGroupType.caaers_super_user.getSecurityRoleName());
		Authentication authentication = new TestingAuthenticationToken("SYSTEM", "ignored", authorities);
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@SuppressWarnings("deprecation")
	public void testInvoke() throws Throwable {
		DataAuditInfo.setLocal(null);
		auditInfoHandler.invoke(messageContext);
		assertNotNull(DataAuditInfo.getLocal());
	}
	
}
