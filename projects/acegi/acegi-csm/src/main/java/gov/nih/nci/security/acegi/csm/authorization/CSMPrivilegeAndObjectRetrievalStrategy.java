package gov.nih.nci.security.acegi.csm.authorization;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

public class CSMPrivilegeAndObjectRetrievalStrategy implements
		PrivilegeAndObjectRetrievalStrategy {

	private List<SignatureToPrivilegeMapping> mappings = new ArrayList<SignatureToPrivilegeMapping>();

	private String processDomainObjectClass;

	public String getProcessDomainObjectClass() {
		return processDomainObjectClass;
	}

	public void setProcessDomainObjectClass(String processDomainObjectClass) {
		this.processDomainObjectClass = processDomainObjectClass;
	}

	public PrivilegeAndObject retrieve(ProceedingJoinPoint pjp) {
		PrivilegeAndObject pao = null;
		for (SignatureToPrivilegeMapping mapping : getMappings()) {
			if (mapping.matches(pjp.getSignature())) {
				Object[] args = pjp.getArgs();
				Object object = getDomainObject(args);

				String privilege = mapping.getPrivilege();
				pao = new PrivilegeAndObject(privilege, object);
				break;

			}
		}
		return pao;
	}

	protected Object getDomainObject(Object[] args) {
		Object obj = null;
		Class klass = null;
		try {
			klass = Class.forName(getProcessDomainObjectClass());
		} catch (Exception ex) {
			throw new RuntimeException("Error loading "
					+ getProcessDomainObjectClass(), ex);
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null && klass.isAssignableFrom(args[i].getClass())) {
				obj = args[i];
				break;
			}
		}
		return obj;
	}

	public List<SignatureToPrivilegeMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<SignatureToPrivilegeMapping> mappings) {
		this.mappings = mappings;
	}

}
