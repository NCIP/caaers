/**
 *
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.security.acegi.csm.authorization.AbstractPrivilegeAndObjectIdGenerator;

/**
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 */
public class TaskPrivilegeAndObjectIdGenerator extends AbstractPrivilegeAndObjectIdGenerator {

    protected String getKeyValue(Object object) {
        return ((Task) object).getUrl();
    }

    protected boolean supports(Object object) {
        return object instanceof Task;
    }

}
