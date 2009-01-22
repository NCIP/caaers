package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
/**
 * Assignee of this type is associated to a User/ResearchStaff
 * @author Biju Joseph
 *
 */
@Entity
@DiscriminatorValue("p")
public class PersonAssignee extends Assignee {
	
	private ResearchStaff researchStaff;
	
	@Override
	@Transient
	public boolean isRole() {
		return false;
	}

	@Override
	@Transient
	public boolean isUser() {
		return true;
	}
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}
	
	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}
	
	@Transient
	public User getUser(){
		return researchStaff;
	}
	@Transient
	private void setUser(ResearchStaff user) {
		this.researchStaff = user;
	}
}
