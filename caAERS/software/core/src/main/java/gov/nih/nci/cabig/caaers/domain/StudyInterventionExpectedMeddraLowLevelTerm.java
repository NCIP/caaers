package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;


/**
 * @author Ion C. Olaru
 */
@Entity
@DiscriminatorValue(value = "meddra")
public class StudyInterventionExpectedMeddraLowLevelTerm extends AbstractStudyInterventionExpectedAE<LowLevelTerm> {

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getFullName()
     */
    @Transient
    public String getFullName() {
    	if(getTerm() == null) return "";
    	return getTerm().getFullName();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#getTerm()
     */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "term_id")
    @Cascade(value = {CascadeType.LOCK})
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }

    /**
     * Gets the low level term.
     *
     * @return the low level term
     */
    @Transient
    public LowLevelTerm getLowLevelTerm() {
        return super.getTerm();
    }

    /**
     * Sets the low level term.
     *
     * @param lowlevelTerm the new low level term
     */
    @Transient
    public void setLowLevelTerm(LowLevelTerm lowlevelTerm) {
        super.setTerm(lowlevelTerm);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#isOtherRequired()
     */
    @Transient
    public boolean isOtherRequired() {
        return false;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm#isMedDRA()
     */
    @Transient
    public boolean isMedDRA() {
    	return true;
    }
}
