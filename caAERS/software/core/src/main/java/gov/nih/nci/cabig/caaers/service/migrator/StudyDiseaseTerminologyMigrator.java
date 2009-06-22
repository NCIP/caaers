package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyDiseaseTerminologyMigrator implements Migrator<Study> {
	
	/**
	 */
	public void migrate(Study src, Study dest,
			DomainObjectImportOutcome<Study> outcome) {
		
        // AeTerminology and Version
        dest.setDiseaseTerminology(src.getDiseaseTerminology());
        outcome.ifNullObject(dest.getDiseaseTerminology(), DomainObjectImportOutcome.Severity.ERROR, 
        		"Disease AeTerminology is either Empty or Not Valid");
        if (dest.getDiseaseTerminology() != null) {
            dest.getDiseaseTerminology().setStudy(dest);
            outcome.ifNullObject(dest.getDiseaseTerminology().getDiseaseCodeTerm(), DomainObjectImportOutcome.Severity.ERROR, "Disease Code Term is either Empty or Not Valid");
        }
		
	}
}
