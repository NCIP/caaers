package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_report_people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ABSTRACT_BASE")
// should be ignored
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {
        @Parameter(name = "sequence", value = "seq_ae_report_people_id")
})
public abstract class ReportPerson extends PersonContact {

    private ExpeditedAdverseEventReport expeditedReport;

    private ReportVersion report;
    
	private Investigator investigator;
	private ResearchStaff researchStaff;
	

    // //// BOUND PROPERTIES

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "report_version_id")
    public ReportVersion getReport() {
        return report;
    }

    public void setReport(ReportVersion report) {
        this.report = report;
    }

    // ////BOUND PROPERTIES

    @OneToOne
    @JoinColumn(name = "report_id")
    public ExpeditedAdverseEventReport getExpeditedReport() {
        return expeditedReport;
    }

    public void setExpeditedReport(ExpeditedAdverseEventReport expeditedReport) {
        this.expeditedReport = expeditedReport;
    }
    
    
    @Transient
	public User getUser() {
		if(getResearchStaff() != null) return researchStaff;
		return getInvestigator();
	}
	
	public void setUser(User user) {
		if(user instanceof ResearchStaff) setResearchStaff((ResearchStaff)user); 
		else setInvestigator((Investigator)user);
	}
    

	@ManyToOne
    @JoinColumn(name = "investigator_id")
	public Investigator getInvestigator() {
		return investigator;
	}

	public void setInvestigator(Investigator investigator) {
		this.investigator = investigator;
	}

	@ManyToOne
    @JoinColumn(name = "researchstaff_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}

	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}

    public ReportPerson copy() {
        ReportPerson reportPerson = (ReportPerson) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, reportPerson, new String[]{"id", "gridId",
                "version", "expeditedReport", "primaryIdentifierValue"});

        return reportPerson;

    }

}
