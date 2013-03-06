/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    /** The role. */
    private UserGroupType role;
    
    /** The entity ids. */
    private List<Integer> entityIds;

    /**
     * Instantiates a new index entry.
     *
     * @param role the role
     */
    public IndexEntry(UserGroupType role){

        entityIds = new ArrayList<Integer>();
        this.role = role;
    }
   
    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public Integer getRoleCode(){
    	if (role == null) return 0;
        return role.getCode();
    }

    /**
     * Gets the entity ids.
     *
     * @return the entity ids
     */
    public List<Integer> getEntityIds() {
        return entityIds;
    }

    /**
     * Sets the entity ids.
     *
     * @param entityIds the new entity ids
     */
    public void setEntityIds(List<Integer> entityIds) {
        this.entityIds = entityIds;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public UserGroupType getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(UserGroupType role) {
        this.role = role;
    }
    
    /**
     * Adds the entity id.
     *
     * @param id the id
     */
    public void addEntityId(Integer id){
        entityIds.add(id);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + String.valueOf(role) + ", " + String.valueOf(entityIds) + "]";
    }
}

