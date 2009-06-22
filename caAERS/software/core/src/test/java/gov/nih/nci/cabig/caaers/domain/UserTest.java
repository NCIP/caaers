package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Jared Flatow
 */
public class UserTest extends CaaersTestCase {

    private User user;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        user = new LocalResearchStaff();
    }

    public void testAddPasswordToHistory() throws Exception {
        user.addPasswordToHistory("test", 1);
        user.addPasswordToHistory("test_b", 1);
        assertEquals(user.getPasswordHistory().size(), 1);

        user.addPasswordToHistory("test_b", 2);
        assertEquals(user.getPasswordHistory().size(), 2);
    }

    public void testGetLastFirst() throws Exception {
        assertEquals(user.getLastFirst(), "");
    }

    public void testGetFullName() throws Exception {
        assertEquals(user.getFullName(), "");
    }
}
