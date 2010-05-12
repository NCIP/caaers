package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.ExpeditedAdverseEventReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.task.TaskGroup;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class DashboardController extends AbstractController {
    ExpeditedAdverseEventReportRepository aeReportRepository;
    String loginId;

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        loginId = SecurityUtils.getUserLoginName();
        List<ExpeditedAdverseEventReport> l = aeReportRepository.getPastDue(loginId);
        ModelAndView mv = new ModelAndView("dashboard");
        mv.getModelMap().put("pastDueReports", l);
        return mv;
    }

    public ExpeditedAdverseEventReportRepository getAeReportRepository() {
        return aeReportRepository;
    }

    public void setAeReportRepository(ExpeditedAdverseEventReportRepository aeReportRepository) {
        this.aeReportRepository = aeReportRepository;
    }
}