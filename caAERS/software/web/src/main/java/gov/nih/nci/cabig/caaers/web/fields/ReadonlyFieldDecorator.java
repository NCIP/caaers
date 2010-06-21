package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

/**
 * @author: Ion C. Olaru
 * @author Biju Joseph
 */
public class ReadonlyFieldDecorator implements FieldDecorator {

  /*
   *   Decorate the field appropriately filling the readonly attribute
   * */
    public void decorate(InputField f) {
       CaaersSecurityFacade securityFacade = CaaersSecurityFacadeImpl.getInstance();
       if(securityFacade != null){
           if(f.getPrivilegeToModify() != null && f.isModifiable()){
               f.setModifiable(securityFacade.checkAuthorization(SecurityUtils.getAuthentication(), f.getPrivilegeToModify()));
           }

           if(f.getPrivilegeToRead() != null && f.isReadable()){
               f.setReadable(securityFacade.checkAuthorization(SecurityUtils.getAuthentication(), f.getPrivilegeToRead()));
           }
       }
      
    }

}