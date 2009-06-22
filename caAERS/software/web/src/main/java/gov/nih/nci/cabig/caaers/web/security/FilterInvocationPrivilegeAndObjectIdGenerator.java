/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import org.acegisecurity.intercept.web.FilterInvocation;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class FilterInvocationPrivilegeAndObjectIdGenerator extends
                RegexPrivilegeAndObjectIdGenerator {

    protected boolean supports(Object object) {
        return object instanceof FilterInvocation;
    }

    protected String getKeyValue(Object object) {
        return ((FilterInvocation) object).getRequestUrl();
    }

}
