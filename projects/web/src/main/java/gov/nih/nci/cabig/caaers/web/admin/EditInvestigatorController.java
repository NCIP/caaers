package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

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
		Investigator investigator = investigatorDao.getInvestigatorById(Integer.parseInt(request
				.getParameter("investigatorId")));

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
	protected boolean shouldSave(final HttpServletRequest request, final Investigator command,
			final Tab<Investigator> tab) {
		// supress for ajax and delete requests
		Object isAjax = findInRequest(request, "_isAjax");
		if (isAjax != null) {
			return false;
		}

		String action = (String) super.findInRequest(request, "_action");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
			return false;
		}
		return super.shouldSave(request, command, tab);

	}

}