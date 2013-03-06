/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User interface related class. Created to capture / render User details.
 * @author Monish
 *
 */
public class UserCommand {
	
	//Attributes used for UI rendering purpose.
    boolean isPO = SecurityUtils.hasAuthorityOf(UserGroupType.person_and_organization_information_manager);
    boolean isUA = SecurityUtils.hasAuthorityOf(UserGroupType.user_administrator);
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailAddress;
	private String nciIdentifier;	
	private String personType = "";
	private List<SitePerson> sitePersonnel = new ArrayList<SitePerson>();
	private String userName;
	private HashMap<String, String> personTypeOptionsMap = new LinkedHashMap<String, String>();
	private boolean createAsPerson;
	private boolean createAsUser;
	private List<SuiteRoleMembershipHelper> roleMembershipHelper = new ArrayList<SuiteRoleMembershipHelper>();
	private Map<String,String> siteMap = new HashMap<String,String>();
	private Map<String,String> studyMap = new HashMap<String,String>();
	private boolean createMode;
	private boolean editMode;
	private User loggedInUser;
	
	//Attributes which will be processed to save data to db.
	private User user;
	private Person person;

    private Integer personId;
    private Integer userId;
    
	private List<SuiteRoleMembership> roleMemberships = new ArrayList<SuiteRoleMembership>();
    private String requestURL = "";

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public UserCommand() {
		personTypeOptionsMap.put("", "Please select");
		personTypeOptionsMap.put("Investigator", "Investigator");
		personTypeOptionsMap.put("ResearchStaff", "ResearchStaff");
	}
	
	/**
	 * This method prepares the command object for use in the UI. 
	 */
	public void buildRolesHelper() {
		Map<SuiteRole,SuiteRoleMembership> map = new HashMap<SuiteRole,SuiteRoleMembership>();
		for(SuiteRoleMembership srM : roleMemberships){
			map.put(srM.getRole(), srM);
		}
		SuiteRoleMembership srM = null;
		for (SuiteRole suiteRole : SuiteRole.values()) {
			SuiteRoleMembershipHelper rh = new SuiteRoleMembershipHelper();
			rh.setSuiteRole(suiteRole);
			if(map.containsKey(suiteRole)){
				rh.setChecked(true);
				if(suiteRole.isScoped()){
					srM = map.get(suiteRole);
					
					if(suiteRole.isStudyScoped()){
						if(srM.isAllStudies()){
							rh.setAllStudyAccess(true);
						}else{
							rh.setAllStudyAccess(false);
							rh.setStudies(srM.getStudyIdentifiers());
						}
					}
					
					if(srM.isAllSites()){
						rh.setAllSiteAccess(true);
					}else{
						rh.setAllSiteAccess(false);
						rh.setSites(srM.getSiteIdentifiers());
					}
				}
			}else{
				rh.setChecked(false);
			}
			roleMembershipHelper.add(rh);
		}
	}
	
	//Utility methods
	/**
	 * This method returns an array of all the available SuiteRoles.
	 */
	public SuiteRole[] getSuiteRoles() {
		return SuiteRole.values();
	}

	
	/**
	 * This method adds a given SuiteRoleMembership to the list of roleMemberships.
	 * @param roleMembership
	 */
	public void addRoleMembership(SuiteRoleMembership roleMembership){
		if(roleMemberships == null){
			roleMemberships = new ArrayList<SuiteRoleMembership>();
		}
		getRoleMemberships().add(roleMembership);
	}
	
	/**
	 * This method will return all the Global roles 
	 * @return
	 */
	public List<SuiteRole> getAllGlobalRoles(){
		List<SuiteRole> allGlobalRoles = new ArrayList<SuiteRole>();
        for (SuiteRole role : SuiteRole.values()) {
        	if(!role.isScoped()){
        		allGlobalRoles.add(role);
        	}
        }
		return allGlobalRoles;
	}
	
