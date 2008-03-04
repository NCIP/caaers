package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class AdverseEventResponseDescriptionTest extends CaaersTestCase {
    private AdverseEventResponseDescription description;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        description = new AdverseEventResponseDescription();
    }

    public void testIsRemovedWhenRemoved() throws Exception {
        description.setDateRemovedFromProtocol(new Date());
        assertTrue(description.isRemovedFromProtocol());
    }

    public void testIsNotRemovedWhenRemoved() throws Exception {
        description.setDateRemovedFromProtocol(null);
        assertFalse(description.isRemovedFromProtocol());
    }
}
