package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class ReportVersionRepository {

		private ReportVersionDao reportVersionDao;

		@Transactional(readOnly = false)
	    public void updateInProcessReports() {
	    	List<ReportVersion> rvs = reportVersionDao.getAllInProcessReports();
	    	NowFactory nowFactory = new NowFactory();
	    	for (ReportVersion rv:rvs) {
	    		Date submittedOrAmendedDate = null;
	    		if (rv.getAmendedOn() != null) {
	    			submittedOrAmendedDate = rv.getAmendedOn();
	    		} else if (rv.getSubmittedOn() != null){
	    			submittedOrAmendedDate = rv.getSubmittedOn();
	    		}
	    		if (submittedOrAmendedDate != null) {
	    			long timeDiff = (nowFactory.getNowTimestamp().getTime() - rv.getSubmittedOn().getTime())/60000;
	    			if (timeDiff > 5) {
	    				rv.setReportStatus(ReportStatus.FAILED);
	    				rv.setSubmissionMessage("Submission failed for unknown reason , Please resubmit");
	    				reportVersionDao.save(rv);
	    			}

	    		}
	    	}    	
	    }
	    
		public void setReportVersionDao(ReportVersionDao reportVersionDao) {
			this.reportVersionDao = reportVersionDao;
		}
		
		
		
}
