package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
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
