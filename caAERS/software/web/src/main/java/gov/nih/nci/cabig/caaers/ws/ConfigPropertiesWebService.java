/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.ConfigProperties;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="ConfigPropertiesWebServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
public interface ConfigPropertiesWebService {
    public gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse createOrUpdateConfigProperties(@WebParam(name="ConfigProperties",
            targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") ConfigProperties configProperties);
}
