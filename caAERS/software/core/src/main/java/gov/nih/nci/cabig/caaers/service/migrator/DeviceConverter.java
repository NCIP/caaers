/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -11:49 AM
 */
public class DeviceConverter {

    public  Device convert(DeviceType deviceType) {
        Device device = new Device();
        device.setCtepDbIdentifier(deviceType.getCtepDbIdentifier());
        device.setCommonName(deviceType.getCommonName());
        device.setBrandName(deviceType.getBrandName());
        device.setType(deviceType.getType());
        device.setRetiredIndicator(deviceType.getStatus().equals(ActiveInactiveStatusType.INACTIVE));
        return device;
    }

    public static DeviceType convert(Device device) {
        DeviceType deviceType = new DeviceType();
        deviceType.setBrandName(device.getBrandName());
        deviceType.setCommonName(device.getCommonName());
        deviceType.setType(device.getType());
        deviceType.setCtepDbIdentifier(device.getCtepDbIdentifier());
        deviceType.setStatus(device.isRetired() ? ActiveInactiveStatusType.INACTIVE : ActiveInactiveStatusType.ACTIVE);
        return deviceType;
    }

}
