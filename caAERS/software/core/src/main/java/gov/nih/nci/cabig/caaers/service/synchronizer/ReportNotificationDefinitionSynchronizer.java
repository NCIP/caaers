package gov.nih.nci.cabig.caaers.service.synchronizer;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * @author chandrasekaravr
 */
public class ReportNotificationDefinitionSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.report.ReportDefinition>{
	
	public void migrate(ReportDefinition xmlReportDefinition, ReportDefinition dbReportDefinition, DomainObjectImportOutcome<ReportDefinition> outcome) {
		List<PlannedNotification> dbNotificationDefs = dbReportDefinition.getPlannedNotificationsInternal();
		List<PlannedNotification> xmlNotificationDefs = xmlReportDefinition.getPlannedNotificationsInternal();
		if( dbNotificationDefs != null) {
			dbNotificationDefs.clear();
			if(xmlNotificationDefs == null) {
				return;
			}//end of if
			for(PlannedNotification defn: xmlNotificationDefs){
				dbReportDefinition.addPlannedNotification(defn);
			} //end of for
		}//end of outer if
	}
}