package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


 
/**
 * The Class Person.
 *
 * @author Kulasekaran
 */
@MappedSuperclass
public abstract class Person extends AbstractIdentifiableDomainObject implements Comparable<Person>{
	
	/** The title. */
	protected String title;
    
    /** The first name. */
    protected String firstName;
    
    /** The middle name. */
    protected String middleName;
    
    /** The last name. */
    protected String lastName;
	
	/** The email address. */
	protected String emailAddress;
	
	/** The phone number. */
	protected String phoneNumber;
	
	/** The fax number. */
	protected String faxNumber;
	
	/** The caaers user. */
	protected _User caaersUser; //TODO - MD : Need to rename this to user, also make sure reporter.jsp field name are modified. 
	
    /**
     * Gets the last first.
     *
     * @return the last first
     */
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

    /**
     * Gets the full name.
     *
     * @return the full name
     */
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
    
    /**
     * Gets the login id.
     *
     * @return the login id
     */
    @Transient
    public String getLoginId(){
    	if(getCaaersUser() != null && getCaaersUser().getCsmUser() != null){
    		return getCaaersUser().getCsmUser().getLoginName();
    	}
    	return null;
    }
    
    /**
     * Sets the login id.
     *
     * @param loginId the new login id
     */
    public void setLoginId(String loginId){
    	if(getCaaersUser() != null && getCaaersUser().getCsmUser() != null){
    		getCaaersUser().setLoginName(loginId);
    		getCaaersUser().getCsmUser().setLoginName(loginId);
    	}
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Person person){
		return getFullName().compareTo(person.getFullName());
	}	
	
	
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    @Transient
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    @Transient
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Gets the middle name.
     *
     * @return the middle name
     */
    @Transient
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the new middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    @Transient
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
    /**
     * Gets the email address.
     *
     * @return the email address
     */
    @Transient	
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    @Transient	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    /**
     * Gets the fax number.
     *
     * @return the fax number
     */
    @Transient	
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * Sets the fax number.
	 *
	 * @param faxNumber the new fax number
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	
	/**
	 * Gets the caaers user.
	 *
	 * @return the caaers user
	 */
	@Transient
    @OneToOne
    @JoinColumn(name = "user_id")	
	public _User getCaaersUser() {
		return caaersUser;
	}

	/**
	 * Sets the caaers user.
	 *
	 * @param caaersUser the new caaers user
	 */
	public void setCaaersUser(_User caaersUser) {
		this.caaersUser = caaersUser;
	}

    /**
     * Checks if is user.
     *
     * @return true, if is user
     */
    @Transient
    public boolean isUser(){
        return getCaaersUser() != null;
    }

    /**
     * Will copy into this Person, the details from the input Person.
     *
     * @param <P> the generic type
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
