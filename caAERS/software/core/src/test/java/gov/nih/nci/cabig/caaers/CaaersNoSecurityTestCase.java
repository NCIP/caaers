/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
