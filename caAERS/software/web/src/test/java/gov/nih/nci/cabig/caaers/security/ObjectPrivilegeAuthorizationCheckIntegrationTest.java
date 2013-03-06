/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.security.authorization.ObjectPrivilegeAuthorizationCheck;

/**
 * ObjectPrivilegeAuthorizationCheck Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 *
 */
public class ObjectPrivilegeAuthorizationCheckIntegrationTest extends CaaersTestCase {

    ObjectPrivilegeAuthorizationCheck chk;

    public void setUp() throws Exception {
        super.setUp();


    }

    public void testBeanLoading(){

        chk = (ObjectPrivilegeAuthorizationCheck)getDeployedApplicationContext().getBean("urlAuthorizationCheck");
        assertNotNull(chk);
    }
}
