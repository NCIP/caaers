package gov.nih.nci.cabig.caaers.web;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * StateInfoInterceptor Tester.
 *
 * @author Biju Joseph
 * @since <pre>11/05/2010</pre>
 * 
 */
public class StateInfoInterceptorTest extends WebTestCase {
    StateInfoInterceptor stateInfoInterceptor;


	private static final String STUDY_SUBJECT_GRID_ID = "studySubjectGridId";
    private static final String ASSIGNMENT = "assignment";
	//state variables for keeping the study/subject/course infor in ae flow
	private static final String SELECTED_STUDY_ID = "pre_selected_study_id";
	private static final String SELECTED_PARTICIPANT_ID = "pre_selected_participant_id";
	private static final String SELECTED_COURSE_ID = "pre_selected_reporting_period_id";

    public void setUp() throws Exception {
        super.setUp();
        stateInfoInterceptor = new StateInfoInterceptor();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    //checks if assignment and study subject grid-id clears off the previous cache. 
    public void testPreHandle() throws Exception {
        
         session.setAttribute(SELECTED_COURSE_ID, 1);
         session.setAttribute(SELECTED_PARTICIPANT_ID,1);
         session.setAttribute(SELECTED_STUDY_ID,1);

         request.setRequestURI("hello?abcd=x");
         stateInfoInterceptor.preHandle(request, response, null);
         assertEquals(1, session.getAttribute(SELECTED_COURSE_ID));

         request.setRequestURI("hello?assignment=x");
         stateInfoInterceptor.preHandle(request, response, null);
         assertNull( session.getAttribute(SELECTED_COURSE_ID));

         session.setAttribute(SELECTED_COURSE_ID, 1);
         session.setAttribute(SELECTED_PARTICIPANT_ID,1);
         session.setAttribute(SELECTED_STUDY_ID,1);

         request.setRequestURI("hello?studySubjectGridId=x");
         stateInfoInterceptor.preHandle(request, response, null);
         assertNull( session.getAttribute(SELECTED_COURSE_ID));


    }

}
