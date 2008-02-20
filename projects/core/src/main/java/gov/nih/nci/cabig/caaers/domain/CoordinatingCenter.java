package gov.nih.nci.cabig.caaers.domain;

/**
 * This class represents the CoordinatingCenter domain object associated with the Adverse event report.
 * @author
 *
 */
public class CoordinatingCenter {
	
	
	private OrganizationAssignedIdentifier organizationAssignedIdentifier;
	private StudyCoordinatingCenter studyCoordinatingCenter;
	
	public OrganizationAssignedIdentifier getOrganizationAssignedIdentifier() {
		return organizationAssignedIdentifier;
	}
	public void setOrganizationAssignedIdentifier(
			OrganizationAssignedIdentifier organizationAssignedIdentifier) {
		this.organizationAssignedIdentifier = organizationAssignedIdentifier;
	}
	public StudyCoordinatingCenter getStudyCoordinatingCenter() {
		return studyCoordinatingCenter;
	}
	public void setStudyCoordinatingCenter(
			StudyCoordinatingCenter studyCoordinatingCenter) {
		this.studyCoordinatingCenter = studyCoordinatingCenter;
	}
	
	

}
