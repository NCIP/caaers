package gov.nih.nci.cabig.caaers.domain.workflow;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

/**
 * Assignee of this type will be associated to a Role.
 * @author Biju Joseph
 *
 */
@Entity
@DiscriminatorValue("r") 
public class RoleAssignee extends Assignee {
	
	private UserGroupType userRole;
	
	@Override
	@Transient
	public boolean isRole() {
		return true;
	}

	@Override
	@Transient
	public boolean isUser() {
		return false;
	}
	
	@Type(type = "userGroupType")
	@Column(name = "user_role_id")
	public UserGroupType getUserRole() {
		return userRole;
	}
	public void setUserRole(UserGroupType userRole) {
		this.userRole = userRole;
	}
	
}
