package gov.nih.nci.security.acegi.csm.authorization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.Signature;

public class RegexSignatureToPrivilegeMapping implements
		SignatureToPrivilegeMapping {
	
	private static final Log logger = LogFactory.getLog(RegexSignatureToPrivilegeMapping.class);
	
	
	private String privilege;
	private String pattern;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getPrivilege() {
		return this.privilege;
	}

	public boolean matches(Signature sig) {
		
		String sigStr = sig.getDeclaringTypeName() + "." + sig.getName();
		logger.debug("Comparing " + getPattern() + " to " + sigStr);
		return sigStr.matches(getPattern());
	}

}
