package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;

import java.util.ArrayList;
import java.util.List;


public class ReviewExternalAEReportingPeriodCommand {

	private List<ExternalAdverseEventReportingPeriod> unApprovedReportingPeriods = new ArrayList<ExternalAdverseEventReportingPeriod>();
	
	public List<ExternalAdverseEventReportingPeriod> getUnApprovedReportingPeriods() {
		return unApprovedReportingPeriods;
	}

	public void setUnApprovedReportingPeriods(
			List<ExternalAdverseEventReportingPeriod> unApprovedReportingPeriods) {
		this.unApprovedReportingPeriods = unApprovedReportingPeriods;
	}
	
	public void addUnApprovedReportingPeriod(ExternalAdverseEventReportingPeriod unApprovedReportingPeriod) {
		getUnApprovedReportingPeriods().add(unApprovedReportingPeriod);
	}

}
