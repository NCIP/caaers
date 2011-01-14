package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.Person;
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
        methodNameMap.put("addsiteResearchStaff", "addSiteResearchStaff");
        methodNameMap.put("removesiteResearchStaff", "removeSiteResearchStaff");
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
        
        if(command != null){
        	//Based on Person type create the right concrete class and set to the Person attribute in the command for later processing
        	if("ResearchStaff".equals(command.getPersonType())){
        		command.setPerson(command.getResearchStaff());
        	}else if("Investigator".equals(command.getPersonType())){
        		LocalInvestigator investigator = new LocalInvestigator();
        		investigator.setFirstName(command.getResearchStaff().getFirstName());
        		investigator.setMiddleName(command.getResearchStaff().getMiddleName());
        		investigator.setLastName(command.getResearchStaff().getLastName());
        		investigator.setEmailAddress(command.getResearchStaff().getEmailAddress());
        		investigator.setNciIdentifier(command.getResearchStaff().getNciIdentifier());
        		SiteInvestigator siteInv;
        		for(SiteResearchStaff srs : command.getResearchStaff().getSiteResearchStaffs()){
        			siteInv = new SiteInvestigator();
        			siteInv.setOrganization(srs.getOrganization());
        			siteInv.setInvestigator(investigator);
        			investigator.addSiteInvestigator(siteInv);
        		}
        		command.setPerson(investigator);
        	}else{
        		command.setPerson(null);
        	}
        	
        	//If Username is provided then we have to create CSMUSER for this person to be able to login to caAERS.
        	if(! StringUtils.isEmpty(command.getUser().getLoginName())){
        		command.getUser().getCsmUser().setLoginName(command.getUser().getLoginName());
        		command.getUser().getCsmUser().setFirstName(command.getResearchStaff().getFirstName());
        		command.getUser().getCsmUser().setLastName(command.getResearchStaff().getLastName());
        		command.getUser().getCsmUser().setEmailId(command.getResearchStaff().getEmailAddress());
        		command.getUser().getCsmUser().setStartDate(DateUtils.today());
        	
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
	        		command.getPerson().setCaaersUser(command.getUser());
	        	}
	        }else{
	        	if(command.getPerson() != null){
	        		command.getPerson().setCaaersUser(null);
	        	}
	        }
        }
    }
    
    @SuppressWarnings("unchecked")
	public ModelAndView addSiteResearchStaff(HttpServletRequest request, Object object, Errors errors) {
        UserCommand  command = (UserCommand)object;
        SiteResearchStaff srs = new SiteResearchStaff();
        command.getResearchStaff().addSiteResearchStaff(srs);
        srs.setResearchStaff(command.getResearchStaff());

        ModelAndView modelAndView = new ModelAndView("admin/ajax/sitePersonnelSection");
        modelAndView.getModel().put("indexes", new Integer[]{command.getResearchStaff().getSiteResearchStaffs().size() - 1});
        return modelAndView;
    }
    
    @SuppressWarnings("unchecked")
	public ModelAndView removeSiteResearchStaff(HttpServletRequest request, Object object, Errors errors) {
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
            command.getResearchStaff().getSiteResearchStaffs().remove(index);
        }
        int size = command.getResearchStaff().getSiteResearchStaffs().size();
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
    	
    	if(!command.getCreateAsPerson() && !command.getCreateAsUser()){
    		errors.reject("USER_PERSON_001", "Either Create as Person or Create as User or both must be checked");
    	}
        String em = command.getResearchStaff().getEmailAddress();
        if (em != null && !em.trim().equals("") && !GenericValidator.isEmail(em)) {
            errors.rejectValue("researchStaff.emailAddress", "USR_006", "Invalid email");
        }
        
        if(command.getCreateAsPerson()){
        	
        	List<SiteResearchStaff> srs = command.getResearchStaff().getSiteResearchStaffs();
        	if(CollectionUtils.isEmpty(srs)){
        		errors.reject("USR_005", "Provide at least one organization");
        	}
            
        	Person person = personRepository.getByEmailAddress(em);
            if(person != null){
            	errors.rejectValue("researchStaff.emailAddress", "USR_010");
            }
            
            for (int i=0; i<srs.size(); i++){
                if ((srs.get(i).getOrganization() == null || srs.get(i).getOrganization().getId() == null)){
                	errors.reject("USR_004", new Object[] {new Integer(i)}, "Provide the organization");
                }
                String email = srs.get(i).getEmailAddress();
                if ( email != null && !email.trim().equals("") && !GenericValidator.isEmail(email)){
                	errors.rejectValue(String.format("researchStaff.siteResearchStaffs[%d].emailAddress", i), "USR_006", "Invalid email");
                }
            }
        }
        
        if(command.getCreateAsUser()){
        	if(StringUtils.isEmpty(command.getUser().getLoginName())){
        		errors.rejectValue("user.loginName", "USR_014");
        	}
        	_User user = userRepository.getUserByLoginName(command.getUser().getLoginName());
        	if(user != null && user.getCsmUser() != null){
        		errors.rejectValue("user.loginName", "USR_001", "Username already taken");
        	}
        }
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