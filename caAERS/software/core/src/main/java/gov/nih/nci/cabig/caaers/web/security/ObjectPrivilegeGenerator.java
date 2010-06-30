package gov.nih.nci.cabig.caaers.web.security;

import java.util.Map;

/**
 * @author Ion C. Olaru
 * @author Biju Joseph (refactoring)
 * 
 */
public interface ObjectPrivilegeGenerator {

    /*
    *   Resolves the object to a String containing the ObjectID and Privilege
    *   @param o - the object to be resolved from the MAP
    *   return String    
    * */
    public String resolve(Object o);

    /**
     * The mapping of object-reference to the ObjectPrivilege 
     * @param map
     */
    public void setObjectPrivilegeMap(Map<String, String> map);
}