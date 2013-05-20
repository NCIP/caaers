/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

 
/**
 * Will hold the accessible objects for a role. 
 * @author: Biju Joseph
 */



public class IndexEntry {


    /** The entity ids. */
    private Integer entityId;

    private Integer privilege;

    /**
     * Instantiates a new index entry.
     *
     * @param entityId the entity ID
     */
    public IndexEntry(Integer entityId){
        this(entityId, 0);
    }
    public IndexEntry(Integer entityId, int privilege){
        this.entityId = entityId;
        this.privilege = privilege;
    }


    public Integer getEntityId() {
        return entityId;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void orPrivilege(int newPrivilege){privilege = privilege | newPrivilege; }

    public void addRole(UserGroupType... roles){
        for(UserGroupType role : roles)  {
            privilege = privilege | role.getPrivilege();
        }
    }

    public boolean  hasRoles()   {
        return privilege > 0;
    }



    /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        return String.valueOf(entityId) + " : " + privilege + ", [" + String.valueOf(UserGroupType.roles(privilege)) + "]\r\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexEntry that = (IndexEntry) o;

        if (!entityId.equals(that.entityId)) return false;
        if (!privilege.equals(that.privilege)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entityId.hashCode();
        result = 31 * result + privilege.hashCode();
        return result;
    }


}

