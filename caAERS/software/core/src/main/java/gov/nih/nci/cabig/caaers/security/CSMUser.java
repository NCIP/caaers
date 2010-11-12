package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.User;

public class CSMUser extends User {

	@Override
	public boolean isActive() {
		return true;
	}
}