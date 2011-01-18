package gov.nih.nci.cabig.caaers.domain.ajax;

import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

/**
 *
 */
public class UserAjaxableDomainObject extends AbstractAjaxableDomainObject {

	private String userName;
    private String lastName;
    private String firstName;
    private String middleName;
    private String organization;
    private String number;
    private String active;
    private String emailAddress;
    private String recordType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
    public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

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