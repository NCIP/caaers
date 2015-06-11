package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * User: Janakiram_G
 * Date: 6/10/15
 */
public class PasswordPolicyQueryTest extends TestCase {
    PasswordPolicyQuery passwordPolicyQuery;

    public void setUp() throws Exception {
        super.setUp();
        passwordPolicyQuery = new PasswordPolicyQuery();
    }

    public void testPasswordPolicyQuery() {
        assertEquals(passwordPolicyQuery.getQueryString(),"select distinct pp from PasswordPolicy pp");
    }
}
