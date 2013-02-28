/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class OrganizationHeldINDTest extends TestCase {
    public void testIsNciAffiliate() throws Exception {
        INDHolder oind = Fixtures.createOrganizationINDHolder(Fixtures.createOrganization("CIP", "CIP"));
        assertTrue(oind.isNciAffiliate());
        oind = Fixtures.createOrganizationINDHolder(Fixtures.createOrganization("CIPx", "CIPx"));
        assertFalse(oind.isNciAffiliate());
    }
}
