package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import static gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus.*;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT })
public class PostAdverseEventStatusTest extends CaaersTestCase {
    public void testDisplayNames() throws Exception {
        assertEquals("Intervention for AE continues", 
            INTERVENTION_CONTINUES.getDisplayName());
        assertEquals("Not recovered", NOT_RECOVERED.getDisplayName());
        assertEquals("Dead", DEAD.getDisplayName());
    }
    
    public void testGetByCode() throws Exception {
        assertSame(RECOVERED_WITH_SEQUELAE, getByCode(RECOVERED_WITH_SEQUELAE.getCode()));
    }
}
