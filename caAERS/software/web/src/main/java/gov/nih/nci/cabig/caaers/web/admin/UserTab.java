package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * 
 * @author Monish
 *
 */
public class UserTab extends TabWithFields<UserCommand>{

	private OrganizationDao organizationDao;
	
	public UserTab() {
		super("User Details", "User Details", "/admin/user");
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(UserCommand command) {
		Map<String, InputFieldGroup> map = new HashMap<String, InputFieldGroup>();
		return map;
	}
	
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, UserCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        return refdata;
    }
    
    @Override
    public void onBind(HttpServletRequest request, UserCommand command, Errors errors) {
        super.onBind(request, command, errors);
        
        if(command != null){
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
        }
    }
    
    @Override
    protected void validate(final UserCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
    	super.validate(command, commandBean, fieldGroups, errors);
    }

    
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}