package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class OtherCauseTest extends AbstractTestCase {

    private OtherCause otherCause;
    private String text;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        otherCause = new OtherCause();

        text = "title";
        otherCause.setText(text);
        otherCause.setVersion(2);
        otherCause.setId(1);
        otherCause.setGridId("grid id");
        otherCause.setReport(new ExpeditedAdverseEventReport());
    }

    public void testCopyBasicProperties() {
        OtherCause copiedOtherCause = otherCause.copy();


        assertNull("must not coy id", copiedOtherCause.getId());

        assertNull("must not coy grid id", copiedOtherCause.getGridId());
        assertNull("must not coy version number", copiedOtherCause.getVersion());
        assertNull("must not coy expeditedReport", copiedOtherCause.getReport());
        assertEquals(text, copiedOtherCause.getText());


    }


}