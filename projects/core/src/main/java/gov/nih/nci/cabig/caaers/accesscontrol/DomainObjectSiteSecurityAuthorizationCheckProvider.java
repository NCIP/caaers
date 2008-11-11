package gov.nih.nci.cabig.caaers.accesscontrol;

import org.acegisecurity.Authentication;

/**
 * Created by IntelliJ IDEA. User: admin Date: Sep 21, 2007 Time: 10:32:26 AM To change this
 * template use File | Settings | File Templates.
 */
public interface DomainObjectSiteSecurityAuthorizationCheckProvider {
    boolean checkAuthorization(Authentication authentication, String permission,
                    Object domainObject);
}
