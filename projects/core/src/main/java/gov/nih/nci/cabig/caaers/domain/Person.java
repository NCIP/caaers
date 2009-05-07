package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


/**
 * @author Kulasekaran
 */
@MappedSuperclass
public abstract class Person extends AbstractIdentifiableDomainObject {
	private String title;
	
    protected String firstName;

    protected String middleName;

    protected String lastName;
    
    @Transient
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Transient
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Transient
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    //the subclass should override this annotation if needed
    @Transient
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
