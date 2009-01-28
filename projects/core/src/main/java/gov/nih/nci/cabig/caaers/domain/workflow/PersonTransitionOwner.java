package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("p")
public class PersonTransitionOwner extends TransitionOwner {

	private User user;
	
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
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

}
