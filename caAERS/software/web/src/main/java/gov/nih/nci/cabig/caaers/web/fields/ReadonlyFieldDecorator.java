package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

/**
 * @author: Ion C. Olaru
 */
public class ReadonlyFieldDecorator implements FieldDecorator {

    private CaaersSecurityFacade caaersSecurityFacade;
    
    /*
    *   Decorate the field appropriately filling the readonly attribute  
    * */
    public void decorate(InputField f) {
        if (!f.isReadonly()) {
            if (caaersSecurityFacade.checkAuthorization(SecurityUtils.getAuthentication(), f.getSecurityObjectId(), "UPDATE")); 
        }
    }

    public CaaersSecurityFacade getCaaersSecurityFacade() {
        return caaersSecurityFacade;
    }

    public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
        this.caaersSecurityFacade = caaersSecurityFacade;
    }
}