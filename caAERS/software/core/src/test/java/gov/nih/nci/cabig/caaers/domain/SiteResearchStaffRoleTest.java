package gov.nih.nci.cabig.caaers.domain;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class SiteResearchStaffRoleTest extends TestCase {
	SiteResearchStaffRole role;
	protected void setUp() throws Exception {
		super.setUp();
		role = new SiteResearchStaffRole();
	}

	public void testIsActive() {
		assertFalse(role.isActive());
		role.setStartDate(new Date());
		assertTrue(role.isActive());
		
	}
	
	public void testIsActive_Inactive(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -2);
		
		role.setEndDate(c.getTime());
		
		c.add(Calendar.MONTH,-1);
		role.setStartDate(c.getTime());
		assertFalse(role.isActive());
	}

	public void testIsInActive() {
		assertTrue(role.isInActive());
		role.setEndDate(new Date());
		assertTrue(role.isInActive());
		role.setStartDate(new Date());
		assertTrue(role.isInActive());
	}
	
	public void testIsActive_InactiveToday(){
		Calendar c = Calendar.getInstance();
		role.setEndDate(c.getTime());
		c.add(Calendar.MONTH,-1);
		role.setStartDate(c.getTime());
		assertFalse(role.isActive());
	}

}
