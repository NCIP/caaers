package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.PersonRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * 
 * @author Monish
 *
 */
public abstract class UserController<C extends UserCommand> extends AutomaticSaveAjaxableFormController<C, User, UserDao>  {
	
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";	
	protected CaaersSecurityFacadeImpl caaersSecurityFacade;
	protected UserRepository userRepository;
	protected PersonRepository personRepository;
	protected ProvisioningSessionFactory proSessionFactory;
	protected OrganizationRepository organizationRepository;
	protected StudyRepository studyRepository;
	private OrganizationDao organizationDao;
    private EventFactory eventFactory;
	
	
	public UserController() {
        setCommandClass(UserCommand.class);
    }
	
    @Override
    protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));

    }
	
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
	
    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        Object subviewName = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "admin/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }
    
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        if (isAjaxRequest(request)) return true;
        return super.suppressValidation(request, command); 
    }
    

	@Override
	protected User getPrimaryDomainObject(C command) {
		return new User();
	}
	
	@Override
	protected UserDao getDao() {
		return null;
	}
    
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {
        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

    /**
     * Will create or modify CSM User
     * @param request
     * @param user
     */
	protected void createOrUpdateUser(HttpServletRequest request, User user){
		
		userRepository.createOrUpdateUser(user, ResetPasswordController.getURL(request.getScheme(), 
																					   request.getServerName(),
																					   request.getServerPort(),
																					   request.getContextPath()));
	}
	
	/**
	 * This method delegates the call to UserRepository to provision all the RoleMemberships for the given User in CSM.
	 * @param csmUser
	 * @param roleMemberships
	 */
	protected void processRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User csmUser, List<SuiteRoleMembership> roleMemberships){
		userRepository.provisionRoleMemberships(csmUser, roleMemberships);
	}
	
	//Setter & Getters.
	
	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = (CaaersSecurityFacadeImpl)caaersSecurityFacade;
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

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }
}