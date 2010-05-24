package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class DashboardController extends AbstractController {
    ReportVersionRepository reportVersionRepository;

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ReportVersion> l1 = reportVersionRepository.getPastDue();
        List<ReportVersion> l2 = reportVersionRepository.getReportActivity();
        ModelAndView mv = new ModelAndView("dashboard");
        mv.getModelMap().put("pastDueReports", l1);
        mv.getModelMap().put("reportActivity", l2);
        return mv;
    }

    public ReportVersionRepository getReportVersionRepository() {
        return reportVersionRepository;
    }

    public void setReportVersionRepository(ReportVersionRepository reportVersionRepository) {
        this.reportVersionRepository = reportVersionRepository;
    }
}