package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AbstractExpeditedReportSingleChild extends AbstractMutableDomainObject implements
                ExpeditedAdverseEventReportChild {
    private ExpeditedAdverseEventReport report;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }
}
