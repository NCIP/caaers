package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT })
public class AttributionTest extends CaaersTestCase {
    public void testToString() throws Exception {
        assertEquals("2: Unlikely", Attribution.UNLIKELY.toString());
        assertEquals("5: Definite", Attribution.DEFINITE.toString());
        assertEquals("4: Probable", Attribution.PROBABLE.toString());
        assertEquals("3: Possible", Attribution.POSSIBLE.toString());
        assertEquals("1: Unrelated", Attribution.UNRELATED.toString());
    }

    public void testFromCode() throws Exception {
        assertEquals(Attribution.DEFINITE, Attribution.getByCode(5));
        assertEquals(Attribution.PROBABLE, Attribution.getByCode(4));
        assertEquals(Attribution.POSSIBLE, Attribution.getByCode(3));
        assertEquals(Attribution.UNLIKELY, Attribution.getByCode(2));
        assertEquals(Attribution.UNRELATED, Attribution.getByCode(1));
    }
}
