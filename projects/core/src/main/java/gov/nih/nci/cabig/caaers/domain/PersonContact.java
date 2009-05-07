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
 * @author Krikor Krumlian
 */
@MappedSuperclass
public abstract class PersonContact extends Person {
	
	private Address address;
	
    private Map<String, String> contactMechanisms = new HashMap<String, String>();

    // TODO: it may be more appropriate to locate these constants somewhere else

    /** {@link #getContactMechanisms} key for the e-mail address */
    public static final String EMAIL = "e-mail";

    /** {@link #getContactMechanisms} key for the fax number */
    public static final String FAX = "fax";

    /** {@link #getContactMechanisms} key for the phone number */
    public static final String PHONE = "phone";

    public static final List<String> DEFAULT_CONTACT_MECHANISM_KEYS = Arrays.asList(EMAIL, PHONE,
                    FAX);

    // //// LOGIC

    @Transient
    public boolean isTransient() { // TODO: this should go in one of the base classes
        return getId() == null;
    }

    @Transient
    public boolean isSavable() {
        return getFirstName() != null && getLastName() != null
                        && getContactMechanisms().get(EMAIL) != null;
    }

    // //// BOUND PROPERTIES

    @CollectionOfElements(fetch=FetchType.EAGER)
    @JoinTable(name = "contact_mechanisms", joinColumns = @JoinColumn(name = "person_id"))
    @MapKey(columns = @Column(name = "type"))
    @Column(name = "value")
    public Map<String, String> getContactMechanisms() {
        return contactMechanisms;
    }

    public void setContactMechanisms(Map<String, String> contactMechanisms) {
        this.contactMechanisms = contactMechanisms;
    }
    
    @Embedded
	public Address getAddress() {
    	if(address == null) address = new Address();
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}
	
	@Transient
	public void setPhoneNumber(String phoneNumber){
		contactMechanisms.put(PHONE, phoneNumber);
	}
	@Transient
	public String getPhoneNumber(){
		return contactMechanisms.get(PHONE);
        return lastName;
	}
	@Transient
	public void setFax(String fax){
		contactMechanisms.put(FAX, fax);
	}
	@Transient
	public String getFax(){
		return contactMechanisms.get(FAX);
	}
	@Transient
	public void setEmailAddress(String emailAddress){
		contactMechanisms.put(EMAIL, emailAddress);
	}
	@Transient
	public String getEmailAddress(){
		return contactMechanisms.get(EMAIL);
	}
	
}
