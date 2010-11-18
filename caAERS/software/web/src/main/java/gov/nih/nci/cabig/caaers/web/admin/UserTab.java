package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

public class UserTab extends TabWithFields<UserCommand>{

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
        	command.getRoleMemberships().clear();
        	ProvisioningSessionFactory factory = new ProvisioningSessionFactory();
        	for(SuiteRoleMembershipHelper roleMembershipHelper : command.getRoleMembershipHelper()){
        		if(roleMembershipHelper.getChecked()){
        			command.addRoleMembership(factory.createSuiteRoleMembership(roleMembershipHelper.getSuiteRole()));
        		}
        	}
        }
    }
    
    @Override
    protected void validate(final UserCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
    	super.validate(command, commandBean, fieldGroups, errors);
    }

}
