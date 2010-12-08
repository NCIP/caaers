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
    	if (role == null) return 0;
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
    public void addEntityId(Integer id){
        entityIds.add(id);
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(role) + ", " + String.valueOf(entityIds) + "]";
    }
}

