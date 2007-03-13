package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;

import org.hibernate.SessionFactory;

public class DIAuthorizationDao implements AuthorizationDAO {
	
	private AuthorizationDAO impl;
	
	private SessionFactory sessionFactory;
	private String applicationContextName;
	
	public String getApplicationContextName() {
		return applicationContextName;
	}

	public void setApplicationContextName(String applicationContextName) {
		this.applicationContextName = applicationContextName;
	}

	public void init(){
		try{
			this.impl = new AuthorizationDAOImpl(getSessionFactory(), getApplicationContextName());
		}catch(Exception ex){
			throw new RuntimeException("Error instantiating AuthorizationDAOImpl: " + ex.getMessage(), ex);
		}
	}

	public void assignGroupRoleToProtectionGroup(String arg0, String arg1, String[] arg2) throws CSTransactionException {
		impl.assignGroupRoleToProtectionGroup(arg0, arg1, arg2);
	}

	public void assignGroupsToUser(String arg0, String[] arg1) throws CSTransactionException {
		impl.assignGroupsToUser(arg0, arg1);
	}

	public void assignOwners(String arg0, String[] arg1) throws CSTransactionException {
		impl.assignOwners(arg0, arg1);
	}

	public void assignParentProtectionGroup(String arg0, String arg1) throws CSTransactionException {
		impl.assignParentProtectionGroup(arg0, arg1);
	}

	public void assignPrivilegesToRole(String arg0, String[] arg1) throws CSTransactionException {
		impl.assignPrivilegesToRole(arg0, arg1);
	}

	public void assignProtectionElement(String arg0, String arg1, String arg2) throws CSTransactionException {
		impl.assignProtectionElement(arg0, arg1, arg2);
	}

	public void assignProtectionElement(String arg0, String arg1) throws CSTransactionException {
		impl.assignProtectionElement(arg0, arg1);
	}

	public void assignProtectionElements(String arg0, String[] arg1) throws CSTransactionException {
		impl.assignProtectionElements(arg0, arg1);
	}

	public void assignToProtectionGroups(String arg0, String[] arg1) throws CSTransactionException {
		impl.assignToProtectionGroups(arg0, arg1);
	}

	public void assignUserRoleToProtectionGroup(String arg0, String[] arg1, String arg2) throws CSTransactionException {
		impl.assignUserRoleToProtectionGroup(arg0, arg1, arg2);
	}

	public void assignUsersToGroup(String arg0, String[] arg1) throws CSTransactionException {
		impl.assignUsersToGroup(arg0, arg1);
	}

	public void assignUserToGroup(String arg0, String arg1) throws CSTransactionException {
		impl.assignUserToGroup(arg0, arg1);
	}

	public boolean checkOwnership(String arg0, String arg1) {
		return impl.checkOwnership(arg0, arg1);
	}

	public boolean checkPermission(AccessPermission arg0, String arg1) throws CSException {
		return impl.checkPermission(arg0, arg1);
	}

	public boolean checkPermission(AccessPermission arg0, Subject arg1) throws CSException {
		return impl.checkPermission(arg0, arg1);
	}

	public boolean checkPermission(String arg0, String arg1, String arg2, String arg3) throws CSException {
		return impl.checkPermission(arg0, arg1, arg2, arg3);
	}

	public boolean checkPermission(String arg0, String arg1, String arg2) throws CSException {
		return impl.checkPermission(arg0, arg1, arg2);
	}

	public boolean checkPermissionForGroup(String arg0, String arg1, String arg2, String arg3) throws CSException {
		return impl.checkPermissionForGroup(arg0, arg1, arg2, arg3);
	}

	public boolean checkPermissionForGroup(String arg0, String arg1, String arg2) throws CSException {
		return impl.checkPermissionForGroup(arg0, arg1, arg2);
	}

	public void createObject(Object arg0) throws CSTransactionException {
		impl.createObject(arg0);
	}

	public void deAssignProtectionElements(String arg0, String arg1) throws CSTransactionException {
		impl.deAssignProtectionElements(arg0, arg1);
	}

	public List getAccessibleGroups(String arg0, String arg1, String arg2) throws CSException {
		return impl.getAccessibleGroups(arg0, arg1, arg2);
	}

	public List getAccessibleGroups(String arg0, String arg1) throws CSException {
		return impl.getAccessibleGroups(arg0, arg1);
	}

	public Application getApplication() {
		return impl.getApplication();
	}

	public Application getApplication(String arg0) throws CSObjectNotFoundException {
		return impl.getApplication(arg0);
	}

