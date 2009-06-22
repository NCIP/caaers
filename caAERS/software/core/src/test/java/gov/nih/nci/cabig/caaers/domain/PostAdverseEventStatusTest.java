package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus.DEAD;
import static gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus.INTERVENTION_CONTINUES;
import static gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus.NOT_RECOVERED;
import static gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus.RECOVERED_WITH_SEQUELAE;
import static gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus.getByCode;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class PostAdverseEventStatusTest extends CaaersTestCase {
    public void testDisplayNames() throws Exception {
        assertEquals("Intervention for AE continues", INTERVENTION_CONTINUES.getDisplayName());
        assertEquals("Not recovered/Not resolved", NOT_RECOVERED.getDisplayName());
        assertEquals("Fatal/Died", DEAD.getDisplayName());
    }

    public void testGetByCode() throws Exception {
        assertSame(RECOVERED_WITH_SEQUELAE, getByCode(RECOVERED_WITH_SEQUELAE.getCode()));
    }
}
