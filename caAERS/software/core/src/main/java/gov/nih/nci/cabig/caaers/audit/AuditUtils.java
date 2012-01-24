package gov.nih.nci.cabig.caaers.audit;


import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.util.Date;

/**
 * Will create an AuditInfo object
 */
public class AuditUtils {
    
    public static DataAuditInfo  generateDataAuditInfo(String username, String ipaddress, String fromURI){
        return new DataAuditInfo(username, ipaddress, new Date(), fromURI);
    }
    public static DataAuditInfo generateDataAuditInfo(){
        return generateDataAuditInfo("SYSTEM", "127.0.0.1", null);
    }
    public static DataAuditInfo generateDataAuditInfo(String userName, String ipaddress){
        return generateDataAuditInfo(userName, ipaddress, null);
    }
    public static DataAuditInfo generateDataAuditInfo(String userName){
        return generateDataAuditInfo(userName, "127.0.0.1");
    }
}
