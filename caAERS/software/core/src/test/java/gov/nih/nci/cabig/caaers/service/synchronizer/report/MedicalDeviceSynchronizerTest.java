package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class MedicalDeviceSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    MedicalDeviceSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new MedicalDeviceSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateNew() throws Exception {
        src.addMedicalDevice(Fixtures.createMedicalDevice(true, true));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getMedicalDevices().size());
    }


    public void testMigrateDelete() throws Exception {
        dest.addMedicalDevice(Fixtures.createMedicalDevice(true, true));
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getMedicalDevices().size());
    }


    public void testMigratEdit() throws Exception {
        StudyDevice sd = Fixtures.createStudyDevice();
        sd.setId(1);
        MedicalDevice md1 = Fixtures.createMedicalDevice(true, true);
        md1.setReprocessorName("tester");
        md1.setStudyDevice(sd);

        MedicalDevice md2 = Fixtures.createMedicalDevice(true, true);
        md2.setStudyDevice(sd);

        src.addMedicalDevice(md1);
        dest.addMedicalDevice(md2);

        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getMedicalDevices().size());
        assertSame(md2, dest.getMedicalDevices().get(0));
        assertEquals("tester",md2.getReprocessorName());
    }

}
