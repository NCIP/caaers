/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class PriorTherapyMigratorTest extends TestCase {
    public void testMigrate() throws Exception {
        PriorTherapy p1 = new PriorTherapy();
        p1.setMeddraCode("111");
        p1.setText("x");
        p1.setMeddraTerm("j");
        p1.setTherapyType("k");
        
        PriorTherapy p2 = new PriorTherapy();
        
        PriorTherapyMigrator migrator = new PriorTherapyMigrator();
        migrator.migrate(p1, p2 , null);
        assertNotNull(p2.getLastSynchedDate());
        
        assertEquals(p1, p2);
        assertEquals("j", p2.getMeddraTerm());
        assertEquals("k", p2.getTherapyType());
        assertEquals("x", p2.getText());
        assertEquals("111", p2.getMeddraCode());

    }
}
