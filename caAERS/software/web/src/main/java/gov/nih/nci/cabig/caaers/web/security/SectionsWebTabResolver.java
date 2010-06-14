package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.ctms.web.chrome.Section;

import java.util.Map;

/**
 * @author Ion C. Olaru
 *
 */
public class SectionsWebTabResolver implements WebTabResolver {

    protected Map<String, String> objectPrivilegeMap;

    public String resolve(Object o) {
        return objectPrivilegeMap.get(((Section)o).getMainUrl());    
    }

    public void setObjectPrivilegeMap(Map<String, String> objectPrivilegeMap) {
        this.objectPrivilegeMap = objectPrivilegeMap;
    }
}