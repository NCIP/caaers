/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

 
/**
 * This base interface is implemented by objects, that are workflow enabled. 
 * @author Biju Joseph
 */

public interface WorkflowAware {
	
	/**
	 * Gets the review status.
	 *
	 * @return the review status
	 */
	ReviewStatus getReviewStatus();
	
	/**
	 * Sets the review status.
	 *
	 * @param rs the new review status
	 */
	void setReviewStatus(ReviewStatus rs);
	
	/**
	 * Sets the workflow id.
	 *
	 * @param workflowId the new workflow id
	 */
	void setWorkflowId(Integer workflowId);
	
	/**
	 * Gets the workflow id.
	 *
	 * @return the workflow id
	 */
	Integer getWorkflowId();
}
