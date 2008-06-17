package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class synchronizes the study identifiers 
 * @author Monish Dombla
 *
 * @param <E>
 */
public class IdentifierSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study> {

	public void migrate(Study dbStudy, Study xmlStudy,
			DomainObjectImportOutcome<Study> outcome) {
		
		List<Identifier> newIdentifiersList = new ArrayList<Identifier>();
		List<Identifier> deleteIdentifiersList = new ArrayList<Identifier>();
		OrganizationAssignedIdentifier remIdentifier = null;
		
		//Identify newly added Identifiers.
		for(Identifier xmlIdentifer : xmlStudy.getOrganizationAssignedIdentifiers()){
			for(Identifier dbIdentifer : dbStudy.getOrganizationAssignedIdentifiers()){
				xmlIdentifer.setId(dbIdentifer.getId());
				if(xmlIdentifer.equals(dbIdentifer)){
					break;
				}else{
					xmlIdentifer.setId(null);
				}
			}
			if(xmlIdentifer.getId() == null){
				newIdentifiersList.add(xmlIdentifer);
			}
		}
		
		//Identify identifiers to be removed.
		for(Identifier dbIdentifer : dbStudy.getOrganizationAssignedIdentifiers()){
			for(Identifier xmlIdentifer : xmlStudy.getOrganizationAssignedIdentifiers()){
				remIdentifier = new OrganizationAssignedIdentifier();
				remIdentifier = (OrganizationAssignedIdentifier)dbIdentifer;
				if(remIdentifier.equals(xmlIdentifer)){
					remIdentifier = null;
					break;
				}
			}
			if(remIdentifier != null){
				deleteIdentifiersList.add(remIdentifier);
			}
		}
		
		//Adding the new identifiers to the existing Study.
		for(Identifier newIdentifier : newIdentifiersList){
			dbStudy.getIdentifiers().add(newIdentifier);
		}
		
		//Removing the identifiers from the existing Study
		for(Identifier delIdentifier : deleteIdentifiersList){
 			dbStudy.getIdentifiers().remove(delIdentifier);
		}
	} //end method
}
