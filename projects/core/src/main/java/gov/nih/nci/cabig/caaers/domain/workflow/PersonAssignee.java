package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.Investigator;
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
	
	private Investigator investigator;
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
	
	@Transient
	public User getUser() {
		if(getResearchStaff() != null) return researchStaff;
		return getInvestigator();
	}
	
	public void setUser(User user) {
		if(user instanceof ResearchStaff) setResearchStaff((ResearchStaff)user); 
		else setInvestigator((Investigator)user);
	}
	
	@ManyToOne
    @JoinColumn(name = "investigator_id")
	public Investigator getInvestigator() {
		return investigator;
	}

	public void setInvestigator(Investigator investigator) {
		this.investigator = investigator;
	}

	@ManyToOne
    @JoinColumn(name = "researchstaff_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}

	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}
}
