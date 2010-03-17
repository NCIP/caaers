package gov.nih.nci.cabig.caaers.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * This class intercepts the org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping, configured
 * in pages-servlet.
 * 
 * The purpose of this class is to clear the state information persisted for various flows.
 * 1.  Clears the Study/Subject/Course details (captured for manage/report adverse event flow)
 *     when the user moves out of AE flow. 
 * 2. Need to maintain the section (second level nav) highlighting , when the user is on Submit flow.
 *    (so some properties for that will be set here).
 *    
 * @author Biju Joseph
 *
 */
public class StateInfoInterceptor  extends HandlerInterceptorAdapter{
	
	//state variables for keeping the study/subject/course infor in ae flow
	private static final String SELECTED_STUDY_ID = "pre_selected_study_id";
	private static final String SELECTED_PARTICIPANT_ID = "pre_selected_participant_id";
	private static final String SELECTED_COURSE_ID = "pre_selected_reporting_period_id";
	
	//this is to store the last saved task. 
	private static final String CURRENT_TASK = "currentTask";
	private static final String LAST_VISITED_TASK = "last_visited_task";
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		//if we are moving out of ae flow... thorw away the AE persisted variables
		String requestUrl = request.getRequestURL().toString();
		if(!requestUrl.contains("/ae/")){
			session.removeAttribute(SELECTED_COURSE_ID);
			session.removeAttribute(SELECTED_PARTICIPANT_ID);
			session.removeAttribute(SELECTED_STUDY_ID);
		}
		
		//save the last task the user is in. 
		Object currentTask = request.getAttribute(CURRENT_TASK);
		
		if(currentTask != null){
			session.setAttribute(LAST_VISITED_TASK, currentTask);
		}else{
			//put the current task as the one (obtained from session)
			Object lastVisitedTask = session.getAttribute(LAST_VISITED_TASK);
			request.setAttribute(CURRENT_TASK,  lastVisitedTask);
		}
		
	}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.contains("studySubjectGridId")) {
            session.removeAttribute(SELECTED_COURSE_ID);
            session.removeAttribute(SELECTED_PARTICIPANT_ID);
            session.removeAttribute(SELECTED_STUDY_ID);
        }
        return super.preHandle(request, response, handler);
    }
}
