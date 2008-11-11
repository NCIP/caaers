package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.security.acegi.csm.authorization.CSMGroupAuthorizationCheck;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;

import org.acegisecurity.Authentication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * Srini Akkala
 */
public class ParticipantSecurityCSMGroupAuthorizationCheckProvider implements
                DomainObjectSiteSecurityAuthorizationCheckProvider {

    private CSMObjectIdGenerator siteObjectIdGenerator;

    private CSMGroupAuthorizationCheck csmGroupAuthorizationCheck;

    private Logger log = Logger
                    .getLogger(ParticipantSecurityCSMGroupAuthorizationCheckProvider.class);
    
    public boolean checkAuthorization(Authentication authentication, String permission,
                    Object domainObject) {
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
