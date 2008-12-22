package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class RoutingAndReviewAjaxFacade {
	
	 private static final Log log = LogFactory.getLog(RoutingAndReviewAjaxFacade.class);
	 private static Class<?>[] CONTROLLERS = {RoutingAndReviewController.class};
	 
	 
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
	 
	public AjaxOutput advanceWorkflow(Integer workflowId, String toStatus, Integer id, String entity){
		AjaxOutput output = new AjaxOutput();
		List<ReviewStatus> statuses = null;
		ReviewStatus reviewStatus = ReviewStatus.valueOf(toStatus);
		if(entity.equals("report")){
			statuses = adverseEventRoutingAndReviewRepository.advanceReportWorkflow(workflowId, reviewStatus, id);
		}else if(entity.equals("reportingPeriod")){
			statuses = adverseEventRoutingAndReviewRepository.advanceReportingPeriodWorkflow(workflowId, reviewStatus, id);
		}
		output.setObjectContent(statuses);
		return output;
	}
	 
	public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
	public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
}
