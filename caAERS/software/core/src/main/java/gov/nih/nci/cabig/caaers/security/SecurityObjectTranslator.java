package gov.nih.nci.cabig.caaers.security;

/**
 * @author: Ion C. Olaru
 * Date: Jun 9, 2010
 * Time: 4:44:57 PM
 */
public class SecurityObjectTranslator {

    public String fromCaAERSToCSM(String caAERSName) {
        if (caAERSName.equals("gov.nih.nci.cabig.caaers.domain.Organization")) return "HealthcareSite";
        if (caAERSName.equals("gov.nih.nci.cabig.caaers.domain.LocalOrganization")) return "HealthcareSite";
        if (caAERSName.equals("gov.nih.nci.cabig.caaers.domain.RemoteOrganization")) return "HealthcareSite";
        if (caAERSName.equals("gov.nih.nci.cabig.caaers.domain.Study")) return "Study";
        if (caAERSName.equals("gov.nih.nci.cabig.caaers.domain.LocalStudy")) return "Study";
        if (caAERSName.equals("gov.nih.nci.cabig.caaers.domain.RemoteStudy")) return "Study";
        return caAERSName;
    }

    public String fromCSMToCaAERS(String csmName) {
        if (csmName.equals("HealthcareSite")) return "gov.nih.nci.cabig.caaers.domain.Organization";
        if (csmName.equals("Study")) return "gov.nih.nci.cabig.caaers.domain.Study";
        return csmName;
    }

}
