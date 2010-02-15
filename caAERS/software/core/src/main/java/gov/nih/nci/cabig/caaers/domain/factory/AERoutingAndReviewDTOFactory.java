package gov.nih.nci.cabig.caaers.domain.factory;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
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

import org.apache.log4j.Logger;
/**
 * The purpose of this class is to obtain an {@link AdverseEventReportingPeriodDTO} object from an {@link AdverseEventReportingPeriod}
 * @author Biju Joseph
 *
 */
public class AERoutingAndReviewDTOFactory {
	
	private static Logger log = Logger.getLogger(AERoutingAndReviewDTOFactory.class);
	private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	
	public AdverseEventReportingPeriodDTO createAdverseEventEvalutionPeriodDTO(AdverseEventReportingPeriod rp, String userId){
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
        if(rp.getEpoch() != null) dto.setEvaluationPeriodTypeName(rp.getEpoch().getName());
		dto.setNoOfAe(rp.getNumberOfAEs());
		dto.setNoOfReport(rp.getNumberOfReports());
		dto.setReviewStatus(rp.getReviewStatus());
		dto.setPossibleActions(adverseEventRoutingAndReviewRepository.nextTransitionNames(rp.getWorkflowId(), userId));
		dto.setReviewComments(createReviewComments(rp.getReviewComments()));
		
		//set the dcp sponsored study flag
		dto.setDcpSponsoredStudy(rp.getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP"));
		
		return dto;
		
	}
	
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
	
	protected List<ReportDTO> createReportDTOs(ExpeditedAdverseEventReport aeReport, String userId){
		ArrayList<ReportDTO> reportDTOs = new ArrayList<ReportDTO>();
		for(Report report : aeReport.getReports()){
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
			dto.setPossibleActions(adverseEventRoutingAndReviewRepository.nextTransitionNamesForReportWorkflow(report, userId));
			dto.setReviewComments(createReviewComments(report.getReviewComments()));
			reportDTOs.add(dto);
		}
		return reportDTOs;
	}
	
	public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
	public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
}
