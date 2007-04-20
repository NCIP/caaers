package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran 
 */
@Entity
@Table
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_repoter_id")
    }
)
public class Reporter extends Person {
	
	@OneToMany
    @JoinColumn(name="person_id" ,nullable=false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN}) 
	public List<ContactMechanism> getContactMechanims() {
		return super.getContactMechanims();
	}
    
	public void setContactMechanims(List<ContactMechanism> contactMechanims) {
		super.setContactMechanims(contactMechanims);
	} 		
	
	public String getFirstName() {
		return super.getFirstName();
	}

	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}

	public String getLastName() {
		return super.getLastName();
	}

	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}	

	public String getMaidenName() {
		return super.getMaidenName();
	}

	public void setMaidenName(String maidenName) {
		super.setMaidenName(maidenName);
	}

	public String getMiddleName() {
		return super.getMiddleName();
	}

	public void setMiddleName(String middleName) {
		super.setMiddleName(middleName);
	}
}
