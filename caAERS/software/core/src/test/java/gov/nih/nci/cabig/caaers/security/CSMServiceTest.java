/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.PrivilegeSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CSMServiceTest extends TestCase {

    private ApplicationContext ctx;

    /**
     * 
     */
    public CSMServiceTest() {
        init();
    }

    /**
     * @param arg0
     */
    public CSMServiceTest(String name) {
        super(name);
        init();
    }

    public void init() {
    	this.ctx = CaaersContextLoader.getApplicationContext();
    }

    public void testCreateRemovePolicy() {
        try {
            // String loginName = "user";
            // User user1 = buildUser(null, loginName, "one");
            // Group group1 = buildGroup(null, "group_one");
            // assignUserToGroup(user1, group1);
            //			
            // String objectId = "org.borg.Cube";
            // ProtectionElement pe1 = buildProtectionElement(null, objectId);
            // ProtectionGroup pGroup1 = buildProtectionGroup(null, objectId);
            // assignProtectionElementToProtectionGroup(pe1, pGroup1);
            //			
            // String privilege = "CREATE";
            // String roleName = objectId + "." + privilege;
            // Role role1 = buildRole(null, roleName);
            // assignPrivilegeToRole(privilege, role1);
            //			
            // assignProtectionGroupAndRoleToGroup(pGroup1, role1, group1);
            // boolean authorized =
            // getUserProvisioningManager().checkPermission(user1.getLoginName(), objectId,
            // privilege);
            // assertTrue("Should be authorized", authorized);
            //			
            // removeProtectionGroupAndRoleFromGroup(pGroup1, role1, group1);
            // authorized = getUserProvisioningManager().checkPermission(user1.getLoginName(),
            // objectId, privilege);
            // assertFalse("Should not be authorized", authorized);
            //			
            // removePrivilegeFromRole(privilege, role1);
            // deleteRole(role1);
            // removeProtectionElementFromProtectionGroup(pe1, pGroup1);
            // deleteProtectionGroup(pGroup1);
            // deleteProtectionElement(pe1);
            // removeUserFromGroup(user1, group1);
            // deleteGroup(group1);
            // deleteUser(user1);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

    public void deleteUser(User user) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        userProvisioningManager.removeUser(user.getUserId().toString());
    }

    public void deleteGroup(Group group) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        userProvisioningManager.removeGroup(group.getGroupId().toString());
    }

    public void removeUserFromGroup(User user, Group group) throws Exception {
        String groupId = group.getGroupId().toString();
        String userId = user.getUserId().toString();
        getUserProvisioningManager().removeUserFromGroup(groupId, userId);
    }

    public void deleteProtectionElement(ProtectionElement protectionElement) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        userProvisioningManager.removeProtectionElement(protectionElement.getProtectionElementId()
                        .toString());
    }

    public void deleteProtectionGroup(ProtectionGroup protectionGroup) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        userProvisioningManager.removeProtectionGroup(protectionGroup.getProtectionGroupId()
                        .toString());
    }

    public void deleteRole(Role role) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        userProvisioningManager.removeRole(role.getId().toString());
    }

    public void removeProtectionElementFromProtectionGroup(ProtectionElement protectionElement,
                    ProtectionGroup protectionGroup) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        String protectionGroupId = protectionGroup.getProtectionGroupId().toString();
        String[] protectionElementIds = new String[] { protectionElement.getProtectionElementId()
                        .toString() };
        userProvisioningManager.removeProtectionElementsFromProtectionGroup(protectionGroupId,
                        protectionElementIds);
    }

    public void removePrivilegeFromRole(String privilegeName, Role role) throws Exception {
        Privilege privilege = getPrivilege(privilegeName);
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        String roleId = role.getId().toString();
        Set privilegeIdsSet = new HashSet();
        Set privileges = userProvisioningManager.getPrivileges(roleId);
        for (Iterator i = privileges.iterator(); i.hasNext();) {
            Privilege p = (Privilege) i.next();
            String pName = p.getName();
            if (!pName.equals(privilegeName)) {
                privilegeIdsSet.add(p.getId().toString());
            }
        }
        String[] privilegeIds = (String[]) privilegeIdsSet.toArray(new String[privilegeIdsSet
                        .size()]);
        userProvisioningManager.assignPrivilegesToRole(roleId, privilegeIds);
    }

    public void removeProtectionGroupAndRoleFromGroup(ProtectionGroup protectionGroup, Role role,
                    Group group) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        String protectionGroupId = protectionGroup.getProtectionGroupId().toString();
        String groupId = group.getGroupId().toString();
        String[] roleIds = new String[] { role.getId().toString() };
        userProvisioningManager.removeGroupRoleFromProtectionGroup(protectionGroupId, groupId,
                        roleIds);
    }

    // public String makeUnique(String s){
    // return s;
    // String us = String.valueOf(System.currentTimeMillis());
    // return s + us.substring(us.length() - 5);
    // }

    public void assignProtectionGroupAndRoleToGroup(ProtectionGroup protectionGroup, Role role,
                    Group group) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        String protectionGroupId = protectionGroup.getProtectionGroupId().toString();
        String groupId = group.getGroupId().toString();
        String[] roleIds = new String[] { role.getId().toString() };
        userProvisioningManager.assignGroupRoleToProtectionGroup(protectionGroupId, groupId,
                        roleIds);
    }

    public void assignPrivilegeToRole(String privilegeName, Role role) throws Exception {
        Privilege privilege = getPrivilege(privilegeName);
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        String roleId = role.getId().toString();
        String[] privilegeIds = new String[] { privilege.getId().toString() };
        userProvisioningManager.assignPrivilegesToRole(roleId, privilegeIds);
    }

    public void assignProtectionElementToProtectionGroup(ProtectionElement protectionElement,
                    ProtectionGroup protectionGroup) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        String protectionGroupId = protectionGroup.getProtectionGroupId().toString();
        String[] protectionElementIds = new String[] { protectionElement.getProtectionElementId()
                        .toString() };
        userProvisioningManager.assignProtectionElements(protectionGroupId, protectionElementIds);
    }

    public Privilege getPrivilege(String privilegeName) throws Exception {
        Privilege privilegeProt = new Privilege();
        privilegeProt.setName(privilegeName);
        SearchCriteria searchCriteria = new PrivilegeSearchCriteria(privilegeProt);
        List list = getUserProvisioningManager().getObjects(searchCriteria);
        return (Privilege) list.get(0);
    }

    public Role buildRole(String roleId, String roleName) throws Exception {
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();

        Role role = null;

        if (isEmpty(roleId)) {
            role = new Role();
        } else {
            role = userProvisioningManager.getRoleById(roleId);
        }

        role.setName(roleName);
        role.setDesc(roleName);
        role.setActive_flag((byte) 1);

        if (isEmpty(roleId)) {
            userProvisioningManager.createRole(role);
        } else {
            userProvisioningManager.modifyRole(role);
        }
        return role;
    }

    public ProtectionGroup buildProtectionGroup(String protectionGroupId, String protectionGroupName)
                    throws Exception {

        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        ProtectionGroup protectionGroup;

        if (isEmpty(protectionGroupId)) {
            protectionGroup = new ProtectionGroup();
        } else {
            protectionGroup = userProvisioningManager.getProtectionGroupById(protectionGroupId);
        }

        protectionGroup.setProtectionGroupName(protectionGroupName);
        protectionGroup.setProtectionGroupDescription(protectionGroupName);
        protectionGroup.setLargeElementCountFlag((byte) 0);

        if (isEmpty(protectionGroupId)) {
            userProvisioningManager.createProtectionGroup(protectionGroup);
        } else {
            userProvisioningManager.modifyProtectionGroup(protectionGroup);
        }
        return protectionGroup;
    }

    public ProtectionElement buildProtectionElement(String peId, String objectId) throws Exception {
        UserProvisioningManager userProvisioningManager = (UserProvisioningManager) getUserProvisioningManager();
        ProtectionElement protectionElement = null;
        if (isEmpty(peId)) {
            protectionElement = new ProtectionElement();
        } else {
            protectionElement = userProvisioningManager.getProtectionElementById(peId);
        }

        protectionElement.setProtectionElementName(objectId);
        protectionElement.setProtectionElementDescription(objectId);
        protectionElement.setProtectionElementType(objectId);
        protectionElement.setObjectId(objectId);
        protectionElement.setAttribute(null);

        if (isEmpty(peId)) {
            userProvisioningManager.createProtectionElement(protectionElement);
        } else {
            protectionElement.setProtectionElementId(new Long(peId));
            userProvisioningManager.modifyProtectionElement(protectionElement);
        }
        return protectionElement;
    }

    public void assignUserToGroup(User user, Group group) throws Exception {
        String groupId = group.getGroupId().toString();
        String[] userIds = new String[] { user.getUserId().toString() };
        getUserProvisioningManager().assignUsersToGroup(groupId, userIds);
    }

    public boolean isEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }

    public UserProvisioningManager getUserProvisioningManager() {
        return (UserProvisioningManager) this.ctx.getBean("csmUserProvisioningManager");
    }

    public Group buildGroup(String groupId, String groupName) throws Exception {

        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        Group group = null;
        if (isEmpty(groupId)) {
            group = new Group();
        } else {
            group = userProvisioningManager.getGroupById(groupId);
        }
        group.setGroupName(groupName);
        group.setGroupDesc(groupName);

        if (isEmpty(groupId)) {
            userProvisioningManager.createGroup(group);
        } else {
            userProvisioningManager.modifyGroup(group);
        }
        return group;
    }

    public User buildUser(String userId, String firstName, String lastName) throws Exception {
        User user = new User();
        UserProvisioningManager userProvisioningManager = getUserProvisioningManager();
        if (isEmpty(userId)) {
            user = new User();
        } else {
            user = userProvisioningManager.getUserById(userId);
        }

        user.setLoginName(firstName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setOrganization(firstName + "_" + lastName + "_org");
        user.setDepartment(firstName + "_" + lastName + "_dept");
        user.setTitle(firstName + "_" + lastName + "_title");
        user.setPhoneNumber("8675309");
        user.setPassword(firstName + "_" + lastName);
        user.setEmailId(firstName + "_" + lastName + "_email");

        if (isEmpty(userId)) {
            userProvisioningManager.createUser(user);
        } else {
            userProvisioningManager.modifyUser(user);
        }
        return user;
    }

}
