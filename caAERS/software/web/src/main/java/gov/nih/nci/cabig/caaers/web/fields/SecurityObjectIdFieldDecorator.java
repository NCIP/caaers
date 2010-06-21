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
       f.setPrivilegeToModify(objectId + ":" + updatePrivilege);
       f.setPrivilegeToRead(objectId + ":" + readPrivilege);
    }
}