/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ReconciliationReportDao;
import gov.nih.nci.cabig.caaers.domain.ReconciliationReport;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReconciliationReportController extends AbstractController{

    ReconciliationReportDao reconciliationReportDao;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String strReportId = request.getParameter("rrId");
        ReconciliationReport report = null;

        if(StringUtils.isNotEmpty(strReportId)){
           report = reconciliationReportDao.getById(Integer.parseInt(strReportId));
        } else {
           report = (ReconciliationReport) request.getAttribute("report");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("report", report);
        mv.setViewName("ae/ae_reconcile_report");
        return mv;
    }

    public ReconciliationReportDao getReconciliationReportDao() {
        return reconciliationReportDao;
    }

    public void setReconciliationReportDao(ReconciliationReportDao reconciliationReportDao) {
        this.reconciliationReportDao = reconciliationReportDao;
    }
}
