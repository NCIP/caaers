/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import java.util.Collection;

import org.acegisecurity.Authentication;

/**
 * Assumes that the required privilege should be determined from examining the
 * object itself, if the privilege is not explicitly supplied.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public abstract class AbstractObjectPrivilegeCSMAuthorizationCheck extends
		AbstractCSMAuthorizationCheck {

	private CSMPrivilegeGenerator privilegeGenerator;

	public CSMPrivilegeGenerator getPrivilegeGenerator() {
		return privilegeGenerator;
	}

	public void setPrivilegeGenerator(CSMPrivilegeGenerator privilegeGenerator) {
		this.privilegeGenerator = privilegeGenerator;
	}
	
	/*
	 * If the given privilege is empty (null or empty string) and the given object
	 * is not an arrary or collection, then this method will invoke the supplied
	 * CSMPrivilegeGenerator on the given object before delegating to the 
	 * AbstractCSMAuthorizationCheck implementation.
	 * 
	 * @see gov.nih.nci.security.acegi.csm.authorization.AbstractCSMAuthorizationCheck#checkAuthorization(org.acegisecurity.Authentication, java.lang.String, java.lang.Object)
	 */
	public boolean checkAuthorization(Authentication authentication, String privilege, Object object){
		String resolvedPrivilege = privilege;
		
		if(object != null && isEmpty(resolvedPrivilege) && !isCollection(object)){
			resolvedPrivilege = getPrivilegeGenerator().generatePrivilege(object);
		}
		
		return super.checkAuthorization(authentication, resolvedPrivilege, object);
	}


	private boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}
	
	private boolean isCollection(Object object){
		return object.getClass().isArray() || object instanceof Collection;
	}

}
