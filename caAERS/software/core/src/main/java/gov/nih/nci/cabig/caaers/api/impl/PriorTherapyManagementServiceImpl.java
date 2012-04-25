package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.PriorTherapyManagementService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.query.PriorTherapyQuery;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapies;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapyType;
import gov.nih.nci.cabig.caaers.service.migrator.PriorTherapyConverter;
import gov.nih.nci.cabig.caaers.service.migrator.PriorTherapyMigrator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PriorTherapyManagementServiceImpl implements PriorTherapyManagementService {
	
	private static Log logger = LogFactory.getLog(PriorTherapyManagementServiceImpl.class);
	private PriorTherapyDao priorTherapyDao;
    private PriorTherapyMigrator priorTherapyMigrator;
    private PriorTherapyConverter priorTherapyConverter;

    @Transactional(readOnly=false)
    public CaaersServiceResponse importPriorTherapies(PriorTherapies xmlPriorTherapies) {

        CaaersServiceResponse response = Helper.createResponse();
        for(PriorTherapyType xmlPriorTherapy : xmlPriorTherapies.getPriorTherapy()){

            ProcessingOutcome outcome = null;

            try{

                PriorTherapy inputPriorTherapy = priorTherapyConverter.convert(xmlPriorTherapy);

                PriorTherapyQuery priorTherapyQuery = new PriorTherapyQuery();
                priorTherapyQuery.filterByMeddraCode(inputPriorTherapy.getMeddraCode());

                PriorTherapy dbPriorTherapy = fetchPriorTherapy(priorTherapyQuery);
                if(dbPriorTherapy == null){
                    dbPriorTherapy = inputPriorTherapy;
                }

                priorTherapyMigrator.migrate(inputPriorTherapy, dbPriorTherapy, null);
                //BJ: this is an overkill for new-device,
                // but it is okay, (it will put the last sync date) and we are not loosing much processing power anyway.
                priorTherapyDao.save(dbPriorTherapy);

                outcome = Helper.createOutcome(PriorTherapy.class, inputPriorTherapy.getMeddraCode(), false,
                        "Processed " + inputPriorTherapy.getText());
            }catch (Exception e){
                logger.error("Error while processing prior therapy ", e);
                String message = "Unable to process : " + e.getMessage();
                outcome = Helper.createOutcome(PriorTherapy.class, xmlPriorTherapy.getMeddraCode(), true, message);
            }
            Helper.populateProcessingOutcome(response, outcome);
        }

        return response;
    }
    
    public PriorTherapy fetchPriorTherapy(PriorTherapyQuery priorTherapyQuery){
        List<PriorTherapy> list = (List<PriorTherapy>) priorTherapyDao.search(priorTherapyQuery);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

	public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}

    public PriorTherapyDao getPriorTherapyDao() {
        return priorTherapyDao;
    }

    public PriorTherapyMigrator getPriorTherapyMigrator() {
        return priorTherapyMigrator;
    }

    public void setPriorTherapyMigrator(PriorTherapyMigrator priorTherapyMigrator) {
        this.priorTherapyMigrator = priorTherapyMigrator;
    }

    public PriorTherapyConverter getPriorTherapyConverter() {
        return priorTherapyConverter;
    }

    public void setPriorTherapyConverter(PriorTherapyConverter priorTherapyConverter) {
        this.priorTherapyConverter = priorTherapyConverter;
    }
}
