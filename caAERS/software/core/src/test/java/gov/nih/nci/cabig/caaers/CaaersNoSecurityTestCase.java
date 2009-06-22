package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

public class CaaersNoSecurityTestCase extends CaaersTestCase{
	
	@Override
	protected void setUp() throws Exception {
		// change the security interceptor with stub.
		super.setUp();
		SecurityTestUtils.enableAuthorization(false, applicationContext);
	}

}
