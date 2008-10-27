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
 * Similar to CaaersTestCase, but will stub-out the AspectJSecurityAspect
 * @author Biju Joseph
 *
 */
public class AbstractNoSecurityTestCase extends CaaersTestCase {
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SecurityTestUtils.enableAuthorization(false, applicationContext);
	}
	
}
