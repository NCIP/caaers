package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurbah Agrawal
 */
public class EditResearchStaffController extends ResearchStaffController<ResearchStaff> {

    private static final Log log = LogFactory.getLog(EditResearchStaffController.class);

    public EditResearchStaffController() {
        setBindOnNewForm(true);
    }

    // /LOGIC

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        ResearchStaff researchStaff = researchStaffRepository.getById(Integer.parseInt(request.getParameter("researchStaffId")));

        if (log.isDebugEnabled()) {
            log.debug("Retrieved ResearchStaff :" + String.valueOf(researchStaff));
        }

        return researchStaff;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    protected ModelAndView processFinish(final HttpServletRequest request,
                                         final HttpServletResponse response, final Object command,
                                         final BindException errors) throws Exception {

        ResearchStaff researchStaff = (ResearchStaff) command;
        ModelAndView modelAndView = new ModelAndView("admin/research_staff_review");
        String emailSendingErrorMessage = "";
        String statusMessage = "";
        if(!errors.hasErrors()){
        	if("saveRemoteRs".equals(request.getParameter("_action"))){
        		researchStaffRepository.evict(researchStaff);
        		ResearchStaff remoteRStoSave = researchStaff.getExternalResearchStaff().get(Integer.parseInt(request.getParameter("_selected")));
        		researchStaffRepository.convertToRemote(researchStaff, remoteRStoSave);
        		researchStaff.setEmailAddress(remoteRStoSave.getEmailAddress());
        		researchStaff.setFirstName(remoteRStoSave.getFirstName());
        		researchStaff.setLastName(remoteRStoSave.getLastFirst());
        		researchStaff.setPhoneNumber(remoteRStoSave.getPhoneNumber());
        		researchStaff.setFaxNumber(remoteRStoSave.getFaxNumber());
        		statusMessage = "Successfully synched ResearchStaff";
        	}else{
        		try{
        			researchStaffRepository.save(researchStaff, ResetPasswordController.getURL(request
                            .getScheme(), request.getServerName(), request.getServerPort(), request
                            .getContextPath()));
        			statusMessage = "Successfully updated ResearchStaff";
        		}catch(MailException e){
        			emailSendingErrorMessage = "Could not send email to user.";
                    logger.error("Could not send email to user.", e);
        		}
        	}
            if (!StringUtils.isBlank(emailSendingErrorMessage)) {
                statusMessage = statusMessage + " But we could not send email to user";
            }
            modelAndView.getModel().put("flashMessage", statusMessage);
        	
        }
        modelAndView.addAllObjects(errors.getModel());
        modelAndView.addObject("researchStaff", researchStaff);
        return modelAndView;
    }
    
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command,
                                     BindException errors, int page) throws Exception {
        ResearchStaff researchStaff = (ResearchStaff) command;
        if("syncResearchStaff".equals(request.getParameter("_action"))){
        	List<ResearchStaff> remoteRs = researchStaffRepository.getRemoteResearchStaff(researchStaff);
    		if(remoteRs != null && remoteRs.size() > 0){
    			researchStaff.setExternalResearchStaff(remoteRs);
    			errors.reject("REMOTE_RS_EXISTS","ResearchStaff with EmailAddress " +researchStaff.getEmailAddress()+ " exisits in external system");
    		}
        }
    }

    @Override
    protected void layoutTabs(final Flow<ResearchStaff> flow) {
        flow.addTab(new ResearchStaffTab());
    }

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final ResearchStaff command, final Tab<ResearchStaff> tab) {
        // supress for ajax and delete requests
        return super.shouldSave(request, command, tab);
    }

}