package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.AE_DATA_COLLECTION;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { AE_DATA_COLLECTION })
public class AdverseEventTest extends CaaersTestCase {
    public void testDefaultExpectedness() throws Exception {
        AdverseEvent ae = new AdverseEvent();
        assertNull(ae.getExpected());
        // assertFalse(ae.getExpected());
    }
}
