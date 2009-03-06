package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.context.SecurityContext;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;



public class RoutingAndReviewResolverController extends AbstractController{
	
	private CSMUserRepository csmUserRepository;
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
		SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		String userId = ((org.acegisecurity.userdetails.User)context.getAuthentication().getPrincipal()).getUsername();
		String redirectUrl = "captureRoutine?adverseEventReportingPeriod=" + reportingPeriodId + "&study=" + studyId + "&participant=" + participantId + "&_page=0&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true";
		if(!csmUserRepository.isSuperUser(userId)){
			User user = csmUserRepository.getUserByName(userId);
			if(user.getUserGroupTypes().contains(UserGroupType.caaers_ae_cd)){
				redirectUrl = "reviewEvaluationPeriod?adverseEventReportingPeriod=" + reportingPeriodId;
			}
		}
		ModelAndView mv = new ModelAndView(new RedirectView(redirectUrl));
		return mv;
	}
	
	private ModelAndView handleAeReportRequest(HttpServletRequest request, HttpServletResponse response,
				String aeReportId){
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportId));
		SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		String userId = ((org.acegisecurity.userdetails.User)context.getAuthentication().getPrincipal()).getUsername();
		String redirectUrl = "edit?aeReport=" + aeReportId;
		if(!csmUserRepository.isSuperUser(userId)){
			User user = csmUserRepository.getUserByName(userId);
			if(user.getUserGroupTypes().contains(UserGroupType.caaers_ae_cd)){
				redirectUrl = "edit?aeReport=" + aeReportId;
			}
		}
		ModelAndView mv = new ModelAndView(new RedirectView(redirectUrl));
		return mv;
	}
	
	@Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
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