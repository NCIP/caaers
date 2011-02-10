package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.User;
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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
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
        if(methodInvocationRequest(request)) return;

        bindPerson(command);
        bindUser(command);

    }
    
    @SuppressWarnings("unchecked")
	public ModelAndView addSitePerson(HttpServletRequest request, Object object, Errors errors) {
        UserCommand  command = (UserCommand)object;
        SitePerson sp = new SitePerson();
        sp.setStartDate(DateUtils.today());
        command.addSitePersonnel(sp);

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

    private void createNewPersonValidation(final UserCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors){
        String em = command.getEmailAddress() ;
        Person person = personRepository.getByEmailAddress(em);
        if(person != null)errors.rejectValue("emailAddress", "USR_010");

        if(StringUtils.isNotEmpty(command.getNciIdentifier())){
            person = personRepository.getByPersonIdentifier(command.getNciIdentifier());
            if(person != null) errors.rejectValue("nciIdentifier", "USR_018" );
        }

        List<SitePerson> sitePersonnel = command.getSitePersonnel();
        if(CollectionUtils.isEmpty(sitePersonnel)){
            errors.reject("USR_005", "Provide at least one organization");
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


    private void editPersonValidation(final UserCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors){
        String em = command.getEmailAddress();
        Person existingPerson = command.getPerson();

        ResearchStaffQuery rsQuery = new ResearchStaffQuery();
        rsQuery.setFiltered(true);
        rsQuery.filterByEmailAddress(em);
        if(existingPerson instanceof ResearchStaff) rsQuery.excludeHavingId(command.getPerson().getId());
        List<ResearchStaff> existingStaffs = personRepository.searchLocalResearchStaff(rsQuery);
        if(CollectionUtils.isNotEmpty(existingStaffs)){
           errors.rejectValue("emailAddress", "USR_010");
        }

        InvestigatorQuery invQuery = new InvestigatorQuery();
        invQuery.setFiltered(true);
        invQuery.filterByEmailAddress(em);
        if(existingPerson instanceof Investigator) invQuery.excludeHavingId(command.getPerson().getId());
        List<Investigator> existingInvs = personRepository.searchLocalInvestigator(invQuery);
        if(CollectionUtils.isNotEmpty(existingInvs)){
           errors.rejectValue("emailAddress", "USR_010");
        }

        if(StringUtils.isNotEmpty(command.getNciIdentifier())){

            rsQuery = new ResearchStaffQuery();
            rsQuery.setFiltered(true);
            rsQuery.filterByNciIdentifier(command.getNciIdentifier());
            if(existingPerson instanceof ResearchStaff) rsQuery.excludeHavingId(command.getPerson().getId());
            existingStaffs = personRepository.searchLocalResearchStaff(rsQuery);
            if(CollectionUtils.isNotEmpty(existingStaffs)){
               errors.rejectValue("nciIdentifier", "USR_018" );
            }

            invQuery = new InvestigatorQuery();
            invQuery.setFiltered(true);
            invQuery.filterByNciIdentifier(command.getNciIdentifier());
            if(existingPerson instanceof Investigator) invQuery.excludeHavingId(command.getPerson().getId());
            existingInvs = personRepository.searchLocalInvestigator(invQuery);
            if(CollectionUtils.isNotEmpty(existingInvs)){
               errors.rejectValue("nciIdentifier", "USR_018" );
            }
            
        }


        List<SitePerson> sitePersonnel = command.getSitePersonnel();
        if(CollectionUtils.isEmpty(sitePersonnel)){
            errors.reject("USR_005", "Provide at least one organization");
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


    
    @Override
    protected void validate(final UserCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
    	
        String em = command.getEmailAddress();
        if (em != null && !em.trim().equals("") && !GenericValidator.isEmail(em)) {
           errors.rejectValue("emailAddress", "USR_006", "Invalid email");
        }

    	if(command.getCreateMode()){
        	if(!command.getCreateAsPerson() && !command.getCreateAsUser()){
        		errors.reject("USER_PERSON_001", "Either Create as Person or Create as User or both must be checked");
        	}

              //another person should not be present.
           if(command.getCreateAsPerson() && em != null){
                createNewPersonValidation(command, commandBean, fieldGroups, errors);
           }

           if(command.getCreateAsUser()){
                if(StringUtils.isEmpty(command.getUserName())){
                    errors.rejectValue("userName", "USR_014");
                }
                User user = userRepository.getUserByLoginName(command.getUserName());
                if(user != null && user.getCsmUser() != null){
                    errors.rejectValue("userName", "USR_001", "Username already taken");
                }
           }

    	} else if(command.getEditMode()){
            //there should not be another person with the same details.
            if(command.getCreateAsPerson()){
                Person person = command.getPerson();
                if(person != null){
                   editPersonValidation(command, commandBean, fieldGroups, errors);
                }else{
                    //creating new person
                   createNewPersonValidation(command, commandBean, fieldGroups, errors);
                }
            }
            //only do user validation if already user don't exist. 
            if(command.getCreateAsUser() && (command.getUser() == null || command.getUser().getCsmUser().getUserId() == null )){
                if(StringUtils.isEmpty(command.getUserName())){
                    errors.rejectValue("userName", "USR_014");
                }
                User user = userRepository.getUserByLoginName(command.getUserName());
                if(user != null && user.getCsmUser() != null){
                    errors.rejectValue("userName", "USR_001", "Username already taken");
                }
            }

        }

    }


    private void bindPerson(UserCommand command){
      if(command.getCreateAsPerson()){
        if(StringUtils.equals("ResearchStaff", command.getPersonType())){
            command.setPerson(buildResearchStaff(command));
        }else if (StringUtils.equals("Investigator", command.getPersonType())){
            command.setPerson(buildInvestigator(command));
        }
      }

    }

    private void bindUser(UserCommand command){
       if(command.getCreateAsUser()){
           buildUser(command);
       }
    }

    /**
     * This method builds a LocalResearchStaff instance from the command object
     * @param command
     * @return
     */
	private ResearchStaff buildResearchStaff(UserCommand command){
        if(command.getPerson() == null || command.getPerson().getId() == null){
           ResearchStaff rs = new LocalResearchStaff();
           rs.setAddress(new Address());
           command.setPerson(rs);

        }
        ResearchStaff rs = (ResearchStaff) command.getPerson();
    	rs.setFirstName(command.getFirstName());
    	rs.setMiddleName(command.getMiddleName());
    	rs.setLastName(command.getLastName());
    	rs.setEmailAddress(command.getEmailAddress());
    	rs.setNciIdentifier(command.getNciIdentifier());
    	rs.setAddress(new Address());
    	SiteResearchStaff srs = null;
    	for(SitePerson sitePerson : command.getSitePersonnel()){
            if(sitePerson.getId() != null){
                srs = rs.findSiteResearchStaffById(sitePerson.getId());
            }else{
                srs = new SiteResearchStaff();
                rs.addSiteResearchStaff(srs);
            }

    		srs.setStartDate(DateUtils.today());
    		srs.setPhoneNumber(sitePerson.getPhoneNumber());
    		srs.setFaxNumber(sitePerson.getFaxNumber());
    		srs.setEmailAddress(sitePerson.getEmailAddress());
    		srs.setOrganization(sitePerson.getOrganization());
    		srs.setAddress(sitePerson.getAddress());
            srs.setStartDate(sitePerson.getStartDate());
            srs.setEndDate(sitePerson.getEndDate());

    	}
    	return rs;
    }

	/**
	 * This method builds a LocalInvestigator instance from the command object
	 * @param command
	 * @return
	 */
	private Investigator buildInvestigator(UserCommand command){
        if(command.getPerson() == null || command.getPerson().getId() == null){
            Investigator inv = new LocalInvestigator();
            command.setPerson(inv);
        }
    	Investigator inv = (Investigator)command.getPerson();
    	inv.setFirstName(command.getFirstName());
    	inv.setMiddleName(command.getMiddleName());
    	inv.setLastName(command.getLastName());
    	inv.setEmailAddress(command.getEmailAddress());
    	inv.setNciIdentifier(command.getNciIdentifier());
    	SiteInvestigator siteInv = null;
    	for(SitePerson sitePerson : command.getSitePersonnel()){
            if(sitePerson.getId() != null){
               siteInv = inv.findSiteInvestigatorById(sitePerson.getId());
            }
            else{
               siteInv = new SiteInvestigator();
               inv.addSiteInvestigator(siteInv);
            }

    		siteInv.setStartDate(DateUtils.today());
    		siteInv.setOrganization(sitePerson.getOrganization());
    		siteInv.setEmailAddress(sitePerson.getEmailAddress());
            siteInv.setStartDate(sitePerson.getStartDate());
            siteInv.setEndDate(sitePerson.getEndDate());

    	}
    	return inv;
    }

	/**
	 * This method builds a User instance from the command object
	 * @param command
	 * @return
	 */
    private User buildUser(UserCommand command){
    	if(command.getUser() == null){
            command.setUser(new User());
        }
        User user = command.getUser();
        command.getUser().setLoginName(command.getUserName());
    	user.setFirstName(command.getFirstName());
    	user.setLastName(command.getLastName());
    	user.setEmailAddress(command.getEmailAddress());
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