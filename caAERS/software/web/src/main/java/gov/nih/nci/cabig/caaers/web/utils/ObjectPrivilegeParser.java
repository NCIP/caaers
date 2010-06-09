package gov.nih.nci.cabig.caaers.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class ObjectPrivilegeParser {
    protected final Log log = LogFactory.getLog(ObjectPrivilegeParser.class);

    private final static String delimiter = "([&|\\||(|)\\s])+"; // "&|() "
    String[] lines;
    String[][] domainObjectsPrivileges;

    public ObjectPrivilegeParser(String objectPrivelegeString) {
        lines = objectPrivelegeString.split(delimiter);

        domainObjectsPrivileges = new String[lines.length][2];
        for (byte i=0; i<domainObjectsPrivileges.length; i++) {
            String[] s = lines[i].split(":");
            domainObjectsPrivileges[i][0] = s[0];
            domainObjectsPrivileges[i][1] = s[1];
        }
    }

    public String[][] getDomainObjectPrivileges() {
        return domainObjectsPrivileges;
    }
}