	public ApplicationContext getApplicationContext() {
		return impl.getApplicationContext();
	}

	public Set getGroups(String arg0) throws CSObjectNotFoundException {
		return impl.getGroups(arg0);
	}

	public Object getObjectByPrimaryKey(Class arg0, String arg1) throws CSObjectNotFoundException {
		return impl.getObjectByPrimaryKey(arg0, arg1);
	}

	public List getObjects(SearchCriteria arg0) {
		return impl.getObjects(arg0);
	}

	public Set getOwners(String arg0) throws CSObjectNotFoundException {
		return impl.getOwners(arg0);
	}

	public Principal[] getPrincipals(String arg0) {
		return impl.getPrincipals(arg0);
	}

	public Collection getPrivilegeMap(String arg0, Collection arg1) throws CSException {
		return impl.getPrivilegeMap(arg0, arg1);
	}

	public Set getPrivileges(String arg0) throws CSObjectNotFoundException {
		return impl.getPrivileges(arg0);
	}

	public ProtectionElement getProtectionElement(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionElement(arg0);
	}

	public Set getProtectionElementPrivilegeContextForGroup(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionElementPrivilegeContextForGroup(arg0);
	}

	public Set getProtectionElementPrivilegeContextForUser(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionElementPrivilegeContextForUser(arg0);
	}

	public Set getProtectionElements(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionElements(arg0);
	}

	public ProtectionGroup getProtectionGroup(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionGroup(arg0);
	}

	public Set getProtectionGroupRoleContextForGroup(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionGroupRoleContextForGroup(arg0);
	}

	public Set getProtectionGroupRoleContextForUser(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionGroupRoleContextForUser(arg0);
	}

	public Set getProtectionGroups(String arg0) throws CSObjectNotFoundException {
		return impl.getProtectionGroups(arg0);
	}

	public Role getRole(String arg0) throws CSObjectNotFoundException {
		return impl.getRole(arg0);
	}

	public User getUser(String arg0) {
		return impl.getUser(arg0);
	}

	public Set getUsers(String arg0) throws CSObjectNotFoundException {
		return impl.getUsers(arg0);
	}

	public void modifyObject(Object arg0) throws CSTransactionException {
		impl.modifyObject(arg0);
	}

	public void removeGroupFromProtectionGroup(String arg0, String arg1) throws CSTransactionException {
		impl.removeGroupFromProtectionGroup(arg0, arg1);
	}

	public void removeGroupRoleFromProtectionGroup(String arg0, String arg1, String[] arg2) throws CSTransactionException {
		impl.removeGroupRoleFromProtectionGroup(arg0, arg1, arg2);
	}

	public void removeObject(Object arg0) throws CSTransactionException {
		impl.removeObject(arg0);
	}

	public void removeOwnerForProtectionElement(String arg0, String arg1, String arg2) throws CSTransactionException {
		impl.removeOwnerForProtectionElement(arg0, arg1, arg2);
	}

	public void removeOwnerForProtectionElement(String arg0, String[] arg1) throws CSTransactionException {
		impl.removeOwnerForProtectionElement(arg0, arg1);
	}

	public void removeProtectionElementsFromProtectionGroup(String arg0, String[] arg1) throws CSTransactionException {
		impl.removeProtectionElementsFromProtectionGroup(arg0, arg1);
	}

	public void removeUserFromGroup(String arg0, String arg1) throws CSTransactionException {
		impl.removeUserFromGroup(arg0, arg1);
	}

	public void removeUserFromProtectionGroup(String arg0, String arg1) throws CSTransactionException {
		impl.removeUserFromProtectionGroup(arg0, arg1);
	}

	public void removeUserRoleFromProtectionGroup(String arg0, String arg1, String[] arg2) throws CSTransactionException {
		impl.removeUserRoleFromProtectionGroup(arg0, arg1, arg2);
	}

	public Collection secureCollection(String arg0, Collection arg1) throws CSException {
		return impl.secureCollection(arg0, arg1);
	}

	public Object secureObject(String arg0, Object arg1) throws CSException {
		return impl.secureObject(arg0, arg1);
	}

	public Object secureUpdate(String arg0, Object arg1, Object arg2) throws CSException {
		return impl.secureUpdate(arg0, arg1, arg2);
	}

	public void setOwnerForProtectionElement(String arg0, String arg1, String arg2) throws CSTransactionException {
		impl.setOwnerForProtectionElement(arg0, arg1, arg2);
	}

	public void setOwnerForProtectionElement(String arg0, String[] arg1) throws CSTransactionException {
		impl.setOwnerForProtectionElement(arg0, arg1);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
