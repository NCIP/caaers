package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the StudyParticipantPriorTherapy domain object associated with the StudyParticipantAssignment
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_prior_therapies")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_spa_prior_therapies_id")})
public class StudyParticipantPriorTherapy extends AbstractMutableDomainObject {
    private StudyParticipantAssignment assignment;

    private PriorTherapy priorTherapy;

    private String other;

    private DateValue startDate;

    private DateValue endDate;

    private List<StudyParticipantPriorTherapyAgent> priorTherapyAgents = new ArrayList<StudyParticipantPriorTherapyAgent>();


    public StudyParticipantPriorTherapy() {
        this.startDate = new DateValue();
        this.endDate = new DateValue();
    }


    // //// LOGIC

    @Transient
    public String getName() {
        if (getPriorTherapy() != null) {
            return getPriorTherapy().getText();
        } else if (getOther() != null) {
            return "Other: " + getOther();
        } else {
            return null;
        }
    }

    // //// BOUND PROPERTIES

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @ManyToOne
    public PriorTherapy getPriorTherapy() {
        return priorTherapy;
    }

    public void setPriorTherapy(PriorTherapy priorTherapy) {
        this.priorTherapy = priorTherapy;
    }

    public void addPriorTherapyAgent(StudyParticipantPriorTherapyAgent priorTherapyAgent) {
        if (getPriorTherapyAgents() == null) {
            priorTherapyAgents = new ArrayList<StudyParticipantPriorTherapyAgent>();
        }
        getPriorTherapyAgents().add(priorTherapyAgent);
        if (priorTherapyAgent != null) priorTherapyAgent.setPriorTherapy(this);
    }

    public void addUniquePriorTherapyAgent(StudyParticipantPriorTherapyAgent newAgent) {
        if (newAgent == null || newAgent.getChemoAgent() == null) return;

        if (getPriorTherapyAgents() == null) {
            priorTherapyAgents = new ArrayList<StudyParticipantPriorTherapyAgent>();
        }

        for (StudyParticipantPriorTherapyAgent existingAgent : getPriorTherapyAgents()) {
            if (existingAgent.getChemoAgent() == null) continue;
            if (existingAgent.getName().equals(newAgent.getName())) return;
        }

        getPriorTherapyAgents().add(newAgent);
        if (newAgent != null) newAgent.setPriorTherapy(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate prior therapy agents")
    public List<StudyParticipantPriorTherapyAgent> getPriorTherapyAgents() {
        return priorTherapyAgents;
    }

    public void setPriorTherapyAgents(List<StudyParticipantPriorTherapyAgent> priorTherapyAgents) {
        this.priorTherapyAgents = priorTherapyAgents;
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping. See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "spa_prior_therapy_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<StudyParticipantPriorTherapyAgent> getPriorTherapyAgentsInternal() {
        return priorTherapyAgents;
    }

    protected void setPriorTherapyAgentsInternal(List<StudyParticipantPriorTherapyAgent> priorTherapyAgentsInternal) {
        this.priorTherapyAgents = priorTherapyAgentsInternal;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "end_date_day")),
            @AttributeOverride(name = "month", column = @Column(name = "end_date_month")),
            @AttributeOverride(name = "year", column = @Column(name = "end_date_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "end_date_zone"))
    })
    public DateValue getEndDate() {
        return endDate;
    }

    public void setEndDate(DateValue endDate) {
        this.endDate = endDate;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "start_date_day")),
            @AttributeOverride(name = "month", column = @Column(name = "start_date_month")),
            @AttributeOverride(name = "year", column = @Column(name = "start_date_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "start_date_zone"))
    })
    public DateValue getStartDate() {
        return startDate;
    }

    public void setStartDate(DateValue startDate) {
        this.startDate = startDate;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((other == null) ? 0 : other.hashCode());
        result = prime * result + ((priorTherapy == null) ? 0 : priorTherapy.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final StudyParticipantPriorTherapy other = (StudyParticipantPriorTherapy) obj;
        if (endDate == null) {
            if (other.endDate != null) return false;
        } else if (!endDate.equals(other.endDate)) return false;
        if (this.other == null) {
            if (other.other != null) return false;
        } else if (!this.other.equals(other.other)) return false;
        if (priorTherapy == null) {
            if (other.priorTherapy != null) return false;
        } else if (!priorTherapy.equals(other.priorTherapy)) return false;
        if (startDate == null) {
            if (other.startDate != null) return false;
        } else if (!startDate.equals(other.startDate)) return false;
        return true;
    }

    public static StudyParticipantPriorTherapy createAssignmentPriorTherapy(SAEReportPriorTherapy saeReportPriorTherapy) {
        if (saeReportPriorTherapy != null) {
            StudyParticipantPriorTherapy studyParticipantPriorTherapy = new StudyParticipantPriorTherapy();
            BeanUtils.copyProperties(saeReportPriorTherapy, studyParticipantPriorTherapy, new String[]{"id", "gridId", "version", "priorTherapyAgents", "assignment", "priorTherapyAgentsInternal"});
            for (PriorTherapyAgent priorTherapyAgent : saeReportPriorTherapy.getPriorTherapyAgents()) {
                studyParticipantPriorTherapy.addPriorTherapyAgent(StudyParticipantPriorTherapyAgent.createAssignmentPriorTherapyAgent(priorTherapyAgent));
            }
            return studyParticipantPriorTherapy;
        }
        return null;
    }
}
