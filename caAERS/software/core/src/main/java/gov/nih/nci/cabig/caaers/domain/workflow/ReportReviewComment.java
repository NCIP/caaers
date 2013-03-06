/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
	public ReportReviewComment() {
		super();
	}
}
