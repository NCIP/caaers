/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents the review comments entered on a {@link AdverseEventReportingPeriod}.
 *
 * @author biju
 */
@Entity
@DiscriminatorValue("rperiod")
public class ReportingPeriodReviewComment extends ReviewComment{

}
