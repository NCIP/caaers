package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_report_people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "role",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("ABSTRACT_BASE") // should be ignored
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_ae_report_people_id")
    }
)
public abstract class ReportPerson extends PersonContact {

	private ExpeditedAdverseEventReport expeditedReport;
    private ReportVersion report;

    ////// BOUND PROPERTIES

    @OneToOne
    @JoinColumn(name="report_version_id")
    public ReportVersion getReport() {
        return report;
    }

    public void setReport(ReportVersion report) {
        this.report = report;
    }
    
//////BOUND PROPERTIES

    @OneToOne
    @JoinColumn(name="report_id")
    public ExpeditedAdverseEventReport getExpeditedReport() {
        return expeditedReport;
    }

    public void setExpeditedReport(ExpeditedAdverseEventReport expeditedReport) {
        this.expeditedReport = expeditedReport;
    }
}
