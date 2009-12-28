package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ReportFormat;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

public class StudyReportTypeSynchronizer implements Migrator<Study>{

	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		dbStudy.getReportFormats().clear();
		for(ReportFormat rf : xmlStudy.getReportFormats()){
			dbStudy.addReportFormat(rf);
		}
	}
}