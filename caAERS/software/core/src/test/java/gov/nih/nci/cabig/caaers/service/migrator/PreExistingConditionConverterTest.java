/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditionType;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class PreExistingConditionConverterTest extends TestCase {
    public void testConvertToDTO() throws Exception {
        PreExistingCondition p = Fixtures.createPreExistingCondition("pc", "111", "Headace", "Pain");
        PreExistingConditionConverter converter = new PreExistingConditionConverter();
        PreExistingConditionType t = converter.convert(p);
        assertEquals("111", t.getMeddraLltCode());
        assertEquals("pc", t.getText());
        assertEquals("Headace", t.getMeddraLlt());
        assertEquals("Pain", t.getMeddraHlgt());
    }

    public void testConvertToDomain() throws Exception {
        PreExistingConditionType t = Fixtures.createPreExistingConditionType("pc", "111", "Headace", "Pain");
        PreExistingConditionConverter converter = new PreExistingConditionConverter();
        PreExistingCondition p = converter.convert(t);
        assertEquals("111", p.getMeddraLltCode());
        assertEquals("pc", p.getText());
        assertEquals("Headace",p.getMeddraLlt());
        assertEquals("Pain", p.getMeddraHlgt());
    }
}
