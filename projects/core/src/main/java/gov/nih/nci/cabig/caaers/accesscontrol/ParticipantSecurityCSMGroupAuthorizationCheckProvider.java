package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.security.acegi.csm.authorization.CSMGroupAuthorizationCheck;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;

import org.acegisecurity.Authentication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by IntelliJ IDEA. User: Biju Joseph Date: Sep 21, 2007 Time: 10:34:53 AM To change this
 * template use File | Settings | File Templates.
 */
public class ParticipantSecurityCSMGroupAuthorizationCheckProvider implements
                DomainObjectSiteSecurityAuthorizationCheckProvider {

    private CSMObjectIdGenerator siteObjectIdGenerator;

    private CSMGroupAuthorizationCheck csmGroupAuthorizationCheck;

    private Logger log = Logger
                    .getLogger(ParticipantSecurityCSMGroupAuthorizationCheckProvider.class);
    
    public boolean checkAuthorization(Authentication authentication, String permission,
                    AbstractMutableDomainObject domainObject) {
        boolean hasPermission = false;
        log.debug("Invoking checkPermission on ParticipantSecurityCSMGroupAuthorizationCheckProvider");
        
        // TO BE IMPLEMENTED
        


        hasPermission = true;
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
