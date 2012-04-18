package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.PriorTherapyLOVService;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

public class PriorTherapyLOVServiceImpl implements PriorTherapyLOVService{
	
	private static Log logger = LogFactory.getLog(PriorTherapyLOVServiceImpl.class);
	private PriorTherapyDao priorTherapyDao;

	@Transactional(readOnly=false)
	public List<EntityErrorMessage> importPriorTherapies(
			List<PriorTherapy> importedPriorTherapies) {
		List<EntityErrorMessage> errorMessages = new ArrayList<EntityErrorMessage>();
		// load all prior therapies from db and check if new ones need to be added
		List<PriorTherapy> existingTherapies = new ArrayList<PriorTherapy>();
		existingTherapies.addAll(priorTherapyDao.getAll());
		Set<String> priorTherapyTextSet = new HashSet<String>();
		for(PriorTherapy therapy : existingTherapies){
			priorTherapyTextSet.add(therapy.getText());
		}
		
		// create new prior therapy and save it if it doesn't exist in db.
		// this is case sensitive comparison so it is possible to have 2 therapies with texts of same character sequence but in different cases
		for(PriorTherapy importedPriorTherapy : importedPriorTherapies){
			EntityErrorMessage errorMessage = new EntityErrorMessage();
			errorMessage.setKlassName(PriorTherapy.class.getName());
			errorMessage.setBusinessId(importedPriorTherapy.getText());
			errorMessages.add(errorMessage);
			// do a case sensitive comparison of therapy text
			if(!priorTherapyTextSet.contains(importedPriorTherapy.getText())){
				logger.info("didn't find prior therapy with text: " + importedPriorTherapy.getText() + " in db. Creating new Prior Therapy.");
				try {
					// didn't find a therapy in db with same case sensitive text. create new one and save.
					PriorTherapy newPriorTherapy = new PriorTherapy();
					newPriorTherapy.setText(importedPriorTherapy.getText());
					priorTherapyDao.save(newPriorTherapy);
				
				} catch (Exception e) {
					// catch any exception in processing and return it in EntityErrorMessage object
					errorMessage.addMessage(e.getMessage());
					logger.error("encountered an error in importing prior thereapy with text :" + importedPriorTherapy.getText());
					logger.error(e.getMessage());
				}
			} 
		}
		return errorMessages;
	}

	public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}

}
