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
	
	protected String name;
	
	@Transient
	public abstract boolean isRole();
	@Transient
	public abstract boolean isUser();
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
}
