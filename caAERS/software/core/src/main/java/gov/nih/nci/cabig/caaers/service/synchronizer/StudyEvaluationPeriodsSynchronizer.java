package gov.nih.nci.cabig.caaers.service.synchronizer;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.AbstractMutableRetireableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Monish Domla
 * @author Biju Joseph (refactored)
 *
 */
public class StudyEvaluationPeriodsSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{

	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		//ignore if epochs is empty in xmlStudy
		if(CollectionUtils.isEmpty(xmlStudy.getEpochs()) ){
			return;
		}
		
		//create an index map
		HashMap<String, Epoch> dbEpochIndexMap = new HashMap<String,Epoch>();
		for(Epoch epoch : dbStudy.getActiveEpochs()){
			dbEpochIndexMap.put(epoch.getName(), epoch);
		}
		
		//identify new epochs, also modify existing ones
		for(Epoch xmlEpoch : xmlStudy.getEpochs()){
			Epoch dbEpoch = dbEpochIndexMap.remove(xmlEpoch.getName());
			if(dbEpoch == null){
				//newly added, so add it to study
				dbStudy.addEpoch(xmlEpoch);
				continue;
			}
			//update the epoch details & solicited aes
			dbEpoch.setDescriptionText(xmlEpoch.getDescriptionText());
			dbEpoch.setEpochOrder(xmlEpoch.getEpochOrder());
			
			//sync - solicited aes
			if(!CollectionUtils.isEmpty(xmlEpoch.getArms())){
				syncSolicitedAEs(xmlEpoch, dbEpoch);
			}
			
		}
		
		//now soft delete the Epochs that are not in xmlStudy
		AbstractMutableRetireableDomainObject.retire(dbEpochIndexMap.values());
		
	}
	
	/**
	 * {@link SolicitedAdverseEvent}s, just need to be removed, if, not present in xml study
	 * @param xmlEpoch
	 * @param dbEpoch
	 */
	private void syncSolicitedAEs(Epoch xmlEpoch,Epoch dbEpoch){
		
		Arm xmlArm = xmlEpoch.getArms().get(0);
		Arm dbArm = dbEpoch.getArms().get(0);
		
		List<SolicitedAdverseEvent> xmlSolicitedEvents = xmlArm.getSolicitedAdverseEvents();
		List<SolicitedAdverseEvent> dbSolicitedEvents = dbArm.getSolicitedAdverseEvents();
		
		//if solicited evenst are not present in xmlStudy, check if dbStudy if empty (create one)
		if(CollectionUtils.isEmpty(xmlSolicitedEvents)){
			if(dbSolicitedEvents == null){
				dbArm.setSolicitedAdverseEvents(new ArrayList<SolicitedAdverseEvent>());
			}
			return; //already did the processing so QUIT
		}
		
		//Create and Index of  DB Solicited Adverse events
		HashMap<SolicitedAdverseEvent, SolicitedAdverseEvent> dbSolicitedEventIndexMap = new HashMap<SolicitedAdverseEvent, SolicitedAdverseEvent>();
		for(SolicitedAdverseEvent dbSolicitedAdverseEvent : dbSolicitedEvents){
			dbSolicitedEventIndexMap.put(dbSolicitedAdverseEvent, dbSolicitedAdverseEvent);
		}
		
		//Add new solicited events
		for(SolicitedAdverseEvent xmlSolicitedEvent : xmlSolicitedEvents){
			if(dbSolicitedEventIndexMap.remove(xmlSolicitedEvent) == null){
				//new - add it to the dbArm
				dbSolicitedEvents.add(xmlSolicitedEvent);
			}
		}
		
		//remove all the ones' not available in xml study.
		for(SolicitedAdverseEvent solicitedAdverseEvent : dbSolicitedEventIndexMap.keySet()){
			dbSolicitedEvents.remove(solicitedAdverseEvent);
		}
		
	}
	
}
