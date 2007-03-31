/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.acegi.csm.authorization.CSMPrivilegeGenerator;

import java.util.Map;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public abstract class AbstractPrivilegeAndObjectIdGenerator implements CSMPrivilegeGenerator, CSMObjectIdGenerator {
	private Map<String, String> objectPrivilegeMap;

	private String separator = ":";

	public Map<String, String> getObjectPrivilegeMap() {
		return objectPrivilegeMap;
	}

	public void setObjectPrivilegeMap(Map<String, String> objectPrivilegeMap) {
		this.objectPrivilegeMap = objectPrivilegeMap;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.acegi.csm.authorization.CSMPrivilegeGenerator#generatePrivilege(java.lang.Object)
	 */
	public String generatePrivilege(Object object) {
		String privilege = null;
		String[] objectPrivilege = getObjectPrivilege(object);
		if (objectPrivilege != null) {
			privilege = objectPrivilege[1];
		}
		return privilege;
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator#generateId(java.lang.Object)
	 */
	public String generateId(Object object) {
		String objectId = null;
		String[] objectPrivilege = getObjectPrivilege(object);
		if (objectPrivilege != null) {
			objectId = objectPrivilege[0];
		}
		return objectId;
	}
	
	protected String[] getObjectPrivilege(Object object){
		String[] objectPrivilege = null;
		if (object != null) {
			if (!supports(object)) {
				throw new IllegalArgumentException("unsupported object "
						+ object.getClass().getName());
			}
			String key = getKeyValue(object);
			objectPrivilege = getObjectPrivilegeArray(key);
		}
		return objectPrivilege;
	}
	
	protected String[] getObjectPrivilegeArray(String key){
		String[] objectPrivilegeArray = null;
		String objectPrivilege = getObjectPrivilegeString(key);
		if (objectPrivilege != null) {
			
			objectPrivilegeArray = objectPrivilege
					.split(getSeparator());
			if (objectPrivilegeArray.length != 2) {
				throw new IllegalStateException("\"" + objectPrivilege
						+ "\".split(\"" + getSeparator()
						+ "\") yields String[] of size "
						+ objectPrivilegeArray.length
						+ ", should be of size 2");
			}

		}
		return objectPrivilegeArray;
	}
	
	protected String getObjectPrivilegeString(String key){
		return getObjectPrivilegeMap().get(key);
	}
	
	protected abstract String getKeyValue(Object object);
	
	protected abstract boolean supports(Object object);
}
