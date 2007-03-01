package gov.nih.nci.security.acegi.csm.authorization;

public class PrivilegeAndObject {
	
	private String privilege;
	private Object object;
	
	public PrivilegeAndObject(){}
	
	public PrivilegeAndObject(String privilege, Object object){
		this.privilege = privilege;
		this.object = object;
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
