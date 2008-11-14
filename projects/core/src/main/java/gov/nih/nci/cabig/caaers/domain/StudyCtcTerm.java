package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue("ctep")
public class StudyCtcTerm extends AbstractStudyTerm<CtcTerm> {

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
    public StudyCtcTerm copy() {
        return (StudyCtcTerm) super.copy();
    }

    @Override
    @Transient
    public boolean isMedDRA() {
    	return false;
    }
}