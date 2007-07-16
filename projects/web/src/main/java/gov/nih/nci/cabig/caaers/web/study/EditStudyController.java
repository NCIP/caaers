package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Priyatam
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public class EditStudyController extends StudyController<Study> {
	
	private static final Log log = LogFactory.getLog(EditStudyController.class);

	public EditStudyController() {
		setBindOnNewForm(true);
	}
	
	///LOGIC
	
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Study study = studyDao.getStudyDesignById(Integer.parseInt(request.getParameter("studyId")));
        if(log.isDebugEnabled()) log.debug("Retrieved Study :" + String.valueOf(study));
        return study;
    }    
    
    @Override
    protected boolean shouldSave(HttpServletRequest request, Study command, Tab<Study> tab) {
    	return false;
        /*return super.shouldSave(request, command, tab)
            && (request.getParameter("_action") == null || "".equals(request.getParameter("_action")));*/
    }
    
//    @Override
//	protected Object currentFormObject(HttpServletRequest request, Object sessionFormObject) throws Exception {
//		if (sessionFormObject != null) {
//			getDao().reassociate((Study) sessionFormObject);
//		}
//		return sessionFormObject;
//	}

	protected boolean isSummaryEnabled() {
        return true;
    }

	protected void layoutTabs(Flow<Study> flow) {
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));
        flow.addTab(new DetailsTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new DiseaseTab());
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, 
    		Object command, BindException errors) throws Exception {
    	 Study study = (Study) command;
    	 studyDao.merge(study);
        return new ModelAndView(new RedirectView("search"));
    }

}