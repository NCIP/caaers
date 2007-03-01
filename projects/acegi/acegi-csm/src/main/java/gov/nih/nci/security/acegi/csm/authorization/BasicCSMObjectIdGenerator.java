/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import java.lang.reflect.Method;

/**
 * @author joshua
 * 
 */
public class BasicCSMObjectIdGenerator implements CSMObjectIdGenerator {

	private String idMethodName;

	private String separator = "";

	private boolean classNameFirst = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see exp.authorization.CSMObjectIdGenerator#generateId(java.lang.Object)
	 */
	public String generateId(Object object) {
		String objectId = null;
		if (object != null) {
			Class klass = object.getClass();
			String className = klass.getName();
			Object id = null;
			String methodName = getIdMethodName();
			if (methodName != null && methodName.length() > 0) {
				try {
					Method getId = klass.getMethod(methodName,
							new Class[0]);
					id = getId.invoke(object, new Object[0]);
				} catch (Exception ex) {
					throw new RuntimeException("Error getting object ID: "
							+ ex.getMessage(), ex);
				}
			}
			String sep = getSeparator();
			if(id == null){
				id = "";
				sep = "";
			}
			if (isClassNameFirst()) {
				objectId = klass.getName() + sep + id;
			} else {
				objectId = id + sep + klass.getName();
			}
		}
		return objectId;
	}

	public boolean isClassNameFirst() {
		return classNameFirst;
	}

	public void setClassNameFirst(boolean classNameFirst) {
		this.classNameFirst = classNameFirst;
	}

	public String getIdMethodName() {
		return idMethodName;
	}

	public void setIdMethodName(String idMethodName) {
		this.idMethodName = idMethodName;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
