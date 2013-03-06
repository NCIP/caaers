/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Priyatam
 */
public class CreateInvestigatorController extends InvestigatorController<InvestigatorCommand> {
	
	private CaaersSecurityFacade securityFacade;

    public CaaersSecurityFacade getSecurityFacade() {
		return securityFacade;
	}

	public void setSecurityFacade(CaaersSecurityFacade securityFacade) {
		this.securityFacade = securityFacade;
	}

	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        Investigator inv =  createInvestigatorWithDesign();
        InvestigatorCommand invCommand = new InvestigatorCommand();
        invCommand.setInvestigator(inv);
        invCommand.setCsmUser(new gov.nih.nci.security.authorization.domainobjects.User());
        return invCommand;
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
    protected void layoutTabs(final Flow<InvestigatorCommand> flow) {
        flow.addTab(new InvestigatorTab());
    }

    private Investigator createInvestigatorWithDesign() {
    	
		boolean canCreateUsers = securityFacade != null
				&& securityFacade.checkAuthorization(SecurityUtils
						.getAuthentication(),
						"gov.nih.nci.cabig.caaers.domain.SiteInvestigatorRole",
						"UPDATE");
		SiteInvestigator siteInvestigator = new SiteInvestigator();
		Investigator investigator = new LocalInvestigator();
		siteInvestigator.setInvestigator(investigator);
		investigator.addSiteInvestigator(siteInvestigator);
		investigator.setAllowedToLogin(canCreateUsers);
		investigator.setWasLoginDisallowed(true);
		investigator.setWasLoginIdNull(true);
		siteInvestigator.setStartDate(DateUtils.today());
		return investigator;
    }
    
    @Override
    protected boolean shouldSave(HttpServletRequest request, InvestigatorCommand command, Tab<InvestigatorCommand> tab) {
    	
    	if(isAjaxRequest(request)) return false;
    	return super.shouldSave(request, command, tab);
    	
    }


     /**
     * In addition to the normal functionality, will check and store the information about proceeding with CSM operation.
     */
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object ocommand, BindException errors, int page) throws Exception {
       InvestigatorCommand command = (InvestigatorCommand) ocommand;
       super.onBindAndValidate(request, command, errors, page);
      if(StringUtils.isNotBlank(command.getInvestigator().getLoginId())){
          gov.nih.nci.security.authorization.domainobjects.User csmUser =  csmUserRepository.getCSMUserByName(command.getInvestigator().getLoginId());
          command.setShouldSync(csmUser != null);
          if(csmUser != null){
            command.setCsmUser(csmUser);
          }
          request.setAttribute("_csmProceed", ((InvestigatorCommand)command).canProceedCSMOperation() );
      }


    }

    /**
     *  In addition to the normal functionality, will check whether the user can proceed with the CSM operation.
     */
    @Override
    protected boolean isFinishRequest(HttpServletRequest request) {
        Boolean _csmProceed = (Boolean)request.getAttribute("_csmProceed");
        boolean canFinish = _csmProceed == null ? true :  _csmProceed.booleanValue() ;
        return canFinish && super.isFinishRequest(request);
    }

    public void populateSaveConfirmationMessage( Map refdata, HttpServletRequest request, Object oCommand, Errors errors, int page){
        Boolean _csmProceed = (Boolean)request.getAttribute("_csmProceed");
        if(_csmProceed == null || _csmProceed) super.populateSaveConfirmationMessage(refdata, request, oCommand, errors, page);
    }

}
