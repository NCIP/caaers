package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.security.authorization.domainobjects.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class CreateResearchStaffController extends ResearchStaffController<ResearchStaffCommand> {

    @Override
    protected void layoutTabs(final Flow<ResearchStaffCommand> flow) {
        flow.addTab(new ResearchStaffTab());
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
        ResearchStaff rs = new LocalResearchStaff();
        rs.setAddress(new Address());
        ResearchStaffCommand command = new ResearchStaffCommand();

        command.setResearchStaff(rs);
        command.setAllRoles(configPropertyRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE));

        // create new SiteResearchStaff
        SiteResearchStaff srs = new SiteResearchStaff();
        command.getResearchStaff().addSiteResearchStaff(srs);
        srs.setResearchStaff(command.getResearchStaff());
        command.addSiteResearchStaffCommandHelper();

        command.setCsmUser(new User());
        
        return command;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        if (isAjaxRequest(request)) return true;
        return super.suppressValidation(request, command); 
    }

    /**
     * In addition to the normal functionality, will check and store the information about proceeding with CSM operation.
     */
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object oCommand, BindException errors, int page) throws Exception {
      ResearchStaffCommand command = (ResearchStaffCommand) oCommand;
      super.onBindAndValidate(request, command, errors, page);
      if(StringUtils.isNotBlank(command.getResearchStaff().getLoginId())){
         gov.nih.nci.security.authorization.domainobjects.User csmUser =  csmUserRepository.getCSMUserByName(command.getResearchStaff().getLoginId());
         command.setShouldSync(csmUser != null);
         if(csmUser != null){
           command.setCsmUser(csmUser);
         } else {
        	 command.setCsmUser(new User());
         }
         request.setAttribute("_csmProceed", ((ResearchStaffCommand)command).canProceedCSMOperation() );
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