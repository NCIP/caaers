package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Domain object representing Study(Protocol)
 *
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "studies")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "studies_id_seq")
    }
)
public class Study extends AbstractDomainObject implements Serializable {

    /**
     * For Backward compatibility.
     */
    private static final long serialVersionUID = -2650610294671313885L;

    private Boolean multiInstitutionIndicator;

    private String shortTitle;

    private String longTitle;

    private String description;

    private String principalInvestigatorCode;

    private String principalInvestigatorName;

    private String primarySponsorCode;

    private String primarySponsorName;

    private String phaseCode;

    private Date reviewDate;

    private List<StudySite> studySites = new LinkedList<StudySite>();

    ////// LOGIC

    public void addStudySite(StudySite studySite) {
        getStudySites().add(studySite);
        studySite.setStudy(this);
    }

    ////// BEAN PROPERTIES

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getPrincipalInvestigatorCode() {
        return principalInvestigatorCode;
    }

    public void setPrincipalInvestigatorCode(String investigatorCode) {
        this.principalInvestigatorCode = investigatorCode;
    }

    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }

    public void setPrincipalInvestigatorName(String investigatorName) {
        this.principalInvestigatorName = investigatorName;
    }

    public String getPrimarySponsorCode() {
        return primarySponsorCode;
    }

    public void setPrimarySponsorCode(String sponsorCode) {
        this.primarySponsorCode = sponsorCode;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPrimarySponsorName() {
        return primarySponsorName;
    }

    public void setPrimarySponsorName(String sponsorName) {
        this.primarySponsorName = sponsorName;
    }

    public Boolean isMultiInstitutionIndicator() {
        return multiInstitutionIndicator;
    }

    public void setMultiInstitutionIndicator(Boolean multiInstitutionIndicator) {
        this.multiInstitutionIndicator = multiInstitutionIndicator;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    public List<StudySite> getStudySites() {
        return studySites;
    }

    public void setStudySites(List<StudySite> studySites) {
        this.studySites = studySites;
    }
    
    public boolean equals(Object other) {
        if(this == other) return true;
        if(!(other instanceof Study) ) return false;
        
        final Study that = (Study) other;
        
        return (
           (this.shortTitle != null && this.shortTitle.equals(that.getShortTitle())) &&
           (this.multiInstitutionIndicator != null && this.multiInstitutionIndicator.equals(that.isMultiInstitutionIndicator())));
   }

   public int hashCode() {
		int hashCode = 17;
		hashCode = 37 * hashCode
				+ ((this.shortTitle != null) ? this.shortTitle.hashCode() : 0);
		hashCode = 37
				* hashCode
				+ ((this.multiInstitutionIndicator != null) ? this.multiInstitutionIndicator
						.hashCode()
						: 0);
		return hashCode;
	}    
}
