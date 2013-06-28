/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Ramakrishna Gundala
 * @since 1.5
 */
public class SurgeryInterventionSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    SurgeryInterventionSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new SurgeryInterventionSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateNew() throws Exception {
        src.addSurgeryIntervention(Fixtures.createSurgeryIntervention());
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSurgeryInterventions().size());
    }

    public void testMigrateRemove() throws Exception {
        dest.addSurgeryIntervention(Fixtures.createSurgeryIntervention());
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getSurgeryInterventions().size());
    }

    public void testEdit() throws Exception {
    	SurgeryIntervention si1 = Fixtures.createSurgeryIntervention();
    	si1.setDescription("cut in the gut");
       
        src.addSurgeryIntervention(si1);
        SurgeryIntervention si2 = Fixtures.createSurgeryIntervention();
        dest.addSurgeryIntervention(si2);

        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getSurgeryInterventions().size());
        assertSame(si2, dest.getSurgeryInterventions().get(0));
        assertEquals("cut in the gut", dest.getSurgeryInterventions().get(0).getDescription());
    }
}
