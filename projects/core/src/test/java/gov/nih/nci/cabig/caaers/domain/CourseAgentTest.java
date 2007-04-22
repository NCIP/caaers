package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */
public class CourseAgentTest extends CaaersTestCase {
    private CourseAgent courseAgent = new CourseAgent();

    public void testGetAdministrationDelayWithNoUnits() throws Exception {
        courseAgent.setAdministrationDelayAmount(new BigDecimal(14));
        courseAgent.setAdministrationDelayUnits(null);
        assertNull(courseAgent.getAdministrationDelay());
    }

    public void testGetAdministrationDelayWithNoAmount() throws Exception {
        courseAgent.setAdministrationDelayAmount(null);
        courseAgent.setAdministrationDelayUnits(DelayUnits.DAYS);
        assertNull(courseAgent.getAdministrationDelay());
    }

    public void testGetAdministrationDelayWithBothValues() throws Exception {
        courseAgent.setAdministrationDelayUnits(DelayUnits.HOURS);
        courseAgent.setAdministrationDelayAmount(new BigDecimal(34));
        assertEquals(new BigDecimal(34 * 60), courseAgent.getAdministrationDelay());
    }

    public void testSetAdministrationDelayNull() throws Exception {
        courseAgent.setAdministrationDelayUnits(DelayUnits.HOURS);
        courseAgent.setAdministrationDelayAmount(new BigDecimal(44));

        courseAgent.setAdministrationDelay(null);
        assertNull("Amount should be null", courseAgent.getAdministrationDelayAmount());
        assertNull("Units should be null", courseAgent.getAdministrationDelayUnits());
    }

    public void testSetAdministrationDelayExactDays() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal(4320));
        assertEquals(DelayUnits.DAYS, courseAgent.getAdministrationDelayUnits());
        assertEquals(3, courseAgent.getAdministrationDelayAmount().intValueExact());
    }

    public void testSetAdministrationDelayExactHours() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal(4260));
        assertEquals(DelayUnits.HOURS, courseAgent.getAdministrationDelayUnits());
        assertEquals(71, courseAgent.getAdministrationDelayAmount().intValueExact());
    }

    public void testSetAdministrationDelayExactMinutes() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal(4259));
        assertEquals(DelayUnits.MINUTES, courseAgent.getAdministrationDelayUnits());
        assertEquals(4259, courseAgent.getAdministrationDelayAmount().intValueExact());
    }

    public void testSetAdministrationDelayFractional() throws Exception {
        courseAgent.setAdministrationDelay(new BigDecimal("4258.3"));
        assertEquals(DelayUnits.MINUTES, courseAgent.getAdministrationDelayUnits());
        assertEquals("4258.3", courseAgent.getAdministrationDelayAmount().toString());
    }
}
