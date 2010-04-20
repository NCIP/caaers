package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * @author Ion C. Olaru
 * 
 */
@Entity
@DiscriminatorValue("ctep")
public class AgentSpecificCtcTerm extends AgentSpecificTerm<CtcTerm> {

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
    	return getTerm().getFullName() + ((otherMeddraTerm != null) ? " " + otherMeddraTerm.getFullName() : "");
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
    public AgentSpecificCtcTerm copy() {
        return (AgentSpecificCtcTerm) super.copy();
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