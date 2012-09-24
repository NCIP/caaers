package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReconciliationReportController extends AbstractController{

//    ReconcilationReportDao dao;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        ReconcilationReport report = null;
        String strReportId  = WebUtils.getStringParameter(request, "rrId") ;
        if(StringUtils.isNotEmpty(strReportId)){
//            report = dao.getById(Integer.parseInt(strReportId));
        }

        ModelAndView mv = new ModelAndView();
//        mv.addObject("report", o);
        mv.setViewName("ae/ae_reconcile_report");
        return mv;
    }


}
