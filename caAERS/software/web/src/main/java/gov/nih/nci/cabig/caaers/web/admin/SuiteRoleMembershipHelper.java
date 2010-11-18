package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;

public class SuiteRoleMembershipHelper {
	
	private SuiteRole suiteRole;
	private boolean checked;
	
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public SuiteRole getSuiteRole() {
		return suiteRole;
	}
	public void setSuiteRole(SuiteRole suiteRole) {
		this.suiteRole = suiteRole;
	}
}
