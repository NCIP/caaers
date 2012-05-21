package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepository;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ConfigProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;


/**
 * @author: Biju Joseph
 */
public class ConfigPropertyServiceImpl {
    private static Log log = LogFactory.getLog(ConfigPropertyServiceImpl.class);
    private ConfigPropertyRepository configPropertyRepository;

    public CaaersServiceResponse createOrUpdateConfigProperties(ConfigProperties configProperties) {
        CaaersServiceResponse response = Helper.createResponse();
        try{
            String configTypeName = configProperties.getName();
            ConfigPropertyType configType = ConfigPropertyType.valueOf(configTypeName);
            configPropertyRepository.removeAll(configType);

            for(gov.nih.nci.cabig.caaers.integration.schema.common.ConfigPropertyType xmlCp : configProperties.getConfigProperty()){
                ConfigProperty cp  = new ConfigProperty();
                cp.setCode(xmlCp.getCode());
                cp.setLastSynchedDate(new Date());
                cp.setConfigType(configType);
                cp.setName(xmlCp.getName());
                cp.setDescription(xmlCp.getDescription());
                configPropertyRepository.saveOrUpdate(cp);

                ProcessingOutcome outcome = Helper.createOutcome(ConfigProperty.class, cp.getCode(), false, "Synchronized : " + cp.getCode());
                Helper.populateProcessingOutcome(response, outcome);
            }

        }catch (Exception e){
            log.error(e);
            Helper.populateError(response, "WS_GEN_000", "Unable to synchronize the LOV :" + e.getMessage());
        }

        return response;
    }

    public void setConfigPropertyRepository(ConfigPropertyRepository configPropertyRepository) {
        this.configPropertyRepository = configPropertyRepository;
    }
}
