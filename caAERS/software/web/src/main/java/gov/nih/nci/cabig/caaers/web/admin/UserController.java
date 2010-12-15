package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao._UserDao;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Monish
 *
 */
public class UserController<C extends UserCommand> extends AutomaticSaveAjaxableFormController<C, gov.nih.nci.cabig.caaers.domain._User, _UserDao>  {
	
	protected CaaersSecurityFacadeImpl caaersSecurityFacade;
	protected UserRepository userRepository;
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	protected ProvisioningSessionFactory proSessionFactory;
	protected OrganizationRepository organizationRepository;
	protected StudyRepository studyRepository;
	
	@Override
    public FlowFactory<C> getFlowFactory() {
        return new FlowFactory<C>() {
            @SuppressWarnings("unchecked")
			public Flow<C> createFlow(C cmd) {
                Flow<C> flow = new Flow<C>("Create User");
                flow.addTab((Tab<C>)new UserTab());
                return flow;
        }
    };
    }
	
	public UserController() {
        setCommandClass(UserCommand.class);
    }
	
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		UserCommand command = new UserCommand();
		command.setUser(new _User());
		return command;
	}
	
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,HttpServletResponse response, Object userCommand, BindException errors) throws Exception {
        UserCommand command = (UserCommand)userCommand;
        _User user = command.getUser();
        boolean isCreateMode = user.getCsmUser() == null || user.getCsmUser().getUserId() == null;
        try {
			createOrUpdateUser(request,user);
        }catch (MailException e) {
            logger.error("Could not send email to user.", e);
        }
        processRoleMemberships(user.getCsmUser(),command.getRoleMemberships());
        String message = isCreateMode ? "created=OK" : "edited=OK";
        ModelAndView modelAndView = new ModelAndView("redirect:editUser?userName=" + user.getLoginName() + "&" + message);
        modelAndView.addAllObjects(errors.getModel());
		return modelAndView;
	}
	
	
	/**
	 * This method creates a User in CSM.
	 * @param request
	 * @param csmUser
	 * @return
	 */
	protected void createOrUpdateUser(HttpServletRequest request,_User user){
		
		userRepository.createOrUpdateUser(user, ResetPasswordController.getURL(request.getScheme(), 
																					   request.getServerName(),
																					   request.getServerPort(),
																					   request.getContextPath()));
	}
	
	/**
	 * This method delegates the call to CaaersSecurityFacade to provision all the RoleMemberships for the given User in CSM.
	 * @param csmUser
	 * @param roleMemberships
	 */
	protected void processRoleMemberships(User csmUser, List<SuiteRoleMembership> roleMemberships){
		caaersSecurityFacade.provisionRoleMemberships(csmUser, roleMemberships);
	}
	
	
	
	//Setter & Getters.
	
	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = (CaaersSecurityFacadeImpl)caaersSecurityFacade;
	}

	@Override
	protected _User getPrimaryDomainObject(C command) {
		// TODO Auto-generated method stub
		return command.getUser();
	}

	@Override
	protected _UserDao getDao() {
		return null;
	}

	public ProvisioningSessionFactory getProSessionFactory() {
		return proSessionFactory;
	}

	public void setProSessionFactory(ProvisioningSessionFactory proSessionFactory) {
		this.proSessionFactory = proSessionFactory;
	}

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public StudyRepository getStudyRepository() {
		return studyRepository;
	}

	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
}