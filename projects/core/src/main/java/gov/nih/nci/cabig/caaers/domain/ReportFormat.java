package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * Domain object representing Report Format
 * 
 * @author Srini Akkala
 */
@Entity
@Table(name = "report_format")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_format_id") })
public class ReportFormat extends AbstractMutableDomainObject implements StudyChild {

    private Study study;

    private ReportFormatType reportFormatType;

    public ReportFormat() {
    }

    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    public Study getStudy() {
        return study;
    }

    public void setStudy(final Study study) {
        this.study = study;
    }

    @Column(name = "REPORT_FORMAT_TYPE")
    @Type(type = "reportFormatType")
    public ReportFormatType getReportFormatType() {
        return reportFormatType;
    }

    public void setReportFormatType(final ReportFormatType reportFormatType) {
        this.reportFormatType = reportFormatType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (study == null ? 0 : study.getId().hashCode());
        result = prime * result + (reportFormatType == null ? 0 : reportFormatType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportFormat other = (ReportFormat) obj;
        if (study == null) {
            if (other.study != null) {
                return false;
            }
        } else if (!study.getId().equals(other.study.getId())) {
            return false;
        }
        if (reportFormatType == null) {
            if (other.reportFormatType != null) {
                return false;
            }
        } else if (!reportFormatType.equals(other.reportFormatType)) {
            return false;
        }
        return true;
    }
}
