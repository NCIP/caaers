package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Priyatam
 */
public class EditStudyController extends StudyController {

	protected static final Log log = LogFactory.getLog(EditStudyController.class);
	
	public EditStudyController()
	{
		setBindOnNewForm(true);
	}	
	
	/**
	 * Create a nested object graph that Create Study Design needs 
	 * @param request - HttpServletRequest
	 * @throws ServletException
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		Study study = studyDao.getStudyDesignById(Integer.parseInt(request.getParameter("studyId")));
		if (study !=null)
		{
			log.debug("Retrieving Study Details for Id: "+study.getId());
		}
		return study;
	}
	
	protected boolean isUpdate() {
		return true;
	}
	
	protected boolean isSummaryEnabled() {
		return true;
	}
	
	/**
	 * Layout Tabs for Edit Workflow
	 * @param request - flow the Flow object
	 */
    protected void layoutTabs(Flow flow) {
        flow.addTab(new EmptyStudyTab("Review and Submit", "Review and Submit", "study/study_reviewsummary"));
    	flow.addTab(new DetailsTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new DiseaseTab());
    }
	
    /**
	 * Post processing : Study needs to be saved on every 'update' event
	 * @param request
	 * @param oCommand
	 * @param errors
	 * @param page
	 */
	@Override
	protected void afterPostProcessPage(HttpServletRequest request, Object oCommand, 
	    Errors errors, int page) {
		Study study = (Study) oCommand;
		if ("update".equals(request.getParameter("_action"))){
			try {
				log.debug("Updating Study");
				studyDao.save(study);
			} catch (RuntimeException e) {
				log.debug("Unable to update Study");
				e.printStackTrace();
			}
		}	
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
	 * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, 
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		// Redirect to Search page
		ModelAndView modelAndView= new ModelAndView(new RedirectView("searchstudy.do"));
    	return modelAndView;
	}

}