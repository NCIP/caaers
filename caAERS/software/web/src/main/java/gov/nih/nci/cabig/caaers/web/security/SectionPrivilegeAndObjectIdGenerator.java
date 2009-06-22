/**
 *
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.ctms.web.chrome.Section;
import gov.nih.nci.security.acegi.csm.authorization.AbstractPrivilegeAndObjectIdGenerator;

/**
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class SectionPrivilegeAndObjectIdGenerator extends AbstractPrivilegeAndObjectIdGenerator {

    protected String getKeyValue(Object object) {
        return ((Section) object).getMainUrl();
    }

    protected boolean supports(Object object) {
        return object instanceof Section;
    }

}
