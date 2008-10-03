package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.*;

/**
 * This class represents the AdverseEventMeddraLowLevelTerm domain object associated with the
 * Adverse event report.
 *
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("meddra")
public class AdverseEventMeddraLowLevelTerm extends AbstractAdverseEventTerm<LowLevelTerm> {

    private String meddraCode;

    public String getMeddraCode() {
        return meddraCode;
    }

    public void setMeddraCode(String meddraCode) {
        this.meddraCode = meddraCode;
    }
    
    @Transient
    public String getFullName() {
    	if(getTerm() == null) return "";
    	return getTerm().getFullName();
    }
    
    @Transient
    public String getUniversalTerm() {
        return getTerm() == null ? null : getTerm().getFullName();
    }

    @OneToOne
    @JoinColumn(name = "term_id")
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }

    @Transient
    public LowLevelTerm getLowLevelTerm() {
        return super.getTerm();
    }

    @Transient
    public void setLowLevelTerm(LowLevelTerm lowlevelTerm) {
        super.setTerm(lowlevelTerm);
    }

    @Override
    @Transient
    public boolean isOtherRequired() {
        //there is no other specify for MedDRA
        return false;
    }

    @Override
    public AdverseEventMeddraLowLevelTerm copy() {
        return (AdverseEventMeddraLowLevelTerm) super.copy();
    }
    
    @Override
    @Transient
    public boolean isMedDRA() {
    	return true;
    }
}
