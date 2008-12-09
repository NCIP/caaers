package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("meddra")
public class ExpectedAEMeddraLowLevelTerm extends AbstractExpectedAE<LowLevelTerm> {

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

    @OneToOne
    @JoinColumn(name = "term_id")
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.LOCK, CascadeType.EVICT})
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }

    @Transient
    public void setLowLevelTerm(LowLevelTerm lowlevelTerm) {
        super.setTerm(lowlevelTerm);
    }

    @Override
    public ExpectedAEMeddraLowLevelTerm copy() {
        return (ExpectedAEMeddraLowLevelTerm) super.copy();
    }

    @Override
    @Transient
    public boolean isMedDRA() {
    	return true;
    }

    @Override
    @Transient
    public boolean isOtherRequired() {
        return false;
    }

}