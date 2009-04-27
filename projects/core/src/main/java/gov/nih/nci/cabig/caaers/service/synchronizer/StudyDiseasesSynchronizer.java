package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

public class StudyDiseasesSynchronizer  implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{

	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		syncCtepDiseases(dbStudy,xmlStudy,outcome);
		syncMeddraDiseases(dbStudy,xmlStudy,outcome);
		
	}
	
	private void syncCtepDiseases(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome){
		
		List<CtepStudyDisease> newCtepStudyDiseaseList = new ArrayList<CtepStudyDisease>();
		
		//Identify New CtepDiseases .
		for(CtepStudyDisease xmlCtepStudyDisease : xmlStudy.getCtepStudyDiseases()){
			for(CtepStudyDisease dbCtepStudyDisease : dbStudy.getCtepStudyDiseases()){
				xmlCtepStudyDisease.setId(dbCtepStudyDisease.getId());
				if(xmlCtepStudyDisease.getDiseaseTerm().equals(dbCtepStudyDisease.getDiseaseTerm())){
					dbCtepStudyDisease.setLeadDisease(xmlCtepStudyDisease.getLeadDisease());
					break;
				}else{
					xmlCtepStudyDisease.setId(null);
				}
			}
			if(xmlCtepStudyDisease.getId() == null){
				newCtepStudyDiseaseList.add(xmlCtepStudyDisease);
			}
		}
		
		//Add New CtepStudyDiseases
		for(CtepStudyDisease newCtepStudyDisease : newCtepStudyDiseaseList){
			dbStudy.getCtepStudyDiseases().add(newCtepStudyDisease);
		}
	}
	
	private void syncMeddraDiseases(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome){
		
		List<MeddraStudyDisease> newMeddraStudyDiseaseList = new ArrayList<MeddraStudyDisease>();
		
		//Identify New MeddraStudyDiseases .
		for(MeddraStudyDisease xmlMeddraStudyDisease : xmlStudy.getMeddraStudyDiseases()){
			for(MeddraStudyDisease dbMeddraStudyDisease : dbStudy.getMeddraStudyDiseases()){
				xmlMeddraStudyDisease.setId(dbMeddraStudyDisease.getId());
				if(xmlMeddraStudyDisease.getMeddraCode().equals(dbMeddraStudyDisease.getTerm().getMeddraCode())){
					break;
				}else{
					xmlMeddraStudyDisease.setId(null);
				}
			}
			if(xmlMeddraStudyDisease.getId() == null){
				newMeddraStudyDiseaseList.add(xmlMeddraStudyDisease);
			}
		}
		
		//Add New MeddraStudyDiseases
		for(MeddraStudyDisease newMeddraStudyDisease : newMeddraStudyDiseaseList){
			dbStudy.getMeddraStudyDiseases().add(newMeddraStudyDisease);
		}
	}
}
