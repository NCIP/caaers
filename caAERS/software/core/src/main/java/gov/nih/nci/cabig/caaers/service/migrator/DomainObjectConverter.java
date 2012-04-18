package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;

import java.util.List;

public class DomainObjectConverter {
	
	public void convertEntityProcessingOutcomes(List<EntityErrorMessage> entityErrorMessages, 
			EntityProcessingOutcomes entityProcessingOutcomesDto){
		
		for(EntityErrorMessage entityErrorMessage : entityErrorMessages){
			EntityProcessingOutcomeType entityProcessingOutcomeType = new EntityProcessingOutcomeType();
			entityProcessingOutcomeType.setBusinessIdentifier(entityErrorMessage.getBusinessId());
			entityProcessingOutcomeType.setKlassName(entityErrorMessage.getKlassName());
			for(String message : entityErrorMessage.getMessages()){
				String messageDto = new String(message);
				entityProcessingOutcomeType.getMessage().add(messageDto);
			}
			
			entityProcessingOutcomesDto.getEntityProcessingOutcome().add(entityProcessingOutcomeType);
		}
	}
}
