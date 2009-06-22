package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("ctep")
public class ExpectedAECtcTerm extends AbstractExpectedAE<CtcTerm> {

    private LowLevelTerm otherMeddraTerm;
    
    @OneToOne
    @JoinColumn(name = "term_id")
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.LOCK, CascadeType.EVICT})
    @Override
    public CtcTerm getTerm() {
        return super.getTerm();
    }

    @Override
    @Transient
    public String getFullName() {
    	if(getTerm() == null) return "";
    	return getTerm().getFullName();
    }

    /**
     * @return CtcTerm
     */
    @Transient
    public CtcTerm getCtcTerm() {
        return super.getTerm();
    }

    /**
     * @param ctcTerm The CTC term
     */
    @Transient
    public void setCtcTerm(CtcTerm ctcTerm) {
        super.setTerm(ctcTerm);
    }

    @Override
    public ExpectedAECtcTerm copy() {
        return (ExpectedAECtcTerm) super.copy();
    }

    @Override
    @Transient
    public boolean isMedDRA() {
    	return false;
    }

    @Override
    @Transient
    public boolean isOtherRequired() {
        if (getTerm() == null) return false;
        return getTerm().isOtherRequired();
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "low_level_term_id")
    public LowLevelTerm getOtherMeddraTerm() {
        return otherMeddraTerm;
    }

    public void setOtherMeddraTerm(LowLevelTerm otherMeddraTerm) {
        this.otherMeddraTerm = otherMeddraTerm;
    }
}