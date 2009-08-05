package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT })
public class HospitalizationTest extends CaaersTestCase {
    public void testToString() throws Exception {
        assertEquals("0: Please select", Hospitalization.NONE.toString());
        assertEquals("2: No", Hospitalization.NO
                        .toString());
        assertEquals("1: Yes", Hospitalization.YES.toString());
    }

    public void testFromCode() throws Exception {
        assertEquals(Hospitalization.NONE, Hospitalization.getByCode(0));
        assertEquals(Hospitalization.YES, Hospitalization.getByCode(1));
        assertEquals(Hospitalization.NO, Hospitalization.getByCode(2));
    }
}
