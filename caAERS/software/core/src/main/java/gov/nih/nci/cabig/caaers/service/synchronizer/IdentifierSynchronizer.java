package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class synchronizes the identifiers 
 * @author Monish Dombla
 *
 * @param <E>
 */
public class IdentifierSynchronizer<E extends AbstractIdentifiableDomainObject> implements Migrator<E> {

	public void migrate(E dbObj, E xmlObj,
			DomainObjectImportOutcome<E> outcome) {
		
		List<Identifier> newIdentifiersList = new ArrayList<Identifier>();
		
		//Identify newly added Identifiers.
		for(Identifier xmlIdentifer : xmlObj.getIdentifiers()){
			if(xmlIdentifer instanceof OrganizationAssignedIdentifier){
				for(Identifier dbIdentifer : dbObj.getIdentifiers()){
					if(dbIdentifer instanceof OrganizationAssignedIdentifier){
						xmlIdentifer.setId(dbIdentifer.getId());
						if(xmlIdentifer.equals(dbIdentifer)){
							break;
						}else{
							xmlIdentifer.setId(null);
						}
					}
				}
				if(xmlIdentifer.getId() == null){
					newIdentifiersList.add(xmlIdentifer);
				}
			}
		}
		//Adding the new identifiers to the existing Study.
		for(Identifier newIdentifier : newIdentifiersList){
			dbObj.getIdentifiers().add(newIdentifier);
		}
	} //end method
}
