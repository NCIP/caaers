package gov.nih.nci.security.acegi.csm.authorization;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.Set;

public interface ExtendedCSMAuthorizationManager extends AuthorizationManager {
	
	/**
	 * Get the names of groups that the user belongs to.
	 * 
	 * @param userName
	 * @return
	 */
	String[] getGroupNames(String userName);
	
	Set getOwners(String objectId) throws CSObjectNotFoundException;
	
//	void modifyObject(Object obj) throws CSTransactionException;

}
