package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.logging.api.util.StringUtils;

public class DeviceQuery extends AbstractQuery {

    public final static String DEVICE_TYPE = "device_type";

    public DeviceQuery() {
        super("SELECT d FROM Device d");
    }

    public void filterByType(String type) {
        if(type != null && !StringUtils.isBlank(type)) {
            andWhere("lower(d.type) = :" + DEVICE_TYPE);
            setParameter(DEVICE_TYPE, type.toLowerCase());
        }
    }
}
