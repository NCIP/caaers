package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class PhysicianSynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    PhysicianSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new PhysicianSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrate() throws Exception {

        Investigator inv = Fixtures.createInvestigator("test");

        inv.setId(1);
        Physician r1 = new Physician();
        r1.setInvestigator(inv);
        r1.copy(inv);
        src.setPhysician(r1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertSame(r1, dest.getPhysician());
    }


    public void testMigrateWithIncomingPhysicianAndExistingPhysicianTheSame() throws Exception {

        Investigator inv = Fixtures.createInvestigator( "test");
        inv.setId(1);

        Investigator inv2 = Fixtures.createInvestigator( "test");
        inv2.setId(1);

        Physician r1 = new Physician();
        r1.setInvestigator(inv);
        r1.copy(inv);
        src.setPhysician(r1);

        Physician r2 = new Physician();
        r2.setInvestigator(inv2);
        dest.setPhysician(r2);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertSame(r2, dest.getPhysician());
        assertSame(r1, src.getPhysician());
    }
}
