/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.NormalDistributionImpl;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "observed_ae_profiles")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_observed_ae_profiles_id") })
public class ObservedAdverseEventProfile extends AbstractMutableDomainObject {
	
	public static final double NULL_ZERO_VALUE=0.00000001;
    
    public ObservedAdverseEventProfile() {
		super();
	}

	public ObservedAdverseEventProfile(TreatmentAssignment treatmentAssignment, DomainObject term, Grade grade) {
		super();
		this.grade = grade;
		this.treatmentAssignment = treatmentAssignment;
		if (term instanceof CtcTerm) {
			this.ctcTerm = (CtcTerm) term;
		}else if(term instanceof LowLevelTerm) {
			this.lowLevelTerm = (LowLevelTerm) term;
		}
	}

	private Grade grade;
    private TreatmentAssignment treatmentAssignment;
    private CtcTerm ctcTerm;
    private LowLevelTerm lowLevelTerm;
    private Double expectedFrequency;
    private Integer totalNoOfRegistrations;
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
        return new ObservedAdverseEventSignificanceLevel(pValue);
    }

	public Double getExpectedFrequency() {
		return expectedFrequency;
	}

	public void setExpectedFrequency(Double expectedFrequency) {
		this.expectedFrequency = expectedFrequency;
	}

	public Integer getTotalNoOfRegistrations() {
		return totalNoOfRegistrations;
	}

	public void setTotalNoOfRegistrations(Integer totalNoOfAE) {
		this.totalNoOfRegistrations = totalNoOfAE;
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
	
	@Transient
	public DomainObject getTerm(){
		if(ctcTerm != null) return ctcTerm;
		else if(lowLevelTerm != null) return lowLevelTerm;
		return null;
	}
	
	public void calculateStatistics(){
		if(totalNoOfRegistrations == null || totalNoOfRegistrations == 0){
			throw new IllegalStateException("totalNoOfRegistrations cannot be null or 0.");
		}
		double effectiveExpectedFrequency = NULL_ZERO_VALUE;
		if(expectedFrequency != null && expectedFrequency.doubleValue() != 0.0){
			effectiveExpectedFrequency = expectedFrequency;
		}
		if(observedNoOfAE == null){
			observedNoOfAE = 0;
		}
		// Calculate observed frequency, observed signification, pValue, standard deviation
		observedFrequency = ((double)observedNoOfAE/(double)totalNoOfRegistrations)*100;
		double x = observedFrequency/100;
		double M = effectiveExpectedFrequency/100;
		standardDeviation = Math.sqrt(M*(1-M)/totalNoOfRegistrations);
		observedSignificance = (x-M)/standardDeviation;
		try {
			pValue = new NormalDistributionImpl(0, 1).cumulativeProbability(observedSignificance);
		} catch (MathException e) {
			throw new RuntimeException(e);
		}
	}

	@Transient
    public boolean isValid() {
        return expectedFrequency != null &&   treatmentAssignment != null;
    }

	@Override
	public String toString() {
		return "ObservedProfile [term=" + getTerm() + ", grade=" + grade + ", expectedFrequency=" + expectedFrequency + ", totalNoOfRegistrations="
				+ totalNoOfRegistrations + ", observedNoOfAE=" + observedNoOfAE + ", standardDeviation="
				+ standardDeviation + ", pValue=" + pValue + ", observedSignificance=" + observedSignificance + ", notificationStatus=" + notificationStatus + "]";
	}
}
