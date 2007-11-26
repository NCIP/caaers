package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObjectTools;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Where(clause="load_status > 0")
public class StudyParticipantAssignment extends AbstractMutableDomainObject {
    private Participant participant;
    private StudySite studySite;
    private Date dateOfEnrollment;
    private List<ExpeditedAdverseEventReport> aeReports;
    private List<RoutineAdverseEventReport> aeRoutineReports;
    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    public StudyParticipantAssignment(Participant participant, StudySite studySite) {
        this.participant = participant;
        this.studySite = studySite;
        this.dateOfEnrollment = new Date();
    }
    
    public StudyParticipantAssignment() { }

    ////// LOGIC

    public void addReport(ExpeditedAdverseEventReport report) {
        report.setAssignment(this);
        getAeReports().add(report);
    }
    
    public void addRoutineReport(RoutineAdverseEventReport routineReport) {
        routineReport.setAssignment(this);
        getAeRoutineReports().add(routineReport);
    }

    ////// BEAN PROPERTIES

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_site_id")
    @Cascade({ CascadeType.LOCK, CascadeType.SAVE_UPDATE })
    public StudySite getStudySite() {
        return studySite;
    }
    
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    @Cascade({ CascadeType.LOCK, CascadeType.SAVE_UPDATE })
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
    public List<ExpeditedAdverseEventReport> getAeReports() {
        if (aeReports == null) aeReports = new ArrayList<ExpeditedAdverseEventReport>();
        return aeReports;
    }

    public void setAeReports(List<ExpeditedAdverseEventReport> aeReports) {
        this.aeReports = aeReports;
    }
    
    @OneToMany(mappedBy = "assignment")
    public List<RoutineAdverseEventReport> getAeRoutineReports() {
        if (aeRoutineReports == null) aeRoutineReports = new ArrayList<RoutineAdverseEventReport>();
        return aeRoutineReports;
    }

    public void setAeRoutineReports(List<RoutineAdverseEventReport> aeRoutineReports) {
        this.aeRoutineReports = aeRoutineReports;
    }
    
    public Integer getLoadStatus() {
		return loadStatus;
	}
    public void setLoadStatus(Integer loadStatus) {
		this.loadStatus = loadStatus;
	}
    
    ////// OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final StudyParticipantAssignment that = (StudyParticipantAssignment) o;

        if (dateOfEnrollment != null ? !dateOfEnrollment.equals(that.dateOfEnrollment) : that.dateOfEnrollment != null)
            return false;
        if (studySite != null ? !studySite.equals(that.studySite) : that.studySite != null)
            return false;
        // Participant#equals calls this method, so we can't use it here
        if (!DomainObjectTools.equalById(participant, that.participant)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (studySite != null ? studySite.hashCode() : 0);
        result = 29 * result + (participant != null ? participant.hashCode() : 0);
        result = 29 * result + (dateOfEnrollment != null ? dateOfEnrollment.hashCode() : 0);
        return result;
    }
}
