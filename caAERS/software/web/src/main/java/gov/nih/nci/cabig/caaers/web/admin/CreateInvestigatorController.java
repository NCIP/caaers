package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author Priyatam
 */
public class CreateInvestigatorController extends InvestigatorController<Investigator> {

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        return createInvestigatorWithDesign();
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        String action = request.getParameter("_action");
        if(isAjaxRequest(request) || targetPage < curPage) return true;
        return super.suppressValidation(request);
    }

    @Override
    protected void layoutTabs(final Flow<Investigator> flow) {
        flow.addTab(new InvestigatorTab());
    }

    private Investigator createInvestigatorWithDesign() {
        SiteInvestigator siteInvestigator = new SiteInvestigator();
        Investigator investigator = new LocalInvestigator();
        siteInvestigator.setInvestigator(investigator);
        investigator.addSiteInvestigator(siteInvestigator);
        investigator.setAllowedToLogin(Boolean.TRUE);
        investigator.setWasLoginDisallowed(true);
        investigator.setWasLoginIdNull(true);
        
        return investigator;
    }
    
    @Override
    protected boolean shouldSave(HttpServletRequest request, Investigator command, Tab<Investigator> tab) {
    	
    	if(isAjaxRequest(request)) return false;
    	return super.shouldSave(request, command, tab);
    	
    }

}