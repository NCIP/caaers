package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

/**
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AdverseEventReportPerson extends Person implements AdverseEventReportChild {
    private AdverseEventReport report;

    @OneToOne
    @JoinColumn(name="report_id")
    public AdverseEventReport getReport() {
        return report;
    }

    public void setReport(AdverseEventReport report) {
        this.report = report;
    }
}
