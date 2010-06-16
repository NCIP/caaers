package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

/**
 * @author: Ion C. Olaru
 */
public class ReadonlyFieldDecorator implements FieldDecorator {

    private CaaersSecurityFacade caaersSecurityFacade;

    /**
     * @param caaersSecurityFacade - this parameter is passed from the concrete Tab using the decorator
     * since the Tab has it injected as of TabWithFields
     * @see TabWithFields  
     *
     * */
    public ReadonlyFieldDecorator(CaaersSecurityFacade caaersSecurityFacade) {
        this.caaersSecurityFacade = caaersSecurityFacade;
    }

    /*
   *   Decorate the field appropriately filling the readonly attribute
   * */
    public void decorate(InputField f) {
        if (!f.isReadonly()) {
            if (caaersSecurityFacade.checkAuthorization(SecurityUtils.getAuthentication(), f.getSecurityObjectId(), "UPDATE")); 
        }
    }

}