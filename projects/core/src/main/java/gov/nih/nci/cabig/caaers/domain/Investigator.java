package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections15.functors.InstantiateFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the Investigator domain object associated with the Adverse event report.
 * 
 * @author Kulasekaran
 * @author Priyatam
 */
@Entity
@Table(name = "investigators")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_investigators_id") })
public class Investigator extends AbstractMutableDomainObject {

    private String firstName;

    private String middleName;

    private String lastName;

    private String nciIdentifier;

    private String emailAddress;

    private String phoneNumber;

    private String faxNumber;

    private final LazyListHelper lazyListHelper;

    public Investigator() {
        lazyListHelper = new LazyListHelper();

        // register with lazy list helper study site.
        lazyListHelper.add(SiteInvestigator.class, new InstantiateFactory<SiteInvestigator>(
                        SiteInvestigator.class));
    }

    // business methods

    public void addSiteInvestigator(final SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setInvestigator(this);
    }

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

    /*
     * public void addSiteInvestigator(SiteInvestigator studyInvestigator) {
     * getSiteInvestigators().add(studyInvestigator); }
     */

    // bean methods
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getNciIdentifier() {
        return nciIdentifier;
    }

    public void setNciIdentifier(final String nciIdentifier) {
        this.nciIdentifier = nciIdentifier;
    }

    @OneToMany(mappedBy = "investigator", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<SiteInvestigator> getSiteInvestigatorsInternal() {
        return lazyListHelper.getInternalList(SiteInvestigator.class);
    }

    public void setSiteInvestigatorsInternal(final List<SiteInvestigator> investigators) {
        lazyListHelper.setInternalList(SiteInvestigator.class, investigators);
    }

    @Transient
    public List<SiteInvestigator> getSiteInvestigators() {
        return lazyListHelper.getLazyList(SiteInvestigator.class);
    }

    public void setSiteInvestigators(final List<SiteInvestigator> investigators) {
        setSiteInvestigatorsInternal(investigators);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFaxNumber(final String faxNumber) {
        this.faxNumber = faxNumber;
    }

    // /OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investigator that = (Investigator) o;

        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (nciIdentifier != null ? !nciIdentifier.equals(that.nciIdentifier) : that.nciIdentifier != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nciIdentifier != null ? nciIdentifier.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
