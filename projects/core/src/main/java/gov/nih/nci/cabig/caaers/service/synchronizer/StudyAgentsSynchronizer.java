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
		
		List<StudyAgent> newStudyAgentList = new ArrayList<StudyAgent>();
		
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
		
		//Adding the new StudyAgents to the existing Study.
		for(StudyAgent newStudyAgent : newStudyAgentList){
			dbStudy.getStudyAgents().add(newStudyAgent);
		}
		
		//Need to set the Study for the update to function
		for(StudyAgent studyAgent : dbStudy.getStudyAgents()){
			studyAgent.setStudy(dbStudy);
		}
	}
}
