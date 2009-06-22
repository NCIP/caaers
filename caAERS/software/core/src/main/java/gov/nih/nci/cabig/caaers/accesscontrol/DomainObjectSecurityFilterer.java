package gov.nih.nci.cabig.caaers.accesscontrol;

import org.acegisecurity.Authentication;

public interface DomainObjectSecurityFilterer {
	Object filter(Authentication authentication, String permission,
			Object returnObject);
}
