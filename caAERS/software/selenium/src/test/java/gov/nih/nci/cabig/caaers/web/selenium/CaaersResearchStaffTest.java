/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.selenium;


public class CaaersResearchStaffTest extends CaaersSeleniumTestCase {
	String firstName = "dilbert2";
	String lastName = "jones2";

	
	public void testCreateResearchStaff() throws Exception {
		aw.login();
		createResearchStaff(firstName, lastName);

		assertTrue("Create Research Staff failure", selenium
				.isTextPresent("Successfully"));
	}

	public void testSearchResearchStaff() throws Exception {
		aw.login();
		searchResearchStaff(firstName, lastName);
		// assertTrue("Search Investigator failed for: " + firstName +" dfsdf "+
		// lastName,false);
		assertTrue("Search Research Staff failed for: " + firstName + " "
				+ lastName, selenium.isTextPresent("regexpi:" + firstName)
				&& selenium.isTextPresent("regexpi:" + lastName));
	}
}
