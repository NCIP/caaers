package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.report.Report;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents the review comments entered on an {@link Report}
 * @author Biju
 *
 */
@Entity
@DiscriminatorValue("report")
public class ReportReviewComment extends ReviewComment {

}
