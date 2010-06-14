package gov.nih.nci.cabig.caaers.web.security;

/**
 * @author Ion C. Olaru
 * 
 */
public interface WebTabResolver {

    /*
    *   Resolves the object to a String containing the ObjectID and Privilege
    *   @param o - the object to be resolved from the MAP
    *   return String    
    * */
    public String resolve(Object o);
}
