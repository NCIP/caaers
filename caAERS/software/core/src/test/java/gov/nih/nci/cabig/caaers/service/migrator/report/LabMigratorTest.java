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

    private LabMigrator migrator;
    
    private ExpeditedAdverseEventReport src;
    
    private ExpeditedAdverseEventReport dest;
    
    private DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    

    public void setUp() throws Exception {
        migrator = new LabMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrateWithValues() throws Exception {
       // To be done.
    }

}
