/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import junit.framework.TestCase;

/**
 * User: Biju Joseph
 * Date: 1/8/13
 */
public class AdverseEventResponseDescriptionMigratorTest extends TestCase {

    AdverseEventResponseDescriptionMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;

    public void setUp() throws Exception {
        migrator = new AdverseEventResponseDescriptionMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }

    public void testMigrate() throws Exception {
        migrator.migrate(src,dest, outcome);
        assertNotNull(src.getResponseDescription());
        assertNotNull(dest.getResponseDescription());
        assertNull(dest.getResponseDescription().getEventDescription());
    }

    public void testMigrateWithValues() throws Exception {

        assertNotNull(src.getResponseDescription());
        src.getResponseDescription().setCauseOfDeath("some cause");
        migrator.migrate(src,dest, outcome);
        assertNotNull(dest.getResponseDescription());
        assertEquals("some cause", dest.getResponseDescription().getCauseOfDeath());
    }
}
