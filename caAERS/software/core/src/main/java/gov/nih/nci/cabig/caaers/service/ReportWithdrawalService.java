package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import org.springframework.transaction.annotation.Transactional;
/**
 * This service is used to withdraw report from external agency, via ServiceMix
 * @author Biju Joseph
 *
 */
@Transactional
public class ReportWithdrawalService {
	/**
	 * This method will withdraw the report from external agency, by delegating the call to service mix. 
	 * @param report - Report to withdraw from external agency
	 */
	public void withdrawExternalReport(Report report){
		
		assert !report.getStatus().equals(ReportStatus.WITHDRAWN) : "Cannot withdraw a report that is already withdrawn";
		
		//send message to service mix.
		
	}
}
