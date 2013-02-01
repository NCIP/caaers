package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
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
        expeditedReportMigrator.preMigrate(src, dest , null);
        assertNull(dest.getCreatedAt());
        assertNull(src.getCreatedAt());

        assertNull(dest.getInvestigationalDeviceAdministered());
        assertNull(src.getInvestigationalDeviceAdministered());

        src.setCreatedAt(new Timestamp(new Date().getTime()));
        expeditedReportMigrator.preMigrate(src, dest , null);
        assertSame(src.getCreatedAt(), dest.getCreatedAt());

        dest.setCreatedAt(new Timestamp(new Date().getTime()));
        expeditedReportMigrator.preMigrate(src, dest , null);
        assertNotNull(dest.getCreatedAt());
        assertNotSame(src.getCreatedAt(), dest.getCreatedAt());


    }
}
