package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

public class StudyEvaluationPeriodsSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{

	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		List<Epoch> newEpochList = new ArrayList<Epoch>();
		
		//Identify New Epochs
		for(Epoch xmlEpoch : xmlStudy.getEpochs()){
			for(Epoch dbEpoch : dbStudy.getEpochs()){
				xmlEpoch.setId(dbEpoch.getId());
				if(xmlEpoch.getName().equals(dbEpoch.getName())){
					syncSolicitedAEs(xmlEpoch,dbEpoch);
					break;
				}else{
					xmlEpoch.setId(null);
				}
			}
			if(xmlEpoch.getId() == null){
				newEpochList.add(xmlEpoch);
			}
		}
		
		//Add New Epochs
		for(Epoch newEpoch : newEpochList){
			dbStudy.getEpochs().add(newEpoch);
		}
	}
	
	private void syncSolicitedAEs(Epoch xmlEpoch,Epoch dbEpoch){
		
		if(xmlEpoch.getArms().get(0).getSolicitedAdverseEvents() != null){
			if(xmlEpoch.getArms().get(0).getSolicitedAdverseEvents().size() == 0){
				if(dbEpoch.getArms().get(0).getSolicitedAdverseEvents() != null){
					dbEpoch.getArms().get(0).getSolicitedAdverseEvents().clear();
				}
				return;
			}
		}
		
		List<SolicitedAdverseEvent> newSolicitedAdverseEventList = new ArrayList<SolicitedAdverseEvent>();
		
		//Identify Newly added SolicitedAdverseEvents
		for(SolicitedAdverseEvent xmlSolicitedAdverseEvent : xmlEpoch.getArms().get(0).getSolicitedAdverseEvents()){
			for(SolicitedAdverseEvent dbSolicitedAdverseEvent : dbEpoch.getArms().get(0).getSolicitedAdverseEvents()){
				xmlSolicitedAdverseEvent.setId(dbSolicitedAdverseEvent.getId());
				if(xmlSolicitedAdverseEvent.equals(dbSolicitedAdverseEvent)){
					break;
				}else{
					xmlSolicitedAdverseEvent.setId(null);
				}
			}
			if(xmlSolicitedAdverseEvent.getId() == null){
				newSolicitedAdverseEventList.add(xmlSolicitedAdverseEvent);
			}
		}
		
		//Add New Epochs
		for(SolicitedAdverseEvent newSolicitedAdverseEvent : newSolicitedAdverseEventList){
			dbEpoch.getArms().get(0).getSolicitedAdverseEvents().add(newSolicitedAdverseEvent);
		}
	}
}
