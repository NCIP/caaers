package gov.nih.nci.cabig.caaers.web.selenium;


public class CaaersInvestigatorTest extends CaaersSeleniumTestCase {
	String firstName = "monica1";
	String lastName = "dubinsky1";

	public void testLogin() throws Exception {
		aw.login();
		assertTrue("Login Failure", true);
		assertTrue("Login Failure", selenium.isTextPresent("Regular Tasks"));
	}

	public void testCreateInvestigator() throws Exception {
		aw.login();
		createInvestigator(firstName, lastName);

		assertTrue("Create Investigator failure", selenium
				.isTextPresent("Successfully"));
	}

	public void testSearchInvestigator() throws Exception {
		aw.login();
		searchInvestigator(firstName, lastName);
		// assertTrue("Search Investigator failed for: " + firstName +" dfsdf "+
		// lastName,false);
		assertTrue("Search Investigator failed for: " + firstName + " "
				+ lastName, selenium.isTextPresent("regexpi:" + firstName)
				&& selenium.isTextPresent("regexpi:" + lastName));
	}
}
