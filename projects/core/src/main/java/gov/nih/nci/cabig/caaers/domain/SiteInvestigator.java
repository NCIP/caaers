package gov.nih.nci.cabig.caaers.domain;

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
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

@Entity
@Table (name = "site_investigators")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_site_investigators_id")
    }
)
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
    
    @OneToMany (mappedBy = "siteInvestigator", fetch = FetchType.LAZY)    
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
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
}
