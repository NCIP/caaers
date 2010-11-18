package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.security.CSMUser;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

import java.util.ArrayList;
import java.util.List;

/**
 * User interface related class. Created to capture / render User details.
 * @author Monish
 *
 */
public class UserCommand {
	
	//Attributes to capture Basic User details.
	private CSMUser csmUser;
	//List which will hold all the RoleMemberships of the user.
	private List<SuiteRoleMembership> roleMemberships;
	private List<SuiteRoleMembershipHelper> roleMembershipHelper = new ArrayList<SuiteRoleMembershipHelper>();
	
	public UserCommand() {
	}
	
	
	public void buildRolesHelper() {
		
		List<SuiteRole> userRoles = new ArrayList<SuiteRole>();
		if(roleMemberships != null){
			for(SuiteRoleMembership srM : roleMemberships){
				userRoles.add(srM.getRole());
			}
		}
		
		for (SuiteRole suiteRole : SuiteRole.values()) {
			SuiteRoleMembershipHelper rh = new SuiteRoleMembershipHelper();
			rh.setSuiteRole(suiteRole);
			if(userRoles.contains(suiteRole)){
				rh.setChecked(true);
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

	public CSMUser getCsmUser() {
		return csmUser;
	}

	public void setCsmUser(CSMUser csmUser) {
		this.csmUser = csmUser;
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

}