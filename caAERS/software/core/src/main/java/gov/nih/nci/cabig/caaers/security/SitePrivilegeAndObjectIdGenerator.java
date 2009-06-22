/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.acegi.csm.authorization.CSMPrivilegeGenerator;

/**
 * @author <a href="mailto:saurabh.agrawal@semanticbits.com">Saurabh Agrawal</a>
 * 
 */
public final class SitePrivilegeAndObjectIdGenerator implements CSMPrivilegeGenerator,
                CSMObjectIdGenerator {

    private String accessPrivilege;

    // default value
    private String pathSeperator = ".";

    /**
     * Returns a CSM privilege name, given an object.
     * 
     * @param object
     *                from which to determine privilege
     * @return CSM privilege name
     */
    public String generatePrivilege(Object object) {
        assertSupports(object);
        return object.getClass().getName() + pathSeperator + accessPrivilege;
    }

    /**
     * Returns a CSM objectId, given an object.
     * 
     * @param object
     *                from which ID should be generated
     * @return CSM objectId
     */
    public String generateId(Object object) {
        assertSupports(object);
        //Organization site = (Organization) object;
        //return Organization.class.getName() + pathSeperator + site.getNciInstituteCode();
        
        //nciInstitudeCode is passed instead of full object
        return "gov.nih.nci.cabig.caaers.domain.Organization" + pathSeperator + object.toString();
    }

    public String getPathSeperator() {
        return pathSeperator;
    }

    public void setPathSeperator(String pathSeperator) {
        this.pathSeperator = pathSeperator;
    }

    public String getAccessPrivilege() {
        return accessPrivilege;
    }

    public void setAccessPrivilege(String accessPrivilege) {
        this.accessPrivilege = accessPrivilege;
    }

    private boolean supports(Object object) {
        return object instanceof java.lang.String;
    }

    private void assertSupports(Object object) {
        if (object == null) {
            throw new NullPointerException("Object is null");
        }
        if (!supports(object)) {
            throw new IllegalArgumentException("unsupported object " + object.getClass().getName());
        }
    }

}
