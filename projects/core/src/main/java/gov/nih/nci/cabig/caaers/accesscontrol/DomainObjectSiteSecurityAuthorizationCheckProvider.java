package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.acegisecurity.Authentication;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: Sep 21, 2007
 * Time: 10:32:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DomainObjectSiteSecurityAuthorizationCheckProvider {
    boolean checkAuthorization(Authentication authentication, String permission, AbstractMutableDomainObject domainObject);
}
