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
		
		if(xmlStudy.getCtepStudyDiseases() != null){
			if(xmlStudy.getCtepStudyDiseases().size() == 0){
				if(dbStudy.getCtepStudyDiseases() != null){
					dbStudy.getCtepStudyDiseases().clear();
				}
				return;
			}
		}
		
		List<CtepStudyDisease> newCtepStudyDiseaseList = new ArrayList<CtepStudyDisease>();
		List<CtepStudyDisease> deleteCtepStudyDiseaseList = new ArrayList<CtepStudyDisease>();
		CtepStudyDisease remCtepStudyDisease = null;
		
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
		
		//Identify CtepStudyDiseases to be Removed
		for(CtepStudyDisease dbCtepStudyDisease : dbStudy.getCtepStudyDiseases()){
			for(CtepStudyDisease xmlCtepStudyDisease : xmlStudy.getCtepStudyDiseases()){
				remCtepStudyDisease = new CtepStudyDisease();
				remCtepStudyDisease = dbCtepStudyDisease;
				if(remCtepStudyDisease.getDiseaseTerm().equals(xmlCtepStudyDisease.getDiseaseTerm())){
					remCtepStudyDisease =  null;
					break;
				}
			}
			if(remCtepStudyDisease != null){
				deleteCtepStudyDiseaseList.add(remCtepStudyDisease);
			}
		}
		
		//Add New CtepStudyDiseases
		for(CtepStudyDisease newCtepStudyDisease : newCtepStudyDiseaseList){
			dbStudy.getCtepStudyDiseases().add(newCtepStudyDisease);
		}
		//Remove CtepStudyDiseases
		for(CtepStudyDisease delCtepStudyDisease : deleteCtepStudyDiseaseList){
			dbStudy.getCtepStudyDiseases().remove(delCtepStudyDisease);
		}
	}
	
	private void syncMeddraDiseases(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome){
		
		if(xmlStudy.getMeddraStudyDiseases() != null){
			if(xmlStudy.getMeddraStudyDiseases().size() == 0){
				if(dbStudy.getMeddraStudyDiseases() != null){
					dbStudy.getMeddraStudyDiseases().clear();
				}
				return;
			}
		}
		
		List<MeddraStudyDisease> newMeddraStudyDiseaseList = new ArrayList<MeddraStudyDisease>();
		List<MeddraStudyDisease> deleteMeddraStudyDiseaseList = new ArrayList<MeddraStudyDisease>();
		MeddraStudyDisease remMeddraStudyDisease = null;
		
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
		
		//Identify MeddraStudyDiseases to be Removed
		for(MeddraStudyDisease dbMeddraStudyDisease : dbStudy.getMeddraStudyDiseases()){
			for(MeddraStudyDisease xmlMeddraStudyDisease : xmlStudy.getMeddraStudyDiseases()){
				remMeddraStudyDisease = new MeddraStudyDisease();
				remMeddraStudyDisease = dbMeddraStudyDisease;
				if(remMeddraStudyDisease.getTerm().getMeddraCode().equals(xmlMeddraStudyDisease.getMeddraCode())){
					remMeddraStudyDisease =  null;
					break;
				}
			}
			if(remMeddraStudyDisease != null){
				deleteMeddraStudyDiseaseList.add(remMeddraStudyDisease);
			}
		}
		
		//Add New MeddraStudyDiseases
		for(MeddraStudyDisease newMeddraStudyDisease : newMeddraStudyDiseaseList){
			dbStudy.getMeddraStudyDiseases().add(newMeddraStudyDisease);
		}
		//Remove MeddraStudyDiseases
		for(MeddraStudyDisease delMeddraStudyDisease : deleteMeddraStudyDiseaseList){
			dbStudy.getMeddraStudyDiseases().remove(delMeddraStudyDisease);
		}
	}
}
