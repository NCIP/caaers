package gov.nih.nci.cabig.caaers.domain.ajax;

import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

 
/**
 * The Class UserAjaxableDomainObject.
 */
public class UserAjaxableDomainObject extends AbstractAjaxableDomainObject {

	/** The user name. */
	private String userName;
    
    /** The last name. */
    private String lastName;
    
    /** The first name. */
    private String firstName;
    
    /** The middle name. */
    private String middleName;
    
    /** The organization. */
    private String organization;
    
    /** The number. */
    private String number;
    
    /** The active. */
    private String active;
    
    /** The email address. */
    private String emailAddress;
    
    /** The record type. */
    private String recordType;
    
    /** The locked. */
    private boolean locked;

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    /**
     * Gets the last name.
     *
     * @return the last name
     */
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
     * Gets the first name.
     *
     * @return the first name
     */
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
     * Gets the middle name.
     *
     * @return the middle name
     */
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
     * Gets the organization.
     *
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets the organization.
     *
     * @param organization the new organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * Gets the number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number.
     *
     * @param number the new number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the active.
     *
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(String active) {
        this.active = active;
    }
    
	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
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
     * Gets the record type.
     *
     * @return the record type
     */
    public String getRecordType() {
		return recordType;
	}

	/**
	 * Sets the record type.
	 *
	 * @param recordType the new record type
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (userName == null ? 0 : userName.hashCode());
        result = prime * result + (firstName == null ? 0 : firstName.hashCode());
        result = prime * result + (lastName == null ? 0 : lastName.hashCode());
        result = prime * result + (organization == null ? 0 : organization.hashCode());
        result = prime * result + (number == null ? 0 : number.hashCode());
        result = prime * result + (emailAddress == null ? 0 : emailAddress.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        UserAjaxableDomainObject o = (UserAjaxableDomainObject)obj;
        
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        if (!ComparisonTools.nullSafeEquals(userName, o.userName)) return false;
        if (!ComparisonTools.nullSafeEquals(firstName, o.firstName)) return false;
        if (!ComparisonTools.nullSafeEquals(lastName, o.lastName)) return false;
        if (!ComparisonTools.nullSafeEquals(organization, o.organization)) return false;
        if (!ComparisonTools.nullSafeEquals(number, o.number)) return false;
        if (!ComparisonTools.nullSafeEquals(emailAddress, o.emailAddress)) return false;

        return true;
    }
}