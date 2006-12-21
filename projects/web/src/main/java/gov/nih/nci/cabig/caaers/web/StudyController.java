package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for the Study module.
 * This handles the Create/Update/List flows for the Study.
 * @author Sujith Vellat Thayyilthodi
 * @see gov.nih.nci.cabig.caaers.dao.StudyDao
 * @since 1.0
 * */
public final class StudyController extends CaaersAbstractFormController {

	private StudyDao studyDao;

	public StudyController() {
		setCommandClass(Study.class);
        setFormView("createStudy");
        setSuccessView("createStudy");
	}

	@Required
	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object oCommand, BindException errors)
			throws Exception {
		Study study = (Study) oCommand;
		studyDao.save(study);
		ModelAndView modelAndView = new ModelAndView(getSuccessView());
		modelAndView.addObject("study", study);
		modelAndView.addAllObjects(errors.getModel());
		return modelAndView;
	}
}