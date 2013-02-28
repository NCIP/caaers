/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.LabService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.LabCategoryDao;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.LabMigrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

public class LabServiceImpl implements LabService{
	
	private static Log logger = LogFactory.getLog(LabServiceImpl.class);
	private LabCategoryDao labCategoryDao;
	private LabMigrator labMigrator;

    public void setLabMigrator(LabMigrator labMigrator) {
		this.labMigrator = labMigrator;
	}

    private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public List<ProcessingOutcome> createOrUpdateLabs(List<LabCategory> labCategories) {
		List<ProcessingOutcome> errorMessages = new ArrayList<ProcessingOutcome>();
		for (LabCategory labCategory:labCategories){
			errorMessages.add(createOrUpdateLab(labCategory));
		}
		return errorMessages;
	}
	
	@Transactional(readOnly=false)
	public ProcessingOutcome createOrUpdateLab(LabCategory labCategory) {
		try {
			LabCategory dbLabCategory = labCategoryDao.getByName(labCategory.getName());
			// check if db category with same name exists
			DomainObjectImportOutcome<LabCategory> outcome = new DomainObjectImportOutcome<LabCategory>(); 
			if(dbLabCategory !=null) {
                logger.info("updating db Category with Name:" + labCategory.getName() + " with remote Category");
                labMigrator.migrate(labCategory, dbLabCategory,outcome);
                labCategoryDao.save(dbLabCategory);
                return Helper.createOutcome(LabCategory.class, labCategory.getName(), false, "Lab Category with name : " +
    					labCategory.getName() + " is updated. " + Helper.concatenateMessagesFromOutcome(outcome));
			} else {
				// db category doesn't exist. Create a new category.
				logger.info("didn't find db Category with Name:" + labCategory.getName() + ". Creating new Category");
				LabCategory newLabCategory = new LabCategory();
				labMigrator.migrate(labCategory, newLabCategory, outcome);
				labCategoryDao.save(newLabCategory);
				return Helper.createOutcome(LabCategory.class, labCategory.getName(), false, "Lab Category with name : " +
						labCategory.getName() + " is created. " +  Helper.concatenateMessagesFromOutcome(outcome) );
            }
			
		} catch (Exception e) {
			logger.error(e);
            return Helper.createOutcome(LabCategory.class, labCategory.getName(), true, e.getMessage());
		}
	}


	public void setLabCategoryDao(LabCategoryDao labCategoryDao) {
		this.labCategoryDao = labCategoryDao;
	}

}
