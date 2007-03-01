package gov.nih.nci.security.acegi.csm.authorization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.acegisecurity.Authentication;

import gov.nih.nci.security.AuthorizationManager;

public abstract class AbstractCSMAuthorizationCheck implements CSMAuthorizationCheck {
	
	
	private CSMObjectIdGenerator objectIdGenerator;

	private AuthorizationManager csmAuthorizationManager;

	public AuthorizationManager getCsmAuthorizationManager() {
		return csmAuthorizationManager;
	}

	public void setCsmAuthorizationManager(
			AuthorizationManager csmAuthorizationManager) {
		this.csmAuthorizationManager = csmAuthorizationManager;
	}

	public CSMObjectIdGenerator getObjectIdGenerator() {
		return objectIdGenerator;
	}

	public void setObjectIdGenerator(CSMObjectIdGenerator objectIdGenerator) {
		this.objectIdGenerator = objectIdGenerator;
	}
	
	public boolean checkAuthorization(Authentication authentication, String privilege, Object object){
		boolean isAuthorized = false;
		
		if(object != null){
			
			Collection collection = null;
			if(object.getClass().isArray()){
				collection = Arrays.asList((Object[])object);
			}else if(object instanceof Collection){
				collection = (Collection)object;
			}else{
				collection = new ArrayList();
				collection.add(object);
			}
			
			String[] objectIds = new String[collection.size()];
			int idx = 0;
			for(Iterator i = collection.iterator(); i.hasNext(); idx++){
				objectIds[idx] = getObjectIdGenerator().generateId(i.next());
			}
			isAuthorized = checkAuthorizationForObjectIds(authentication, privilege, objectIds);
			
		}
		
		return isAuthorized;
	}
	
	public boolean checkAuthorizationForObjectIds(Authentication authentication, String privilege, String[] objectIds){
		
		boolean isAuthorized = true;
		for(int i = 0; i < objectIds.length && isAuthorized; i++){
			isAuthorized = checkAuthorizationForObjectId(authentication, privilege, objectIds[i]);
		}
		return isAuthorized;
		
	}

}
