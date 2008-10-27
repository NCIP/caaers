package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.afterinvocation.AfterInvocationProvider;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CaaersDbNoSecurityTestCase extends CaaersDbTestCase {
	protected static AfterInvocationProvider stubProvider;
	protected static List<AfterInvocationProvider> stubProviders = new ArrayList<AfterInvocationProvider>();
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SecurityTestUtils.enableAuthorization(false, applicationContext);
	}
}
