package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "treatment_assignment_study_interventions")
@Inheritance(strategy = InheritanceType.JOINED)
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ta_si_id") })
public abstract class TreatmentAssignmentStudyIntervention extends AbstractMutableDomainObject implements Serializable {

    protected TreatmentAssignment treatmentAssignment;
    protected List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs = new ArrayList<AbstractStudyInterventionExpectedAE>();

    @Transient
    public abstract StudyIntervention getStudyIntervention();

    @ManyToOne
    @JoinColumn(name = "treatment_assignment_id", nullable = false)
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    @OneToMany(mappedBy = "treatmentAssignmentStudyIntervention", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<AbstractStudyInterventionExpectedAE> getAbstractStudyInterventionExpectedAEs() {
        return abstractStudyInterventionExpectedAEs;
    }

    public void setAbstractStudyInterventionExpectedAEs(List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs) {
        this.abstractStudyInterventionExpectedAEs = abstractStudyInterventionExpectedAEs;
    }

    public void addAbstractStudyInterventionExpectedAEs(AbstractStudyInterventionExpectedAE expectedTerm) {
        this.getAbstractStudyInterventionExpectedAEs().add(expectedTerm);
        expectedTerm.setTreatmentAssignmentStudyIntervention(this);
    }

}
