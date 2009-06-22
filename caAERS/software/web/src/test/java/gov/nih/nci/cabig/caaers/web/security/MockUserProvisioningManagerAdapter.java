/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class MockUserProvisioningManagerAdapter implements UserProvisioningManager {

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignGroupRoleToProtectionGroup(java.lang.String,
     *      java.lang.String, java.lang.String[])
     */
    public void assignGroupRoleToProtectionGroup(String arg0, String arg1, String[] arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignGroupsToUser(java.lang.String,
     *      java.lang.String[])
     */
    public void assignGroupsToUser(String arg0, String[] arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignOwners(java.lang.String,
     *      java.lang.String[])
     */
    public void assignOwners(String arg0, String[] arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignParentProtectionGroup(java.lang.String,
     *      java.lang.String)
     */
    public void assignParentProtectionGroup(String arg0, String arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignPrivilegesToRole(java.lang.String,
     *      java.lang.String[])
     */
    public void assignPrivilegesToRole(String arg0, String[] arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignProtectionElements(java.lang.String,
     *      java.lang.String[])
     */
    public void assignProtectionElements(String arg0, String[] arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignToProtectionGroups(java.lang.String,
     *      java.lang.String[])
     */
    public void assignToProtectionGroups(String arg0, String[] arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignUserRoleToProtectionGroup(java.lang.String,
     *      java.lang.String[], java.lang.String)
     */
    public void assignUserRoleToProtectionGroup(String arg0, String[] arg1, String arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignUserToGroup(java.lang.String,
     *      java.lang.String)
     */
    public void assignUserToGroup(String arg0, String arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#assignUsersToGroup(java.lang.String,
     *      java.lang.String[])
     */
    public void assignUsersToGroup(String arg0, String[] arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#createApplication(gov.nih.nci.security.authorization.domainobjects.Application)
     */
    public void createApplication(Application arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#createGroup(gov.nih.nci.security.authorization.domainobjects.Group)
     */
    public void createGroup(Group arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#createPrivilege(gov.nih.nci.security.authorization.domainobjects.Privilege)
     */
    public void createPrivilege(Privilege arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#createProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
     */
    public void createProtectionGroup(ProtectionGroup arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#createRole(gov.nih.nci.security.authorization.domainobjects.Role)
     */
    public void createRole(Role arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#createUser(gov.nih.nci.security.authorization.domainobjects.User)
     */
    public void createUser(User arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getApplicationById(java.lang.String)
     */
    public Application getApplicationById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getGroupById(java.lang.String)
     */
    public Group getGroupById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getGroups(java.lang.String)
     */
    public Set getGroups(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getObjects(gov.nih.nci.security.dao.SearchCriteria)
     */
    public List getObjects(SearchCriteria arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getOwners(java.lang.String)
     */
    public Set getOwners(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getPrivilegeById(java.lang.String)
     */
    public Privilege getPrivilegeById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getPrivileges(java.lang.String)
     */
    public Set getPrivileges(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getProtectionElementPrivilegeContextForGroup(java.lang.String)
     */
    public Set getProtectionElementPrivilegeContextForGroup(String arg0)
                    throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getProtectionElementPrivilegeContextForUser(java.lang.String)
     */
    public Set getProtectionElementPrivilegeContextForUser(String arg0)
                    throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getProtectionElements(java.lang.String)
     */
    public Set getProtectionElements(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupById(java.lang.String)
     */
    public ProtectionGroup getProtectionGroupById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupRoleContextForGroup(java.lang.String)
     */
    public Set getProtectionGroupRoleContextForGroup(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupRoleContextForUser(java.lang.String)
     */
    public Set getProtectionGroupRoleContextForUser(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getRoleById(java.lang.String)
     */
    public Role getRoleById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getUserById(java.lang.String)
     */
    public User getUserById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#getUsers(java.lang.String)
     */
    public Set getUsers(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyApplication(gov.nih.nci.security.authorization.domainobjects.Application)
     */
    public void modifyApplication(Application arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyGroup(gov.nih.nci.security.authorization.domainobjects.Group)
     */
    public void modifyGroup(Group arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyPrivilege(gov.nih.nci.security.authorization.domainobjects.Privilege)
     */
    public void modifyPrivilege(Privilege arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
     */
    public void modifyProtectionElement(ProtectionElement arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
     */
    public void modifyProtectionGroup(ProtectionGroup arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyRole(gov.nih.nci.security.authorization.domainobjects.Role)
     */
    public void modifyRole(Role arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#modifyUser(gov.nih.nci.security.authorization.domainobjects.User)
     */
    public void modifyUser(User arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeApplication(java.lang.String)
     */
    public void removeApplication(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeGroup(java.lang.String)
     */
    public void removeGroup(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeGroupFromProtectionGroup(java.lang.String,
     *      java.lang.String)
     */
    public void removeGroupFromProtectionGroup(String arg0, String arg1)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeGroupRoleFromProtectionGroup(java.lang.String,
     *      java.lang.String, java.lang.String[])
     */
    public void removeGroupRoleFromProtectionGroup(String arg0, String arg1, String[] arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removePrivilege(java.lang.String)
     */
    public void removePrivilege(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeProtectionElement(java.lang.String)
     */
    public void removeProtectionElement(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeProtectionElementsFromProtectionGroup(java.lang.String,
     *      java.lang.String[])
     */
    public void removeProtectionElementsFromProtectionGroup(String arg0, String[] arg1)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeProtectionGroup(java.lang.String)
     */
    public void removeProtectionGroup(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeRole(java.lang.String)
     */
    public void removeRole(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeUser(java.lang.String)
     */
    public void removeUser(String arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeUserFromGroup(java.lang.String,
     *      java.lang.String)
     */
    public void removeUserFromGroup(String arg0, String arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeUserFromProtectionGroup(java.lang.String,
     *      java.lang.String)
     */
    public void removeUserFromProtectionGroup(String arg0, String arg1)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.UserProvisioningManager#removeUserRoleFromProtectionGroup(java.lang.String,
     *      java.lang.String, java.lang.String[])
     */
    public void removeUserRoleFromProtectionGroup(String arg0, String arg1, String[] arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#assignProtectionElement(java.lang.String,
     *      java.lang.String)
     */
    public void assignProtectionElement(String arg0, String arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#assignProtectionElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void assignProtectionElement(String arg0, String arg1, String arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkOwnership(java.lang.String,
     *      java.lang.String)
     */
    public boolean checkOwnership(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
     *      javax.security.auth.Subject)
     */
    public boolean checkPermission(AccessPermission arg0, Subject arg1) throws CSException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission,
     *      java.lang.String)
     */
    public boolean checkPermission(AccessPermission arg0, String arg1) throws CSException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkPermission(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public boolean checkPermission(String arg0, String arg1, String arg2) throws CSException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkPermission(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean checkPermission(String arg0, String arg1, String arg2, String arg3)
                    throws CSException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkPermissionForGroup(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public boolean checkPermissionForGroup(String arg0, String arg1, String arg2)
                    throws CSException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#checkPermissionForGroup(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean checkPermissionForGroup(String arg0, String arg1, String arg2, String arg3)
                    throws CSException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#createProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
     */
    public void createProtectionElement(ProtectionElement arg0) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#deAssignProtectionElements(java.lang.String,
     *      java.lang.String)
     */
    public void deAssignProtectionElements(String arg0, String arg1) throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getAccessibleGroups(java.lang.String,
     *      java.lang.String)
     */
    public List getAccessibleGroups(String arg0, String arg1) throws CSException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getAccessibleGroups(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List getAccessibleGroups(String arg0, String arg1, String arg2) throws CSException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getApplication(java.lang.String)
     */
    public Application getApplication(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getApplicationContext()
     */
    public ApplicationContext getApplicationContext() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getPrincipals(java.lang.String)
     */
    public Principal[] getPrincipals(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getPrivilegeMap(java.lang.String,
     *      java.util.Collection)
     */
    public Collection getPrivilegeMap(String arg0, Collection arg1) throws CSException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getProtectionElement(java.lang.String)
     */
    public ProtectionElement getProtectionElement(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getProtectionElement(java.lang.String,
     *      java.lang.String)
     */
    public ProtectionElement getProtectionElement(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getProtectionElementById(java.lang.String)
     */
    public ProtectionElement getProtectionElementById(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getProtectionGroups()
     */
    public List getProtectionGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getProtectionGroups(java.lang.String)
     */
    public Set getProtectionGroups(String arg0) throws CSObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#getUser(java.lang.String)
     */
    public User getUser(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#initialize(java.lang.String)
     */
    public void initialize(String arg0) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#removeOwnerForProtectionElement(java.lang.String,
     *      java.lang.String[])
     */
    public void removeOwnerForProtectionElement(String arg0, String[] arg1)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#removeOwnerForProtectionElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void removeOwnerForProtectionElement(String arg0, String arg1, String arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#secureCollection(java.lang.String,
     *      java.util.Collection)
     */
    public Collection secureCollection(String arg0, Collection arg1) throws CSException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#secureObject(java.lang.String,
     *      java.lang.Object)
     */
    public Object secureObject(String arg0, Object arg1) throws CSException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#secureUpdate(java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    public Object secureUpdate(String arg0, Object arg1, Object arg2) throws CSException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#setAuditUserInfo(java.lang.String,
     *      java.lang.String)
     */
    public void setAuditUserInfo(String arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#setEncryptionEnabled(boolean)
     */
    public void setEncryptionEnabled(boolean arg0) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#setOwnerForProtectionElement(java.lang.String,
     *      java.lang.String[])
     */
    public void setOwnerForProtectionElement(String arg0, String[] arg1)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.AuthorizationManager#setOwnerForProtectionElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void setOwnerForProtectionElement(String arg0, String arg1, String arg2)
                    throws CSTransactionException {
        // TODO Auto-generated method stub

    }

}
