package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * This class is used instead of SeleneseTestCase because SeleneseTestCase has a bug
 * in it and throws an Access Denied error for the proxy.
 *
 * @author Biju Joseph
 */
public abstract class AbstractSeleniumTestCase extends AbstractTestCase {

    DefaultSelenium selenium;
    String waitTime = "50000";

    protected String NCI_CODE;
    protected final Logger logger = Logger.getLogger(getClass());


    protected DefaultSelenium createSeleniumClient(String url) throws Exception {
        return new DefaultSelenium("localhost", 4444, "*chrome", url);
    }

    public void testSystemAdminLogin() {

        selenium.open("/public/login");
        loginAdmin();
        assertEquals("caAERS || Welcome to caAERS", selenium.getTitle());

    }

    public void testCreateOrganiation() {

        createOrganization("Org name", NCI_CODE);
        validateOrganizationCreation("Org name", NCI_CODE);


    }

    public void testCreateOrganiationForExistingIdentifier() {

        createOrganization("Org name", NCI_CODE);
        validateOrganizationCreation("Org name", NCI_CODE);

        //now create the same organization again

        createOrganization("another org name", NCI_CODE);

        assertTrue("two organizations must not have same identifier", selenium.isTextPresent("Nci Identifier already exits in the datbase"));

    }

    protected void createOrganization(String orgName, String nciCode) {
        openPage("/admin/createOrganization");
        selenium.type("name", orgName);
        selenium.type("nciInstituteCode", nciCode);
        selenium.type("descriptionText", "desc");
        selenium.click("flow-next");
        selenium.waitForPageToLoad(waitTime);

    }

    protected void openPage(String pageUrl) {
        selenium.open("https://localhost:8443/caaers/pages" + pageUrl);
        if (selenium.isTextPresent("Enter caAERS")) {
            loginAdmin();
        }
    }

    protected void validateOrganizationCreation(String orgName, String nciCode) {
        selenium.isTextPresent("Confirmation");
        selenium.isTextPresent("You have successfully created Organization with");
        selenium.isTextPresent(orgName);
        selenium.isTextPresent("desc");
        selenium.isTextPresent(nciCode);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
        NCI_CODE = "NCI" + getRandonDigit();
        selenium = createSeleniumClient("https://localhost:8443/caaers");
        selenium.start();

    }

    protected String getRandonDigit() {
        String name = "" + Calendar.getInstance().getTime().getTime();
        name = name.substring(name.length() - 5, name.length() - 1);
        return name;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
        // selenium.stop();

    }


    public void login() {
        login("priuser", "eauditnet");
    }

    public void loginAdmin() {
        login("SYSTEM_ADMIN", "system_admin");
    }

    public void login(String userId, String password) {
        selenium.type("j_username", userId);
        selenium.type("j_password", password);

        selenium.submit("login");
        selenium.waitForPageToLoad(waitTime);
    }
}
