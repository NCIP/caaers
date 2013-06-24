package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class DiseaseHistorySynchronizerTest extends AbstractTestCase {
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    DiseaseHistorySynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new DiseaseHistorySynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrate() throws Exception {

        DiseaseHistory r1 = Fixtures.createDiseaseHistory();
        src.setDiseaseHistory(r1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertEquals("Other primary", dest.getDiseaseHistory().getOtherPrimaryDisease());
    }


    public void testMigrateUnSet() throws Exception {

        DiseaseHistory r1 = Fixtures.createDiseaseHistory();
        dest.setDiseaseHistory(r1);

        synchronizer.migrate(src, dest, outcome);

        assertFalse(outcome.hasErrors());
        assertNull(dest.getDiseaseHistory().getOtherPrimaryDisease());
    }
}
