package gov.nih.nci.cabig.caaers.domain.factory;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReviewCommentsDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

import org.apache.log4j.Logger;
/**
 * The purpose of this class is to obtain an {@link AdverseEventReportingPeriodDTO} object from an {@link AdverseEventReportingPeriod}
 * @author Biju Joseph
 *
 */
public class AERoutingAndReviewDTOFactory {
	
	private WorkflowService workflowService;
	private static Logger log = Logger.getLogger(AERoutingAndReviewDTOFactory.class);
	
	public AdverseEventReportingPeriodDTO createAdverseEventEvalutionPeriodDTO(AdverseEventReportingPeriod rp){
		if(rp == null) return null;
		if(rp.getWorkflowId() == null) {
			log.warn("The workflowID for AdverseEventReportingPeriod#" + rp.getId() + " is null");
			return null;
		}
		
		AdverseEventReportingPeriodDTO dto = new AdverseEventReportingPeriodDTO();
		dto.setWorkflowId(rp.getWorkflowId());
		dto.setAdverseEventReportingPeriod(rp);
		dto.setParticipant(rp.getParticipant());
		dto.setStudy(rp.getStudy());
		dto.setId(rp.getId());
		dto.setEvaluationPeriodName(rp.getName());
		dto.setEvaluationPeriodTypeName(rp.getEpoch().getName());
		dto.setNoOfAe(rp.getNumberOfAEs());
		dto.setNoOfReport(rp.getNumberOfReports());
		dto.setReviewStatus(rp.getReviewStatus());
		dto.setPossibleActions(workflowService.nextTransitionNames(rp.getWorkflowId()));
		dto.setReviewComments(createReviewComments(rp.getReviewComments()));
		
		//set the dcp sponsored study flag
		dto.setDcpSponsoredStudy(rp.getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP"));
		
		return dto;
		
	}
	
	public AdverseEventReportDTO createAdverseEventReportDTO(Report report, ExpeditedAdverseEventReport aeReport){
		if(report == null) return null;
		AdverseEventReportDTO dto = new AdverseEventReportDTO();
		dto.setWorkflowId(report.getWorkflowId());
		dto.setReport(report);
		dto.setStudy(aeReport.getStudy());
		dto.setParticipant(aeReport.getParticipant());
		dto.setId(report.getId());
		dto.setName(report.getName());
		dto.setNoOfAe(aeReport.getNumberOfAes());
		dto.setStatus(report.getLastVersion().getReportStatus());
		dto.setReportVersionId(report.getLastVersion().getReportVersionId());
		dto.setReviewStatus(report.getReviewStatus());
		dto.setPossibleActions(workflowService.nextTransitionNames(report.getWorkflowId()));
		dto.setReviewComments(createReviewComments(report.getReviewComments()));
		return dto;
	}
	
	protected List<ReviewCommentsDTO> createReviewComments(List<? extends ReviewComment> comments){
		List<ReviewCommentsDTO> commentDtos = new ArrayList<ReviewCommentsDTO>();
		if(comments != null){

			for(ReviewComment comment : comments){
				ReviewCommentsDTO commentDto = new ReviewCommentsDTO(comment.getFullComment());
				commentDtos.add(commentDto);
			}
			
		}
		return commentDtos;
	}
	
	
	
	public WorkflowService getWorkflowService() {
		return workflowService;
	}
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
}
