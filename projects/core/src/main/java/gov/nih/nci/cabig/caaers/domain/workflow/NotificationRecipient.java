package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the Persons assigned to a Task. They will receive a Notification once as task is created and that task is assigned
 * to them. It will also appear in their Inbox (task lists) when they login into the system.
 * @author Sameer Sawant
 */

@Entity
@Table(name = "wf_notification_recipient")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_notification_recipient_id")})
public class NotificationRecipient extends AbstractMutableDomainObject{
	
	private String name; //eg: user1 (login id) or (StudyPI - a role)
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
