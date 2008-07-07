package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

public class StudyAgentsSynchronizer  implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{
	
	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		if(xmlStudy.getStudyAgents() != null){
			if(xmlStudy.getStudyAgents().size() == 0){
				if(dbStudy.getStudyAgents() != null && !dbStudy.getStudyAgents().isEmpty()){
					while(!dbStudy.getStudyAgents().isEmpty()){
						dbStudy.getStudyAgents().remove(0);
					}
				}
				return;
			}
		}
		
		List<StudyAgent> newStudyAgentList = new ArrayList<StudyAgent>();
		List<StudyAgent> deleteStudyAgentList = new ArrayList<StudyAgent>();
		StudyAgent remStudyAgent = null;
		
		//Identify newly added StudyAgent.
		for(StudyAgent xmlStudyAgent : xmlStudy.getStudyAgents()){
			for(StudyAgent dbStudyAgent : dbStudy.getStudyAgents()){
				
				String xmlNscNumber = xmlStudyAgent.getAgent().getNscNumber();
				String dbNscNumber = dbStudyAgent.getAgent().getNscNumber();
				
				xmlStudyAgent.setId(dbStudyAgent.getId());
				if(xmlNscNumber != null && dbNscNumber != null) {
					if(xmlNscNumber.equals(dbNscNumber)){
						break;
					}else{
						xmlStudyAgent.setId(null);
					}
				}else{
					if(xmlStudyAgent.getAgentName().equals(dbStudyAgent.getAgentName())){
						break;
					}else{
						xmlStudyAgent.setId(null);
					}
				}
				
			}
			if(xmlStudyAgent.getId() == null){
				newStudyAgentList.add(xmlStudyAgent);
			}
		}
		
		//Identify StudyAgent to be removed.
		for(StudyAgent dbStudyAgent : dbStudy.getStudyAgents()){
			for(StudyAgent xmlStudyAgent : xmlStudy.getStudyAgents()){
				remStudyAgent = new StudyAgent();
				remStudyAgent = dbStudyAgent;
				
				String xmlNscNumber = xmlStudyAgent.getAgent().getNscNumber();
				String dbNscNumber = dbStudyAgent.getAgent().getNscNumber();
				
				if(dbNscNumber != null && xmlNscNumber != null){
					if(dbNscNumber.equals(xmlNscNumber)){
						remStudyAgent = null;
						break;
					}
				}else{
					if(remStudyAgent.getAgentName().equals(xmlStudyAgent.getAgentName())){
						remStudyAgent = null;
						break;
					}
				}
				
			}
			if(remStudyAgent != null){
				deleteStudyAgentList.add(remStudyAgent);
			}
		}
		
		//Adding the new StudyAgents to the existing Study.
		for(StudyAgent newStudyAgent : newStudyAgentList){
			dbStudy.getStudyAgents().add(newStudyAgent);
		}
		
		//Removing the StudyAgents from the existing Study
		for(StudyAgent delStudyAgent : deleteStudyAgentList){
 			dbStudy.getStudyAgents().remove(delStudyAgent);
		}
		
		//Need to set the Study for the update to function
		for(StudyAgent studyAgent : dbStudy.getStudyAgents()){
			studyAgent.setStudy(dbStudy);
		}
	}
}
