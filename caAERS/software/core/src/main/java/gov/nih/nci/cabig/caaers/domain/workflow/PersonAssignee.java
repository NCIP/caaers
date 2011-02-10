package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
 

/**
 * Assignee of this type is associated to a User/ResearchStaff.
 *
 * @author Biju Joseph
 */
@Entity
@DiscriminatorValue("p")
public class PersonAssignee extends Assignee {
	
	/** The investigator. */
	private Investigator investigator;
	
	/** The research staff. */
	private ResearchStaff researchStaff;
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.workflow.Assignee#isRole()
	 */
	@Override
	@Transient
	public boolean isRole() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.workflow.Assignee#isPerson()
	 */
	@Override
	@Transient
	public boolean isUser() {
		return true;
	}
	
	/**
	 * Gets the person.
	 *
	 * @return the person
	 */
	@Transient
	public Person getPerson() {
		if(getResearchStaff() != null) return researchStaff;
		return getInvestigator();
	}
	
	/**
	 * Sets the person.
	 *
	 * @param user the new person
	 */
	public void setPerson(Person user) {
		if(user instanceof ResearchStaff) setResearchStaff((ResearchStaff)user); 
		else setInvestigator((Investigator)user);
	}
	
	/**
	 * Gets the investigator.
	 *
	 * @return the investigator
	 */
	@ManyToOne
    @JoinColumn(name = "investigator_id")
	public Investigator getInvestigator() {
		return investigator;
	}

	/**
	 * Sets the investigator.
	 *
	 * @param investigator the new investigator
	 */
	public void setInvestigator(Investigator investigator) {
		this.investigator = investigator;
	}

	/**
	 * Gets the research staff.
	 *
	 * @return the research staff
	 */
	@ManyToOne
    @JoinColumn(name = "researchstaff_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}

	/**
	 * Sets the research staff.
	 *
	 * @param researchStaff the new research staff
	 */
	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}
}
