package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections15.functors.InstantiateFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * This class represents the Investigator domain object associated with the Adverse event report.
 * 
 * @author Kulasekaran
 * @author Priyatam
 * @author Biju Joseph
 */
@Entity
@Table(name = "investigators")
public class Investigator extends User {

    private String nciIdentifier;

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

 
    // /OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investigator that = (Investigator) o;

        if (nciIdentifier != null ? !nciIdentifier.equals(that.nciIdentifier) : that.nciIdentifier != null)
            return false;

        return true && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nciIdentifier != null ? nciIdentifier.hashCode() : 0);
        return result;
    }
}
