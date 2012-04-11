package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.integration.schema.common.DeviceType;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -11:49 AM
 */
public class DeviceConverter {

    public static Device convert(DeviceType d) {
        Device o = new Device();
        o.setBrandName(d.getBrandName());
        o.setCommonName(d.getCommonName());
        o.setType(d.getType());
        return o;
    }

    public static DeviceType convert(Device d) {
        DeviceType o = new DeviceType();
        o.setBrandName(d.getBrandName());
        o.setCommonName(d.getCommonName());
        o.setType(d.getType());
        return o;
    }

}
