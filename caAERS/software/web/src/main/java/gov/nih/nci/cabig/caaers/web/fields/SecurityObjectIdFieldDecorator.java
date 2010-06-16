package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;

/**
 * @author: Ion C. Olaru
 */
public class SecurityObjectIdFieldDecorator implements FieldDecorator {

    private Class objectIdClass; 

    public SecurityObjectIdFieldDecorator(Class objectIdClass) {
        this.objectIdClass = objectIdClass;
    }

  /**
   *   Decorate the field appropriately
   * */
    public void decorate(InputField f) {
        if (f.getSecurityObjectId() == null) {
            f.setSecurityObjectId(objectIdClass.getName());
        }
    }
}