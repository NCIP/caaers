package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "ta_study_interventions")
@Inheritance(strategy = InheritanceType.JOINED)
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ta_si_id") })
public class TreatmentAssignmentStudyIntervention extends AbstractMutableDomainObject implements Serializable {

    protected TreatmentAssignment treatmentAssignment;
    protected List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs = new ArrayList<AbstractStudyInterventionExpectedAE>();

    @Transient
    public StudyIntervention getStudyIntervention() {
        throw new RuntimeException("This method can't be called on the parent class.");
    };

    @ManyToOne
    @JoinColumn(name = "treatment_assignment_id", nullable = false)
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    @OneToMany(mappedBy = "treatmentAssignmentStudyIntervention", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy
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
