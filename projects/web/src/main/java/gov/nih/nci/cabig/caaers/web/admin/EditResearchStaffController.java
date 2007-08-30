package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
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
		ResearchStaff researchStaff = researchStaffDao.getById(Integer
				.parseInt(request.getParameter("researchStaffId")));

		if (log.isDebugEnabled()) {
			log.debug("Retrieved ResearchStaff :" + String.valueOf(researchStaff));
		}

		return researchStaff;
	}

	@Override
	protected ResearchStaff save(final ResearchStaff researchStaff, final Errors errors) {
		if (errors.hasErrors()) {
			return researchStaff;
		}
		getDao().save(researchStaff);
		return researchStaff;
	}

	@Override
	protected boolean isSummaryEnabled() {
		return true;
	}

	@Override
	protected void layoutTabs(final Flow<ResearchStaff> flow) {
		flow.addTab(new ResearchStaffTab());

	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {
		ResearchStaff researchStaff = (ResearchStaff) command;
		researchStaffDao.merge(researchStaff);
		request.setAttribute("flashMessage", "Successfully updated the ResearchStaff");
		ModelAndView modelAndView = new ModelAndView("admin/research_staff_details");
		request.getSession().setAttribute(getFormSessionAttributeName(), command);
		return modelAndView;
	}

	@Override
	protected boolean shouldSave(final HttpServletRequest request, final ResearchStaff command,
			final Tab<ResearchStaff> tab) {
		// supress for ajax and delete requests
		return super.shouldSave(request, command, tab);
	}

}