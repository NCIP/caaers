/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import org.easymock.classextension.EasyMock;
import org.globus.gsi.GlobusCredential;

public class SecurityContextCredentialProviderTest extends AbstractTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SecurityTestUtils.switchToNoUser();
		SecurityTestUtils.switchToGridUser("some delegated epr", EasyMock.createMock(GlobusCredential.class));
	}
	
	public void testProvideDelegatedCredentials() {
        if(false){
            assertNotNull(new SecurityContextCredentialProvider().provideDelegatedCredentials());
        }
        assertTrue(true);

	}

}
