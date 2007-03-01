package gov.nih.nci.security.acegi.csm.authorization;

import org.aspectj.lang.Signature;

public interface SignatureToPrivilegeMapping {
	
	boolean matches(Signature sig);
	
	String getPrivilege();

}
