package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.Transient;

/**
 * Just a place holder, for me to finish-up with Rules
 */
public class ObservedAdverseEventProfile {
    
    private Grade grade;
    private TreatmentAssignment treatmentAssignment;
    private CtcTerm ctcTerm;
    private LowLevelTerm lowLevelTerm;


    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    public CtcTerm getCtcTerm() {
        return ctcTerm;
    }

    public void setCtcTerm(CtcTerm ctcTerm) {
        this.ctcTerm = ctcTerm;
    }

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
        return new ObservedAdverseEventSignificanceLevel(0);
    }


}
