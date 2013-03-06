/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
        List<ReportVersion> reportActivity = reportVersionRepository.getReportActivity();
        ModelAndView mv = new ModelAndView("dashboard/dashboard_AEReporter");
        mv.addObject("reportActivity", reportActivity);

        List<TaskNotificationDTO> taskNotifications = rrRepositry.getTaskNotificationByUserLogin(loginId);
        for (TaskNotificationDTO dto : taskNotifications) {
            if(dto.getDescription() != null){
               dto.setDescription(dto.getDescription().replaceAll("(http(s)?://(.)*?)[\\s]", "<a href='$1' class='linkHere' target='_blank'>here</a> "));
            }
        }
        mv.addObject("taskNotifications", taskNotifications);
        
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
