/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.EvaluateAndInitiateInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.SaveAndEvaluateAEsOutputMessage;
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

	public ExpeditedAdverseEventReport convert(EvaluateAndInitiateInputMessage evaluateInputMessage, AdverseEventReportingPeriod repPeriod, SaveAndEvaluateAEsOutputMessage response) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		ExpeditedAdverseEventReport aeSrcReport = new ExpeditedAdverseEventReport();
		for(AdverseEvent adverseEvent: repPeriod.getAdverseEvents()) {
			aeSrcReport.addAdverseEventUnidirectional(adverseEvent);
		}
		aeSrcReport.setExternalId(evaluateInputMessage.getReportId());
		aeSrcReport.setReporter(utility.convertReporter(evaluateInputMessage.getReporter()));
		aeSrcReport.setPhysician(utility.convertPhysician(evaluateInputMessage.getPhysician()));
		aeSrcReport.setExternalId(evaluateInputMessage.getReportId());
		aeSrcReport.setCreatedAt(now);
		aeSrcReport.setReportingPeriod(repPeriod);
		List<Report> reports = new ArrayList<Report>();
		
		if(response.getRecommendedActions() != null && response.getRecommendedActions().size() > 0) {
			Report report = new Report();
			report.setReportDefinition(new ReportDefinition());
			report.getReportDefinition().setName(response.getRecommendedActions().get(0).getReport());
			if(evaluateInputMessage.isWithdrawReport() != null && evaluateInputMessage.isWithdrawReport().booleanValue()) {
				report.setWithdrawnOn(now);
			}
			reports.add(report);
		}
		aeSrcReport.setReports(reports);
		return aeSrcReport;
	}
	
}