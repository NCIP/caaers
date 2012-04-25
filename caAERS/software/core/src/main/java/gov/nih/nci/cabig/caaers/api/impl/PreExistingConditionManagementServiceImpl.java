package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.PreExistingConditionManagementService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.query.PreExistingConditionQuery;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditions;
import gov.nih.nci.cabig.caaers.service.migrator.PreExistingConditionConverter;
import gov.nih.nci.cabig.caaers.service.migrator.PreExistingConditionMigrator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PreExistingConditionManagementServiceImpl implements PreExistingConditionManagementService {
	
	private static Log logger = LogFactory.getLog(PreExistingConditionManagementServiceImpl.class);
	private PreExistingConditionDao preExistingConditionDao;
    private PreExistingConditionMigrator preExistingConditionMigrator;
    private PreExistingConditionConverter preExistingConditionConverter;

    @Transactional(readOnly=false)
    public CaaersServiceResponse importPreExistingConditions(PreExistingConditions xmlPreExistingConditions) {

        CaaersServiceResponse response = Helper.createResponse();
        for(PreExistingConditionType xmlPreCondition : xmlPreExistingConditions.getPreExistingCondition()){

            ProcessingOutcome outcome = null;

            try{

                PreExistingCondition inputPreCondition = preExistingConditionConverter.convert(xmlPreCondition);

                PreExistingConditionQuery preConditionQuery = new PreExistingConditionQuery();
                preConditionQuery.filterByMeddraCode(inputPreCondition.getMeddraLltCode());

                PreExistingCondition dbPreCondition = fetchPreExistingCondition(preConditionQuery);
                if(dbPreCondition == null){
                    dbPreCondition = inputPreCondition;
                }

                preExistingConditionMigrator.migrate(inputPreCondition, dbPreCondition, null);
                //BJ: this is an overkill for new-device,
                // but it is okay, (it will put the last sync date) and we are not loosing much processing power anyway.
                preExistingConditionDao.save(dbPreCondition);

                outcome = Helper.createOutcome(PreExistingCondition.class, inputPreCondition.getMeddraLltCode(), false,
                        "Processed " + inputPreCondition.getText());
            }catch (Exception e){
                logger.error("Error while processing PreExistingCondition therapy ", e);
                String message = "Unable to process : " + e.getMessage();
                outcome = Helper.createOutcome(PreExistingCondition.class, xmlPreCondition.getMeddraLltCode(), true, message);
            }
            Helper.populateProcessingOutcome(response, outcome);
        }

        return response;
    }
    public PreExistingCondition fetchPreExistingCondition(PreExistingConditionQuery preExistingConditionQuery){
        List<PreExistingCondition> list = (List<PreExistingCondition>) preExistingConditionDao.search(preExistingConditionQuery);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public PreExistingConditionDao getPreExistingConditionDao() {
        return preExistingConditionDao;
    }

    public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
		this.preExistingConditionDao = preExistingConditionDao;
	}

    public PreExistingConditionMigrator getPreExistingConditionMigrator() {
        return preExistingConditionMigrator;
    }

    public void setPreExistingConditionMigrator(PreExistingConditionMigrator preExistingConditionMigrator) {
        this.preExistingConditionMigrator = preExistingConditionMigrator;
    }

    public PreExistingConditionConverter getPreExistingConditionConverter() {
        return preExistingConditionConverter;
    }

    public void setPreExistingConditionConverter(PreExistingConditionConverter preExistingConditionConverter) {
        this.preExistingConditionConverter = preExistingConditionConverter;
    }
}
