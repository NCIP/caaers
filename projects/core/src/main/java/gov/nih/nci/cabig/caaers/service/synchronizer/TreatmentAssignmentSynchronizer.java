package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class synchronizes all TreatmentAssignments 
 * @author Monish Dombla
 *
 */
public class TreatmentAssignmentSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>  {

	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		if(xmlStudy.getTreatmentAssignments() != null){
			if(xmlStudy.getTreatmentAssignments().size() == 0){
				if(dbStudy.getTreatmentAssignments() != null){
					while(!dbStudy.getTreatmentAssignments().isEmpty()){
						dbStudy.getTreatmentAssignments().remove(0);
					}
				}
				return;
			}
		}
		
		
		List<TreatmentAssignment> newTreatmentAssignmentList = new ArrayList<TreatmentAssignment>();
		List<TreatmentAssignment> deleteTreatmentAssignmentList = new ArrayList<TreatmentAssignment>();
		TreatmentAssignment remTreatmentAssignment = null;
		
		//Identify New TreatmentAssignments and Also update existing ones.
		for(TreatmentAssignment xmlTreatmentAssignment : xmlStudy.getTreatmentAssignments()){
			for(TreatmentAssignment dbTreatmentAssignment : dbStudy.getTreatmentAssignments()){
				xmlTreatmentAssignment.setId(dbTreatmentAssignment.getId());
				if(xmlTreatmentAssignment.getCode().equals(dbTreatmentAssignment.getCode())){
					dbTreatmentAssignment.setComments(xmlTreatmentAssignment.getComments());
					dbTreatmentAssignment.setDescription(xmlTreatmentAssignment.getDescription());
					dbTreatmentAssignment.setDoseLevelOrder(xmlTreatmentAssignment.getDoseLevelOrder());
					break;
				}else{
					xmlTreatmentAssignment.setId(null);
				}
			}
			if(xmlTreatmentAssignment.getId() == null){
				newTreatmentAssignmentList.add(xmlTreatmentAssignment);
			}
		}
		
		//Identify TreamentAssignments to be Removed
		for(TreatmentAssignment dbTreatmentAssignment : dbStudy.getTreatmentAssignments()){
			for(TreatmentAssignment xmlTreatmentAssignment : xmlStudy.getTreatmentAssignments()){
				remTreatmentAssignment = new TreatmentAssignment();
				remTreatmentAssignment = dbTreatmentAssignment;
				if(remTreatmentAssignment.getCode().equals(xmlTreatmentAssignment.getCode())){
					remTreatmentAssignment = null;
					break;
				}
			}
			if(remTreatmentAssignment != null){
				deleteTreatmentAssignmentList.add(remTreatmentAssignment);
			}
		}
		
		//Add New TreatmentAssignments
		for(TreatmentAssignment newTreatmentAssignment : newTreatmentAssignmentList){
			dbStudy.getTreatmentAssignments().add(newTreatmentAssignment);
		}
		//Remove TreatmentmentAssignments
		for(TreatmentAssignment delTreatmentAssignment : deleteTreatmentAssignmentList){
			dbStudy.getTreatmentAssignments().remove(delTreatmentAssignment);
		}
		
		//Need to set the Study for the update to function
		for(TreatmentAssignment treatmentAssignment : dbStudy.getTreatmentAssignments()){
			treatmentAssignment.setStudy(dbStudy);
		}
		
		
	}//end method

}
