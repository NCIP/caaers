package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

/**
 * @author Biju Joseph
 */
public class PhysicanTest extends AbstractTestCase {

    private Physician physician;
    private Address address;
    private String firstName;
    private String lastName;
    private String middleName;
    private ReportVersion reportVersion;
    private String title;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        physician = new Physician();
        address = new Address();
        address.setZip(20171);
        address.setCity("Herndon");
        address.setState("VA");
        address.setStreet("park center road");


        firstName = "first name";
        lastName = "last name";
        middleName = "middle name";

        reportVersion = new ReportVersion();
        reportVersion.setCcEmails("cc email");

        title = "title";
        physician.setTitle(title);
        physician.setVersion(2);
        physician.setMiddleName(middleName);
        physician.setLastName(lastName);
        physician.setId(1);
        physician.setGridId("grid id");
        physician.setFirstName(firstName);
        physician.setExpeditedReport(new ExpeditedAdverseEventReport());
        physician.setAddress(address);
    }

    public void testCopyBasicProperties() {
        Physician copiedPhysician = physician.copy();


        assertNull("must not coy id", copiedPhysician.getId());

        assertNull("must not coy grid id", copiedPhysician.getGridId());
        assertNull("must not coy version number", copiedPhysician.getVersion());
        assertNull("must not coy expeditedReport", copiedPhysician.getExpeditedReport());
        assertEquals(address, copiedPhysician.getAddress());
        assertEquals(firstName, copiedPhysician.getFirstName());
        assertEquals(lastName, copiedPhysician.getLastName());
        assertEquals(middleName, copiedPhysician.getMiddleName());
        assertEquals(title, copiedPhysician.getTitle());


    }

    public void testCopyContactMechanism() {
    	physician.setPhoneNumber("1");
    	physician.setEmailAddress("2");
    	physician.setFax("3");

        Physician copiedPhysician = physician.copy();

        assertEquals(3, copiedPhysician.getContactMechanisms().keySet().size());

        assertEquals("3", copiedPhysician.getContactMechanisms().get("fax"));

    }
}
