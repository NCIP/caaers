package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.asael.ASAELType;


/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
public interface ASAELService {

    public CaaersServiceResponse createOrUpdateASAEL(ASAELType inputMessage);
}
