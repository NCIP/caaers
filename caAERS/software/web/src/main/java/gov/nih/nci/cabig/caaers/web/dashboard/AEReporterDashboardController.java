/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.dto.TaskNotificationDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 * 
 */
public class AEReporterDashboardController extends DashboardController {

    ReportVersionRepository reportVersionRepository;
    AdverseEventRoutingAndReviewRepositoryImpl rrRepositry;
    PersonDao personDao;
    
    public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginId = SecurityUtils.getUserLoginName();
        Date startDate = DateUtils.firstDayOfThisMonth();
        Date endDate = DateUtils.lastDayOfThisMonth();

//        List<ReportVersionDTO> reportActivity = reportVersionRepository.getReportActivity(startDate, endDate);
        ModelAndView mv = new ModelAndView("dashboard/dashboard_AEReporter");
//        mv.addObject("reportActivity", reportActivity);

        List<TaskNotificationDTO> taskNotifications = rrRepositry.getTaskNotificationByUserLogin(loginId);
        for (TaskNotificationDTO dto : taskNotifications) {
            if(dto.getDescription() != null){
               dto.setDescription(dto.getDescription().replaceAll("(http(s)?://(.)*?)[\\s]", "<a href='$1' class='linkHere' target='_blank'>here</a> "));
            }
        }
        mv.addObject("taskNotifications", taskNotifications);
        
        Boolean isStaff = true;
        
        Person loggedInPerson = personDao.getByLoginId(loginId);
        if(loggedInPerson instanceof Investigator){
        	isStaff = false;
        }
        
        request.setAttribute("isStaff", isStaff);
        
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
