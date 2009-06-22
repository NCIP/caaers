package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Biju Joseph
 */
public class OutComeTest extends AbstractTestCase {

    private Outcome outcome;
    private OutcomeType outcomeType;

    private Date date;

    private String other;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        outcomeType = OutcomeType.DEATH;

        date = edu.nwu.bioinformatics.commons.DateUtils.createDate(2008, Calendar.NOVEMBER, 2);

        other = "other";
        outcome = new Outcome();
        outcome.setId(1);
        outcome.setGridId("grid id");
        outcome.setDate(date);
        outcome.setOutcomeType(outcomeType);
        outcome.setOther(other);
        outcome.setVersion(1);
    }


    public void testCopy() {

        Outcome copiedOutcome = outcome.copy();
        assertNotNull(outcome.getId());
        assertNotNull(outcome.getVersion());
        assertNotNull(outcome.getGridId());

        assertNull(copiedOutcome.getId());
        assertNull(copiedOutcome.getGridId());
        assertNull("version number must be null", copiedOutcome.getVersion());

        assertEquals("others must be same", other, copiedOutcome.getOther());

        assertEquals("outcomeType must be same", outcomeType, copiedOutcome.getOutcomeType());

        assertEquals("date must be same", date, copiedOutcome.getDate());

        assertNotSame("both outcome must refer to different objects", outcome, copiedOutcome);
        assertNotEquals("outcomes must not refer to same object", outcome, copiedOutcome);

    }
}
