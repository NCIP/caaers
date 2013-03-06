/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.PersonRole;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

 
/**
 * The Class RoleTransitionOwner.
 */
@Entity
@DiscriminatorValue("r") 
public class RoleTransitionOwner extends TransitionOwner {

	
	/** The user role. */
	private PersonRole userRole;
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.workflow.TransitionOwner#isRole()
	 */
	@Override
	@Transient
	public boolean isRole() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.workflow.TransitionOwner#isPerson()
	 */
	@Override
	@Transient
	public boolean isPerson() {
		return false;
	}
	
	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	@Type(type = "personRoleType")
	@Column(name = "user_role_id")
	public PersonRole getUserRole() {
		return userRole;
	}
	
	/**
	 * Sets the user role.
	 *
	 * @param userRole the new user role
	 */
	public void setUserRole(PersonRole userRole) {
		this.userRole = userRole;
	}

}
