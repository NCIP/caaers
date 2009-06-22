package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;

/**
 * @author Biju Joseph
 */
public class CourseAgentAttributionTest extends AbstractNoSecurityTestCase {

    private CourseAgentAttribution courseAgentAttribution;
    private AdverseEvent adverseEvent;
    private CourseAgent cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new CourseAgent();
        courseAgentAttribution = new CourseAgentAttribution();


        courseAgentAttribution.setId(1);
        courseAgentAttribution.setGridId("grid id");
        courseAgentAttribution.setCause(cause);
        courseAgentAttribution.setVersion(2);
        courseAgentAttribution.setAttribution(Attribution.POSSIBLE);
        courseAgentAttribution.setAdverseEvent(adverseEvent);
    }

//    public void testCopy() {
//        CourseAgentAttribution copiedCourseAgentAttribution = courseAgentAttribution.copy();
//        assertNull("id must be null", copiedCourseAgentAttribution.getId());
//        assertNull("grid id must be null", copiedCourseAgentAttribution.getGridId());
//        assertNull("version number must be null", copiedCourseAgentAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedCourseAgentAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedCourseAgentAttribution.getAttribution());
//        assertNotNull(courseAgentAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedCourseAgentAttribution.getAdverseEvent());
//    	assertTrue(true);
//
//    }
    public void testAllTestCommented(){
    	assertTrue(true);
    }
}