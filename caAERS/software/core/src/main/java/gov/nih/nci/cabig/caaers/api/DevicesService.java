package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.devices.DevicesType;




/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
public interface DevicesService {
    public CaaersServiceResponse createOrUpdateDevices(DevicesType inputMessage);
}
