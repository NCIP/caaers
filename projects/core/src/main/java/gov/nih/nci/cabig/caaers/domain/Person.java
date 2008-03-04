package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.MappedSuperclass;

/**
 * @author Kulasekaran
 */
@MappedSuperclass
public abstract class Person extends AbstractIdentifiableDomainObject {
    private String firstName;

    private String middleName;

    private String lastName;

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
