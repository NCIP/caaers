/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.report.ExpeditedReportMigrator;
import junit.framework.TestCase;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User: Biju Joseph
 * Date: 1/8/13
 */
public class ExpeditedReportMigratorTest extends TestCase {

    ExpeditedReportMigrator expeditedReportMigrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;

    public void setUp() throws Exception {
         expeditedReportMigrator = new ExpeditedReportMigrator();
         src = new ExpeditedAdverseEventReport();
         dest = new ExpeditedAdverseEventReport();
    }

    public void testPreMigrate() throws Exception {
        DomainObjectImportOutcome<ExpeditedAdverseEventReport> outCome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        expeditedReportMigrator.preMigrate(src, dest , outCome);
        assertNull(dest.getCreatedAt());
        assertNull(src.getCreatedAt());

        assertNull(dest.getInvestigationalDeviceAdministered());
        assertNull(src.getInvestigationalDeviceAdministered());

        src.setCreatedAt(new Timestamp(new Date().getTime()));
        expeditedReportMigrator.preMigrate(src, dest , outCome);
        assertSame(src.getCreatedAt(), dest.getCreatedAt());

        dest.setCreatedAt(new Timestamp(new Date().getTime()));
        expeditedReportMigrator.preMigrate(src, dest , outCome);
        assertNotNull(dest.getCreatedAt());
        assertNotSame(src.getCreatedAt(), dest.getCreatedAt());


    }
}
