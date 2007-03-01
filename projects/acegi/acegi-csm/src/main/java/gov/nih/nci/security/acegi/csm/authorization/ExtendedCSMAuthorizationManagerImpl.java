/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;

/**
 * @author joshua
 *
 */
public class ExtendedCSMAuthorizationManagerImpl extends
		UserProvisioningManagerImpl implements ExtendedCSMAuthorizationManager {
	
	/**
	 * @param arg0
	 * @throws CSConfigurationException
	 */
	public ExtendedCSMAuthorizationManagerImpl(String arg0)
			throws CSConfigurationException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws CSConfigurationException
	 */
	public ExtendedCSMAuthorizationManagerImpl(String arg0, HashMap arg1)
			throws CSConfigurationException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws CSConfigurationException
	 */
	public ExtendedCSMAuthorizationManagerImpl(String arg0, URL arg1)
			throws CSConfigurationException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws CSConfigurationException
	 */
	public ExtendedCSMAuthorizationManagerImpl(String arg0, String arg1,
			boolean arg2) throws CSConfigurationException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws CSConfigurationException
	 */
	public ExtendedCSMAuthorizationManagerImpl(String arg0, String arg1,
			boolean arg2, URL arg3) throws CSConfigurationException {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see exp.authentication.ExtendedCSMAuthorizationManager#getGroupNames(java.lang.String)
	 */
	public String[] getGroupNames(String userName) {

		String[] groupNames = null;
		
		User user = this.getUser(userName);
		if(user != null){
			Set groups;
			try {
				groups = this.getGroups(user.getUserId().toString());
			} catch (CSObjectNotFoundException ex) {
				throw new RuntimeException("Couldn't find groups for user '" + userName + "'", ex);
			}
			groupNames = new String[groups.size()];
			int idx = 0;
			for(Iterator i = groups.iterator(); i.hasNext(); idx++){
				Group group = (Group)i.next();
				groupNames[idx] = group.getGroupName();
			}
		}

		return groupNames;
	}


}
