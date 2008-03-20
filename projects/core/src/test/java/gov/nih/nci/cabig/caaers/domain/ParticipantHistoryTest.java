package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class ParticipantHistoryTest extends AbstractTestCase {

    public void testFindBodySurfaceArea() throws Exception {
        double wt = 3234;
        double ht = 4424;
        double bsa = Math.sqrt((ht * wt) / 3600);
        double calBsa = ParticipantHistory.bodySuraceArea(ht, "Centimeter", wt, "Kilogram");
        double nwt = (wt / 2.20462262185);
        double nht = (ht * 2.54);

        double bsa2 = Math.sqrt((nht * nwt) / 3600);
        double calBsa2 = ParticipantHistory.bodySuraceArea(ht, "Inch", wt, "Pound");
        assertEquals("BodySurfaceArea is wrong", bsa2, calBsa2);
    }
}
