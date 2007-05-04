package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

/**
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AbstractAdverseEventReportSingleChild extends AbstractDomainObject implements AdverseEventReportChild {
    private AdverseEventReport report;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    public AdverseEventReport getReport() {
        return report;
    }

    public void setReport(AdverseEventReport report) {
        this.report = report;
    }
}
