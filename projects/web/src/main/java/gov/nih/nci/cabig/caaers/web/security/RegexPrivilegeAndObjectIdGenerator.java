/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.security.acegi.csm.authorization.AbstractPrivilegeAndObjectIdGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class RegexPrivilegeAndObjectIdGenerator extends AbstractPrivilegeAndObjectIdGenerator {

    private static final Log logger = LogFactory.getLog(RegexPrivilegeAndObjectIdGenerator.class);

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.web.security.AbstractPrivilegeAndObjectIdGenerator#getKeyValue(java.lang.Object)
     */
    @Override
    protected String getKeyValue(Object object) {
        return (String) object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.web.security.AbstractPrivilegeAndObjectIdGenerator#supports(java.lang.Object)
     */
    @Override
    protected boolean supports(Object object) {
        return object instanceof String;
    }

    protected String getObjectPrivilegeString(String key) {
        String objPrivStr = null;
        for (String pattern : getObjectPrivilegeMap().keySet()) {
            if (key.matches(pattern)) {
                objPrivStr = getObjectPrivilegeMap().get(pattern);
                break;
            }
        }

        logger.debug("############### Returning " + objPrivStr + " #################");

        return objPrivStr;
    }

    public static void main(String[] args) {
        System.out.println("/pages/ae/list".matches("/pages/ae/list.*"));
    }

}
