/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
 * The Class PersonTransitionOwner.
 */
@Entity
@DiscriminatorValue("p")
public class PersonTransitionOwner extends TransitionOwner {

	/** The investigator. */
	private Investigator investigator;
	
	/** The research staff. */
	private ResearchStaff researchStaff;
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.workflow.TransitionOwner#isRole()
	 */
	@Override
	@Transient
	public boolean isRole() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.workflow.TransitionOwner#isPerson()
	 */
	@Override
	@Transient
	public boolean isPerson() {
		return true;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@Transient
	public Person getPerson() {
		if(getResearchStaff() != null) return researchStaff;
		return getInvestigator();
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
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
