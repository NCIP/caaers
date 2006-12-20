package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Sujith Vellat Thayyilthodi
 */
@Entity
@Table(name="studies")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="studies_id_seq")
    }
)
public class Study extends AbstractDomainObject implements Serializable{

	/**
	 * For Backward compatibility. 
	 */
	private static final long serialVersionUID = -2650610294671313885L;

	private boolean multiInstitutionIndicator;
	
	private String title;

    private String investigatorCode;
    
    private String investigatorName;
    
    private String sponsorCode;

    private String sponsorName;

	private String phaseCode;

	private Date reviewDate;
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String shortTitle) {
		this.title = shortTitle;
	}
	
    public String getInvestigatorCode() {
		return investigatorCode;
	}

	public void setInvestigatorCode(String investigatorCode) {
		this.investigatorCode = investigatorCode;
	}

	public String getInvestigatorName() {
		return investigatorName;
	}

	public void setInvestigatorName(String investigatorName) {
		this.investigatorName = investigatorName;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public boolean isMultiInstitutionIndicator() {
		return multiInstitutionIndicator;
	}

	public void setMultiInstitutionIndicator(boolean multiInstitutionIndicator) {
		this.multiInstitutionIndicator = multiInstitutionIndicator;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
    
}
