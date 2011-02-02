package gov.nih.nci.cabig.caaers.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.MapKey;

 
/**
 * The Class PersonContact.
 *
 * @author Krikor Krumlian
 */
@MappedSuperclass
public abstract class PersonContact extends Person {
	
	/** The address. */
	private Address address;
	
    /** The contact mechanisms. */
    private Map<String, String> contactMechanisms = new HashMap<String, String>();

    // TODO: it may be more appropriate to locate these constants somewhere else

    /** The Constant EMAIL. {@link #getContactMechanisms} key for the e-mail address */
    public static final String EMAIL = "e-mail";

    /** The Constant FAX. {@link #getContactMechanisms} key for the fax number */
    public static final String FAX = "fax";

    /** The Constant PHONE. {@link #getContactMechanisms} key for the phone number */
    public static final String PHONE = "phone";

    /** The Constant DEFAULT_CONTACT_MECHANISM_KEYS. */
    public static final List<String> DEFAULT_CONTACT_MECHANISM_KEYS = Arrays.asList(EMAIL, PHONE,
                    FAX);

    // //// LOGIC

    /**
     * Checks if is transient.
     *
     * @return true, if is transient
     */
    @Transient
    public boolean isTransient() { // TODO: this should go in one of the base classes
        return getId() == null;
    }

    /**
     * Checks if is savable.
     *
     * @return true, if is savable
     */
    @Transient
    public boolean isSavable() {
        return getFirstName() != null && getLastName() != null
                        && getContactMechanisms().get(EMAIL) != null;
    }

    // //// BOUND PROPERTIES

    /**
     * Gets the contact mechanisms.
     *
     * @return the contact mechanisms
     */
    @CollectionOfElements(fetch=FetchType.EAGER)
    @JoinTable(name = "contact_mechanisms", joinColumns = @JoinColumn(name = "person_id"))
    @MapKey(columns = @Column(name = "type"))
    @Column(name = "value")
    public Map<String, String> getContactMechanisms() {
        return contactMechanisms;
    }

    /**
     * Sets the contact mechanisms.
     *
     * @param contactMechanisms the contact mechanisms
     */
    public void setContactMechanisms(Map<String, String> contactMechanisms) {
        this.contactMechanisms = contactMechanisms;
    }
    
    /**
     * Gets the address.
     *
     * @return the address
     */
    @Embedded
	public Address getAddress() {
    	if(address == null) address = new Address();
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getTitle()
	 */
	@Override
	public String getTitle() {
		return super.getTitle();
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		super.setTitle(title);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#setPhoneNumber(java.lang.String)
	 */
	@Transient
	public void setPhoneNumber(String phoneNumber){
		contactMechanisms.put(PHONE, phoneNumber);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getPhoneNumber()
	 */
	@Transient
	public String getPhoneNumber(){
		return contactMechanisms.get(PHONE);
	}
	
	/**
	 * Sets the fax.
	 *
	 * @param fax the new fax
	 */
	@Transient
	public void setFax(String fax){
		contactMechanisms.put(FAX, fax);
	}
	
	/**
	 * Gets the fax.
	 *
	 * @return the fax
	 */
	@Transient
	public String getFax(){
		return contactMechanisms.get(FAX);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#setEmailAddress(java.lang.String)
	 */
	@Transient
	public void setEmailAddress(String emailAddress){
		contactMechanisms.put(EMAIL, emailAddress);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getEmailAddress()
	 */
	@Transient
	public String getEmailAddress(){
		return contactMechanisms.get(EMAIL);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Person#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		return middleName;
	}

	
}
