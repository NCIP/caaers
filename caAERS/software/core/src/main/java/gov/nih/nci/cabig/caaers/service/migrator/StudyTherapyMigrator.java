package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyTherapyMigrator implements Migrator<Study> {
	
	/**
	 * This method will copy the {@link StudyTherapy} from src to dest study
	 * @param src - Source study
	 * @param dest - Destination study
	 * @param outcome - The outcome of migration 
	 */
	public void migrate(Study src, Study dest,	DomainObjectImportOutcome<Study> outcome) {
		
		for(StudyTherapy therapy : src.getStudyTherapies()){
			if(!dest.hasTherapyOfType(therapy.getStudyTherapyType())){
				dest.addStudyTherapy(therapy.getStudyTherapyType());
			}
		}
	    
	}
	
	
}
