package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class AdverseEventResponseDescriptionSynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    AdverseEventResponseDescriptionSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new AdverseEventResponseDescriptionSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrate() throws Exception {

        AdverseEventResponseDescription r1 = Fixtures.createAdverseEventResponseDescription();
        src.setResponseDescription(r1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertEquals(r1.getEventDescription(), dest.getResponseDescription().getEventDescription());
    }


    public void testMigrateUnSet() throws Exception {

        AdverseEventResponseDescription r1 = Fixtures.createAdverseEventResponseDescription();
        dest.setResponseDescription(r1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertNull(dest.getResponseDescription().getEventDescription());
    }
}
