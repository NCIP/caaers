
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a ResearchStaff Design. This uses
 * AbstractTabbedFlowFormController to implement tabbed workflow
 * @author Saurabh
 */
public abstract class ResearchStaffController<C extends ResearchStaff> extends
		AutomaticSaveFlowFormController<C, ResearchStaff, ResearchStaffDao> {

	private static final Log log = LogFactory.getLog(ResearchStaffController.class);

    private static final String CAAERS_SITE_CORDINATOR = "caaersSiteCordinator";
     private static final String CAAERS_STUDY_CORDINATOR = "caaersStudyCordinator";
     private static final String CAAERS_PARTICIPANT_CORDINATOR = "caaersParticipantCordinator";
     private static final String CAAERS_AE_CORDINATOR = "caaersAECordinator";

    protected ResearchStaffDao researchStaffDao;

    private OrganizationDao organizationDao;

	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public ResearchStaffController() {
		setCommandClass(ResearchStaff.class);
		Flow<C> flow = new Flow<C>("Create Research Staff");
		layoutTabs(flow);
		setFlow(flow);
		setAllowDirtyBack(false);
		setAllowDirtyForward(false);
	}

	// /LOGIC
	@Override
	protected ResearchStaff getPrimaryDomainObject(final C command) {
		return command;
	}

	@Required
	public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	@Override
	protected ResearchStaffDao getDao() {
		return researchStaffDao;
	}

	protected abstract void layoutTabs(Flow<C> flow);

	@Override
	protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));
        ControllerTools.registerEnumEditor(binder, UserGroupType.class);

    }

	@Override
	@SuppressWarnings("unchecked")
	protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors,
			final int page) throws Exception {
		Map<String, Object> refdata = super.referenceData(request, command, errors, page);
        ResearchStaff researchStaff= (ResearchStaff) command;
        refdata.put("isCaaersPartcipantCordinator",new Boolean(false));
        refdata.put("isCaaersStudyCordinator",new Boolean(false));
        refdata.put("isCaaersAECordinator",new Boolean(false));
        if(researchStaff.getUserGroupTypes().contains(UserGroupType.caaers_participant_cd)){
        refdata.put("isCaaersPartcipantCordinator",new Boolean(true));
        }if(researchStaff.getUserGroupTypes().contains(UserGroupType.caaers_study_cd)){
        refdata.put("isCaaersStudyCordinator",new Boolean(true));
        }if(researchStaff.getUserGroupTypes().contains(UserGroupType.caaers_ae_cd)){
        refdata.put("isCaaersAECordinator",new Boolean(true));
        }
        
        return refdata;
	}

	/**
	 * Override this in sub controller if summary is needed
	 * @return
	 */
	protected boolean isSummaryEnabled() {
		return false;
	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {
        ResearchStaff researchStaff = (ResearchStaff) command;

        populateUserRole(researchStaff,request);


       researchStaffDao.save(researchStaff);


       ModelAndView modelAndView = new ModelAndView("admin/research_staff_review");
       modelAndView.addAllObjects(errors.getModel());
       modelAndView.addObject("researchStaff", researchStaff);
       return modelAndView;


	}

    protected boolean findUsersRoleInRequest(final HttpServletRequest request, String parameterName){
        return request.getParameter(parameterName) != null && request.getParameter(parameterName) != null && request.getParameter(parameterName).equalsIgnoreCase("true");

    }

    protected void populateUserRole(ResearchStaff researchStaff , final HttpServletRequest request ) {
        researchStaff.setUserGroupTypes(new ArrayList<UserGroupType>());

        if(findUsersRoleInRequest(request,CAAERS_PARTICIPANT_CORDINATOR)){
            researchStaff.addUserGroupType(UserGroupType.caaers_participant_cd);

        }if(findUsersRoleInRequest(request,CAAERS_STUDY_CORDINATOR)){
            researchStaff.addUserGroupType(UserGroupType.caaers_study_cd);

        } if(findUsersRoleInRequest(request,CAAERS_AE_CORDINATOR)){
            researchStaff.addUserGroupType(UserGroupType.caaers_ae_cd);

        }

    }
}
