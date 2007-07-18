package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class DescriptionTabTest extends AeTabTestCase {
    @Override
    protected DescriptionTab createTab() {
        return new DescriptionTab();
    }

    public void testFields() throws Exception {
        assertFieldProperties("desc",
            "aeReport.responseDescription.eventDescription",
            "aeReport.responseDescription.presentStatus",
            "aeReport.responseDescription.recoveryDate",
            "aeReport.responseDescription.retreated",
            "aeReport.responseDescription.dateRemovedFromProtocol"
        );
    }
}
