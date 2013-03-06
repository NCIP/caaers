/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for UserCommand.
 * @author Monish
 *
 */
public class SuiteRoleMembershipHelper {
	
	private SuiteRole suiteRole;
	private boolean checked;
	private boolean allSiteAccess;
	private boolean allStudyAccess;
	private String ccIdentifier;
	private List<String> sites = new ArrayList<String>();
	private List<String> studies = new ArrayList<String>();
	private String nciCode;
	private String selectedSiteForDisplay;
	private String selectedStudyForDisplay;
	private String studyId;
	
    public boolean getStudyScoped() {
        return suiteRole.isStudyScoped();
    }

    public boolean getSiteScoped() {
        return suiteRole.isSiteScoped();
    }

    public boolean getScoped() {
        return suiteRole.isScoped();
    }
	
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
	public boolean getAllSiteAccess() {
		return allSiteAccess;
	}
	public void setAllSiteAccess(boolean allSiteAccess) {
		this.allSiteAccess = allSiteAccess;
	}
	public List<String> getSites() {
		return sites;
	}
	public void setSites(List<String> sites) {
		this.sites = sites;
	}

	public String getNciCode() {
		return nciCode;
	}

	public void setNciCode(String nciCode) {
		this.nciCode = nciCode;
	}

	public boolean getAllStudyAccess() {
		return allStudyAccess;
	}

	public void setAllStudyAccess(boolean allStudyAccess) {
		this.allStudyAccess = allStudyAccess;
	}

	public List<String> getStudies() {
		return studies;
	}

	public void setStudies(List<String> studies) {
		this.studies = studies;
	}

	public String getCcIdentifier() {
		return ccIdentifier;
	}

	public void setCcIdentifier(String ccIdentifier) {
		this.ccIdentifier = ccIdentifier;
	}

	public String getSelectedSiteForDisplay() {
		return selectedSiteForDisplay;
	}

	public void setSelectedSiteForDisplay(String selectedSiteForDisplay) {
		this.selectedSiteForDisplay = selectedSiteForDisplay;
	}

	public String getSelectedStudyForDisplay() {
		return selectedStudyForDisplay;
	}

	public void setSelectedStudyForDisplay(String selectedStudyForDisplay) {
		this.selectedStudyForDisplay = selectedStudyForDisplay;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
}
