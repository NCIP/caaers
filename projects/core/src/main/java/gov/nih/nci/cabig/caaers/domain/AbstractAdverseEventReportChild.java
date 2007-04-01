package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

/**
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AbstractAdverseEventReportChild extends AbstractDomainObject implements AdverseEventReportChild {
    private AdverseEventReport report;

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false, nullable=false)
    public AdverseEventReport getReport() {
        return report;
    }

    public void setReport(AdverseEventReport report) {
        this.report = report;
    }
}
