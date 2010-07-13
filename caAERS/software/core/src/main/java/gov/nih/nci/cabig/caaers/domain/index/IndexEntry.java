package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Will hold the accessible objects for a role. 
 * @author: Biju Joseph
 */



public class IndexEntry {

    private UserGroupType role;
    private List<Integer> entityIds;

    public IndexEntry(UserGroupType role){

        entityIds = new ArrayList<Integer>();
        this.role = role;
    }
   
    public Integer getRoleCode(){
        return role.getCode();
    }

    public List<Integer> getEntityIds() {
        return entityIds;
    }

    public void setEntityIds(List<Integer> entityIds) {
        this.entityIds = entityIds;
    }

    public UserGroupType getRole() {
        return role;
    }

    public void setRole(UserGroupType role) {
        this.role = role;
    }
}

