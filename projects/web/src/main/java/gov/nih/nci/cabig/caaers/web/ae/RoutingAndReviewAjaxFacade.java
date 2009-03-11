package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.validation.validator.AdverseEventReportingPeriodValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class RoutingAndReviewAjaxFacade {
	
	 private static final Log log = LogFactory.getLog(RoutingAndReviewAjaxFacade.class);
	 private static Class<?>[] CONTROLLERS = {RoutingAndReviewController.class};
	 
	 private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	 
	 private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	 private AdverseEventReportingPeriodValidator adverseEventReportingPeriodValidator = new AdverseEventReportingPeriodValidator();
	 private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	 
	 protected Object extractCommand() {
	    	
		WebContext webContext = WebContextFactory.get();
		Object command = null;
		for (Class<?> controllerClass : CONTROLLERS) {
			String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
			command = webContext.getSession().getAttribute(formSessionAttributeName);
			if (command == null) {
				log.debug("Command not found using name " + formSessionAttributeName);
			} else {
				log.debug("Command found using name " + formSessionAttributeName);
				break;
			}
		}
		if (command == null) {
			throw new CaaersSystemException("Could not find command in session");
		} else {
			return command;
		}
	 }
	 
	public AjaxOutput advanceWorkflow(Integer workflowId, String toTransition, Integer id, String entity){
		AjaxOutput output = new AjaxOutput();
		List<String> transitions = null;
		if(entity.equals("aeReport")){
			ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(id);
			transitions = adverseEventRoutingAndReviewRepository.advanceReportWorkflow(workflowId, toTransition, aeReport, getUserId());
			output.setHtmlContent(aeReport.getReviewStatus().getDisplayName());
		}else if(entity.equals("reportingPeriod")){
			AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(id);
			transitions = adverseEventRoutingAndReviewRepository.advanceReportingPeriodWorkflow(workflowId, toTransition, reportingPeriod, getUserId());
			output.setHtmlContent(reportingPeriod.getReviewStatus().getDisplayName());
		}
		output.setObjectContent(transitions);
		return output;
	}
	
	public AjaxOutput validateTransition(Integer reportingPeriodId, String toTransition){
		AdverseEventReportingPeriod adverseEventReportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		AjaxOutput output = new AjaxOutput();
		Errors errors = new BindException(adverseEventReportingPeriod, "adverseEventReportingPeriod");
		
		if(toTransition.equals("Submit to Data Coordinator")){
			adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
			if(errors.hasErrors()){
				List<String> errorsList = new ArrayList<String>();
				for(Object error: errors.getAllErrors()){
					ObjectError objError = (ObjectError) error;
					errorsList.add(objError.getCode());
				}
				output.setObjectContent(errorsList);
			}
		}
		return output;
	}
	
	protected WebContext getWebContext(){
    	return WebContextFactory.get();
    }
	
	protected String getUserId(){
		WebContext webContext = getWebContext();
		SecurityContext context = (SecurityContext)webContext.getHttpServletRequest().getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		String userId = ((User)context.getAuthentication().getPrincipal()).getUsername();
		return userId;
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
	
	public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao){
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
	
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao(){
		return expeditedAdverseEventReportDao;
	}
	
	public void setAdverseEventReportingPeriodValidator(AdverseEventReportingPeriodValidator adverseEventReportingPeriodValidator){
		this.adverseEventReportingPeriodValidator = adverseEventReportingPeriodValidator;
	}
	
	public AdverseEventReportingPeriodValidator getAdverseEventReportingPeriodValidator(){
		return adverseEventReportingPeriodValidator;
	}
}
