/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser;
import junit.framework.TestCase;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class ObjectPrivilegeParserTest extends TestCase {

    public void testParserComposite() {
        ObjectPrivilegeParser opp = new ObjectPrivilegeParser("gov.AdverseEvent#startDate:CREATE && (gov.Study:DELETE || gov.Participant:EDIT)");
        assertEquals(3, opp.getDomainObjectPrivileges().length);

        assertEquals("gov.AdverseEvent#startDate", opp.getDomainObjectPrivileges()[0][0]);
        assertEquals("CREATE", opp.getDomainObjectPrivileges()[0][1]);

        assertEquals("gov.Study", opp.getDomainObjectPrivileges()[1][0]);
        assertEquals("DELETE", opp.getDomainObjectPrivileges()[1][1]);

        assertEquals("gov.Participant", opp.getDomainObjectPrivileges()[2][0]);
        assertEquals("EDIT", opp.getDomainObjectPrivileges()[2][1]);
    }

    public void testParserSimple() {
        ObjectPrivilegeParser opp = new gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser("gov.AdverseEvent:EDIT");
        assertEquals(1, opp.getDomainObjectPrivileges().length);

        assertEquals("gov.AdverseEvent", opp.getDomainObjectPrivileges()[0][0]);
        assertEquals("EDIT", opp.getDomainObjectPrivileges()[0][1]);
    }
}
