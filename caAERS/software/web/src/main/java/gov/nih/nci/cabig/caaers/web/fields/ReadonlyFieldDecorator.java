package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

/**
 * @author: Ion C. Olaru
 */
public class ReadonlyFieldDecorator implements FieldDecorator {

    private CaaersSecurityFacade caaersSecurityFacade;

    /**
     * @see TabWithFields  
     *
     * */
    public ReadonlyFieldDecorator() {
        this.caaersSecurityFacade = CaaersSecurityFacadeImpl.getInstance();
    }

    /*
   *   Decorate the field appropriately filling the readonly attribute
   * */
    public void decorate(InputField f) {
        // if (caaersSecurityFacade == null) return;
        if (!f.isReadonly()) {
            if (caaersSecurityFacade.checkAuthorization(SecurityUtils.getAuthentication(), f.getSecurityObjectId(), "UPDATE")); 
        }
    }

}