	/**
	 * This method will return all the Site scoped roles 
	 * @return
	 */
	public List<SuiteRole> getAllSiteScopedRoles(){
		List<SuiteRole> allSiteScopedRoles = new ArrayList<SuiteRole>();
        for (SuiteRole role : SuiteRole.values()) {
        	if(role.isSiteScoped()){
        		allSiteScopedRoles.add(role);
        	}
        }
		return allSiteScopedRoles;
	}
	
	/**
	 * This method will return all the Site & Study scoped roles.
	 * @return
	 */
	public List<SuiteRole> getAllStudyScopedRoles(){
		List<SuiteRole> allStudyScopedRoles = new ArrayList<SuiteRole>();
        for (SuiteRole role : SuiteRole.values()) {
        	if(role.isStudyScoped()){
        		allStudyScopedRoles.add(role);
        	}
        }
		return allStudyScopedRoles;
	}
	
	/**
	 * This method will return true if the logged is a user_administrator and has All Site access.
	 * @return
	 */
	public  boolean isUAAllSite(){
		RoleMembership uaRoleMembership = getLoggedInUser().getRoleMembershipMap().get(UserGroupType.user_administrator);
		if (uaRoleMembership != null){
			return uaRoleMembership.isAllSite();
		}else{
			return false;
		}
	}
	
	/**
	 * This method will return true if the logged is a person_and_organization_information_manager and has All Site access.
	 * @return
	 */
	public  boolean isPOAllSite(){
		RoleMembership poRoleMembership = getLoggedInUser().getRoleMembershipMap().get(UserGroupType.person_and_organization_information_manager);
		if (poRoleMembership != null){
			return poRoleMembership.isAllSite();
		}else{
			return false;
		}
	}
	
	//Setters & Getters for the private attributes if this class.

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SuiteRoleMembership> getRoleMemberships() {
		return roleMemberships;
	}

	public void setRoleMemberships(List<SuiteRoleMembership> roleMemberships) {
		this.roleMemberships = roleMemberships;
	}


	public List<SuiteRoleMembershipHelper> getRoleMembershipHelper() {
		return roleMembershipHelper;
	}

	public void setRoleMembershipHelper(
			List<SuiteRoleMembershipHelper> roleMembershipHelper) {
		this.roleMembershipHelper = roleMembershipHelper;
	}


	public Map<String, String> getSiteMap() {
		return siteMap;
	}


	public void setSiteMap(Map<String, String> siteMap) {
		this.siteMap = siteMap;
	}


	public Map<String, String> getStudyMap() {
		return studyMap;
	}

	public void setStudyMap(Map<String, String> studyMap) {
		this.studyMap = studyMap;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public HashMap<String, String> getPersonTypeOptionsMap() {
		return personTypeOptionsMap;
	}

	public void setPersonTypeOptionsMap(HashMap<String, String> personTypeOptionsMap) {
		this.personTypeOptionsMap = personTypeOptionsMap;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean getCreateAsPerson() {
		return createAsPerson;
	}

	public void setCreateAsPerson(boolean createAsPerson) {
		this.createAsPerson = createAsPerson;
	}

	public boolean getCreateAsUser() {
		return createAsUser;
	}

	public void setCreateAsUser(boolean createAsUser) {
		this.createAsUser = createAsUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getNciIdentifier() {
		return nciIdentifier;
	}

	public void setNciIdentifier(String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}

	public List<SitePerson> getSitePersonnel() {
		return sitePersonnel;
	}

	public void setSitePersonnel(List<SitePerson> sitePersonnel) {
		this.sitePersonnel = sitePersonnel;
	}
	
	public void addSitePersonnel(SitePerson sitePerson){
		getSitePersonnel().add(sitePerson);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getCreateMode() {
		return createMode;
	}

	public void setCreateMode(boolean createMode) {
		this.createMode = createMode;
	}

	public boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean getPO() {
		return isPO;
	}

	public boolean getUA() {
		return isUA;
	}

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToDay(){
        return DateUtils.formatDate(DateUtils.today());
    }

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
