package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.dao.ExternalAdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.query.ExternalAdverseEventReportingPeriodQuery;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.web.admin.ReviewExternalAEReportingPeriodCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Ramakrishna Gundala
 */

@SuppressWarnings("deprecation")
public class ReviewExternalAEReportingPeriodController extends SimpleFormController{
	
	private ExternalAdverseEventReportingPeriodDao externalAdverseEventReportingPeriodDao;
	  	
		public void setExternalAdverseEventReportingPeriodDao(
			ExternalAdverseEventReportingPeriodDao externalAdverseEventReportingPeriodDao) {
		this.externalAdverseEventReportingPeriodDao = externalAdverseEventReportingPeriodDao;
	}

		public ReviewExternalAEReportingPeriodController() {
			setCommandClass(ReviewExternalAEReportingPeriodCommand.class);
			setBindOnNewForm(true);
			setFormView("admin/external_ae_reporting_periods");
	        setSuccessView("admin/external_ae_reporting_periods");
		}
		
	    @Override
	    protected Object formBackingObject(HttpServletRequest request) throws Exception {
	    	ReviewExternalAEReportingPeriodCommand cmd =  new ReviewExternalAEReportingPeriodCommand();
	    	ExternalAdverseEventReportingPeriodQuery query = new ExternalAdverseEventReportingPeriodQuery();
	    	query.filterByUnApproved();
	    	List<ExternalAdverseEventReportingPeriod> results  = externalAdverseEventReportingPeriodDao.searchExternalAEReportingPeriods(query);
	    	cmd.setUnApprovedReportingPeriods(results);
	    	return cmd;
	    }
	    
}
