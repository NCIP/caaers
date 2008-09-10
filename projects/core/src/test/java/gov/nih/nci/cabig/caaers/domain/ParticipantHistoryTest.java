package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.math.BigDecimal;

/**
 * @author Biju Joseph
 */
public class ParticipantHistoryTest extends AbstractTestCase {

    private ParticipantHistory participantHistory;
    private double wt;
    private double ht;
    private String baselinePerformance;
    private ParticipantHistory.Measure height;
    private BigDecimal quantity;
    private ParticipantHistory.Measure weight;
    private BigDecimal weightQuantity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();


        wt = 3234;
        ht = 4424;

        participantHistory = new ParticipantHistory();

        baselinePerformance = "base line";
        participantHistory.setBaselinePerformanceStatus(baselinePerformance);
        height = new ParticipantHistory.Measure();
        height.setUnit("inch");
        quantity = new BigDecimal(ht);
        height.setQuantity(quantity);
        participantHistory.setHeight(height);
        weight = new ParticipantHistory.Measure();
        weightQuantity = new BigDecimal(wt);
        weight.setQuantity(weightQuantity);
        weight.setUnit("Pound");
        participantHistory.setWeight(weight);
        participantHistory.setReport(new ExpeditedAdverseEventReport());
    }


    public void testCopy() {
        ParticipantHistory history = participantHistory.copy();


        assertNull("must not coy id", history.getId());

        assertNull("must not coy grid id", history.getGridId());
        assertNull("must not coy version number", history.getVersion());
        assertNull("must not coy expeditedReport", history.getReport());
        assertEquals(participantHistory.getBodySurfaceArea(), history.getBodySurfaceArea());
        assertEquals(baselinePerformance, history.getBaselinePerformanceStatus());
        assertEquals(height, history.getHeight());
        assertEquals(weight, history.getWeight());


    }

    public void testFindBodySurfaceArea() throws Exception {
        double bsa = Math.sqrt((ht * wt) / 3600);
        double calBsa = ParticipantHistory.bodySuraceArea(ht, "Centimeter", wt, "Kilogram");
        double nwt = (wt / 2.20462262185);
        double nht = (ht * 2.54);

        double bsa2 = Math.sqrt((nht * nwt) / 3600);
        double calBsa2 = ParticipantHistory.bodySuraceArea(ht, "Inch", wt, "Pound");
        assertEquals("BodySurfaceArea is wrong", bsa2, calBsa2);
    }


}
