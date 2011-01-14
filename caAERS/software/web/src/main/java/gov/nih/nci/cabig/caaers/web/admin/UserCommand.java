package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
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
	
	//Attributes to capture Basic User details.
	private _User user;
	//List which will hold all the RoleMemberships of the user.
	private List<SuiteRoleMembership> roleMemberships = new ArrayList<SuiteRoleMembership>();
	private List<SuiteRoleMembershipHelper> roleMembershipHelper = new ArrayList<SuiteRoleMembershipHelper>();
	private Map<String,String> siteMap = new HashMap<String,String>();
	private Map<String,String> studyMap = new HashMap<String,String>();
	
	private HashMap<String, String> personTypeOptionsMap = new LinkedHashMap<String, String>();
	private String personType;
	//ResearchStaff Class is used as a DTO here. 
	private ResearchStaff researchStaff;
	private Person person;
	
	private boolean createAsPerson = true;
	private boolean createAsUser = true;
	
    boolean isPO = SecurityUtils.hasAuthorityOf(UserGroupType.person_and_organization_information_manager);
    boolean isUA = SecurityUtils.hasAuthorityOf(UserGroupType.user_administrator);

	
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
	
	//Setters & Getters for the private attributes if this class.

	public _User getUser() {
		return user;
	}

	public void setUser(_User user) {
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

	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}

	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
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
}