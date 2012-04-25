package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapyType;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class PriorTherapyConverterTest extends TestCase {
    
    public void testConvertToDto() throws Exception {
        
        PriorTherapyConverter converter = new PriorTherapyConverter();
        PriorTherapy p = new PriorTherapy();
        p.setMeddraTerm("x");
        p.setTherapyType("y");
        p.setMeddraCode("1111");
        p.setText("jjj");
        PriorTherapyType t = converter.convert(p);
        assertEquals("jjj", t.getText());
        assertEquals("1111", t.getMeddraCode());
        assertEquals("y", t.getTherapyType());
    }

    public void testConvertToDomain() throws Exception {
        PriorTherapyConverter converter = new PriorTherapyConverter();
        PriorTherapyType p = new PriorTherapyType();
        p.setMeddraTerm("x");
        p.setTherapyType("y");
        p.setMeddraCode("1111");
        p.setText("jjj");
        PriorTherapy t = converter.convert(p);
        assertEquals("jjj", t.getText());
        assertEquals("1111", t.getMeddraCode());
        assertEquals("y", t.getTherapyType());
    }
}
