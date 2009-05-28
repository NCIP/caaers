package gov.nih.nci.cabig.caaers.service.synchronizer;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.AbstractMutableRetireableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;

/**
 * This Class synchronizes all TreatmentAssignments 
 * @author Monish Dombla
 * @author Biju Joseph (refactored)
 *
 */
public class TreatmentAssignmentSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>  {
	
	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
		
		
		//if there is no TAC available in XMLStudy, ignore
		if(CollectionUtils.isEmpty(xmlStudy.getTreatmentAssignments())){
			return;
		}
		
		//create an Index of existing ones (available in DB)
		Hashtable<String, TreatmentAssignment> dbTacIndexMap = new Hashtable<String, TreatmentAssignment>();
		for(TreatmentAssignment ta : dbStudy.getActiveTreatmentAssignments()){
			dbTacIndexMap.put(ta.getCode(), ta);
		}
		
		
		//Identify New TreatmentAssignments and also update existing ones.
		for(TreatmentAssignment xmlTreatmentAssignment : xmlStudy.getTreatmentAssignments()){
			
			TreatmentAssignment ta = dbTacIndexMap.remove(xmlTreatmentAssignment.getCode());
			if(ta == null){
				//newly added one, so add it to study 
				dbStudy.addTreatmentAssignment(xmlTreatmentAssignment);
				continue;
			}
			
			 //existing one - update the details 
			if(StringUtils.isNotEmpty(xmlTreatmentAssignment.getDescription()))
				ta.setDescription(xmlTreatmentAssignment.getDescription());
			if(StringUtils.isNotEmpty(xmlTreatmentAssignment.getCode()))
				ta.setComments(xmlTreatmentAssignment.getComments());
			if(xmlTreatmentAssignment.getDoseLevelOrder() != null)
				ta.setDoseLevelOrder(xmlTreatmentAssignment.getDoseLevelOrder());
		}
		
		//soft delete - all the TACs that were not present in XML Study
		AbstractMutableRetireableDomainObject.retire(dbTacIndexMap.values());
		
		
		
	}//end method

}
