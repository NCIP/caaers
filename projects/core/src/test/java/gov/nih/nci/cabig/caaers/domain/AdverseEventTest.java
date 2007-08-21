package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({AE_DATA_COLLECTION })
public class AdverseEventTest extends CaaersTestCase {
    public void testDefaultExpectedness() throws Exception {
        AdverseEvent ae = new AdverseEvent();
        assertNull(ae.getExpected());
        //assertFalse(ae.getExpected());
    }
}
