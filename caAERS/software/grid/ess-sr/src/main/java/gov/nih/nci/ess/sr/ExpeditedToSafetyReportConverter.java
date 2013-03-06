/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.sr;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;

public interface ExpeditedToSafetyReportConverter {

	public abstract SafetyReportVersion convertExpeditedAdverseEventReport(
			ExpeditedAdverseEventReport expeditedAEReport);
	
	public abstract gov.nih.nci.ess.safetyreporting.types.ReportDefinition convertReportDefinition(
			gov.nih.nci.cabig.caaers.domain.report.ReportDefinition reportDefinition);

}
