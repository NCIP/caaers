package gov.nih.nci.cabig.caaers.domain.factory;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReviewCommentsDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
 

/**
 * The purpose of this class is to obtain an {@link AdverseEventReportingPeriodDTO} object from an {@link AdverseEventReportingPeriod}.
 *
 * @author Biju Joseph
 */
public class AERoutingAndReviewDTOFactory {
	
	/** The log. */
	private static Logger log = Logger.getLogger(AERoutingAndReviewDTOFactory.class);
	
	/** The adverse event routing and review repository. */
	private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	
	/**
	 * Creates a new AERoutingAndReviewDTO object.
	 *
	 * @param rp the rp
	 * @param userId the user id
	 * @param courseWorkflowEnabled the course workflow enabled
	 * @return the adverse event reporting period dto
	 */
	public AdverseEventReportingPeriodDTO createAdverseEventEvalutionPeriodDTO(AdverseEventReportingPeriod rp, String userId, Boolean courseWorkflowEnabled){
		if(rp == null) return null;
		AdverseEventReportingPeriodDTO dto = new AdverseEventReportingPeriodDTO();
		dto.setAdverseEventReportingPeriod(rp);
		dto.setParticipant(rp.getParticipant());
		dto.setStudy(rp.getStudy());
		dto.setId(rp.getId());
		dto.setEvaluationPeriodName(rp.getName());
		if(rp.getEpoch() != null) dto.setEvaluationPeriodTypeName(rp.getEpoch().getName());
		dto.setNoOfAe(rp.getNumberOfAEs());
		dto.setNoOfReport(rp.getNumberOfReports());
		//set the dcp sponsored study flag
		dto.setDcpSponsoredStudy(rp.getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP"));
		
		if(rp.getWorkflowId() == null) {
			// This means that the workflow is not enabled for courses.
			// So even if the course existed in a workflow, status and actions should not be shown in this case.
			dto.setWorkflowId(null);
			dto.setReviewStatus(null);
			dto.setPossibleActions(null);
			dto.setReviewComments(null);
			return dto;
		}else{
			dto.setWorkflowId(rp.getWorkflowId());
			dto.setReviewStatus(rp.getReviewStatus());
			dto.setPossibleActions(adverseEventRoutingAndReviewRepository.nextTransitionNames(rp.getWorkflowId(), userId));
			dto.setReviewComments(createReviewComments(rp.getReviewComments()));
		}
		
		return dto;
	}
	
	/**
	 * Creates a new AERoutingAndReviewDTO object.
	 *
	 * @param aeReport the ae report
	 * @param userId the user id
	 * @return the expedited adverse event report dto
	 */
	public ExpeditedAdverseEventReportDTO createAdverseEventReportDTO(ExpeditedAdverseEventReport aeReport, String userId){
		if(aeReport == null) return null;
		ExpeditedAdverseEventReportDTO dto = new ExpeditedAdverseEventReportDTO();
		dto.setAeReport(aeReport);
		dto.setStudy(aeReport.getStudy());
		dto.setParticipant(aeReport.getParticipant());
		dto.setId(aeReport.getId());
		dto.setName("Expedited Report");
		dto.setNoOfAe(aeReport.getNumberOfAes());
		dto.setReports(createReportDTOs(aeReport, userId));
		return dto;
	}
	
	/**
	 * Creates a new AERoutingAndReviewDTO object.
	 *
	 * @param comments the comments
	 * @return the list< review comments dt o>
	 */
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
	
	/**
	 * Creates a new AERoutingAndReviewDTO object.
	 *
	 * @param aeReport the ae report
	 * @param userId the user id
	 * @return the list< report dt o>
	 * @author dkrylov
	 * @author Biju Joseph
	 */
	protected List<ReportDTO> createReportDTOs(
			ExpeditedAdverseEventReport aeReport, String userId) {
		ArrayList<ReportDTO> reportDTOs = new ArrayList<ReportDTO>();
		for (Report report : aeReport.getReports()) {
			if (report.getWorkflowId() == null) {
				continue;
			}
			final List<String> possibleActions = adverseEventRoutingAndReviewRepository
					.nextTransitionNamesForReportWorkflow(report, userId);
			// the following two checks are related to http://jira.semanticbits.com/browse/CAAERS-4319
			// essentially we are filtering out reports with no actions or with REPLACED/AMENDED status.
			if (CollectionUtils.isEmpty(possibleActions)) {
				continue;
			}
			if (ReportStatus.REPLACED.equals(report.getStatus())
					|| ReportStatus.AMENDED.equals(report.getStatus())) {
				continue;
			}
			ReportDTO dto = new ReportDTO();
			dto.setId(report.getId());
			dto.setName(report.getName());
			dto.setWorkflowId(report.getWorkflowId());
			ReportVersion lastVersion = report.getLastVersion();
			dto.setReportVersionId(lastVersion.getId());
			dto.setStatus(report.getStatus());
			dto.setNoOfAe(aeReport.getNumberOfAes());
			dto.setReport(report);
			dto.setReviewStatus(report.getReviewStatus());
			dto.setPossibleActions(possibleActions);
			dto.setReviewComments(createReviewComments(report
					.getReviewComments()));
			reportDTOs.add(dto);
		}
		return reportDTOs;
	}
	
	/**
	 * Gets the adverse event routing and review repository.
	 *
	 * @return the adverse event routing and review repository
	 */
	public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
	
	/**
	 * Sets the adverse event routing and review repository.
	 *
	 * @param adverseEventRoutingAndReviewRepository the new adverse event routing and review repository
	 */
	public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
}
