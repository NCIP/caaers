package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.security.acegi.csm.authorization.CSMGroupAuthorizationCheck;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;

import org.acegisecurity.Authentication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by IntelliJ IDEA. User: Biju Joseph Date: Sep 21, 2007 Time: 10:34:53 AM To change this
 * template use File | Settings | File Templates.
 */
public class StudySiteSiteSecurityCSMGroupAuthorizationCheckProvider implements
                DomainObjectSiteSecurityAuthorizationCheckProvider {

    private CSMObjectIdGenerator siteObjectIdGenerator;

    private CSMGroupAuthorizationCheck csmGroupAuthorizationCheck;

    private Logger log = Logger
                    .getLogger(StudySiteSiteSecurityCSMGroupAuthorizationCheckProvider.class);

    public boolean checkAuthorization(Authentication authentication, String permission,
                    Object domainObject) {
        boolean hasPermission = false;
        log.debug("Invoking checkPermission on StudySiteSiteSecurityCSMGroupAuthorizationCheckProvider");

        if (domainObject instanceof Study) {
            Study study = (Study) domainObject;
            // if no sites then make it globally accessible
            if (study.getStudySites().size() > 0) {
                for (StudySite site : study.getStudySites()) {
                    Organization organization = site.getOrganization();
                    log.debug("### Checking permission for user on site:" + organization.getNciInstituteCode());
                    hasPermission = csmGroupAuthorizationCheck.checkAuthorizationForObjectId( authentication, permission, siteObjectIdGenerator.generateId(organization));
                    // only needs permission on one of the sites
                    if (hasPermission) break;
                }
            } else {
                log.debug("Unsupported object sent to StudySiteSiteSecurityCSMGroupAuthorizationCheckProvider. Expecting Study object found "
                                                + domainObject.getClass().getName());
                hasPermission = true;
            }

        }
        return hasPermission;
    }

    @Required
    public void setSiteObjectIdGenerator(CSMObjectIdGenerator siteObjectIdGenerator) {
        this.siteObjectIdGenerator = siteObjectIdGenerator;
    }

    @Required
    public void setCsmGroupAuthorizationCheck(CSMGroupAuthorizationCheck csmGroupAuthorizationCheck) {
        this.csmGroupAuthorizationCheck = csmGroupAuthorizationCheck;
    }
}
