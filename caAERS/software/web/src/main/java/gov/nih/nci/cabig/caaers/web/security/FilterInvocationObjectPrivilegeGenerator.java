/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import org.acegisecurity.intercept.web.FilterInvocation;

import java.util.Map;

/**
 * @author <a href="mailto:ion.olaru@semanticbits.com">Ion C. Olaru</a>
 *
 */
public class FilterInvocationObjectPrivilegeGenerator implements ObjectPrivilegeGenerator {

    protected Map<String, String> objectPrivilegeMap;

    /**
     * The mapping of object-reference to the ObjectPrivilege
     * @param map
     */
    public void setObjectPrivilegeMap(Map<String, String> map) {
        this.objectPrivilegeMap = map;
    }

    public String resolve(Object o) {
        String s = ((FilterInvocation)o).getRequestUrl();
        return objectPrivilegeMap.get(s);
    }

}
