package gov.nih.nci.ess.sr;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;

public interface SafetyToExpeditedReportConverter {
	
	public abstract ExpeditedAdverseEventReport convertSafetyReport(
			SafetyReportVersion safetyReportVersion);
	
	public abstract gov.nih.nci.cabig.caaers.domain.report.ReportDefinition convertSafetyReportDefinition(
			gov.nih.nci.ess.safetyreporting.types.ReportDefinition safetyReportDefinition);

}
