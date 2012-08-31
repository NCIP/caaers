package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationController extends AutomaticSaveAjaxableFormController<AdverseEventReconciliationCommand, AdverseEventReportingPeriod, AdverseEventReportingPeriodDao> {

    public AdverseEventReconciliationController() {
        Flow<AdverseEventReconciliationCommand> flow = new Flow<AdverseEventReconciliationCommand>("Reconcile Adverse Events");
        flow.addTab(new AdverseEventLinkTab("Link Adverse Event Data", "Link Adverse Events", "ae_reconcile_link"));
        flow.addTab(new AdverseEventLinkTab("Merge Adverse Event Data", "Merge Adverse Events", "ae_reconcile_merge"));
        flow.addTab(new AdverseEventLinkTab("Reconciliation Summary", "Summary", "ae_reconcile_summary"));
    }

    @Override
    protected AdverseEventReportingPeriod getPrimaryDomainObject(AdverseEventReconciliationCommand command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected AdverseEventReportingPeriodDao getDao() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(HttpServletRequest request, Object o,	Errors errors, int page) throws Exception {
        return super.referenceData(request, o, errors, page);
    }


        @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
