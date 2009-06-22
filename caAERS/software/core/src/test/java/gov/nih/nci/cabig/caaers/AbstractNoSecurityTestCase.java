package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;


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
