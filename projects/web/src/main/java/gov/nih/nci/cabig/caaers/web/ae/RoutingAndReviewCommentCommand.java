package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;

import java.util.List;
/**
 * This command class, is used by the {@link RoutingAndReviewCommentController}
 * @author Biju Joseph
 *
 */
public class RoutingAndReviewCommentCommand {
	private Integer entityId;
	private Integer commentId;
	private String entity;
	private String comment;
	private String userId;
	
	private List<? extends ReviewComment> previousComments;
	
	private Report report;
	private AdverseEventReportingPeriod reportingPeriod;
	
	public RoutingAndReviewCommentCommand() {
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public List<? extends ReviewComment> getPreviousComments() {
		return previousComments;
	}

	public void setPreviousComments(List<? extends ReviewComment> previousComments) {
		this.previousComments = previousComments;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setCommentId(Integer commentId){
		this.commentId = commentId;
	}
	
	public Integer getCommentId(){
		return commentId;
	}
}
