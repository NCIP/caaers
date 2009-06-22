package gov.nih.nci.cabig.caaers.domain;

/**
 * This purely fabricated class is used for XML import, and decorates a {@link StudyCoordinatingCenter}.
 *
 * @author Krikor
 * @author Biju Joseph
 */
public class CoordinatingCenter  {

	private StudyCoordinatingCenter studyCoordinatingCenter;
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;
    
    public CoordinatingCenter() {
	}
    
    public CoordinatingCenter(StudyCoordinatingCenter coordinatingCenter){
    	this.studyCoordinatingCenter = coordinatingCenter;
    }
    
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

    public void setStudyCoordinatingCenter(StudyCoordinatingCenter studyCoordinatingCenter) {
        this.studyCoordinatingCenter = studyCoordinatingCenter;
    }

}
