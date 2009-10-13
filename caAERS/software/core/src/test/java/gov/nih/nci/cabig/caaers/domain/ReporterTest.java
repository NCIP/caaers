package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

/**
 * @author Biju Joseph
 */
public class ReporterTest extends AbstractTestCase {

    private Reporter reporter;
    private Address address;
    private String firstName;
    private String lastName;
    private String middleName;
    private ReportVersion reportVersion;
    private String title;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        reporter = new Reporter();
        address = new Address();
        address.setZip("20171");
        address.setCity("Herndon");
        address.setState("VA");
        address.setStreet("park center road");


        firstName = "first name";
        lastName = "last name";
        middleName = "middle name";

        reportVersion = new ReportVersion();
        reportVersion.setCcEmails("cc email");

        title = "title";
        reporter.setTitle(title);
        reporter.setVersion(2);
        reporter.setMiddleName(middleName);
        reporter.setLastName(lastName);
        reporter.setId(1);
        reporter.setGridId("grid id");
        reporter.setFirstName(firstName);
        reporter.setExpeditedReport(new ExpeditedAdverseEventReport());
        reporter.setAddress(address);
    }

    public void testCopyBasicProperties() {
        Reporter copiedReporter = reporter.copy();


        assertNull("must not coy id", copiedReporter.getId());

        assertNull("must not coy grid id", copiedReporter.getGridId());
        assertNull("must not coy version number", copiedReporter.getVersion());
        assertNull("must not coy expeditedReport", copiedReporter.getExpeditedReport());
        assertEquals(address, copiedReporter.getAddress());
        assertEquals(firstName, copiedReporter.getFirstName());
        assertEquals(lastName, copiedReporter.getLastName());
        assertEquals(middleName, copiedReporter.getMiddleName());
        assertEquals(title, copiedReporter.getTitle());


    }

    public void testCopy() {
    	ResearchStaff staff = new LocalResearchStaff();
    	staff.setFirstName("a");
    	staff.setEmailAddress("k");
    	reporter.copy(staff);
    	assertSame(reporter.getUser(), staff);
    	assertEquals("a", reporter.getFirstName());
    	assertNull(reporter.getLastName());
    	assertNull(reporter.getPhoneNumber());
    	assertEquals("k", reporter.getEmailAddress());
    }
}