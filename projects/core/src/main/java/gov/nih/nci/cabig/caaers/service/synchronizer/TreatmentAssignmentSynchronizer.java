package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * This Class synchronizes all TreatmentAssignments 
 * @author Monish Dombla
 *
 */
public class TreatmentAssignmentSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>  {

	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		List<TreatmentAssignment> newTreatmentAssignmentList = new ArrayList<TreatmentAssignment>();
		
		//Identify New TreatmentAssignments and Also update existing ones.
		for(TreatmentAssignment xmlTreatmentAssignment : xmlStudy.getTreatmentAssignments()){
			for(TreatmentAssignment dbTreatmentAssignment : dbStudy.getTreatmentAssignments()){
				xmlTreatmentAssignment.setId(dbTreatmentAssignment.getId());
				if(xmlTreatmentAssignment.getCode().equals(dbTreatmentAssignment.getCode())){
					dbTreatmentAssignment.setDescription(xmlTreatmentAssignment.getDescription());
					if(xmlTreatmentAssignment.getComments() != null &&  StringUtils.isNotEmpty(xmlTreatmentAssignment.getComments())){
						dbTreatmentAssignment.setComments(xmlTreatmentAssignment.getComments());
					}
					if(xmlTreatmentAssignment.getDoseLevelOrder() != null){
						dbTreatmentAssignment.setDoseLevelOrder(xmlTreatmentAssignment.getDoseLevelOrder());
					}
					break;
				}else{
					xmlTreatmentAssignment.setId(null);
				}
			}
			if(xmlTreatmentAssignment.getId() == null){
				newTreatmentAssignmentList.add(xmlTreatmentAssignment);
			}
		}
		
		//Add New TreatmentAssignments
		for(TreatmentAssignment newTreatmentAssignment : newTreatmentAssignmentList){
			dbStudy.getTreatmentAssignments().add(newTreatmentAssignment);
		}
		
		//Need to set the Study for the update to function
		for(TreatmentAssignment treatmentAssignment : dbStudy.getTreatmentAssignments()){
			treatmentAssignment.setStudy(dbStudy);
		}
		
	}//end method

}
