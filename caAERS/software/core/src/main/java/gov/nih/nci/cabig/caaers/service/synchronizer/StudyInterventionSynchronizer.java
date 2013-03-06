/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.AbstractMutableRetireableDomainObject;
import gov.nih.nci.cabig.caaers.domain.OtherIntervention;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ion C. Olaru
 *         Date: 5/3/12 -4:48 PM
 */
public class StudyInterventionSynchronizer implements Migrator<Study> {

    public void migrate(Study dest, Study src, DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome) {
    	HashMap<String, OtherIntervention> map = new HashMap<String, OtherIntervention>();
		for(OtherIntervention otherIntervention : dest.getActiveOtherInterventions()) {
			map.put(otherIntervention.getHashKey(), otherIntervention);
		}
		
		for(OtherIntervention xmlOtherIntervention : src.getOtherInterventions()){
			OtherIntervention otherIntervention = map.remove(xmlOtherIntervention.getHashKey());
			if(otherIntervention == null){
				//newly added one, so add it to study
				dest.addOtherIntervention(xmlOtherIntervention);
				continue;
			}
			//Update- do nothing
			
		}
		
		//now soft delete, all the ones not present in XML Study
		AbstractMutableRetireableDomainObject.retire(map.values());
		
		
//        List<OtherIntervention> otherInterventions = src.getOtherInterventions();
//        if (CollectionUtils.isEmpty(otherInterventions)) return;
//        Set<String> destInterventionsSet = new HashSet<String>();
//
//        for (OtherIntervention otherIntervention : dest.getOtherInterventions()) {
//            destInterventionsSet.add(otherIntervention.getHashKey());
//        }
//
//        for (OtherIntervention otherIntervention : otherInterventions) {
//            if (destInterventionsSet.add(otherIntervention.getHashKey())) {
//                dest.addOtherIntervention(otherIntervention);
//            }
//        }
    }

}
