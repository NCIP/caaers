package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Priyatam
 */
public class EditStudyController extends StudyController {
    private static final Log log = LogFactory.getLog(EditStudyController.class);

    public EditStudyController() {
        setBindOnNewForm(true);
    }

    /**
     * Create a nested object graph that Create Study Design needs
     *
     * @param request - HttpServletRequest
     * @throws ServletException
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Study study = studyDao.getStudyDesignById(Integer.parseInt(request.getParameter("studyId")));
        if (study != null) {
            log.debug("Retrieving Study Details for Id: " + study.getId());
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
     *
     * @param request - flow the Flow object
     */
    protected void layoutTabs(Flow flow) {
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));
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
     */
    @Override
    protected void postProcessPage(
        HttpServletRequest request, Object oCommand, Errors errors, int page
    ) throws Exception {
        super.postProcessPage(request, oCommand, errors, page);
        Study study = (Study) oCommand;
        if ("update".equals(request.getParameter("_action"))) {
            try {
                log.debug("Updating Study");
                studyDao.save(study);
            } catch (RuntimeException e) {
                log.debug("Unable to update Study");
                throw e;
            }
        }
    }

    @Override
    protected ModelAndView processFinish(
        HttpServletRequest request, HttpServletResponse response, Object command, BindException errors
    ) throws Exception {
        // Redirect to Search page
        return new ModelAndView(new RedirectView("search"));
    }
}