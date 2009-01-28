package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.PersonRole;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@DiscriminatorValue("r") 
public class RoleTransitionOwner extends TransitionOwner {

	
	private PersonRole userRole;
	
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
	
	@Type(type = "personRoleType")
	@Column(name = "user_role_id")
	public PersonRole getUserRole() {
		return userRole;
	}
	public void setUserRole(PersonRole userRole) {
		this.userRole = userRole;
	}

}
