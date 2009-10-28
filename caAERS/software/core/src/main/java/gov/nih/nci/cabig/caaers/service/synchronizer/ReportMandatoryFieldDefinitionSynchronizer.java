package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * @author Sameer Sawant
 */
public class ReportMandatoryFieldDefinitionSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.report.ReportDefinition>{
	
	public void migrate(ReportDefinition xmlReportDefinition, ReportDefinition dbReportDefinition, DomainObjectImportOutcome<ReportDefinition> outcome) {
		dbReportDefinition.getMandatoryFields().clear();
		for(ReportMandatoryFieldDefinition defn: xmlReportDefinition.getMandatoryFields()){
			dbReportDefinition.getMandatoryFields().add(defn);
		}
	}
}