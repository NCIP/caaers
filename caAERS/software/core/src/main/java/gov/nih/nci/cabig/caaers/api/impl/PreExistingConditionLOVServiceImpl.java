package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.PreExistingConditionLOVService;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

public class PreExistingConditionLOVServiceImpl implements PreExistingConditionLOVService{
	
	private static Log logger = LogFactory.getLog(PreExistingConditionLOVServiceImpl.class);
	private PreExistingConditionDao preExistingConditionDao;

	@Transactional(readOnly=false)
	public List<EntityErrorMessage> importPreExistingConditions(
			List<PreExistingCondition> importedPreExistingConditions) {
		List<EntityErrorMessage> errorMessages = new ArrayList<EntityErrorMessage>();
		// load all pre-existing conditions from db and check if new ones need to be added
		List<PreExistingCondition> existingConditions = new ArrayList<PreExistingCondition>();
		existingConditions.addAll(preExistingConditionDao.getAll());
		Set<String> preExistingConditionTextSet = new HashSet<String>();
		for(PreExistingCondition condition : existingConditions){
			preExistingConditionTextSet.add(condition.getText());
		}
		
		// create new pre-existing condition and save it if it doesn't exist in db. this is case sensitive comparison so 
		// it is possible to have 2 pre-existing conditions with texts of same character sequence but in different cases
		for(PreExistingCondition importedPreExistingCondition : importedPreExistingConditions){
			EntityErrorMessage errorMessage = new EntityErrorMessage();
			errorMessage.setBusinessId(importedPreExistingCondition.getText());
			errorMessage.setKlassName(PreExistingCondition.class.getName());
			errorMessages.add(errorMessage);
			// do a case sensitive comparison of condition text
			if(!preExistingConditionTextSet.contains(importedPreExistingCondition.getText())){
				logger.info("didn't find prior condition with text: " + importedPreExistingCondition.getText() + " in db. " +
						"Creating new PreExisting Condition.");
				try {
					// didn't find a pre-existing condition in db with same case sensitive text. create new one and save.
					PreExistingCondition newPreExistingCondition = new PreExistingCondition();
					newPreExistingCondition.setText(importedPreExistingCondition.getText());
					preExistingConditionDao.save(newPreExistingCondition);
				
				} catch (Exception e) {
					// catch any exception in processing and return it in EntityErrorMessage object
					errorMessage.addMessage(e.getMessage());
					logger.error("encountered an error in importing pre-existing condition with text :" + importedPreExistingCondition.getText());
					logger.error(e.getMessage());
				}
			} 
		}
		return errorMessages;
	}

	public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
		this.preExistingConditionDao = preExistingConditionDao;
	}

}
