package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * This class represents the Persons assigned to a Task. They will receive a Notification once as task is created and that task is assigned
 * to them. It will also appear in their Inbox (task lists) when they login into the system.
 * @author Sameer Sawant
 */

@Entity
@Table(name = "wf_role_recipient")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_role_recipient_id")})
public class RoleRecipient extends AbstractMutableDomainObject{
	
	private UserGroupType userGroupType; //eg: user1 (login id) or (StudyPI - a role)

	@Type(type = "userGroupType")
    @Column(name = "code")
	public UserGroupType getUserGroupType() {
		return userGroupType;
	}
	
	public void setUserGroupType(UserGroupType userGroupType) {
		this.userGroupType = userGroupType;
	}
}
