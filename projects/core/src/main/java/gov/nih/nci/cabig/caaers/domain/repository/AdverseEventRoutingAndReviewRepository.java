package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
/**
 * 
 * Will query the {@link AdverseEventReportingPeriod} objects, matching the criteria. 
 * 
 * @author Biju Joseph
 *
 */
public interface AdverseEventRoutingAndReviewRepository {
	
	public List<AdverseEventReportingPeriodDTO> findAdverseEventReportingPeriods(Participant participant, Study study, StudySite studySite, ReviewStatus reviewStatus);
	
	public List<? extends ReviewComment> fetchReviewCommentsForReport(Integer reportId);
	
	public List<? extends ReviewComment> fetchReviewCommentsForReportingPeriod(Integer rpId);
	
	public void addReportReviewComment(Integer reportId, String comment, String userId);
	
	public void addReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId);
	
	public List<ReviewStatus> advanceReportWorkflow(Integer workflowId, ReviewStatus reviewStatus, Integer id);
	
	public List<ReviewStatus> advanceReportingPeriodWorkflow(Integer workflowId, ReviewStatus reviewStatus, Integer id);
}
