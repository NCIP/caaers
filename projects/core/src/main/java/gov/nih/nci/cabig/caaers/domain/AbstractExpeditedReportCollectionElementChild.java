package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Base class for AdverseEventChild implementors which are 1:N with AE report.
 * 
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AbstractExpeditedReportCollectionElementChild extends AbstractMutableDomainObject
                implements ExpeditedAdverseEventReportChild {
    private ExpeditedAdverseEventReport report;

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = false)
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }
}
