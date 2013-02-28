/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;


/**
 * @author: Ion C. Olaru
 * @author Biju Joseph
 */
public class SecurityObjectIdFieldDecorator implements FieldDecorator {

    private String objectId;
    private String updatePrivilege;
    private String readPrivilege;

    public SecurityObjectIdFieldDecorator(Class objectIdClass) {
        this(objectIdClass, "READ", "UPDATE");
    }

    public SecurityObjectIdFieldDecorator(Class klass, String readPrivilege, String updatePrivilege){
        objectId = klass.getName();
        this.readPrivilege = readPrivilege;
        this.updatePrivilege = updatePrivilege;
    }

  /**
   *   Decorate the field appropriately
   * */
    public void decorate(InputField f) {
       if(f.getPrivilegeToModify() == null) f.setPrivilegeToModify(objectId + ":" + updatePrivilege);
       if(f.getPrivilegeToRead() == null) f.setPrivilegeToRead(objectId + ":" + readPrivilege);
    }
}
