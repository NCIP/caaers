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
