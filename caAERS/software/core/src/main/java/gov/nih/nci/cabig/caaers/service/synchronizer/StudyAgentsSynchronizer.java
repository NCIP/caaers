/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.AbstractMutableRetireableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

/**
 * @author Monish Domla
 * @author Biju Joseph (refactored)
 *
 */
public class StudyAgentsSynchronizer  implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{
	
	public void migrate(Study dbStudy, Study xmlStudy, DomainObjectImportOutcome<Study> outcome) {
		
		//Ignore if the section is empty- Update- This is no longer true since CTEP sync should remove and override the agents
//		if(CollectionUtils.isEmpty(xmlStudy.getStudyAgents())){
//			return;
//		}
		
		//create an index of existing agents in the dbStudy.
		HashMap<String, StudyAgent> dbStudyAgentIndexMap = new HashMap<String, StudyAgent>();
		for(StudyAgent sa : dbStudy.getActiveStudyAgents()){
			dbStudyAgentIndexMap.put(sa.getAgentName(), sa);
		}

		//identify new study agents, also update existing ones.
		for(StudyAgent xmlStudyAgent : xmlStudy.getStudyAgents()){
			StudyAgent sa = dbStudyAgentIndexMap.remove(xmlStudyAgent.getAgentName());
			if(sa == null){
				//newly added one, so add it to study
				dbStudy.addStudyAgent(xmlStudyAgent);
				continue;
			}
			
			//existing one - so update if necessary
			//BJ : the original code did not do anything, so nothing to update. 
			//Update the StudyAgents as it can change between Adders Sync
			sa.setIndType(xmlStudyAgent.getIndType());
			sa.setPartOfLeadIND(xmlStudyAgent.getPartOfLeadIND());
			sa.getStudyAgentINDAssociations().clear();
			for(StudyAgentINDAssociation ass : xmlStudyAgent.getStudyAgentINDAssociations()){
				sa.addStudyAgentINDAssociation(ass);
			}
		}
		
		//now soft delete, all the ones not present in XML Study
		AbstractMutableRetireableDomainObject.retire(dbStudyAgentIndexMap.values());
	}
}
