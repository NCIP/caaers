package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medaV
 * Date: 1/23/13
 */
public class LabMigratorTest extends TestCase {

    private MedicalDeviceMigrator migrator;
    
    private ExpeditedAdverseEventReport src;
    
    private ExpeditedAdverseEventReport dest;
    
    private DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    

    public void setUp() throws Exception {
        migrator = new MedicalDeviceMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrate() throws Exception {
    	MedicalDevice device = Fixtures.createMedicalDevice(true, false);
    	src.getMedicalDevices().add(device);
        migrator.migrate(src,dest, outcome);
        
        assertEquals(device.getDeviceReprocessed().getDisplayName(), "Yes");
    }
    
    public void testMigrateWithValues() throws Exception {
    	
    	 MedicalDevice medicalDevice = new MedicalDevice(Fixtures.createStudyDevice());

         String brandName = "brand name";
         medicalDevice.getStudyDevice().getDevice().setBrandName(brandName);
         String catalogNumber = "catalong number";
         medicalDevice.getStudyDevice().setCatalogNumber(catalogNumber);
         String commonName = "common name";
         medicalDevice.getStudyDevice().getDevice().setCommonName(commonName);
         DeviceOperator other = DeviceOperator.OTHER;
         medicalDevice.setDeviceOperator(other);
         Availability returned = Availability.RETURNED;

         medicalDevice.setDeviceReprocessed(ReprocessedDevice.YES);
         String deviceType = "device type";
         medicalDevice.getStudyDevice().getDevice().setType(deviceType);
         Availability evaluationAvailability = Availability.YES;
         
         
         migrator.migrate(src,dest, outcome);
         
         assertEquals(evaluationAvailability.getDisplayName(), "Yes");
         assertEquals(returned.getDisplayName(), "Returned");
         assertEquals(medicalDevice.getDeviceOperator().getDisplayName(), "Other");
         
    }

}
