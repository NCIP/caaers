/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.EvaluateAndInitiateInputMessage;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;

/**
 * @author Dirk Walter
 */

public class EvaluateAndInitiateReportConverter {
	AdverseEventConverter aeConverter = new AdverseEventConverter();
	
	ExpeditedAdverseEventReportConverterUtility utility = new ExpeditedAdverseEventReportConverterUtility();
	
	public void setStudyDao(StudyDao studyDao) {
		utility.setStudyDao(studyDao);
	}

	public ExpeditedAdverseEventReport convert(EvaluateAndInitiateInputMessage evaluateInputMessage, AdverseEventReportingPeriod repPeriod) {
		ExpeditedAdverseEventReport aeSrcReport = new ExpeditedAdverseEventReport();
		aeSrcReport.setExternalId(evaluateInputMessage.getReportId());
		aeSrcReport.setReporter(utility.convertReporter(evaluateInputMessage.getReporter()));
		aeSrcReport.setPhysician(utility.convertPhysician(evaluateInputMessage.getPhysician()));
		aeSrcReport.setExternalId(evaluateInputMessage.getReportId());
		aeSrcReport.setAssignment(repPeriod.getAssignment());
		aeSrcReport.setReportingPeriod(repPeriod);
		return aeSrcReport;
	}
	
}