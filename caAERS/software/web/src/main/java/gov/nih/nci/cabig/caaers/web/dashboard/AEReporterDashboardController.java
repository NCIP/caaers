package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.dto.TaskNotificationDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class AEReporterDashboardController extends DashboardController {

    ReportVersionRepository reportVersionRepository;
    AdverseEventRoutingAndReviewRepositoryImpl rrRepositry;
    
    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginId = SecurityUtils.getUserLoginName();

        List<ReportVersion> l1 = reportVersionRepository.getPastDue();
        List<ReportVersion> l2 = reportVersionRepository.getReportActivity();
        ModelAndView mv = new ModelAndView("dashboard/dashboard_AEReporter");
        mv.addObject("pastDueReports", l1);
        mv.addObject("reportActivity", l2);

        List<TaskNotificationDTO> l3 = rrRepositry.getTaskNotificationByUserLogin(loginId);
        for (TaskNotificationDTO dto : l3) {
            dto.setMessage(dto.getMessage().replaceAll("(http(s)?://(.)*?)[\\s]", "<a href='$1' class='linkHere' target='_blank'>here</a> "));
        }
        mv.addObject("taskNotifications", l3);
        
        return mv;
    }

    public ReportVersionRepository getReportVersionRepository() {
        return reportVersionRepository;
    }

    public void setReportVersionRepository(ReportVersionRepository reportVersionRepository) {
        this.reportVersionRepository = reportVersionRepository;
    }

    public AdverseEventRoutingAndReviewRepositoryImpl getRrRepositry() {
        return rrRepositry;
    }

    public void setRrRepositry(AdverseEventRoutingAndReviewRepositoryImpl rrRepositry) {
        this.rrRepositry = rrRepositry;
    }
}