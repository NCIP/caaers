package gov.nih.nci.cabig.caaers.utils;

import java.util.UUID;

/**
 * A local implementation of GridIdentifierCreator, suitable for use in environments where there is
 * no external identifier service. The each identifier returned is the string version of a call to
 * {UUID#randomUUID}.
 * 
 * @see {UUID}
 * @author Rhett Sutphin
 */
public class LocalGridIdentifierCreator implements GridIdentifierCreator {
    public String getGridIdentifier(String id) {
        return UUID.randomUUID().toString();
    }
}
