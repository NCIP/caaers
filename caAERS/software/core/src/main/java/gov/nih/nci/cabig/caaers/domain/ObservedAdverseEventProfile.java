package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "observed_ae_profiles")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_observed_ae_profiles") })
public class ObservedAdverseEventProfile extends AbstractMutableDomainObject {
    
    private Grade grade;
    private TreatmentAssignment treatmentAssignment;
    private CtcTerm ctcTerm;
    private LowLevelTerm lowLevelTerm;
    private Double expectedFrequency;
    private Integer totalNoOfAE;
    private Double observedFrequency;
    private Integer observedNoOfAE;
    private Double standardDeviation;
    private Double pValue;
    private double observedSignificance = 0;
    private NotificationStatus notificationStatus;

    @Type(type = "grade")
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @ManyToOne
    @JoinColumn(name = "treatment_assignment_id", nullable = false)
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    @ManyToOne
    @JoinColumn(name = "term_id")
    public CtcTerm getCtcTerm() {
        return ctcTerm;
    }

    public void setCtcTerm(CtcTerm ctcTerm) {
        this.ctcTerm = ctcTerm;
    }

    @ManyToOne
    @JoinColumn(name = "meddra_llt_id")
    public LowLevelTerm getLowLevelTerm() {
        return lowLevelTerm;
    }

    public void setLowLevelTerm(LowLevelTerm lowLevelTerm) {
        this.lowLevelTerm = lowLevelTerm;
    }

    @Transient
    public CtcCategory getCtcCategory(){
        if(getCtcTerm() != null) return getCtcTerm().getCategory();
        return null;
    }

    @Transient
    public ObservedAdverseEventSignificanceLevel getSignificance(){
        return new ObservedAdverseEventSignificanceLevel(observedSignificance);
    }

	public Double getExpectedFrequency() {
		return expectedFrequency;
	}

	public void setExpectedFrequency(Double expectedFrequency) {
		this.expectedFrequency = expectedFrequency;
	}

	public Integer getTotalNoOfAE() {
		return totalNoOfAE;
	}

	public void setTotalNoOfAE(Integer totalNoOfAE) {
		this.totalNoOfAE = totalNoOfAE;
	}

	public Double getObservedFrequency() {
		return observedFrequency;
	}

	public void setObservedFrequency(Double observedFrequency) {
		this.observedFrequency = observedFrequency;
	}

	public Integer getObservedNoOfAE() {
		return observedNoOfAE;
	}

	public void setObservedNoOfAE(Integer observedNoOfAE) {
		this.observedNoOfAE = observedNoOfAE;
	}

	public Double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(Double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public Double getpValue() {
		return pValue;
	}

	public void setpValue(Double pValue) {
		this.pValue = pValue;
	}

	public double getObservedSignificance() {
		return observedSignificance;
	}

	public void setObservedSignificance(double observedSignificance) {
		this.observedSignificance = observedSignificance;
	}

	@Type(type = "notificationStatus")
	public NotificationStatus getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(NotificationStatus notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

}
