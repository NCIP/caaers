package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


/**
 * @author Kulasekaran
 */
@MappedSuperclass
public abstract class Person extends AbstractIdentifiableDomainObject implements Comparable<Person>{
	
	protected String title;
    protected String firstName;
    protected String middleName;
    protected String lastName;
	protected String emailAddress;
	protected String phoneNumber;
	protected String faxNumber;
	protected _User caaersUser; //TODO - MD : Need to rename this to user, also make sure reporter.jsp field name are modified. 
	
    @Transient
    public String getLastFirst() {
        StringBuilder name = new StringBuilder();
        boolean hasFirstName = getFirstName() != null;
        if (getLastName() != null) {
            name.append(getLastName());
            if (hasFirstName) {
                name.append(", ");
            }
        }
        if (hasFirstName) {
            name.append(getFirstName());
        }
        return name.toString();
    }

    @Transient
    public String getFullName() {
        StringBuilder name = new StringBuilder();
        boolean hasLastName = getLastName() != null;
        if (getFirstName() != null) {
            name.append(getFirstName());
            if (hasLastName) {
                name.append(' ');
            }
        }
        if (hasLastName) {
            name.append(getLastName());
        }
        return name.toString();
    }
    
    @Transient
    public String getLoginId(){
    	if(getCaaersUser() != null && getCaaersUser().getCsmUser() != null){
    		return getCaaersUser().getCsmUser().getLoginName();
    	}
    	return null;
    }
    
    public void setLoginId(String loginId){
    	if(getCaaersUser() != null && getCaaersUser().getCsmUser() != null){
    		getCaaersUser().setLoginName(loginId);
    		getCaaersUser().getCsmUser().setLoginName(loginId);
    	}
    }
    
	public int compareTo(Person person){
		return getFullName().compareTo(person.getFullName());
	}	
	
	
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
    
    @Transient
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
    @Transient	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

    @Transient	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    @Transient	
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	
	@Transient
    @OneToOne
    @JoinColumn(name = "user_id")	
	public _User getCaaersUser() {
		return caaersUser;
	}

	public void setCaaersUser(_User caaersUser) {
		this.caaersUser = caaersUser;
	}

    @Transient
    public boolean isUser(){
        return getCaaersUser() != null;
    }

    /**
     * Will copy into this Person, the details from the input Person
     * @param p - The Person from which the details to be copied from.
     */
    public <P extends Person> void sync(P p){
        setTitle(p.getTitle());
        setFirstName(p.getFirstName());
        setMiddleName(p.getMiddleName());
        setLastName(p.getLastName());
        setEmailAddress(p.getEmailAddress());
        setPhoneNumber(p.getPhoneNumber());
        setFaxNumber(p.getFaxNumber());
        
        if(getCaaersUser() == null){
            setCaaersUser(p.getCaaersUser());
        }else{
            getCaaersUser().sync(p.getCaaersUser());
        }

    }
}
