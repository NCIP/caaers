package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.impl.ConfigPropertyServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ConfigProperties;
import gov.nih.nci.cabig.caaers.ws.ConfigPropertiesWebService;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author: Biju Joseph
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.ConfigPropertiesWebService",
        serviceName="ConfigPropertiesWebService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ConfigPropertiesWebServiceImpl implements ConfigPropertiesWebService{
    private ConfigPropertyServiceImpl configPropertyService;
    public CaaersServiceResponse createOrUpdateConfigProperties(@WebParam(name = "ConfigProperties",
            targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common") ConfigProperties configProperties) {
        return configPropertyService.createOrUpdateConfigProperties(configProperties);
    }

    public void setConfigPropertyService(ConfigPropertyServiceImpl configPropertyService) {
        this.configPropertyService = configPropertyService;
    }
}
