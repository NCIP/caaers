package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 * @author Kulasekaran
 * @version 1.0
 */
//@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends AbstractIdentifiableDomainObject
{			
	private String firstName;
    private String middleName;
    private String maidenName;
    private String lastName;          
    private List<ContactMechanism> contactMechanims = new ArrayList<ContactMechanism>();
	
	@OneToMany
    @JoinColumn(name="person_id" ,nullable=false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN}) 
	public List<ContactMechanism> getContactMechanims() {
		return contactMechanims;
	}
    
    //@OneToMany (mappedBy="person", fetch=FetchType.LAZY)
    //@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    

	public void setContactMechanims(List<ContactMechanism> contactMechanims) {
		this.contactMechanims = contactMechanims;
	}		
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
		
}

