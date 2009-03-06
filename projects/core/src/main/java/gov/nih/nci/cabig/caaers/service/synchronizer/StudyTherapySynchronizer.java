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
		
		if(xmlStudy.getStudyTherapies() != null){
			if(xmlStudy.getStudyTherapies().size() == 0){
				if(dbStudy.getStudyTherapies() != null){
					dbStudy.getStudyTherapies().clear();
				}
				return;
			}
		}

		List<StudyTherapy> newStudyTherapyList = new ArrayList<StudyTherapy>();
		List<StudyTherapy> deleteStudyTherapyList = new ArrayList<StudyTherapy>();
		StudyTherapy remStudyTherapy = null;
		
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
		
		//Identify StudyTherapy to be removed.
		for(StudyTherapy dbStudyTherapy : dbStudy.getStudyTherapies()){
			for(StudyTherapy xmlStudyTherapy : xmlStudy.getStudyTherapies()){
				remStudyTherapy = new StudyTherapy();
				remStudyTherapy = (StudyTherapy)dbStudyTherapy;
				if(remStudyTherapy.getStudyTherapyType().equals(xmlStudyTherapy.getStudyTherapyType())){
					remStudyTherapy = null;
					break;
				}
			}
			if(remStudyTherapy != null){
				deleteStudyTherapyList.add(remStudyTherapy);
			}
		}
		
		//Adding the new StudyTherapies to the existing Study.
		for(StudyTherapy newStudyTherapy : newStudyTherapyList){
			dbStudy.getStudyTherapies().add(newStudyTherapy);
		}
		
		//Removing the StudyTherapies from the existing Study
		for(StudyTherapy delStudyTherapy : deleteStudyTherapyList){
 			dbStudy.getStudyTherapies().remove(delStudyTherapy);
		}
		
		//Need to set the Study for the update to function
		for(StudyTherapy studyTherapy : dbStudy.getStudyTherapies()){
			studyTherapy.setStudy(dbStudy);
		}
		
	}

}
