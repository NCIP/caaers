package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurbah Agrawal
 */
public class EditInvestigatorController extends InvestigatorController<Investigator> {

    private static final Log log = LogFactory.getLog(EditInvestigatorController.class);

    public EditInvestigatorController() {
        setBindOnNewForm(true);
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {

        request.getSession().removeAttribute(InvestigatorAjaxFacade.CREATE_INVESTIGATOR_FORM_NAME);
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        Investigator investigator = investigatorDao.getInvestigatorById(Integer.parseInt(request.getParameter("investigatorId")));
        investigator.setWasLoginDisallowed(BooleanUtils.isFalse(investigator.getAllowedToLogin()));
        investigator.setWasLoginIdNull(investigator.getLoginId() == null);
        if (log.isDebugEnabled()) {
            log.debug("Retrieved Investigator :" + String.valueOf(investigator));
        }

        return investigator;

    }

    @Override
    protected Investigator save(final Investigator investigator, final Errors errors) {

        if (errors.hasErrors()) {
            return investigator;
        }
        Investigator mergedInvestigator = getDao().merge(investigator);
        getDao().initialize(mergedInvestigator);
        getDao().save(mergedInvestigator);
        return mergedInvestigator;
    }

    @Override
    protected boolean isSummaryEnabled() {
        return true;
    }

    @Override
    protected void layoutTabs(final Flow<Investigator> flow) {
        flow.addTab(new InvestigatorTab());

    }

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final Investigator command, final Tab<Investigator> tab) {
    	
    	if(isAjaxRequest(request)) return false;
        String action = (String) super.findInRequest(request, "_action");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
            return false;
        }
        return super.shouldSave(request, command, tab);

    }
    
    
    @SuppressWarnings("unchecked")
	@Override
    protected ModelAndView processFinish(final HttpServletRequest request,
                                         final HttpServletResponse response, final Object command,
                                         final BindException errors) throws Exception {

    	Investigator investigator = (Investigator) command;
        ModelAndView modelAndView = new ModelAndView("admin/investigator_review");
        String emailSendingErrorMessage = "";
        String statusMessage = "";
        if(!errors.hasErrors()){
        	if("saveRemoteInv".equals(request.getParameter("_action"))){
        		getDao().evict(investigator);
        		Investigator remoteInvtoSave = investigator.getExternalInvestigators().get(Integer.parseInt(request.getParameter("_selected")));
        		getInvestigatorRepository().convertToRemote(investigator, remoteInvtoSave);
        		investigator.setEmailAddress(remoteInvtoSave.getEmailAddress());
        		investigator.setFirstName(remoteInvtoSave.getFirstName());
        		investigator.setLastName(remoteInvtoSave.getLastFirst());
        		investigator.setPhoneNumber(remoteInvtoSave.getPhoneNumber());
        		investigator.setFaxNumber(remoteInvtoSave.getFaxNumber());
        		statusMessage = "Successfully synched Investigator";
        	}else{
        		try{
        			getInvestigatorRepository().save(investigator, ResetPasswordController.getURL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath()));
        			statusMessage = "Successfully updated Investigator";
        		}catch(MailException e){
        			statusMessage = "Successfully updated Investigator";
        			emailSendingErrorMessage = "Could not send email to user.";
                    logger.error("Could not send email to user.", e);
        		}
        	}
            if (!StringUtils.isBlank(emailSendingErrorMessage)) {
                statusMessage = statusMessage + " But we could not send email to user";
            }
            modelAndView.getModel().put("flashMessage", statusMessage);
        	
        }
        request.setAttribute("_noStdFlashMessage", true);
        modelAndView.addAllObjects(errors.getModel());
        modelAndView.addObject("investigator", investigator);
        return modelAndView;
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command,BindException errors, int page) throws Exception {
    	super.onBindAndValidate(request, command, errors, page);
        Investigator investigator = (Investigator) command;
        if("syncInvestigator".equals(request.getParameter("_action"))){
        	Investigator remoteInvestigator = new RemoteInvestigator();   
        	List<Investigator> remoteInvs = null;
        	if (investigator.getNciIdentifier() != null) {
        		remoteInvestigator.setNciIdentifier(investigator.getNciIdentifier());
        		remoteInvs = getDao().getRemoteInvestigators(remoteInvestigator);
        	}
        	

    		if(remoteInvs != null && remoteInvs.size() > 0){
    			investigator.setExternalInvestigators(remoteInvs);
    			errors.reject("REMOTE_INV_EXISTS","Investigator with NCI Identifier " +investigator.getNciIdentifier()+ " exisits in external system");
    		}
        }
    }
}