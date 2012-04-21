package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;

import java.util.List;

public class ProcessingOutcomeConverter {
	
	public void convertEntityProcessingOutcomes(List<ProcessingOutcome> outcomes,
			EntityProcessingOutcomes entityProcessingOutcomesDto){
		
		for(ProcessingOutcome outcome : outcomes){
			EntityProcessingOutcomeType entityProcessingOutcomeDto = new EntityProcessingOutcomeType();
            entityProcessingOutcomeDto.setBusinessIdentifier(outcome.getBusinessId());
			entityProcessingOutcomeDto.setKlassName(outcome.getKlassName());
			for(String message : outcome.getMessages()){
				String messageDto = new String(message);
				entityProcessingOutcomeDto.getMessage().add(messageDto);
			}
			entityProcessingOutcomeDto.setFailed(outcome.isFailed());
			entityProcessingOutcomesDto.getEntityProcessingOutcome().add(entityProcessingOutcomeDto);
		}
	}
}
