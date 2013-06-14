package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ReporterSynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    ReporterSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new ReporterSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }
    public void testMigrate() throws Exception {

        Organization org = Fixtures.createOrganization("test");
        ResearchStaff rs = Fixtures.createResearchStaff(org, null, "test");
        rs.setId(1);
        Reporter r1 = new Reporter();
        r1.setResearchStaff(rs);
        r1.copy(rs);
        src.setReporter(r1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertSame(r1, dest.getReporter());
    }
}
