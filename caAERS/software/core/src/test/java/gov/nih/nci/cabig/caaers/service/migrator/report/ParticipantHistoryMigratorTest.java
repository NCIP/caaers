package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory.Measure;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: medaV
 * Date: 1/18/13
 */
public class ParticipantHistoryMigratorTest extends TestCase {

    ParticipantHistoryMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;

    public void setUp() throws Exception {
        migrator = new ParticipantHistoryMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrate() throws Exception {
        migrator.migrate(src,dest, outcome);
        assertNotNull(src.getParticipantHistory());
        assertNotNull(dest.getParticipantHistory());
    }

    public void testMigrateWithValues() throws Exception {

        assertNotNull(src.getParticipantHistory());
        ParticipantHistory.Measure ht = new ParticipantHistory.Measure();
        ht.setQuantity(160.0);
        ht.setUnit("m");
        src.getParticipantHistory().setHeight(ht);
        
        migrator.migrate(src,dest, outcome);
        
        assertNotNull(dest.getParticipantHistory().getHeight());
    }
}
