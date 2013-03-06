/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.tools.hibernate;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC. Refactor into a shared library. */
public class WonderfulNamingStrategyTest extends CaaersTestCase {
    private static final String DC = "DON'T CARE";

    private WonderfulNamingStrategy strategy = new WonderfulNamingStrategy();

    public void testForeignKeyColumn() throws Exception {
        assertEquals("planned_event_id", strategy.foreignKeyColumnName("plannedEvent", DC, DC, DC));
    }

    public void testTableName() throws Exception {
        assertEquals("participants", strategy.classToTableName("Participant"));
        assertEquals("adverse_events", strategy.classToTableName("AdverseEvent"));
        assertEquals("activities", strategy.classToTableName("Activity"));
    }
}
