package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the SiteInvestigator domain object associated with the Adverse event
 * report.
 * 
 * @author
 * 
 */
@Entity
@Table(name = "site_investigators")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_site_investigators_id") })
public class SiteInvestigator extends AbstractMutableDomainObject {

    private Organization organization;

    private Investigator investigator;

    private String statusCode;

    private String emailAddress;

    private Date statusDate;

    private List<StudyInvestigator> studyInvestigators = new ArrayList<StudyInvestigator>();

    @ManyToOne
    @JoinColumn(name = "investigator_id")
    public Investigator getInvestigator() {
        return investigator;
    }

    @OneToMany(mappedBy = "siteInvestigator", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyInvestigator> getStudyInvestigators() {
        return studyInvestigators;
    }

    public void setStudyInvestigators(List<StudyInvestigator> studyInvestigators) {
        this.studyInvestigators = studyInvestigators;
    }

    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }

    @ManyToOne
    @JoinColumn(name = "site_id")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Column(name = "status_code")
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(name = "status_date")
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    // /OBJECT METHODS
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((investigator == null) ? 0 : investigator.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
        result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final SiteInvestigator other = (SiteInvestigator) obj;
        if (emailAddress == null) {
            if (other.emailAddress != null) return false;
        } else if (!emailAddress.equals(other.emailAddress)) return false;
        if (investigator == null) {
            if (other.investigator != null) return false;
        } else if (!investigator.equals(other.investigator)) return false;
        if (organization == null) {
            if (other.organization != null) return false;
        } else if (!organization.equals(other.organization)) return false;
       // if (statusDate == null) {
        //    if (other.statusDate != null) return false;
        //} else if (!statusDate.equals(other.statusDate)) return false;
        return true;
    }

}
