package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class InvestigatorTest extends TestCase {
	
	private Investigator inv;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		inv = new  LocalInvestigator();
		inv.setEmailAddress("joel.biju@kk.com");
		inv.setFirstName("Joel");
		inv.setLastName("Joseph");
		
	}
	public void testHashCode() {
		int hashcode = inv.hashCode();
		assertNotNull(hashcode);
	}

	public void testEqualsObject() {
		Investigator inv2 = new  LocalInvestigator();
		inv2.setEmailAddress("joel.biju@kk.com");
		inv2.setFirstName("Joel");
		inv2.setLastName("Joseph");
		
		assertTrue(inv.equals(inv2));
		inv2.setEmailAddress("jank@rediffmail.com");
		assertFalse(inv.equals(inv2));
	}

}
