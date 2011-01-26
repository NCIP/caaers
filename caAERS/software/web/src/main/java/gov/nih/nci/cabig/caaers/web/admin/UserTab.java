package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.repository.PersonRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Monish
 *
 */
public class UserTab extends TabWithFields<UserCommand>{

	private PersonRepository personRepository;
	private UserRepository userRepository;
	private OrganizationDao organizationDao;
	Map<String, String> methodNameMap = new HashMap<String, String>();
	
	public UserTab() {
		super("User Details", "User Details", "/admin/user");
        methodNameMap.put("addsitePerson", "addSitePerson");
        methodNameMap.put("removesitePerson", "removeSitePerson");
	}
	
	@Override
    public String getAJAXMethodInvAttrName() {
        return "_asynchronous";
    }

    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
    	return methodNameMap.get(task + currentItem);
    }	
	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(UserCommand command) {
		Map<String, InputFieldGroup> map = new HashMap<String, InputFieldGroup>();
		return map;
	}
	
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, UserCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        if (!StringUtils.isEmpty(request.getParameter("created")) && request.getParameter("created").equals("OK")) {
        	refdata.put("flashMessage", getMessage("MSG_USER.created", null, Locale.getDefault()));
        }  
        if (!StringUtils.isEmpty(request.getParameter("edited")) && request.getParameter("edited").equals("OK")) {
        	refdata.put("flashMessage", getMessage("MSG_USER.updated", null, Locale.getDefault()));
        }  
        
        return refdata;
    }
    
    @Override
    public void onBind(HttpServletRequest request, UserCommand command, Errors errors) {
        super.onBind(request, command, errors);
        
        if(methodInvocationRequest(request)) return;
        
    	//Based on Person type create the right concrete class and set to the Person attribute in the command for later processing
    	if("ResearchStaff".equals(command.getPersonType())){
    		command.setPerson(buildResearchStaff(command));
    	}else if("Investigator".equals(command.getPersonType())){
    		command.setPerson(buildInvestigator(command));
    	}else{
    		command.setPerson(null);
    	}
    	
    	//If Username is provided then we have to create CSMUSER for this person to be able to login to caAERS.
    	if(! StringUtils.isEmpty(command.getUserName()) && command.getCreateAsUser()){
    		command.setUser(buildUser(command));	
        }else{
        	if(command.getPerson() != null){
        		command.getPerson().setCaaersUser(null);
        	}
        }
    }
    
    @SuppressWarnings("unchecked")
	public ModelAndView addSitePerson(HttpServletRequest request, Object object, Errors errors) {
        UserCommand  command = (UserCommand)object;
        command.addSitePersonnel(new SitePerson());

        ModelAndView modelAndView = new ModelAndView("admin/ajax/sitePersonnelSection");
        modelAndView.getModel().put("indexes", new Integer[]{command.getSitePersonnel().size() - 1});
        return modelAndView;
    }
    
    @SuppressWarnings("unchecked")
	public ModelAndView removeSitePerson(HttpServletRequest request, Object object, Errors errors) {
    	UserCommand  command = (UserCommand)object;
        ModelAndView modelAndView = new ModelAndView("admin/ajax/sitePersonnelSection");

        // DELETE
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
        }

        if (index >= 0) {
            command.getSitePersonnel().remove(index);
        }
        int size = command.getSitePersonnel().size();
        Integer[] indexes = new Integer[size];
        for(int i = 0 ; i < size ; i++) {
            indexes[i] = size - (i + 1);
        }
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }
    
    @Override
    protected void validate(final UserCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
    	
    	super.validate(command, commandBean, fieldGroups, errors);
    	
    	if(command.getCreateMode()){
        	if(!command.getCreateAsPerson() && !command.getCreateAsUser()){
        		errors.reject("USER_PERSON_001", "Either Create as Person or Create as User or both must be checked");
        	}
            String em = command.getEmailAddress();
            if (em != null && !em.trim().equals("") && !GenericValidator.isEmail(em)) {
                errors.rejectValue("emailAddress", "USR_006", "Invalid email");
            }
            
            if(command.getCreateAsPerson()){
            	
            	List<SitePerson> sitePersonnel = command.getSitePersonnel();
            	if(CollectionUtils.isEmpty(sitePersonnel)){
            		errors.reject("USR_005", "Provide at least one organization");
            	}
                
            	Person person = personRepository.getByEmailAddress(em);
                if(person != null){
                	errors.rejectValue("emailAddress", "USR_010");
                }
                
                for (int i=0; i<sitePersonnel.size(); i++){
                    if ((sitePersonnel.get(i).getOrganization() == null || sitePersonnel.get(i).getOrganization().getId() == null)){
                    	errors.reject("USR_004", new Object[] {new Integer(i)}, "Provide the organization");
                    }
                    String email = sitePersonnel.get(i).getEmailAddress();
                    if ( email != null && !email.trim().equals("") && !GenericValidator.isEmail(email)){
                    	errors.rejectValue(String.format("sitePersonnel[%d].emailAddress", i), "USR_006", "Invalid email");
                    }
                }
            }
            
            if(command.getCreateAsUser()){
            	if(StringUtils.isEmpty(command.getUserName())){
            		errors.rejectValue("userName", "USR_014");
            	}
            	_User user = userRepository.getUserByLoginName(command.getUserName());
            	if(user != null && user.getCsmUser() != null){
            		errors.rejectValue("userName", "USR_001", "Username already taken");
            	}
            }
    	}
    }

    /**
     * This method builds a LocalResearchStaff instance from the command object
     * @param command
     * @return
     */
	private ResearchStaff buildResearchStaff(UserCommand command){
    	LocalResearchStaff rs = new LocalResearchStaff();
    	rs.setFirstName(command.getFirstName());
    	rs.setMiddleName(command.getMiddleName());
    	rs.setLastName(command.getLastName());
    	rs.setEmailAddress(command.getEmailAddress());
    	rs.setNciIdentifier(command.getNciIdentifier());
    	rs.setAddress(new Address());
    	SiteResearchStaff srs = null;
    	for(SitePerson sitePerson : command.getSitePersonnel()){
    		srs = new SiteResearchStaff();
    		srs.setStartDate(DateUtils.today());
    		srs.setPhoneNumber(sitePerson.getPhoneNumber());
    		srs.setFaxNumber(sitePerson.getFaxNumber());
    		srs.setEmailAddress(sitePerson.getEmailAddress());
    		srs.setOrganization(sitePerson.getOrganization());
    		srs.setAddress(sitePerson.getAddress());
    		rs.addSiteResearchStaff(srs);
    	}
    	return rs;
    }
    
	/**
	 * This method builds a LocalInvestigator instance from the command object
	 * @param command
	 * @return
	 */
	private Investigator buildInvestigator(UserCommand command){
    	LocalInvestigator inv = new LocalInvestigator();
    	inv.setFirstName(command.getFirstName());
    	inv.setMiddleName(command.getMiddleName());
    	inv.setLastName(command.getLastName());
    	inv.setEmailAddress(command.getEmailAddress());
    	inv.setNciIdentifier(command.getNciIdentifier());
    	SiteInvestigator siteInv = null;
    	for(SitePerson sitePerson : command.getSitePersonnel()){
    		siteInv = new SiteInvestigator();
    		siteInv.setStartDate(DateUtils.today());
    		siteInv.setOrganization(sitePerson.getOrganization());
    		siteInv.setEmailAddress(sitePerson.getEmailAddress());
    		inv.addSiteInvestigator(siteInv);
    	}
    	return inv;
    }
	
	/**
	 * This method builds a _User instance from the command object
	 * @param command
	 * @return
	 */
    private _User buildUser(UserCommand command){
    	_User user = new _User();
    	user.setLoginName(command.getUserName());
    	user.getCsmUser().setLoginName(command.getUserName());
    	user.getCsmUser().setFirstName(command.getFirstName());
    	user.getCsmUser().setLastName(command.getLastName());
    	user.getCsmUser().setEmailId(command.getEmailAddress());
    	user.getCsmUser().setStartDate(DateUtils.today());
	
    	if(command.getRoleMemberships() != null && command.getRoleMemberships().size() > 0){
    		command.getRoleMemberships().clear();
    	}
    	ProvisioningSessionFactory factory = new ProvisioningSessionFactory();
    	SuiteRoleMembership suiteRoleMembership = null;
    	for(SuiteRoleMembershipHelper roleMembershipHelper : command.getRoleMembershipHelper()){
    		if(roleMembershipHelper.getChecked()){
    			suiteRoleMembership = factory.createSuiteRoleMembership(roleMembershipHelper.getSuiteRole());
    			if(roleMembershipHelper.getSuiteRole().isScoped()){
    				if(roleMembershipHelper.getSuiteRole().isStudyScoped()){
    					if(roleMembershipHelper.getAllStudyAccess()){
    						suiteRoleMembership.forAllStudies();
    					}else{
    						suiteRoleMembership.forStudies(roleMembershipHelper.getStudies());
    					}
    				}
    				if(roleMembershipHelper.getAllSiteAccess()){
        				suiteRoleMembership.forAllSites();
        			}else{
        				suiteRoleMembership.forSites(roleMembershipHelper.getSites());
        			}
    			}
    			command.addRoleMembership(suiteRoleMembership);
    		}
    	}
    	if(command.getPerson() != null){
    		command.getPerson().setCaaersUser(user);
    	} 
    	return user;
    }
    
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}