package gov.nih.nci.cabig.caaers.domain.security;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class RolePrivilege.
 */
@Entity
@Table(name = "role_privilege")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_role_privilege_id") })
public class RolePrivilege extends AbstractMutableDomainObject{
	
	/** The role name. */
	private String roleName;
	
	/** The object id. */
	private String objectId;
	
	/** The privilege. */
	private String privilege;
	
	/**
	 * Gets the role name.
	 *
	 * @return the role name
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/**
	 * Sets the role name.
	 *
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * Gets the object id.
	 *
	 * @return the object id
	 */
	public String getObjectId() {
		return objectId;
	}
	
	/**
	 * Sets the object id.
	 *
	 * @param objectId the new object id
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * Gets the privilege.
	 *
	 * @return the privilege
	 */
	public String getPrivilege() {
		return privilege;
	}
	
	/**
	 * Sets the privilege.
	 *
	 * @param privilege the new privilege
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
}
