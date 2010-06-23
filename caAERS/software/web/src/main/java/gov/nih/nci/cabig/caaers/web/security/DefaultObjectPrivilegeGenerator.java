package gov.nih.nci.cabig.caaers.web.security;

import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class DefaultObjectPrivilegeGenerator implements ObjectPrivilegeGenerator {

    protected Map<String, String> objectPrivilegeMap;

    public String resolve(Object o) {
        return objectPrivilegeMap.get(String.valueOf(o));
    }

    /**
     * The mapping of object-reference to the ObjectPrivilege
     *
     * @param map
     */
    public void setObjectPrivilegeMap(Map<String, String> map) {
        this.objectPrivilegeMap = map;
    }
}
