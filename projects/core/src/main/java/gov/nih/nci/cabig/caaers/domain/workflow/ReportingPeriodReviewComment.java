package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * Represents the review comments entered on a {@link AdverseEventReportingPeriod}
 * @author biju
 *
 */
@Entity
@DiscriminatorValue("rperiod")
public class ReportingPeriodReviewComment extends ReviewComment{

}
