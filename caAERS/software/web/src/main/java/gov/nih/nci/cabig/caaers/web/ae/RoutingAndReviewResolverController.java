package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
		String redirectUrl = "captureRoutine?adverseEventReportingPeriod=" + reportingPeriodId + "&study=" + studyId + "&participant=" + participantId + "&_page=0&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true";
		//CAAERS-3308
		//if(!isSuperUser && SecurityUtils.checkAuthorization(UserGroupType.caaers_data_cd , UserGroupType.caaers_central_office_sae_cd,UserGroupType.caaers_study_cd)){
		// above logic works if user has only one role , if user has another which is authorized to write , based on the above logic the conrol is taking user to read view , 
		// so checking with roles which excludes above roles  
		if(!isSuperUser && !SecurityUtils.checkAuthorization(UserGroupType.caaers_user , UserGroupType.caaers_admin, UserGroupType.caaers_super_user,
				UserGroupType.caaers_participant_cd,UserGroupType.caaers_ae_cd,UserGroupType.caaers_site_cd, UserGroupType.caaers_physician)){
			redirectUrl = "reviewEvaluationPeriod?adverseEventReportingPeriod=" + reportingPeriodId;
		}
		
		ModelAndView mv = new ModelAndView(new RedirectView(redirectUrl));
		return mv;
	}
	
	private ModelAndView handleAeReportRequest(HttpServletRequest request, HttpServletResponse response,String aeReportId){
		String reportId = request.getParameter("report");
		String redirectUrl ="edit?aeReport=" + aeReportId + "&report=" + reportId;;
		boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
		//CAAERS-3308
		//if(!isSuperUser && SecurityUtils.checkAuthorization(UserGroupType.caaers_data_cd , UserGroupType.caaers_central_office_sae_cd,UserGroupType.caaers_study_cd)){
		// above logic works if user has only one role , if user has another which is authorized to write , based on the above logic the conrol is taking user to read view , 
		// so checking with roles which excludes above roles  
		if(!isSuperUser && !SecurityUtils.checkAuthorization(UserGroupType.caaers_user , UserGroupType.caaers_admin, UserGroupType.caaers_super_user,
				UserGroupType.caaers_participant_cd,UserGroupType.caaers_ae_cd,UserGroupType.caaers_site_cd, UserGroupType.caaers_physician)){
			redirectUrl = "reviewAeReport?aeReport=" + aeReportId + "&report=" + reportId;
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