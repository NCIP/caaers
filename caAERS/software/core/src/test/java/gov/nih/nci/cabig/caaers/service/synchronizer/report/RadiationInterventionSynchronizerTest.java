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
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Ramakrishna Gundala
 * @since 1.5
 */
public class RadiationInterventionSynchronizerTest extends AbstractTestCase {

    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
    RadiationInterventionSynchronizer synchronizer;

    public void setUp() throws Exception {
        super.setUp();
        synchronizer = new RadiationInterventionSynchronizer();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();

    }

    public void testMigrateNew() throws Exception {
        src.addRadiationIntervention(Fixtures.createRadiationIntervention());
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getRadiationInterventions().size());
    }

    public void testMigrateRemove() throws Exception {
        dest.addRadiationIntervention(Fixtures.createRadiationIntervention());
        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(0, dest.getRadiationInterventions().size());
    }

    public void testEdit() throws Exception {
    	RadiationIntervention ri1 = Fixtures.createRadiationIntervention();
    	ri1.setFractionNumber("22");
    	ri1.setAdjustment("slight increase");
    	ri1.setDaysElapsed("38");
       
        src.addRadiationIntervention(ri1);
        RadiationIntervention ri2 = Fixtures.createRadiationIntervention();
        dest.addRadiationIntervention(ri2);

        synchronizer.migrate(src, dest, outcome);
        assertFalse(outcome.hasErrors());
        assertEquals(1, dest.getRadiationInterventions().size());
        assertSame(ri2, dest.getRadiationInterventions().get(0));
        assertEquals("22", dest.getRadiationInterventions().get(0).getFractionNumber());
        assertEquals("slight increase", dest.getRadiationInterventions().get(0).getAdjustment());
        assertEquals("38", dest.getRadiationInterventions().get(0).getDaysElapsed());
    }
}
