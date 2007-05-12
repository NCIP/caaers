package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Study Controller for 'Create' Workflow
 * 
 * @author Priyatam
 */
public class CreateStudyController extends StudyController {
     
	/**
	 * Layout Tabs
	 * @param request - flow the Flow object
	 */
    protected void layoutTabs(Flow flow) {
        flow.addTab(new DetailsTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new DiseaseTab());
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));
    }

    /**
     * Create a nested object graph that Create Study Design needs
     *
     * @param request - HttpServletRequest
     * @throws ServletException
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        return createDefaultStudyWithDesign();
    }

    /* (non-Javadoc)
      * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish
      * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
      * java.lang.Object, org.springframework.validation.BindException)
      */
    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        Study study = (Study) command;
        studyDao.save(study);

        ModelAndView modelAndView = new ModelAndView("study_confirmation");
        modelAndView.addAllObjects(errors.getModel());
        response.sendRedirect("view?studyName=" + study.getShortTitle() + "&type=confirm");
        return null;
    }
    
}