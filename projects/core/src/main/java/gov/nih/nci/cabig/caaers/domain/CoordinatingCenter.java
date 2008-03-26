package gov.nih.nci.cabig.caaers.domain;

/**
 * This class represents the CoordinatingCenter domain object associated with the Adverse event
 * report.
 *
 * @author
 */
public class CoordinatingCenter extends AbstractSponsor {


    private StudyCoordinatingCenter studyCoordinatingCenter;


    public StudyCoordinatingCenter getStudyCoordinatingCenter() {
        return studyCoordinatingCenter;
    }

    public void setStudyCoordinatingCenter(StudyCoordinatingCenter studyCoordinatingCenter) {
        this.studyCoordinatingCenter = studyCoordinatingCenter;
    }

    public StudyOrganization getStudyOrganization() {
        return studyCoordinatingCenter;
    }
}
