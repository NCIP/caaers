/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.List;

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
