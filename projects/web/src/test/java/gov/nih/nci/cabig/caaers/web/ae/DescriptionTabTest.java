package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class DescriptionTabTest extends AeTabTestCase<DescriptionTab<CreateAdverseEventCommand>> {
    @Override
    protected DescriptionTab<CreateAdverseEventCommand> createTab() {
        return new DescriptionTab<CreateAdverseEventCommand>();
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
