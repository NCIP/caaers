package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessageType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessages;

import java.util.List;

public class DomainObjectConverter {
	
	public void convertEntityErrorMessages(List<EntityErrorMessage> entityErrorMessages, 
			EntityErrorMessages entityErrorMessagesDto){
		
		for(EntityErrorMessage entityErrorMessage : entityErrorMessages){
			EntityErrorMessageType entityErrorMessageType = new EntityErrorMessageType();
			entityErrorMessageType.setBusinessIdentifier(entityErrorMessage.getBusinessId());
			for(String message : entityErrorMessage.getMessages()){
				String messageDto = new String(message);
				entityErrorMessageType.getMessage().add(messageDto);
			}
			
			entityErrorMessagesDto.getEntityErrorMessage().add(entityErrorMessageType);
		}
	}
}
