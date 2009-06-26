package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

public class StudyTherapySynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{
	
	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		dbStudy.getStudyTherapies().clear();

		if (xmlStudy.getDrugAdministrationTherapyType()) {
	    	dbStudy.addStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION);
		}

		if (xmlStudy.getDeviceTherapyType()) {
			dbStudy.addStudyTherapy(StudyTherapyType.DEVICE);
		}

		if (xmlStudy.getRadiationTherapyType()) {
			dbStudy.addStudyTherapy(StudyTherapyType.RADIATION);
		}
		if (xmlStudy.getSurgeryTherapyType()) {
			dbStudy.addStudyTherapy(StudyTherapyType.SURGERY);
		}

		if (xmlStudy.getBehavioralTherapyType()) {
			dbStudy.addStudyTherapy(StudyTherapyType.BEHAVIORAL);
		}
	}
}
