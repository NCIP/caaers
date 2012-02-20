package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "study_interventions_exp_aes")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "seq_si_aes_id")})
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractStudyInterventionExpectedAE<T extends DomainObject> extends AbstractMutableDomainObject implements AbstractTerm  {

    private T term;

    private TreatmentAssignmentStudyIntervention treatmentAssignmentStudyIntervention;
    private double expectednessFrequency;
    private double grade1Frequency;
    private double grade2Frequency;
    private double grade3Frequency;
    private double grade4Frequency;
    private double grade5Frequency;
    /** The expected. */
    private boolean expected = true;

    public AbstractStudyInterventionExpectedAE(TreatmentAssignmentStudyIntervention treatmentAssignmentStudyIntervention, AgentSpecificTerm agentSpecificTerm, boolean expected){
    	this.expected = expected;
    	this.treatmentAssignmentStudyIntervention = treatmentAssignmentStudyIntervention;
    	this.term = (T)agentSpecificTerm.getTerm();
    }
    
	public AbstractStudyInterventionExpectedAE() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getExpectednessFrequency() {
        return expectednessFrequency;
    }

    public void setExpectednessFrequency(double expectednessFrequency) {
        this.expectednessFrequency = expectednessFrequency;
    }

    public double getGrade1Frequency() {
        return grade1Frequency;
    }

    public void setGrade1Frequency(double grade1Frequency) {
        this.grade1Frequency = grade1Frequency;
    }

    public double getGrade2Frequency() {
        return grade2Frequency;
    }

    public void setGrade2Frequency(double grade2Frequency) {
        this.grade2Frequency = grade2Frequency;
    }

    public double getGrade3Frequency() {
        return grade3Frequency;
    }

    public void setGrade3Frequency(double grade3Frequency) {
        this.grade3Frequency = grade3Frequency;
    }

    public double getGrade4Frequency() {
        return grade4Frequency;
    }

    public void setGrade4Frequency(double grade4Frequency) {
        this.grade4Frequency = grade4Frequency;
    }

    public double getGrade5Frequency() {
        return grade5Frequency;
    }

    public void setGrade5Frequency(double grade5Frequency) {
        this.grade5Frequency = grade5Frequency;
    }

    @ManyToOne
    @JoinColumn(name = "ta_study_intervention_id", nullable = false)
    public TreatmentAssignmentStudyIntervention getTreatmentAssignmentStudyIntervention() {
        return treatmentAssignmentStudyIntervention;
    }

    public void setTreatmentAssignmentStudyIntervention(TreatmentAssignmentStudyIntervention treatmentAssignmentStudyIntervention) {
        this.treatmentAssignmentStudyIntervention = treatmentAssignmentStudyIntervention;
    }

    @Transient
    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }

	public boolean isExpected() {
		return expected;
	}

	public void setExpected(boolean expected) {
		this.expected = expected;
	}
	
	/**
     * Checks if is med dra.
     *
     * @return true, if is med dra
     */
    @Transient
    public abstract boolean isMedDRA();
    
    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public abstract String getFullName();
}
