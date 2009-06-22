package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

public class StudyTherapySynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{
	
	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		List<StudyTherapy> newStudyTherapyList = new ArrayList<StudyTherapy>();
		
		//Identify newly added StudyTherapy.
		for(StudyTherapy xmlStudyTherapy : xmlStudy.getStudyTherapies()){
			for(StudyTherapy dbStudyTherapy : dbStudy.getStudyTherapies()){
				xmlStudyTherapy.setId(dbStudyTherapy.getId());
				if(xmlStudyTherapy.getStudyTherapyType().equals(dbStudyTherapy.getStudyTherapyType())){
					break;
				}else{
					xmlStudyTherapy.setId(null);
				}
			}
			if(xmlStudyTherapy.getId() == null){
				newStudyTherapyList.add(xmlStudyTherapy);
			}
		}
		
		//Adding the new StudyTherapies to the existing Study.
		for(StudyTherapy newStudyTherapy : newStudyTherapyList){
			dbStudy.getStudyTherapies().add(newStudyTherapy);
		}
		
		//Need to set the Study for the update to function
		for(StudyTherapy studyTherapy : dbStudy.getStudyTherapies()){
			studyTherapy.setStudy(dbStudy);
		}
		
	}

}
