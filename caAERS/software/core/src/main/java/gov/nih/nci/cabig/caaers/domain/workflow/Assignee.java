package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the Persons assigned to a Task. They will receive a Notification once as task is created and that task is assigned
 * to them. It will also appear in their Inbox (task lists) when they login into the system.
 * @author Sameer Sawant
 * @author Biju Joseph
 */
@Entity
@Table(name = "wf_assignees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dtype")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "SEQ_WF_ASSIGNEES_ID")})
public abstract class Assignee extends AbstractMutableDomainObject {
	
	/** The name. */
	protected String name;
	
	/**
	 * Checks if is role.
	 *
	 * @return true, if is role
	 */
	@Transient
	public abstract boolean isRole();
	
	/**
	 * Checks if is user.
	 *
	 * @return true, if is user
	 */
	@Transient
	public abstract boolean isUser();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
