package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.User;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
/**
 * This controller will deal with the addition and deletion of comments.
 * 
 * @author Biju Joseph
 *
 */
public class RoutingAndReviewCommentController extends SimpleFormController {
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	public static final String AJAX_ENTITY = "entity";
	public static final String AJAX_ENTITY_ID = "entityId";
	public static final String AJAX_ACTION = "action";
	private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	public RoutingAndReviewCommentController() {
		setCommandClass(RoutingAndReviewCommentCommand.class);
		setBindOnNewForm(true);
		setFormView("ae/listReviewComments");
        setSuccessView("ae/listReviewComments");
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		return new RoutingAndReviewCommentCommand();
	}
	
	@Override
	protected boolean isFormSubmission(HttpServletRequest request) {
		Object isAjax = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
		if(isAjax != null && !isAjax.equals(""))
			return true;
		
		String comment = request.getParameter("comment");
		return comment != null;
	}
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	@Override
	protected void onBindOnNewForm(HttpServletRequest request, Object command)	throws Exception {
		super.onBindOnNewForm(request, command);
		RoutingAndReviewCommentCommand cmd = (RoutingAndReviewCommentCommand) command;
		String entity = cmd.getEntity();
		Integer id = cmd.getEntityId();
		List<? extends ReviewComment> prevComments = null;
		if("aeReport".equals(entity)){
			prevComments = adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReport(id);
		} else if("reportingPeriod".equals(entity)){
			prevComments = adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReportingPeriod(id);
		}
		cmd.setPreviousComments(prevComments);
		
		String userId = SecurityUtils.getUserLoginName();
		cmd.setUserId(userId);
	}
	
	@Override
	protected void onBindAndValidate(HttpServletRequest request,Object command, BindException errors) throws Exception {
		super.onBindAndValidate(request, command, errors);
		RoutingAndReviewCommentCommand cmd = (RoutingAndReviewCommentCommand) command;
		String userId = SecurityUtils.getUserLoginName();
		cmd.setUserId(userId);
		Object action = findInRequest(request, AJAX_ACTION);
		if(action == null){
			if(cmd.getComment() == null){
				errors.reject("RAR_003", "Invalid comment");
			}
		}
	}
	
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)	throws Exception {
		ModelAndView mv =  super.processFormSubmission(request, response, command, errors);
		RoutingAndReviewCommentCommand cmd = (RoutingAndReviewCommentCommand) command;
		String entity = cmd.getEntity();
		Integer id = cmd.getEntityId();
		String comment = cmd.getComment();
		String userId = cmd.getUserId();
		Object action = findInRequest(request, AJAX_ACTION);
		if(action != null){
			if(action.equals("addPopupComment")){
				if("aeReport".equals(entity)){
					adverseEventRoutingAndReviewRepository.addReportReviewComment(id, comment, userId);
				}else if("reportingPeriod".equals(entity)){
					AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(id);
					adverseEventRoutingAndReviewRepository.addReportingPeriodReviewComment(reportingPeriod, comment, userId);
				}
			
				// Fetch the new list of comments again and set it up in the command.
				cmd.setComment(""); // Clear the command so that it disappears from the textArea in the popup
				populatePreviousComments(entity, id, cmd);
			}else if(action.equals("editPopupComment")){
				Integer commentId = cmd.getCommentId();
				if("aeReport".equals(entity)){
					adverseEventRoutingAndReviewRepository.editReportReviewComment(id, comment, userId, commentId);
				}else if("reportingPeriod".equals(entity)){
					AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(id);
					adverseEventRoutingAndReviewRepository.editReportingPeriodReviewComment(reportingPeriod, comment, userId, commentId);
				}
				
				// Fetch the new list of comments again and set it up in the command.
				cmd.setComment("");
				populatePreviousComments(entity, id, cmd);
			}else if(action.equals("deletePopupComment")){
				Integer commentId = cmd.getCommentId();
				if("aeReport".equals(entity)){
					adverseEventRoutingAndReviewRepository.deleteReportReviewComment(id, commentId);
				}else if("reportingPeriod".equals(entity)){
					adverseEventRoutingAndReviewRepository.deleteReportingPeriodReviewComment(id, commentId);
				}
				cmd.setComment("");
				populatePreviousComments(entity, id, cmd);
			}
			else{
				Object ajaxSubview = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
				if(ajaxSubview != null && ((String)action).equals("fetchComments")){
					mv.setViewName("ae/ajax/" + ajaxSubview);
					populatePreviousComments(entity, id, cmd);
				}
			}
		}
		return mv;
	}
	
	public void populatePreviousComments(String entity, Integer id, RoutingAndReviewCommentCommand cmd){
		List<? extends ReviewComment> prevComments = null;
		if("aeReport".equals(entity)){
			prevComments = adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReport(id);
		} else if("reportingPeriod".equals(entity)){
			prevComments = adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReportingPeriod(id);
		}
		cmd.setPreviousComments(prevComments);
	}
	
	public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
	public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
	
	public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao(){
		return adverseEventReportingPeriodDao;
	}
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
}
