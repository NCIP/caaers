package gov.nih.nci.ess.sr;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;

public interface ExpeditedToSafetyReportConverter {

	public abstract SafetyReportVersion convertExpeditedAdverseEventReport(
			ExpeditedAdverseEventReport expeditedAEReport);

}