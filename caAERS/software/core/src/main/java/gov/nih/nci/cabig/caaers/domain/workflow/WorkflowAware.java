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
	
	ReviewStatus getReviewStatus();
	void setReviewStatus(ReviewStatus rs);
	
	void setWorkflowId(Integer workflowId);
	Integer getWorkflowId();
}
