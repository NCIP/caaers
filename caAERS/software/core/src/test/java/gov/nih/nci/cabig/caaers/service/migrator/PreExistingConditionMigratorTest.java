/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class PreExistingConditionMigratorTest extends TestCase {
    public void testMigrate() throws Exception {
       PreExistingConditionMigrator migrator = new PreExistingConditionMigrator();
        PreExistingCondition p1  = Fixtures.createPreExistingCondition("p", "111", "Head", "body");
        PreExistingCondition p2 = Fixtures.createPreExistingCondition("a", "z", "b", "c");
        migrator.migrate(p1, p2, null);
        assertEquals("p", p2.getText());
        assertEquals("body", p2.getMeddraHlgt());
        assertEquals("Head", p2.getMeddraLlt());
        assertEquals("111", p2.getMeddraLltCode());
        assertNotNull(p2.getLastSynchedDate());

    }
}
