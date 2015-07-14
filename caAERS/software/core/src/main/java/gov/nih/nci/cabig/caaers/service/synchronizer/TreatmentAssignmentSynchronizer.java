/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer;

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
 * @author Ion C. Olaru
 *
 */
public class TreatmentAssignmentSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>  {
	
	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {
        //create an Index of existing ones (available in DB)
        Hashtable<String, TreatmentAssignment> dbTacIndexMap = new Hashtable<String, TreatmentAssignment>();
        Hashtable<String, TreatmentAssignment> dbCtepIndexMap = new Hashtable<String, TreatmentAssignment>();
        for (TreatmentAssignment ta : dbStudy.getActiveTreatmentAssignments()) {
            dbTacIndexMap.put(ta.getCode().toUpperCase(), ta);
            if(ta.getCtepDbIdentifier() != null && !ta.getCtepDbIdentifier().isEmpty()) {
            	dbCtepIndexMap.put(ta.getCtepDbIdentifier().toUpperCase(), ta);
            }
        }

        //Identify New TreatmentAssignments and also update existing ones.
        for (TreatmentAssignment xmlTreatmentAssignment : xmlStudy.getTreatmentAssignments()) {

            TreatmentAssignment ta = null;
            if (xmlTreatmentAssignment.getCtepDbIdentifier() != null) {
            	ta = dbCtepIndexMap.get(xmlTreatmentAssignment.getCtepDbIdentifier().toUpperCase());
            }
            if(ta == null) {
            	ta = dbTacIndexMap.remove(xmlTreatmentAssignment.getCode().toUpperCase());
            }

            if (ta == null) {
                //newly added one, so add it to study
                dbStudy.addTreatmentAssignment(xmlTreatmentAssignment);
                continue;
            }

            //existing one - update the details
            if (StringUtils.isNotEmpty(xmlTreatmentAssignment.getDescription())) ta.setDescription(xmlTreatmentAssignment.getDescription());
            if (StringUtils.isNotEmpty(xmlTreatmentAssignment.getComments())) ta.setComments(xmlTreatmentAssignment.getComments());
            if (xmlTreatmentAssignment.getDoseLevelOrder() != null) ta.setDoseLevelOrder(xmlTreatmentAssignment.getDoseLevelOrder());
        }

        //soft delete - all the TACs that were not present in XML Study
		AbstractMutableRetireableDomainObject.retire(dbTacIndexMap.values());

	}

}
