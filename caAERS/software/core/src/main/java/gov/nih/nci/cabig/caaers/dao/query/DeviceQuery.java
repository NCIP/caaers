package gov.nih.nci.cabig.caaers.dao.query;

import org.apache.commons.lang.StringUtils;

public class DeviceQuery extends AbstractQuery {

    public final static String COMMON_NAME = "common_name";
    public final static String BRAND_NAME = "brand_name";
    public final static String DEVICE_TYPE = "device_type";

    public DeviceQuery() {
        super("SELECT d FROM Device d");
    }

    public void filterByType(String type) {
        if (StringUtils.isBlank(type)) {
            andWhere("d.type IS NULL OR d.type = ''");
        } else {
            andWhere("lower(d.type) = :" + DEVICE_TYPE);
            setParameter(DEVICE_TYPE, type.toLowerCase());
        }
    }

    public void filterByCtepDbIdentifier(String ctepDbIdentifier){
       if(StringUtils.isEmpty(ctepDbIdentifier)){
           andWhere("d.ctepDbIdentifier is NULL OR d.ctepDbIdentifier = ''");
       } else {
           andWhere("d.ctepDbIdentifier = :dbId");
           setParameter("dbId", ctepDbIdentifier);
       }
    }
    public void filterByCommonName(String commonName) {
        if (StringUtils.isBlank(commonName)) {
            andWhere("d.commonName IS NULL OR d.commonName = ''");
        } else {
            andWhere("lower(d.commonName) = :" + COMMON_NAME);
            setParameter(COMMON_NAME, commonName.toLowerCase());
        }
    }

    public void filterByMatchText(String text) {
        if (!StringUtils.isBlank(text)) {
            andWhere("lower(d.commonName) LIKE :" + COMMON_NAME + " OR lower(d.brandName) LIKE :" + BRAND_NAME + " OR lower(d.type) LIKE :" + DEVICE_TYPE);
            String _t = "%" + text.toLowerCase() + "%";
            setParameter(COMMON_NAME, _t);
            setParameter(BRAND_NAME, _t);
            setParameter(DEVICE_TYPE, _t);
        }
    }

    public void filterByBrandName(String brandName) {
        if (StringUtils.isBlank(brandName)) {
            andWhere("d.brandName IS NULL OR d.brandName = ''");
        } else {
            andWhere("lower(d.brandName) = :" + BRAND_NAME);
            setParameter(BRAND_NAME, brandName.toLowerCase());
        }
    }
}
