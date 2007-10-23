package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Priyatam
 */
public class CreateInvestigatorController extends InvestigatorController<Investigator> {

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		return createInvestigatorWithDesign();
	}

	@Override
	protected boolean suppressValidation(final HttpServletRequest request, final Object command) {
		return suppressValidation(request);
	}

	@Override
	protected void layoutTabs(final Flow<Investigator> flow) {
		flow.addTab(new InvestigatorTab());
	}

	private Investigator createInvestigatorWithDesign() {
		SiteInvestigator siteInvestigator = new SiteInvestigator();
		Investigator investigator = new Investigator();
		siteInvestigator.setInvestigator(investigator);	
		investigator.addSiteInvestigator(siteInvestigator);
		return investigator;
	}

}