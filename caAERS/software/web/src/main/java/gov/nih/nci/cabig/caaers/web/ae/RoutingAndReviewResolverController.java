package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;



public class RoutingAndReviewResolverController extends AbstractController{
	
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
				HttpServletResponse response){
		String reportingPeriodId = request.getParameter("adverseEventReportingPeriod");
		String aeReportId = request.getParameter("aeReport");
		
		if(reportingPeriodId != null && !reportingPeriodId.equals(""))
			return handleReportingPeriodRequest(request, response, reportingPeriodId);
		else
			return handleAeReportRequest(request, response, aeReportId);
	}
	
	private ModelAndView handleReportingPeriodRequest(HttpServletRequest request, HttpServletResponse response,
				String reportingPeriodId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(Integer.parseInt(reportingPeriodId));
		Integer studyId = reportingPeriod.getStudy().getId();
		Integer participantId = reportingPeriod.getParticipant().getId();
		String redirectUrl = "";
		
		// Check if the course is active.
		// If the course is inactive (isRetired) then the user will be directed to the routing and review page.
		if(reportingPeriod.isRetired()){
			redirectUrl = "routingAndReview?study=" + studyId + "&participant=" + participantId + "&retiredReportingPeriod=true&paginationAction=firstPage&numberOfResultsPerPage=15";
		}else{
            boolean fromRoutingAndReview = StringUtils.equals(request.getParameter("src"), "RoutingReview");
			if(fromRoutingAndReview && SecurityUtils.checkAuthorization(UserGroupType.ae_study_data_reviewer) && reportingPeriod.getReviewStatus() == ReviewStatus.DATA_COORDINATOR_REVIEW){
				redirectUrl = "reconcileAe?rpId=" + reportingPeriod.getId();
                AdverseEventReconciliationController.clearCommandObject(request);
			} else{
				redirectUrl = "captureRoutine?adverseEventReportingPeriod=" + reportingPeriodId + "&study=" + studyId + "&participant=" + participantId + "&_page=0&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true";
				// Only ae_reporter will be taken to the edit flow of adverse events ie the url mentioned above. For all other roles, the user will be redirected to the read-only page.
				if(!SecurityUtils.checkAuthorization(UserGroupType.ae_reporter)){
					redirectUrl = "reviewEvaluationPeriod?adverseEventReportingPeriod=" + reportingPeriodId;
				}
			}
		}
		ModelAndView mv = new ModelAndView(new RedirectView(redirectUrl));
		return mv;
	}
	
	private ModelAndView handleAeReportRequest(HttpServletRequest request, HttpServletResponse response,String aeReportId){
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportId));
		String reportId = request.getParameter("report");
        String reviewAndSubmit =  request.getParameter("ras");
        String redirectUrl ="edit?aeReport=" + aeReportId + "&report=" + reportId;
        if(StringUtils.isNotEmpty(reviewAndSubmit)){
            redirectUrl = redirectUrl + "&_page=10&action=reportSubmission";
        }
		
		// Check if the course for which the report is created is active.
		// If the course is inactive (isRetired) then the user will be directed to the routing and review page.
		if(aeReport.getReportingPeriod().isRetired()){
			Integer studyId = aeReport.getReportingPeriod().getStudy().getId();
			Integer participantId = aeReport.getReportingPeriod().getParticipant().getId();
			redirectUrl = "routingAndReview?study=" + studyId + "&participant=" + participantId + "&retiredReportingPeriod=true&paginationAction=firstPage&numberOfResultsPerPage=15";
		}else{
			// Only ae_reporter will be taken to the edit flow of adverse events ie the url mentioned above. For all other roles, the user will be redirected to the read-only page.
			if(!SecurityUtils.checkAuthorization(UserGroupType.ae_reporter)){
				redirectUrl = "reviewAeReport?aeReport=" + aeReportId + "&report=" + reportId;
			}
		}
		ModelAndView mv = new ModelAndView(new RedirectView(redirectUrl));
		return mv;
	}
	
	
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao(){
		return adverseEventReportingPeriodDao;
	}
	
	public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao(){
		return expeditedAdverseEventReportDao;
	}
	
	public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao){
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
}