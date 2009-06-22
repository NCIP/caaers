package gov.nih.nci.cabig.caaers.web.admin;

// import Apache commons
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

public class ImportMeddraController extends ParameterizableViewController {

    private static Log log = LogFactory.getLog(ImportMeddraController.class);
    
    private MeddraVersionDao meddraVersionDao;

    public ImportMeddraController() {
        setViewName("admin/meddra_import");
    }

    public ModelAndView handleRequestInternal(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        setViewName("admin/meddra_import");
        ModelAndView mav = new ModelAndView("admin/meddra_import");
        mav.addObject("meddraVersions", meddraVersionDao.getAll());
        log.debug("modelAndView" + mav.getViewName());

        return mav;
    }
    
    public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }
}
