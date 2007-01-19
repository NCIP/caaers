package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import gov.nih.nci.cabig.caaers.domain.AbstractDomainObject;
import gov.nih.nci.cabig.caaers.domain.StudySite;


/**
 * @author Krikor Krumlian
 */

@Entity
@Table (name = "participant_assignments")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_participant_assignments_id")
    }
)
public class StudyParticipantAssignment extends AbstractDomainObject {
    private Participant participant;
    private StudySite studySite;
    private Date dateOfEnrollment;
    private List<AdverseEventReport> aeReports;

    /*
     * Constructor
     */
    public StudyParticipantAssignment(Participant participant,
			StudySite studySite) {
		this.participant = participant;
		this.studySite = studySite;
		this.dateOfEnrollment = new Date();
	}
    
    public StudyParticipantAssignment()
    {
    	
    }
    // //// BEAN PROPERTIES

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_site_id")
    public StudySite getStudySite() {
        return studySite;
    }
    
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    public Participant getParticipant() {
        return participant;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    @Column(name = "date_of_enrollment")
    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    @OneToMany(mappedBy = "assignment")
    public List<AdverseEventReport> getAeReports() {
        return aeReports;
    }

    public void setAeReports(List<AdverseEventReport> aeReports) {
        this.aeReports = aeReports;
    }

    ////// OBJECT METHODS

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final StudyParticipantAssignment that = (StudyParticipantAssignment) o;

        if (dateOfEnrollment != null ? !dateOfEnrollment.equals(that.dateOfEnrollment) : that.dateOfEnrollment != null)
            return false;
        if (studySite != null ? !studySite.equals(that.studySite) : that.studySite != null)
            return false;
        // Participant#equals calls this method, so we can't use it here
        if (!AbstractDomainObject.equalById(participant, that.participant)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (studySite != null ? studySite.hashCode() : 0);
        result = 29 * result + (participant != null ? participant.hashCode() : 0);
        result = 29 * result + (dateOfEnrollment != null ? dateOfEnrollment.hashCode() : 0);
        return result;
    }
}
